
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kata on 2017.09.28..
 */
class LocationFileReader {
    ArrayList<Location> locations = new ArrayList<>();

    void read() {
        File file;
        List<String> lines;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a filepath, or type 'src/main/resources/sample_input.txt' for the sample file: ");
            String pathName = scan.next();
            file = new File(pathName);
            try {
                lines = Files.readAllLines(file.toPath());
                parseLines(lines);
            } catch (IOException e) {
                System.out.println("Please enter a valid file path!");
            } catch (IllegalArgumentException e) {
                System.out.println("The file contains invalid formatting.");
            }
        } while (!file.exists());

    }

    private void parseLines(List<String> lines) {
        for (String line : lines) {
            validateLine(line);
            Location location = generateLocation(line);
            locations.add(location);
        }
        for (String line : lines) {
            setDependency(locations, line);
        }
    }

    private void validateLine(String line) {
        if (!line.matches("[A-Za-z]+\\s*=>\\s*[A-Za-z]*")) {
            throw new IllegalArgumentException("Invalid line format.");
        }
    }

    private Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    private Location generateLocation(String line) {
        String[] splitted = line.split("=>");
        return new Location(splitted[0].trim());
    }

    private void setDependency(ArrayList<Location> locations, String line) {
        String[] splitted = line.split("=>");

        if (splitted.length == 2) {
            String second = splitted[1].trim();
            if (getLocationByName(second) == null) {
                locations.add(new Location(second));
            }
            for (Location location : locations) {
                if (location.getName().equals(second)) {
                    location.setNextLocation(getLocationByName(splitted[0].trim()));
                }
            }
        }
    }
}
