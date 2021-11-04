/*
 * 
 * Autore: Giuseppe Liberati
 */

package Atleti;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


public class AtletiGUI extends JPanel {
	
	
	
	private static ArrayList<String> atleti = new ArrayList<String>();
	//private TableRowSorter myTableRowSorter;
	 private DefaultTableModel model = new DefaultTableModel();
	 private JTable jTable = new JTable(model);
	 private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
	    
       
	    
	    
	private static AtletiDB atleta;
	private JTable table;
	private JLabel lblCognome;
	private JLabel lblNome;
	private JLabel lblDataNascita;
	private JLabel lblSquadra;
	private JLabel lblID;
	private JTextField txfID;
	private JTextField txfCognome;
	private JTextField txfNome;
	private JTextField txfDataNascita;
	private JTextField txfSquadra;
	private JTextArea result;
	private JButton ordina;
	private JButton inserisci;
	private JButton elimina;
	private JButton filtra;
	private JButton display;
	private JButton aggiorna;
	private JButton reset;
	private String testoTextFieldID = "";
	private String testoTextFieldCognome = "";
	private String testoTextFieldNome = "";
	private String testoTextFieldDataNascita = "";
	private String testoTextFieldSquadra = "";
	Character selectedLetter = null;
	
	
	
	
			//COSTRUTTORE
			public AtletiGUI() throws IOException {
				
				//POSIZIONO IL GRIDLAYOUT SOPRA IL CONTENITORE JPANEL
				//super(new GridLayout(5,2));
				
				//Definisco le funzioni
				add(tabella());
				add(datiInput());
				add(buttons());
				
				
				
				
				this.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
			}
			
			
			
class MyActionListenerInserisci implements ActionListener{
				
				
				@Override
				public void actionPerformed(ActionEvent e)  {
					
					
					try {
						
						testoTextFieldCognome = txfCognome.getText();
						testoTextFieldNome = txfNome.getText();
						testoTextFieldDataNascita = txfDataNascita.getText();
						testoTextFieldSquadra = txfSquadra.getText();
						
						
						try {
							atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
				
							
						} catch (SQLException e2) {
							
							e2.printStackTrace();
						}
						
						
						if((!testoTextFieldCognome.equals("")) & (!testoTextFieldNome.equals(""))
								& (!testoTextFieldDataNascita.equals("") & (!testoTextFieldSquadra.equals("")))){
							
							//Inserisco i dati con la funzione "inserisci" della classe AtletiDB
							atleta.inserisci(testoTextFieldCognome, testoTextFieldNome, testoTextFieldDataNascita,testoTextFieldSquadra);
							
							//Azzero le righe della Jtable per non avere duplicati in fase di inseriemnto
							DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							tableModel.setRowCount(0);
							
							//Costruisco la visualizzazione nella JTable
							DefaultTableModel tm = (DefaultTableModel) table.getModel();
							    ArrayList<Persona> aux = new ArrayList<Persona>();
							    
							    //Visualizzo nella JTable l'elenco dei dati aggiornati
							        aux = atleta.getAll();
							   		   
							        Vector<Vector> data = new Vector<Vector>(); 
									Vector<String> row = null; 
									//Ciclo per scorrere i record ed inserirli
							    for (int i = 0; i < aux.size(); i++) {
							        //String[] data = new String[4];
							    	row = new Vector<String>();

							    	row.add(0, String.valueOf(aux.get(i).getId()));
									row.add(1, aux.get(i).getCognome());
									row.add(2, aux.get(i).getNome());
									row.add(3, aux.get(i).getDatanascita());
									row.add(4, aux.get(i).getSquadra());		          
									//data.add(row);
									
									//Visualizzo i record aggiornati
							        tableModel.addRow(row);
							    }
							    //table.setModel(tableModel);		
							
							txfCognome.setText("");
							txfNome.setText("");
							txfDataNascita.setText("");
							txfSquadra.setText("");
							
							//Rimetto il focus al campo prodotto.
							txfCognome.requestFocusInWindow();
							
							//Mostro a video una finestra quando i dati sono stati trovati con successo.
							JOptionPane.showMessageDialog(null,  "Atleta inserito !");
												
							
						}	
					
						else JOptionPane.showMessageDialog(null,  "Inserisci i dati !");
				}
					catch(NumberFormatException | SQLException ex) {
								JOptionPane.showMessageDialog(null, "Errore nel formato del dato inserito");
								return;
								}
						}	
					}
			

class MyActionListenerDisplay implements ActionListener{
	
	
	

