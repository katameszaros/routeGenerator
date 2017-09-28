import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kata on 2017.09.28..
 */
public class LocationGenerator {
    static ArrayList<Location> locations = new ArrayList<Location>();

    public static void readLocationsFromFile() throws IOException {
        Path worldListPath = new File("src/main/resources/sample_input.txt").toPath();
        List<String> lines = Files.readAllLines(worldListPath);
        for (String str : lines) {
            Location location = generateLocation(str);
            locations.add(location);
        }
        for (String str : lines) {
            setDependency(locations, str);
        }
    }

    private static Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    private static Location generateLocation(String line) {
        String[] splitted = line.split(" => ");
        Location location = new Location(splitted[0]);
        return location;
    }

    private static void setDependency(ArrayList<Location> locations, String line) {
        String[] splitted = line.split(" => ");

        if (splitted.length == 2) {
            if (getLocationByName(splitted[1]) == null) {
                locations.add(new Location(splitted[1]));
            }
        }

        for (Location location : locations) {
            if (location.getName().equals(splitted[1])) {
                location.setNextLocation(getLocationByName(splitted[0]));
            }
        }
    }
}
