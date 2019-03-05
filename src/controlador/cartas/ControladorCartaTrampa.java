package controlador.cartas;

import modelo.cartas.cartasTrampa.CartaTrampa;

public class ControladorCartaTrampa  extends ControladorCartaAlGoOh {

	public ControladorCartaTrampa(CartaTrampa carta) {
		super(carta);
	}

	public CartaTrampa obtenerCarta() {
		return (CartaTrampa)carta;
	}
	
}
