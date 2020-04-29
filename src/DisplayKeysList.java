import java.awt.*;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;


import com.didisoft.pgp.KeyStore;
import com.didisoft.pgp.exceptions.NoPublicKeyFoundException;
import com.didisoft.pgp.*;
import java.io.*;


public class DisplayKeysList {
	LocalPath l;
	private File pubKeyFile; 
	private JDialog mydialog;
	private JTable table;
	Color color = new Color(63,96,124);
	Font  f2  = new Font(Font.SERIF,  Font.BOLD, 16);
	 
		
		public void showKeyslist() throws Exception {
			JMenu mb=new JMenu();
			mb.setBackground(color);
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
		
		  JLabel picLabel = new JLabel(new ImageIcon("src/icons/userlist.png"));
          picLabel.setBounds(100, 20, 700, 350);
         
          JLabel picLabel1 = new JLabel(new ImageIcon("src/icons/users.png"));
          picLabel1.setBounds(100, 20, 700, 350);
         
		
		DefaultTableModel model = new DefaultTableModel(new String[]{"USER", "KEY ID", "ALGORITHM","KEY TYPE","PUBLIC KEY FILE"}, 0);
		table=new JTable() {
			 @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
			        Component comp = super.prepareRenderer(renderer, row, col);
			        Object value = getModel().getValueAt(row, col);
			            if (value.equals(false)) {
			                comp.setBackground(Color.red);
			            } else if (value.equals(true)) {
			                comp.setBackground(Color.green);
			            } else {
			                comp.setBackground(Color.white);
			            }
			        
			        return comp;
			    
		}};
		KeyPairInformation[] keys = ks.getKeys();
		for(KeyPairInformation key: keys){
			String user=key.getUserID();
			String keyId=key.getKeyIDLongHex();
			String keyAlgo=key.getAlgorithm();
			String keyType="";
			if (key.hasPrivateKey()) {
				keyType = "Key Pair";	
			} else {
				keyType = "Public";	
			}
			String pub_Key="";
			if(key.isRevoked()) {
				pub_Key="KEYS REVOKED";
			}else {
				pub_Key="Click here to export";
			}
			
			model.addRow(new Object[]{user, keyId, keyAlgo, keyType, pub_Key});
			
		}
		
		table.setModel(model);
		
		
		 table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		 
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			 public void mouseClicked(java.awt.event.MouseEvent e){
					int row=table.rowAtPoint(e.getPoint());
					int col= table.columnAtPoint(e.getPoint());
					String user=table.getValueAt(row,0).toString();
					String status=table.getValueAt(row,4).toString();
					   if(col==4 && status.equals("Click here to export")) {
						   try {
							   JFileChooser j = new JFileChooser(new File(l.localPath+"src/KeyFiles/")); 
							   j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							   j.showSaveDialog(null); 
							   File fileToSave = j.getSelectedFile();
							   String path=fileToSave.getAbsolutePath()+"\\"+"pubKey_"+user+".asc";
							   System.out.println(path);
							   ks.exportPublicKey(path, user, true);
							   //int input = JOptionPane.showConfirmDialog(null,"<html>Public key file has been saved to this path:<br/>"+path+"</html>", "Message", JOptionPane.DEFAULT_OPTION);
						   		} catch (NoPublicKeyFoundException | IOException e1) {
						   		   // TODO Auto-generated catch block
						   		   e1.printStackTrace();
						   	   }
						}
			 }
		 });
		for(int i=0;i<5;i++) {
		 TableColumn col = table.getColumnModel().getColumn(i);
	        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
	        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
	        col.setCellRenderer(dtcr);
		}
		table.setRowHeight(table.getRowHeight()+10);
		table.setBackground(Color.WHITE);
		

		JLabel l=new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ENCRYPT AND SEND CONFIDENTIAL DATA TO THESE USERS</html>");
		l.setFont(f2);
		l.setForeground(color);
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(l,BorderLayout.NORTH);              
        panel.add(picLabel, BorderLayout.WEST);
        panel.add(picLabel1, BorderLayout.EAST);
        JScrollPane tableContainer = new JScrollPane(table);
        panel.add(tableContainer, BorderLayout.CENTER);
        panel.setVisible(true);
        mydialog=new JDialog();
        mydialog.setBackground(Color.WHITE);
        mydialog.add(panel);
        mydialog.setSize(850,500);
        mydialog.setLocationRelativeTo(null);
        mydialog.setVisible(true);
      

	}
		



}
