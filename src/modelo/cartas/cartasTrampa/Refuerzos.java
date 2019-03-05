package modelo.cartas.cartasTrampa;

import modelo.Jugador;
import modelo.cartas.cartasMonstruo.CartaMonstruo;

public class Refuerzos extends CartaTrampa {
	    
	public Refuerzos(){ 
		super("Refuerzos"); 
	}
	
	@Override
    public boolean permiteContinuarElAtaque() {
    	return true;
    }


    @Override
	public void activarEfecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugador){
		jugador.incrementarTemporalmentePuntosDeAtaque(cartaAtacada);
	}

}
