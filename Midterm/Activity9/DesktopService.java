
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DesktopService {
    private ArrayList<Desktop> desktops = new ArrayList<>();

    public DesktopService() {
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

    public void displayList() {
        Desktop.displayHeader();
        int x = 0;
        for (Desktop d : desktops) {
            System.out.printf("%-15s", "Unit #" + x++);
            d.displaySpecs();
            System.out.println();
        }
    }
    public void displayList(List<Desktop> d) {
        Desktop.displayHeader();
        int x = 0;
        for (Desktop de : d) {
            System.out.printf("%-15s", "Unit #" + x++);
            de.displaySpecs();
            System.out.println();
        }
    }

    public void addDesktop(Desktop d) {
        desktops.add(d);
    }

    public void searchDesktops() {
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
    }

    public void editDesktops() {
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
                        break;
                    case 2: // Brand
                        System.out.println(selected.getMotherboardBrand());
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
                        break;
                    case 2:// Cores
                        System.out.println(selected.getCpuCores());
                        break;
                    case 3:// ClockGHz
                        System.out.println(selected.getCpuClockGHz());
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
                        break;
                    case 2: // Type
                        System.out.println(selected.getMemoryType());
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
                        break;
                    case 2: // Type
                        System.out.println(selected.getStorageType());
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
    }

    public void deleteDesktop() {
        int index = new InputField("Delete Index : ")
                .min(0)
                .max(desktops.size() - 1)
                .nextInt();
        Desktop selected = desktops.get(index);
        Desktop.displayHeader();
        System.out.printf("%-15s", "Unit #" + desktops.indexOf(selected));
        selected.displaySpecs();
        int confirmation = new InputField("""
                Delete Desktop?
                [1] Yes
                [0] No
                : """).min(0).max(1).nextInt();
        switch (confirmation) {
            case 1:
                desktops.remove(index);
                System.out.println("Deleted desktop " + index);
                break;
            case 0:
                break;
        }
    }

    public void sortDesktop() {
        // There are two types of data in my Desktop class
        // numeral type and string type

        // i could somehow make a selection that will determine
        // which field of Desktop class will be used to sort this

        // sort by numbers asc or desc
        // sort by first letter char value as int asc or desc

        // copying over editDesktops as template
        // i've tried refactoring using service, and thank god it made
        // DesktopRegistration simpler and easier to read
        String selectedCategory = "";
        int sortCategory = new InputField("""
                Sort category :
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

        switch (sortCategory) {
            case 1: // Motherboard
                switch (new InputField("""
                        Motherboard
                            [1] Model
                            [2] Brand
                            [0] Exit
                            """).setAllowedChars("12").nextInt()) {
                    case 1: // Model
                        selectedCategory = "MotherboardModel";
                        break;
                    case 2: // Brand
                        selectedCategory = "MotherboardBrand";
                        break;
                    case 0: // Exit
                        break;
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
                        selectedCategory = "CPUModel";
                        break;
                    case 2:// Cores
                        selectedCategory = "CPUCores";
                        break;
                    case 3:// ClockGHz
                        selectedCategory = "CPUClockGHz";
                        break;
                    case 0:// Exit
                        break;
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
                        selectedCategory = "MemorySize";
                        break;
                    case 2: // Type
                        selectedCategory = "MemoryType";
                        break;
                    case 0: // Exit
                        break;
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
                        selectedCategory = "StorageSize";
                        break;
                    case 2: // Type
                        selectedCategory = "StorageType";
                        break;
                    case 0: // Exit
                        break;
                }
                break;
            case 5: // GPU
                selectedCategory = "GPU";
                break;
            case 6: // PSU
                selectedCategory = "PSU";
                break;
            case 0:
                break;
        }
        ArrayList<Desktop> sortedDesktops = new ArrayList<>();
        for(Desktop d: desktops){
            sortedDesktops.add(d);  
        }

        Desktop d;
        Desktop nd;
        for (int s = 0; true; s++) {
            boolean swapped = false;
            for (int i = 0; i < sortedDesktops.size()-1-s; i++) {
                d = sortedDesktops.get(i); 
                nd = sortedDesktops.get(i+1);
                String value = null;
                double number = 0;
                String nextValue = null;
                double nextNumber = 0;;
                switch (selectedCategory) {
                    case "MotherboardModel":
                        value = d.getMotherboardModel();
                        nextValue = nd.getMotherboardModel();
                        break;
                    case "MotherboardBrand":
                        value = d.getMotherboardBrand();
                        nextValue = nd.getMotherboardBrand();
                        break;
                    case "CPUModel":
                        value = d.getCpuModel();
                        nextValue = nd.getCpuModel();
                        break;
                    case "CPUCores":
                        number = d.getCpuCores();
                        nextNumber = nd.getCpuCores();
                        break;
                    case "CPUClockGHz":
                        number = d.getCpuClockGHz();
                        nextNumber = nd.getCpuClockGHz();
                        break;
                    case "MemorySize":
                        number = d.getMemorySize();
                        nextNumber = nd.getMemorySize();
                        break;
                    case "MemoryType":
                        value = d.getMemoryType();
                        nextValue = nd.getMemoryType();
                        break;
                    case "StorageSize":
                        number = d.getStorageSize();
                        nextNumber = nd.getStorageSize();
                        break;
                    case "StorageType":
                        value = d.getStorageType();
                        nextValue = nd.getStorageType();
                        break;
                    case "GPU":
                        value = d.getGpuModel();
                        nextValue = nd.getGpuModel();
                        break;
                    case "PSU":
                        number = d.getPowerSupplyWattage();
                        nextNumber = nd.getPowerSupplyWattage();
                        break;
                }
                if(number!=0){
                    if(number > nextNumber){
                        swapped = true;
                        sortedDesktops.set(i+1, d);
                        sortedDesktops.set(i,nd);
                    }
                }
                if(value!=null){
                    if(value.toCharArray()[0] > nextValue.toCharArray()[0]){
                        swapped = true;
                        sortedDesktops.set(i+1, d);
                        sortedDesktops.set(i,nd);
                    }
                }
                // displayList(sortedDesktops);
            }
            if(swapped){
                break;
            }
            displayList(sortedDesktops);
        }
    }
}
