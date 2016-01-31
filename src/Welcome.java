

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Surface extends JPanel {
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        g2d.setFont(new Font("Purisa", Font.PLAIN, 13));

        g2d.drawString("Welcome!", 20, 30);
        g2d.drawString("To the game of Wheel of Fortune!", 20, 60);     
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Welcome extends JFrame {
    
    public Welcome() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Soulmate");

        add(new Surface());

        setSize(420, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);      
    }
 
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                Welcome ex = new Welcome();
                ex.setVisible(true);
            }
        });
    }
}