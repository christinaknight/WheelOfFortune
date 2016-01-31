import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {

	
	private JFrame f;
	private JPanel p;
	private JButton b1;
	private JLabel lab;
	
	public test()
	{
		gui();
	}
	
	public void gui()
	{
		f = new JFrame("Test Frame");
		f.setVisible(true);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		p.setBackground(Color.pink);
		
		b1 = new JButton("Test Button");
		lab = new JLabel("This is Test Label");
		
		p.add(b1);
		p.add(lab);
		
		f.add(p);
		
		b1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Button has been pressed.");
			}
			
		});
	}
	
	public static void main(String[] args) 
	{
		new test();
	}
}
