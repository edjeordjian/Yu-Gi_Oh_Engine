package pruebas;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import modelo.MazoAlGoOh;
import modelo.PartidaAlGoOh;
import modelo.Puntos;
import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasCampo.Sogen;
import modelo.cartas.cartasCampo.Wasteland;
import modelo.cartas.cartasMagia.AgujeroNegro;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMagia.Fisura;
import modelo.cartas.cartasMagia.OllaDeLaCodicia;
import modelo.cartas.cartasMonstruo.BrazoDerechoExodia;
import modelo.cartas.cartasMonstruo.BrazoIzquierdoExodia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
import modelo.cartas.cartasMonstruo.CuerpoExodia;
import modelo.cartas.cartasMonstruo.DragonBlancoDeOjosAzules;
import modelo.cartas.cartasMonstruo.DragonBlancoDefinitivoDeOjosAzules;
import modelo.cartas.cartasMonstruo.InsectoComeHombres;
import modelo.cartas.cartasMonstruo.Jinzo7;
import modelo.cartas.cartasMonstruo.PiernaDerechaExodia;
import modelo.cartas.cartasMonstruo.PiernaIzquierdaExodia;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.cartas.cartasTrampa.CilindroMagico;
import modelo.cartas.cartasTrampa.Refuerzos;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;


public class PartidaAlGoOhTest {

