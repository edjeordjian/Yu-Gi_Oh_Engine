package modelo;

public class Puntos {
	
	private int cantidad;
	
	
	public Puntos(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	@Override
	public String toString() {
		return Integer.toString(this.cantidad);
	}


	public boolean sonMayoresOIguales(Puntos otroPuntos) {
		return this.cantidad >= otroPuntos.cantidad;
	}
	
	public boolean sonMayores(Puntos otrosPuntos) {
		return (this.cantidad > otrosPuntos.cantidad);
	}

	
	public Puntos obtenerDiferenciaCon(Puntos otrosPuntos) {
		return new Puntos(Math.abs(this.cantidad - otrosPuntos.cantidad));
	}
	
	
	public boolean sonIguales(Puntos otrosPuntos) {
		return this.cantidad == otrosPuntos.cantidad;
	}


	public boolean sonMenores(Puntos otrosPuntos) {
		return !sonMayores(otrosPuntos) && !sonIguales(otrosPuntos);
	}

	
	public boolean sonNegativos(Puntos otrosPuntos) {
		return otrosPuntos.sonMenores(new Puntos(0));
	}
	
	
	public void restarPuntos(Puntos otroPunto) {
		this.cantidad = this.cantidad - otroPunto.cantidad;
	}

    public void sumarPuntos(Puntos puntos) {
		this.cantidad = this.cantidad + puntos.cantidad;
    }

    public int getCantidad() {
		return this.cantidad;
    }
}
