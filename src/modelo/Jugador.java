package modelo;


import java.util.ArrayList;

import modelo.cartas.Carta;
import modelo.cartas.EfectoSobreCampo;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.SacrificioInvalidoException;

public class Jugador {

	private CampoDeBatalla campo;
	private Puntos puntosDeVida;
	private String nombre;
	private ArrayList<CambioTemporal> cambiosTemporales;

	
	public Jugador(String nombre) {
		this.campo = new CampoDeBatalla();
		this.puntosDeVida = new Puntos(Constantes.CANTIDAD_DE_PUNTOS_DE_VIDA_INICIALES);
		this.nombre = nombre;
		cambiosTemporales = new ArrayList<CambioTemporal>();
	}



	@Override
	public String toString() {
		return this.nombre;
	}



	public void agregarCartaAlMazo(Carta carta) throws MazoLlenoException {
		campo.agregarCartaAlMazo(carta);
	}


	public void ponerCartaEnLaMano() throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException {
	   campo.ponerCartaEnLaMano();
	}
	
	
	public void invocarCartaMonstruoEnPosicionAtaque(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException {
		campo.invocarCartaMonstruoEnPosicionDeAtaque(carta, cartasASacrificar);
	}

	public void invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException {
		campo.invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(carta, sacrificios);
	}


	public void invocarCartaMonstruoEnPosicionDefensa(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException {
		campo.invocarCartaMonstruoEnPosicionDeDefensa(carta, cartasASacrificar);
	}


	public void invocarCartaMonstruoEnPosicionDefensaBocaAbajo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException {
		campo.invocarCartaMonstruEnPosicionDeDefensaBocaAbajo(carta, cartasASacrificar);
	}


	public boolean puntosDeVidaSon(Puntos puntos) {
		return this.puntosDeVida.sonIguales(puntos);
	}


	public void invocarCartaMagiaBocaArriba(CartaMagia cartaMagia, Jugador oponente) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		campo.invocarCartaMagiaBocaArriba(cartaMagia);
		activarCartaMagia(cartaMagia, oponente);
	}

	public void invocarCartaMagiaBocaAbajo(CartaMagia cartaMagia) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		campo.invocarCartaMagiaBocaAbajo(cartaMagia);
	}


