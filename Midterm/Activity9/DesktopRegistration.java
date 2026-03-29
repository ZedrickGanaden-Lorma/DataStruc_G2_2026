import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DesktopRegistration {
    public static DesktopService ds = new DesktopService();
    public static void main(String[] args) {
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
                case 0:// Exit
                    break app;
                case 1:// Add Desktop
                    Desktop newDesktop = new Desktop();
                    newDesktop.inputAllSpecs();
                    ds.addDesktop(newDesktop);
                    break;
                case 2:// Search
                    ds.searchDesktops();
                    break;
                case 3:// Edit
                    ds.editDesktops();
                    break;
                case 4:// Delete
                    ds.deleteDesktop();
                    break;
                case 5:// Sort
                    ds.sortDesktop();
                    break;
                case 6:// List
                    ds.displayList();
                    break;
            }
        }
    }
}
