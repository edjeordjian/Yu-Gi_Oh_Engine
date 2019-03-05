package modelo.cartas.cartasMonstruo.posiciones;

import modelo.Jugador;
import modelo.Puntos;

public class PosicionDeAtaque implements PosicionCartaMonstruo {

	private Puntos puntos;
	
	public PosicionDeAtaque(Puntos puntosDeAtaque) {
		this.puntos = puntosDeAtaque;
	}


	public boolean esPosicionDeAtaque() {
    	return true;
    }

    
    public boolean esPosicionDeDefensa() { 
    	return false;
    }

    @Override
	public Puntos recibirAtaque(Puntos puntosAtacante, Jugador jugadorAtacado) {
		Puntos diferencia = puntosAtacante.obtenerDiferenciaCon(this.puntos);
		
		if (puntosAtacante.sonMayores(this.puntos)) {
			jugadorAtacado.quitarPuntosDeVida(diferencia);
			return new Puntos(0);
		}
		
		return diferencia;
	}
    
	@Override
	public void atacar(PosicionCartaMonstruo posicionDefensor, Jugador jugadorAtacante, Jugador jugadorAtacado) {
		Puntos vidaPerdida = posicionDefensor.recibirAtaque(this.puntos, jugadorAtacado);
		jugadorAtacante.quitarPuntosDeVida(vidaPerdida);
	}


	@Override
	public boolean destruyeA(PosicionCartaMonstruo posicionDefensor) {
		return posicionDefensor.esDestruidoPor(this.puntos);
	}


	@Override
	public boolean esDestruidoPor(Puntos puntos) {
		return puntos.sonMayoresOIguales(this.puntos);
	}

	@Override
	public PosicionCartaMonstruo cambiarDePosicion(Puntos puntosDeAtaque, Puntos puntosDeDefensa) {
		return new PosicionDeDefensa(puntosDeDefensa);
	}

}
