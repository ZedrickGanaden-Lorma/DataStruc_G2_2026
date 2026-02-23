import java.util.ArrayList;
import java.util.Scanner;

// This java project aims to simplify input checks for my programs
/**
 * An input handler for simple inputs such as int, double, String, and char.
 * 
 * This does not have complicated input processing and is only to be used for
 * simple inputs
 * 
 * 
 */
public class InputHandler {
    /// 0.5 still is incomplete, so expect bugs or bad documentation
    private static String version = "0.5";

    /// The message presented to the user when calling setters.
    private String prompt;
    /// Stores values from setters.
    private Object value;

    private static final Scanner scan = new Scanner(System.in);

    /**
     * Creates an input handler for simple inputs such as int, double, String, and
     * char.
     * 
     * This does not have complicated input processing and is only to be used for
     * simple inputs
     * 
     * 
     */
    public InputHandler(String prompt) {
        this.prompt = prompt;
    }

    // Letter limits
    private char[] allowedChars;

    // Sentence limits
    private boolean onlyAlphabetic = false;
    private boolean onlyNumeric = false;

    // Numeric limits
    private double min;
    private boolean hasMin;

    private double max;
    private boolean hasMax;

    // Error messages
    private static boolean globalPauseOnError = true;
    private boolean pauseOnError = true;

    private String minErrMsg;
    private String maxErrMsg;
    private String mismatchMsg;
    private String notNumericMsg;
    private String notAlphabeticMsg;

    // Error pause for user acknowledgement
    private void pause() {
        if (globalPauseOnError) {
            if (pauseOnError) {
                System.out.println("Press enter to continue");
                scan.nextLine();
                System.out.println("");
            }
        }
    }

    // Limits
    // - Numeral
    public InputHandler min(double min) {
        hasMin = true;
        this.min = min;
        return this;
    }

    public InputHandler max(double max) {
        hasMax = true;
        this.max = max;
        return this;
    }

    // - Alphabetic

    public InputHandler onlyNumeric() {
        onlyAlphabetic = false;
        onlyNumeric = true;
        return this;
    }

    public InputHandler onlyAlphabetic() {
        onlyNumeric = false;
        onlyAlphabetic = true;
        return this;
    }

    public InputHandler setAllowedChars(char[] allowed) {
        this.allowedChars = allowed;
        return this;
    }

    public InputHandler setAllowedChars(String allowed) {
        this.allowedChars = allowed.toCharArray();
        return this;
    }

    // Messages

    public InputHandler noPauseOnError() {
        this.pauseOnError = false;
        return this;
    }

    public static void setGlobalNoPauseOnError() {
        globalPauseOnError = false;
    }

    /// Sets the error message sent when input is lower than set minimum
    public InputHandler setMinErrMsg(String msg) {
        this.minErrMsg = msg;
        return this;
    }

    /// Sets the error message sent when input exceeds the set maximum
    public InputHandler setMaxErrMsg(String msg) {
        this.maxErrMsg = msg;
        return this;
    }

    /// Sets the error message sent when input mismatches the intended type
    public InputHandler setMismatchMsg(String msg) {
        this.mismatchMsg = msg;
        return this;
    }

    // Inputters

    /// Sets the value variable of the {@link #Input} Class to an int after being
    /// validated by the enabled checks, any error detected by these checks will
    /// return the user to the start
    /// 
    /// Checks used by this method
    /// 1. Mismatch check - always enabled
    /// 2. Minimum check - optional
    /// 3. Maximum check - optional
    public InputHandler setInt() {
        int in;
        while (true) {
            System.out.print(prompt);
            // mismatch
            if (!scan.hasNextInt()) {
                scan.nextLine();
                if (mismatchMsg == null) {
                    System.err.println("input mismatch");
                    pause();
                    continue;
                }
                System.err.println(mismatchMsg);
                pause();
                continue;
            }
            in = scan.nextInt();
            scan.nextLine();
            // Allowed chars
            {
                boolean charMismatch = true;
                if (allowedChars != null) {
                    for (char ch : (in + "").toCharArray()) {
                        for (char match : allowedChars) {
                            if (ch == match) {
                                charMismatch = false;
                                break;
                            }
                        }
                    }
                    if (charMismatch) {
                        if (mismatchMsg == null) {
                            System.err.println("Input has invalid chars");
                            pause();
                            continue;
                        }
                        System.err.println(mismatchMsg);
                        pause();
                        continue;
                    }
                    break;
                }
            }
            // minimum
            if (hasMin) {
                if (in < min) {
                    if (minErrMsg == null) {
                        System.err.println("input is lower than minimum");
                        pause();
                        continue;
                    }
                    System.err.println(minErrMsg);
                    pause();
                    continue;
                }
            }
            // maximum
            if (hasMax) {
                if (in > max) {
                    if (maxErrMsg == null) {
                        System.err.println("input exceeds maximum");
                        pause();
                        continue;
                    }
                    System.err.println(maxErrMsg);
                    pause();
                    continue;
                }
            }
            break;
        }
        value = in;
        return this;
    }

