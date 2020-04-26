import javax.swing.*;

import javax.swing.border.TitledBorder;



import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame {
  int width=800;
  int height=500;
  int flag;
  Icon iconEn = new ImageIcon("src/icons/encrypt.png");
  private JButton btnEncryptFile = new JButton("<html>Encrypt<br/> File<html>",iconEn);
  Icon iconDe = new ImageIcon("src/icons/decrypt.png");
  private JButton btnDecryptFile = new JButton("<html>Decrypt<br/> File<html>",iconDe);
  private JButton btnEncryptText = new JButton("<html>Encrypt<br/> Text<html>",iconEn);
  private JButton btnDecryptText = new JButton("<html>Decrypt<br/> Text<html>",iconDe);
  //Icon iconKe = new ImageIcon("src/icons/keyring.png");
  //private JButton btnKeyRings = new JButton("<html>Key <br/>Rings</html>",iconKe);
  Icon iconDigsign = new ImageIcon("src/icons/digsign.png");
  private JButton btnDigSign = new JButton("<html>Digital<br/> Signature",iconDigsign);
  Icon iconPGPKey = new ImageIcon("src/icons/genKey.png");
  private JButton btnCreateKey = new JButton("<html>Generate <br/> Key",iconPGPKey);
 // Icon iconVerify = new ImageIcon("src/icons/verify.png");
  //private JButton btnVerify = new JButton("Verify",iconVerify);
  
  Icon iconKeysList = new ImageIcon("src/icons/KeysList.png");
  private JButton btnKeysList = new JButton("<html>View Keys <br/> List",iconKeysList);
  Icon revokedKeysList = new ImageIcon("src/icons/revoke.png");
  private JButton btnRevokedKeysList = new JButton("<html>Revoked<br/>Keys List",revokedKeysList);
  
  private JMenuItem findKey;
  private JMenuItem viewKeys;
 // private JMenuItem importKey;
  private JMenuItem revokeKey;
  private JLabel pswd;
  private JPasswordField passText;
  private JLabel uId;
  private JTextField uIdText;
  private JButton submit;
  private JDialog mydialog;
  
  
  private JLabel lblA = new JLabel("KEY MANAGEMENT");
  private JLabel lblB = new JLabel("ENCRYPTION/DECRYPTION & DIGITAL SIGNATURE");
  private JLabel lblC = new JLabel("NOTE: Please check if the user has revoked keys or not before encryption.");
 
  
  public void enterUnamePassDialog() {
  	JPanel panel=new JPanel();
		String title = "Security check";
		TitledBorder b = BorderFactory.createTitledBorder(title);
		panel.setBorder(b);
		panel.setLayout(new GridBagLayout());
		 GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.CENTER;
	        constraints.insets = new Insets(10, 10, 10, 10);
	           
	        panel.setSize(new Dimension(400,200));
	     
        uId=new JLabel("User Id :");
		uIdText=new JTextField(40);
		uIdText.setMinimumSize(new Dimension(200,20));
      
		pswd=new JLabel("Password :");
		passText=new JPasswordField(40);
		passText.setMinimumSize(new Dimension(200,20));
      
		submit=new JButton("Submit");
		 
		 constraints.gridx = 0;
	     constraints.gridy = 0;
	     panel.add(uId,constraints);
	     constraints.gridx = 1;
	     panel.add(uIdText,constraints);
		 constraints.gridx = 0;
	     constraints.gridy = 1;
	     panel.add(pswd,constraints);
	     constraints.gridx = 1;
	     panel.add(passText,constraints);
	     constraints.gridx = 0;
	     constraints.gridy = 2;
	     constraints.gridwidth=2;
	     panel.add(submit,constraints);  

	     panel.setVisible(true);
			
		mydialog=new JDialog();
	    mydialog.add(panel);
	    mydialog.setSize(400,200);
	    mydialog.setLocationRelativeTo(null);
	    mydialog.setVisible(true);
      
      
  }
  
  public MyFrame() {
    
	setTitle("PGP Tool");
    ImageIcon logo = new ImageIcon("src/icons/logo.png");
    setIconImage(logo.getImage());
   
    setSize(width,height);
    setLocation(new Point(300,200));
    setLayout(null);    
    setResizable(false);
   
    JMenuBar mb = new JMenuBar();
    //JMenu menu1 = new JMenu("Key Server");
    JMenu menu2 = new JMenu("Keys");
    
    findKey =  new JMenuItem(new AbstractAction("Find Public Key") {
        public void actionPerformed(ActionEvent e) {
           // KeyServerDialog ksd=new KeyServerDialog();
            //ksd.getPublicKey();
        }
    });
    viewKeys =  new JMenuItem(new AbstractAction("View Keys Info") {
        public void actionPerformed(ActionEvent e) {
        	 DisplayKeysList kl=new DisplayKeysList();
       	  try {
       		kl.showKeyslist();
       	} catch (Exception e1) {
       		// TODO Auto-generated catch block
       		e1.printStackTrace();
       	}
        }
    });
//    importKey =  new JMenuItem(new AbstractAction("Import keys") {
//        public void actionPerformed(ActionEvent e) {
//        	ImportKeys ik=new ImportKeys();
//        	try {
//				ik.importKeys();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//        }
//    });
   revokeKey =  new JMenuItem(new AbstractAction("Revoke Keys") {
        public void actionPerformed(ActionEvent e) {
        	enterUnamePassDialog();
        	
        	submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	String u=uIdText.getText();
                	String p=passText.getText();
                	System.out.println(p);
                	int n = JOptionPane.showConfirmDialog(null, "<html>Are you sure you want to revoke your PGP keys?<br/>Once distributed, others will be unable to encrypt data to this key.</html>", "Select an Option...",
            				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                	if(n==0) {
                    RevokeKey rk=new RevokeKey();
                    try {
						rk.revokeKey(u,p);
						mydialog.dispose();
						
					} catch (Exception e1) {
						System.out.println("userid and password didnt match");
						 int input = JOptionPane.showConfirmDialog(null,
					                "<html>User Id and Password did not match<br/>Please try again</html>","Message", JOptionPane.DEFAULT_OPTION);
					    
						e1.printStackTrace();
					}
                	}
               }
             });
        }
    });
    
   // mb.add(menu1);
    mb.add(menu2);
    //menu1.add(findKey);
    menu2.add(viewKeys);
   // menu2.add(importKey);
    menu2.add(revokeKey);
    setJMenuBar(mb);
    
    initComponent();    
    initEvent();    
  }
  public void paint(Graphics gr)
  {
             super.paint(gr);
             gr.setColor(Color.GRAY);
             gr.drawRect(10, 50, 385, 440);
             gr.drawRect(405, 50, 385, 440);
  }

  private void initComponent(){

    btnEncryptFile.setBounds(425,70,130,60);
    btnDecryptFile.setBounds(620,70,130,60);
    btnEncryptText.setBounds(425,160,130,60);
    btnDecryptText.setBounds(620,160,130,60);
    btnKeysList.setBounds(130, 180, 130, 60);
    btnDigSign.setBounds(530,250,130,60);
    btnCreateKey.setBounds(130, 80, 130, 60);
   // btnVerify.setBounds(425, 250, 130, 60);
   //btnKeysList.setBounds(130, 180, 130, 60);
    btnRevokedKeysList.setBounds(130, 280, 130, 60);
    
    lblA.setBounds(150,10,200,20);
    lblB.setBounds(450,10,300,20);
    lblC.setBounds(180,410,430,20);
    lblC.setBackground(Color.white);
    lblC.setOpaque(true);
    
    add(btnEncryptFile);
    add(btnDecryptFile);
    add(btnEncryptText);
    add(btnDecryptText);
    //add(btnKeyRings);
    add(btnDigSign);
    add(btnCreateKey);
    //add(btnVerify);
    add(btnKeysList);
    add(btnRevokedKeysList);
     
    
    add(lblA);
    add(lblB);
    add(lblC);
   
  }

  private void initEvent(){

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(1);
      }
    });

   
    
    btnEncryptFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnEncryptFile(e);
       }
     });
    
    btnDecryptFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnDecryptFile(e);
       }
     });
    
    btnDecryptText.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnEncryptText(e);
       }
     });
    
    btnDecryptFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnDecryptText(e);
       }
     });
    
