/*
 * Just for testing map and city objects.
 */
public class tester {
    public static void main(String[] args) {
        City a = new City("Wolfville");
        City b = new City("San Francisco");
        City c = new City("Los Angeles");
        int size = 3;
        int adj[][] = {
            {1, 1, 9},
            {1, 2, 3},
            {3, 2, 1},
        };
        Map m = new Map(3);
        m.addCity(a, adj[0]);
        m.addCity(b, adj[1]);
        m.addCity(c, adj[2]);
        m.printMatrix();
        m.drawMap();
    }
}
