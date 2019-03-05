package controlador;

import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaCampo;
import javafx.scene.layout.HBox;
import vista.cartas.VistaCartaVacia;

public class ControladorMazoDeCampo extends ControladorMazoAlGoOh {


	public
	ControladorMazoDeCampo(int cantidad) {
		super(cantidad);
		this.representacionCartas.getChildren().add((new VistaCartaVacia()).obtenerVisualizacion());
		
	}
	
	public HBox obtenerRepresentacion() {
		return this.representacionCartas;
	}

	
	public void agregarCarta(ControladorCartaAlGoOh carta) {
		ControladorCartaCampo cartaCampo = (ControladorCartaCampo) carta;
		this.controladoresCartas.add(carta);
		this.representacionCartas.getChildren().clear();
		this.representacionCartas.getChildren().add(cartaCampo.obtenerVisualizacion());
	}

}
