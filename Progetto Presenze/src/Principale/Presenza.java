package Principale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Presenza implements Serializable  		//Attributi
{
	int codiceidentificativoPresenza;
	int numeroMatricola;
	LocalDateTime oraIngresso;
	LocalDateTime oraUscita;
	
///////////////////////////////////////////////////////////////////////////////////////////////////// Costruttori	
	public Presenza(int codiceidentificativoPresenza, int numeroMatricola, LocalDateTime oraIngresso) 
	{
		this.codiceidentificativoPresenza = codiceidentificativoPresenza;
		this.numeroMatricola = numeroMatricola;
		this.oraIngresso = oraIngresso;
	}
	
	public Presenza()
	{
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////	Getter and Setter
	public int getcodiceidentificativoPresenza() 
	{
		return codiceidentificativoPresenza;
	}
	
	public void setcodiceidentificativoPresenza(int codiceidentificativoPresenza) 
	{
		this.codiceidentificativoPresenza = codiceidentificativoPresenza;
	}
	
	public int getNumeroMatricola() 
	{
		return numeroMatricola;
	}
	
	public void setNumeroMatricola(int numeroMatricola) 
	{
		this.numeroMatricola = numeroMatricola;
	}
	
	public LocalDateTime getOraIngresso() 
	{
		return oraIngresso;
	}
	
	public void setOraIngresso(LocalDateTime oraIngresso) 
	{
		this.oraIngresso = oraIngresso;
	}
	
	public LocalDateTime getOraUscita() 
	{
		return oraUscita;
	}
	
	public void setOraUscita(LocalDateTime oraUscita) 
	{
		this.oraUscita = oraUscita;
	}

	public String DataAndOraIngressoToSting()
	{
		String Stringa = "La data di ingresso è: " + oraIngresso.getYear() + " " + oraIngresso.getMonth() + " " + oraIngresso.getDayOfMonth() + "  " + "  " + "e l'ora di ingresso è: " + oraIngresso.getHour() + ":" + oraIngresso.getMinute();
		return Stringa;
	}
	
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

	
	public String toString() 
	{
		return "Presenza [codiceidentificativoPresenza=" + codiceidentificativoPresenza + ", numeroMatricola=" + numeroMatricola + ", " + this.DataAndOraIngressoToSting() + ", " + this.DataAndOraUscitaToSting() + "]";
	}
	
	public void BackupDegliEliminati() throws IOException 
	{		
		PrintWriter file_output =new PrintWriter ( new BufferedWriter (new FileWriter ("Backup presenze.txt", true )));
		
		file_output.println(this.toString());
		
		file_output.close();
	}
	
	public void BackupDegliEliminatiManualmente() throws IOException 
	{		
		PrintWriter file_output =new PrintWriter ( new BufferedWriter (new FileWriter ("Backup presenze eliminate manualmente.txt", true )));
		
		file_output.println(this.toString());
		
		file_output.close();
	}
	
	
	

}
