package controlador;

import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaMonstruo;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Constantes;
import modelo.MazoAlGoOh;
import modelo.PartidaAlGoOh;
import modelo.cartas.Carta;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import vista.cartas.VistaInfoCartaAlGoOh;

public class ControladorPartidaAlGoOh {
	private PartidaAlGoOh partida;
	private ControladorJugador jugadorActivo;
	private ControladorJugador jugadorPasivo;

	private Text informacionAlUsuario;
	private Stage stage;
	private Scene visualizacion;
	private GridPane representacion;
	private VistaInfoCartaAlGoOh panelInfoCarta;
	private boolean seTomoCartaDelMazo = false;
	private boolean seInicioLaPartida = false;

	public ControladorPartidaAlGoOh(String unNombreJugador, String otroNombreJugador, Stage stage) {
	    this.partida = new PartidaAlGoOh(unNombreJugador, otroNombreJugador);

	    try {
			partida.iniciar();

		}
	    catch (MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException
				| CloneNotSupportedException e) {
			e.printStackTrace();
		}

		this.jugadorActivo = new ControladorJugador(partida.obtenerJugadorActivo(), false, this);
		this.jugadorPasivo = new ControladorJugador(partida.obtenerJugadorPasivo(), true,this);
		this.informacionAlUsuario = crearInformacionAlUsuario();
        this.representacion = new GridPane();
        
        VBox panelJugadorActivo = jugadorActivo.obtenerPanel();
        VBox panelJugadorPasivo = jugadorPasivo.obtenerPanel();
        GridPane panelCentral = obtenerPanelCentral(); 
      
        VBox panelDeBotones = new VBox();
        panelDeBotones.getChildren().addAll(
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		new Label(),
        		obtenerBotonDeFase(),
        		obtenerBotonDeTurno());

        this.panelInfoCarta = new VistaInfoCartaAlGoOh();

		Image imagen = new Image(Constantes.RUTA_DE_LAS_IMAGENES + "fondo_2.jpg");
		BackgroundImage imagenFondo = new BackgroundImage(imagen,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,null);
		Background fondo = new Background(imagenFondo);
		this.representacion.setBackground(fondo);
        
	    this.representacion.setHgap(1);
	    this.representacion.setAlignment(Pos.CENTER); 

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        this.representacion.getColumnConstraints().addAll(col1,col2,col3);
	    
		this.representacion.add(panelJugadorActivo, 2, 2);
        this.representacion.add(panelInfoCarta, 0, 1);
        this.representacion.add(panelJugadorPasivo, 2, 0);
        this.representacion.add(panelCentral,1,1);
    	this.representacion.add(panelDeBotones, 2, 1);
    	this.representacion.add(informacionAlUsuario, 0, 0);
    	
    	this.stage = stage;
    	this.visualizacion = new Scene(this.representacion);
    	
		this.stage.setScene(this.visualizacion);
		this.stage.setFullScreen(true);

		jugadorPasivo.deshabilitarCampo();
    }

	public void iniciar() {
		actualizarInformacionAlUsuario("");
        seInicioLaPartida = true;
        verificarFinDelJuego();
        this.stage.show();
	}

	public MazoAlGoOh<CartaMonstruo> obtenerSacrificiosJugadorActivo(){
		return this.jugadorActivo.obtenerSacrificios();
	}
	
	public PartidaAlGoOh obtenerPartida() {
		return this.partida;
	}
	
	
	public void actualizarInformacionAlUsuario(String texto) {
	    this.informacionAlUsuario.setText(
	    		"\n" +
	    		this.partida.obtenerJugadorActivo().toString()	    		
	    		+ "\n" +
	    		this.partida.obtenerFase().toString()  
	    		+ "\n" +
	    		texto);
	}
	
	public Text crearInformacionAlUsuario() {
		Text infoUsuario = new Text();
		
		infoUsuario.setFont(Font.font("Tahoma", FontWeight.BOLD,  20));
		infoUsuario.minWidth(500);
		infoUsuario.minHeight(200);
		infoUsuario.prefWidth(500);
		infoUsuario.prefHeight(200);
		infoUsuario.setFill(Color.YELLOW);
        return infoUsuario;
	}
	
    private GridPane obtenerPanelCentral() {
        GridPane panelCentral = new GridPane();

    	panelCentral.setAlignment(Pos.CENTER);
    	panelCentral.setPadding(new javafx.geometry.Insets(10,10,10,10));
    	panelCentral.setVgap(5);
    	panelCentral.setHgap(5);
    	panelCentral.add(jugadorActivo.obtenerRepresentacion(), 0, 2);
    	panelCentral.add(jugadorPasivo.obtenerRepresentacion(), 0, 0);

	    return panelCentral;
    }