    private void agregarCartaInicialALosMazos(PartidaAlGoOh partida) throws MazoLlenoException {
        // para que no pierda
        Carta carta = new CartaMonstruoGenerica("", 0, 0, 1);
        partida.agregarCartaAlMazoDeJugadorActivo(carta);
        partida.terminarTurno();
        partida.agregarCartaAlMazoDeJugadorActivo(carta);
        partida.terminarTurno();
    }

	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConMayorAtaqueLaCartaConMenorAtaqueTerminaEnElCementero() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
		    agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta1, carta2);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(carta1.estaEnElCementerio());
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConMayorAtaqueSuOponenteVeSusPuntosDeVidaDisminuidos() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta1, carta2);
			
		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 90)));
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConMenorAtaqueLaCartaConMenoAtaqueTerminaEnElCementero() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(carta2.estaEnElCementerio());
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConMenorAtaqueElVeSusPuntosDeVidaDisminuidos() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 90)));
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConIgualAtaqueSuCartaTerminaEnElCementerio() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(carta2.estaEnElCementerio());
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConIgualAtaqueLaOtraCartaTerminaEnElCementerio() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(carta1.estaEnElCementerio());
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConIgualAtaqueSusPuntosDeVidaNoSeModifican() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
	}


	@Test
	public void testCuandoUnJugadorAtacaUnaCartaMonstruoEnPosicionAtaqueConOtraCartaMonstruoConIgualAtaqueLodPuntosDeVidaDelOtroNoSeModifican() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
	}


	@Test
	public void testCuandoUnJugadorConUnaCartaMonstruoEnPosicionDeAtaqueAtacaOtraCartaMonstruoEnPosicionDeDefensaConMenorDefensaEstaUltimaSeDestruye() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 1, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		assertTrue(carta1.estaEnElCementerio());
	}


	@Test
	public void testCuandoUnJugadorConUnaCartaMonstruoEnPosicionDeAtaqueAtacaOtraCartaMonstruoEnPosicionDeDefensaConMenorDefensaEstaLosPuntosDeVidaDelJugadorAtacanteNoSeModifican() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
	}


	@Test
	public void testCuandoUnJugadorConUnaCartaMonstruoEnPosicionDeAtaqueAtacaOtraCartaMonstruoEnPosicionDeDefensaConMenorDefensaLosPuntosDeVidaDelJugadorAtacadoNoSeModifican() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
	}


	@Test
	public void testCuandoUnJugadorConUnaCartaMonstruoEnPosicionDeAtaqueAtacaOtraCartaMonstruoEnPosicionDeDefensaConMayorDefensaLaPrimeraNoSeDestruye() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 1, 1);

		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertFalse(carta2.estaEnElCementerio());
	}


	@Test
	public void testCuandoUnJugadorConUnaCartaMonstruoEnPosicionDeAtaqueAtacaOtraCartaMonstruoEnPosicionDeDefensaConMayorDefensaEstaLosPuntosDeVidaDelJugadorAtacanteNoSeModifican() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 500, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
	}


	@Test
	public void testCuandoUnJugadorConUnaCartaMonstruoEnPosicionDeAtaqueAtacaOtraCartaMonstruoEnPosicionDeDefensaConMayorDefensaLosPuntosDeVidaDelJugadorAtacadoNoSeModifican() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}

		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
	}

	
	@Test
	public void testAgujeroNegroDestruyeTodosLosMonstruosDelJugadorActivo() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",200, 200, 1);
		CartaMonstruo carta4 = new CartaMonstruoGenerica("a",1234, 0, 1);
		CartaMagia agujeroNegro = new AgujeroNegro();
		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(agujeroNegro);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta4);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta4, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMagiaBocaArribaDeJugadorActivo(agujeroNegro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(0) );
	}

	
	@Test
	public void testAgujeroNegroDestruyeTodosLosMonstruosDelJugadorPasivo() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",200, 200, 1);
		CartaMonstruo carta4 = new CartaMonstruoGenerica("a",1234, 0, 1);
		CartaMagia agujeroNegro = new AgujeroNegro();
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(agujeroNegro);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(carta4);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta4, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMagiaBocaArribaDeJugadorActivo(agujeroNegro);
            partida.terminarTurno();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}
		
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorPasivo(0) );
	}

	
	@Test
	public void testAgujeroNegroNoQuitaPuntosAlJugadorAtacante() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",200, 200, 1);
		CartaMonstruo carta4 = new CartaMonstruoGenerica("a",1234, 0, 1);
		CartaMagia agujeroNegro = new AgujeroNegro();
		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(agujeroNegro);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta4);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta4, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMagiaBocaArribaDeJugadorActivo(agujeroNegro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            fail();
		}
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
	}

	
	@Test
	public void testAgujeroNegroNoQuitaPuntosAlJugadorPasivo() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",200, 200, 1);
		CartaMonstruo carta4 = new CartaMonstruoGenerica("a",1234, 0, 1);
		CartaMagia agujeroNegro = new AgujeroNegro();
		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(agujeroNegro);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta4);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta4, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMagiaBocaArribaDeJugadorActivo(agujeroNegro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
	}


	@Test
	public void testInvocarUnMonstruoDe5Estrellas() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",100, 10, 5);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(1);

		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            cartasASacrificar.agregarCarta(carta1);
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, cartasASacrificar);


		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(2) );
	}

	
	@Test
	public void testInvocarUnMonstruoDe5EstrellasRequiereUnSacrificio() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 5);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(1);
        try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			cartasASacrificar.agregarCarta(carta1);
			partida.terminarTurno();
			partida.terminarTurno();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta2, cartasASacrificar);


		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(carta1.estaEnElCementerio());
	}

	
	@Test
	public void testUnJugadorPuedeUtilizarUnaCartaDe5EstrellasCorrectamenteInvocadaQueSePuedeUtilizar() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",100, 10, 5);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(1);
        try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
            partida.cambiarDeFase();
			cartasASacrificar.agregarCarta(carta1);
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta3, cartasASacrificar);
			partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta3);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
		}
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 90)));
	}


	@Test
	public void testInvocarUnMonstruoDe6Estrellas() throws SacrificioInvalidoException {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",100, 10, 6);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(1);
		try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
			cartasASacrificar.agregarCarta(carta1);
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, cartasASacrificar);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(2));
	}

	
	@Test
	public void testInvocarUnMonstruoDe6EstrellasRequiereUnSacrificio() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 6);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(1);
        try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			cartasASacrificar.agregarCarta(carta1);
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta2, cartasASacrificar);


		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(carta1.estaEnElCementerio());
	}

	
	@Test
	public void testUnJugadorPuedeUtilizarUnaCartaDe6EstrellasCorrectamenteInvocadaQueSePuedeUtilizar() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",100, 10, 6);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(1);
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			cartasASacrificar.agregarCarta(carta1);
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta3, cartasASacrificar);
			partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta3);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 90)));
	}

	
	@Test
	public void testInvocarUnMonstruoDe7Estrellas() throws SacrificioInvalidoException {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",1000, 10, 7);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(2);
		
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			cartasASacrificar.agregarCarta(carta1);
			cartasASacrificar.agregarCarta(carta2);
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, cartasASacrificar);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
	}



	@Test
	public void testInvocarUnMonstruoDe7EstrellasRequiereDestruyeOtraCartaPorSacrificio() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 100, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 100, 2);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",1000, 10, 7);
		MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(2);
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			cartasASacrificar.agregarCarta(carta1);
			cartasASacrificar.agregarCarta(carta2);
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, cartasASacrificar);


		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(carta2.estaEnElCementerio());
	}


	@Test
	public void testInvocarUnMonstruoDe7EstrellasRequiereDestruyeUnaCartaPorSacrificio() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 100, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 100, 2);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",1000, 10, 7);
		MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(2);
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			cartasASacrificar.agregarCarta(carta1);
			cartasASacrificar.agregarCarta(carta2);
			partida.terminarTurno();
			partida.terminarTurno();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, cartasASacrificar);


		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(carta1.estaEnElCementerio());
	}

	@Test
	public void testUnJugadorPuedeUtilizarUnaCartaDe7EstrellasCorrectamenteInvocada() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",1, 10, 1);
		CartaMonstruo carta4 = new CartaMonstruoGenerica("a",1000, 10, 7);
        MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<>(2);
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.agregarCartaAlMazoDeJugadorActivo(carta4);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
			cartasASacrificar.agregarCarta(carta1);
			cartasASacrificar.agregarCarta(carta3);
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta4, cartasASacrificar);
			partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta4, carta2);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000 - 990)));
	}

	
	@Test
    public void testOllaDeLaCodiciaAgregaDosCartasALaMano() {
	    PartidaAlGoOh partida = new PartidaAlGoOh("J1", "J2");
        CartaMagia ollaDeLaCodicia = new OllaDeLaCodicia();
        CartaMonstruo carta1 = new CartaMonstruoGenerica("a",0,0, 0);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("a",0,0, 0);
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.agregarCartaAlMazoDeJugadorActivo(ollaDeLaCodicia);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMagiaBocaArribaDeJugadorActivo(ollaDeLaCodicia);

        } catch (MazoLlenoException | CartaAlGoOhInexistenteException | MazoVacioException | FuncionNoAccesibleException e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(partida.tieneLaCantidadDeCartasEnLaManoElJugadorActivo(2));
    }

	
    @Test
    public void testFisuraEliminaMonstruoOponenteConMenorAtaque() {
	    PartidaAlGoOh partida = new PartidaAlGoOh("J1", "J2");
	    CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 0, 1);
	    CartaMonstruo carta2 = new CartaMonstruoGenerica("a",1000, 0, 1);
	    CartaMagia fisura = new Fisura();


        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(fisura);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMagiaBocaArribaDeJugadorActivo(fisura);
            partida.terminarTurno();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
    }

    
	@Test
	public void testUnMonstruoDeCadaLadoDelCampoActivarCartaMagicaWastelandElAtaqueDelMonstruoPropioAumenta200(){
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 100, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 100, 1);
		CartaCampo wasteland = new Wasteland();
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(wasteland);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.invocarCartaCampoDeJugadorActivoBocaArriba(wasteland);
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		// el atacante aumento en 200 sus puntos de ataque por lo que quita puntos de vida al oponente
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(7800)));
	}

	
    @Test
    public void testUnMonstruoDeCadaLadoDelCampoActivarCartaMagicaWastelandLaDefensaDelMonstruoOponenteAumenta300(){
        PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
        CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 100, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 100, 1);
        CartaCampo wasteland = new Wasteland();
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(wasteland);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaCampoDeJugadorActivoBocaArriba(wasteland);
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
            partida.terminarTurno();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        // El monstruo atacado no se destruyo ya que igualo los puntos del atacante
        assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
    }

    
    @Test
    public void testUnMonstruoDeCadaLadoDelCampoActivarCartaMagicaSogenElAtaqueDelMonstruoOponenteAumenta200(){
        PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
        CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 100, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 100, 1);
        CartaCampo sogen = new Sogen();
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.terminarTurno();
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(sogen);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.invocarCartaCampoDeJugadorActivoBocaArriba(sogen);
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        // el atacante aumento en 200 sus puntos de ataque por lo que quita puntos de vida al oponente
        assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(7800)));
    }

    
    @Test
    public void testUnMonstruoDeCadaLadoDelCampoActivarCartaMagicaSogenLaDefensaDelMonstruoPropioAumenta500(){
        PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
        CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 100, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 100, 1);
        CartaCampo sogen = new Sogen();
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(sogen);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.invocarCartaCampoDeJugadorActivoBocaArriba(sogen);
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
            partida.terminarTurno();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        // El monstruo atacado no se destruyo ya que supero los puntos del atacante
        assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
    }

    @Test
    public void testJinzo7PuedeAtacarALosPuntosDeVidaDirectamente() {
		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 100, 1);
		CartaMonstruo jinzo7 = new Jinzo7(); //ataque 500 def 400
		try {
            agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.agregarCartaAlMazoDeJugadorActivo(jinzo7);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(jinzo7, new MazoAlGoOh<CartaMonstruo>(0));
			partida.cambiarDeFase();
			partida.atacarJugadorPasivoALosPuntosDeVidaCon(jinzo7);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		// El monstruo atacado no se destruyo ya que supero los puntos del atacante
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(7500)));
	}

    
    @Test
    public void testInvocacionDragonDefinitivoDeOjosAzulesSacrificandoTresDragonesDeOjosAzules() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1", "2");
    	CartaMonstruo dragon1 = new DragonBlancoDeOjosAzules();
    	CartaMonstruo dragon2 = new DragonBlancoDeOjosAzules();
    	CartaMonstruo dragon3 = new DragonBlancoDeOjosAzules();
    	CartaMonstruo dragond = new DragonBlancoDefinitivoDeOjosAzules();
    	CartaMonstruo cartaAux = new CartaMonstruoGenerica("a",1,1,1);
    	MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(2);
    	try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(dragond);
            partida.agregarCartaAlMazoDeJugadorActivo(dragon1);
            partida.agregarCartaAlMazoDeJugadorActivo(dragon2);
            partida.agregarCartaAlMazoDeJugadorActivo(dragon3);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            cartasASacrificar.agregarCarta(cartaAux);
            cartasASacrificar.agregarCarta(cartaAux);
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon1, cartasASacrificar);
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(2);
            cartasASacrificar.agregarCarta(cartaAux);
            cartasASacrificar.agregarCarta(cartaAux);
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon2, cartasASacrificar);
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(2);
            cartasASacrificar.agregarCarta(cartaAux);
            cartasASacrificar.agregarCarta(cartaAux);
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon3, cartasASacrificar);
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(3);
            cartasASacrificar.agregarCarta(dragon1);
            cartasASacrificar.agregarCarta(dragon2);
            cartasASacrificar.agregarCarta(dragon3);
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragond, cartasASacrificar);
    	} catch (Exception e) {
            e.printStackTrace();
            fail();
    	}
    	
    	assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
    }

    
    @Test
    public void testNoSePuedeInvocarDragonDefinitivoDeOjosAzulesSacrificandoDosDragonesDeOjosAzules() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1", "2");
    	CartaMonstruo dragon1 = new DragonBlancoDeOjosAzules();
    	CartaMonstruo dragon2 = new DragonBlancoDeOjosAzules();
    	CartaMonstruo dragond = new DragonBlancoDefinitivoDeOjosAzules();
    	CartaMonstruo cartaAux = new CartaMonstruoGenerica("a",1,1,1);
    	MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(2);
    	try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(dragond);
            partida.agregarCartaAlMazoDeJugadorActivo(dragon1);
            partida.agregarCartaAlMazoDeJugadorActivo(dragon2);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.agregarCartaAlMazoDeJugadorActivo(cartaAux);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            cartasASacrificar.agregarCarta(cartaAux);
            cartasASacrificar.agregarCarta(cartaAux);
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon1, cartasASacrificar);
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(cartaAux, new MazoAlGoOh<CartaMonstruo>(0));
            cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(2);
            cartasASacrificar.agregarCarta(cartaAux);
            cartasASacrificar.agregarCarta(cartaAux);
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon2, cartasASacrificar);
            cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(3);
            cartasASacrificar.agregarCarta(dragon1);
            cartasASacrificar.agregarCarta(dragon2);
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragond, cartasASacrificar);
            fail();
    	} catch (SacrificioInvalidoException e) {
        	assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(2));
    	} 
    	
    	catch (MonstruoYaInvocadoException | FuncionNoAccesibleException | MazoLlenoException | CartaAlGoOhInexistenteException | MazoVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

    }

    
    @Test
    public void testInsectoComeHombresDestruyeAlMonstruoQueLoAtacaSiEstaBocaAbajoYEnModoDefensa() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",1000,1000,1);
    	CartaMonstruo insecto = new InsectoComeHombres();
    	
    	try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(insecto);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaBocaAbajoDeJugadorActivo(insecto, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta, insecto);
    	} catch (Exception e) {
    	    e.printStackTrace();
            fail();

    	}
    	
    	assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(0) );
    }

    
    @Test
    public void testInsectoComeHombresNoDestruyeAlMonstruoQueLoAtacaSiNoEstaBocaAbajo() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",1000,1000,1);
    	CartaMonstruo insecto = new InsectoComeHombres();
    	
    	try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(insecto);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(insecto, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta, insecto);
    	} catch (Exception e) {
    		e.printStackTrace();
    		fail();
    	}
    	
    	assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1) );
    }

    
    @Test
    public void testInsectoComeHombresSigueEnElCampoLuegoDeSerAtacadoEstandoBocaAbajoYEnModoDefensa() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",1000,1000,1);
    	CartaMonstruo insecto = new InsectoComeHombres();
    	
    	try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(insecto);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaBocaAbajoDeJugadorActivo(insecto, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta, insecto);
            partida.terminarTurno();
    	} catch (Exception e) {
    		e.printStackTrace();
    		fail();
    	}
    	
    	assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1) );
    }
    

    @Test
    public void testLuegoDeQueInsectoComeHombresEsAtacadoElJugadorAtacanteNoPierdePuntosDeVida() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",1000,1000,1);
    	CartaMonstruo insecto = new InsectoComeHombres();
    	
    	try	{
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(insecto);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaBocaAbajoDeJugadorActivo(insecto, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta, insecto);
    	} catch (Exception e) {
    	    e.printStackTrace();
            fail();

    	}
    	
    	assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)) );
    }
    
    
    @Test
    public void testLuegoDeQueInsectoComeHombresEsAtacadoElJugadorAtacadoNoPierdePuntosDeVida() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",1000,1000,1);
    	CartaMonstruo insecto = new InsectoComeHombres();
    	
    	try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(insecto);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaBocaAbajoDeJugadorActivo(insecto, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta, insecto);
            partida.terminarTurno();
    	} catch (Exception e) {
    		e.printStackTrace();
            fail();
    	}
    	
    	assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)) );
    }

    
    @Test
    public void testRefuerzosSeActivaAnteUnAtaque() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",100,100,1);
    	CartaMonstruo otraCarta = new CartaMonstruoGenerica("a",500,100,1);
    	CartaTrampa refuerzos = new Refuerzos();
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(carta);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.agregarCartaAlMazoDeJugadorActivo(refuerzos);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.invocarCartaTrampaBocaAbajo(refuerzos);
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(otraCarta);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(otraCarta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
    		partida.atacarCartaMonstruoConCartaMonstruo(otraCarta, carta);
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}
    	
    	assertTrue(otraCarta.estaEnElCementerio());
    }
        

    @Test
    public void testRefuerzosSeActivaAnteUnAtaque2() {
    	
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",100,100,1);
    	CartaMonstruo otraCarta = new CartaMonstruoGenerica("a",500,100,1);
    	CartaTrampa refuerzos = new Refuerzos();
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(carta);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.terminarTurno();
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(refuerzos);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaTrampaBocaAbajo(refuerzos);
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(otraCarta);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(otraCarta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
    		partida.atacarCartaMonstruoConCartaMonstruo(otraCarta, carta);
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}
    	
    	assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 100)) );
    }
    

    @Test
    public void testRefuerzosSeActivaAnteUnAtaque3() {
    	
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo carta = new CartaMonstruoGenerica("a",100,100,1);
    	CartaMonstruo otraCarta = new CartaMonstruoGenerica("a",500,100,1);
    	CartaTrampa refuerzos = new Refuerzos();
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(carta);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.agregarCartaAlMazoDeJugadorActivo(refuerzos);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.invocarCartaTrampaBocaAbajo(refuerzos);
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(otraCarta);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(otraCarta, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.cambiarDeFase();
    		partida.atacarCartaMonstruoConCartaMonstruo(otraCarta, carta);
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}
    	
    	assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)) );
    }
    
    @Test
    public void testCilindroMagicoSeActivaAnteUnAtaque() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaTrampa cilindro = new CilindroMagico();
    	CartaMonstruo monstruo = new CartaMonstruoGenerica("a",1000, 1000, 0);
    	try{
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(cilindro);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaTrampaBocaAbajo(cilindro);
    		partida.terminarTurno();
    		partida.cambiarDeFase();
    		partida.cambiarDeFase();
    		partida.atacarJugadorPasivoALosPuntosDeVidaCon(monstruo);
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}	
    	assertFalse(monstruo.estaEnElCementerio());
    }
    
    @Test
    public void testCilindroMagicoSeActivaAnteUnAtaque2() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaTrampa cilindro = new CilindroMagico();
    	CartaMonstruo monstruo = new CartaMonstruoGenerica("a",1000, 1000, 0);
    	try{
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(cilindro);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
    		partida.invocarCartaTrampaBocaAbajo(cilindro);
    		partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
    		partida.atacarJugadorPasivoALosPuntosDeVidaCon(monstruo);
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}	
    	assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(7000)));
    }

    @Test
    public void testCilindroMagicoSeActivaAnteUnAtaque3() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaTrampa cilindro = new CilindroMagico();
    	CartaMonstruo monstruo = new CartaMonstruoGenerica("a",1000, 1000, 0);
    	try{
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo, new MazoAlGoOh<CartaMonstruo>(0));
    		partida.terminarTurno();
    		partida.agregarCartaAlMazoDeJugadorActivo(cilindro);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.cambiarDeFase();
    		partida.invocarCartaTrampaBocaAbajo(cilindro);
    		partida.terminarTurno();
    		partida.cambiarDeFase();
    		partida.cambiarDeFase();
    		partida.atacarJugadorPasivoALosPuntosDeVidaCon(monstruo);
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}	
    	assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
    }
    
    @Test
    public void testAlVaciarseElMazoDelJugadorActivoEstePierdeElJuego() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo monstruo = new CartaMonstruoGenerica("a",10,10,1);
    	
    	try{
    		partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}
    
    	assertTrue(partida.jugadorActivoPerdioLaPartida());
    	
    }

    @Test
    public void testAlVaciarseElMazoDelJugadorActivoElOtroGanaElJuego() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	CartaMonstruo monstruo = new CartaMonstruoGenerica("a",10,10,1);
    	
    	try{
    		partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    	}
    	
    	catch(Exception e) {
            e.printStackTrace();
            fail();
    	}
    
    	assertTrue(partida.jugadorPasivoGanoLaPartida());
    	
    }

    
    @Test
    public void testElJugadorActivoNoPerdioLaParidaAlIniciarElJuego() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
    	assertFalse(partida.jugadorActivoPerdioLaPartida());
    	
    }

    
    @Test
    public void testAlTenerLasCincoPartesDeExodiaElJugadorActivoGana() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo parte1 = new BrazoDerechoExodia();
    	CartaMonstruo parte2 = new BrazoIzquierdoExodia();
    	CartaMonstruo parte3 = new PiernaDerechaExodia();
    	CartaMonstruo parte4 = new PiernaIzquierdaExodia();
    	CartaMonstruo parte5 = new CuerpoExodia();
    	
    	
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte1);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte2);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte3);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte4);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte5);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    	}
    	
    	catch (Exception e){
    		
    	}
    	
    	assertTrue(partida.jugadorActivoGanoLaPartida());
    }

    
    @Test
    public void testAlTenerLasCincoPartesDeExodiaElJugadorPasivoPierde() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo parte1 = new BrazoDerechoExodia();
    	CartaMonstruo parte2 = new BrazoIzquierdoExodia();
    	CartaMonstruo parte3 = new PiernaDerechaExodia();
    	CartaMonstruo parte4 = new PiernaIzquierdaExodia();
    	CartaMonstruo parte5 = new CuerpoExodia();
    	
    	
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte1);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte2);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte3);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte4);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte5);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    	}
    	
    	catch (Exception e){
            e.printStackTrace();
            fail();
    	}
    	
    	assertTrue(partida.jugadorPasivoPerdioLaPartida());
    }

    
    @Test
    public void testAlTenerCuatroPartesDeExodiaElJugadorActivoNoGana() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo parte1 = new BrazoDerechoExodia();
    	CartaMonstruo parte2 = new BrazoIzquierdoExodia();
    	CartaMonstruo parte3 = new PiernaDerechaExodia();
    	CartaMonstruo parte4 = new PiernaIzquierdaExodia();
    	CartaMonstruo parte5 = new CuerpoExodia();
    	
    	
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte1);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte2);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte3);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte4);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte5);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    	}
    	
    	catch (Exception e){
            e.printStackTrace();
            fail();
    	}
    	
    	assertFalse(partida.jugadorActivoGanoLaPartida());
    }

    
    @Test
    public void testAlTenerCuatroPartesDeExodiaElJugadorPasivoNoPierde() {
    	PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
    	CartaMonstruo parte1 = new BrazoDerechoExodia();
    	CartaMonstruo parte2 = new BrazoIzquierdoExodia();
    	CartaMonstruo parte3 = new PiernaDerechaExodia();
    	CartaMonstruo parte4 = new PiernaIzquierdaExodia();
    	CartaMonstruo parte5 = new CuerpoExodia();
    	
    	
    	try {
            agregarCartaInicialALosMazos(partida);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte1);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte2);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte3);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte4);
    		partida.agregarCartaAlMazoDeJugadorActivo(parte5);
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    		partida.ponerCartaEnLaManoDeJugadorActivo();
    	}
    	
    	catch (Exception e){
            e.printStackTrace();
            fail();
    	}
    	
    	assertFalse(partida.jugadorPasivoPerdioLaPartida());
    }

	@Test
    public void testInvocarCartaMonstruoBocaAbajoYVoltearla() {
	    CartaMonstruo carta = new CartaMonstruoGenerica("",100, 100, 1);
	    PartidaAlGoOh partida = new PartidaAlGoOh("","");
	    try {
            agregarCartaInicialALosMazos(partida);
	        partida.agregarCartaAlMazoDeJugadorActivo(carta);
	        partida.ponerCartaEnLaManoDeJugadorActivo();
	        partida.cambiarDeFase();
	        partida.invocarCartaMonstruoEnPosicionAtaqueBocaAbajoDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
	        partida.terminarTurno();
	        partida.terminarTurno();
	        partida.cambiarDeFase();
	        partida.cambiarCartaMonstruoABocaArriba(carta);
        } catch (Exception e) {
	        e.printStackTrace();
	        fail();
        }

        assertTrue(carta.estaInvocadaBocaArriba());
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionDeAtaqueYCambiarlaDePosicion() {
        CartaMonstruo carta = new CartaMonstruoGenerica("",100, 100, 1);
        PartidaAlGoOh partida = new PartidaAlGoOh("","");
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarCartaMonstruoDePosicion(carta);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(carta.estaEnPosicionDeDefensa());
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionDeDefensaYCambiarlaDePosicion() {
        CartaMonstruo carta = new CartaMonstruoGenerica("",100, 100, 1);
        PartidaAlGoOh partida = new PartidaAlGoOh("","");
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarCartaMonstruoDePosicion(carta);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(carta.estaEnPosicionDeAtaque());
    }

    @Test
    public void testMonstruoInvocadoBocaAbajoNoPuedeAtacar() {
	    CartaMonstruo carta1 = new CartaMonstruoGenerica("", 1000, 1000, 1);
	    CartaMonstruo carta2 = new CartaMonstruoGenerica("", 1000, 1000, 1);
	    PartidaAlGoOh partida = new PartidaAlGoOh("", "");
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueBocaAbajoDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta1, carta2);
        } catch (MonstruoYaAtacoException | FuncionNoAccesibleException | MonstruoYaInvocadoException |  MazoLlenoException | CartaAlGoOhInexistenteException | MazoVacioException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 
        catch (MonstruoNoPuedeAtacarException e) {
            assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
        } 
    }

    @Test
    public void testCuandoUnMonstruoBocaAbajoEsAtacadoPasaAEstarBocaArriba() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 1000, 1000, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 2000, 1000, 1);
        PartidaAlGoOh partida = new PartidaAlGoOh("", "");
        try {
            agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionAtaqueBocaAbajoDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.cambiarDeFase();
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta1, carta2);
        } catch (MazoLlenoException | CartaAlGoOhInexistenteException | MazoVacioException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(carta2.estaInvocadaBocaArriba());
    }
}


