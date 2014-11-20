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
	private final int w = 600;
	private final int h = 400;
        private LinkedList<City> cities;
	public Map() {
            cities = new LinkedList<City>();
            cities.add(new City("Vancouver"));
        }
	public void addCity(City _city) {
	    cities.add(_city);
	    System.out.println("Added city: " + _city.getName() + ", size = " +
                    cities.size());
        }
	@Override
	public void paint(Graphics g) {
                System.out.println(cities.size());
                Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		//g2d.fillRect(0,0, w, h);
		int x = 0, y = 0;
		java.util.Random r = new java.util.Random();
                g2d.setColor(Color.BLACK);
                for (int i = 0; i < cities.size(); i++) {
                        g2d.draw(new Ellipse2D.Double(x, y, 30, 30));
			x = (int) r.nextInt() % w;
			//y = r.nextInt() % y;
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
