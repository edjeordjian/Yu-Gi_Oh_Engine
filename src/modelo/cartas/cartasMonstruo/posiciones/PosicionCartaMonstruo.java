package modelo.cartas.cartasMonstruo.posiciones;

import modelo.Jugador;
import modelo.Puntos;
import modelo.excepciones.MonstruoNoPuedeAtacarException;

public interface PosicionCartaMonstruo {

	boolean esPosicionDeAtaque();
	boolean esPosicionDeDefensa();
	void atacar(PosicionCartaMonstruo posicionAtacado, Jugador jugadorAtacante, Jugador jugadorAtacado) throws MonstruoNoPuedeAtacarException;
	Puntos recibirAtaque(Puntos puntosAtacante, Jugador jugadorAtacado);
	boolean destruyeA(PosicionCartaMonstruo posicion);
	boolean esDestruidoPor(Puntos puntos);
    PosicionCartaMonstruo cambiarDePosicion(Puntos puntosDeAtaque, Puntos puntosDeDefensa);
}
