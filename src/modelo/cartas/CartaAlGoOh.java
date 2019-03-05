package modelo.cartas;


import modelo.Jugador;
import modelo.cartas.estados.EnElCementerio;
import modelo.cartas.estados.EnElMazo;
import modelo.cartas.estados.EnLaMano;
import modelo.cartas.estados.EstadoCartaAlGoOh;
import modelo.cartas.estados.InvocacionBocaAbajo;
import modelo.cartas.estados.InvocacionBocaArriba;

public abstract class CartaAlGoOh implements Carta{

	protected EstadoCartaAlGoOh estado = new EnElMazo();
	protected String nombre = "NO ESPECIFICADO";
	
	public void activarInvocacionBocaAbajo() {
		 estado = new InvocacionBocaAbajo();
	}
	 
	
	public boolean tieneNombre(String unNombre) {
		return this.nombre.equals(unNombre);
	}
	
	
	@Override
	public String toString() {
		return nombre;
	}


	public boolean estaInvocadaBocaAbajo() {
		 return estado.estaInvocadaBocaAbajo();
	}
	 
	 
	public void activarInvocacionBocaArriba(Jugador jugadorPasivo) {
		 estado = new InvocacionBocaArriba();
	}
	 
	 
	public boolean estaInvocadaBocaArriba() {
		return estado.estaInvocadaBocaArriba();
	}
	
	
	public boolean estaEnElCementerio() {
		return estado.estaEnElCementerio();
	}
	
	 
	public void pasarAlMazo() {
	    this.estado = new EnElMazo();
	}
	 
	 
	public void pasarALaMano() {
		this.estado = new EnLaMano();
	}

	public void pasarAlCementerio() {
		this.estado = new EnElCementerio();
	}


    public String getEstado() {return this.estado.toString();}

    @Override
    public CartaAlGoOh newInstance() throws CloneNotSupportedException{
	    CartaAlGoOh carta = (CartaAlGoOh) this.clone();
	    carta.estado = new EnElMazo();
	    return carta;
    }

    public boolean estaEnLaMano() {
    	return estado.estaEnLaMano();
    }

}

