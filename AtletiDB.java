
/*
 * 
 * Autore: Giuseppe Liberati.
 * 
 */

package Atleti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;



public class AtletiDB extends JPanel{
	
	private Connection conn;
	
	private PreparedStatement stmtLeggiBySquadra;
	private PreparedStatement stmtInserisci;
	private PreparedStatement stmtLeggiTutti;
	private String cercaSquadra = "";
	private PreparedStatement stmtElimina = null;
	private PreparedStatement stmtLeggiDistinct;
	private PreparedStatement stmtAggiorna = null;

	private final static String SQL_READ_DISTINCT = "SELECT Distinct nome, cognome FROM atleti WHERE squadra LIKE ?";
	private final String SQL_FIND_TEMPLATE = "SELECT * FROM atleti WHERE squadra LIKE ? ORDER BY squadra ASC, cognome ASC, nome ASC";
	private final static String SQL_INSERT = "INSERT INTO atleti (Cognome, Nome, Datanascita, Squadra) VALUES (?, ?, ?, ?)";
	private final static String SQL_READ_BY_SQUADRA = "SELECT * FROM atleti WHERE squadra LIKE ? ORDER BY squadra ASC, cognome ASC, nome ASC";
	
	//private final static String SQL_READ_DISTINCT = "SELECT * FROM atleti WHERE squadra LIKE ? ORDER BY squadra ASC, cognome ASC, nome ASC";
	private final static String SQL_UPDATE = "UPDATE atleti SET cognome = ? WHERE id = ?";
	private final static String SQL_READ_ALL = "SELECT * FROM atleti";
	private final static String SQL_DELETE = "DELETE FROM atleti WHERE cognome LIKE ?";

	public AtletiDB(String serverAddr, String dbName, String username, String password) throws SQLException {
		String url = "jdbc:mysql://" + serverAddr + "/" + dbName;

		
		conn = DriverManager.getConnection(url, username, password);
		
		// Definisco tutti gli statements necessari
		stmtInserisci = conn.prepareStatement(SQL_INSERT);
		stmtLeggiBySquadra = conn.prepareStatement(SQL_READ_BY_SQUADRA);
		stmtLeggiTutti = conn.prepareStatement(SQL_READ_ALL);
		
		stmtElimina = conn.prepareStatement(SQL_DELETE);
		stmtLeggiDistinct = conn.prepareStatement(SQL_READ_DISTINCT);
		stmtAggiorna = conn.prepareStatement(SQL_UPDATE);
		 
		//stmtLeggiDistinct = conn.prepareStatement(SQL_READ_BY_SQUADRA);
	}	
		public void close() throws SQLException, IOException {
			
					
			if (stmtInserisci != null)
				stmtInserisci.close();
					
			if (stmtLeggiTutti != null)
				stmtLeggiTutti.close();
			
			if (stmtLeggiDistinct != null)
				stmtLeggiDistinct.close();
			
			if (stmtLeggiBySquadra != null)
				stmtLeggiBySquadra.close();
			
			if (stmtAggiorna != null)
				stmtAggiorna.close();
			
			
			if (conn != null)
				conn.close();
			}
		
		public ArrayList<Persona> inserisci(String Cognome, String Nome, String Datanascita, String Squadra) throws SQLException {
			
			// Imposto i parametri della query SQL_INSERT
			stmtInserisci.setString(1, Cognome);
			stmtInserisci.setString(2, Nome);
			stmtInserisci.setString(3, Datanascita);
			stmtInserisci.setString(4, Squadra);
			// Eseguo la query
			stmtInserisci.executeUpdate();
			
			return null;
		}
		