	@Override
	public void actionPerformed(ActionEvent e)  {
		
		try {
			
			testoTextFieldCognome = txfCognome.getText();
			testoTextFieldNome = txfNome.getText();
			testoTextFieldDataNascita = txfDataNascita.getText();
			testoTextFieldSquadra = txfSquadra.getText();
			
			try {
				atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
	
				
			} catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			
			if((testoTextFieldCognome.equals("")) | (testoTextFieldNome.equals(""))
					| (testoTextFieldDataNascita.equals("") | (testoTextFieldSquadra.equals("")))){
				
				//atleta.leggiBySquadra(testoTextFieldCognome);
				//displayResult();
				
				
				
				
				//Azzero le righe della Jtable per non avere duplicati in fase di inseriemnto
				//DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				//tableModel.setRowCount(0);
				
				//Costruisco la visualizzazione nella JTable
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				tm.setRowCount(0);
				    ArrayList<Persona> aux = new ArrayList<Persona>();
				    
				    //Visualizzo nella JTable l'elenco dei dati aggiornati
				    	//String varTemp = atleta.leggiBySquadra(testoTextFieldCognome);
				    
				       aux = atleta.getAll();
				    	
				        Vector<Vector> data = new Vector<Vector>(); 
						Vector<String> row = null; 
						//Ciclo per scorrere i record ed inserirli
				    for (int i = 0; i < aux.size(); i++) {
				        //String[] data = new String[4];
				    	row = new Vector<String>();

				    	row.add(0, String.valueOf(aux.get(i).getId()));
						row.add(1, aux.get(i).getCognome());
						row.add(2, aux.get(i).getNome());
						row.add(3, aux.get(i).getDatanascita());
						row.add(4, aux.get(i).getSquadra());		          
						//data.add(row);
						
						//Visualizzo i record aggiornati
				        tm.addRow(row);
				    }
				
				
				
				
				
				
				
				//Dopo le operazioni di inserimento resetto i campi.
				txfCognome.setText("");
				txfNome.setText("");
				txfDataNascita.setText("");
				txfSquadra.setText("");
				
				//Rimetto il focus al campo prodotto.
				txfCognome.requestFocusInWindow();
				
				//Mostro a video una finestra quando i dati sono stati trovati con successo.
				JOptionPane.showMessageDialog(null,  "Elenco visualizzato!");
									
				
			}	
		
			else JOptionPane.showMessageDialog(null,  "Inserisci i dati !");
	}
		catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Errore nel formato del dato inserito");
					return;
					} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}	
		}



			
class MyActionListenerOrdina implements ActionListener{
				
				
				

