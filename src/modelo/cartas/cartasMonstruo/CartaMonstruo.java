package modelo.cartas.cartasMonstruo;

import modelo.Ataque;
import modelo.AtaqueDirectoAlJugador;
import modelo.Jugador;
import modelo.MazoAlGoOh;
import modelo.NivelDeMonstruo;
import modelo.Puntos;
import modelo.cartas.CartaAlGoOh;
import modelo.cartas.cartasMonstruo.posiciones.PosicionCartaMonstruo;
import modelo.cartas.cartasMonstruo.posiciones.PosicionDeAtaque;
import modelo.cartas.cartasMonstruo.posiciones.PosicionDeDefensa;
import modelo.cartas.estados.EnElMazo;
import modelo.cartas.estados.InvocacionBocaArriba;
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;


public abstract class CartaMonstruo extends CartaAlGoOh {

	protected PosicionCartaMonstruo posicion;
	protected Puntos puntosDeAtaque;
	protected Puntos puntosDeDefensa;
	protected NivelDeMonstruo nivel;


	public CartaMonstruo(String nombre, int cantidadPuntosDeAtaque, int cantidadPuntosDeDefensa, int numeroDeNivel) {
		this.nombre = nombre;
		this.puntosDeAtaque = new Puntos(cantidadPuntosDeAtaque);
		this.puntosDeDefensa = new Puntos(cantidadPuntosDeDefensa);
		this.nivel = new NivelDeMonstruo(numeroDeNivel);
		this.posicion = new PosicionDeAtaque(this.puntosDeAtaque);
	}

    public boolean estaEnPosicionDeAtaque(){
        return posicion.esPosicionDeAtaque();
    }
    
    
    public boolean estaEnPosicionDeDefensa(){
        return posicion.esPosicionDeDefensa();
    }
    

    public void activarPosicionDeAtaque() {
    	this.estado = new InvocacionBocaArriba();
    	this.posicion = new PosicionDeAtaque(this.puntosDeAtaque);
    }
    
    public void cambiarAPosicionDeAtaque() {
    	this.posicion = new PosicionDeAtaque(this.puntosDeAtaque);
    }
    
    
    public void activarPosicionDeDefensa() {
    	this.estado = new InvocacionBocaArriba();
    	this.posicion = new PosicionDeDefensa(this.puntosDeDefensa);
    }
    
    
    public void atacarCartaMonstruo(CartaMonstruo cartaAtacada, Jugador jugadorAtacante, Jugador jugadorAtacado) throws MonstruoNoPuedeAtacarException {
    	new Ataque(this.posicion, this, cartaAtacada, jugadorAtacante, jugadorAtacado);
    }

    
    public void recibirAtaque(PosicionCartaMonstruo posicionAtacante, Jugador jugadorAtacante, Jugador jugadorAtacado) throws MonstruoNoPuedeAtacarException {
		posicionAtacante.atacar(this.posicion, jugadorAtacante, jugadorAtacado);
	}

	
	public boolean destruyeA(CartaMonstruo cartaAtacada) {
		return cartaAtacada.esDestruidaPor(this.posicion);
	}


	public boolean esDestruidaPor(PosicionCartaMonstruo posicionAtacante) {
		return posicionAtacante.destruyeA(this.posicion);
	}

    public boolean tieneMenorAtaqueQue(CartaMonstruo otraCarta) {
        return otraCarta.tieneMayorOIgualAtaqueQue(this.puntosDeAtaque);
    }

    private boolean tieneMayorOIgualAtaqueQue(Puntos puntosDeAtaque) {
        return !puntosDeAtaque.sonMayoresOIguales(this.puntosDeAtaque);
    }

    public void aumentarPuntosDeAtaque(Puntos puntos) {
        this.puntosDeAtaque.sumarPuntos(puntos);
        
        if(estaEnPosicionDeAtaque())
        	this.posicion = new PosicionDeAtaque(this.puntosDeAtaque);
    }

    public void aumentarPuntosDeDefensa(Puntos puntos) {
        this.puntosDeDefensa.sumarPuntos(puntos);
        
        if(estaEnPosicionDeDefensa())
        	this.posicion = new PosicionDeDefensa(this.puntosDeDefensa);
    }

    public boolean sonCantidadDeSacrificiosNecesarios(MazoAlGoOh<CartaMonstruo> sacrificios) {
    	return this.nivel.sonCantidadSuficienteDeSacrificios(sacrificios);
    }
    
	public void quitarPuntosDeVidaA(Jugador jugador) throws AtaqueALosPuntosDeVidaInvalidoException{
		if(! jugador.noTieneMonstruosInvocados()){
			throw new AtaqueALosPuntosDeVidaInvalidoException();
		}
		
		quitarPuntosDeVidaDirectamenteA(jugador);
	}

	
	public void quitarPuntosDeVidaDirectamenteA(Jugador jugador) {	
		new AtaqueDirectoAlJugador(jugador, this.puntosDeAtaque);
	}


	public void disminuirPuntosDeAtaque(Puntos puntos) {
		 this.puntosDeAtaque.restarPuntos(puntos);
	}
    
	
	public boolean tienePuntosDeAtaque(Puntos otrosPuntos) {
		return this.puntosDeAtaque.sonIguales(otrosPuntos);
	}
	
	
	public boolean tienePuntosDeDefensa(Puntos otrosPuntos) {
		return this.puntosDeDefensa.sonIguales(otrosPuntos);
	}

    public void cambiarDePosicion() {
    	this.posicion = posicion.cambiarDePosicion(this.puntosDeAtaque, this.puntosDeDefensa);
	}


	public void cambiarAPosicionDeDefensa() {
		this.posicion = new PosicionDeDefensa(this.puntosDeDefensa);
		 }

    public String getPuntosDeAtaque() {return  this.puntosDeAtaque.toString();}

	public String getPuntosDeDefensa() {return this.puntosDeDefensa.toString();}

	@Override
	public CartaMonstruo newInstance() throws CloneNotSupportedException {
		CartaMonstruo carta = (CartaMonstruo) this.clone();
		carta.puntosDeDefensa = new Puntos(this.puntosDeDefensa.getCantidad());
		carta.puntosDeAtaque = new Puntos(this.puntosDeAtaque.getCantidad());
		carta.posicion = new PosicionDeAtaque(carta.puntosDeAtaque);
		carta.nivel = new NivelDeMonstruo(this.nivel.getNivel());
		carta.estado = new EnElMazo();
    	return carta;
	}
}