    /// Sets the value variable of the {@link #Input} Class to a double after being
    /// validated by the enabled checks, any error detected by these checks will
    /// return the user to the start
    /// 
    /// Checks used by this method
    /// 1. Mismatch check - always enabled
    /// 2. Minimum check - optional
    /// 3. Maximum check - optional
    public InputHandler setDouble() {
        Double in;
        while (true) {
            System.out.print(prompt);
            // mismatch
            if (!scan.hasNextDouble()) {
                scan.nextLine();
                if (mismatchMsg == null) {
                    System.err.println("input mismatch");
                    pause();
                    continue;
                }
                System.err.println(mismatchMsg);
                pause();
                continue;
            }
            in = scan.nextDouble();
            scan.nextLine();
            // Allowed chars
            {
                boolean charMismatch = true;
                if (allowedChars != null) {
                    for (char ch : (in + "").toCharArray()) {
                        for (char match : allowedChars) {
                            if (ch == match) {
                                charMismatch = false;
                                break;
                            }
                        }
                    }
                    if (charMismatch) {
                        if (mismatchMsg == null) {
                            System.err.println("Input has invalid chars");
                            pause();
                            continue;
                        }
                        System.err.println(mismatchMsg);
                        pause();
                        continue;
                    }
                    break;
                }
            }
            // minimum
            if (hasMin) {
                if (in < min) {
                    if (minErrMsg == null) {
                        System.err.println("input is lower than minimum");
                        pause();
                        continue;
                    }
                    System.err.println(minErrMsg);
                    pause();
                    continue;
                }
            }
            // maximum
            if (hasMax) {
                if (in > max) {
                    if (maxErrMsg == null) {
                        System.err.println("input exceeds maximum");
                        pause();
                        continue;
                    }
                    System.err.println(maxErrMsg);
                    pause();
                    continue;
                }
            }
            break;
        }
        value = in;
        return this;
    }

    // todo : create string and char input

    /// Sets the value variable of the {@link #Input} Class to a string after being
    /// validated by the enabled checks, any error detected by these checks will
    /// return the user to the start
    /// 
    /// Checks used by this method
    /// 1. Minimum length check - optional
    /// 2. Maximum length check - optional
    /// 3. Allowed only check - optional - exiting
    /// 4. Numeric only check - optional
    /// 5. Alphabetic only check - optional
    public InputHandler setString() {
        String in;
        while (true) {
            System.out.print(prompt);
            in = scan.nextLine();
            // minimum length
            if (hasMin) {
                if (in.length() < min) {
                    if (minErrMsg == null) {
                        System.err.println("input is smaller than minimum length");
                        pause();
                        continue;
                    }
                    System.err.println(minErrMsg);
                    pause();
                    continue;
                }
            }
            // maximum length
            if (hasMax) {
                if (in.length() > max) {
                    if (maxErrMsg == null) {
                        System.err.println("input exceeds maximum length");
                        pause();
                        continue;
                    }
                    System.err.println(maxErrMsg);
                    pause();
                    continue;
                }
            }
            // Allowed chars
            {
                boolean charMismatch = true;
                if (allowedChars != null) {
                    for (char ch : in.toCharArray()) {
                        for (char match : allowedChars) {
                            if (ch == match) {
                                charMismatch = false;
                                break;
                            }
                        }
                    }
                    if (charMismatch) {
                        if (mismatchMsg == null) {
                            System.err.println("Input has invalid chars");
                            pause();
                            continue;
                        }
                        System.err.println(mismatchMsg);
                        pause();
                        continue;
                    }
                    break;
                }
            }
            // only numeric check
            {
                boolean hasNonNumeric = false;
                if (onlyNumeric) {
                    for (char c : in.toCharArray()) {
                        if (!Character.isDigit(c)) {
                            hasNonNumeric = true;
                            break;
                        }
                    }
                    if (hasNonNumeric) {
                        if (notNumericMsg == null) {
                            System.err.println("Cannot have non-numeric chars in input");
                            pause();
                            continue;
                        }
                        System.err.println(notNumericMsg);
                        pause();
                        continue;
                    }
                }
            }
            // only aphabetic check
            {
                boolean hasNonAlphabetic = false;
                if (onlyAlphabetic) {
                    for (char c : in.toCharArray()) {
                        if (!Character.isAlphabetic(c)) {
                            hasNonAlphabetic = true;
                            break;
                        }
                    }
                    if (hasNonAlphabetic) {
                        System.out.println(in);
                        if (notNumericMsg == null) {
                            System.err.println("Cannot have non-alphabetic chars in input");
                            pause();
                            continue;
                        }
                        System.err.println(notAlphabeticMsg);
                        pause();
                        continue;
                    }
                }
            }
            break;
        }
        value = in;
        return this;
    }

