package Test;
import Principale.*;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;


public class PresenzaTest {

	@Test
	public void testPresenzaIntIntLocalDateTime() throws Exception 
	{
		int codiceidentificativoPresenza = 22;
		int numeroMatricola = 123;
		LocalDateTime oraIngresso= LocalDateTime.now();
		Presenza P0 = new Presenza(22, 123, oraIngresso);
		Presenza P1 = new Presenza();
		P1.setcodiceidentificativoPresenza(codiceidentificativoPresenza);
		P1.setNumeroMatricola(numeroMatricola);
		P1.setOraIngresso(oraIngresso);
		assertEquals(P0.getcodiceidentificativoPresenza(), P1.getcodiceidentificativoPresenza());
		assertEquals(P0.getNumeroMatricola(), P1.getNumeroMatricola());
		assertEquals(P0.getOraIngresso(), P1.getOraIngresso());
		assertEquals(P0.getOraUscita(), P1.getOraUscita());
	}

	@Test
	public void testPresenza() throws Exception 
	{
		Presenza P0 = new Presenza();
		assertEquals(P0.getcodiceidentificativoPresenza(), 0);
		assertEquals(P0.getNumeroMatricola(), 0);
		assertEquals(P0.getOraIngresso(), null);
		assertEquals(P0.getOraUscita(), null);
	}

	@Test
	public void testGetcodiceidentificativoPresenza() throws Exception 
	{
		Presenza P0 = new Presenza(15, 16, LocalDateTime.now());
		assertEquals(P0.getcodiceidentificativoPresenza(), 15);
	}

	@Test
	public void testGetNumeroMatricola() throws Exception 
	{
		Presenza P0 = new Presenza(10, 11, LocalDateTime.now());
		assertEquals(P0.getNumeroMatricola(), 11);
	}

	@Test
	public void testGetOraIngresso() throws Exception 
	{
		LocalDateTime now = LocalDateTime.now();
		Presenza P0 = new Presenza(9, 7, now);
		assertEquals(P0.getOraIngresso(), now);
	}

	@Test
	public void testGetOraUscita() throws Exception 
	{
		LocalDateTime now = LocalDateTime.now();
		Presenza P0 = new Presenza(9, 7, now);
		LocalDateTime now2 = now.plusMinutes(5);
		P0.setOraUscita(now2);
		assertEquals(P0.getOraUscita(), now.plusMinutes(5));
	}

}
