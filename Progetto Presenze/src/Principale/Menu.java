package Principale;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public class Menu 
{
	public static void voce1(Presenze lista, Presenza presenzaVoce1) throws PresenzeException 		//aggiugni alla lista
	{
		lista.inserisciInPosizione(presenzaVoce1, lista.getElementi()+1);
	}
	
	public static boolean voce2(Presenze lista, int numeroMatricola) throws PresenzeException 		//immetti orario di uscita
	{
		
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{
			if (lista.getPresenza(i).getNumeroMatricola() == numeroMatricola && lista.getPresenza(i).getOraUscita() == null) 
			{
				lista.getPresenza(i).setOraUscita(LocalDateTime.now());
				return true;
			}
		}
		return false;
		
	}
	
	public static void voce3(Presenze lista) throws PresenzeException 		//Visualizza tutti i presenti attualmente (per ordine di ingresso)
	{
		VisualizzaRiordinato.ordineCronologico(lista);
	}
	
	public static void voce4(Presenze lista, int anno, int mese, int giorno, int numeromatricola) throws PresenzeException 		//Ricerca in base a data e numero di matricola
	{
		boolean controllo = false;
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{
			if (lista.getPresenza(i).getOraIngresso().getYear() == anno && lista.getPresenza(i).getOraIngresso().getMonthValue() == mese && lista.getPresenza(i).getOraIngresso().getDayOfMonth() == giorno && lista.getPresenza(i).getNumeroMatricola() == numeromatricola) 
			{
				System.out.println("Il dipendente e i suoi dati sono stati trovati, sono elencati di seguito:");
				System.out.println(lista.getPresenza(i).toString() + "\n");
				controllo = true;
			}
		}
		if (controllo == false) 
		{
			System.out.println("Mi spiace ma sembra che alcuni dati siano errati, o non è stato trovato alcun dipendente");
		}
		System.out.println("\n" + "\n");
	}	
	
	public static void voce5(Presenze lista) throws PresenzeException 		//Visualizza tutti i presenti attualmente (per ordine di ingresso)
	{
		VisualizzaRiordinato.ordineCronologicoInverso(lista);
	}
	
	public static void voceEliminaVecchi(Presenze lista) throws PresenzeException, IOException 		//in ultimo bisogna auto eliminare quelli più vecchi di 30 giorni e salvarli su file
	{
		LocalDateTime now = LocalDateTime.now();
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{
			long differenzaInGiorni = ChronoUnit.HOURS.between(lista.getPresenza(i).getOraIngresso(), now);
			if (differenzaInGiorni > 720) //uso le ore per essere preciso all'ora e non al secondo ad esempio (720 ore = 30 giorni)
			{
				lista.getPresenza(i).BackupDegliEliminati();
				lista.eliminaInPosizione(i);
				i--;
			} 

		}
	}
	
	public static boolean EliminaManualmente(Presenze lista, int numeroMatricola) throws PresenzeException, IOException			//il return in questo caso funge come un break per i cicli, niente di più;
	{
		int posizione = 0;
		int numeroiterazioni = 0;
		int elementi = lista.getElementi()+1;
		for (int i = 1; i < elementi; i++) 
		{		
			posizione = ricercaInBaseAllaMatricola(lista, numeroMatricola);
			if (posizione == -69 && numeroiterazioni == 0) 
			{
				System.out.println("La matricola che hai inserito pare non essere nei nostri archivi" + "\n" + "\n");
				return false;
			} 
			else if (posizione == -69 && numeroiterazioni > 0) 
			{
				System.out.println("Tutti i dati della matricola che hai inserito sono stati salvati ed eliminati" + "\n" + "\n");
				return false;
			}
			else 
			{
			lista.getPresenza(posizione).BackupDegliEliminatiManualmente();
			lista.eliminaInPosizione(posizione);
			elementi--;
			i--;
			numeroiterazioni++;
			}
		}
		System.out.println("Tutti i dati della matricola che hai inserito sono stati salvati ed eliminati" + "\n" + "\n");
		
		
		return true;

	}
	
	public static int ricercaInBaseAllaMatricola(Presenze lista, int numeroMatricola) throws PresenzeException
	{
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{
			if (lista.getPresenza(i).getNumeroMatricola() == numeroMatricola) 
			{
				return i;
			} 
		}
		return -69; //-69 è il valore per cui la matricola non esiste nella lista
	}
	
	
	
	
}
