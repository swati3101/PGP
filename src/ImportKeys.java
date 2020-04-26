import java.io.File;

import javax.swing.JFileChooser;

import com.didisoft.pgp.KeyStore;

public class ImportKeys {
		public void importKeys() throws Exception {	
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
		 JFileChooser j = new JFileChooser(new File("C:/Users/saura/eclipse-workspace/Gui-PGP-Gui/src/KeyFiles/")); 
		   j.showOpenDialog(null); 
		   File fileToSave = j.getSelectedFile();
		   String path=fileToSave.getAbsolutePath();
		   System.out.println(path);
		  
		ks.importPublicKey(path);
		}
}
