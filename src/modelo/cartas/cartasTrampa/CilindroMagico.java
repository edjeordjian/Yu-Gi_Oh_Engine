package modelo.cartas.cartasTrampa;

import modelo.Jugador;
import modelo.cartas.cartasMonstruo.CartaMonstruo;

public class CilindroMagico extends CartaTrampa {
    
	public CilindroMagico(){ 
		super("Cilindro Magico"); 
	}
	
	@Override
    public boolean permiteContinuarElAtaque() {
    	return false;
    }

    @Override
	public void activarEfecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugador){
		cartaAtacante.quitarPuntosDeVidaDirectamenteA(jugador);
	}
    
}