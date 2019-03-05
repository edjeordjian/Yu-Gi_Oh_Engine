package vista.eventos;

import controlador.ControladorPartidaAlGoOh;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ClicEnUnaDeLasCartasDelMazoEvent implements EventHandler<MouseEvent> {


    private final ControladorPartidaAlGoOh controladorPartida;

    public ClicEnUnaDeLasCartasDelMazoEvent(ControladorPartidaAlGoOh controladorPartida) {
        this.controladorPartida = controladorPartida;
    }

    @Override
    public void handle(MouseEvent e) {
    	
    	if ((Button) e.getTarget() != controladorPartida.obtenerJugadorActivo().obtenerControladorMazo())
			return;
    	
        controladorPartida.ponerCartaEnLaManoDeJugadorActivo();
    }
}
