package controlador.cartas;


import modelo.cartas.cartasCampo.CartaCampo;

public class ControladorCartaCampo extends ControladorCartaAlGoOh {

	public ControladorCartaCampo(CartaCampo carta) {
		super(carta);
	}

	public CartaCampo obtenerCarta() {
		return (CartaCampo) carta;
	}
	
}
