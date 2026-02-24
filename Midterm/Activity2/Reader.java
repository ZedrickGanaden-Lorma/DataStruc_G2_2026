import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public static ArrayList<String[]> desktops = new ArrayList<String[]>();

    public static void loadData() {
        try (Scanner scanner = new Scanner(new File("DesktopList.txt"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                desktops.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void displayArray(ArrayList<String[]> desktops) {
        for (String[] arr : desktops) {
            System.out.println(String.join(" ", arr));
        }
    }

    public static void main(String[] args) {
        loadData();
        displayArray(desktops);
    }
}
