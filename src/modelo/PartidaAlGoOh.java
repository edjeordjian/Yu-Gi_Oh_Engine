package modelo;

import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasCampo.Sogen;
import modelo.cartas.cartasCampo.Wasteland;
import modelo.cartas.cartasMagia.AgujeroNegro;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMagia.Fisura;
import modelo.cartas.cartasMagia.OllaDeLaCodicia;
import modelo.cartas.cartasMonstruo.Aitsu;
import modelo.cartas.cartasMonstruo.AmphibianBeast;
import modelo.cartas.cartasMonstruo.AncientOneOfTheDeepForest;
import modelo.cartas.cartasMonstruo.BrazoDerechoExodia;
import modelo.cartas.cartasMonstruo.BrazoIzquierdoExodia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.ClawReacher;
import modelo.cartas.cartasMonstruo.CloudianSmokeBall;
import modelo.cartas.cartasMonstruo.CuerpoExodia;
import modelo.cartas.cartasMonstruo.CyberTechAlligator;
import modelo.cartas.cartasMonstruo.DDTrainer;
import modelo.cartas.cartasMonstruo.DarkAssailant;
import modelo.cartas.cartasMonstruo.DragonBlancoDeOjosAzules;
import modelo.cartas.cartasMonstruo.DragonBlancoDefinitivoDeOjosAzules;
import modelo.cartas.cartasMonstruo.GemKnightCrystal;
import modelo.cartas.cartasMonstruo.HinotamaSoul;
import modelo.cartas.cartasMonstruo.InsectoComeHombres;
import modelo.cartas.cartasMonstruo.Jinzo7;
import modelo.cartas.cartasMonstruo.KillerNeedle;
import modelo.cartas.cartasMonstruo.LauncherSpider;
import modelo.cartas.cartasMonstruo.PiernaDerechaExodia;
import modelo.cartas.cartasMonstruo.PiernaIzquierdaExodia;
import modelo.cartas.cartasMonstruo.Rabidragon;
import modelo.cartas.cartasMonstruo.SealmasterMeisei;
import modelo.cartas.cartasMonstruo.TriHornedDragon;
import modelo.cartas.cartasMonstruo.WingsOfWickedFlame;
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
import modelo.fases.Fase;
import modelo.fases.FaseFinal;
import modelo.fases.FaseInicial;
import modelo.fases.FaseJuegoTerminado;


public class PartidaAlGoOh {

	private Turno turno;
	private Jugador perdedor;
	private Jugador ganador;
	private Fase fase;
	
    public PartidaAlGoOh(String unNombreJugador, String otroNombreJugador) {
		Jugador unJugador = new Jugador(unNombreJugador);
		Jugador otroJugador = new Jugador(otroNombreJugador);
        this.turno = new Turno(unJugador, otroJugador);
        this.perdedor = null;
        this.ganador = null;
        this.fase = new FaseInicial();
    }

    public void agregarCartaAlMazoDeJugadorActivo(Carta carta) throws MazoLlenoException {
		turno.obtenerJugadorActivo().agregarCartaAlMazo(carta);
	}

	public void ponerCartaEnLaManoDeJugadorActivo() throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException, FuncionNoAccesibleException {
		fase.ponerCartaEnLaMano(turno.obtenerJugadorActivo());
		verificarFinDelJuego();
	}

	private void verificarMazoVacio() {
		Jugador jugadorActual = turno.obtenerJugadorActivo();
		Jugador otroJugador = turno.obtenerJugadorPasivo();

		if(jugadorActual.tieneMazoVacio()) {
			this.perdedor = jugadorActual;
			this.ganador = otroJugador;
			terminarJuego();
		}
	}
	
	private void verificarExodiaEnLaMano() {
		Jugador jugadorActual = turno.obtenerJugadorActivo();
		Jugador otroJugador = turno.obtenerJugadorPasivo();
		if(jugadorActual.tieneAExodiaEnLaMano()) {
			this.ganador = jugadorActual;
			this.perdedor = otroJugador;
			terminarJuego();
		}
	}
	
