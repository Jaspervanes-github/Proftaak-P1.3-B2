import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    private FileReader fr;

    public void test() {
        try {
            fr = new FileReader("Simulation/res/Terrain/Map.json");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        int i;
        try {
            while ((i = fr.read()) != -1)
                System.out.print((char) i);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
