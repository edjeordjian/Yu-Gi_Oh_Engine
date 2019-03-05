package modelo.cartas.cartasMonstruo.posiciones;

import modelo.Jugador;
import modelo.Puntos;
import modelo.excepciones.MonstruoNoPuedeAtacarException;

public class PosicionDeDefensa implements PosicionCartaMonstruo {

	
	private Puntos puntos;
	
    public PosicionDeDefensa(Puntos puntosDeDefensa) {
		this.puntos = puntosDeDefensa;
	}


	public boolean esPosicionDeAtaque() { 
    	return false; 
    }

    
    public boolean esPosicionDeDefensa(){
        return true;
    }

    @Override
    public Puntos recibirAtaque(Puntos puntosAtacante, Jugador jugadorAtacado){
    	return new Puntos(0);
    }

	@Override
	public void atacar(PosicionCartaMonstruo posicionAtacado, Jugador jugadorAtacante, Jugador jugadorAtacado) throws MonstruoNoPuedeAtacarException {
		throw new MonstruoNoPuedeAtacarException();
	}

	
	@Override
	public boolean destruyeA(PosicionCartaMonstruo posicionDefensor) {
		return false;
	}


	@Override
	public boolean esDestruidoPor(Puntos puntos) {
		return puntos.sonMayores(this.puntos);
	}

	@Override
	public PosicionCartaMonstruo cambiarDePosicion(Puntos puntosDeAtaque, Puntos puntosDeDefensa) {
		return new PosicionDeAtaque(puntosDeAtaque);
	}

}
