package controlador;

import javafx.scene.control.ScrollPane;
import modelo.MazoAlGoOh;
import modelo.cartas.Carta;

public class ControladorMano extends ControladorMazoAlGoOh{
	
	ScrollPane representacion = new ScrollPane();
	
	public ControladorMano(int cantidad) {
		super(cantidad);
		this.representacion = new ScrollPane();
    	this.representacion.setPrefSize(500, 100);
		this.representacion.setFitToHeight(true);
	}
	
    public ControladorMano(MazoAlGoOh<Carta> cartas) {
        super(cartas);
		this.representacion = new ScrollPane();
    	this.representacion.setPrefSize(500, 100);
		this.representacion.setFitToHeight(true);
	}


    public ScrollPane obtenerRepresentacion() {	
		representacion.setContent(this.representacionCartas);
    	representacion.setHvalue(representacion.getHmax());
		return this.representacion;
	}

	public ScrollPane obtenerRepresentacionVacia() {
		representacion.setContent(null);
		return this.representacion;
	}

}