				@Override
				public void actionPerformed(ActionEvent e)  {
					
					try {
						
						testoTextFieldID = txfID.getText();
						testoTextFieldCognome = txfCognome.getText();
						testoTextFieldNome = txfNome.getText();
						testoTextFieldDataNascita = txfDataNascita.getText();
						testoTextFieldSquadra = txfSquadra.getText();
						
						try {
							atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
				
							
						} catch (SQLException e2) {
							
							e2.printStackTrace();
						}
						
						
						if((!testoTextFieldCognome.equals("")) | (!testoTextFieldNome.equals(""))
								| (!testoTextFieldDataNascita.equals("") | (!testoTextFieldSquadra.equals("")))){
							
							//atleta.leggiBySquadra(testoTextFieldCognome);
							//displayResult();				
							
							//Azzero le righe della Jtable per non avere duplicati in fase di inseriemnto
							DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							tableModel.setRowCount(0);
							
							//Costruisco la visualizzazione nella JTable
							DefaultTableModel tm = (DefaultTableModel) table.getModel();
							 //Vector<Persona> aux = new Vector<Persona>();
							    ArrayList<Persona> aux = new ArrayList<Persona>();
							    
							    //Visualizzo nella JTable l'elenco dei dati aggiornati
							    	//String varTemp = atleta.leggiBySquadra(testoTextFieldCognome);
							       aux = atleta.leggiBySquadra(testoTextFieldCognome);
							    	
							        Vector<Vector> data = new Vector<Vector>(); 
									Vector<String> row = null; 
									//Ciclo per scorrere i record ed inserirli
							    for (int i = 0; i < aux.size(); i++) {
							        //String[] data = new String[4];
							    	row = new Vector<String>();

							    	row.add(0, String.valueOf(aux.get(i).getId()));
									row.add(1, aux.get(i).getCognome());
									row.add(2, aux.get(i).getNome());
									row.add(3, aux.get(i).getDatanascita());
									row.add(4, aux.get(i).getSquadra());		          
									//data.add(row);
									
									//Visualizzo i record aggiornati
							        tableModel.addRow(row);
							    }
											
							
							//Dopo le operazioni di inserimento resetto i campi.
							txfCognome.setText("");
							txfNome.setText("");
							txfDataNascita.setText("");
							txfSquadra.setText("");
							
							//Rimetto il focus al campo prodotto.
							txfCognome.requestFocusInWindow();
							
							//Mostro a video una finestra quando i dati sono stati trovati con successo.
							JOptionPane.showMessageDialog(null,  "Atleti ordinati per squadra !");
												
							
						}	
					
						else JOptionPane.showMessageDialog(null,  "Inserisci i dati !");
				}
					catch(NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Errore nel formato del dato inserito");
								return;
								} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						}	
					}

class MyActionListenerFiltra implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		testoTextFieldSquadra = txfSquadra.getText();
			
			try {
				atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
	
			} catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			if((!testoTextFieldSquadra.equals(""))){
				
			
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
					
				DefaultTableModel tm = (DefaultTableModel)table.getModel(); 
				Vector<Persona> aux = new Vector<Persona>();
			char c = testoTextFieldSquadra.charAt(0);
				//char c =(char)0;
			//char s = testoTextFieldSquadra;
			
				try {
					aux = atleta.findRecords(c);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
					
			
						Vector<Vector> data = new Vector<Vector>(); 
						Vector<String> row = null; 
						
				    for (int i = 0; i < aux.size(); i++) {
				        //String[] data = new String[4];
				    	row = new Vector<String>();

				    	row.add(0, String.valueOf(aux.get(i).getId()));
						row.add(1, aux.get(i).getNome());
						row.add(2, aux.get(i).getCognome());
						row.add(3, aux.get(i).getDatanascita());
						row.add(4, aux.get(i).getSquadra());		          
						//data.add(row);
				        tableModel.addRow(row);
				    }
				    
				  //Dopo le operazioni di inserimento resetto i campi.
					txfCognome.setText("");
					txfNome.setText("");
					txfDataNascita.setText("");
					txfSquadra.setText("");
					
					//Rimetto il focus al campo prodotto.
					txfCognome.requestFocusInWindow();
					
					//Mostro a video una finestra quando i dati sono stati trovati con successo.
					JOptionPane.showMessageDialog(null,  "Atleti ordinati per squadra !");
				    }
				    }
}		
				
