package Principale;

import java.util.Scanner;
/**
 * Si verificano alcune condizioni
 * @author Ervin
 *
 */
public class Controlli 
{
	/**
	 * Si verifica che l'input sia numerico
	 * @return controllo (int)
	 */
	public static int controlloInputInt()
	{
		Scanner tastiera = new Scanner(System.in);
		int controllo = 0;
		do
		{
		try 
		{
			controllo  = tastiera.nextInt();
			break;
		} 
		catch (java.util.InputMismatchException e) 
		{
			System.out.println("Inserimento sbagliato... RIPROVA");
			tastiera.nextLine();
		}	
		}while(true);
		
		return controllo;
	}
	
	/**
	 * Si controlla che la matricola non esista già in lista e non abbia ancora effettuato l'uscita
	 * @param lista (Presenze)
	 * @param numeromatricola (int)
	 * @return boolean
	 * @throws PresenzeException
	 */
	
	public static boolean controlloNumeroMatricola(Presenze lista, int numeromatricola) throws PresenzeException		//verifico che il numero di matricola non sia già presente nella lista e non abbia ancora effettuato l'uscita
	{
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{
			if (lista.getPresenza(i).getNumeroMatricola() == numeromatricola && lista.getPresenza(i).getOraUscita() == null) 
			{
				return false;		//C'è già questa matricola
			} 
		}		
		return true;	//non c'è già questa matricola
	}
	
	
}
