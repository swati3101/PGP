import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.didisoft.pgp.KeyPairInformation;
import com.didisoft.pgp.KeyStore;
import com.didisoft.pgp.PGPException;
import com.didisoft.pgp.PGPLib;

public class DecryptDialog extends JDialog {
	private JTextField SourceTextField;
	private JTextField TargetTextField;
	private JPasswordField PassKeyField;
	Color color = new Color(63,96,124);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptDialog dialog = new DecryptDialog();
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
	public DecryptDialog() {
		setTitle("File Decryption");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		SourceTextField = new JTextField();
		SourceTextField.setBounds(183, 30, 241, 23);
		getContentPane().add(SourceTextField);
		SourceTextField.setColumns(10);
		
		TargetTextField = new JTextField();
		TargetTextField.setBounds(183, 82, 241, 23);
		getContentPane().add(TargetTextField);
		TargetTextField.setColumns(10);
		
		PassKeyField = new JPasswordField();
		PassKeyField.setBounds(183, 126, 241, 23);
		getContentPane().add(PassKeyField);
		
		Font  f2  = new Font(Font.SERIF,  Font.BOLD, 15);
		JLabel PasskeyLabel = new JLabel("Password : ");
		PasskeyLabel.setFont(f2);
		PasskeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PasskeyLabel.setBounds(45, 130, 89, 14);
		getContentPane().add(PasskeyLabel);
		
		final JLabel DecryptMsgLabel = new JLabel("");
		DecryptMsgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DecryptMsgLabel.setBounds(156, 182, 163, 23);
		getContentPane().add(DecryptMsgLabel);
		
		JButton SourceButton = new JButton("Source File");
		SourceButton.setBackground(Color.lightGray);
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
		SourceButton.setBounds(45, 29, 130, 23);
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
		TargetButton.setBounds(45, 81, 130, 23);
		getContentPane().add(TargetButton);
		
		
		JButton DecryptButton = new JButton("Decrypt");
		DecryptButton.setBackground(color);
		DecryptButton.setForeground(Color.WHITE);
		DecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KeyStore ks = null;
				try {
					ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
					PGPLib pgp = new PGPLib();
					
					//String privateKeyPass = "123456789";
					pgp.decryptFile(SourceTextField.getText(),ks,PassKeyField.getText(),TargetTextField.getText()+"decrypted.txt");
					int input = JOptionPane.showConfirmDialog(null,
			                 "File decryption completed.", "Message", JOptionPane.DEFAULT_OPTION);
			    
					//DecryptMsgLabel.setText("File decryption completed.");
				} catch (PGPException e2) {
					int input = JOptionPane.showConfirmDialog(null,
			                 "Incorrect Password", "Message", JOptionPane.DEFAULT_OPTION);
			    	
					//DecryptMsgLabel.setText("Incorrect Password");
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					int input = JOptionPane.showConfirmDialog(null,
			                 "Incorrect Password", "Message", JOptionPane.DEFAULT_OPTION);
			    	
					e2.printStackTrace();
				}  
				
			}
		});
		DecryptButton.setBounds(144, 227, 174, 23);
		getContentPane().add(DecryptButton);

	}
}