import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Tom Hadlaw, Zach Bearinger
 */
public class Map extends JPanel{
    private int size;
    private static final int vrad = 40;
    private int scale = 2;
    private final int w = (int) 1100 / scale;
    private final int h = (int) 700 / scale;
    private boolean generateMap = true;
    public LinkedList<City> cities;
    public int runnerLocation, chaserLocation;
    private Random r = new Random();
    private int weightMatrix[][];
  
    public Map(int _size) {
        size = _size;
        //Cities 0 through n correspond to 0 through nth matrix entries
        //on the weight matrix.
        cities = new LinkedList<City>();
        weightMatrix = new int[size][size];
        runnerLocation = 0;
   }
   public void setChaserLocation(int _chaserLocation) {
	   chaserLocation = _chaserLocation;
   }
   public void setRunnerLocation(int currLocation) {
	   runnerLocation = currLocation;
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
    public City searchCities(String name) {
    	City curr = null;
    	for (int i = 0; i < cities.size(); i++) {
    		curr = cities.get(i);
    		if (curr.getName() == name) return curr;
    	}
    	return curr;
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));	
        g2d.setRenderingHint(
    			RenderingHints.KEY_ANTIALIASING, 
    			RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 0, y = 0;
        java.util.Random r = new java.util.Random();
    	if (generateMap) {
	        g2d.setColor(Color.BLACK);
	        //Draw cities.
	        for (int i = 0; i < size; i++) {
	            Color cityColor = new Color(r.nextInt(100)+155, r.nextInt(100)+155,
	                    r.nextInt(100)+155);
	            //x = (int) vrad + r.nextInt(w - 3*vrad);
	            //y = (int) vrad + r.nextInt(h - 3*vrad);
	            x = cities.get(i).getX();
	            y = cities.get(i).getY();
	            //cities.get(i).setX(x);
	            //cities.get(i).setY(y);
	            cities.get(i).setColor(cityColor);
	            g2d.setColor(cityColor);
	            g2d.fill(new Ellipse2D.Double(x/scale, y/scale, vrad, vrad));
	            g2d.setColor(new Color(189, 255, 226));
	            g2d.drawString(cities.get(i).getName(), x/scale, y/scale);
	        }
	        //Draw roads.
	        g2d.setColor(Color.BLACK);
	        for (int i = 0; i < size; i++) {
	            City currCity = cities.get(i);
	            int off = (int) vrad / 2;
	            for (int j = 0; j < currCity.getAdjNum(); j++) {
	                g2d.drawLine(currCity.getX()/scale + off, currCity.getY()/scale + off,
	                        currCity.getAdjCity(j).getX()/scale + off, currCity.getAdjCity(j).getY()/scale + off);
	            }
	        }
	        generateMap = false;
    	}
    	else {
	        g2d.setColor(Color.DARK_GRAY);
	        for (int i = 0; i < size; i++) {
	            City currCity = cities.get(i);
	            int off = (int) vrad / 2;
	            for (int j = 0; j < currCity.getAdjNum(); j++) {
	                g2d.drawLine(currCity.getX()/scale + off, currCity.getY()/scale + off,
	                        currCity.getAdjCity(j).getX()/scale + off, currCity.getAdjCity(j).getY()/scale + off);
	            }
	        }
	        for (int i = 0; i < size; i++) {
	            x = cities.get(i).getX();
	            y = cities.get(i).getY();
	            Color cityColor = cities.get(i).getColor();
	            g2d.setColor(cityColor);
	            g2d.fill(new Ellipse2D.Double(x/scale, y/scale, vrad, vrad));
	            g2d.setColor(Color.BLACK);
	            g2d.drawString(cities.get(i).getName(), x/scale, y/scale);
	        }
    	}
    	City runLoc = cities.get(runnerLocation);
    	//City chaseLoc = chaserLocation;
    	g2d.setColor(new Color(189, 255, 226));
    	g2d.drawString("Runner", runLoc.getX()/scale + vrad, runLoc.getY()/scale + vrad);
    	g2d.setColor(Color.PINK);
    	g2d.drawString("Chaser", cities.get(chaserLocation).getX()/scale + vrad,
    			cities.get(chaserLocation).getY()/scale + ((int) vrad/2));
    	g2d.setColor(Color.BLACK);
    	g2d.drawString("runner: " + Integer.toString(runnerLocation), w - 70, h - 60);
    	g2d.drawString("chaser: " + Integer.toString(chaserLocation), w - 70, h - 50);
    }
    JFrame frame;
    public JFrame drawMap() {
        frame = new JFrame("Map Viewer");
        frame.setBackground(Color.GRAY);
        frame.add(this);
        frame.setSize(w, h);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
    }
    public void refreshMap() {
    	if (runnerLocation == chaserLocation) {
    		System.exit(0);
    	}
    	frame.add(this);
    	frame.revalidate();
        frame.repaint();
    }
	public City getCity(int i) {
		return cities.get(i);
	}
	
	public int mapSize() {
		return cities.size();
	}
}
