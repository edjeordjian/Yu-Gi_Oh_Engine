package modelo.cartas.cartasMagia;

import modelo.CampoDeBatalla;
import modelo.MazoAlGoOh;
import modelo.cartas.EfectoSobreCampo;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public class Fisura extends CartaMagia implements EfectoSobreCampo {
    
	public Fisura(){ 
		super("Fisura"); 
	}
	
    @Override
    public void activarEfecto(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {

    }

    @Override
    public void activarEfectoOponente(CampoDeBatalla campo) throws CartaAlGoOhInexistenteException, MazoLlenoException {
        MazoAlGoOh<CartaMonstruo> monstruos = campo.obtenerMonstruosInvocadosBocaArriba();
        try {
        CartaMonstruo min = monstruos.obtenerCartaTope();
        
        for (CartaMonstruo carta : monstruos) {
            if (carta.tieneMenorAtaqueQue(min)) 
            	min = carta;
        }
        
        campo.enviarCartaAlCementerio(min);
        
        }
        
        catch(MazoVacioException e)
        {
        	return;
        }
        
    }
   
}
