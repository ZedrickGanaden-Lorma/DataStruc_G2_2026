import static java.lang.System.out;

import java.io.FileWriter;
import java.text.Format;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EnhancedGrocery {
    public static Scanner input = new Scanner(System.in);

    // This class contains a product that is available in store
    public static class Product {

        public Product() {
        }

        public Product(String name, double price, String type) {
            this.name = name;
            this.price = price;
            this.type = type;
        }

        String name;
        double price;
        String type;
    }

    // This class contains a product taken by customers
    public static class Item {

        public Item(Product product) {
        }

        public Item(Product product, int quantity) {
            this.name = product.name;
            this.quantity = quantity;
            this.price = product.price;
            this.total = product.price * quantity;
        }

        String name;
        int quantity;
        double price, total;
    }

    // This class contains a customer's info, receipt, and cart
    public static class Customer {

        String name;
        double total, pay, change;
        Cart cart;

        public Customer(String name) {
            this.name = name;
            this.cart = new Cart();
        }

        public void addToCart(Product product, int quantity) {
            cart.addItem(new Item(product, quantity));
            total += product.price * quantity;
        }

        // Probably not gonna use this
        public void removeFromCart(Product product) {
            cart.removeItem(new Item(product));
        }

        public void selectProduct(ArrayList<Product> list) {
            while (true) {
                try {
                    out.println("Enter product number");
                    int prodNum = input.nextInt();
                    out.println("Enter product quantity");
                    int quantity = input.nextInt();
                    Product p = list.get(prodNum);
                    out.printf("""
                            Selected Product    : %-15s
                            Quantity            : %-15d
                            Price               : ₱%-5.2f
                            Total               : ₱%-5.2f
                            """, p.name, quantity, p.price, (quantity * p.price));
                    addToCart(p, quantity);
                    break;
                } catch (InputMismatchException e) {
                    out.println("Cannot accept anything other than integers as input");
                }
            }
        }
    }

    // This class contains the items and the total of the items
    public static class Cart {

        ArrayList<Item> items;
        public double total;
        public int totalItems;

        public Cart() {
            items = new ArrayList<>();
            total = 0;
            totalItems = 0;
        }

        public void addItem(Item item) {
            // Check if item already in cart
            for (int x = 0; x < items.size(); x++) {
                if (items.get(x).name.equals(item.name)) {
                    items.get(x).quantity += item.quantity;
                    items.get(x).total += item.total;
                    total += item.total;
                    return;
                }
            }
            totalItems++;
            total += item.total;
            items.add(item);

        }

        public void removeItem(Item item) {
            for (int i = 0; i < items.size(); i++) {
                // Find similar item
                if (items.get(i).name.equals(item.name)) {
                    if (item.quantity < items.get(i).quantity) {
                        items.get(i).quantity -= item.quantity;
                        total -= item.total;
                    } else {
                        items.remove(i);
                    }
                }
            }
        }

        public void listItems() {
            for (int x = 0; x < items.size(); x++) {
                Item item = items.get(x);
                out.printf("%-15s ₱%-5.2f x%2d = ₱%-5.2f %n", item.name, item.price, item.quantity,
                        item.total);
            }
            out.printf("Total : ₱%-5.2f%n%n", total);
        }

        public String getItems() {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < items.size(); x++) {
                Item item = items.get(x);
                sb.append(String.format("%-15s ₱%-5.2f x%2d = ₱%-5.2f %n", item.name, item.price, item.quantity,
                        item.total));
            }
            sb.append(String.format("Total : ₱%-5.2f%n", total));
            return sb.toString();
        }
    }

    public static void printProductList(ArrayList<Product> products) {
        for (int x = 0; x < products.size(); x++) {
            Product p = products.get(x);
            out.printf("    [%d] %-15s ₱%-5.2f%n", x, p.name,
                    p.price);
        }
    }

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("C2Tea", 16, "Drink"));
        products.add(new Product("Cocacola 200ml", 11, "Drink"));
        products.add(new Product("Cocacola 325ml", 16, "Drink"));
        products.add(new Product("Skyflakes", 8, "Snacks"));
        products.add(new Product("Ice", 3, "Miscellaneous"));

        ArrayList<Customer> sales = new ArrayList<>();
        boolean stay = true;
        do {
            // Name input check
            String name;
            while (true) {
                out.println("Enter Customer Name");
                name = input.nextLine();
                boolean hasDigit = false;
                for (char c : name.toCharArray()) {
                    if (Character.isDigit(c)) {
                        hasDigit = true;
                    }
                }
                if (hasDigit) {
                    out.println("A name must not have a number");
                    continue;
                }
                break;
            }
            Customer customer = new Customer(name);

            boolean shopStay = true;
            do {// Shopping loop
                printProductList(products);
                customer.selectProduct(products);
                out.println("Add another item to cart? Y/N");
                while (true) {
                    switch (input.nextLine().toLowerCase()) {
                        case "y":
                            shopStay = true;
                            break;
                        case "n":
                            shopStay = false;
                            break;
                        default:
                            continue;
                    }
                    break;
                }
                customer.cart.listItems();
            } while (shopStay);
            // Customer pay
            out.print("Bill : " + customer.total + "\n");
            double pay;
            while (true) {
                out.println("Enter payment");
                String strInput = input.nextLine();
                for (char c : strInput.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        out.println("Cannot accept anything other than integers");
                    }
                    continue;
                }
                pay = Double.parseDouble(strInput);
                break;
            }
            if (pay < customer.total) {
                double missing = customer.total - pay;
                out.println("You are short " + missing);
            } else {
                // logging to memory
                customer.pay = pay;
                double change = customer.pay - customer.total;
                customer.change = change;
                sales.add(customer);
                out.printf("Change : %-5.2f%n", change);
                out.println("Successfully bought items");
            }

            out.println("Another customer? Y/N");
            while (true) {
                switch (input.nextLine().toLowerCase()) {
                    case "y":
                        stay = true;
                        break;
                    case "n":
                        stay = false;
                        break;
                    default:
                        continue;
                }
                break;
            }
        } while (stay);
        out.print("Logging sales to file");

        try (FileWriter fw = new FileWriter("Sales.txt")) {
            for (Customer c : sales) {
                fw.write(String.format("""
                        Order of : %s%n
                        Items bought :
                        -------------------
                        %s
                        -------------------
                        Paid    : %-5.2f
                        Change  : %-5.2f


                        """, c.name, c.cart.getItems(), c.pay, c.change));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
