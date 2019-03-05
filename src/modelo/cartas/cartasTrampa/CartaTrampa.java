package modelo.cartas.cartasTrampa;

import modelo.Jugador;
import modelo.cartas.CartaAlGoOh;
import modelo.cartas.cartasMonstruo.CartaMonstruo;

public abstract class CartaTrampa extends CartaAlGoOh{

	public CartaTrampa(String nombre) { 
		this.nombre = nombre;	
	}
	
	public abstract void activarEfecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugador);
	public abstract boolean permiteContinuarElAtaque();

}
