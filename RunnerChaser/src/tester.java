/*
 * Just for testing map and city objects.
 */
public class tester {
    public static void main(String[] args) {
        City a = new City("Wolfville");
        City b = new City("San Francisco");
        City c = new City("Los Angeles");
        City d = new City("Halifax");
        int size = 3;
        int adj[][] = {
          // a  b  c  
            {1, 0, 1, 0}, //a
            {1, 0, 1, 0}, //a
            {1, 1, 0, 0}, //b
            {1, 1, 1, 0}, //c
        };
        Map m = new Map(4);
        m.addCity(a, adj[0]);
        m.addCity(b, adj[1]);
        m.addCity(c, adj[2]);
        m.addCity(d, adj[3]);
        m.printMatrix();
        m.drawMap();
    }
}
