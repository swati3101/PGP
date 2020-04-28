import com.didisoft.pgp.*;

import javax.swing.*;

public class RevokeKey {
	
	public void revokeKey(String uId, String pass) throws Exception {
		
		RevocationLib lib = new RevocationLib();
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
			
			 byte revocationCode = RevocationLib.REASON_KEY_NO_LONGER_USED;
		     String revocationDescription = "This key is no longer used";
		        
		        // revoke key directly
		     lib.revokeKey(ks,uId, pass, revocationCode, revocationDescription);
		     int input = JOptionPane.showConfirmDialog(null,
		                "Your key has been successfully revoked","Message", JOptionPane.DEFAULT_OPTION);
			    
			
	}

}
