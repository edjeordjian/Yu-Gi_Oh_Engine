package pruebas;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

import modelo.MazoAlGoOh;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;


public class MazoAlGoOhTest {

	@Test
	public void testAgregarMasCartasAlMazoDelLimiteEstablecidoArrojaMazoLlenoException(){
		MazoAlGoOh<CartaMonstruo> mazo = new MazoAlGoOh<CartaMonstruo>(1);

		try {
			mazo.agregarCarta(new CartaMonstruoGenerica("a",0,0,1));
			mazo.agregarCarta(new CartaMonstruoGenerica("a",0,0,1));
		}
		
		catch (MazoLlenoException e) {
			assertTrue(true);
		}
		
	}
	
	
	@Test
	public void testIntentarObtenerLaCartaTopeDeUnMazoVacioArrojaMazoVacioException() {
		MazoAlGoOh<CartaMonstruo> mazo = new MazoAlGoOh<CartaMonstruo>(0);
		
		try { 
			mazo.obtenerCartaTope();
		}
		
		catch (MazoVacioException e) {
			assertTrue(true);
		}
		
	}
	
	
	@Test
	public void testQuitarUnaCartaInexistenteDelMazoArrojaCartaAlGoOhInexistenteException() {
		MazoAlGoOh<CartaMonstruo> mazo = new MazoAlGoOh<CartaMonstruo>(1);
		
		try { 
			mazo.removerCarta(new CartaMonstruoGenerica("a",0,0,1));
		}
		
		catch (CartaAlGoOhInexistenteException e) {
			assertTrue(true);
		}
		
	}
	

}
