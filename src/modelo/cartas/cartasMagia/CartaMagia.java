package modelo.cartas.cartasMagia;

import modelo.cartas.CartaAlGoOh;
import modelo.cartas.EfectoSobreCampo;

public abstract class CartaMagia extends CartaAlGoOh implements EfectoSobreCampo {
   
	public CartaMagia(String nombre) { 
		this.nombre = nombre;	
	}	

}