class MyActionListenerAggiorna implements ActionListener{
	
	
	@Override
	public void actionPerformed(ActionEvent e)  {
		
		
		try {
			
			//testoNome = txfNome.getText();
			testoTextFieldCognome = txfCognome.getText();
			//testoTelefono = txfTelefono.getText();
			//testoEmail = txfEmail.getText();
			testoTextFieldID = txfID.getText();
			int ID = Integer.parseInt(testoTextFieldID);
			
			try {
				atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
	
				
			} catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			
			if((!testoTextFieldCognome.equals("")) & ( ID != 0)){
				
				//Aggiorno i dati con la funzione "aggiorna" della classe AtletiDB
				atleta.aggiorna(testoTextFieldCognome, ID);
				
				//Azzero le righe della Jtable per non avere duplicati in fase di inseriemnto
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				
				//Costruisco la visualizzazione nella JTable
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				    ArrayList<Persona> aux = new ArrayList<Persona>();
				    
				    //Visualizzo nella JTable l'elenco dei dati aggiornati
				        aux = atleta.getAll();
				   		   
				        Vector<Vector> data = new Vector<Vector>(); 
						Vector<String> row = null; 
						//Ciclo per scorrere i record ed inserirli
				    for (int i = 0; i < aux.size(); i++) {
				        //String[] data = new String[4];
				    	row = new Vector<String>();

				    	row.add(0, String.valueOf(aux.get(i).getId()));
						row.add(1, aux.get(i).getCognome());
						row.add(2, aux.get(i).getNome());
						row.add(3, aux.get(i).getDatanascita());
						row.add(4, aux.get(i).getSquadra());	
						//row.add(4, String.valueOf(aux.get(i).getId()));
						//data.add(row);
						
						//Visualizzo i record aggiornati
				        tableModel.addRow(row);
				    }
				    //table.setModel(tableModel);		
				//txfID.setText("");
				txfCognome.setText("");
				txfNome.setText("");
				txfDataNascita.setText("");
				txfSquadra.setText("");
				
				//Rimetto il focus al campo prodotto.
				txfCognome.requestFocusInWindow();
				
				//Mostro a video una finestra quando i dati sono stati trovati con successo.
				JOptionPane.showMessageDialog(null,  "Utente aggiornato !");
									
				
			}	
		
			else JOptionPane.showMessageDialog(null,  "Inserisci i dati !");
	}
		catch(NumberFormatException | SQLException ex) {
					JOptionPane.showMessageDialog(null, "Errore nel formato del dato inserito");
					return;
					}
			}	
		}







			
class MyActionListenerElimina implements ActionListener{
	
	
	@Override
	public void actionPerformed(ActionEvent e)  {
		
		
		try {
			
			testoTextFieldCognome = txfCognome.getText();
			testoTextFieldNome = txfNome.getText();
			testoTextFieldDataNascita = txfDataNascita.getText();
			testoTextFieldSquadra = txfSquadra.getText();
			
			try {
				atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
	
				
			} catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			
			//if((!testoTextFieldCognome.equals("")) & (testoTextFieldNome.equals(""))
				//	& (testoTextFieldDataNascita.equals("") & (testoTextFieldSquadra.equals("")))){
			if((!testoTextFieldCognome.equals(""))){
				//DefaultTableModel model = (DefaultTableModel)this.getModel(); 
				//DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				//tableModel.setRowCount(0);	
				
				atleta.elimina(testoTextFieldCognome);
				
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
					
				
				
				DefaultTableModel tm = (DefaultTableModel)table.getModel(); 
				ArrayList<Persona> aux = new ArrayList<Persona>();
				//int rows = model.getRowCount(); 
				//for(int i = rows - 1; i >=0; i--) {
			
				//  model.removeRow(i); 
				//}
				
				        aux = atleta.getAll();
				   		   
				        Vector<Vector> data = new Vector<Vector>(); 
						Vector<String> row = null; 
						
				    for (int i = 0; i < aux.size(); i++) {
				        //String[] data = new String[4];
				    	row = new Vector<String>();

				    	row.add(0, String.valueOf(aux.get(i).getId()));
						row.add(1, aux.get(i).getCognome());
						row.add(2, aux.get(i).getNome());
						row.add(3, aux.get(i).getDatanascita());
						row.add(4, aux.get(i).getSquadra());		          
						//data.add(row);
				        tableModel.addRow(row);
				    
				    }
			
				    //table.setModel(tableModel);		
				
				txfCognome.setText("");
				txfNome.setText("");
				txfDataNascita.setText("");
				txfSquadra.setText("");
				
				//Rimetto il focus al campo prodotto.
				txfCognome.requestFocusInWindow();
				
				//Mostro a video una finestra quando i dati sono stati trovati con successo.
				JOptionPane.showMessageDialog(null,  "Atleta cancellato !");
									
				
			}else JOptionPane.showMessageDialog(null,  "Inserisci i dati !");
	
		}
		catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Errore nel formato del dato inserito");
					return;
					} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}	
		}


