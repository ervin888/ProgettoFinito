package Principale;

import java.io.*;
/**
 * Classe che si occupa del salvataggio e il caricamento di tutto il progetto
 * @author Ervin
 *
 */
public class SaveRestore 
{
	/**
	 * Salva il progetto
	 * @param lista
	 * @throws IOException
	 * @throws PresenzeException
	 */
	public static void saveProject(Presenze lista) throws IOException, PresenzeException			//non mi interessa gestire le eccezioni in quanto genero sempre tutto da 0
	{
		ObjectOutputStream out =new ObjectOutputStream ( new BufferedOutputStream (new FileOutputStream ("Data.dat" )));
		int elementi = lista.getElementi(); 
		out.writeObject(elementi);		
		elementi++;
		for (int i = 1; i < elementi; i++)
		{
			out.writeObject(lista.getPresenza(i));			
		}
		out.close();
	}
	/**
	 * Carica il progetto
	 * @param lista
	 * @return
	 * @throws PresenzeException
	 */
	public static boolean loadProject(Presenze lista) throws PresenzeException			//qua lo gestisco in quanto mi serve sapere se posso caricare o no i dati  
	{
		try
		{
			int elementi = 0;
			ObjectInputStream in =new ObjectInputStream ( new BufferedInputStream (new FileInputStream ("Data.dat" )));
			elementi = (int) in.readObject();
			elementi++;
			Presenza rTemp = new Presenza();
			for (int i = 1; i < elementi; i++) 
			{
				rTemp = (Presenza) in.readObject();
				lista.inserisciInPosizione(rTemp, i);
			}
			in.close();
			return true;
		}
		catch (FileNotFoundException e)			//niente file
		{
			return false;
		}
		catch (IOException e)			//problemi di collegamento (fisici)
		{
			return false;
		}	
		catch (ClassNotFoundException e)			//problemi di corruzione
		{
			return false;
		}
	}
	
	
	
	
}
