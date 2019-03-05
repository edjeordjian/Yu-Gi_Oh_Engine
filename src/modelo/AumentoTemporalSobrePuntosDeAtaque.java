package modelo;

import modelo.cartas.cartasMonstruo.CartaMonstruo;

public class AumentoTemporalSobrePuntosDeAtaque implements CambioTemporal{
	
	private CartaMonstruo carta;
	private Puntos puntos;

	public  AumentoTemporalSobrePuntosDeAtaque(CartaMonstruo carta, Puntos puntos) {
		this.carta = carta;
		this.puntos = puntos;
	}
	
	public void realizar() {
		this.carta.aumentarPuntosDeAtaque(this.puntos);
	}

	
	public void revertir() {
		this.carta.disminuirPuntosDeAtaque(this.puntos);
	}
}
