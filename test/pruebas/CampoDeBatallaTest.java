package pruebas;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import modelo.CampoDeBatalla;
import modelo.MazoAlGoOh;
import modelo.cartas.CartaAlGoOh;
import modelo.cartas.cartasMagia.AgujeroNegro;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.cartas.cartasTrampa.CilindroMagico;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.SacrificioInvalidoException;


public class CampoDeBatallaTest {


	@Test
	public void testCampoDeBatallaPermiteInvocarUnaCartaMonstruoEnModoAtaque() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaMonstruo carta = new CartaMonstruoGenerica("a", 0,0,1);

		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaMonstruoEnPosicionDeAtaque(carta, new MazoAlGoOh<CartaMonstruo>(0));
		}

		catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException e) {
			//TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		}

		assertTrue(campo.cartaMonstruoEstaEnPosicionDeAtaque(carta));
	}


	@Test
	public void testCampoDeBatallaPermiteInvocarUnaCartaMonstruoEnModoDefensa() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaMonstruo carta = new CartaMonstruoGenerica("a", 0,0,1);
		
		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaMonstruoEnPosicionDeDefensa(carta, new MazoAlGoOh<CartaMonstruo>(0));
		} 
		
		catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(campo.cartaMonstruoEstaEnPosicionDeDefensa(carta));
	}
	
	
	@Test
	public void testCampoDeBatallaPermiteInvocarUnaCartaMagiaBocaAbajo() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaAlGoOh carta = new AgujeroNegro();
		
		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaMagiaBocaAbajo(carta);
		} 
		
		catch (CartaAlGoOhInexistenteException | MazoLlenoException | MazoVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

        assertTrue(campo.cartaEstaInvocadaBocaAbajo(carta));
	}

	
	@Test
	public void testCampoDeBatallaPermiteInvocarUnaCartaTrampaBocaAbajo() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaTrampa carta = new CilindroMagico();
		
		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaTrampaBocaAbajo(carta);
		} 
		
		catch (CartaAlGoOhInexistenteException | MazoLlenoException | MazoVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

        assertTrue(campo.cartaEstaInvocadaBocaAbajo(carta));
	}
	
	
	@Test
	public void testCampoDeBatallaPermiteEnviarUnaCartaMonstruoAlCementerio() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaMonstruo carta = new CartaMonstruoGenerica("a", 0,0,1);
		
		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaMonstruoEnPosicionDeAtaque(carta, new MazoAlGoOh<CartaMonstruo>(0));
			campo.enviarCartaAlCementerio(carta);
		} 
		
		catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		assertTrue(campo.cartaEstaEnElCementerio(carta));
	}

    @Test
    public void testCampoDeBatallaPermiteInvocarUnaCartaMonstruoEnModoAtaqueBocaAbajo() {
        CampoDeBatalla campo = new CampoDeBatalla();
        CartaMonstruo carta = new CartaMonstruoGenerica("a", 0,0,1);

        try {
            campo.agregarCartaAlMazo(carta);
            campo.ponerCartaEnLaMano();
            campo.invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(carta, new MazoAlGoOh<CartaMonstruo>(0));
        }

        catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException e) {
            //TODO Auto-generated catch block
            fail();
            e.printStackTrace();
        }

        assertTrue(campo.cartaMonstruoEstaEnPosicionDeAtaque(carta));
    }
	
}