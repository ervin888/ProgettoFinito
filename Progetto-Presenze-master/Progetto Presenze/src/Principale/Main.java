package Principale;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Il Main gestisce tutto e semplicemente non ha metodi all'infuori del main
 * @author Ervin
 *
 */
public class Main 
{

	public static void main(String[] args) throws PresenzeException, IOException 
	{
		int codiceidentificativo = 0;		//creo il codice identificativo che sarà progressivo
		Presenze Lista = new Presenze();	//creo la lista

		if (SaveRestore.loadProject(Lista) == true) 
		{
			System.out.println("Il database è stato aperto e letto correttamente");
			System.out.println();
			System.out.println();
		} 
		else 
		{
			System.out.println("I dati non sono stati trovati, o non esistono, o sono corrotti");
			System.out.println();
			System.out.println();
		}
		try 
		{
			codiceidentificativo = Lista.getPresenza(Lista.getElementi()).getcodiceidentificativoPresenza();	//restauro, se esiste il vecchio codice identificativo
			codiceidentificativo++; 
		} 
		catch (PresenzeException e) 
		{
		}
		///////////////////////////////////////
		int controllo = 0;
		int variabile = 0;
		do
		{
			Menu.voceEliminaVecchi(Lista);		//FUNZIONE CHE ELIMINA LE TIMBRATURE PIù VECCHIE DI 30 GIORNI (PARAMETRO MODIFICABILE PER ESEGUIRE IL TEST)
			SaveRestore.saveProject(Lista);
			System.out.println("Voci selezionabili del Menu inserendo il numero desiderato: (in caso inserisci un altro numero il programma termina)");
			System.out.println("1)  Aggiungi presenza");
			System.out.println("2)  Registra un'uscita");
			System.out.println("3)  Visualizza tutti i presenti (per ordine di ingresso)");
			System.out.println("4)  Ricerca in base a data e numero di matricola");
			System.out.println("5)  Visualizza tutte le presenze in lista");
			System.out.println("6)  Visualizza tutti i presenti (per ordine di ingresso inverso)");
			System.out.println("7)  Elimina manualmente tutte le presenze di una matricola (salvandola su un file apposito)");
			
			controllo = Controlli.controlloInputInt();

			switch (controllo) 
			{
			case 1:
				Menu.voceEliminaVecchi(Lista);
				Presenza presenzaVoce1 = new Presenza();
				System.out.println("Inserisci il numero di matricola");
				variabile = Controlli.controlloInputInt();
				if (Controlli.controlloNumeroMatricola(Lista, variabile) == false) 
				{
					System.out.println("Il numero della matricola è già esistente e non ha ancora eseguito la timbratura di uscita" + "\n" + "\n");
				}	
				else
				{
				presenzaVoce1.setcodiceidentificativoPresenza(codiceidentificativo);
				presenzaVoce1.setNumeroMatricola(variabile);
				presenzaVoce1.setOraIngresso(LocalDateTime.now());
				/////////////////////////////////////////////////////////////
				Menu.voce1(Lista, presenzaVoce1);
				codiceidentificativo++;
				System.out.println("La tua timbratura è stata correttamente processata, di seguito riportiamo i dati di quest'ultima");
				System.out.println(Lista.getPresenza(Lista.getElementi()).DataAndOraIngressoToSting());
				System.out.println("/////////////////////////////////////////////////////////////////" + "\n" + "\n" + "\n");
				}
				break;
				
			case 2:
				Menu.voceEliminaVecchi(Lista);
				System.out.println("Inserisci il codice della matricola di cui vuoi timbrare l'uscita");
				variabile = Controlli.controlloInputInt();
				if (Menu.voce2(Lista, variabile) == true) 
				{
					System.out.println("Uscita registrata" + "\n" + "\n");
				}
				else
				{
					System.out.println("Il codice da te inserito sembra non essere nella lista" + "\n" + "\n");
				}
				break;
				
			case 3:
				Menu.voceEliminaVecchi(Lista);
				Menu.voce3(Lista);
				break;
				
			case 4:
				Menu.voceEliminaVecchi(Lista);
				int anno = 0;
				int mese = 0;
				int giorno = 0;
				System.out.println("Molto bene, ora ti chiederò ancora un po' di dati per la ricerca");
				do
				{
				System.out.println("Inserisci l'anno (come numero)");
				anno = Controlli.controlloInputInt();
				System.out.println("Inserisci il mese (come numero)");
				mese = Controlli.controlloInputInt();
				System.out.println("Inserisci il giorno (come numero)");
				giorno = Controlli.controlloInputInt();
				if (anno>LocalDateTime.now().getYear() || mese > 12 || mese < 1 || giorno < 1 || giorno > 31) 
				{
					System.out.println("Controlla bene i tuoi dati di ricerca e reinseriscili da capo..." + "\n");
				}
				else
				{
					break;
				}
				}while(true);
				
				System.out.println("Inserisci il numero di matricola");
				variabile = Controlli.controlloInputInt();				
				Menu.voce4(Lista, anno, mese, giorno, variabile);
				break;

			case 5:
				Menu.voceEliminaVecchi(Lista);
				for (int i = 1; i < Lista.getElementi()+1; i++) 
				{
					System.out.println(Lista.getPresenza(i).toString());
				}
				System.out.println("\n" + "\n");
				break;
				
			case 6:
				Menu.voceEliminaVecchi(Lista);
				Menu.voce5(Lista);
				break;
				
			case 7:
				Menu.voceEliminaVecchi(Lista);
				System.out.println("Inserisci il numero della matricola che ti interessa eliminare");
				variabile = Controlli.controlloInputInt();
				Menu.EliminaManualmente(Lista, variabile);
				break;
				
			
				
			default: 
			System.out.println("Programma terminato");
			controllo = -1;
				break;
			}			
		}while(controllo != -1);
		
		Menu.voceEliminaVecchi(Lista);
		SaveRestore.saveProject(Lista);
		
		
	}
		
		
		
		

}


