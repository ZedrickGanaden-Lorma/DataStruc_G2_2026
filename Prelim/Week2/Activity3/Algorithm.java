
import java.util.Scanner;

public class Algorithm {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rental = 0, sales = 0,
                comedy = 0, horror = 0, scifi = 0, drama = 0, cartoon = 0,
                dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
        System.out.println("Registration");
        while (true) {
            System.out.println("1. DVD");
            System.out.println("2. VCD");
            System.out.println("3. Tape");
            System.out.println("Input Type");
            int code = input.nextInt();
            input.nextLine();
            switch (code) {
                case 1:
                    dvdTotal += 1;
                    break;
                case 2:
                    vcdTotal += 1;
                    break;
                case 3:
                    tapeTotal += 1;
                    break;
            }
            System.out.println("Input Title");
            String title = input.nextLine();
            System.out.println("Input Genre");
            System.out.println("1. Horror");
            System.out.println("2. Scifi");
            System.out.println("3. Drama");
            System.out.println("4. Comedy");
            System.out.println("5. Cartoon");
            int category = input.nextInt();
            switch (category) {
                case 1:
                    horror += 1;
                    break;
                case 2:
                    scifi += 1;
                    break;
                case 3:
                    drama += 1;
                    break;
                case 4:
                    comedy += 1;
                    break;
                case 5:
                    cartoon += 1;
                    break;
            }
            System.out.println("Duration");
            int minutes = input.nextInt();
            input.nextLine();
            System.out.println("Setting");
            String setting = input.nextLine();
            System.out.println("Transaction Type");
            System.out.println("1. Rental");
            System.out.println("2. Sales");
            int transactionType = input.nextInt();
            switch (transactionType) {
                case 1:
                    rental += 1;
                    break;
                case 2:
                    sales += 1;
                    break;
            }
            System.out.println("Price");
            int price = input.nextInt();
            input.nextLine();
            System.out.println("Register Another? yes / no");
            String choice = input.nextLine();
            if (choice.equals("yes")) {
                System.out.println("\n\nNew Registration");
                continue;
            }
            break;
        }
        System.out.println("\nREPORTS");
        System.out.println("Transactions-----------------------");
        System.out.println("For Rent        : " + rental);
        System.out.println("For Sale        : " + sales);
        System.out.println("Media type-------------------------");
        System.out.println("DVD Total       : " + dvdTotal);
        System.out.println("VCD Total       : " + vcdTotal);
        System.out.println("Tape Total      : " + tapeTotal);
        System.out.println("Genre Totals-----------------------");
        System.out.println("Horror Movies   : " + horror);
        System.out.println("Scifi Movies    : " + scifi);
        System.out.println("Drama Movies    : " + drama);
        System.out.println("Comedy Movies   : " + comedy);
        System.out.println("Cartoon Movies  : " + cartoon);
    }
}
