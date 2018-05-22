package Principale;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Si occupa di tutte le voci del menu
 * @author Ervin
 *
 */
public class Menu 
{
	/**
	 * Aggiunge elementi alla lista
	 * @param lista (Presenze)
	 * @param presenzaVoce1
	 * @throws PresenzeException
	 */
	public static void voce1(Presenze lista, Presenza presenzaVoce1) throws PresenzeException 		//aggiugni alla lista
	{
		lista.inserisciInPosizione(presenzaVoce1, lista.getElementi()+1);
	}
	/**
	 * Immette l'orario di uscita
	 * @param lista (Presenze)
	 * @param numeroMatricola (int)
	 * @return boolean
	 * @throws PresenzeException
	 */
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
	/**
	 * Visualizza i presenti in ordine cronologico
	 * @param lista (Presenze)
	 * @throws PresenzeException
	 */
	public static void voce3(Presenze lista) throws PresenzeException 		//Visualizza tutti i presenti attualmente (per ordine di ingresso)
	{
		VisualizzaRiordinato.ordineCronologico(lista);
	}
	/**
	 * Ricerca in base a data e matricola
	 * @param lista (Presenze)
	 * @param anno (int)
	 * @param mese (int)
	 * @param giorno (int)
	 * @param numeromatricola (int)
	 * @throws PresenzeException
	 */
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
	/**
	 * Visualizza tutti i presenti in ordine cronologico inverso
	 * @param lista (Presenze)
	 * @throws PresenzeException
	 */
	public static void voce5(Presenze lista) throws PresenzeException 		//Visualizza tutti i presenti attualmente (per ordine di ingresso inverso)
	{
		VisualizzaRiordinato.ordineCronologicoInverso(lista);
	}
	/**
	 * Elimina i dati relativi alle timbrature più vecchie di 30 giorni 
	 * @param lista (Presenze)
	 * @throws PresenzeException
	 * @throws IOException
	 */
	public static void voceEliminaVecchi(Presenze lista) throws PresenzeException, IOException 		//QUI ELIMINIAMO LE TIMBRATURE VECCHIE
	{
		LocalDateTime now = LocalDateTime.now();
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{				//PER MODIFICARE E FARE I TEST AL POSTO DI ChronoUnit.HOURS.between USA ChronoUnit.HOURS.between ChronoUnit.SECONDS.between CHE TROVA LA DIFFERENZA IN SECONDI E NON ORE E AL POSTO DI if (differenzaInGiorni > 720) SCRIVI if (differenzaInGiorni > 10) COSì OGNI 10 SECONDI LE ELIMINA)
			long differenzaInGiorni = ChronoUnit.SECONDS.between(lista.getPresenza(i).getOraIngresso(), now); //QUI VERIFICHIAMO CHE LA DIFFERENZA TRA LA DATA ODIERNA E LA DATA DI TIMBRATURA NON SIA MAGGIORE DI 30 GIORNI. 
			if (differenzaInGiorni > 10) //uso le ore per essere preciso all'ora e non al secondo ad esempio (720 ore = 30 giorni)
			{
				lista.getPresenza(i).BackupDegliEliminati();
				lista.eliminaInPosizione(i);
				i--;
			} 

		}
	}
	/**
	 * Elimina manualmente tutti i dati relativi ad una matricola
	 * @param lista (Presenze)
	 * @param numeroMatricola (int)
	 * @return boolean
	 * @throws PresenzeException
	 * @throws IOException
	 */
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
	/**
	 * Questo metodo cerca in base alla matricola e ritorna la posizione dell'oggetto con quel numero
	 * @param lista (Presenze)
	 * @param numeroMatricola (int)
	 * @return int (un controllo per verificare se esiste la mtricola)
	 * @throws PresenzeException
	 */
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
