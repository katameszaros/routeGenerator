import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kata on 2017.09.28..
 */
public class RouteFinderTest {

    Location x = new Location("x");
    Location y = new Location("y");
    Location z = new Location("z");

    RouteFinder generator;
    List<Location> locations;

    @BeforeEach
    void setUp() {
        locations = Arrays.asList(x, y, z);
        generator = new RouteFinder(locations);
    }

    @Test
    void generate_generatesRouteWithNoDependencies() {
        generator.generate();
        assertEquals(generator.getRoute(), Arrays.asList(x, y, z));
    }

    @Test
    void generate_generatesRouteWithOneDependency() {
        y.setNextLocation(x);
        generator.generate();
        assertEquals(generator.getRoute(), Arrays.asList(y, x, z));
    }

    @Test
    void generate_generatesRouteWithAllDependency() {
        x.setNextLocation(z);
        z.setNextLocation(y);
        generator.generate();
        assertEquals(generator.getRoute(), Arrays.asList(x, z, y));
    }
}