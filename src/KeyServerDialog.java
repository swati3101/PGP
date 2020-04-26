import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyServerDialog {
	private JDialog mydialog;
	private JPanel panel;
	private JLabel uname;
	private JTextField unameText; 
	private JButton find;
	public void getPublicKey() {

			panel=new JPanel();
			String title = "Find Public Key";
			TitledBorder b = BorderFactory.createTitledBorder(title);
			panel.setBorder(b);
			panel.setLayout(new GridBagLayout());
			 GridBagConstraints constraints = new GridBagConstraints();
		        constraints.anchor = GridBagConstraints.CENTER;
		        constraints.insets = new Insets(10, 10, 10, 10);
		           
		        panel.setSize(new Dimension(400,200));
		     
			
			uname=new JLabel("Enter username :");
			unameText=new JTextField(40);
			unameText.setMinimumSize(new Dimension(200,20));
	        
			find=new JButton("Find..");
			
			 constraints.gridx = 0;
		     constraints.gridy = 0;
		     panel.add(uname,constraints);
		     constraints.gridx = 1;
		     panel.add(unameText,constraints);
		     constraints.gridx = 0;
		     constraints.gridy = 1;
		     constraints.gridwidth=2;
		     panel.add(find,constraints);  
 
		     panel.setVisible(true);
				
			mydialog=new JDialog();
		    mydialog.add(panel);
		    mydialog.setSize(400,200);
	        mydialog.setLocationRelativeTo(null);
	        mydialog.setVisible(true);
	        
	        find.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String uname=unameText.getText();
	            	//GetPublicKeyFromDatabase k= new GetPublicKeyFromDatabase();
	            	//k.fetchPublicKey(uname);
	            	mydialog.dispose();
	            }
	         });
		
			
	
	}
}
