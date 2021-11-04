/*
 * 
 /* Autore: Giuseppe Liberati.
  * L'aggiornamento avviene modificando il cognome ed inserendo l'ID del record.
  * L'eliminazione avviene inserendo il cognome.
  * Nell'inserimento l'ID non serve.
  */
 

package Atleti;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;


public class AtletiTest {

	/*
	 * -------------------------------------------------------------------------------------------------
	 * Recupero i parametri di connessione al DB da un file esterno al progetto (resources/db.properties).
	 * -------------------------------------------------------------------------------------------------
	 */
		
		private static Properties getConnectionData() {

	     Properties props = new Properties();

	     String fileName = "resources/db.properties";

	     try (FileInputStream in = new FileInputStream(fileName)) {
	         props.load(in);
	     } catch (IOException ex) {
	         Logger lgr = Logger.getLogger("Atleta");
	         lgr.log(Level.SEVERE, ex.getMessage(), ex);
	     }

	     return props;
	 }
//		----------------------------------------------------------
		public static Properties props = getConnectionData();

		public static String DB_URL = props.getProperty("db.url");
		public static String DB_NAME = props.getProperty("db.name");
		public static String DB_USER = props.getProperty("db.user");
		public static String DB_PASS = props.getProperty("db.passwd");
//		----------------------------------------------------------
	
		public static void main(String[] args) {
			
			JFrame jf = new JFrame("Atleti");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(800, 470);
			jf.setLocationRelativeTo(null);
		
			try {
				jf.add(new AtletiGUI());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			//jf.pack();
			jf.setVisible(true);
		}
	}



	
		

