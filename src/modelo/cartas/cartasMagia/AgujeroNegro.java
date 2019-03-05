package modelo.cartas.cartasMagia;

import modelo.CampoDeBatalla;
import modelo.cartas.EfectoSobreCampo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public class AgujeroNegro extends CartaMagia implements EfectoSobreCampo {
	
	public AgujeroNegro(){ 
		super("Agujero Negro"); 
	}
	
	public void activarEfecto(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		campo.eliminarTodasLasCartasMonstruo();
	}

	@Override
	public void activarEfectoOponente(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		campo.eliminarTodasLasCartasMonstruo();
	}

}