class MyActionListenerReset implements ActionListener{
	
	
	@Override
	public void actionPerformed(ActionEvent e)  {
		
		
			
			txfCognome.setText("");
			txfNome.setText("");
			txfDataNascita.setText("");
			txfSquadra.setText("");
			txfID.setText("");
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setRowCount(0);
			txfCognome.requestFocusInWindow();
			
		
	}
}
			//Visualizzo i dati nella JTable e si aggiornano dopo ogni inserimento,
			// il programma va chiuso e riaperto per visualizzare il cambiamento.
			
			public JPanel tabella() throws IOException {
				JPanel panel = new JPanel(new BorderLayout());
				table = readAllData();
				
				table.setPreferredScrollableViewportSize(new Dimension(650, 270));
				table.setFillsViewportHeight(true);	
				
				 Color ivory=new Color(255,255,208);
				 table.setBackground(ivory);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setSize(400, 300);
				panel.add(scrollPane, BorderLayout.CENTER);
				
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				
				//myTableRowSorter = new TableRowSorter();
				//table.setRowSorter(myTableRowSorter);
				
				
				return panel;
			}
			
			//Creo dei sottopannelli dove inserire i layout ed i relativi compomenti.
			
			public JPanel datiInput()throws IOException{
				
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
				 lblCognome = new JLabel("Cognome", JLabel.RIGHT);
				 panel.add(lblCognome);
				 txfCognome = new JTextField();
				 Color ivory = new Color(255,255,208);
				 txfCognome.setBackground(ivory);
				 txfCognome.setPreferredSize(new Dimension(90,30));
				 panel.add(txfCognome);
				 lblNome = new JLabel("Nome", JLabel.RIGHT);
				 panel.add(lblNome);
				 txfNome = new JTextField();
				 Color ivory2 = new Color(255,255,208);
				 txfNome.setBackground(ivory2);
				 txfNome.setPreferredSize(new Dimension(90,30));
				 panel.add(txfNome);
				 lblDataNascita = new JLabel("Data", JLabel.RIGHT);
				 panel.add(lblDataNascita);
				 txfDataNascita = new JTextField();
				 Color ivory3 = new Color(255,255,208);
				 txfDataNascita.setBackground(ivory3);
				 txfDataNascita.setPreferredSize(new Dimension(90,30));
				 panel.add(txfDataNascita);
				 lblSquadra = new JLabel("Squadra", JLabel.RIGHT);
				 panel.add(lblSquadra);
				 txfSquadra = new JTextField();
				 Color ivory4 = new Color(255,255,208);
				 txfSquadra.setBackground(ivory4);
				 txfSquadra.setPreferredSize(new Dimension(90,30));
				 panel.add(txfSquadra);
				lblID = new JLabel("ID", JLabel.RIGHT);
				 panel.add(lblID);
				 txfID = new JTextField();
				 Color ivory5 = new Color(255,255,208);
				 txfID.setBackground(ivory5);
				 txfID.setPreferredSize(new Dimension(90,30));
				 panel.add(txfID);
							 
				 return panel;
			}
			
			


			
			public JPanel buttons()throws IOException{
				
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
				
				display = new JButton("Display");
				display.addActionListener(new MyActionListenerDisplay());
				panel.add(display);
				
				inserisci = new JButton("Inserisci");
				inserisci.addActionListener(new MyActionListenerInserisci());
				panel.add(inserisci);
				
				ordina = new JButton("Ordina per squadra");
				ordina.addActionListener(new MyActionListenerOrdina());
				panel.add(ordina);
				
				filtra = new JButton("Filtra per squadra");
				filtra.addActionListener(new MyActionListenerFiltra());
				panel.add(filtra);
				
				aggiorna = new JButton("Aggiorna");
				aggiorna.addActionListener(new MyActionListenerAggiorna());
				panel.add(aggiorna);
				
				elimina = new JButton("Elimina");
				elimina.addActionListener(new MyActionListenerElimina());
				panel.add(elimina);
				
				reset = new JButton("Reset");
				reset.addActionListener(new MyActionListenerReset());
				panel.add(reset);
				
				
				
				return panel;
			}
			
			
			
			//Utilizzo il metodo che mi permette di costruire la JTable ed effettuare la connessione al DB nella classe atletiDB
			
			private static JTable readAllData() throws IOException {
				JTable aux_table = null;
				
				try {
					
					atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
					
					ResultSetMetaData rsmd = atleta.getMetadata();
					int c = rsmd.getColumnCount();
								
					ArrayList<Persona> aux = atleta.getAll();			
					
					Vector<Vector> data = new Vector<Vector>(); 
					Vector<String> row = null; 
					 
					for (int r = 0; r < aux.size(); r++) {
						row = new Vector<String>();
						
						row.add(0, String.valueOf(aux.get(r).getId()));
						row.add(1, aux.get(r).getCognome());
						row.add(2, aux.get(r).getNome());
						row.add(3, aux.get(r).getDatanascita());
						row.add(4, aux.get(r).getSquadra());
						
						
						data.add(row);
						
					}
						
						
					Vector<String> columnNames = new Vector<String>(c);

					 for(int i = 1; i <= c; i++) {
						 columnNames.add(rsmd.getColumnName(i)); 
					 } 
					 
					 aux_table = new JTable(data, columnNames); 
					
				} catch (SQLException eSQL) {
					System.out.println("ERRORE DATABASE: " + eSQL.toString());	
				} finally {
					if (atleta != null)
						try {
							atleta.close();
						} catch (SQLException eSQL) {
							eSQL.printStackTrace();
						}
				}
				
				return aux_table;
			}
		
			
			private static void stampaElenco(ArrayList<String> elencoAtleti) {
				System.out.println("\n");
				System.out.println("--- Elenco degli atleti ---");
				for (String itr : elencoAtleti)
					System.out.println(itr);
				System.out.println("-------------------------");
				System.out.println("\n");
			}
			
			
			class MyActionListenerFiltra2 implements ActionListener{
				
				@Override
				public void actionPerformed(ActionEvent e)  {
						
					try {
						
						testoTextFieldCognome = txfCognome.getText();
						testoTextFieldNome = txfNome.getText();
						testoTextFieldDataNascita = txfDataNascita.getText();
						testoTextFieldSquadra = txfSquadra.getText();
						
						try {
							atleta = new AtletiDB(AtletiTest.DB_URL, AtletiTest.DB_NAME, AtletiTest.DB_USER, AtletiTest.DB_PASS);
				
							
						} catch (SQLException e2) {
							
							e2.printStackTrace();
						}
						
						
						if((testoTextFieldCognome.equals("")) | (testoTextFieldNome.equals(""))
								| (testoTextFieldDataNascita.equals("") | (!testoTextFieldSquadra.equals("")))){
							
							//Azzero le righe della Jtable per non avere duplicati in fase di inseriemnto
							DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							tableModel.setRowCount(0);
		
							//Dopo le operazioni di inserimento resetto i campi.
							txfCognome.setText("");
							txfNome.setText("");
							txfDataNascita.setText("");
							txfSquadra.setText("");
							
							//Rimetto il focus al campo prodotto.
							txfCognome.requestFocusInWindow();
							
							//Mostro a video una finestra quando i dati sono stati trovati con successo.
							JOptionPane.showMessageDialog(null,  "Atleti ordinati per squadra !");
												
							
						}	
					
						else JOptionPane.showMessageDialog(null,  "Inserisci i dati !");
				}
					catch(NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Errore nel formato del dato inserito");
								return;
								}
					
						}	
					}
							
						
						
							
						
					}
			

				
