import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Tom Hadlaw, Zach Bearinger
 */
public class Map extends JPanel{
    private int size;
    private static final int vrad = 30;
    private static final int scalingFactor = 100;
    private final int w = 600;
    private final int h = 400;
    public LinkedList<City> cities;
    private int weightMatrix[][];
    public Map(int _size) {
        size = _size;
        //Cities 0 through n correspond to 0 through nth matrix entries
        //on the weight matrix.
        cities = new LinkedList<City>();
        weightMatrix = new int[size][size];
   }
   public void addCity(City _city) {
        if (cities.size() == size) {
            System.out.println("Exceeded number of defined map size");
            return;
        }
        cities.add(_city);
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));	
        g2d.setRenderingHint(
    			RenderingHints.KEY_ANTIALIASING, 
    			RenderingHints.VALUE_ANTIALIAS_ON);System.out.println(cities.size());
        int x = 0, y = 0;
        java.util.Random r = new java.util.Random();
        g2d.setColor(Color.BLACK);
        //Draw cities.
        for (int i = 0; i < size; i++) {
            Color cityColor = new Color(r.nextInt(255), r.nextInt(255),
                    r.nextInt(255));
            x = (int) vrad + r.nextInt(w - 3*vrad);
            y = (int) vrad + r.nextInt(h - 3*vrad);    
            cities.get(i).setX(x);
            cities.get(i).setY(y);
            g2d.setColor(cityColor);
            g2d.fill(new Ellipse2D.Double(x, y, vrad, vrad));
            g2d.setColor(Color.BLACK);
            g2d.drawString(cities.get(i).getName(), x, y);
        }
        //Draw roads.
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            City currCity = cities.get(i);
            int off = (int) vrad / 2;
            for (int j = 0; j < currCity.getAdjNum(); j++) {
                g2d.drawLine(currCity.getX() + off, currCity.getY() + off,
                        currCity.getAdjCity(j).getX() + off, currCity.getAdjCity(j).getY() + off);
            }
        }
        /*int diag = 1;
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
         }*/
    }
    public void drawMap() {
        JFrame frame = new JFrame("Map Viewer");
        frame.add(this);
        frame.setSize(w, h);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	public City getCity(int i) {
		return cities.get(i);
	}
	
}
