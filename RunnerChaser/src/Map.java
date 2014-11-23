import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Tom Hadlaw, Zach Bearinger
 */
public class Map extends JPanel{
	int size;
	private static final int vrad = 30;
	private static final int scalingFactor = 100;
        private final int w = 600;
	private final int h = 400;
        private LinkedList<City> cities;
        private int weightMatrix[][];
        public Map(int _size) {
            size = _size;
            //Cities 0 through n correspond to 0 through nth matrix entries
            //on the weight matrix.
            cities = new LinkedList<City>();
            weightMatrix = new int[size][size];
        }
	public void addCity(City _city, int adj[]) {
            if (cities.size() == size) {
                System.out.println("Exceeded number of defined map size");
                return;
            }
            cities.add(_city);
            for (int i = 0; i < size; i++) {
                weightMatrix[cities.size() - 1][i] = adj[i];
            }
            System.out.println("Added city!");

        }
        
        public void printMatrix() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.printf("[" + weightMatrix[i][j] + "]"); 
                }
                System.out.println("");
            }
        }
        @Override
	public void paint(Graphics g) {
                System.out.println(cities.size());
                Graphics2D g2d = (Graphics2D) g;
		int x = 0, y = 0;
		java.util.Random r = new java.util.Random();
                g2d.setColor(Color.BLACK);
                int diag = 1;
                boolean drawn[] = new boolean[size];                
                for (int i = 0; i < size; i++) drawn[i] = false;
                System.out.println("Drawing map!!!");
                for (int i = 0; i < size; i++) {
                    if (!drawn[i]) {
                        drawn[i] = true;
                        // Choose random spot within the frame for the city.
                        x = (int) vrad + r.nextInt(w - 3*vrad);
                        y = (int) vrad + r.nextInt(h - 3*vrad);
                        
                        cities.get(i).setX(x);
                        cities.get(i).setY(y);
                        for (int j = 0; j < diag; j++) {
                            if (weightMatrix[i][j] > 0) {
                                int off = (int) vrad / 2;
                                g2d.drawLine(x + off, y + off, 
                                        cities.get(j).getX() + off,
                                            cities.get(j).getY() + off);
                            }
                        }
                        g2d.draw(new Ellipse2D.Double(x, y, vrad, vrad));
                        diag++;
                    }
                }
	}
	public void drawMap() {
                JFrame frame = new JFrame("Map Viewer");
		frame.add(this);
		frame.setSize(w, h);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
