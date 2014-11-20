/*
 * Just for testing map and city objects.
 */
public class tester {
    public static void main(String[] args) {
        City a = new City("Wolfville");
        City b = new City("San Francisco");
        City c = new City("Los Angeles");
        Map m = new Map();
        m.addCity(a);
        m.addCity(b);
        m.addCity(c);
        m.drawMap();
    }
}
