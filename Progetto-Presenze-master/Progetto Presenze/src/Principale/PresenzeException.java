package Principale;
/**
 * Clsse creata dal programmatore per la gestione delle semplici eccezioni
 * @author Ervin
 *
 */
public class PresenzeException extends Exception 
{
	private String messaggio;
	
	/**
	 * Così si permette di stampare su schermo un messaggio
	 * @param messaggio (String)
	 */
	public PresenzeException (String messaggio)
	{
		this.messaggio=messaggio;
	}
	/**
	 * Si restituisce la stringa
	 */
	public String toString()
	{
		return messaggio;
	}
}
