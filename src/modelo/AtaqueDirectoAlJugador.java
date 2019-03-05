package modelo;

public class AtaqueDirectoAlJugador {

	public AtaqueDirectoAlJugador(Jugador jugador, Puntos puntosAQuitar) {
		jugador.quitarPuntosDeVida(puntosAQuitar);
	}

}
