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
                d.setMotherboardSpecs(line[0], line[1]);
                d.setCPUSpecs(line[2], Integer.parseInt(line[3]), Double.parseDouble(line[4]));
                d.setMemorySpecs(Integer.parseInt(line[5]), line[6]);
                d.setStorageSpecs(Integer.parseInt(line[7]), line[8]);
                d.setGPUModel(line[9]);
                d.setPSUWattage(Integer.parseInt(line[10]));
                desktops.add(d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void displayList() {
        Desktop.displayHeader();
        int x = 0;
        for (Desktop d : desktops) {
            System.out.printf("%-15s", "Unit #" + x++);
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
                case 2:// Search
                    int searchCategory = new InputField("""
                            Search by :
                            [1] Unit number
                            [2] Motherboard brand
                            [3] CPU model
                            [4] Memory type
                            [5] Storage size
                            [6] GPU model
                            [7] PSU wattage
                            """).min(1).max(7).nextInt();
                    InputField searchField = new InputField("""
                            Search : """);
                    String keyword;
                    boolean match = false;
                    Search: switch (searchCategory) {
                        case 1: {
                            Desktop d = desktops.get(searchField.min(0)
                                    .max(desktops.size() - 1).nextInt());
                            System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                            d.displaySpecs();
                        }
                            break;
                        case 2:
                            keyword = searchField.nextString();
                            for (Desktop d : desktops) {
                                if (keyword.equals(d.getMotherboardBrand())) {
                                    match = true;
                                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                                    d.displaySpecs();
                                }
                            }
                            if (match)
                                break Search;
                            System.out.println(keyword + " Not found");
                            break;
                        case 3:
                            keyword = searchField.nextString();
                            for (Desktop d : desktops) {
                                if (keyword.equals(d.getCpuModel())) {
                                    match = true;
                                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                                    d.displaySpecs();
                                }
                            }
                            if (match)
                                break Search;
                            System.out.println(keyword + " Not found");
                            break;
                        case 4:
                            keyword = searchField.nextString();
                            for (Desktop d : desktops) {
                                if (keyword.equals(d.getMemoryType())) {
                                    match = true;
                                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                                    d.displaySpecs();
                                }
                            }
                            if (match)
                                break Search;
                            System.out.println(keyword + " Not found");
                            break;
                        case 5:
                            keyword = searchField.nextString();
                            for (Desktop d : desktops) {
                                if (keyword.equals(d.getStorageSize() + "")) {
                                    match = true;
                                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                                    d.displaySpecs();
                                }
                            }
                            if (match)
                                break Search;
                            System.out.println(keyword + " Not found");
                            break;
                        case 6:
                            keyword = searchField.nextString();
                            for (Desktop d : desktops) {
                                if (keyword.equals(d.getGpuModel())) {
                                    match = true;
                                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                                    d.displaySpecs();
                                }
                            }
                            if (match)
                                break Search;
                            System.out.println(keyword + " Not found");
                            break;
                        case 7:
                            keyword = searchField.nextString();
                            for (Desktop d : desktops) {
                                if (keyword.equals(d.getPowerSupplyWattage() + "")) {
                                    match = true;
                                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(d));
                                    d.displaySpecs();
                                }
                            }
                            if (match)
                                break Search;
                            System.out.println(keyword + " Not found");
                            break;
                    }
                    break;
                case 3:// Edit
                    int index = new InputField("Edit Index : ")
                            .min(0)
                            .max(desktops.size() - 1)
                            .nextInt();
                    Desktop selected = desktops.get(index);
                    Desktop.displayHeader();
                    System.out.printf("%-15s", "Unit #" + desktops.indexOf(selected));
                    selected.displaySpecs();
                    int editCategory = new InputField("""
                            Edit category :
                            [1] Motherboard
                                - Model
                                - Brand
                            [2] CPU
                                - Model
                                - Cores
                                - ClockGHz
                            [3] Memory
                                - Size
                                - Type
                            [4] Storage
                                - Size
                                - Type
                            [5] GPU
                                - Model
                            [6] PSU
                                - Wattage
                            [0] Exit
                            """).min(0).max(6).nextInt();

                    Edit: switch (editCategory) {
                        case 1: // Motherboard
                            switch (new InputField("""
                                    Motherboard
                                        [1] Model
                                        [2] Brand
                                        [0] Exit
                                        """).setAllowedChars("12").nextInt()) {
                                case 1: // Model
                                    System.out.println(selected.getMotherboardModel());
                                    selected.inputMotherboardModel();
                                    break;
                                case 2: // Brand
                                    System.out.println(selected.getMotherboardBrand());
                                    selected.inputMotherboardBrand();
                                    break;
                                case 0: // Exit
                                    break Edit;
                            }
                            break;
                        case 2: // CPU
                            switch (new InputField("""
                                    CPU
                                        [1] Model
                                        [2] Cores
                                        [3] ClockGHz
                                        [0] Exit
                                        """).nextInt()) {
                                case 1:// Model
                                    System.out.println(selected.getCpuModel());
                                    selected.inputCPUModel();
                                    break;
                                case 2:// Cores
                                    System.out.println(selected.getCpuCores());
                                    selected.inputCPUCores();
                                    break;
                                case 3:// ClockGHz
                                    System.out.println(selected.getCpuClockGHz());
                                    selected.inputCPUClockGHz();
                                    break;
                                case 0:// Exit
                                    break Edit;
                            }
                            break;
                        case 3: // Memory
                            switch (new InputField("""
                                    Memory
                                        [1] Size
                                        [2] Type
                                        [0] Exit
                                        """).nextInt()) {
                                case 1: // Size
                                    System.out.println(selected.getMemorySize());
                                    selected.inputMemorySize();
                                    break;
                                case 2: // Type
                                    System.out.println(selected.getMemoryType());
                                    selected.inputMemoryType();
                                    break;
                                case 0: // Exit
                                    break Edit;
                            }
                            break;
                        case 4: // Storage
                            switch (new InputField("""
                                    Storage
                                        [1] Size
                                        [2] Type
                                        [0] Exit
                                        """).nextInt()) {
                                case 1: // Size
                                    System.out.println(selected.getStorageSize());
                                    selected.inputStorageSize();
                                    break;
                                case 2: // Type
                                    System.out.println(selected.getStorageType());
                                    selected.inputStorageType();
                                    break;
                                case 0: // Exit
                                    break Edit;
                            }
                            break;
                        case 5: // GPU
                            System.out.println(selected.getGpuModel());
                            selected.setGPUModel(new InputField("set Model : ").nextString());
                            break;
                        case 6: // PSU
                            System.out.println(selected.getPowerSupplyWattage());
                            selected.setPSUWattage(new InputField("set Wattage : ").min(20).nextInt());
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 6:
                    displayList();
                    break;
            }
        }
    }
}
