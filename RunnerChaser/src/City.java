/**
 * 
 */
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
/**
 * @author Tom Hadlaw, Zach Bearinger.
 *
 */
public class City {
    private String name;
    public LinkedList<City> adjCities;
    public LinkedList<NPCAgent> populous;
    public boolean drawn = false;
    private int cityNum;
    private Random r = new Random();
    //private LinkedList<NPCAgent> populous;
    private int x, y; //the cartesian coordinates of where we draw the city.
    private Color cityColor;
    public City(int number) {
        cityNum = number;
        adjCities = new LinkedList<City>();
        populous = new LinkedList<NPCAgent>();
    }
    public void setName(String _name) {
        name = _name;
    }
    public void setColor(Color c) {
    	cityColor = c;
    }
    public Color getColor() {
    	return cityColor;
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
    public int getRandomDestinationIndex() {
    	int numOfCities = adjCities.size();
    	return r.nextInt(numOfCities);
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
