package modelo.cartas.cartasMonstruo;

import modelo.MazoAlGoOh;

public class DragonBlancoDefinitivoDeOjosAzules extends CartaMonstruo{
	
	public DragonBlancoDefinitivoDeOjosAzules(){
		super("Dragón Blanco Definitivo de Ojos Azules", 4500, 3800, 12);
    }

	@Override
    public boolean sonCantidadDeSacrificiosNecesarios(MazoAlGoOh<CartaMonstruo> sacrificios) {
    	return sacrificios.todasLasCartasSon((new DragonBlancoDeOjosAzules()).toString()) && sacrificios.tieneLaCantidadDeCartas(3);
    }
	
}
