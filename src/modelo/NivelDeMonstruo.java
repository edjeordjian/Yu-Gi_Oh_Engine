package modelo;

import modelo.cartas.cartasMonstruo.CartaMonstruo;

public class NivelDeMonstruo {
	
    private int numeroDeNivel;

    public NivelDeMonstruo(int nivel) {
        this.numeroDeNivel = nivel;
    }

    private int obtenerCantidadDeSacrificiosRequeridos() {
        if (this.numeroDeNivel <  Constantes.NIVEL_PARA_UN_SACRIFICIO)
        	return 0;
        else if (this.numeroDeNivel >=  Constantes.NIVEL_PARA_UN_SACRIFICIO && this.numeroDeNivel < Constantes.NIVEL_PARA_DOS_SACRIFICIOS)
        	return 1;
        
        return 2;
    }


    public boolean sonCantidadSuficienteDeSacrificios(MazoAlGoOh<CartaMonstruo> sacrificios) {
    	return sacrificios.tieneLaCantidadDeCartas(obtenerCantidadDeSacrificiosRequeridos());
    }

    public int getNivel() {
        return this.numeroDeNivel;
    }
}
