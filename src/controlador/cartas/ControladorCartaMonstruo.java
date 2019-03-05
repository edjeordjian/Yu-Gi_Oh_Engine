package controlador.cartas;

import modelo.cartas.cartasMonstruo.CartaMonstruo;
public class ControladorCartaMonstruo extends ControladorCartaAlGoOh{

	boolean estaOscura;
	boolean estaRotada ;
	
	public ControladorCartaMonstruo(CartaMonstruo carta) {
		super(carta);
		estaRotada = false;
		estaOscura = false;
	}

	public void rotar() {
		this.visualizacion.rotar();
		estaRotada = true;
	}

	public void ponerEnModoNormal() {
		this.visualizacion.ponerEnModoNormal();
		estaRotada = false;
	}

	
	public CartaMonstruo obtenerCarta() {
		return (CartaMonstruo)carta;
	}

	public void oscurecer() {
		this.visualizacion.oscurecer();
		estaOscura = true;
	}

	public void cambiarAColorNormal() {
		this.visualizacion.cambiarAColorNormal();
		estaOscura = false;
	}
	
	public boolean estaOscura() {
		return estaOscura;
	}

	public boolean estaRotada() {
		return this.estaRotada;
	}
}
