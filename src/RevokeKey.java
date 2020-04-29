import com.didisoft.pgp.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class RevokeKey {
	Color color = new Color(63,96,124);
	 Font  f2  = new Font(Font.SERIF,  Font.BOLD, 16);
	 
	
	public void revokeKey(String uId, String pass) throws Exception {
		
		RevocationLib lib = new RevocationLib();
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
		int f=0;
		KeyPairInformation[] keys1 = ks.getKeys();
		for(KeyPairInformation key: keys1){
			String user=key.getUserID();
			if(user.equals(uId)) {
				if(key.isRevoked()) {
					f=1;
				}
			}
		}
		if(f==1) {
			
			 int input = JOptionPane.showConfirmDialog(null,
				        "<html>Your keys are already revoked</html>","Message", JOptionPane.DEFAULT_OPTION);
			 
				return;
		}
		
			 byte revocationCode = RevocationLib.REASON_KEY_NO_LONGER_USED;
		     String revocationDescription = "This key is no longer used";
		        
		        // revoke key directly
		     lib.revokeKey(ks,uId, pass, revocationCode, revocationDescription);
		     
		     JPanel panel=new JPanel();
				String title = "KEYS INFORMATION";
				TitledBorder b = BorderFactory.createTitledBorder(title);
				panel.setBorder(b);
				panel.setBackground(Color.WHITE);
				panel.setLayout(new GridBagLayout());
				 GridBagConstraints constraints = new GridBagConstraints();
			        constraints.anchor = GridBagConstraints.CENTER;
			        constraints.insets = new Insets(10, 10, 10, 10);
			           
			        panel.setSize(new Dimension(500,300));
		     
		     KeyPairInformation[] keys = ks.getKeys();
     		for(KeyPairInformation key: keys){
     			if(key.getUserID().equals(uId)) {
     				
     				
	        			String keyId=key.getKeyIDLongHex();
	        			String keyAlgo=key.getAlgorithm();	        			
	        			String getValidity=String.valueOf(key.getValidDays());
	        			String exp="";
	        			boolean isExp=key.isExpired();
	        			if(isExp) {
	        				exp="Key Expired";
	        			}else {
	        				exp="Keys not expired yet";
	        			}
	        			
	        			JLabel l1=new JLabel("Key Id :");
	        			l1.setFont(f2);
	        			l1.setForeground(color);
	        			JLabel l2=new JLabel(keyId);
	        			l2.setFont(f2);
	        			l2.setForeground(color);	        			
	        			JLabel l3=new JLabel("Key Algorithm :");
	        			l3.setFont(f2);
	        			l3.setForeground(color);	        			
	        			JLabel l4=new JLabel(keyAlgo);
	        			l4.setFont(f2);
	        			l4.setForeground(color);
	        			
	        			JLabel l5=new JLabel("Key Validity(days) :");
	        			l5.setFont(f2);
	        			l5.setForeground(color);
	        			
	        			JLabel l6=new JLabel(getValidity);
	        			l6.setFont(f2);
	        			l6.setForeground(color);
	        			
	        			JLabel l7=new JLabel("Key Expiration :");
	        			l7.setFont(f2);
	        			l7.setForeground(color);
	        			
	        			JLabel l8=new JLabel(exp);
	        			l8.setFont(f2);
	        			l8.setForeground(color);
	        			
	        			JLabel l9=new JLabel("User Id :");
	        			l9.setFont(f2);
	        			l9.setForeground(color);
	        			
	        			JLabel l10=new JLabel(key.getUserID());
	        			l10.setFont(f2);
	        			l10.setForeground(color);
	        			
	        			JLabel msg=new JLabel("YOUR KEY HAS BEEN SUCCESSFULLY REVOKED");
	        			 Font  f3  = new Font(Font.SERIF,  Font.BOLD, 18);
	        			msg.setFont(f3);	
	        			
	        			 constraints.gridx = 0;
	        		     constraints.gridy = 0;
	        		     constraints.gridwidth=1;
	        		     panel.add(l9,constraints);
	        		     
	        		     constraints.gridx = 1;
	        		     constraints.gridy = 0;
	        		     panel.add(l10,constraints);
	        		     
	        		     constraints.gridx = 0;
	        		     constraints.gridy = 1;
	        		     panel.add(l1,constraints);
	        		     
	        		     constraints.gridx = 1;
	        		     constraints.gridy = 1;
	        		     panel.add(l2,constraints);
	        		     
	        			 constraints.gridx = 0;
	        		     constraints.gridy = 2;
	        		     panel.add(l3,constraints);
	        		     
	        		     constraints.gridx = 1;
	        		     constraints.gridy = 2;
	        		     panel.add(l4,constraints);
	        		     
	        		     constraints.gridx = 0;
	        		     constraints.gridy = 3;
	        		     panel.add(l5,constraints);
	        		     
	        		     constraints.gridx = 1;
	        		     constraints.gridy = 3;
	        		     panel.add(l6,constraints);
	        		     
	        		     constraints.gridx = 0;
	        		     constraints.gridy = 4;
	        		     panel.add(l7,constraints);
	        		     
	        		     constraints.gridx = 1;
	        		     constraints.gridy = 4;
	        		     panel.add(l8,constraints);
	        		     
	        		     constraints.gridx = 0;
	        		     constraints.gridy = 5;
	        		     constraints.gridwidth=2;
	        		     panel.add(msg,constraints);
	        		     
	        		     
	        			
	        		}
     		}
     		
     		JDialog mydialog=new JDialog();
    	    mydialog.add(panel);
    	    mydialog.setSize(500,350);
    	    mydialog.setLocationRelativeTo(null);
    	    mydialog.setVisible(true);
     		
		     
//		     int input = JOptionPane.showConfirmDialog(null,
//		                "Your key has been successfully revoked","Message", JOptionPane.DEFAULT_OPTION);
			    
			
	}

}
