package Principale;

//ricordiamo che la posizione 0 non esiste e che verrà sempre messo in posizione 1 
public class Presenze 
{

	private Nodo head;
	private int elementi;
	
	public Presenze()
	{
		head=null;
		elementi=0;
	}
	
	public int getElementi()
	{
		return elementi;
	}

	public void setElementi(int elementi)
	{
		this.elementi = elementi;
	}
	
	private Nodo creaNodo(Presenza R, Nodo link)
	{
		Nodo nodo= new Nodo(R);
		nodo.setLink(link);
		return nodo;
	}
	
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
	
	public void inserisciInTesta (Presenza R)
	{
		
		Nodo p=creaNodo(R, head);
		head=p;
		elementi++;
	}
	
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
	
	public void eliminaInTesta() throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		head=head.getLink();
		elementi--;
	}
	
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
	
	public String visita (int posizione) throws PresenzeException 
	{
		if (elementi==0)
			throw new PresenzeException ("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new PresenzeException ("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo().toString();		
	}
	
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