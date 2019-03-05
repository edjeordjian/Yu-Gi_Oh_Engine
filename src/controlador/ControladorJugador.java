package controlador;

import java.util.List;

import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaCampo;
import controlador.cartas.ControladorCartaMagia;
import controlador.cartas.ControladorCartaMonstruo;
import controlador.cartas.ControladorCartaTrampa;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Constantes;
import modelo.Jugador;
import modelo.MazoAlGoOh;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.MazoLlenoException;
import vista.VistaJugador;
import vista.eventos.ClicEnLaCartaCampoInvocadaEvent;
import vista.eventos.ClicEnUnaDeLasCartasDeLaManoEvent;
import vista.eventos.ClicEnUnaDeLasCartasDelMazoEvent;
import vista.eventos.ClicEnUnaDeLasCartasTrampaOMagiaInvocadasEvent;
import vista.eventos.ClicEnUnoDeLosMonstruosInvocadosEvent;

public class ControladorJugador {
	
	private ControladorCampoDeBatalla representacion;
	private String nombre;
	private String puntosDeVida;
	private VistaJugador vista;
	private MazoAlGoOh<CartaMonstruo> sacrificios;
	private Jugador jugador;
	private EventHandler<MouseEvent> clicEnLaMano;
	private EventHandler<MouseEvent> clicEnLosMonstruos;
	private EventHandler<MouseEvent> clicEnElMazo;
	private EventHandler<MouseEvent> clicMagiaYTrampa;
	private EventHandler<MouseEvent> clicEnElCampo;

	public ControladorJugador(Jugador jugador, boolean campoInvertido,ControladorPartidaAlGoOh partida) {
		this.nombre = jugador.toString();
		this.jugador = jugador;
		this.puntosDeVida = jugador.obtenerPuntosDeVida();
		this.representacion = new ControladorCampoDeBatalla(campoInvertido,jugador.getCampo());
		this.vista = new VistaJugador(this.nombre, this.puntosDeVida);
		this.sacrificios = new MazoAlGoOh<CartaMonstruo>(Constantes.MAX_CARTAS_MONSTRUO_INVOCABLES);
		
		clicEnLaMano = new ClicEnUnaDeLasCartasDeLaManoEvent(partida);
		clicEnLosMonstruos = new ClicEnUnoDeLosMonstruosInvocadosEvent(partida);
		clicEnElMazo =  new ClicEnUnaDeLasCartasDelMazoEvent(partida);
		clicMagiaYTrampa = new ClicEnUnaDeLasCartasTrampaOMagiaInvocadasEvent(partida);
		clicEnElCampo = new ClicEnLaCartaCampoInvocadaEvent(partida);

		this.representacion.obtenerMano().addEventFilter(MouseEvent.MOUSE_CLICKED, clicEnLaMano);
		this.representacion.obtenerMonstruosInvocados().addEventFilter(MouseEvent.MOUSE_CLICKED, clicEnLosMonstruos);
		this.representacion.obtenerMazo().addEventFilter(MouseEvent.MOUSE_CLICKED, clicEnElMazo);
		this.representacion.obtenerCartasMagiaYTrampaInvocadas().addEventFilter(MouseEvent.MOUSE_CLICKED, clicMagiaYTrampa);
		this.representacion.obtenerCartaCampoInvocada().addEventFilter(MouseEvent.MOUSE_CLICKED, clicEnElCampo);
	}
	
	public ScrollPane obtenerMano(){
		return this.representacion.obtenerMano();
	}
	
	public Jugador obtenerJugador() {
		return this.jugador;
	}
	
	public VBox obtenerPanel() {
		return vista.obtenerRepresentacion();
	}
	
	public HBox obtenerMonstruosInvocados(){
		return this.representacion.obtenerMonstruosInvocados();
	}

	public void agregarCartaAlMazo(ControladorCartaAlGoOh carta) throws MazoLlenoException {
		this.representacion.agregarCartaAlMazo(carta);
	}

	public void ponerCartaEnLaMano(){	
		this.representacion.ponerCartaEnLaMano();
	}

	public void invocarCartaMonstruoEnPosicionDeAtaque(ControladorCartaMonstruo controladorCarta) {
		this.representacion.invocarCartaMonstruoEnPosicionDeAtaque(controladorCarta);
	}

	public void invocarCartaMonstruoEnPosicionDeDefensa(ControladorCartaMonstruo controladorCarta) {
		this.representacion.invocarCartaMonstruoEnPosicionDeDefensa(controladorCarta);
	}
	
