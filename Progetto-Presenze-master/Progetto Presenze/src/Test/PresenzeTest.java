
package Test;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import org.junit.Test;

import Principale.Presenza;
import Principale.Presenze;


public class PresenzeTest {
	LocalDateTime now = LocalDateTime.now();
	Presenza P0 = new Presenza(0, 123, now);
	Presenza P1 = new Presenza(1, 1234, now.plusMinutes(10));
	Presenza P2 = new Presenza(2, 12345, now.plusMinutes(20));
	
	@Test
	public void testPresenze() throws Exception 
	{
		Presenze Lista = new Presenze();
		assertEquals(Lista.getElementi(), 0); //ha settato correttamente gli elementi quindi anche l'head
	}

	@Test
	public void testGetElementi() throws Exception 
	{
		Presenze Lista = new Presenze();
		assertEquals(Lista.getElementi(), 0);
		Lista.inserisciInTesta(P0);
		assertEquals(Lista.getElementi(), 1);
	}

	@Test
	public void testInserisciInTesta() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);
		assertEquals(Lista.getPresenza(1), P0);
	}

	@Test
	public void testInserisciInCoda() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);	//pongo questo oggetto in testa
		Lista.inserisciInTesta(P1);	//ora pongo questo in testa quindi P0 slitta di una posizione, slitta in coda
		assertEquals(Lista.getPresenza(2), P0);  //e qui lo verifico
		Lista.inserisciInCoda(P2);  //ora metto P2 in coda
		assertEquals(Lista.getPresenza(3), P2); //e qui verifico
	}

	@Test
	public void testInserisciInPosizione() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);
		Lista.inserisciInTesta(P1);
		Lista.inserisciInTesta(P2); //a questo punto la lista è composta da 1)P2 2)P1 3)P0
		Lista.inserisciInPosizione(P0, 2); //ora se ha funzionato dovrebbe essere 1)P2 2)P0 3) P1 4) P0
		assertEquals(Lista.getPresenza(1), P2);
		assertEquals(Lista.getPresenza(2), P0);
		assertEquals(Lista.getPresenza(3), P1);
		assertEquals(Lista.getPresenza(4), P0);	//accertato e corretto
	}

	@Test
	public void testEliminaInTesta() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);
		Lista.inserisciInTesta(P1);
		Lista.inserisciInTesta(P2); //a questo punto la lista è composta da 1)P2 2)P1 3)P0
		Lista.eliminaInTesta(); //Ora sarà 1)P1 2)P0
		assertEquals(Lista.getPresenza(1), P1); //ecco qui la prova
	}

	@Test
	public void testEliminaInCoda() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);
		Lista.inserisciInTesta(P1);
		Lista.inserisciInTesta(P2); //a questo punto la lista è composta da 1)P2 2)P1 3)P0
		Lista.eliminaInCoda(); //Ora sarà 1)P2 2)P1
		assertEquals(Lista.getPresenza(1), P2);
		assertEquals(Lista.getPresenza(2), P1);
	}

	@Test
	public void testEliminaInPosizione() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);
		Lista.inserisciInTesta(P1);
		Lista.inserisciInTesta(P2); //a questo punto la lista è composta da 1)P2 2)P1 3)P0
		Lista.eliminaInPosizione(2); //ora elimino P1 e diventa 1)P2 2)P0
		assertEquals(Lista.getPresenza(1), P2); //ecco la prova
		assertEquals(Lista.getPresenza(2), P0);
		Lista.eliminaInPosizione(2); //ora rimane solo P2
		assertEquals(Lista.getPresenza(1), P2);
		
	}

	@Test
	public void testGetPresenza() throws Exception 
	{
		Presenze Lista = new Presenze();
		Lista.inserisciInTesta(P0);
		Lista.inserisciInTesta(P1);
		Lista.inserisciInCoda(P2);
		assertEquals(Lista.getPresenza(1), P1);
		assertEquals(Lista.getPresenza(2), P0);
		assertEquals(Lista.getPresenza(3), P2);
		
	}

}
