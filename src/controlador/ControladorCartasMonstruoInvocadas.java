package controlador;

import javafx.scene.layout.HBox;

public class ControladorCartasMonstruoInvocadas extends ControladorMazoAlGoOh{
	
	public ControladorCartasMonstruoInvocadas(int cantidad) {
		super(cantidad);
	}

	public HBox obtenerRepresentacion() {
		return this.representacionCartas;
	}

}
