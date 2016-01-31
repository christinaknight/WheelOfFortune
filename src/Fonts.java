import java.awt.*;

public class Fonts extends Frame
{
   public void welcome(Graphics g)
   {
      setBackground(Color.white);

	      
      Font arialBolditalic12 = new Font("Arial", Font.BOLD, 25);
	      g.setColor(Color.magenta);
	      g.setFont(arialBolditalic12);
	      g.drawString("Welcome to the Wheel of Fortune Game!", 120, 120);
   }
	public static void main(String[] args) 
	{
	      Frame ff = new Fonts();
	      ff.resize(400,400);
	      ff.show();
	}
}