/*
 * 
 * Autore: Giuseppe Liberati.
 */

package Atleti;
//Costruisco la classe che mi definisce l'istanza dell'atleta.
public class Persona {

	private int id;
	private String cognome;
	private String nome;
	private String datanascita;
	private String squadra;
	
public Persona(int i, String c, String n, String d, String s) {
		
		id = i;
		cognome = c;
		nome = n;
		datanascita = d;
		squadra = s;
}
//Polimorfismo: creo il secondo costruttore.
public Persona() {
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getDatanascita() {
	return datanascita;
}

public void setDatanascita(String datanascita) {
	this.datanascita = datanascita;
}

public String getSquadra() {
	return squadra;
}

public void setSquadra(String squadra) {
	this.squadra = squadra;
}



}
