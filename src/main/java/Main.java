import java.io.IOException;
import java.util.List;

/**
 * Created by kata on 2017.09.28..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        LocationFileReader locationReader= new LocationFileReader();
        locationReader.read();
        RouteFinder generator = new RouteFinder(locationReader.locations);
        generator.generate();
        List<Location> route = generator.getRoute();
        for (Location location : route) {
            System.out.println(location.getName());
        }
    }
}
