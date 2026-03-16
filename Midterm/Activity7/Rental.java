import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Rental {
    public static String log = "";

    public static void println(String text) {
        System.out.println(text);
        log += text + "\n";
    }

    public static void addLog(Object o) {
        log += o + "\n";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rental = 0, sales = 0,
                comedy = 0, horror = 0, scifi = 0, drama = 0, cartoon = 0,
                dvdTotal = 0, vcdTotal = 0, tapeTotal = 0;
        println("EFM Movie Rental Registration");
        while (true) {
            println("1. DVD");
            println("2. VCD");
            println("3. Tape");
            println("Input Type");
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
            println("Input Title");
            String title = input.nextLine();
            addLog(title);
            println("Input Genre");
            println("1. Horror");
            println("2. Scifi");
            println("3. Drama");
            println("4. Comedy");
            println("5. Cartoon");
            int category = input.nextInt();
            addLog(category);
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
            println("Duration");
            int minutes = input.nextInt();
            addLog(minutes);
            input.nextLine();
            println("Setting");
            String setting = input.nextLine();
            addLog(setting);
            println("Transaction Type");
            println("1. Rental");
            println("2. Sales");
            int transactionType = input.nextInt();
            addLog(transactionType);
            switch (transactionType) {
                case 1:
                    rental += 1;
                    break;
                case 2:
                    sales += 1;
                    break;
            }
            println("Price");
            int price = input.nextInt();
            addLog(price);
            input.nextLine();
            println("Register Another? yes / no");
            String choice = input.nextLine();
            addLog(choice);
            if (choice.equals("yes")) {
                println("\n\nNew Registration");
                continue;
            }
            break;
        }
        println("\nREPORTS");
        println("Transactions-----------------------");
        println("For Rent        : " + rental);
        println("For Sale        : " + sales);
        println("Media type-------------------------");
        println("DVD Total       : " + dvdTotal);
        println("VCD Total       : " + vcdTotal);
        println("Tape Total      : " + tapeTotal);
        println("Genre Totals-----------------------");
        println("Horror Movies   : " + horror);
        println("Scifi Movies    : " + scifi);
        println("Drama Movies    : " + drama);
        println("Comedy Movies   : " + comedy);
        println("Cartoon Movies  : " + cartoon);
        try {
            File f = new File("RentalLogs.txt");
            FileWriter fw = new FileWriter(f);
            fw.write(log);
            fw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
