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
	LinkedList<City> cities;
	public Map() {
            cities = new LinkedList<City>();
            cities.add(new City("Los Angeles"));
        }
	public void addCity(City _city) {
            cities = new LinkedList<City>();
	    cities.add(_city);
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		g2d.fillRect(0,0, w, h);
		int x = 0, y = 0;
		g2d.setColor(Color.BLACK);
		//g2d.draw(new Ellipse2D.Double(x, y, 30, 30));
                for (int i = 0; i < cities.size(); i++) {
			g2d.draw(new Ellipse2D.Double(x, y, 30, 30));
			x += 30;
			y += 30;
		}
	}
	public void drawMap() {
		JFrame frame = new JFrame("Map Viewer");
		frame.add(new Map());
		frame.setSize(w, h);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
