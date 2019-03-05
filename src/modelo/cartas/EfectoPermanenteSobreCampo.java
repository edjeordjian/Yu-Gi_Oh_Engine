package modelo.cartas;

import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public interface EfectoPermanenteSobreCampo extends EfectoSobreCampo{
    void activarEfecto(CartaMonstruo carta) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException;
    void activarEfectoOponente(CartaMonstruo carta) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException;
}
