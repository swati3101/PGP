import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateKeyDialog {
	private JDialog mydialog;
	
	JTextField uname;
	 JTextField emailId;
	 JPasswordField pass;
	 JPasswordField passAgain;
	 Color color = new Color(63,96,124);
	 Font  f2  = new Font(Font.SERIF,  Font.BOLD, 16);
	    
		
	public void createPanel() {

		 JPanel panel = new JPanel();

		String title = "CREATE KEYS";
		TitledBorder border = BorderFactory.createTitledBorder(title);
		panel.setBorder(border);
//		 BufferedImage myPicture = null;
//			try {
//				myPicture = ImageIO.read(new File("src/icons/createKeys-removebg-preview.png"));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	           
		  JLabel picLabel = new JLabel(new ImageIcon("src/icons/createKeys.png"));
          picLabel.setBounds(100, 20, 700, 350);
     
	           Font  f3  = new Font(Font.SERIF,  Font.BOLD, 17);
	           JLabel msg = new JLabel("\"REMEMBER TO KEEP YOUR KEYS SAFE\"");
		       msg.setForeground(color);
		       msg.setBounds(400, 50, 200, 30);;
	           msg.setFont(f3);
	              
		       
		        panel.setSize(new Dimension(770,570));
		    	panel.setBackground(Color.WHITE);	      
		 panel.setLayout(new GridBagLayout());
		 GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.NORTH;
	        constraints.insets = new Insets(10, 10, 10, 10);
	           
	        JLabel username = new JLabel("Username");
	        username.setFont(f2);
	        JLabel email = new JLabel("Email");
	        email.setFont(f2);
	        JLabel passphrase = new JLabel("Passphrase");
	        passphrase.setFont(f2);
	        JLabel passphraseAgain = new JLabel("Re-enter Passphrase");
	        passphraseAgain.setFont(f2);
	        
	        JButton create = new JButton("Create");
	        create.setSize(70, 20);
	        create.setBackground(color);
	        create.setForeground(Color.WHITE);
	        JButton cancel = new JButton("Cancel");
	        cancel.setBackground(color);
	        cancel.setForeground(Color.WHITE);
	        cancel.setSize(70, 20);
	        
	           
	        
	        uname = new JTextField();
	        //uname.setSize(200, 30);
	        uname.setMinimumSize(new Dimension(200,20));
	        emailId = new JTextField(40);
	        emailId.setMinimumSize(new Dimension(200,20));
	        pass = new JPasswordField(40);
	        pass.setMinimumSize(new Dimension(200,20));
	        passAgain = new JPasswordField(40);
	        passAgain.setMinimumSize(new Dimension(200,20));
	        
	        
	        constraints.gridx = 0;
	        constraints.gridy = 0; 
	        panel.add(msg);
	        constraints.gridx = 1;
	        constraints.gridy = 0; 
	        panel.add(picLabel);
		       
	        constraints.gridx = 0;
	        constraints.gridy = 1;   
	        panel.add(username,constraints);
	        
	        constraints.gridx = 1;
	        constraints.gridy = 1; 
	        
	        panel.add(uname,constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 2;
	        panel.add(email,constraints);
	        constraints.gridx = 1;
	        constraints.gridy = 2;
	        panel.add(emailId,constraints);
	        constraints.gridx = 0;
	        constraints.gridy = 3;
	        panel.add(passphrase,constraints);
	        constraints.gridx = 1;
	        constraints.gridy = 3;
	        panel.add(pass,constraints);
	        constraints.gridx = 0;
	        constraints.gridy = 4;
	        panel.add(passphraseAgain,constraints);
	        constraints.gridx = 1;
	        panel.add(passAgain,constraints);
	        constraints.gridx = 0;
	        constraints.gridy = 5;
	        panel.add(create,constraints);
	        constraints.gridx = 1;        
	        panel.add(cancel,constraints);
	        

	        panel.setVisible(true);
	        mydialog=new JDialog();
	        mydialog.add(panel);
	        mydialog.setSize(600,500);
	        mydialog.setLocationRelativeTo(null);
	        mydialog.setVisible(true);
	  
	  //on create button click
	        create.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String msg1="";
	            	String msg2="";
	            	String msg3="";
	            	String msg4="";
	            	String msg5="";
	         
	            	JDialog alert=new JDialog(mydialog,"Alert Message");
	            	String u=uname.getText();
	            	if(u.equals("")) {
	            		msg1="Please enter username";
	            	}
	            	String eId=emailId.getText();
	            	String EMAIL_PATTERN = 
	            		    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	            	if (!eId.matches(EMAIL_PATTERN)) {
	            		msg2="Please enter a valid email Id";
	            		}
		            if(eId.equals("")) {
	            		msg2="Please enter your email Id";
	            	}
		            String p1=String.valueOf(pass.getPassword());
		            if(p1.equals("")) {
	            		msg3="Please enter password";
	            	}
	            	String p2=String.valueOf(passAgain.getPassword());
	            	if(p2.equals("")) {
	            		msg4="Please re-enter your password";
	            	}
		            
	            	if(!(p1.equals(p2))) {
	                	msg5="Passwords do not match";
	                	//System.out.println(p1+" "+p2);
	                	
	                }
	            	if(!(msg1.equals("")) || !(msg2.equals("")) || !(msg3.equals("")) || !(msg4.equals("")) || !(msg5.equals("")) )
		            {	
	            		JLabel l=new JLabel("<html>"+msg1+"<br/>"+msg2+"<br/>"+msg3+"<br/>"+msg4+"<br/>"+msg5+"</html>");
	                	l.setHorizontalAlignment(JLabel.CENTER);
	                	l.setVerticalTextPosition(JLabel.BOTTOM);
	                	l.setHorizontalTextPosition(JLabel.CENTER);
	                	alert.add(l);
	                	alert.setSize(300,200);
	        	        alert.setLocationRelativeTo(null);
	        	        alert.setVisible(true);
	            	}
	            	else {
	            		
	            		int input = JOptionPane.showConfirmDialog(null,
	                            "<html>This information will be published to key server<br/>Are you sure you want to send the information to the key server?</html>", "Warning!",JOptionPane.YES_NO_CANCEL_OPTION);
	            		if(input==0) {
	            			mydialog.dispose();
	            			KeyGenService k=new KeyGenService();
	               
			                try {
								k.generateKey(u,eId,p1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	            		}
	            		mydialog.dispose();
	            		
	            	}
	           }
	         });
	        
	        cancel.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	mydialog.dispose();
	            }
	         });
	      
	        
	}


}