    private Button obtenerBotonDeFase() {

        Button cambioDeFase = new Button("Cambiar de Fase");
        cambioDeFase.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
           @Override
        	public void handle(MouseEvent e) {
        	   
			   if (partida.laFaseActualEsLaUltima()) 
         		  actualizarInformacionAlUsuario("NO HAY PROXIMA FASE: CAMBIAR DE TURNO");
        	   
        	   else {
        	   		if (!seTomoCartaDelMazo) {
        	   			mensajePorCartaNoTomada();
        	   			return;
					}
        		    partida.cambiarDeFase();
               		actualizarInformacionAlUsuario("");
        	   }
         	  }
        	   
        });
        
        return cambioDeFase;
    }

    private Button obtenerBotonDeTurno() {    	
        Button terminarTurno = new Button("Terminar turno");

        terminarTurno.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
            	
            	if (!seTomoCartaDelMazo) {
    	   			mensajePorCartaNoTomada();
            		return;
            	}
            	
                terminarTurno();
            }
		});

        return terminarTurno;
    }

	public void terminarTurno() {
		seTomoCartaDelMazo = false;
		partida.terminarTurno();
		intercambiarJugadores();
		actualizarInformacionAlUsuario("");
	}


    public void intercambiarJugadores() {
    	ControladorJugador jugadorAuxiliar = null;
    	jugadorAuxiliar = this.jugadorActivo;
    
    	this.jugadorActivo = this.jugadorPasivo;
    	this.jugadorActivo.habilitarCampo();
    	
    	this.jugadorPasivo = jugadorAuxiliar;
    	this.jugadorPasivo.deshabilitarCampo();
	}

    public void ponerCartaEnLaManoDeJugadorActivo(){
		try
		{
			if (!seTomoCartaDelMazo || ! seInicioLaPartida) {
				this.partida.ponerCartaEnLaManoDeJugadorActivo();
				this.jugadorActivo.ponerCartaEnLaMano();
				seTomoCartaDelMazo = true;
				
			} else {
				mensajePorCartaYaTomada();
			}
		}

		catch(FuncionNoAccesibleException e) {
		} catch (CartaAlGoOhInexistenteException | MazoLlenoException | MazoVacioException e) {
			e.printStackTrace();
		}
		verificarFinDelJuego();
	}


	public void agregarCartaAlMazoDeJugadorActivo(ControladorCartaAlGoOh carta){
		try {
			this.partida.agregarCartaAlMazoDeJugadorActivo(carta.obtenerCarta());
			this.jugadorActivo.agregarCartaAlMazo(carta);
		}

		catch (MazoLlenoException e) {
			e.printStackTrace();
		}

	}

	public void agregarCartaAlMazoDeJugadorPasivo(ControladorCartaAlGoOh carta){
		try {
			this.partida.agregarCartaAlMazoDeJugadorActivo(carta.obtenerCarta());
			this.jugadorPasivo.agregarCartaAlMazo(carta);
		}

		catch (MazoLlenoException e) {
			e.printStackTrace();
		}

	}

	public ControladorJugador obtenerJugadorActivo() {
		return jugadorActivo;
	}
	

	public ControladorJugador obtenerJugadorPasivo() {
		return jugadorPasivo;
	}


	public void actualizarCartasEnElCementerio() {
		actualizarCartasEnElCementerioAux(jugadorActivo);
		actualizarCartasEnElCementerioAux(jugadorPasivo);
	}
	
	private void actualizarCartasEnElCementerioAux(ControladorJugador jugador){
		jugador.actualizarCartasEnElCementerio();
	}

	public void actualizarPuntosDeVida() {
		actualizarPuntosDeVidaAux(jugadorActivo, partida.obtenerJugadorActivo().obtenerPuntosDeVida());
		actualizarPuntosDeVidaAux(jugadorPasivo, partida.obtenerJugadorPasivo().obtenerPuntosDeVida());
		verificarFinDelJuego();
	}

	private void actualizarPuntosDeVidaAux(ControladorJugador jugador, String puntos) {
		jugador.actualizarPuntosDeVida(puntos);
	}

	public Object obtenerNodoEnFoco() {
		return this.visualizacion.focusOwnerProperty().get();
	}

	public void verificarFinDelJuego(){
    	String mensajeAdicional = "GANO ";

    	
    	if(partida.jugadorActivoGanoLaPartida())
    		mensajeAdicional += jugadorActivo.obtenerNombre().toUpperCase();
    	
    	else if(partida.jugadorPasivoGanoLaPartida())
    		 mensajeAdicional += jugadorPasivo.obtenerNombre().toUpperCase();
    	
    	else
    		return;
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("AlGoOh");
    	alert.setHeaderText(mensajeAdicional);
    	alert.setContentText("Juego terminado");
    	alert.showAndWait();
    	System.exit(0);
    }
	
    public void actualizarCartasEnElMazo() {
    	
    	try {
        actualizarCartasEnElMazo(jugadorActivo);
        actualizarCartasEnElMazo(jugadorPasivo);
    	}
    	
    	catch(IndexOutOfBoundsException e){
    	}
    }

    
    
    private void actualizarCartasEnElMazo(ControladorJugador controlador) {
    	controlador.actualizarCartasEnElMazo();
    }
    
	public void actualizarCartasMonstruoInvocadas() {
		actualizarCartasMonstruoInvocadas(jugadorActivo);
		actualizarCartasMonstruoInvocadas(jugadorPasivo);
	}

	private void actualizarCartasMonstruoInvocadas(ControladorJugador controlador) {
		controlador.actualizarCartasMonstruoInvocadas();
	}
	
	public void actualizarCartasEnMano() {
		actualizarCartasEnMano(jugadorActivo);
		actualizarCartasEnMano(jugadorPasivo);
	}

	private void actualizarCartasEnMano(ControladorJugador controlador) {
		controlador.actualizarCartasEnMano();
	}


	public void agregarSacrificioParaJugadorActivo(ControladorCartaMonstruo carta) {
		try {
			jugadorActivo.obtenerSacrificios().agregarCarta(carta.obtenerCarta());
			carta.oscurecer();
		} catch (MazoLlenoException e) {
			e.printStackTrace();
		}
	}
	
	public void quitarSacrificioParaJugadorActivo(ControladorCartaMonstruo carta)  {
		jugadorActivo.obtenerSacrificios().quitarCarta(carta.obtenerCarta());
		carta.cambiarAColorNormal();
	}


	public void actualizarSacrificiosParaJugadorActivo() {
		for(CartaMonstruo carta: jugadorActivo.obtenerSacrificios()) {
			if(carta.estaEnElCementerio()) {
				jugadorActivo.obtenerSacrificios().quitarCarta(carta);
			}
		}
	}

	public void setInfoCarta(Carta carta) {
		this.panelInfoCarta.actualizar(carta);
	}

	public void mensajePorFuncionNoAccesible() {
		this.actualizarInformacionAlUsuario( "NO SE PUEDE INVOCAR ESTA CARTA EN LA FASE ACTUAL");
	}

	public void mensajePorExcesoDeCartasMagiaYTrampa() {
        actualizarInformacionAlUsuario("NO SE PUEDEN INVOCAR MAS CARTAS DE ESE TIPO");
	}

	public void mensajePorExcesoDeCartasMonstruo() {
		actualizarInformacionAlUsuario("NO SE PUEDE INVOCAR MAS MONSTRUOS");
	}
	
	public void mensajeMonstruoYaInvocado() {
		actualizarInformacionAlUsuario("YA SE INVOCO UN MONSTRUO EN ESTE TURNO");
	}
	
	public void mensajePorCartaAjena() {
		actualizarInformacionAlUsuario("ESA NO ES UNA CARTA PROPIA");
	}
	
	public void mensajePorCartaNoTomada() {
		actualizarInformacionAlUsuario("SE DEBE TOMAR UNA CARTA DEL MAZO");
	}
	
	public void mensajePorSacrificioInvalido() {
		actualizarInformacionAlUsuario( "LOS SACRIFICIOS INDICADOS SON INCORRECTOS PARA LA INVOCACION");
	}
	
	public void mensajePorCartaYaBocaArriba() {
		actualizarInformacionAlUsuario("ESA CARTA YA ESTA BOCA ARRIBA");
	}
	
	public void mensajePorCartaYaBocaAbajo() {
		actualizarInformacionAlUsuario("ESTA CARTA YA ESTA BOCA ABAJO");	
	}
	
	public void mensajePorCambioInvalido() {
		actualizarInformacionAlUsuario("LA CARTA SOLO PUEDE CAMBIAR EN LA FASE DE PREPARACION, Y NO EN EL MISMO TURNO DE INVOCACION");
	}
	
	public void mensajePorAtaqueDobleDeMonstruo() {
		actualizarInformacionAlUsuario("EL MONSTRUO PUEDE ATACAR SOLO UNA VEZ POR TURNO");
	}
	
	public void mensajePorAtaqueInvalido() {
		actualizarInformacionAlUsuario("SE DEBE ATACAR EN FASE DE ATAQUE CON EL MONSTRUO EN MODO ATAQUE Y BOCA ARRIBA");
	}
	
	public void mensajePorMonstruosEnElCampo() {
		 actualizarInformacionAlUsuario("EL OPONENTE TODAVIA TIENE MONSTRUOS EN SU CAMPO");
	}
	
	public void mensajePorCartaYaEnModoAtaque() {
		actualizarInformacionAlUsuario("ESA CARTA YA ESTA EN MODO ATAQUE");
	}
	
	public void mensajePorCartaYaEnModoDefensa() {
		actualizarInformacionAlUsuario("ESA CARTA YA ESTA EN MODO DEFENSA");		
	}
	
	public void mensajePorCartaYaTomada() {
		actualizarInformacionAlUsuario("SOLO SE PUEDE TOMAR UNA CARTA DEL MAZO POR TURNO");
	}


}
