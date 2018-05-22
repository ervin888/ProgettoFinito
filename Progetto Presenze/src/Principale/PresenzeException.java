package Principale;

public class PresenzeException extends Exception 
{
	private String messaggio;
	
	public PresenzeException (String messaggio)
	{
		this.messaggio=messaggio;
	}
	
	public String toString()
	{
		return messaggio;
	}
}
