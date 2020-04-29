
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class Begin extends JWindow {

    static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
    static Begin execute;
    //private static CreateSplashScreen execute;
    private static int count;
    private static Timer timer1;

    public Begin() {

        Container container = getContentPane();
        container.setLayout(null);
       // container.setBounds(10, 10, 400, 400);

        JPanel panel = new JPanel();
        ImageIcon icon=new ImageIcon();
        
        panel.setBorder(null);
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 10, 350, 150);
        panel.setLayout(null);
        container.add(panel);
        
        Color color = new Color(63,96,124);
        
        JLabel label = new JLabel("PGP TOOL");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setBounds(180, 50, 280, 30);
        label.setForeground(color);
        panel.add(label);
       
        JLabel label1 = new JLabel("V 1.0");
        label1.setFont(new Font("Verdana", Font.BOLD, 15));
        label1.setForeground(color);
        label1.setBounds(200, 70, 280, 30);
        panel.add(label1);
       
        JLabel label2 = new JLabel("LET'S BEGIN");
        label2.setFont(new Font("Verdana", Font.BOLD, 15));
        label2.setForeground(color);
        label2.setBounds(180, 100, 280, 30);
        panel.add(label2);
        
        BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("src/icons/splash1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(10, 10, 130,130);
        panel.add(picLabel);

        progressBar.setMaximum(50);
        progressBar.setBounds(55, 180, 250, 15);
        progressBar.setForeground(color);
        container.add(progressBar);
        loadProgressBar();
        setSize(370, 215);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;

                progressBar.setValue(count);

                //System.out.println(count);

                if (count == 20) {

                    createFrame();

                    execute.setVisible(false);//swapped this around with timer1.stop()

                    timer1.stop();
                }

            }

            private void createFrame() throws HeadlessException {
            	execute.setVisible(false);
        	    timer1.stop();
            	 MyFrame f = new MyFrame();
            	    f.setVisible(true);
            	    
            }
        };
        timer1 = new Timer(50, al);
        timer1.start();
    }

    public static void main(String[] args) {
			execute = new Begin();        
    	}
};