//    btnKeyRings.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            btnKeyRings(e);
//       }
//     });
//    
    btnDigSign.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnDigSign(e);
       }
     });
    
    btnCreateKey.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	btnCreateKey(e);
       }
     });
    
    btnKeysList.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	btnKeysList(e);
       }
     });
    
    btnRevokedKeysList.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	btnRevokedKeysList(e);
       }
     });
  }
  

  private void btnEncryptFile(ActionEvent evt){
	    //Create separate class for implementing this function
  }
  
  private void btnDecryptFile(ActionEvent evt){
	    //Create separate class for implementing this function
  }
  private void btnEncryptText(ActionEvent evt){
	    //Create separate class for implementing this function
  }
  private void btnDecryptText(ActionEvent evt){
	    //Create separate class for implementing this function
  }
//  private void btnKeyRings(ActionEvent evt){
//	  DisplayKeysList kl=new DisplayKeysList();
//	  try {
//		kl.showKeyslist();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	    //Create separate class for implementing this function
//  }
  private void btnDigSign(ActionEvent evt){
	    //Create separate class for implementing this function
  }
  private void btnCreateKey(ActionEvent evt){
	    //Create separate class for implementing this function
	 
	  CreateKeyDialog cl=new CreateKeyDialog();
	  cl.createPanel();
  }
  private void btnKeysList(ActionEvent evt){
	  DisplayKeysList kl=new DisplayKeysList();
	  try {
		kl.showKeyslist();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    //Create separate class for implementing this function
  }
	
	private void btnRevokedKeysList(ActionEvent evt){
		DisplayRevokedKeys dr= new DisplayRevokedKeys();
		try {
			dr.showRevokedUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		  
		    
	  }
  
}
