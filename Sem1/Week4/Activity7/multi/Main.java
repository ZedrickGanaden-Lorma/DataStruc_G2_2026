import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    static void pause() {
        System.out.println("Press enter to continue");
        input.nextLine();
        System.out.println();
    }

    static void selectApp() {
        boolean active = true;
        while (active) {
            char[] allowed = "123x".toCharArray();
            char in = new InputHandler("""
                    Please select one of the following.
                    [1] EFM Grocery ShopperMart POS
                    [2] EFM Movie Rental Registration
                    [3] Colleges Enrollment

                    [x] Exit

                    Choice : """)
                    .setAllowedChars(allowed)
                    .noPauseOnError()
                    .nextChar();
            switch (in) {
                case '1':
                    Grocery.main(null);
                    continue;
                case '2':
                    Rental.main(null);
                    continue;
                case '3':
                    Enrollment.main(null);
                    continue;
                case 'x':
                    active = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to EFM Systems");
        selectApp();
        System.out.println("""
                Thank you for using EFM Enterprise Systems
                Good bye""");
    }
}