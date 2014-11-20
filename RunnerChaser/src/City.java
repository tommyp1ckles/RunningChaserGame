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
	public City(String _name) {
		name = _name;
		adjCities = new LinkedList<City>();
		//populous = new LinkedList<NPCAgent>();
	}
	public void addAdjCity(City _city) {
		adjCities.add(_city);
	}
        public String getName() {
            return name;
        }
}
