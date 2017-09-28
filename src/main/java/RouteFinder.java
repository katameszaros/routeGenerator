import java.util.ArrayList;
import java.util.List;

/**
 * Created by kata on 2017.09.28..
 */
class RouteFinder {

    private List<Location> locations;
    private List<Location> route;

    RouteFinder(List<Location> locations) {
        this.locations = locations;
    }

    List<Location> getRoute() {
        return route;
    }

    void generate() {
        ArrayList<Location> starterPoints = getStarterPoints();
        this.route = new ArrayList<>();
        for (Location starterPoint : starterPoints) {
            route.add(starterPoint);
            if (starterPoint.hasNextLocation()) {
                visitNextLocation(starterPoint);
            }
        }
    }

    private ArrayList<Location> getStarterPoints() {
        ArrayList<Location> starterPoints = new ArrayList<>();
        for (Location location : locations) {
            if (location.getBeforeLocation() == null) {
                starterPoints.add(location);
            }
        }
        return starterPoints;
    }

    private void visitNextLocation(Location location) {
        if (location.hasNextLocation()) {
            route.add(location.getNextLocation());
            visitNextLocation(location.getNextLocation());
        }
    }
}
