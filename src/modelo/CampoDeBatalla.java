package modelo;


import modelo.cartas.Carta;
import modelo.cartas.CartaAlGoOh;
import modelo.cartas.EfectoSobreCampo;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.BrazoDerechoExodia;
import modelo.cartas.cartasMonstruo.BrazoIzquierdoExodia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CuerpoExodia;
import modelo.cartas.cartasMonstruo.PiernaDerechaExodia;
import modelo.cartas.cartasMonstruo.PiernaIzquierdaExodia;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.SacrificioInvalidoException;


public class CampoDeBatalla {	
	private MazoAlGoOh<Carta> mazo;
	private MazoAlGoOh<Carta> mano;
	private MazoAlGoOh<CartaMonstruo> cartasMonstruoInvocadas;
	private MazoAlGoOh<Carta> cartasMagiaYTrampaInvocadas;
	private MazoAlGoOh<Carta> cementerio;
	private MazoAlGoOh<CartaCampo> cartaCampoInvocada;

	public CampoDeBatalla() {
		this.mazo =  new MazoAlGoOh<Carta>(Constantes.CANTIDAD_DE_CARTAS_MAZO);
		this.mano = new MazoAlGoOh<Carta>(Constantes.CANTIDAD_DE_CARTAS_MAZO);
		this.cementerio = new MazoAlGoOh<Carta>(Constantes.CANTIDAD_DE_CARTAS_MAZO);
		this.cartasMonstruoInvocadas = new MazoAlGoOh<CartaMonstruo>(Constantes.MAX_CARTAS_MONSTRUO_INVOCABLES);
		this.cartasMagiaYTrampaInvocadas = new MazoAlGoOh<Carta>(Constantes.MAX_CARTAS_MAGIA_Y_TRAMPA_INVOCABLES);
		this.cartaCampoInvocada = new MazoAlGoOh<CartaCampo>(Constantes.MAX_CARTAS_CAMPO_INVOCABLES);
	}
	
	
	public void agregarCartaAlMazo(Carta carta) throws MazoLlenoException {
		this.mazo.agregarCarta(carta);
		carta.pasarAlMazo();
	}
	
	
	private void realizarSacrificios(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws SacrificioInvalidoException, CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		verificarSacrificios(carta, cartasASacrificar);
		eliminarCartas(cartasASacrificar);
		activarEfectoPorCampo(carta);
	}
	
