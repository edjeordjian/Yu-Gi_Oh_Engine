package vista.cartas;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import modelo.Constantes;
import modelo.cartas.CartaAlGoOh;

public class VistaCartaAlGoOh{

	private Button visualizacion;
	private ImageView imagenDeBoton;
	
	public VistaCartaAlGoOh(CartaAlGoOh carta)  {
		
		String directorioImagen = Constantes.RUTA_DE_LAS_IMAGENES +
				carta.toString()+ Constantes.EXTENSION_IMAGENES_DE_CARTAS;

		imagenDeBoton = new ImageView(directorioImagen);
		imagenDeBoton.setFitWidth(Constantes.ANCHO_DE_REPRESENTACION_CARTA); 
		imagenDeBoton.setFitHeight(Constantes.ALTO_DE_REPRESENTACION_CARTA); 
		this.visualizacion = new Button();
		this.visualizacion.setGraphic(imagenDeBoton);
	}
	
	
	public VistaCartaAlGoOh(String directorioImagen)
	{
		imagenDeBoton = new ImageView(directorioImagen);
		imagenDeBoton.setFitWidth(Constantes.ANCHO_DE_REPRESENTACION_CARTA); 
		imagenDeBoton.setFitHeight(Constantes.ALTO_DE_REPRESENTACION_CARTA); 
		this.visualizacion = new Button();
		this.visualizacion.setGraphic(imagenDeBoton);
	}
	
	public Button obtenerVisualizacion() {
		return this.visualizacion;
	}

	public void rotar() {
		if(this.visualizacion.getRotate() == 0)
			this.visualizacion.setRotate(90);
		
	}

	public void oscurecer() {
        javafx.scene.effect.ColorAdjust rojo = new ColorAdjust();
        rojo.setBrightness(-1.0);
        imagenDeBoton.setEffect(rojo);
	}


	public void cambiarAColorNormal() {
        javafx.scene.effect.ColorAdjust normal = new ColorAdjust();
        normal.setBrightness(0);
		imagenDeBoton.setEffect(normal);
	}


	public void ponerEnModoNormal() {
		if(this.visualizacion.getRotate() != 0)
			this.visualizacion.setRotate(0);
	}

}
