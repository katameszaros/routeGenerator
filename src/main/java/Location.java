/**
 * Created by kata on 2017.09.28..
 */
public class Location {
    String name;
    Location nextLocation;
    Location beforeLocation;


    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public Location getBeforeLocation() {
        return beforeLocation;
    }

    public void setBeforeLocation(Location beforeLocation) {
        this.beforeLocation = beforeLocation;
    }

    public boolean hasNextLocation(){
        if(getNextLocation()!=null){
            return true;
        }
        return false;
    }
}
