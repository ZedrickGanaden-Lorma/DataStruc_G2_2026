package com.zedrick.multi;

import java.util.Scanner;

import com.zedrick.multi.Grocery.*;
import com.zedrick.multi.Enrollment.*;
import com.zedrick.multi.MovieRental.*;;

public class Main {
    static Scanner input = new Scanner(System.in);
    static String menu = """
            Please select one of the following.
            [1] Grocery Shopper
            [2] Movie Rental
            [3] Colleges Enrollment

            [x] Exit

            """;

    static void selectApp() {
        while (true) {
            char[] allowed = "123x".toCharArray();
            char in = ' ';
            // Input check
            while (true) {
                System.out.println(menu);
                System.out.print("Choice : ");
                in = input.nextLine().toLowerCase().charAt(0);
                boolean hasInvalid = true;
                for (char match : allowed) {
                    // System.out.println("Checking if " + match + " == " + in + " " + (in ==
                    // match));
                    if (in == match) {
                        hasInvalid = false;
                        break;
                    }

                }
                if (hasInvalid) {
                    System.err.printf("%n%s - not accepted as input%n", in);
                    continue;
                }
                break;
            }
            switch (in) {
                case '1':
                    Grocery.main(null);
                    break;
                case '2':
                    Rental.main(null);
                    break;
                case '3':
                    Enrollment.main(null);
                    break;
                case 'x':
                    System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to EFM Systems");
        selectApp();
    }
}