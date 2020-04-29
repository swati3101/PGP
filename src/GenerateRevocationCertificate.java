import com.didisoft.pgp.*;


import javax.swing.*;


public class GenerateRevocationCertificate {
	LocalPath l;
	public void revocationCertiGen(String uId, String pass) throws Exception {
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
		int f=0;
		KeyPairInformation[] keys = ks.getKeys();
		for(KeyPairInformation key: keys){
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
		
		RevocationLib lib = new RevocationLib();
        
        // reason and description
        byte revocationCode = RevocationLib.REASON_KEY_NO_LONGER_USED;
        String revocationDescription = "This key is no longer used";
        
        // where will be stored the certificate
        String certificateOutputFile = "src/revocationCerti/revocation_certificate_"+uId+".txt";
        
        // create the revocation certificate
        lib.createRevocationCertificateInFile(ks, 
                                            uId,
                                            pass, 
                                            revocationCode, 
                                            revocationDescription, 
                                            certificateOutputFile); 
        String path=l.localPath+certificateOutputFile;
        int input = JOptionPane.showConfirmDialog(null,
        
                "<html>Your Revocation Certificate has been created and saved to this path:<br/>"+path+"</html>","Message", JOptionPane.DEFAULT_OPTION);

		
	}
}
