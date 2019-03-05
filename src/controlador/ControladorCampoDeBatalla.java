package controlador;

import java.util.ArrayList;
import java.util.List;

import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaCampo;
import controlador.cartas.ControladorCartaMagia;
import controlador.cartas.ControladorCartaMonstruo;
import controlador.cartas.ControladorCartaTrampa;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import modelo.CampoDeBatalla;
import modelo.Constantes;
import modelo.excepciones.MazoLlenoException;

public class ControladorCampoDeBatalla {
	
    private GridPane representacion;
    
    private ControladorMano mano;
    private ControladorCementerio cementerio;
    private ControladorMazoDeCampo cartaCampo;
    private ControladorMazo mazo;
    private ControladorCartasMonstruoInvocadas monstruosInvocados;
    private ControladorCartasMagiaYTrampa magiaYTrampa;
    private boolean representacionInvertida;
    private CampoDeBatalla campo;
    
    public ControladorCampoDeBatalla(boolean invertirRepresentacion, CampoDeBatalla campo) {
    	this.campo=campo;
    	this.mazo = new ControladorMazo(campo.obtenerMazo());
    	this.mano = new ControladorMano(campo.obtenerMano());
    	this.cementerio = new ControladorCementerio(Constantes.CANTIDAD_DE_CARTAS_MAZO);
    	this.cartaCampo = new ControladorMazoDeCampo(Constantes.MAX_CARTAS_CAMPO_INVOCABLES);
    	this.monstruosInvocados = new ControladorCartasMonstruoInvocadas(Constantes.MAX_CARTAS_MONSTRUO_INVOCABLES);
    	this.magiaYTrampa = new ControladorCartasMagiaYTrampa(Constantes.MAX_CARTAS_MAGIA_Y_TRAMPA_INVOCABLES);
    	this.representacionInvertida = invertirRepresentacion;
    	
    	this.representacion = new GridPane();
		Image imagen=new Image(Constantes.RUTA_DE_LAS_IMAGENES + "fondo_1.jpg"); //akakakakaka
		BackgroundImage imagenFondo=new BackgroundImage(imagen,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
		Background fondo=new Background(imagenFondo);
		this.representacion.setBackground(fondo);
    	this.representacion.setAlignment(Pos.CENTER);
    	this.representacion.setVgap(20);
    	this.representacion.setHgap(20);
    	
    	if(this.representacionInvertida) {
        	this.representacion.add(mano.obtenerRepresentacionVacia(), 1, 0);
        	this.representacion.add(magiaYTrampa.obtenerRepresentacion(), 1, 1);
        	this.representacion.add(mazo.obtenerRepresentacion(), 2, 1);
        	this.representacion.add(cartaCampo.obtenerRepresentacion(), 0, 2);
        	this.representacion.add(monstruosInvocados.obtenerRepresentacion(), 1, 2);
        	this.representacion.add(cementerio.obtenerRepresentacion(), 2, 2);
        	
       	    RowConstraints row1 = new RowConstraints();
       	    row1.setPercentHeight(40);
            RowConstraints row2 = new RowConstraints();
            row2.setPercentHeight(30);
            RowConstraints row3 = new RowConstraints();
            row3.setPercentHeight(30);
            this.representacion.getRowConstraints().addAll(row1,row2,row3);
    	}
    	
    	else {
        	this.representacion.add(mano.obtenerRepresentacion(), 1, 2);
        	this.representacion.add(cartaCampo.obtenerRepresentacion(), 0, 0);
        	this.representacion.add(cementerio.obtenerRepresentacion(), 2, 0);
        	this.representacion.add(mazo.obtenerRepresentacion(), 2, 1);
        	this.representacion.add(monstruosInvocados.obtenerRepresentacion(), 1, 0);
        	this.representacion.add(magiaYTrampa.obtenerRepresentacion(), 1, 1);
   
       	    RowConstraints row1 = new RowConstraints();
       	    row1.setPercentHeight(30);
            RowConstraints row2 = new RowConstraints();
            row2.setPercentHeight(30);
            RowConstraints row3 = new RowConstraints();
            row3.setPercentHeight(40);
            this.representacion.getRowConstraints().addAll(row1,row2,row3);
    	}
    	
    }


	public ScrollPane obtenerMano() {
		return this.mano.obtenerRepresentacion();
	}

	public HBox obtenerMonstruosInvocados() {
		return this.monstruosInvocados.obtenerRepresentacion();
	}
	
	
	public void agregarCartaAlMazo(ControladorCartaAlGoOh carta) throws MazoLlenoException {
		mazo.agregarCarta(carta);
	}
    
	
	public void ponerCartaEnLaMano(){
		mano.agregarCarta(mazo.obtenerCartaTope());
		/* ESTO ES PARA PROBAR RÁPIDO SI SE QUEDA SIN CARTAS
		if(this.representacionInvertida) 
        	this.representacion.add(mano.obtenerRepresentacionVacia(), 1, 0);
    	else 
        	this.representacion.add(mano.obtenerRepresentacion(), 1, 2);
		*/
	}

	public ControladorCartaAlGoOh obtenerControladorEnMano(Button carta) {
		return mano.obtenerControlador(carta);
	}

	
	public ControladorCartaAlGoOh obtenerControladorEnMonstruosInvocados(Button carta) {
		return monstruosInvocados.obtenerControlador(carta);
	}


	public void invocarCartaMonstruoEnPosicionDeAtaque(ControladorCartaMonstruo carta) {
		mano.quitarCarta(carta);
		monstruosInvocados.agregarCarta(carta);
	}
	
	
	public void invocarCartaMonstruoEnPosicionDeDefensa(ControladorCartaMonstruo carta) {
		mano.quitarCarta(carta);
		carta.rotar();
		monstruosInvocados.agregarCarta(carta);
	}
	
	public GridPane obtenerRepresentacion() {
		return this.representacion;
	}


	public void invocarCartaMagiaBocaAbajo(ControladorCartaMagia carta) {
		mano.quitarCarta(carta);
		carta.ponerBocaAbajo();
		magiaYTrampa.agregarCarta(carta);
	}


	public void invocarCartaMagiaBocaArriba(ControladorCartaMagia carta) {
		mano.quitarCarta(carta);
		magiaYTrampa.agregarCarta(carta);
	}


	public void invocarCartaTrampaBocaAbajo(ControladorCartaTrampa carta) {
		mano.quitarCarta(carta);
		carta.ponerBocaAbajo();
		magiaYTrampa.agregarCarta(carta);
	}

	public void enviarCartaAlCementerio(ControladorCartaAlGoOh carta,ControladorMazoAlGoOh origen) {
		origen.quitarCarta(carta);
		carta.ponerBocaArriba();
		cementerio.agregarCarta(carta);
	}
	

	public void actualizarCartasEnElCementerio() {
		List<ControladorCartaAlGoOh> cartasMagiaYTrampa =  magiaYTrampa.obtenerControladores();
		ControladorCartaAlGoOh cartaActual = null;
		List<ControladorCartaAlGoOh> cartasAEliminar = new ArrayList<ControladorCartaAlGoOh>();
		
		for(ControladorCartaAlGoOh carta : cartasMagiaYTrampa ){
			
			try {
				ControladorCartaMagia cartaMagia = (ControladorCartaMagia)carta;
				cartaActual = cartaMagia;
			}
			
			catch (ClassCastException e) {
				ControladorCartaTrampa cartaTrampa = (ControladorCartaTrampa)carta;
				cartaActual = cartaTrampa;
			}
			
			if(cartaActual.obtenerCarta().estaEnElCementerio()){
				cartasAEliminar.add(cartaActual);
			}
		}
		
		for (ControladorCartaAlGoOh carta: cartasAEliminar) {
	    	enviarCartaAlCementerio(carta, magiaYTrampa);
		}
		
		List<ControladorCartaAlGoOh> cartasMonstruos =  monstruosInvocados.obtenerControladores();
		
		for(ControladorCartaAlGoOh carta : cartasMonstruos ){
			ControladorCartaMonstruo cartaMonstruo = (ControladorCartaMonstruo)carta;
			
			if(cartaMonstruo.obtenerCarta().estaEnElCementerio()){
				cartasAEliminar.add(cartaMonstruo);
			}
			
		}
		
		for (ControladorCartaAlGoOh carta: cartasAEliminar) {
	    	enviarCartaAlCementerio(carta, monstruosInvocados);
		}

		List<ControladorCartaAlGoOh> mano = this.mano.obtenerControladores();

		for (ControladorCartaAlGoOh carta : mano ) {
			if(carta.obtenerCarta().estaEnElCementerio()) {
				cartasAEliminar.add(carta);
			}
		}
		for (ControladorCartaAlGoOh carta: cartasAEliminar) {
			enviarCartaAlCementerio(carta, this.mano);
		}
		
	}


	public List<ControladorCartaAlGoOh> obtenerControladoresDeMonstruosInvocados() {
		return monstruosInvocados.obtenerControladores();
	}


	public ControladorCartaAlGoOh obtenerControladorDeMonstruosInvocados(Button target) {
		return monstruosInvocados.obtenerControlador(target);
	}

	public Button obtenerMazo() {
    	return this.mazo.obtenerRepresentacion();
	}


	public HBox obtenerCartasMagiaYTrampaInvocadas() {
    	return this.magiaYTrampa.obtenerRepresentacion();
	}

	public ControladorCartaAlGoOh obtenerControladorDeCartasMagiaInvocadas(Button target) {
    	return magiaYTrampa.obtenerControlador(target);
	}

	public void actualizarCartasEnElMazo() {
		while (this.mazo.verTope().obtenerCarta().estaEnLaMano()) {
			ponerCartaEnLaMano();
		}
	
	}
	
	public void actualizarCartasMonstruoInvocadas() {
    	List<ControladorCartaAlGoOh> alCementerio = new ArrayList<>();
		for (ControladorCartaAlGoOh carta : this.monstruosInvocados.obtenerControladores()) {
			if (carta.obtenerCarta().estaEnElCementerio()) {
//				enviarCartaAlCementerio(carta, this.monstruosInvocados);
				alCementerio.add(carta);
			} 
			
			if(carta.estaBocaAbajo() && carta.obtenerCarta().estaInvocadaBocaArriba()) {
				cambiarCartaMonstruoABocaArriba((ControladorCartaMonstruo)carta);
			}
			
			if((!carta.estaBocaAbajo()) && carta.obtenerCarta().estaInvocadaBocaAbajo()) {
				cambiarCartaMonstruoABocaAbajo((ControladorCartaMonstruo)carta);
			}
		}
		for (ControladorCartaAlGoOh carta : alCementerio) {
			enviarCartaAlCementerio(carta, this.monstruosInvocados);
		}
	}
	
	public void actualizarCartasEnMano() {
		for (ControladorCartaAlGoOh carta : this.mano.obtenerControladores()) {
			if (carta.obtenerCarta().estaEnElCementerio()) {
				enviarCartaAlCementerio(carta, this.mano);
			} 
			
			if(carta.estaBocaAbajo() && carta.obtenerCarta().estaInvocadaBocaArriba()) {
				cambiarCartaMonstruoABocaArriba((ControladorCartaMonstruo)carta);
			}
			
			if((!carta.estaBocaAbajo()) && carta.obtenerCarta().estaInvocadaBocaAbajo()) {
				cambiarCartaMonstruoABocaAbajo((ControladorCartaMonstruo)carta);
			}
			
		}
	}

	public void invocarCartaMonstruoEnPosicionDeDefensaBocaAbajo(ControladorCartaMonstruo controladorCarta) {
		mano.quitarCarta(controladorCarta);
		controladorCarta.ponerBocaAbajo();
		controladorCarta.rotar();
		monstruosInvocados.agregarCarta(controladorCarta);
	}


	public void invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(ControladorCartaMonstruo carta) {
		mano.quitarCarta(carta);
		carta.ponerBocaAbajo();
		monstruosInvocados.agregarCarta(carta);
	}


	public void cambiarCartaMonstruoABocaArriba(ControladorCartaMonstruo carta) {
		boolean rotar = false;
		if(carta.estaRotada())
			rotar = true;
			
		monstruosInvocados.quitarCarta(carta);
		
		carta.ponerBocaArriba();
		if(rotar) 
			carta.rotar();
		
		monstruosInvocados.agregarCarta(carta);
	}


	public void cambiarCartaMonstruoABocaAbajo(ControladorCartaMonstruo carta) {
		boolean rotar = false;
		
		if(carta.estaRotada())
			rotar = true;
			
		monstruosInvocados.quitarCarta(carta);
		carta.ponerBocaAbajo();
		
		if(rotar) 
			carta.rotar();
		
		monstruosInvocados.agregarCarta(carta);
	}


	public void cambiarCartaMonstruoAModoAtaque(ControladorCartaMonstruo carta) {

		monstruosInvocados.quitarCarta(carta);
		carta.ponerEnModoNormal();
		monstruosInvocados.agregarCarta(carta);
	}


	public void cambiarCartaMonstruoAModoDefensa(ControladorCartaMonstruo carta) {
		monstruosInvocados.quitarCarta(carta);
		carta.rotar();
		monstruosInvocados.agregarCarta(carta);
	}

	public ControladorCartaAlGoOh obtenerControladorDeCartaCampoInvocada(Button target) {
		return cartaCampo.obtenerControlador(target);
	}

	public HBox obtenerCartaCampoInvocada() {
    	return this.cartaCampo.obtenerRepresentacion();
	}

	public void mostrarMano() {
		this.representacion.getChildren().remove(mano.obtenerRepresentacion());
		if (representacionInvertida) this.representacion.add(mano.obtenerRepresentacion(), 1, 0);
		else this.representacion.add(mano.obtenerRepresentacion(), 1, 2);

	}

	public void ocultarMano() {
		this.representacion.getChildren().remove(mano.obtenerRepresentacion());
		if (representacionInvertida) this.representacion.add(mano.obtenerRepresentacionVacia(), 1, 0);
		else this.representacion.add(mano.obtenerRepresentacionVacia(), 1, 2);
	}

	public ControladorCartaAlGoOh obtenerControladorDeMazo(Button target) {
		return mazo.obtenerControlador(target);
	}


	public void deshabilitarCampo() {
		//this.mazo.obtenerRepresentacion().addEventFilter(MouseEvent.ANY, consumirEvento);
	}


	public void habilitarCampo() {
		//this.mazo.obtenerRepresentacion().removeEventFilter(MouseEvent.ANY, consumirEvento);
	}


	public void invocarCartaCampo(ControladorCartaCampo carta) {
		mano.quitarCarta(carta);
		cartaCampo.agregarCarta(carta);
	}

}
