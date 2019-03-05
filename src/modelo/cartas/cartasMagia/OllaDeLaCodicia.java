package modelo.cartas.cartasMagia;

import modelo.CampoDeBatalla;
import modelo.cartas.EfectoSobreCampo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public class OllaDeLaCodicia extends CartaMagia implements EfectoSobreCampo {
    
	public OllaDeLaCodicia(){ 
		super("Olla de la Codicia"); 
	}
	
    @Override
    public void activarEfecto(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException {
    	try {
        campo.ponerCartaEnLaMano();
        campo.ponerCartaEnLaMano();
    	}
    	catch(MazoVacioException e) {
    		return;
    	}
     }

    @Override
    public void activarEfectoOponente(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {

    }
}
