package Principale;

//ricordiamo che la posizione 0 non esiste e che verrà sempre messo in posizione 1 
/**
 * Lista delle presenze
 * @author Ervin
 *
 */
public class Presenze 
{

	private Nodo head;
	private int elementi;
	/**
	 * Crea una lista vuota per essere poi popolata
	 */
	public Presenze()
	{
		head=null;
		elementi=0;
	}
	/**
	 * Restituisce il numero degli oggetti presenti in lista
	 * @return elementi (int)
	 */
	public int getElementi()
	{
		return elementi;
	}
/**
 * Setta il numero degli elementi
 * @param elementi (int)
 */
	public void setElementi(int elementi)
	{
		this.elementi = elementi;
	}
	/**
	 * Crea un nodo
	 * @param R
	 * @param link
	 * @return nodo
	 */
	private Nodo creaNodo(Presenza R, Nodo link)
	{
		Nodo nodo= new Nodo(R);
		nodo.setLink(link);
		return nodo;
	}
	/**
	 * Ottiene il link di una posizione
	 * @param posizione
	 * @return nodo
	 * @throws PresenzeException
	 */
	private Nodo getLinkPosizione(int posizione) throws PresenzeException 
	{
		
		Nodo p;
		int n;
		p=head;
		n=1;
		
		if (posizione<1 || posizione>getElementi())
			throw new PresenzeException ("Posizione non valida");
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
			
		while(p.getLink()!=null && n<posizione)
		{
			p=p.getLink();	
			n++;
		}
		
		return p;
	}
	/**
	 * Inserisce l'oggetto in testa
	 * @param R (Presenza)
	 */
	public void inserisciInTesta (Presenza R)
	{
		
		Nodo p=creaNodo(R, head);
		head=p;
		elementi++;
	}
	/**
	 * Trasforma in stringa
	 */
	public String toString()
	{
		String risultato="Head";
		if (elementi==0)
			return risultato+="-->";
		Nodo p=head;
		while (p!=null)
		{
			risultato+="-->"+p.getInfo().toString();
			p=p.getLink();
		}
		return risultato;
	}
	/**
	 * Inserisce l'oggetto in coda
	 * @param R (Presenza)
	 * @throws PresenzeException
	 */
	public void inserisciInCoda(Presenza R) throws PresenzeException 
	{
		if(elementi==0)
		{
			inserisciInTesta(R);
			return;
		}
		
		Nodo pn=creaNodo(R, null);
		Nodo p=getLinkPosizione(elementi);
		p.setLink(pn);
		elementi++;	
	}
	/**
	 * Inserisce l'oggetto nella posizione desiderata
	 * @param R (Presenza)
	 * @param posizione (int)
	 * @throws PresenzeException
	 */
	public void inserisciInPosizione(Presenza R,int posizione) throws PresenzeException 
	{
		if (posizione<=1)
		{
			inserisciInTesta(R);
			return;
		}
		if (posizione>elementi)
		{
			inserisciInCoda(R);
			return;
		}
		
		Nodo pn=creaNodo(R, getLinkPosizione(posizione));
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(pn);
		elementi++;
	}
	/**
	 * Elimina il primo oggetto della lista
	 * @throws PresenzeException
	 */
	public void eliminaInTesta() throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		head=head.getLink();
		elementi--;
	}
	/**
	 * Elimina l'ultimo oggetto della lista
	 * @throws PresenzeException
	 */
	public void eliminaInCoda() throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		if (elementi==1)
		{
			eliminaInTesta();
			return;
		}
		
		Nodo p=getLinkPosizione(elementi-1);
		p.setLink(null);
		elementi--;
	}
	/**
	 * Elimina l'oggetto nella posizione desiderata
	 * @param posizione (int)
	 * @throws PresenzeException
	 */
	public void eliminaInPosizione(int posizione) throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new PresenzeException ("Posizione non valida");
	
		if (posizione==1)
		{
			eliminaInTesta();
			return;
		}
		if (posizione==elementi)
		{
			eliminaInCoda();
			return;
		}
		
		Nodo p;
		p=getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(p.getLink());
		elementi--;
		
	}
	/**
	 * Visualizza come stringa l'oggetto nella posizione desiderata
	 * @param posizione (int)
	 * @return Stringa
	 * @throws PresenzeException
	 */
	public String visita (int posizione) throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new PresenzeException ("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo().toString();		
	}
	/**
	 * Ottieni l'oggetto nella posizione desiderata
	 * @param posizione (inte)
	 * @return Presenza 
	 * @throws PresenzeException
	 */
	public Presenza getPresenza (int posizione) throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new PresenzeException ("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();		
	}
	
	
	
	
	
	
}