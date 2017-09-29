import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Created by kata on 2017.09.28..
 */
class LocationFileReaderTest {

    private LocationFileReader reader;
    private File testFile;
    private Scanner scanner;
    Location a;
    Location b;


    @Test
    void validFileName_readerGeneratesLocations() throws IOException {
        testFile = createTestFile("a =>",  "b =>");
        String path = testFile.getAbsolutePath();
        InputStream stream = new ByteArrayInputStream(path.getBytes());
        scanner=new Scanner(stream);
        reader = new LocationFileReader(scanner);
        reader.read();

        a = new Location("a");
        b = new Location("b");

        assertEquals(Arrays.asList(a, b).size(), reader.locations.size());
    }

    @Test
    void validFileName_readerSetsDependencies() throws IOException {
        testFile = createTestFile("a => b",  "b =>");
        String path = testFile.getAbsolutePath();
        InputStream stream = new ByteArrayInputStream(path.getBytes());
        scanner=new Scanner(stream);
        reader = new LocationFileReader(scanner);
        reader.read();

        a = new Location("a");
        b = new Location("b");
        b.setNextLocation(a);

        assertEquals(b.getNextLocation().getName(),
                reader.locations.get(1).getNextLocation().getName());
    }


    public static File createTestFile(String... lines) throws IOException {
        File file = File.createTempFile("filee", "txt");
        PrintStream content = new PrintStream(file);
        for (String line : lines) {
            content.println(line);
        }
        content.close();
        return file;
    }

}