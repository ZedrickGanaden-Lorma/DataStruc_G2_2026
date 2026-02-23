import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) {
        ArrayList<Desktop> desktops = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("DesktopList.csv"))) {
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
        desktops.get(0).displayHeader();
        for (Desktop d : desktops) {
            d.displaySpecs();
        }
    }
}
