package Principale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe Presenza (singolo oggetto) con i suoi attributi
 * @author Ervin
 *
 */
public class Presenza implements Serializable  		//Attributi
{

	int codiceidentificativoPresenza;
	int numeroMatricola;
	LocalDateTime oraIngresso;
	LocalDateTime oraUscita;
	
///////////////////////////////////////////////////////////////////////////////////////////////////// Costruttori	
	/**
	 * Costruttore di Presenza.
	 * @param codiceidentificativoPresenza indica un numero arbitrariamente attribuito dal programma che identifica una sola timbratura.
	 * @param numeroMatricola indica il numero di matricola del dipendete
	 * @param oraIngresso viene auto aggiunta sulla base dell'orario al momento della timbratura. indica l'orario di inzio
	 */
	public Presenza(int codiceidentificativoPresenza, int numeroMatricola, LocalDateTime oraIngresso) 
	{
		this.codiceidentificativoPresenza = codiceidentificativoPresenza;
		this.numeroMatricola = numeroMatricola;
		this.oraIngresso = oraIngresso;
	}
	/**
	 * Costruttore vuoto di Presenza
	 */
	public Presenza()
	{
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////	Getter and Setter
	/**
	 * si ottiene il codiceidentificativo.
	 * @return codiceidentificativo (come intero)
	 */
	public int getcodiceidentificativoPresenza() 
	{
		return codiceidentificativoPresenza;
	}
	/**
	 * Si setta il codiceidentificativo
	 * @param codiceidentificativo (come intero)
	 */
	public void setcodiceidentificativoPresenza(int codiceidentificativoPresenza) 
	{
		this.codiceidentificativoPresenza = codiceidentificativoPresenza;
	}
	/**
	 * si ottiene il numero di matricola.
	 * @return numeromatricola (come intero)
	 */
	public int getNumeroMatricola() 
	{
		return numeroMatricola;
	}
	/**
	 * si setta il numero della matricola
	 * @param numeroMatricola numeromatricola (come intero)
	 */
	public void setNumeroMatricola(int numeroMatricola) 
	{
		this.numeroMatricola = numeroMatricola;
	}
	/**
	 * Si ottiene l'ora di ingresso
	 * @return oraingresso (come LocalDateTime)
	 */
	public LocalDateTime getOraIngresso() 
	{
		return oraIngresso;
	}
	/**
	 * Si setta l'ora di ingresso
	 * @param oraingresso (come LocalDateTime)
	 */
	public void setOraIngresso(LocalDateTime oraIngresso) 
	{
		this.oraIngresso = oraIngresso;
	}
	/**
	 * Si ottiene l'ora di uscita
	 * @return orauscita (come LocalDateTime)
	 */
	public LocalDateTime getOraUscita() 
	{
		return oraUscita;
	}
	/**
	 * Si setta l'ora di uscita
	 * @param orauscita (come LocalDateTime)
	 */
	public void setOraUscita(LocalDateTime oraUscita) 
	{
		this.oraUscita = oraUscita;
	}
/**
 * Si ottiene una stringa contenente data e ora di ingresso secondo il formato scelto dal programmatore
 * @return Stringa 
 */
	public String DataAndOraIngressoToSting()
	{
		String Stringa = "La data di ingresso è: " + oraIngresso.getYear() + " " + oraIngresso.getMonth() + " " + oraIngresso.getDayOfMonth() + "  " + "  " + "e l'ora di ingresso è: " + oraIngresso.getHour() + ":" + oraIngresso.getMinute();
		return Stringa;
	}
	/**
	 * Si ottiene una stringa contenente data e ora di uscita secondo il formato scelto dal programmatore
	 * @return Stringa 
	 */
	public String DataAndOraUscitaToSting()
	{
		try 
		{
			String Stringa = "La data di uscita è: " + oraUscita.getYear() + " " + oraUscita.getMonth() + " " + oraUscita.getDayOfMonth() + "  " + "  " + "e l'ora di uscita è: " + oraUscita.getHour() + ":" + oraUscita.getMinute();
			return Stringa;
		} 
		catch (NullPointerException e) 
		{
			return "Data di uscita non ancora inserita";
		}
	}

	/**
	 * Si ottiene una stringa contenente i dati di una timbratura
	 * @return Stringa 
	 */
	public String toString() 
	{
		return "Presenza [codiceidentificativoPresenza=" + codiceidentificativoPresenza + ", numeroMatricola=" + numeroMatricola + ", " + this.DataAndOraIngressoToSting() + ", " + this.DataAndOraUscitaToSting() + "]";
	}
	/**
	 * Si esegue il Backup delle timbrature più vecchie di 30 giorni
	 * @throws IOException gestione di eccezzioni di I/O
	 */
	public void BackupDegliEliminati() throws IOException 
	{		
		PrintWriter file_output =new PrintWriter ( new BufferedWriter (new FileWriter ("Backup presenze.txt", true )));
		
		file_output.println(this.toString());
		
		file_output.close();
	}
	/**
	 * Si esegue il Backup delle timbrature prima che i dati di queste vengano eliminati in base alla scelta dell'utente
	 * @throws IOExceptiongestione di eccezzioni di I/O
	 */
	public void BackupDegliEliminatiManualmente() throws IOException 
	{		
		PrintWriter file_output =new PrintWriter ( new BufferedWriter (new FileWriter ("Backup presenze eliminate manualmente.txt", true )));
		
		file_output.println(this.toString());
		
		file_output.close();
	}
	
	
	

}
