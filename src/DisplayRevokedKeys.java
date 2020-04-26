import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.didisoft.pgp.KeyPairInformation;
import com.didisoft.pgp.KeyStore;
import com.didisoft.pgp.exceptions.NoPublicKeyFoundException;


public class DisplayRevokedKeys {	
		public void showRevokedUsers() throws Exception {
			
			KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"USER ID", "KEY ID", "PUBLIC KEY FILE"}, 0);
			JTable table=new JTable();
			
			KeyPairInformation[] keys = ks.getKeys();
			for(KeyPairInformation key: keys){
				if(key.isRevoked()) {
					String user=key.getUserID();
					String keyId=key.getKeyIDLongHex();
					String pub_Key="Click here to export";
				model.addRow(new Object[]{user, keyId, pub_Key});
				}
			}
			
			table.setModel(model);
			
			table.addMouseListener(new java.awt.event.MouseAdapter(){
				 public void mouseClicked(java.awt.event.MouseEvent e){
						int row=table.rowAtPoint(e.getPoint());
						int col= table.columnAtPoint(e.getPoint());
						String user=table.getValueAt(row,0).toString();
						   if(col==2) {
							   try {
								   String path="C:/Users/saura/eclipse-workspace/Gui-PGP-Gui/src/KeyFiles/pubKey_"+user+".asc";
								   ks.exportPublicKey(path, user, true);
								   int input = JOptionPane.showConfirmDialog(null,"<html>Public key file has been saved to this path:<br/>"+path+"<br/>The exported public key is revoked and can be distributed to other users in order to prevent its future usage</html>", "Message", JOptionPane.DEFAULT_OPTION);
							   		} catch (NoPublicKeyFoundException | IOException e1) {
							   		   // TODO Auto-generated catch block
							   		   e1.printStackTrace();
							   	   }
							}
				 }
			 });
			
			for(int i=0;i<3;i++) {
				 TableColumn col = table.getColumnModel().getColumn(i);
			        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
			        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			        col.setCellRenderer(dtcr);
			}
			table.setRowHeight(table.getRowHeight()+10);

			JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout());
	        JScrollPane tableContainer = new JScrollPane(table);
	        panel.setBorder(BorderFactory.createTitledBorder(
	        	      BorderFactory.createEtchedBorder(), "LIST OF USERS WHOSE KEYS ARE REVOKED", TitledBorder.CENTER,
	        	      TitledBorder.TOP));
	        	      
	        panel.add(tableContainer, BorderLayout.CENTER);
	        panel.setVisible(true);
	        JDialog mydialog=new JDialog();
	        mydialog.add(panel);
	        mydialog.setSize(800,300);
	        mydialog.setLocationRelativeTo(null);
	        mydialog.setVisible(true);
		
	}


}
