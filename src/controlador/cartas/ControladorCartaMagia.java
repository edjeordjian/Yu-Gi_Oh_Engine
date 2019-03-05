package controlador.cartas;

import modelo.cartas.cartasMagia.CartaMagia;

public class ControladorCartaMagia extends ControladorCartaAlGoOh {

	public ControladorCartaMagia(CartaMagia carta) {
		super(carta);
	}

	public CartaMagia obtenerCarta() {
		return (CartaMagia)carta;
	}
	
}
