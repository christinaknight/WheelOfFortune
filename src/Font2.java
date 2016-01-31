
import java.awt.*;

public class Font2 extends Frame
{
   public void paint(Graphics g)
   {
	      
   Font arialBolditalic12 = new Font("Arial", Font.BOLD, 25);
	      g.setColor(Color.blue);
	      g.setFont(arialBolditalic12);
	      g.drawString("Welcome to the Wheel of Fortune Game!", 120, 120);
	      g.setColor(Color.lightGray);
	      g.drawOval(300, 150, 150, 150);
	      g.fillOval(300, 150, 150, 150);
   }
   
   public static void main (String args[])
   {
      Frame ff = new Font2();
      ff.resize(400,400);
      ff.show();
   }
}