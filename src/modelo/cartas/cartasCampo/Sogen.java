package modelo.cartas.cartasCampo;

import modelo.Puntos;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public class Sogen extends CartaCampo {
    
	public Sogen(){ 
    	super("Sogen"); 
    }

	@Override
	public void activarEfecto(CartaMonstruo carta) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
        carta.aumentarPuntosDeDefensa(new Puntos(500));
	}

	@Override
	public void activarEfectoOponente(CartaMonstruo carta)
			throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		carta.aumentarPuntosDeAtaque(new Puntos(200));
	}
	

}
