import java.io.File;
import java.io.FileWriter;
import java.text.Format;

public class Files {
    public static void main(String[] args) {
        try {
            File f = new File("Students.txt");
            FileWriter fw = new FileWriter(f);
            int age[] = { 5, 4, 6 };
            String[] names = { "Samantha", "David", "Carlo" };
            fw.write(String.format("%-10s %-5s %n", "names", "ages"));
            for (int x = 0; x < names.length; x++) {
                fw.write(String.format("%-10s %-5d %n", names[x], age[x]));
            }
            fw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}