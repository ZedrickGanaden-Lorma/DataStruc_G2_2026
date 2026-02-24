import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DesktopRegistration {
    public static ArrayList<Desktop> desktops = new ArrayList<>();

    public static void loadData() {
        try (Scanner scanner = new Scanner(new File("DesktopList.txt"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                Desktop d = new Desktop();
                // Double.parseDouble(line[])
                // Integer.parseInt(line[])
                d.setMotherboardSpecs(line[1], line[2]);
                d.setCPUSpecs(line[3], Integer.parseInt(line[4]), Double.parseDouble(line[5]));
                d.setMemorySpecs(Integer.parseInt(line[6]), line[7]);
                d.setStorageSpecs(Integer.parseInt(line[8]), line[9]);
                d.setGPUSpecs(line[10]);
                d.setPSUWattage(Integer.parseInt(line[11]));
                desktops.add(d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void displayList() {
        desktops.get(0).displayHeader();
        for (Desktop d : desktops) {
            d.displaySpecs();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        loadData();
        app: while (true) {
            int choice = new InputField("""
                    Computer Unit Registration
                    [1] Add Desktop
                    [2] Search
                    [3] Edit
                    [4] Delete
                    [5] Sort
                    [6] List

                    [0] Exit
                    """).max(6).min(0).nextInt();
            switch (choice) {
                case 0:
                    break app;
                case 1:
                    Desktop newDesktop = new Desktop();
                    newDesktop.inputAllSpecs();
                    desktops.add(newDesktop);
                    break;
                case 2:
                    int searchChoice = new InputField("""
                            Search by :
                            [1] Unit Number
                            [2] Motherboard
                            [3] CPU
                            [4] Memory
                            [5] Storage
                            [6] GPU
                            [7] PSU
                            """).min(1).max(7).nextInt();
                case 6:
                    displayList();
            }
        }
    }
}
