import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.didisoft.pgp.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class EncryptDialog extends JDialog {
	private JTextField SourceTextField;
	private JTextField TargetTextField;
	private JTextField UidTextField;
	Color color = new Color(63,96,124);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptDialog dialog = new EncryptDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public EncryptDialog() {
		setTitle("File Encryption");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		
		
		SourceTextField = new JTextField();
		SourceTextField.setBounds(181, 30, 243, 22);
		getContentPane().add(SourceTextField);
		SourceTextField.setColumns(10);
		
		TargetTextField = new JTextField();
		TargetTextField.setBounds(181, 85, 243, 23);
		getContentPane().add(TargetTextField);
		TargetTextField.setColumns(10);
		
		JLabel UseridLabel = new JLabel("Recipient UserId :");
		Font  f2  = new Font(Font.SERIF,  Font.BOLD, 15);
		UseridLabel.setFont(f2);
		UseridLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UseridLabel.setBounds(30, 143, 119, 20);
		getContentPane().add(UseridLabel);
		
		UidTextField = new JTextField();
		UidTextField.setBounds(181, 143, 243, 20);
		getContentPane().add(UidTextField);
		UidTextField.setColumns(10);
		
		final JLabel EncryptMsgLabel = new JLabel("");
		EncryptMsgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EncryptMsgLabel.setBounds(130, 187, 214, 22);
		getContentPane().add(EncryptMsgLabel);
		
		
		JButton SourceButton = new JButton("Source File");
		SourceButton.setBackground(Color.lightGray);
		//SourceButton.setForeground(Color.WHITE);
		SourceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
		        jFileChooser.setCurrentDirectory(new File("C:"));
		         
		        int result = jFileChooser.showOpenDialog(new JFrame());
		     
		     
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = jFileChooser.getSelectedFile();
		            SourceTextField.setText(selectedFile.getAbsolutePath());
		        }
			}
		});
		SourceButton.setBounds(36, 29, 130, 23);
		getContentPane().add(SourceButton);
		
		JButton TargetButton = new JButton("Target Directory");
		TargetButton.setBackground(Color.lightGray);
		//TargetButton.setForeground(Color.WHITE);
		TargetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        jFileChooser.setCurrentDirectory(new File("C:"));
		         
		        int result = jFileChooser.showOpenDialog(new JFrame());
		     
		     
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = jFileChooser.getSelectedFile();
		            TargetTextField.setText(selectedFile.getAbsolutePath());
		        }
				
			}
		});
		TargetButton.setBounds(36, 84, 130, 23);
		getContentPane().add(TargetButton);
		
		
		JButton EncryptionButton = new JButton("Encrypt");
		EncryptionButton.setBackground(color);
		EncryptionButton.setForeground(Color.WHITE);
		EncryptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 
			 
			    try {
			    		
			    	KeyStore keyStore = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
					  // create an instance of the library
					 PGPLib pgp = new PGPLib();	
					 				
					 
				    // is output ASCII or binary
				    boolean asciiArmor = false;
				    // should integrity check information be added
				    // set to true for compatibility with GnuPG 2.2.8+
				    boolean withIntegrityCheck = false;
			    	
				    CheckRevokedKey ch = new CheckRevokedKey();
				    
				    if(ch.checkIfRevoked(UidTextField.getText()))
				    
			    	{
				    	int input = JOptionPane.showConfirmDialog(null,
				                 "The key has been revoked by the user.", "Message", JOptionPane.DEFAULT_OPTION);
				    	
				    	//EncryptMsgLabel.setText("The key has been revoked by the user.");
			    	}
				    else
				    {
				    	pgp.encryptFile(SourceTextField.getText(),keyStore,UidTextField.getText(),TargetTextField.getText()+File.separator+"encrypted_"+UidTextField.getText()+".pgp",asciiArmor, withIntegrityCheck);	
				    	//System.out.println(TargetTextField.getText()+File.separator+"Output.pgp"); 
				    	int input = JOptionPane.showConfirmDialog(null,
				                 "File encryption completed.", "Message", JOptionPane.DEFAULT_OPTION);
				    	
				    	//EncryptMsgLabel.setText("File encryption completed.");
				    	
				    }
				
					
					
			    } catch (PGPException e1) {
					// TODO Auto-generated catch block
			    	 int input = JOptionPane.showConfirmDialog(null,
			                 "Please Enter a valid UserId", "Message", JOptionPane.DEFAULT_OPTION);
			    	
			    	//EncryptMsgLabel.setText("Please Enter a valid UserId");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}  
				
				
			}
		});
		EncryptionButton.setBounds(130, 227, 196, 23);
		getContentPane().add(EncryptionButton);
		
		

	}
}