	private void verificarJugadorSinVida() {
		Jugador jugadorActual = turno.obtenerJugadorActivo();
		Jugador otroJugador = turno.obtenerJugadorPasivo();
		
		if(jugadorActual.seQuedoSinPuntosDeVida() ) {
			this.ganador = otroJugador;
			this.perdedor = jugadorActual;
			terminarJuego();
		}
		
		else if(otroJugador.seQuedoSinPuntosDeVida()) {
			this.ganador = jugadorActual;
			this.perdedor = otroJugador;
			terminarJuego();
		}
	}
	
	private void verificarFinDelJuego() {
		verificarMazoVacio();
		verificarExodiaEnLaMano();
		verificarJugadorSinVida();
	}
	
    private void terminarJuego() {
        this.fase = new FaseJuegoTerminado();
    }


    public void invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException, MonstruoYaInvocadoException {
		fase.invocarCartaMonstruoEnPosicionAtaque(turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo(), carta, cartasASacrificar);
	}


	public void invocarCartaMonstruoEnPosicionDefensaDeJugadorActivo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException, MonstruoYaInvocadoException {
        fase.invocarCartaMonstruoEnPosicionDefensa(turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo(), carta, cartasASacrificar);
	}


	public void invocarCartaMonstruoEnPosicionDefensaBocaAbajoDeJugadorActivo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar)  throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException, MonstruoYaInvocadoException {
		fase.invocarCartaMonstruoEnPosicionDefensaBocaAbajo(turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo(), carta, cartasASacrificar);
	}


    public void invocarCartaMonstruoEnPosicionAtaqueBocaAbajoDeJugadorActivo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, FuncionNoAccesibleException, MonstruoYaInvocadoException {
        fase.invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo(), carta, sacrificios);
    }

	public void atacarCartaMonstruoConCartaMonstruo(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, MonstruoNoPuedeAtacarException, MonstruoYaAtacoException, FuncionNoAccesibleException {
		fase.atacarCartaMonstruoConCartaMonstruo(cartaAtacante, cartaAtacada, turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo());
		verificarFinDelJuego();
	}


	public boolean puntosDeVidaDeJugadorActivoSon(Puntos puntos) {
		return turno.obtenerJugadorActivo().puntosDeVidaSon(puntos);
	}


    public boolean puntosDeVidaDeJugadorPasivoSon(Puntos puntos) {
        return turno.obtenerJugadorPasivo().puntosDeVidaSon(puntos);
    }

    public void cambiarCartaMonstruoABocaArriba(CartaMonstruo carta) throws FuncionNoAccesibleException {
        fase.cambiarCartaMonstruoABocaArriba(carta, turno.obtenerJugadorPasivo());
    }

    public void cambiarCartaMonstruoDePosicion(CartaMonstruo carta) throws FuncionNoAccesibleException {
        fase.cambiarCartaMonstruoDePosicion(carta);
    }

	public void invocarCartaMagiaBocaArribaDeJugadorActivo(CartaMagia cartaMagia) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException, FuncionNoAccesibleException {
        fase.invocarCartaMagiaBocaArriba(cartaMagia, turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo());
        verificarFinDelJuego();
	}

    public void invocarCartaMagiaBocaAbajoDeJugadorActivo(CartaMagia cartaMagia) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException, FuncionNoAccesibleException {
        fase.invocarCartaMagiaBocaAbajo(cartaMagia, turno.obtenerJugadorActivo());
    }

    public void cambiarCartaMagiaABocaArriba(CartaMagia cartaMagia) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        fase.cambiarCartaMagiaABocaArriba(cartaMagia, turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo());
        verificarFinDelJuego();
    }

	public boolean tieneLaCantidadDeMonstruosInvocadosElJugadorActivo(int cantidad) {
		return turno.obtenerJugadorActivo().tieneLaCantidadDeMonstruos(cantidad);
	}


	public boolean tieneLaCantidadDeMonstruosInvocadosElJugadorPasivo(int cantidad){
		return turno.obtenerJugadorPasivo().tieneLaCantidadDeMonstruos(cantidad);
	}


	public void terminarTurno() {
		turno.obtenerJugadorActivo().revertirCambiosTemporales();
		turno.terminarTurno(turno.obtenerJugadorActivo());
        this.fase = new FaseInicial();
    }


	public boolean tieneLaCantidadDeCartasEnLaManoElJugadorActivo(int cantidad) {
		return turno.obtenerJugadorActivo().tieneLaCantidadDeCartasEnLaMano(cantidad);
	}


	public boolean tieneLaCantidadDeCartasEnLaManoElJugadorPasivo(int cantidad) {
		return turno.obtenerJugadorPasivo().tieneLaCantidadDeCartasEnLaMano(cantidad);
	}

	public boolean tieneLaCantidadDeCartasEnElMazoElJugadorActivo(int cantidad) {
		return turno.obtenerJugadorActivo().tieneLaCantidadDeCartasEnElMazo(cantidad);
	}


	public boolean tieneLaCantidadDeCartasEnElMazoElJugadorPasivo(int cantidad) {
		return turno.obtenerJugadorPasivo().tieneLaCantidadDeCartasEnElMazo(cantidad);
	}

	public void invocarCartaCampoDeJugadorActivoBocaArriba(CartaCampo cartaCampo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
		fase.invocarCartaCampoBocaArriba(cartaCampo, turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo());
	}


	public void atacarJugadorPasivoALosPuntosDeVidaCon(CartaMonstruo carta) throws AtaqueALosPuntosDeVidaInvalidoException, MazoLlenoException, CartaAlGoOhInexistenteException, MonstruoYaAtacoException, MonstruoNoPuedeAtacarException, FuncionNoAccesibleException {
		Jugador jugadorPasivo = turno.obtenerJugadorPasivo();
		Jugador jugadorActivo = turno.obtenerJugadorActivo();
		fase.atacarALosPuntosDeVida(carta, jugadorActivo, jugadorPasivo);
		verificarFinDelJuego();

	}


	public void invocarCartaTrampaBocaAbajo(CartaTrampa cartaTrampa) throws CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException {
		fase.invocarCartaTrampaBocaAbajo(turno.obtenerJugadorActivo(), cartaTrampa);
	}


	public void activarCartaMagiaDesdeLaMano(CartaMagia cartaMagia) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        fase.activarCartaMagiaDesdeLaMano(cartaMagia, turno.obtenerJugadorActivo(), turno.obtenerJugadorPasivo());
        verificarFinDelJuego();
	}

    public void cambiarDeFase() {
    	try {
        this.fase = fase.cambiarDeFase();
        
    	}
    	catch(FuncionNoAccesibleException e){
            turno.terminarTurno(turno.obtenerJugadorActivo());
        }
    }

	public boolean jugadorActivoPerdioLaPartida() {
		return turno.obtenerJugadorActivo().equals(perdedor);
	}


	public boolean jugadorPasivoPerdioLaPartida() {
		return turno.obtenerJugadorPasivo().equals(perdedor);
	}


	public boolean jugadorActivoGanoLaPartida() {
		return turno.obtenerJugadorActivo().equals(ganador);
	}


	public boolean jugadorPasivoGanoLaPartida() {
		return turno.obtenerJugadorPasivo().equals(ganador);
	}

	public void iniciar() throws MazoLlenoException, MazoVacioException, CartaAlGoOhInexistenteException, CloneNotSupportedException {
		MazoAlGoOh<Carta> cartasPosibles = obtenerCartasPosibles();
		Jugador jugadorActivo = turno.obtenerJugadorActivo();
		Jugador jugadorPasivo = turno.obtenerJugadorPasivo();

		jugadorActivo.construiMazoCon(cartasPosibles);
		jugadorActivo.ponerCartasEnLaMano(5);
		jugadorPasivo.construiMazoCon(cartasPosibles);
		jugadorPasivo.ponerCartasEnLaMano(5);
	}
	
    public MazoAlGoOh<Carta> obtenerCartasPosibles() throws MazoLlenoException{
		MazoAlGoOh<Carta> cartasPosibles = new MazoAlGoOh<Carta>(Constantes.CANTIDAD_DE_CARTAS_POSIBLES);

		//Monstruos sin efecto
		cartasPosibles.agregarCarta(new Aitsu());
		cartasPosibles.agregarCarta(new AmphibianBeast());
		cartasPosibles.agregarCarta(new AncientOneOfTheDeepForest());
		cartasPosibles.agregarCarta(new BrazoDerechoExodia());
		cartasPosibles.agregarCarta(new BrazoIzquierdoExodia());
		cartasPosibles.agregarCarta(new ClawReacher());
		cartasPosibles.agregarCarta(new CloudianSmokeBall());
		cartasPosibles.agregarCarta(new CuerpoExodia());
		cartasPosibles.agregarCarta(new CyberTechAlligator());
		cartasPosibles.agregarCarta(new DarkAssailant());
		cartasPosibles.agregarCarta(new DDTrainer());
		cartasPosibles.agregarCarta(new DragonBlancoDefinitivoDeOjosAzules());
		cartasPosibles.agregarCarta(new DragonBlancoDeOjosAzules());
		cartasPosibles.agregarCarta(new GemKnightCrystal());
		cartasPosibles.agregarCarta(new HinotamaSoul());
		cartasPosibles.agregarCarta(new KillerNeedle());
		cartasPosibles.agregarCarta(new LauncherSpider());
		cartasPosibles.agregarCarta(new PiernaDerechaExodia());
		cartasPosibles.agregarCarta(new PiernaIzquierdaExodia());
		cartasPosibles.agregarCarta(new Rabidragon());
		cartasPosibles.agregarCarta(new SealmasterMeisei());
		cartasPosibles.agregarCarta(new TriHornedDragon());
		cartasPosibles.agregarCarta(new WingsOfWickedFlame());
		//Monstruos con efecto
		cartasPosibles.agregarCarta(new InsectoComeHombres());
		cartasPosibles.agregarCarta(new Jinzo7());
		//Trampanew 
		cartasPosibles.agregarCarta(new CilindroMagico());
		cartasPosibles.agregarCarta(new Refuerzos());
		//Magianew 
		cartasPosibles.agregarCarta(new AgujeroNegro());
		cartasPosibles.agregarCarta(new Fisura());
		cartasPosibles.agregarCarta(new OllaDeLaCodicia());
		//Campo
		cartasPosibles.agregarCarta(new Sogen());
		cartasPosibles.agregarCarta(new Wasteland());
		return cartasPosibles;
	}

    //Para MVC

	public Fase obtenerFase() {
		return this.fase;
	}
	
	public Jugador obtenerJugadorActivo() {
		return turno.obtenerJugadorActivo();
	}
	
	public Jugador obtenerJugadorPasivo() {
		return turno.obtenerJugadorPasivo();
	}

	public boolean laFaseActualEsLaUltima() {
		return this.fase.toString().equals((new FaseFinal()).toString());
	}


	// para debug
/*
	public void obtenerTodo() {
    	System.out.println("Jugador: " +  this.obtenerJugadorActivo().toString());
    	System.out.println("	Puntos de vida: " + this.obtenerJugadorActivo().getPuntosDeVida());
    	this.obtenerJugadorActivo().obtenerTodo();
    	System.out.println();
    	System.out.println();
		System.out.println("Jugador: " +  this.obtenerJugadorPasivo().toString());
		System.out.println("	Puntos de vida: " + this.obtenerJugadorPasivo().getPuntosDeVida());
		this.obtenerJugadorPasivo().obtenerTodo();
		System.out.println();
		System.out.println();

	}*/

	public void cambiarCartaMonstruoABocaAbajo(CartaMonstruo carta) throws FuncionNoAccesibleException {

        fase.cambiarCartaMonstruoABocaAbajo(carta);
	}

	public void cambiarCartaMonstruoAModoAtaque(CartaMonstruo carta) throws FuncionNoAccesibleException {
		   fase.cambiarCartaMonstruoAModoAtaque(carta);
		}

	public void cambiarCartaMonstruoAModoDefensa(CartaMonstruo carta) throws FuncionNoAccesibleException {
		   fase.cambiarCartaMonstruoAModoDefensa(carta);
		}
}
