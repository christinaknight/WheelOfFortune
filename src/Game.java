
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

@SuppressWarnings("serial")
public class Game extends JPanel {
    int x = 0;
    int y = 0;
    int xCenter = 100;
    int yCenter = 100;
    static double angle = 10;
    //private void moveBall() {
      //  x = x + 1;
        //y = y + 1;
   // }
    private void moveInCircle(){
    	double angleRad = angle * 2.0 * (Math.PI / 360.0);
    	y = (int) ((int) 50.0 * Math.sin(angleRad) + yCenter);
    	x =  (int) ((int) 50.0 * Math.cos(angleRad) + xCenter);
    	angle = angle + 2;
    	//System.out.println(angle);
    	//System.out.println("x " + x);
    	//System.out.println("y " + y);
    }
    @Override
    public void paint(Graphics g) {
    	setBackground(Color.MAGENTA);
    	setForeground(Color.BLACK);
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawOval(50, 50, 100, 100);
        //g2d.setColor(Color.RED);
        //Double circle = new Ellipse2D.Double(50, 50, 100, 100);
       // g2d.fill(circle);
        g2d.drawLine(100, 100, x, y);
       // g2d.fillOval(x, y, 30, 30);
    }
    
    public static double angleFinder()
    {
    	Random rand = new Random ( );
    	int randomSpin = (rand.nextInt(21)); //I think 30 - how many options?x
		double location = randomSpin * 17.1429;
		return location;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Sample Frame");
        Game game = new Game();
        frame.add(game);
       frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        double location = angleFinder();
        System.out.println("angle" + location);
   
        while (angle < (360 * 2) + location) {
            game.moveInCircle();
            game.repaint();
            Thread.sleep(10);
        }
    }
    }
