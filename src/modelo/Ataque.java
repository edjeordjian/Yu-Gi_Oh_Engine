package modelo;

import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.posiciones.PosicionCartaMonstruo;
import modelo.excepciones.MonstruoNoPuedeAtacarException;

public class Ataque {
	
	public Ataque(PosicionCartaMonstruo posicionAtacante, CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorAtacante, Jugador jugadorAtacado) throws MonstruoNoPuedeAtacarException {
		
		cartaAtacada.recibirAtaque(posicionAtacante, jugadorAtacante, jugadorAtacado);
		
		if (cartaAtacante.destruyeA(cartaAtacada))
			jugadorAtacado.enviarAlCementerio(cartaAtacada);
		
		if (cartaAtacada.destruyeA(cartaAtacante))
			jugadorAtacante.enviarAlCementerio(cartaAtacante);

		
	}
}