	public void activarEfectoPorCampo(CartaMonstruo carta) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		if(!this.cartaCampoInvocada.estaVacio()) {
			for(CartaCampo campo: cartaCampoInvocada) {
				campo.activarEfecto(carta);
			}
		}
	}
	
	public void activarEnOponenteEfectoPorCampo(CartaMonstruo carta) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		if(!this.cartaCampoInvocada.estaVacio()) {
			for(CartaCampo campo: cartaCampoInvocada) {
				campo.activarEfectoOponente(carta);
			}
		}
	}
	
	
	
	public void ponerCartaEnLaMano() throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException{
		Carta carta = mazo.obtenerCartaTope();
		this.mano.agregarCarta(carta);
		carta.pasarALaMano();
	}
	
	
	private void invocarCarta(Carta carta, MazoAlGoOh lugarDeInvocacion) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		if (! this.mano.tieneLaCarta(carta))
			throw new CartaAlGoOhInexistenteException();
		
		lugarDeInvocacion.agregarCarta(carta);
		mano.removerCarta(carta);
	}
	
	
	private void verificarSacrificios(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws SacrificioInvalidoException{
		if (!carta.sonCantidadDeSacrificiosNecesarios(cartasASacrificar))
			throw new SacrificioInvalidoException();	
	}
		
	
	public void invocarCartaMonstruoEnPosicionDeAtaque(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException {
		realizarSacrificios(carta, cartasASacrificar);
		invocarCarta(carta, cartasMonstruoInvocadas);
		carta.activarPosicionDeAtaque();
		carta.activarInvocacionBocaArriba(null);
	}
	
	
	public boolean cartaMonstruoEstaEnPosicionDeAtaque(CartaMonstruo carta){
		return carta.estaEnPosicionDeAtaque();
	}

	
	public void invocarCartaMonstruoEnPosicionDeDefensa(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException {
		realizarSacrificios(carta, cartasASacrificar);
		invocarCarta(carta, cartasMonstruoInvocadas);
		carta.activarPosicionDeDefensa();
		carta.activarInvocacionBocaArriba(null);
	}
	
	
	public void invocarCartaMonstruEnPosicionDeDefensaBocaAbajo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException {
		invocarCartaMonstruoEnPosicionDeDefensa(carta, cartasASacrificar);
		carta.activarInvocacionBocaAbajo();
	}
	
	
	public boolean cartaMonstruoEstaEnPosicionDeDefensa(CartaMonstruo carta){
		return carta.estaEnPosicionDeDefensa();
	}
	
	
	public void invocarCartaMagiaBocaAbajo(CartaAlGoOh carta) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		invocarCarta(carta, cartasMagiaYTrampaInvocadas);
		carta.activarInvocacionBocaAbajo();
	}

	
	public void invocarCartaTrampaBocaAbajo(CartaTrampa carta) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		invocarCarta(carta, cartasMagiaYTrampaInvocadas);
		carta.activarInvocacionBocaAbajo();
	}
	
	
	public boolean cartaEstaInvocadaBocaAbajo(Carta carta) {
		return carta.estaInvocadaBocaAbajo();
	}
	
	
	public void enviarCartaAlCementerio(Carta carta) throws CartaAlGoOhInexistenteException, MazoLlenoException{
		try {
			cementerio.agregarCarta(carta);


			if (cartasMagiaYTrampaInvocadas.tieneLaCarta(carta))
				cartasMagiaYTrampaInvocadas.removerCarta(carta);

			else if (cartaCampoInvocada.tieneLaCarta(carta))
				cartasMagiaYTrampaInvocadas.removerCarta(carta);

			else if (cartasMonstruoInvocadas.tieneLaCarta(carta))
				cartasMonstruoInvocadas.removerCarta((CartaMonstruo)carta);
			else if (mano.tieneLaCarta(carta))
				mano.removerCarta(carta);
				
			carta.pasarAlCementerio();
		}

		catch (CartaAlGoOhInexistenteException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean cartaEstaEnElCementerio(Carta carta) {
		return carta.estaEnElCementerio();
	}

	public void invocarCartaMagiaBocaArriba(CartaMagia cartaMagia) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		invocarCarta(cartaMagia, cartasMagiaYTrampaInvocadas);
		cartaMagia.activarInvocacionBocaArriba(null);
	}


	public boolean tieneLaCantidadDeMonstruos(int cantidad) {
		return cartasMonstruoInvocadas.tieneLaCantidadDeCartas(cantidad);
	}

	
	public boolean tieneLaCantidadDeCartasEnLaMano(int cantidad) {
		return mano.tieneLaCantidadDeCartas(cantidad);
	}
	
	
	private void eliminarCartas(MazoAlGoOh cartas) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		while(!cartas.estaVacio()) {
			enviarCartaAlCementerio(cartas.obtenerCartaTope());
		}
	}
	
	
	public void eliminarTodasLasCartasMonstruo() throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		eliminarCartas(cartasMonstruoInvocadas);
	}

	
	public void activarEfecto(EfectoSobreCampo cartaConEfecto) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException{
		cartaConEfecto.activarEfecto(this);
		enviarCartaAlCementerio((CartaAlGoOh)cartaConEfecto);
	}

	
	public void activarEfectoCartaCampo(EfectoSobreCampo cartaConEfecto) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException{
		cartaConEfecto.activarEfecto(this);
	}
	
	
    public void activarEfectoOponente(EfectoSobreCampo cartaConEfecto) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
	    cartaConEfecto.activarEfectoOponente(this);
    }

    
    public MazoAlGoOh<CartaMonstruo> obtenerMonstruosInvocadosBocaArriba() throws MazoLlenoException {
        return this.cartasMonstruoInvocadas.obtenerMonstruosInvocadosBocaArriba();
    }

    public MazoAlGoOh<CartaMonstruo> obtenerMonstruosInvocados() {
	    return this.cartasMonstruoInvocadas;
    }

    public void invocarCartaCampoBocaArriba(CartaCampo cartaCampo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
        invocarCarta(cartaCampo, cartaCampoInvocada);
        cartaCampo.activarInvocacionBocaArriba(null);
        activarEfectoCartaCampo(cartaCampo);
    }


	public MazoAlGoOh<CartaTrampa> obtenerCartasTrampaPorAtaque() throws MazoLlenoException {
		
		MazoAlGoOh<CartaTrampa> cartasTrampa = new MazoAlGoOh<CartaTrampa>(Constantes.MAX_CARTAS_MAGIA_Y_TRAMPA_INVOCABLES);
		CartaTrampa cartaTrampa;
		
		for (Carta carta : cartasMagiaYTrampaInvocadas) {
			try {
				cartaTrampa = (CartaTrampa) carta;
				cartasTrampa.agregarCarta(cartaTrampa);
			}
			
			catch (ClassCastException e){
				continue;
			}
        }
		
		return cartasTrampa;
	}


	public boolean tieneMazoVacio() {
		return this.mazo.estaVacio();
	}


	public boolean tieneAExodiaEnLaMano() {
		return 
				this.mano.tieneLaCarta( ( new BrazoIzquierdoExodia() ).toString() ) &&
				this.mano.tieneLaCarta( ( new BrazoDerechoExodia() ).toString() ) &&
				this.mano.tieneLaCarta( ( new PiernaDerechaExodia() ).toString() ) &&
				this.mano.tieneLaCarta( ( new PiernaIzquierdaExodia() ).toString() ) &&
				this.mano.tieneLaCarta( ( new CuerpoExodia() ).toString() );
	}


	public void construiMazoCon(MazoAlGoOh<Carta> cartasPosibles) throws MazoLlenoException, CloneNotSupportedException {
		this.mazo.agregarCartasAleatoriamente(cartasPosibles, Constantes.CANTIDAD_DE_CARTAS_MAZO);
	}


	public boolean cantidadDeCartasEnElMazoEs(int cantidad) {
		return this.mazo.tieneLaCantidadDeCartas(cantidad);
	}

    public void invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException {
		invocarCartaMonstruoEnPosicionDeAtaque(carta, sacrificios);
		carta.activarInvocacionBocaAbajo();
    }


    // para debug
	/*
    public void obtenerTodo() {
		System.out.println("	Cartas en mano:");
		for (Carta carta : this.mano) {
			System.out.println("		" + carta.toString() + ", " + carta.getEstado());
		}
		System.out.println("	Monstruos invocados:");
		for (CartaMonstruo carta : this.cartasMonstruoInvocadas) {
			System.out.println("		" + carta.toString() + ", " + carta.getEstado() + ", " + carta.getPuntosDeAtaque() + ", " + carta.getPuntosDeDefensa());
		}
		System.out.println("	Magia y trampa invocados:");
		for (Carta carta : this.cartasMagiaYTrampaInvocadas) {
			System.out.println("		" + carta.toString() + ", " + carta.getEstado());
		}
		System.out.println("	Campo invocados:");
		for (Carta carta : this.cartaCampoInvocada) {
			System.out.println("		" + carta.toString() + ", " + carta.getEstado());
		}
		System.out.println("	Cementerio:");
		for (Carta carta : this.cementerio) {
			System.out.println("		" + carta.toString() + ", " + carta.getEstado());
		}
		System.out.println("	Mazo:");
		for (Carta carta : this.mazo) {
			System.out.println("		" + carta.toString() + ", " + carta.getEstado());
		}
	}*/

    
    //PARA MVC
	public MazoAlGoOh<Carta> obtenerMazo() {
		return this.mazo;
	}


	public MazoAlGoOh<Carta> obtenerMano() {
		return this.mano;
	}


	public void eliminarUnMonstruoInvocado() throws CartaAlGoOhInexistenteException, MazoLlenoException {
		try {
			enviarCartaAlCementerio(cartasMonstruoInvocadas.obtenerCartaTope());
		} 
		
		catch (MazoVacioException e) {
			return;
		}
	}


	public MazoAlGoOh obtenerCartasMagiaYtrampa() {
		return cartasMagiaYTrampaInvocadas;
	}
}
