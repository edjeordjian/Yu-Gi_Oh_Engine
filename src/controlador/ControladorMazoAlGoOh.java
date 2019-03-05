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
import javafx.scene.layout.HBox;
import modelo.MazoAlGoOh;
import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasTrampa.CartaTrampa;

public abstract class ControladorMazoAlGoOh {

	protected HBox representacionCartas;	
	protected List<ControladorCartaAlGoOh> controladoresCartas;
	

	public ControladorMazoAlGoOh(int cantidad) {
		representacionCartas = new HBox();
		representacionCartas.setSpacing(20);
		representacionCartas.setAlignment(Pos.TOP_RIGHT);
		controladoresCartas = new ArrayList<ControladorCartaAlGoOh>();
	}
	
	public ControladorMazoAlGoOh(MazoAlGoOh<Carta> mazo) {
		representacionCartas = new HBox();
		representacionCartas.setSpacing(20);
		representacionCartas.setAlignment(Pos.TOP_RIGHT);
		controladoresCartas = new ArrayList<ControladorCartaAlGoOh>();
		
		for (Carta carta: mazo){
			try {
				agregarCarta(new ControladorCartaMonstruo((CartaMonstruo)carta));
	        }
	        catch(ClassCastException e) {
	        	
	        	try {
	        		agregarCarta(new ControladorCartaMagia((CartaMagia) carta));
	        	}
	        	catch(ClassCastException e2)
	        	{
	                try {
	                	agregarCarta(new ControladorCartaTrampa((CartaTrampa) carta));
	               }
	                catch(ClassCastException e3)
	            	{
	                	agregarCarta(new ControladorCartaCampo((CartaCampo) carta));
	            	}
	        	}
	        }
			
		}
		
	}

	public void agregarCarta(ControladorCartaAlGoOh carta) {
		controladoresCartas.add(carta);
		representacionCartas.getChildren().add(carta.obtenerVisualizacion());
	}
	
	public ControladorCartaAlGoOh obtenerCartaTope() {
		int ultPos = controladoresCartas.size() - 1;
		ControladorCartaAlGoOh ultimaCarta = controladoresCartas.get(ultPos);
		controladoresCartas.remove(ultPos);
		this.representacionCartas.getChildren().remove(ultimaCarta.obtenerVisualizacion());
		return ultimaCarta;
	}
	
	public abstract Object obtenerRepresentacion();

	public void quitarCarta(ControladorCartaAlGoOh carta) {
		controladoresCartas.remove(carta);
		representacionCartas.getChildren().remove(carta.obtenerVisualizacion());	
	}
	
	public ControladorCartaAlGoOh obtenerControlador(Button carta) {
		for(ControladorCartaAlGoOh controlador: this.controladoresCartas) {
			if (carta.equals(controlador.obtenerVisualizacion())) {
				return controlador;
			}
		}
		return null;
	}
	
	public ControladorCartaAlGoOh obtenerControlador(Carta carta) {
		for(ControladorCartaAlGoOh controlador: this.controladoresCartas) {
			if (carta.toString().equals(controlador.obtenerCarta().toString())) {
				return controlador;
			}
		}
		return null;
	}
	
	

	public List<ControladorCartaAlGoOh> obtenerControladores() {
		return this.controladoresCartas;
	}

	public ControladorCartaAlGoOh verTope(){
		int ultPos = controladoresCartas.size() - 1;
		return controladoresCartas.get(ultPos);
	}
}
