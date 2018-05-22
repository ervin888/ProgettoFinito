package Principale;
/**
 * Insieme a Presenze forma la Lista
 * @author Ervin
 *
 */
public class Nodo 
{
	private Presenza info;
	private Nodo link;
	/**
	 * Crea un nodo
	 * @param persona (Presenza)
	 */
	public Nodo(Presenza persona)
	{
		setInfo(persona);
		link=null;
	}
/**
 * Si ottiene l'oggetto legato al nodo
 * @return info (Presenza)
 */
	public Presenza getInfo() 
	{
		return info;
	}
/**
 * si lega l'oggetto al nodo
 * @param info
 */
	public void setInfo(Presenza info) 
	{
		this.info = info;
	}
/**
 * Si ottiene il link al nodo successivo
 * @return link (nodo)
 */
	public Nodo getLink() 
	{
		return link;
	}
/**
 * Si imposta il link al prossimo nodo
 * @param link
 */
	public void setLink(Nodo link) 
	{
		this.link = link;
	}
	
	
}