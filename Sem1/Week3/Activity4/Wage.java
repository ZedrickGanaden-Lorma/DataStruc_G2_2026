import java.util.Scanner;

public class Wage {
    public static Scanner input = new Scanner(System.in);

    public static String[] enterName() {
        System.out.println("Enter your name");
        String fName, mName, lName;
        // three loops for fName, mName, lName input and checks
        while (true) {
            System.out.print("First name : ");
            fName = input.nextLine();
            boolean hasInvalid = false;
            for (char c : fName.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.err.println("Cannot have numbers in name");
                    hasInvalid = true;
                    break;
                }
                if (Character.isWhitespace(c)) {
                    System.err.println("Cannot have spaces in name");
                    hasInvalid = true;
                    break;
                }
            }
            if (hasInvalid)
                continue;
            break;
        }
        while (true) {
            System.out.print("Middle name : ");
            mName = input.nextLine();
            boolean hasInvalid = false;
            for (char c : mName.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.err.println("Cannot have numbers in name");
                    hasInvalid = true;
                    break;
                }
                if (Character.isWhitespace(c)) {
                    System.err.println("Cannot have spaces in name");
                    hasInvalid = true;
                    break;
                }
            }
            if (hasInvalid)
                continue;
            break;
        }
        while (true) {
            System.out.print("Last name : ");
            lName = input.nextLine();
            boolean hasInvalid = false;
            for (char c : lName.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.err.println("Cannot have numbers in name");
                    hasInvalid = true;
                    break;
                }
                if (Character.isWhitespace(c)) {
                    System.err.println("Cannot have spaces in name");
                    hasInvalid = true;
                    break;
                }
            }
            if (hasInvalid)
                continue;
            break;
        }
        return new String[] { fName, mName, lName };
    }

    public static int enterEmployeeNum() {
        int employeeNum;
        while (true) {
            System.out.println("Enter your Employee Number");
            String numInput = input.nextLine();
            boolean hasInvalid = false;
            for (char c : numInput.toCharArray()) {
                if (Character.isAlphabetic(c)) {
                    System.out.println("Cannot have letters in employee number");
                    hasInvalid = true;
                    break;
                }
                if (Character.isWhitespace(c)) {
                    System.err.println("Cannot have spaces in employee number");
                    hasInvalid = true;
                    break;
                }
            }
            if (hasInvalid)
                continue;
            employeeNum = Integer.parseInt(numInput);
            break;
        }
        return employeeNum;
    }

    public static double enterDouble(String msg) {
        double d;
        while (true) {
            System.out.println(msg);
            String numInput = input.nextLine();
            boolean hasText = false;
            for (char c : numInput.toCharArray()) {
                if (Character.isAlphabetic(c)) {
                    System.out.println("Cannot have letters in your " + msg);
                    hasText = false;
                    break;
                }
            }
            if (hasText)
                continue;
            d = Double.parseDouble(numInput);
            break;
        }
        return d;
    }

    public static double computeSalary(double rate, double hours, double overTimePay, double totalDeductions) {
        return ((rate * hours) + overTimePay) - totalDeductions;
    }

    public static void main(String[] args) {
        int employeeNum;
        // format is {fName, mName, lName}
        String[] nameArr;
        double hours, hourlyRate, overTimePay, totalDeductions;
        nameArr = enterName();
        employeeNum = enterEmployeeNum();
        hourlyRate = enterDouble("Hourly rate");
        hours = enterDouble("Hours");
        overTimePay = enterDouble("Overtime pay");
        totalDeductions = enterDouble("Total Deductions");
        System.out.println(nameArr[0] + " " + nameArr[1] + " " + nameArr[2]);
        System.out.printf("Your net pay is : %-5.2f", computeSalary(hourlyRate, hours, overTimePay, totalDeductions));
    }
}