    /**
     * Sets the value variable of the {@link #Input} Class to a string after being
     * validated by the enabled checks, any error detected by these checks will
     * return the user to the start
     * 
     * Checks used by this method
     * 1. Char only check - always enabled
     * 2. Allowed chars check - optional - exiting
     * 3. Numeric only check - optional
     * 4. Alphabetic only check - optional
     * 
     * @return returns Input object for further processing
     */

    public InputHandler setChar() {
        {
            String in;
            while (true) {
                System.out.print(prompt);
                in = scan.nextLine();
                // Char only check
                if (in.length() != 1) {
                    if (mismatchMsg == null) {
                        System.err.println("Can only input a character");
                        pause();
                        continue;
                    }
                    System.err.println(mismatchMsg);
                    pause();
                    continue;
                }

                // Allowed chars
                {
                    boolean charMismatch = true;
                    if (allowedChars != null) {
                        for (char match : allowedChars) {
                            if (in.charAt(0) == match) {
                                charMismatch = false;
                                break;
                            }
                        }
                        if (charMismatch) {
                            if (mismatchMsg == null) {
                                System.err.println("Input has invalid chars");
                                pause();
                                continue;
                            }
                            System.err.println(mismatchMsg);
                            pause();
                            continue;
                        }
                        break;
                    }
                }
                // only numeric check
                {
                    boolean hasNonNumeric = false;
                    if (onlyNumeric) {
                        if (!Character.isDigit(in.charAt(0))) {
                            hasNonNumeric = true;
                            break;
                        }

                        if (hasNonNumeric) {
                            if (notNumericMsg == null) {
                                System.err.println("Cannot have non-numeric chars in input");
                                pause();
                                continue;
                            }
                            System.err.println(notNumericMsg);
                            pause();
                            continue;
                        }
                    }
                }
                // only aphabetic check
                {
                    boolean hasNonAlphabetic = false;
                    if (onlyAlphabetic) {
                        if (!Character.isAlphabetic(in.charAt(0))) {
                            hasNonAlphabetic = true;
                            break;
                        }
                        if (hasNonAlphabetic) {
                            System.out.println(in);
                            if (notNumericMsg == null) {
                                System.err.println("Cannot have non-alphabetic chars in input");
                                pause();
                                continue;
                            }
                            System.err.println(notAlphabeticMsg);
                            pause();
                            continue;
                        }
                    }
                }
                break;
            }
            value = in;
            return this;
        }
    }

    // Getters
    public int getIntValue() {
        return Integer.parseInt(value.toString());
    }

    public double getDoubleValue() {
        return Double.parseDouble(value.toString());
    }

    public String getStringValue() {
        return value.toString();
    }

    public char getCharValue() {
        return value.toString().charAt(0);
    }

    // Input & Getter
    public int nextInt() {
        return setInt().getIntValue();
    }

    public double nextDouble() {
        return setDouble().getDoubleValue();
    }

    public String nextString() {
        return setString().getStringValue();
    }

    public char nextChar() {
        return setChar().getCharValue();
    }
}
