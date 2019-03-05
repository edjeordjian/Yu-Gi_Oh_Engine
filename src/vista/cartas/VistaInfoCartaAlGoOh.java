package vista.cartas;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import modelo.Constantes;
import modelo.cartas.Carta;

public class VistaInfoCartaAlGoOh extends VBox{

	private ImageView imagenCarta;

	public VistaInfoCartaAlGoOh() {
		this.setAlignment(Pos.CENTER);
		imagenCarta = new ImageView(Constantes.RUTA_DE_LAS_IMAGENES + "boca_abajo.png");
		imagenCarta.setFitWidth(Constantes.ANCHO_DE_REPRESENTACION_CARTA * Constantes.FACTOR_DE_AUMENTO_PARA_VISUALIZACION_DE_CARTA);
		imagenCarta.setFitHeight(Constantes.ALTO_DE_REPRESENTACION_CARTA * Constantes.FACTOR_DE_AUMENTO_PARA_VISUALIZACION_DE_CARTA);
		this.getChildren().add(imagenCarta);
	}

	public void actualizar(Carta carta) {

		this.getChildren().clear();

		//this.nombre = carta.toString();

		String directorioImagen = Constantes.RUTA_DE_LAS_IMAGENES +
				carta.toString() +
				Constantes.EXTENSION_IMAGENES_DE_CARTAS;

		imagenCarta = new ImageView(directorioImagen);
		imagenCarta.setFitWidth(Constantes.ANCHO_DE_REPRESENTACION_CARTA * Constantes.FACTOR_DE_AUMENTO_PARA_VISUALIZACION_DE_CARTA);
		imagenCarta.setFitHeight(Constantes.ALTO_DE_REPRESENTACION_CARTA * Constantes.FACTOR_DE_AUMENTO_PARA_VISUALIZACION_DE_CARTA);
		//No aporta más información de la existente
		//Label nombre = new Label(this.nombre);
		//nombre.setFont(new Font("Cambria", 15));
		//nombre.setAlignment(Pos.CENTER);
		//nombre.setWrapText(true);
		//this.getChildren().add(nombre);
		this.getChildren().add(imagenCarta);
	}


}
