package vista.eventos;

import controlador.ControladorPartidaAlGoOh;
import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaMagia;
import controlador.cartas.ControladorCartaTrampa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public class ClicEnUnaDeLasCartasTrampaOMagiaInvocadasEvent implements EventHandler<MouseEvent> {

    private final ControladorPartidaAlGoOh controladorPartida;

    public ClicEnUnaDeLasCartasTrampaOMagiaInvocadasEvent(ControladorPartidaAlGoOh controladorPartida) {
        this.controladorPartida = controladorPartida;
    }

    @Override
    public void handle(MouseEvent e) {
        ControladorCartaAlGoOh carta = null;

        try {
            carta = (ControladorCartaMagia) controladorPartida.obtenerJugadorActivo().obtenerControladorDeCartasMagiaYTrampaInvocadas((Button) e.getTarget());
    		
            if(carta == null) {
				ControladorCartaAlGoOh cartaOponente = (ControladorCartaMagia) controladorPartida.obtenerJugadorPasivo().obtenerControladorEnMano((Button) e.getTarget());
	
				if (cartaOponente != null && cartaOponente.obtenerCarta().estaInvocadaBocaArriba())
						controladorPartida.setInfoCarta(cartaOponente.obtenerCarta());
				
				return;
			}
            
            
            controladorPartida.setInfoCarta(carta.obtenerCarta());
            
            ContextMenu menuDesplegableMagia = new ContextMenu();
            MenuItem ponerCartaMagiaBocaArriba = new MenuItem("Poner Carta Magia Boca Arriba");
            final CartaMagia cartaMagia = ((ControladorCartaMagia) carta).obtenerCarta();
            menuDesplegableMagia.getItems().add(ponerCartaMagiaBocaArriba);
            ponerCartaMagiaBocaArriba.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent e){

                    try {
                    	if(!controladorPartida.obtenerJugadorActivo().obtenerJugador().obtenerCartasMagiaYTrampa().tieneLaCarta(cartaMagia))
                    	{
                    		controladorPartida.mensajePorCartaAjena();
                			return;
                    	}
                    	
                    	controladorPartida.obtenerPartida().cambiarCartaMagiaABocaArriba(cartaMagia);
                        controladorPartida.actualizarCartasEnElCementerio();
                        controladorPartida.actualizarCartasEnElMazo();
                        controladorPartida.actualizarPuntosDeVida();
                    }

                    catch(FuncionNoAccesibleException e2)
                    {
                    	controladorPartida.mensajePorFuncionNoAccesible();
                    }


                    catch (MazoLlenoException | CartaAlGoOhInexistenteException | MazoVacioException e1) {
                        e1.printStackTrace();
                    }
                }
            }); 
            carta.obtenerVisualizacion().setContextMenu(menuDesplegableMagia);
        } 
        
        catch(ClassCastException e3){
        	
        	try {    
        		carta = (ControladorCartaTrampa) controladorPartida.obtenerJugadorActivo().obtenerControladorDeCartasMagiaYTrampaInvocadas((Button) e.getTarget());
        		if(carta == null) {
        			controladorPartida.mensajePorCartaAjena();
        			return;
        		}
        		controladorPartida.setInfoCarta(carta.obtenerCarta());
                controladorPartida.actualizarCartasEnElCementerio();
                controladorPartida.actualizarCartasEnElMazo();
                controladorPartida.actualizarPuntosDeVida();	
        	}
        	

            catch(ClassCastException e4){
            	
            }
        	
        }
        
    }

}
