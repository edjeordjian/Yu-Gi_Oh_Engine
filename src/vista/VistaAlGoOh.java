package vista;

import javafx.scene.image.ImageView;

public class VistaAlGoOh {
	
	private ImageView visualizacion;
	
	public VistaAlGoOh(int ancho, int alto, String directorioImagen) {
		this.visualizacion = new ImageView(directorioImagen);
		this.visualizacion.setFitWidth(ancho); 
		this.visualizacion.setFitHeight(alto); 
	}

	public ImageView obtenerVisualizacion() {
		return this.visualizacion;
	}
	
}
