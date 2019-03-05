package controlador;

import  javafx.scene.control.Button;
import modelo.MazoAlGoOh;
import modelo.cartas.Carta;
import vista.cartas.VistaCartaAlGoOh;
import vista.cartas.VistaMazo;

public class ControladorMazo extends ControladorMazoAlGoOh{

	protected VistaCartaAlGoOh representacionCartas;
	
	public ControladorMazo(int cantidad) {
		super(cantidad);
		representacionCartas = new VistaMazo();
	}

	public ControladorMazo(MazoAlGoOh<Carta> mazo) {
		super(mazo);
		representacionCartas = new VistaMazo();
	}


	public Button obtenerRepresentacion() {
		return this.representacionCartas.obtenerVisualizacion();
	}

}