		public ArrayList<Persona>leggiBySquadra(String squadra) throws SQLException {
		//public String leggiBySquadra(String squadra) throws SQLException {

			ArrayList<Persona> aux = new ArrayList<Persona>();
			//stmtLeggiBySquadra.setString(1, "%"  + squadra + "%");
			stmtLeggiBySquadra.setString(1, "%"  + squadra + "%");
			
			try(ResultSet rs = stmtLeggiBySquadra.executeQuery()) {
				
				//Utilizzo il secondo costruttore dell'oggetto Persona
				//per ordinare i campi della JTable partendo da un ricerca 
				//fatta per squadra.
			  while (rs.next()) {	
				  Persona p = new Persona();
				  
				  p.setId (rs.getInt("id"));
				  p.setCognome (rs.getString("cognome")); 
				  p.setNome (rs.getString("nome"));
				  p.setDatanascita (rs.getString("datanascita"));
				  p.setSquadra (rs.getString("squadra"));
				  
				  aux.add(p);
		      }
		      }
			  return aux;
			//cercaSquadra = String.valueOf(aux);
			//return cercaSquadra;
			}
			
		
	
			
			//
			//return aux;
		
		
		
		public ArrayList<Persona> getAll() throws SQLException {
			
			ArrayList<Persona> persona = new ArrayList<Persona>();
			Persona s;
			
			try(ResultSet rs = stmtLeggiTutti.executeQuery()) {
				 
			  while (rs.next()) {
				s = new Persona(
					rs.getInt("id"),
					rs.getString("cognome"),
					rs.getString("nome"),
					rs.getString("datanascita"),
					rs.getString("squadra")						
					
					);
				
				persona.add(s);
		      }
			  
			}
			
			return persona;
		}
		
		public ResultSetMetaData getMetadata() throws SQLException {
			ResultSetMetaData rsmd;
			try(ResultSet rs = stmtLeggiTutti.executeQuery()) {
				rsmd = (ResultSetMetaData) rs.getMetaData();		  
			}
			return rsmd;
		}
		
		
			public ArrayList<Persona> leggi() throws SQLException {
				ArrayList<Persona> aux = new ArrayList<Persona>();
				try(ResultSet rs = stmtLeggiTutti.executeQuery()) {
					 while (rs.next()) {	
						  Persona p = new Persona();
						  
						  p.setId (rs.getInt("id"));
						  p.setCognome (rs.getString("cognome")); 
						  p.setNome (rs.getString("nome"));
						  p.setDatanascita (rs.getString("datanascita"));
						  p.setSquadra (rs.getString("squadra"));
						  
						  aux.add(p);
				      }
				      }
					  return aux;
					}

			public void elimina(String cognome) throws SQLException {
				stmtElimina.setString(1, "%" + cognome + "%");

				stmtElimina.executeUpdate();
				}
			
			public void aggiorna(String cognome, int id) throws SQLException {
				
				stmtAggiorna.setString(1, cognome);
				stmtAggiorna.setInt(2, id);
				stmtAggiorna.executeUpdate();		
			}
			
			
			
			public Vector<Persona> findRecords(Character startChar) throws SQLException {
				
				Vector<Persona> records = new Vector<Persona>();		
				
				try(PreparedStatement stmt = conn.prepareStatement(SQL_FIND_TEMPLATE)) {
					/*
					if (startChar == null) {
						// La funzione è stata chiamata senza filtro per lettera
						// per cui prendo tutti i records
						stmt.setString(1, "%");
					}
					else
					{
						// costruisco il frammento con la prima lettera e il carattere %
						// per l'operatore LIKE nella query Sql
						 */
						 
						String likeFragment = startChar.toString() + "%";
						stmt.setString(1, likeFragment);
					//}
					

					try(ResultSet res = stmt.executeQuery()) {
						while(res.next()) {
							Persona c = new Persona();
							c.setId(res.getInt("id"));
							c.setCognome(res.getString("cognome"));
							c.setNome(res.getString("nome"));
							c.setDatanascita(res.getString("datanascita"));
							c.setSquadra(res.getString("squadra"));
							
							records.add(c);
						}
					}
				}
				
				return records;
			}
			
			
			
			
}
