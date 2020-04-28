/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maitreyi Sharma
 */

import com.didisoft.pgp.*;
import com.didisoft.pgp.exceptions.FileIsEncryptedException;
import com.didisoft.pgp.exceptions.WrongPasswordException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DigitalSignature {
    private String username;
    private String password;
    private String inputDoc;
    PGPLib pgp;
    boolean asciiArmor;
   // KeyStore ks;
    
    public void setPassword(String pass){
        password = String.valueOf(pass);
        System.out.println(password);
    }
    
    public void setInputDoc(String doc){
        inputDoc = String.valueOf(doc);
        System.out.println(inputDoc);
    }
    
    public void setUsername(String usnm){
        username = String.valueOf(usnm);
        System.out.println(username);
    } 
    
      
    public DigitalSignature() throws IOException, PGPException{
        pgp = new PGPLib();
        asciiArmor = false; 
        //ks = new KeyStore("KeyFiles/pgp_KeyStore.keystore", "keystore_password");
       // kg = new KeyGeneration();
     //   kg.generate_key();
    }
    
    public void signFile() throws PGPException, WrongPasswordException, IOException{
                
        KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
        
        System.out.println("signing initiated!!");
        if (inputDoc == null || password == null){
            System.out.println("null pointer found!!");
        }
        boolean asciiArmor = false;
        String signPath="src/DataFiles/signed_"+username+".pgp";
	pgp.signFile(inputDoc,
                    ks, 
                    username, 
                    password, 
                    signPath,
                    false);
        String path="C:/Users/saura/eclipse-workspace/Gui-PGP-Gui/"+signPath;
        System.out.println("Signed file created!!");
        JOptionPane.showMessageDialog(new JFrame(), "<html>Document signed and saved to this path:<br/>"+path+"</html>");
    }
    
    public boolean verifyFile() throws PGPException, FileIsEncryptedException, IOException{
        
        KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", "keystore_password");
        if (username == null){
            System.out.println("user credentials invalid!!");
            return false;
        }     
        boolean validSignature = pgp.verifyFile(inputDoc,
                                                ks, 
                                                "src/DataFiles/verified.txt");	
	if (validSignature) {
            System.out.println("Signature is valid.");
            JOptionPane.showMessageDialog(new JFrame(), "Signature Valid.");
            return true;
	} else {
            JOptionPane.showMessageDialog(new JFrame(), "Signature Invalid.");
            System.out.println("!Signature is invalid!");
            return false;
	}
    }
}
