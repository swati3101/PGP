import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import com.didisoft.pgp.*;





public class KeyGenService {
	LocalPath l;
	public void generateKey(String uname, String emailId, String pass) throws Exception {
		
		// initialize the KeyStore where the key will be generated
				KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
				
				// key primary user Id
				String userId = emailId;
				
				// preferred hashing algorithms
				String[] hashingAlgorithms = new String[]
				                             {HashAlgorithm.SHA1,
											  HashAlgorithm.SHA256,
											  HashAlgorithm.SHA384,
											  HashAlgorithm.SHA512,
											  HashAlgorithm.MD5};
				
				// preferred compression algorithms
				String[] compressions = new String[]
				                            {CompressionAlgorithm.ZIP,
											CompressionAlgorithm.ZLIB,
											CompressionAlgorithm.UNCOMPRESSED};
				
				// preferred symmetric key algorithms
				String[] cyphers = new String[] {
				                     CypherAlgorithm.CAST5,
									  CypherAlgorithm.AES_128,
									  CypherAlgorithm.AES_192,
									  CypherAlgorithm.AES_256,
									  CypherAlgorithm.TWOFISH};
				
				String privateKeyPassword = pass;

				int keySizeInBits = 2048;
				try 
				{
				    KeyPairInformation key = 
				 ks.generateKeyPair(keySizeInBits, 
									userId, 
									KeyAlgorithm.RSA, 
									privateKeyPassword, 
									compressions, 
									hashingAlgorithms, 
									cyphers);
				    
				    KeyPairInformation.SubKey[] subKeys = key.getPrivateSubKeys();
				    JFileChooser j = new JFileChooser(new File(l.localPath+"src/KeyFiles/")); 
					   j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					   j.setDialogTitle("Select folder to save your keys");
					   j.showSaveDialog(null); 
					   File fileToSave = j.getSelectedFile();
					   String pub_path=fileToSave.getAbsolutePath()+"\\"+"pubKey_"+userId+".asc";
					   String pri_path=fileToSave.getAbsolutePath()+"\\"+"priKey_"+userId+".asc";
					   
					   System.out.println(pub_path);
					   System.out.println(pri_path);
					   
				    ks.exportPublicKey(pub_path, userId, true);
				    ks.exportPrivateKey(pri_path, userId, true);
				  
					    
				    
				} 
				catch (com.didisoft.pgp.PGPException e) 
				{
				    System.out.println(e.getMessage());
				    if (e.getUnderlyingException() != null) {
				        e.getUnderlyingException().printStackTrace();
				    }
				}	
				
	        	        	       
	    }	
	
}
