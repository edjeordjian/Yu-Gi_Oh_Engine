package vista.eventos;

import controlador.ControladorPartidaAlGoOh;
import controlador.cartas.ControladorCartaMonstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.MazoAlGoOh;
import modelo.cartas.cartasMonstruo.CartaMonstruo;

public abstract class InvocacionDeMonstruoEvent implements EventHandler<ActionEvent>{
	
	protected MazoAlGoOh<CartaMonstruo> sacrificios;
	protected ControladorCartaMonstruo carta;
	protected ControladorPartidaAlGoOh controladorPartida;
	
	public InvocacionDeMonstruoEvent(ControladorPartidaAlGoOh controladorPartida, ControladorCartaMonstruo carta , MazoAlGoOh<CartaMonstruo> sacrificios) {
		this.controladorPartida = controladorPartida;
		this.carta = carta;
		this.sacrificios = sacrificios;
	}
		
}
