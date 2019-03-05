package controlador.cartas;

import javafx.scene.control.Button;
import modelo.cartas.CartaAlGoOh;
import vista.cartas.VistaCartaAlGoOh;
import vista.cartas.VistaCartaBocaAbajo;

public class ControladorCartaAlGoOh {

	protected CartaAlGoOh carta;
	protected VistaCartaAlGoOh visualizacion;
	protected boolean estaBocaAbajo = false;
	
	public ControladorCartaAlGoOh(CartaAlGoOh carta) {
		this.carta = carta;
		this.estaBocaAbajo = false;
		this.visualizacion = new VistaCartaAlGoOh(this.carta);
	}


	@Override
	public String toString() {
		return carta.toString();
	}

	public Button obtenerVisualizacion() {
		return this.visualizacion.obtenerVisualizacion();
	}
	
	public void ponerBocaAbajo() {
		this.estaBocaAbajo = true;
		this.visualizacion = new VistaCartaBocaAbajo();
	}
	
	public void ponerBocaArriba() {
		this.estaBocaAbajo = false;
		this.visualizacion = new VistaCartaAlGoOh(this.carta);
	}
	
	public CartaAlGoOh obtenerCarta() {
		return this.carta;
	}
	
	public boolean estaBocaAbajo() {
		return estaBocaAbajo;
	}
}
