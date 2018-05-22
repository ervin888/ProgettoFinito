package Principale;

//questa classe riordina la lista anche se non servirebbe in quanto sono gi� in ordine cronologico ma lo faccio per dimostrare la sua utilit�, oltretutto cambiano la condizione dell'if si pu� invertire il tutto mettendo come prime le presenze pi� recenti
/**
 * Classe che riordina
 * @author Ervin
 *
 */
public class VisualizzaRiordinato 
{
	/**
	 * Visualizza in ordine cronologico i presenti
	 * @param lista
	 * @throws PresenzeException
	 */
	public static void ordineCronologico(Presenze lista) throws PresenzeException
	{
		boolean controllo = true;
		int elementi = lista.getElementi()+1;
		Presenze listatemp = new Presenze();
		for (int i = 1; i < elementi; i++) 
		{
			if (lista.getPresenza(i).getOraUscita() == null) 
			{
				listatemp.inserisciInPosizione(lista.getPresenza(i), i);
			}			
		}
		elementi = listatemp.getElementi();
	do
	{
		controllo = true;
		for (int i = 1; i < elementi; i++) 
		{
			if (listatemp.getPresenza(i).getOraIngresso().compareTo(listatemp.getPresenza(i+1).getOraIngresso()) > 0)
			{
				listatemp.inserisciInPosizione(listatemp.getPresenza(i+1), i);
				listatemp.eliminaInPosizione(i+2);
				controllo = false;
			}
			else
			{
			}
		}
	}while(controllo == false);			//false = controllo non passato, non in ordine
		
	VisualizzaRiordinato.visualizzaListaTempRiordinata(listatemp);
	}
	/**
	 * Visualizza in ordine cronologico inverso i presenti
	 * @param lista
	 * @throws PresenzeException
	 */
	public static void ordineCronologicoInverso(Presenze lista) throws PresenzeException
	{
		boolean controllo = true;
		int elementi = lista.getElementi()+1;
		Presenze listatemp = new Presenze();
		for (int i = 1; i < elementi; i++) 
		{
			if (lista.getPresenza(i).getOraUscita() == null) 
			{
				listatemp.inserisciInPosizione(lista.getPresenza(i), i);
			}			
		}
		elementi = listatemp.getElementi();
	do
	{
		controllo = true;
		for (int i = 1; i < elementi; i++) 
		{
			if (listatemp.getPresenza(i).getOraIngresso().compareTo(listatemp.getPresenza(i+1).getOraIngresso()) < 0)
			{
				listatemp.inserisciInPosizione(listatemp.getPresenza(i+1), i);
				listatemp.eliminaInPosizione(i+2);
				controllo = false;
			}
			else
			{
			}
		}
	}while(controllo == false);			//false = controllo non passato, non in ordine
		
	VisualizzaRiordinato.visualizzaListaTempRiordinata(listatemp);
	}
	/**
	 * Metodo che stampa a schermo la lista riordinata
	 * @param lista
	 * @throws PresenzeException
	 */
	public static void visualizzaListaTempRiordinata(Presenze lista) throws PresenzeException
	{
		for (int i = 1; i < lista.getElementi()+1; i++) 
		{
			System.out.println(lista.getPresenza(i).toString());
		}
		System.out.println("Visualizzazione completata");
		System.out.println("--------------------------------------------------");
		System.out.println();
	}
	
	
	
	
	
}
