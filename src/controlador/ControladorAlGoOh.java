package controlador;

import javafx.scene.control.Button;
import vista.cartas.VistaCartaAlGoOh;

public class ControladorAlGoOh {
	
	private VistaCartaAlGoOh visualizacion;
	
	public ControladorAlGoOh(VistaCartaAlGoOh vista) {
		this.visualizacion = vista;
	}
	
	public Button obtenerVisualizacion() {
		return this.visualizacion.obtenerVisualizacion();
	}
}
