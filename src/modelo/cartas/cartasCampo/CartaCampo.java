package modelo.cartas.cartasCampo;

import modelo.CampoDeBatalla;
import modelo.MazoAlGoOh;
import modelo.cartas.CartaAlGoOh;
import modelo.cartas.EfectoPermanenteSobreCampo;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public abstract class CartaCampo extends CartaAlGoOh implements EfectoPermanenteSobreCampo {
	
	public CartaCampo(String nombre) { 
		this.nombre = nombre;	
	}
	
    @Override
    public void activarEfecto(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
        MazoAlGoOh<CartaMonstruo> monstruos = campo.obtenerMonstruosInvocados();
    	for (CartaMonstruo carta : monstruos) {
    		activarEfecto(carta);
        }
    }

    @Override
    public void activarEfectoOponente(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
    	MazoAlGoOh<CartaMonstruo> monstruos = campo.obtenerMonstruosInvocados();
    	for (CartaMonstruo carta : monstruos) {  
    		activarEfectoOponente(carta);
    		}
    }
    
}
