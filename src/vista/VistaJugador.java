package vista;


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VistaJugador {
   
	VBox datos;
    Text nombreJugador;
    Text puntos;

    public VistaJugador(String nombre, String puntosDeVida) {
	    datos = new VBox();
	    nombreJugador = new Text(nombre);
	    nombreJugador.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
	    nombreJugador.setFill(Color.YELLOW);
	    puntos = new Text(puntosDeVida);
	    puntos.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
	    puntos.setFill(Color.YELLOW);
	    datos.getChildren().add(nombreJugador);
	    datos.getChildren().add(puntos);
    }
    
    public VBox obtenerRepresentacion() {
    	return this.datos;
    }

	public void actualizarPuntosDeVida(String unosPuntos) {
		datos.getChildren().remove(this.puntos);
		datos.getChildren().add(obtenerLabelJugador(unosPuntos));
	}
	
	public Text obtenerLabelJugador(String unosPuntos) {
		this.puntos  = new Text(unosPuntos);
		this.puntos.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
	    puntos.setFill(Color.YELLOW);
		return this.puntos;
	}

}
