package modelo;

import java.util.Random;

public class Turno {

   private Jugador jugadorActivo;
   private Jugador jugadorPasivo;

    public Turno(Jugador unJugador, Jugador otroJugador) {
    	jugadorActivo = unJugador;
    	jugadorPasivo = otroJugador;
    	
        if (new Random().nextBoolean()) {
        	jugadorActivo = otroJugador;
        	jugadorPasivo = unJugador;
        }
    }

    
    public Jugador obtenerJugadorActivo() {
        return this.jugadorActivo;
    }

    
    public Jugador obtenerJugadorPasivo() {
        return this.jugadorPasivo;
    }

    
    public void terminarTurno(Jugador jugadorActivo) {
    	this.jugadorActivo = this.jugadorPasivo;
    	this.jugadorPasivo = jugadorActivo;
    }
    
}
