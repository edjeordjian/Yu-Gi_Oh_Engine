package modelo.cartas.cartasMonstruo;

import modelo.AtaqueDirectoAlJugador;
import modelo.Jugador;

public class Jinzo7 extends CartaMonstruo{
    
	public Jinzo7(){
    	super("Jinzo7", 500, 400, 2);
    }
    
	
	public void quitarPuntosDeVidaA(Jugador jugador){
		new AtaqueDirectoAlJugador(jugador, this.puntosDeAtaque);
    }
	
}
