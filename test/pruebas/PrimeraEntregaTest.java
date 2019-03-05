package pruebas;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import modelo.CampoDeBatalla;
import modelo.MazoAlGoOh;
import modelo.PartidaAlGoOh;
import modelo.Puntos;
import modelo.cartas.Carta;
import modelo.cartas.CartaAlGoOh;
import modelo.cartas.cartasMagia.AgujeroNegro;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.cartas.cartasTrampa.CilindroMagico;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;


public class PrimeraEntregaTest {

	private void agregarCartaInicialALosMazos(PartidaAlGoOh partida) throws MazoLlenoException {
		// para que no pierda
		Carta carta = new CartaMonstruoGenerica("", 0, 0, 1);
		partida.agregarCartaAlMazoDeJugadorActivo(carta);
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(carta);
		partida.terminarTurno();
	}

	@Test
	public void test01ColocarCartaMonstruoEnPosicionDeAtaque() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaMonstruo carta = new CartaMonstruoGenerica("a",0,0,1);

		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaMonstruoEnPosicionDeAtaque(carta, new MazoAlGoOh<CartaMonstruo>(0));;
		}

		catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		assertTrue(carta.estaEnPosicionDeAtaque());
		assertTrue(carta.estaInvocadaBocaArriba());
		assertTrue(campo.cartaMonstruoEstaEnPosicionDeAtaque(carta));
		assertTrue(!campo.cartaEstaInvocadaBocaAbajo(carta));
	}


	@Test
	public void test02ColocarCartaMonstruoEnPosicionDeDefensa() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaMonstruo carta = new CartaMonstruoGenerica("a",0,0,1);

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


		assertTrue(carta.estaEnPosicionDeDefensa());
		assertTrue(carta.estaInvocadaBocaArriba());
		assertTrue(campo.cartaMonstruoEstaEnPosicionDeDefensa(carta));
		assertTrue(!campo.cartaEstaInvocadaBocaAbajo(carta));
	}


	@Test
	public void test03ColocarCartaMagicaEnElCampoBocaAbajo() {
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

		assertTrue(carta.estaInvocadaBocaAbajo());
		assertTrue(campo.cartaEstaInvocadaBocaAbajo(carta));
		assertFalse(campo.cartaEstaEnElCementerio(carta));
	}


	@Test
	public void test04ColocarCartaTrampaEnElCampoBocaAbajo() {
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

		assertTrue(carta.estaInvocadaBocaAbajo());
		assertTrue(campo.cartaEstaInvocadaBocaAbajo(carta));
		assertFalse(campo.cartaEstaEnElCementerio(carta));
	}


	@Test
	public void test05EnviarCartaAlCementerioYVerificarQueEsteAhi() {
		CampoDeBatalla campo = new CampoDeBatalla();
		CartaMonstruo carta = new CartaMonstruoGenerica("a",0,0,1);

		try {
			campo.agregarCartaAlMazo(carta);
			campo.ponerCartaEnLaMano();
			campo.invocarCartaMonstruoEnPosicionDeAtaque(carta, new MazoAlGoOh<CartaMonstruo>(0));
			campo.enviarCartaAlCementerio(carta);
		}

		catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException e) {
			e.printStackTrace();
            fail();
        }

		assertTrue(carta.estaEnElCementerio());
		assertTrue(campo.cartaEstaEnElCementerio(carta));
	}


	@Test
	public void test06AtacarCartaEnPosicionDeAtaqueQueTieneMenosPuntos() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
			agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
            // Colocar una carta de monstruo en posiciÃ³n de ataque
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			// el oponente coloca otra carta de monstruo en posiciÃ³n de ataque (con mayor ataque).
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2,new MazoAlGoOh<CartaMonstruo>(0));
			partida.cambiarDeFase();
			//Atacar al primer monstruo
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		// verificar que este se destruyÃ³
		assertTrue(carta1.estaEnElCementerio());
		//y sufro daÃ±o a los puntos de vida igual a la diferencia de los puntos de ataque de lo monstruos
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000 - 90)));

	}


	@Test
	public void test07AtacarCartaEnPosicionDeAtaqueQueTieneMasPuntos() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);

		try {
			agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            // Colocar una carta de monstruo en posiciÃ³n de ataque
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			//el oponente coloca otra carta de monstruo en posiciÃ³n de ataque (con menor ataque),
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			// atacar al primer monstruo
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		//verificar que el monstruo atacante es destruido
		assertTrue(carta2.estaEnElCementerio());
		//y el atacante recibe daÃ±o a los puntos de vida igual a la diferencia de ataques.
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 90)));

	}


	@Test
	public void test08AtacarCartaEnPosicionDeAtaqueQueTieneIgualPuntos() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);

		try {
			agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            //Colocar una carta de monstruo en posiciÃ³n de ataque
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			// El oponente coloca otra carta de  monstruo en posiciÃ³n de ataque (con igual ataque)
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2,new MazoAlGoOh<CartaMonstruo>(0));
            partida.cambiarDeFase();
			//atacar al primer monstruo
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		// verificar que ambos monstruos son destruidos
		assertTrue(carta1.estaEnElCementerio());
		assertTrue(carta2.estaEnElCementerio());
		// y nadie recibe daÃ±o a los puntos de vida.
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));

	}


	@Test
	public void test09AtacarCartaEnPosicionDeDefensaQueTieneMenosPuntos() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 1, 1);

		try {
			agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
            //Colocar una carta de monstruo en posiciÃ³n de defensa
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			// , el oponente coloca otra carta  de monstruo en posiciÃ³n de ataque
			//(con mayor ataque que la defensa del primer monstruo)
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2,new MazoAlGoOh<CartaMonstruo>(0));
			//atacar al primer monstruo
			partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		}

		catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		// verificar que este se destruyÃ³
		assertTrue(carta1.estaEnElCementerio());

		//  y no sufriÃ³ ningÃºn daÃ±o vital.
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));

	}


	@Test
	public void test10AtacarCartaEnPosicionDeDefensaQueTieneMasPuntos() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 1, 1);

		try {
			agregarCartaInicialALosMazos(partida);
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            //Colocar una carta de monstruo en posiciÃ³n de defensa
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            // , el oponente coloca otra carta  de monstruo en posiciÃ³n de ataque
            //(con mayor ataque que la defensa del primer monstruo)
            partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2,new MazoAlGoOh<CartaMonstruo>(0));
            //atacar al primer monstruo
            partida.cambiarDeFase();
            partida.atacarCartaMonstruoConCartaMonstruo(carta2, carta1);
		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		//verificar que este no se destruyÃ³ y no sufriÃ³
		assertFalse(carta1.estaEnElCementerio());
		//No sufriÃ³ ninguno daÃ±o vital.
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
	}


	@Test
	public void test11ColocarAgujeroNegroBocaArribaActivandoseSuEfecto() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1000, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",200, 200, 1);
		CartaMonstruo carta4 = new CartaMonstruoGenerica("a",1234, 0, 1);
		CartaMagia agujeroNegro = new AgujeroNegro();
		try {
			agregarCartaInicialALosMazos(partida);
            // Colocar monstruos en ambos lados del campo.
            partida.agregarCartaAlMazoDeJugadorActivo(carta1);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2,new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
            partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta3,new MazoAlGoOh<CartaMonstruo>(0));
            partida.terminarTurno();
            partida.agregarCartaAlMazoDeJugadorActivo(carta4);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta4,new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.agregarCartaAlMazoDeJugadorActivo(agujeroNegro);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			// Colocar â€œAgujero negroâ€� boca arriba (es decir, se activa el efecto)
			partida.invocarCartaMagiaBocaArribaDeJugadorActivo(agujeroNegro);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		//Verificar que se destruyeron todos los monstruos de ambos lados del campo,
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(0));
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorPasivo(0));
		//y que nadie recibiÃ³ daÃ±o alguno.
		assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000)));
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));

	}


	@Test
	public void test12InvocarMonstruoDe5o6EstrellasQueRequiereUnSacrificio() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",100, 10, 6);
		MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(1);
		try {
			agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			//Se coloca un monstruo en el campo
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
			partida.cambiarDeFase();
			// se quiere colocar un monstruo de 5 o 6 estrellas que requiere  sacrificio.
			cartasASacrificar.agregarCarta(carta1);
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, cartasASacrificar);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }
		// Se verifica que se convocÃ³ al monstruo y se destruyÃ³ el primero.
		assertFalse(carta2.estaEnElCementerio());
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
		assertTrue(carta1.estaEnElCementerio());
	}


	@Test
	public void test13InvocarMonstruoDe7oMasEstrellasQueRequiereDosSacrificios() {

		PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",10, 1, 1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("a",10, 10, 1);
		CartaMonstruo carta3 = new CartaMonstruoGenerica("a",1000, 10, 7);
		MazoAlGoOh<CartaMonstruo> cartasASacrificar = new MazoAlGoOh<CartaMonstruo>(2);
		try {
			agregarCartaInicialALosMazos(partida);
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			//Se colocan 2 monstruos en el campo
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta1,new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(carta2,new MazoAlGoOh<CartaMonstruo>(0));
			//Se quiere colocar un monstruo de 7 o mÃ¡s estrellas que requiere 2 sacrificios
			partida.terminarTurno();
			partida.terminarTurno();
			partida.cambiarDeFase();
			cartasASacrificar.agregarCarta(carta1);
			cartasASacrificar.agregarCarta(carta2);
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta3, cartasASacrificar);

		} catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            fail();
        }

		// Se verifica que se convocÃ³ al monstruo y se destruyeron los demÃ¡s.
		assertTrue(carta1.estaEnElCementerio());
		assertTrue(carta2.estaEnElCementerio());
		assertFalse(carta3.estaEnElCementerio());
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
	}
}