	private void activarEfecto(EfectoSobreCampo cartaConEfecto) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		campo.activarEfecto(cartaConEfecto);
	}

	private void activarEfectoOponente(EfectoSobreCampo cartaConEfecto) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		campo.activarEfectoOponente(cartaConEfecto);
	}

	public boolean tieneLaCantidadDeMonstruos(int cantidad) {
		return campo.tieneLaCantidadDeMonstruos(cantidad);
	}

	public Puntos getPuntosDeVida() {
		return this.puntosDeVida;
	}


	public void enviarAlCementerio(Carta carta) {
		try {
			campo.enviarCartaAlCementerio(carta);
		} catch (MazoLlenoException | CartaAlGoOhInexistenteException e) {}
	}


	public boolean tieneLaCantidadDeCartasEnLaMano(int cantidad) {
		return campo.tieneLaCantidadDeCartasEnLaMano(cantidad);
	}


    public void invocarCartaCampoBocaArriba(CartaCampo cartaCampo, Jugador oponente) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
        campo.invocarCartaCampoBocaArriba(cartaCampo);
        oponente.activarEfectoOponente(cartaCampo);
    }
    
    public void darEfectoAOponentePorCartaCampo(CartaMonstruo carta) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException{
    	campo.activarEnOponenteEfectoPorCampo(carta);
    }


	public void quitarPuntosDeVida(Puntos puntosAQuitar){
		this.puntosDeVida.restarPuntos(puntosAQuitar);
		if (this.puntosDeVida.sonNegativos(this.puntosDeVida))
			this.puntosDeVida = new Puntos(0);
	}


	public boolean verificacionDeCartasTrampa(Jugador jugadorAtacado, CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada) throws MazoLlenoException, CartaAlGoOhInexistenteException {

		MazoAlGoOh<CartaTrampa> cartasTrampa = jugadorAtacado.obtenerCartasTrampaPorAtaque();
		boolean continuarConElAtaque = true;

        try {
            CartaTrampa carta = cartasTrampa.obtenerCartaTope();
            activarCartaTrampa(carta, cartaAtacante, cartaAtacada, this, jugadorAtacado);
            if (!carta.permiteContinuarElAtaque()) continuarConElAtaque = false;
        } catch (MazoVacioException e) { 
        	/* Si el mazo esta vacio no hace nada */
        	// ¿Por qué no iterar? Porque sólo se activa una.
        	}
        catch (NullPointerException e) {
        	//La carta trampa no puede tener efecto sobre la otra.
        }

		return continuarConElAtaque;
	}


	public void atacarCartaMonstruoConCartaMonstruo(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorAtacado) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, MonstruoNoPuedeAtacarException {

		if (verificacionDeCartasTrampa(jugadorAtacado, cartaAtacante, cartaAtacada))
			cartaAtacante.atacarCartaMonstruo(cartaAtacada, this, jugadorAtacado);
	}


	public void agregarCambioTemporal(CambioTemporal cambio) {
		cambiosTemporales.add(cambio);
	}


	public void revertirCambiosTemporales() {
		for (CambioTemporal cambio: this.cambiosTemporales) {
			cambio.revertir();
		}
	}


	private void activarCartaTrampa(CartaTrampa carta, CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorAtacante, Jugador jugadorAtacado) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		carta.activarEfecto(cartaAtacante, cartaAtacada, jugadorAtacante);
		jugadorAtacado.enviarAlCementerio(carta);
	}


	public MazoAlGoOh<CartaTrampa>  obtenerCartasTrampaPorAtaque() throws MazoLlenoException {
		return campo.obtenerCartasTrampaPorAtaque();
	}


	public boolean noTieneMonstruosInvocados() {
		return campo.tieneLaCantidadDeMonstruos(0);
	}


	public void incrementarTemporalmentePuntosDeAtaque(CartaMonstruo carta) {

		CartaMonstruo cartaAModificar;
		CambioTemporal cambio = null;

		cartaAModificar = (CartaMonstruo) carta;
		cambio = new AumentoTemporalSobrePuntosDeAtaque(cartaAModificar, new Puntos(500));
		cambio.realizar();
		agregarCambioTemporal(cambio);
		
	}


	public void invocarCartaTrampaBocaAbajo(CartaTrampa cartaTrampa) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		campo.invocarCartaTrampaBocaAbajo(cartaTrampa);
	}


	public void atacarALosPuntosDeVida(CartaMonstruo cartaAtacante, Jugador jugador) throws MazoLlenoException, AtaqueALosPuntosDeVidaInvalidoException, CartaAlGoOhInexistenteException {
	if (verificacionDeCartasTrampa(jugador, cartaAtacante, null))
		cartaAtacante.quitarPuntosDeVidaA(jugador);
	}


	public boolean tieneMazoVacio() {
		return this.campo.tieneMazoVacio();
	}


	public boolean tieneAExodiaEnLaMano() {
		return campo.tieneAExodiaEnLaMano();
	}


    public void activarCartaMagia(CartaMagia cartaMagia, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
        activarEfecto(cartaMagia);
        jugadorPasivo.activarEfectoOponente(cartaMagia);
    }


    public boolean seQuedoSinPuntosDeVida() {
    	return !this.puntosDeVida.sonMayores(new Puntos(0)) ;
    }


	public boolean tieneLaCantidadDeCartasEnElMazo(int cantidad) {
		return this.campo.cantidadDeCartasEnElMazoEs(cantidad);
	}


	public void construiMazoCon(MazoAlGoOh<Carta> cartasPosibles) throws MazoLlenoException, CloneNotSupportedException {
		this.campo.construiMazoCon(cartasPosibles);
	}

	public void ponerCartasEnLaMano(int cantidad) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException {
		for (int i = 0; i < cantidad; i++) {
			ponerCartaEnLaMano();
		}
	}

	//Para MVC
    public String obtenerPuntosDeVida() {
        return this.puntosDeVida.toString() ;
	}


	// para debug
	/*
    public void obtenerTodo() {
		campo.obtenerTodo();
	}*/

	public MazoAlGoOh<Carta> obtenerMano() {
		return this.campo.obtenerMano();
	}


    public CampoDeBatalla getCampo() {
		return campo;
    }


	public void eliminarUnMonstruoInvocado() throws CartaAlGoOhInexistenteException, MazoLlenoException {
		campo.eliminarUnMonstruoInvocado();
	}

	public MazoAlGoOh obtenerCartasMagiaYTrampa() {
		return campo.obtenerCartasMagiaYtrampa();
	}
}