	public ControladorCartaAlGoOh obtenerControladorEnMano(Button carta) {
		return this.representacion.obtenerControladorEnMano(carta);
	}
		
	public GridPane obtenerRepresentacion() {
		return this.representacion.obtenerRepresentacion();
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
	
	public String obtenerPuntosDeVida() {
		return this.puntosDeVida;
	}

	public void invocarCartaMagiaBocaAbajo(ControladorCartaMagia carta) {
		this.representacion.invocarCartaMagiaBocaAbajo(carta);
	}

	public void invocarCartaMagiaBocaArriba(ControladorCartaMagia carta) {
		this.representacion.invocarCartaMagiaBocaArriba(carta);	
	}

	public void invocarCartaTrampaBocaAbajo(ControladorCartaTrampa carta) {
		this.representacion.invocarCartaTrampaBocaAbajo(carta);	
	}

	public void actualizarCartasEnElCementerio() {
		representacion.actualizarCartasEnElCementerio();
	}

	public void actualizarPuntosDeVida(String puntos) {
		this.puntosDeVida = puntos;
		vista.actualizarPuntosDeVida(puntos);
	}

	public List<ControladorCartaAlGoOh> obtenerControladoresDeMonstruosInvocados() {
		return representacion.obtenerControladoresDeMonstruosInvocados();
	}

	public ControladorCartaAlGoOh obtenerControladorEnMonstruosInvocados(Button target) {
		return representacion.obtenerControladorDeMonstruosInvocados(target);
		}

	public Button obtenerMazo() {
		return this.representacion.obtenerMazo();
	}

    public HBox obtenerCartasMagiaYTrampaInvocadas() {
        return this.representacion.obtenerCartasMagiaYTrampaInvocadas();
    }

    public ControladorCartaAlGoOh obtenerControladorDeCartasMagiaYTrampaInvocadas(Button target) {
	    return representacion.obtenerControladorDeCartasMagiaInvocadas(target);
    }

    public ControladorCartaAlGoOh obtenerControladorDeCartaCampoInvocada(Button target) {
		return representacion.obtenerControladorDeCartaCampoInvocada(target);
	}

    public void actualizarCartasEnElMazo() {
        representacion.actualizarCartasEnElMazo();
    }

	public void actualizarCartasEnMano() {
		representacion.actualizarCartasEnMano();
	}

	public void invocarCartaMonstruoEnPosicionDeDefensaBocaAbajo(ControladorCartaMonstruo controladorCarta) {
		this.representacion.invocarCartaMonstruoEnPosicionDeDefensaBocaAbajo(controladorCarta);
	}

	public void invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(ControladorCartaMonstruo carta) {
		this.representacion.invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(carta);	
	}

	public void cambiarCartaMonstruoABocaArriba(ControladorCartaMonstruo carta) {
		this.representacion.cambiarCartaMonstruoABocaArriba(carta);
	}

	public void cambiarCartaMonstruoABocaAbajo(ControladorCartaMonstruo carta) {
		this.representacion.cambiarCartaMonstruoABocaAbajo(carta);
	}

	public void cambiarCartaMonstruoAModoAtaque(ControladorCartaMonstruo carta) {
		this.representacion.cambiarCartaMonstruoAModoAtaque(carta);
		}

	public void cambiarCartaMonstruoAModoDefensa(ControladorCartaMonstruo carta) {
		this.representacion.cambiarCartaMonstruoAModoDefensa(carta);
		}

	public HBox obtenerCartaCampoInvocada() {
		return this.representacion.obtenerCartaCampoInvocada();
	}

	public MazoAlGoOh<CartaMonstruo> obtenerSacrificios() {
		return this.sacrificios;
	}

	public void deshabilitarCampo() {
		this.representacion.ocultarMano();
		this.representacion.deshabilitarCampo();	
	}

	public void habilitarCampo() {
		this.representacion.mostrarMano();
		this.representacion.habilitarCampo();
	}

	public void invocarCartaCampo(ControladorCartaCampo cartaCampo) {
		this.representacion.invocarCartaCampo(cartaCampo);
	}

	public void actualizarCartasMonstruoInvocadas() {
		representacion.actualizarCartasMonstruoInvocadas();
	}

	public Button obtenerControladorMazo() {
		return this.representacion.obtenerMazo();
	}

}
