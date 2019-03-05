package controlador;

import controlador.cartas.ControladorCartaAlGoOh;
import javafx.scene.layout.HBox;
import vista.cartas.VistaCartaVacia;

public class ControladorCementerio extends ControladorMazoAlGoOh {

	
	public ControladorCementerio(int cantidad) {
		super(cantidad);
		this.representacionCartas.getChildren().add((new VistaCartaVacia()).obtenerVisualizacion());
		
	}
	
	public HBox obtenerRepresentacion() {
		return this.representacionCartas;
	}

	
	public void agregarCarta(ControladorCartaAlGoOh carta) {
		this.representacionCartas.getChildren().remove(0);
		this.representacionCartas.getChildren().add(carta.obtenerVisualizacion());
	}

}
