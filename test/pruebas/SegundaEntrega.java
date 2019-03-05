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
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;


public class SegundaEntrega {

	private void agregarCartaInicialALosMazos(PartidaAlGoOh partida) throws MazoLlenoException {
		// para que no pierda
		Carta carta = new CartaMonstruoGenerica("", 0, 0, 1);
		partida.agregarCartaAlMazoDeJugadorActivo(carta);
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(carta);
		partida.terminarTurno();
	}
	
	@Test
	public void test01wasteland() {
	
	try 
	{	
		/* Colocar un monstruo de cada lado del campo. */
		PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
		agregarCartaInicialALosMazos(partida);
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100,150,1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("b",200,300,2);
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
		
		/*Activo la carta mágica Wasteland*/
		CartaCampo wasteland = new Wasteland();
		partida.agregarCartaAlMazoDeJugadorActivo(wasteland);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaCampoDeJugadorActivoBocaArriba(wasteland);
		
		/*Verificar que de un lado del campo, el ataque del monstruo aumenta en 200 puntos*/
		assertTrue(carta1.tienePuntosDeAtaque(new Puntos(100 + 200)));
		/*  y del otro lado del campo, se aumenta la defensa del monstruo en 300 puntos.*/	
		assertTrue(carta2.tienePuntosDeDefensa(new Puntos(300 + 300)));
		
		
	}
		
	catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e){
		e.printStackTrace();
		fail();
	}
}

	
	@Test
	public void test02sogen() {
		/*
		 * Colocar un monstruo de cada lado del campo. */
	try 
	{	
		PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
		agregarCartaInicialALosMazos(partida);
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100,150,1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("b",200,300,2);
		partida.agregarCartaAlMazoDeJugadorActivo(carta1);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(carta2);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
		
		/* Activo la carta mágica Sogen*/
        partida.terminarTurno();
        partida.terminarTurno();
		CartaCampo sogen = new Sogen();
		partida.agregarCartaAlMazoDeJugadorActivo(sogen);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaCampoDeJugadorActivoBocaArriba(sogen);
		
		/*Verificar que de un lado del campo, 
		 * el ataque del monstruo aumenta en 200 puntos*/
		assertTrue(carta2.tienePuntosDeDefensa(new Puntos(300 + 500)));
		
		/* y  del otro lado del campo, se aumenta la defensa del monstruo en 
		 * 300 puntos.*/	
		assertTrue(carta1.tienePuntosDeAtaque(new Puntos(100 + 200)));
	
	}
		
	catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e){
		e.printStackTrace();
		fail();
	} 
	
	}

	
	@Test
	public void test03ollaDeLaCodicia () {


		PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
		try {
			agregarCartaInicialALosMazos(partida);
			CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100,150,1);
			CartaMonstruo carta2 = new CartaMonstruoGenerica("b",200,300,2);
			CartaMagia olla = new OllaDeLaCodicia();
			/*Activar la carta mágica Olla de la codicia, */
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.agregarCartaAlMazoDeJugadorActivo(olla);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMagiaBocaArribaDeJugadorActivo(olla);
		}
			
		catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | FuncionNoAccesibleException e)
		{
			e.printStackTrace();
			fail();
		}
		/*y verificar que tomo 2 cartas del mazo.*/
		assertTrue(partida.tieneLaCantidadDeCartasEnLaManoElJugadorActivo(2));
	
	}
	

	@Test
	public void test04Fisura() {
		/*
		 * Colocar 2 monstruos en el campo enemigo, con diferente ataque. */
	try 
	{	
		PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
		agregarCartaInicialALosMazos(partida);
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100,150,1);
		CartaMonstruo carta2 = new CartaMonstruoGenerica("b",200,300,2);
		partida.agregarCartaAlMazoDeJugadorActivo(carta1);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(carta2);
		partida.ponerCartaEnLaManoDeJugadorActivo();
		partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		
		/*Activo la cartamágica Fisura*/
		CartaMagia fisura = new Fisura();
		partida.agregarCartaAlMazoDeJugadorActivo(fisura);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMagiaBocaArribaDeJugadorActivo(fisura);
		
		/*Verificar que el de menor ataque es destruido.*/
		assertTrue(carta1.estaEnElCementerio());
		assertFalse(carta2.estaEnElCementerio());
	
	}
		
	catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e){
		e.printStackTrace();
		fail();
	} 
	
	}

	
	@Test
	public void test05Jinzo7() {
		
	try 
	{	
		PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
		agregarCartaInicialALosMazos(partida);
		CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100,150,1);
		
		//Colocar un monstruo en el campo enemigo
		partida.agregarCartaAlMazoDeJugadorActivo(carta1);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		
		/*Invoco a Jinzo #7 en mi lado del campo.*/
		CartaMonstruo jinzo = new Jinzo7();
		partida.agregarCartaAlMazoDeJugadorActivo(jinzo);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(jinzo, new MazoAlGoOh<CartaMonstruo>(0));
        partida.cambiarDeFase();
		
		/*
		 Verificar que puedo atacar a los puntos de vida directamente.
		 */
		partida.atacarJugadorPasivoALosPuntosDeVidaCon(jinzo);
		assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000 - 500)));
	
	}
		
	catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | AtaqueALosPuntosDeVidaInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoYaAtacoException | MonstruoNoPuedeAtacarException e){
		e.printStackTrace();
		fail();
	} 
	
	}

	
	@Test
	public void test06DragonesDeOjosAzules() {
		
	try 
	{
		//Invocar 3 dragones blancos de ojos azules
		PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
		agregarCartaInicialALosMazos(partida);
		CartaMonstruo dragon1 = new DragonBlancoDeOjosAzules();
		CartaMonstruo dragon2 = new DragonBlancoDeOjosAzules();
		CartaMonstruo dragon3 = new DragonBlancoDeOjosAzules();
		CartaMonstruo monstruoAux = new CartaMonstruoGenerica("A",1,1,1);
		
		partida.agregarCartaAlMazoDeJugadorActivo(monstruoAux);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruoAux, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(monstruoAux);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruoAux, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		MazoAlGoOh<CartaMonstruo> sacrificios1 = new MazoAlGoOh<CartaMonstruo>(2);
		sacrificios1.agregarCarta(monstruoAux);
		sacrificios1.agregarCarta(monstruoAux);
		partida.agregarCartaAlMazoDeJugadorActivo(dragon1);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon1, sacrificios1);


		partida.terminarTurno();
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(monstruoAux);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruoAux, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(monstruoAux);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruoAux, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		MazoAlGoOh<CartaMonstruo> sacrificios2 = new MazoAlGoOh<CartaMonstruo>(2);
		sacrificios2.agregarCarta(monstruoAux);
		sacrificios2.agregarCarta(monstruoAux);
		partida.agregarCartaAlMazoDeJugadorActivo(dragon2);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon2, sacrificios2);


		partida.terminarTurno();
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(monstruoAux);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruoAux, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		partida.agregarCartaAlMazoDeJugadorActivo(monstruoAux);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruoAux, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		partida.terminarTurno();
		MazoAlGoOh<CartaMonstruo> sacrificios3 = new MazoAlGoOh<CartaMonstruo>(2);
		sacrificios3.agregarCarta(monstruoAux);
		sacrificios3.agregarCarta(monstruoAux);
		partida.agregarCartaAlMazoDeJugadorActivo(dragon3);
		partida.ponerCartaEnLaManoDeJugadorActivo();
		partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragon3, sacrificios3);
		
		/* Invocar al Dragón definitivo de ojos azules
		sacrificando los 3 dragones el lado del campo del jugador 
		que los invoco */
		partida.terminarTurno();
		partida.terminarTurno();
		MazoAlGoOh<CartaMonstruo> sacrificios = new MazoAlGoOh<CartaMonstruo>(3);
		sacrificios.agregarCarta(dragon1);
		sacrificios.agregarCarta(dragon2);
		sacrificios.agregarCarta(dragon3);
		CartaMonstruo dragond = new DragonBlancoDefinitivoDeOjosAzules();
		partida.agregarCartaAlMazoDeJugadorActivo(dragond);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(dragond, sacrificios);
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
		assertTrue(dragond.estaEnPosicionDeAtaque());
		assertTrue(dragond.estaInvocadaBocaArriba());
		
	}
		
	catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException e){
		e.printStackTrace();
		fail();
	} 
	
	}

	
	
	@Test
	public void test07InsectoComeHombres() {
		
	try 
	{
		//Colocar al Insecto come-hombres, en posición de defensa boca abajo. 
		PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
		agregarCartaInicialALosMazos(partida);
		CartaMonstruo bug = new InsectoComeHombres();
		partida.agregarCartaAlMazoDeJugadorActivo(bug);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionDefensaBocaAbajoDeJugadorActivo(bug, new MazoAlGoOh<CartaMonstruo>(0));
		partida.terminarTurno();
		
		// Invocar un monstruo enemigo 
		CartaMonstruo monstruo = new CartaMonstruoGenerica("a", 1000,1000,1);
		partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
		partida.ponerCartaEnLaManoDeJugadorActivo();
        partida.cambiarDeFase();
		partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo, new MazoAlGoOh<CartaMonstruo>(0));
        partida.cambiarDeFase();
		//y atacar al insecto.
		partida.atacarCartaMonstruoConCartaMonstruo(monstruo, bug);
		
		// verificar que este se destruye
		assertTrue(monstruo.estaEnElCementerio());
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(0));
		
		// y que mi monstruo sigue en el  campo. 
		partida.terminarTurno();
		assertFalse(bug.estaEnElCementerio());
		assertTrue(partida.tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(1));
		
	}
		
	catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e){
		e.printStackTrace();
		fail();
	} 
	
	}

	
	
	@Test
	public void test08CilindroMagico() {
		
		try {
			PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
			agregarCartaInicialALosMazos(partida);
			CartaMonstruo monstruo = new CartaMonstruoGenerica("a", 1000,1000,1);
			
			//Colocar un monstruo del lado enemigo
			partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			
			//luego coloco la carta trampa Cilindro mágico de mi lado del campo.
			CartaTrampa cilindro1 = new CilindroMagico();
			partida.agregarCartaAlMazoDeJugadorActivo(cilindro1);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            CartaTrampa cilindro2 = new CilindroMagico();
            partida.agregarCartaAlMazoDeJugadorActivo(cilindro2);
            partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaTrampaBocaAbajo(cilindro1);
            partida.invocarCartaTrampaBocaAbajo(cilindro2);
            partida.terminarTurno();
			
			//Atacar con el monstruo y verificar que se activa la carta trampa,
			partida.cambiarDeFase();
			partida.cambiarDeFase();
            partida.atacarJugadorPasivoALosPuntosDeVidaCon(monstruo);
			// se niega el ataque 
			assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
			assertTrue(cilindro2.estaEnElCementerio());
			
			// la otra carta trampa sigue invocada boca abajo
			assertTrue(cilindro1.estaInvocadaBocaAbajo());
						
			/*,  el oponente recibe el daño directamente en sus puntos
			de vida.*/
			assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 1000)));
			
		}
		
		catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | AtaqueALosPuntosDeVidaInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoYaAtacoException | MonstruoNoPuedeAtacarException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void test09Refuerzos() {
		
		try {
			PartidaAlGoOh partida = new PartidaAlGoOh("j1","j2");
			agregarCartaInicialALosMazos(partida);
			CartaMonstruo monstruo = new CartaMonstruoGenerica("a", 1000,1000,1);
			
			//Coloco un monstruo en posición de ataque
			partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			partida.terminarTurno();
			// y la carta trampa Reinforcements de mi lado del campo
			CartaTrampa refuerzos = new Refuerzos();
			partida.agregarCartaAlMazoDeJugadorActivo(refuerzos);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaTrampaBocaAbajo(refuerzos);
			partida.terminarTurno();
			
			// coloco un monstruo en el campo enemigo (con 400 puntos mas de
			// ataque que el primero) 
			CartaMonstruo monstruo2 = new CartaMonstruoGenerica("a", 1400, 0, 1);
			partida.agregarCartaAlMazoDeJugadorActivo(monstruo2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
            partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(monstruo2, new MazoAlGoOh<CartaMonstruo>(0));
			
			//atacar al primer monstruo
            partida.cambiarDeFase();
			partida.atacarCartaMonstruoConCartaMonstruo(monstruo2, monstruo);
			
			// verificar que se activa la carta trampa y el monstruo enemigo es destruido
			assertTrue(monstruo2.estaEnElCementerio());
			assertTrue(refuerzos.estaEnElCementerio());
			
			//y se infligió 100 puntos de daño a la vida del otro jugador.
			assertTrue(partida.puntosDeVidaDeJugadorActivoSon(new Puntos(8000 - 100)));
			assertTrue(partida.puntosDeVidaDeJugadorPasivoSon(new Puntos(8000)));
			assertTrue(monstruo.tienePuntosDeAtaque(new Puntos(1500)));
			partida.terminarTurno();
			assertTrue(monstruo.tienePuntosDeAtaque(new Puntos(1000)));
			
		}
		
		catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoNoPuedeAtacarException | MonstruoYaAtacoException e) {
			e.printStackTrace();
			fail();
		}
	}

	
	
	@Test
	public void test10MazoVacio() {
		
	
		try
		{
			PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
			CartaMonstruo monstruo = new CartaMonstruoGenerica("a", 100, 100, 1);
			
			for (int i = 0; i < 40; i++) {
				partida.agregarCartaAlMazoDeJugadorActivo(monstruo);
			}
			assertFalse(partida.jugadorActivoPerdioLaPartida());
			
			// Extraer todas las cartas del mazo,
			for (int i = 0; i < 40; i++) {
			assertFalse(partida.jugadorActivoPerdioLaPartida());
			partida.ponerCartaEnLaManoDeJugadorActivo();
			}
			// verificar que la partida terminó y el jugador perdió.
			assertTrue(partida.jugadorActivoPerdioLaPartida());
		
		}
		
		catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | FuncionNoAccesibleException e)
		{
			e.printStackTrace();
			fail();
		}
	}
	


	@Test
	public void test11Exodia() {
		
	
		try
		{
			PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
			agregarCartaInicialALosMazos(partida);
			CartaMonstruo parte1 = new BrazoDerechoExodia();
			CartaMonstruo parte2 = new BrazoIzquierdoExodia();
			CartaMonstruo parte3 = new CuerpoExodia();
			CartaMonstruo parte4 = new PiernaDerechaExodia();
			CartaMonstruo parte5 = new PiernaIzquierdaExodia();
			
			partida.agregarCartaAlMazoDeJugadorActivo(parte1);
			partida.agregarCartaAlMazoDeJugadorActivo(parte2);
			partida.agregarCartaAlMazoDeJugadorActivo(parte3);
			partida.agregarCartaAlMazoDeJugadorActivo(parte4);
			partida.agregarCartaAlMazoDeJugadorActivo(parte5);
			assertFalse(partida.jugadorActivoGanoLaPartida());
			assertFalse(partida.jugadorPasivoPerdioLaPartida());
			
			partida.ponerCartaEnLaManoDeJugadorActivo();
			assertFalse(partida.jugadorActivoGanoLaPartida());
			assertFalse(partida.jugadorPasivoPerdioLaPartida());
			
			partida.ponerCartaEnLaManoDeJugadorActivo();
			assertFalse(partida.jugadorActivoGanoLaPartida());
			assertFalse(partida.jugadorPasivoPerdioLaPartida());
			
			partida.ponerCartaEnLaManoDeJugadorActivo();
			assertFalse(partida.jugadorActivoGanoLaPartida());
			assertFalse(partida.jugadorPasivoPerdioLaPartida());
			
			partida.ponerCartaEnLaManoDeJugadorActivo();
			assertFalse(partida.jugadorActivoGanoLaPartida());
			assertFalse(partida.jugadorPasivoPerdioLaPartida());
			
			partida.ponerCartaEnLaManoDeJugadorActivo();
			assertTrue(partida.jugadorActivoGanoLaPartida());
			assertTrue(partida.jugadorPasivoPerdioLaPartida());
		}
		
		catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | FuncionNoAccesibleException e)
		{
			e.printStackTrace();
			fail();
		}
	}

}

