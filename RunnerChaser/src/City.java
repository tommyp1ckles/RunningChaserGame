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
    public LinkedList<City> adjCities;
    public boolean drawn = false;
    private int cityNum;
    //private LinkedList<NPCAgent> populous;
    private int x, y; //the cartesian coordinates of where we draw the city
    public City(int number) {
        cityNum = number;
        adjCities = new LinkedList<City>();
        //populous = new LinkedList<NPCAgent>();
    }
    public void setName(String _name) {
        name = _name;
    }
    public void setX(int _x) {
        x = _x;
    }
    public void setY(int _y) {
        y = _y;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAdjNum() {
        return adjCities.size();
    }
    public City getAdjCity(int i) {
        return adjCities.get(i);
    }
    public void addAdjCity(City _city) {
        adjCities.add(_city);
        System.out.println("Making " + name + " adjacent to " + _city.getName());
        _city.adjCities.add(this);
    }
    public String getName() {
        return name;
    }
    public int getNum() {
    	return cityNum;
    }
}
