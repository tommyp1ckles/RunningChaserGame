import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author tom
 *
 */
public class MapViewer extends JFrame{
/*
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillOval(20, 20, 50, 50);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}*/    
	public static void main(String[] args) {
		JFrame frame = new JFrame("Runner Chaser Viewer");
		frame.setDefaultLookAndFeelDecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(966, 600));
		frame.setResizable(false);
		//frame.getContentPanel();
		frame.setVisible(true);
	}

}
