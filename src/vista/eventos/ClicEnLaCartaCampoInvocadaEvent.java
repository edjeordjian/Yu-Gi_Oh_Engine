package vista.eventos;

import controlador.ControladorPartidaAlGoOh;
import controlador.cartas.ControladorCartaCampo;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ClicEnLaCartaCampoInvocadaEvent implements EventHandler<MouseEvent> {

    private final ControladorPartidaAlGoOh controladorPartida;

    public ClicEnLaCartaCampoInvocadaEvent(ControladorPartidaAlGoOh controlador) {
        this.controladorPartida = controlador;
    }

    @Override
    public void handle(MouseEvent event) {

        ControladorCartaCampo carta = (ControladorCartaCampo) controladorPartida.obtenerJugadorActivo().obtenerControladorDeCartaCampoInvocada((Button) event.getTarget());
        if(carta != null)
        	controladorPartida.setInfoCarta(carta.obtenerCarta());
        else
            carta = (ControladorCartaCampo) controladorPartida.obtenerJugadorPasivo().obtenerControladorDeCartaCampoInvocada((Button) event.getTarget());
            if(carta != null)
                controladorPartida.setInfoCarta(carta.obtenerCarta());
        }
    }
