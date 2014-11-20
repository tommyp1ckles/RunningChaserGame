/**
 * 
 */
import java.util.LinkedList;
/**
 * @author Tom Hadlaw, Zach Bearinger.
 *
 */
public class City {
    private String name;
    private LinkedList<City> adjCities;
    //private LinkedList<NPCAgent> populous;
    private int x, y; //the cartesian coordinates of where we draw the city
    public City(String _name) {
        name = _name;
        adjCities = new LinkedList<City>();
        //populous = new LinkedList<NPCAgent>();
    }
    public void setX(int _x) {
        x = _x;
    }
    public void setY(int _y) {
        y = _y;
    }
    public void addAdjCity(City _city) {
        adjCities.add(_city);
    }
    public String getName() {
        return name;
    }
}
