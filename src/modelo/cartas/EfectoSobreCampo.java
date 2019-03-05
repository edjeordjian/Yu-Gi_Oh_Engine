package modelo.cartas;

import modelo.CampoDeBatalla;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public interface EfectoSobreCampo {

    void activarEfecto(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException;
    void activarEfectoOponente(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException;
}
