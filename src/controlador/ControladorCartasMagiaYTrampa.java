package controlador;

import javafx.scene.layout.HBox;

public class ControladorCartasMagiaYTrampa extends ControladorMazoAlGoOh{
	
	
	public ControladorCartasMagiaYTrampa(int cantidad) {
		super(cantidad);
	}

	@Override
	public HBox obtenerRepresentacion() {
		return this.representacionCartas;
	}
}
