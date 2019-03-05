package vista.eventos;

import controlador.ControladorPartidaAlGoOh;
import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaCampo;
import controlador.cartas.ControladorCartaMagia;
import controlador.cartas.ControladorCartaMonstruo;
import controlador.cartas.ControladorCartaTrampa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modelo.MazoAlGoOh;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;

public class ClicEnUnaDeLasCartasDeLaManoEvent implements EventHandler<MouseEvent>{
	
	private ControladorPartidaAlGoOh controladorPartida;
	
	public ClicEnUnaDeLasCartasDeLaManoEvent(ControladorPartidaAlGoOh controladorPartida) {
		this.controladorPartida = controladorPartida;
	}
	
	@Override
	public void handle(MouseEvent e) {

		ControladorCartaAlGoOh carta = null;
		ContextMenu opcionesSecundariasMonstruo = new ContextMenu();
		
		try {
			
			try  {
				carta = (ControladorCartaMonstruo) controladorPartida.obtenerJugadorActivo().obtenerControladorEnMano((Button) e.getTarget());

				if(carta == null) {
					ControladorCartaAlGoOh cartaOponente = (ControladorCartaMonstruo) controladorPartida.obtenerJugadorPasivo().obtenerControladorEnMano((Button) e.getTarget());
		
					if (cartaOponente.obtenerCarta().estaInvocadaBocaArriba())
							controladorPartida.setInfoCarta(cartaOponente.obtenerCarta());

					return;
				}
				
				invocacionCartaMonstruo(e, (ControladorCartaMonstruo) carta, opcionesSecundariasMonstruo);
				controladorPartida.setInfoCarta(carta.obtenerCarta());
			}
			
			catch (ClassCastException e5) {
				
				try {
					carta = (ControladorCartaMagia) controladorPartida.obtenerJugadorActivo().obtenerControladorEnMano((Button) e.getTarget());
					
					if(carta == null) {
						ControladorCartaAlGoOh cartaOponente = (ControladorCartaMagia) controladorPartida.obtenerJugadorPasivo().obtenerControladorEnMano((Button) e.getTarget());
			
						if (cartaOponente.obtenerCarta().estaInvocadaBocaArriba())
								controladorPartida.setInfoCarta(cartaOponente.obtenerCarta());
						
						return;
					}
					
					invocacionCartaMagia(e, (ControladorCartaMagia) carta);
					controladorPartida.setInfoCarta(carta.obtenerCarta());
				}
				 
				 catch (ClassCastException e6) {
				    try {
                        carta = (ControladorCartaTrampa) controladorPartida.obtenerJugadorActivo().obtenerControladorEnMano((Button) e.getTarget());
                        
                        if(carta == null) {
    						ControladorCartaAlGoOh cartaOponente = (ControladorCartaTrampa) controladorPartida.obtenerJugadorPasivo().obtenerControladorEnMano((Button) e.getTarget());
    			
    						if (cartaOponente.obtenerCarta().estaInvocadaBocaArriba())
    								controladorPartida.setInfoCarta(cartaOponente.obtenerCarta());
    						
    						return;
    					}
                        
                        invocacionCartaTrampa(e, (ControladorCartaTrampa) carta);
                        controladorPartida.setInfoCarta(carta.obtenerCarta());
                    } 
				    
				    catch (ClassCastException e7) {
				        carta = (ControladorCartaCampo) controladorPartida.obtenerJugadorActivo().obtenerControladorEnMano((Button) e.getTarget());
				        
				        if(carta == null) {
    						ControladorCartaAlGoOh cartaOponente = (ControladorCartaCampo) controladorPartida.obtenerJugadorPasivo().obtenerControladorEnMano((Button) e.getTarget());
    			
    						if (cartaOponente.obtenerCarta().estaInvocadaBocaArriba())
    								controladorPartida.setInfoCarta(cartaOponente.obtenerCarta());
    						
    						return;
    					}
                        
				        invocacionCartaCampo(e, (ControladorCartaCampo) carta);
				        controladorPartida.setInfoCarta(carta.obtenerCarta());	
				    }
				 }
			}

            controladorPartida.actualizarCartasEnMano();
            //controladorPartida.actualizarCartasMonstruoInvocadas();
            controladorPartida.actualizarCartasEnElMazo();
            controladorPartida.actualizarCartasEnElCementerio();
            controladorPartida.actualizarPuntosDeVida();
			
		}
		
		catch (ClassCastException e4) {
			//ESTO ES CUANDO SE TOCA EL SCROLLPANE
		}
		
		
	}

    private void invocacionCartaCampo(MouseEvent e, final ControladorCartaCampo cartaCampo) {
    	
        if(e.getButton() == MouseButton.PRIMARY) {
            controladorPartida.setInfoCarta(cartaCampo.obtenerCarta());
            return;
        }

        ContextMenu menuCampo = new ContextMenu();
        MenuItem invocarBocaArriba = new MenuItem("Invocar Carta Campo Boca Arriba");
        menuCampo.getItems().add(invocarBocaArriba);
        invocarBocaArriba.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                try {
                    controladorPartida.obtenerPartida().invocarCartaCampoDeJugadorActivoBocaArriba(cartaCampo.obtenerCarta());
                    controladorPartida.obtenerJugadorActivo().invocarCartaCampo(cartaCampo);
                    //controladorPartida.actualizarCartasEnLaMano();
                } 
                
                catch (FuncionNoAccesibleException e1) {
                	controladorPartida.mensajePorFuncionNoAccesible();
				}
                
                catch (CartaAlGoOhInexistenteException | MazoVacioException e1) {
                	controladorPartida.mensajePorCartaAjena();
                    
                } catch (MazoLlenoException e1) {
                   controladorPartida.mensajePorExcesoDeCartasMagiaYTrampa();
                    
                } 
            }});
        
        cartaCampo.obtenerVisualizacion().setContextMenu(menuCampo);
    }


    private void invocacionCartaTrampa(MouseEvent e, final ControladorCartaTrampa carta) {
        if(e.getButton() == MouseButton.PRIMARY) {
            controladorPartida.setInfoCarta(carta.obtenerCarta());
            return;
        }
        
	    ContextMenu menuTrampa = new ContextMenu();
        MenuItem invocarBocaAbajo = new MenuItem("Invocar Carta Trampa Boca Abajo");
        menuTrampa.getItems().add(invocarBocaAbajo);
        invocarBocaAbajo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                try {
                    controladorPartida.obtenerPartida().invocarCartaTrampaBocaAbajo(carta.obtenerCarta());
                    controladorPartida.obtenerJugadorActivo().invocarCartaTrampaBocaAbajo(carta);
                } 
                
                catch (CartaAlGoOhInexistenteException e1) {
                	controladorPartida.mensajePorCartaAjena();
                } 
                
                catch (MazoLlenoException e1) {
                    controladorPartida.mensajePorExcesoDeCartasMagiaYTrampa();
                }
                
                catch(FuncionNoAccesibleException e3)
                {
                	controladorPartida.mensajePorFuncionNoAccesible();
                }
                
            }});
        carta.obtenerVisualizacion().setContextMenu(menuTrampa);
	}


	public void invocacionCartaMagia(MouseEvent e, final ControladorCartaMagia carta) {

		if(e.getButton() == MouseButton.PRIMARY) {
			controladorPartida.setInfoCarta(carta.obtenerCarta());
		}
		
		else
		{
			final ContextMenu menuCartasMagia = new ContextMenu();
			MenuItem invocarBocaAbajo = new MenuItem("Invocar Carta Magia Boca Abajo");
			menuCartasMagia.getItems().add(invocarBocaAbajo);
			invocarBocaAbajo.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e){
					try {
						controladorPartida.obtenerPartida().invocarCartaMagiaBocaAbajoDeJugadorActivo(carta.obtenerCarta());
				        controladorPartida.obtenerJugadorActivo().invocarCartaMagiaBocaAbajo(carta);
					}
					catch (CartaAlGoOhInexistenteException | MazoVacioException e1) {
						controladorPartida.mensajePorCartaAjena();
						} 
					
					catch (MazoLlenoException e1) {
					        controladorPartida.mensajePorExcesoDeCartasMagiaYTrampa();
						} 
					
					catch (FuncionNoAccesibleException e1) {
					        controladorPartida.mensajePorFuncionNoAccesible();
			        }
					}});
			
		    MenuItem activarCartaMagia = new MenuItem("Activar Carta Magia");
		    menuCartasMagia.getItems().add(activarCartaMagia);
		    activarCartaMagia.setOnAction(new EventHandler<ActionEvent>(){

		        @Override
		        public void handle(ActionEvent e){

		            try {
		                controladorPartida.obtenerPartida().activarCartaMagiaDesdeLaMano(carta.obtenerCarta());
		                carta.obtenerVisualizacion().setContextMenu(menuCartasMagia);

		                controladorPartida.actualizarCartasEnElMazo();
		                controladorPartida.actualizarCartasEnElCementerio();
		                controladorPartida.actualizarPuntosDeVida();
		            }

		            catch ( CartaAlGoOhInexistenteException | MazoVacioException e1) {
		            	controladorPartida.mensajePorCartaAjena();
		            } 
		            
		            catch (MazoLlenoException e3) {
		            	controladorPartida.mensajePorExcesoDeCartasMagiaYTrampa();
		            }
		            
		            catch (FuncionNoAccesibleException e1) {
		                controladorPartida.mensajePorFuncionNoAccesible();
		            }
		        }});

		    carta.obtenerVisualizacion().setContextMenu(menuCartasMagia);
		}
	}

    public void invocacionCartaMonstruo(MouseEvent e,  ControladorCartaMonstruo carta, ContextMenu opcionesSecundariasMonstruo ) {
		
		MazoAlGoOh<CartaMonstruo> sacrificios  = new MazoAlGoOh<CartaMonstruo>(0);
		
		if(!controladorPartida.obtenerSacrificiosJugadorActivo().estaVacio())
			sacrificios = controladorPartida.obtenerSacrificiosJugadorActivo();

        if(e.getButton() == MouseButton.PRIMARY) {
           controladorPartida.setInfoCarta(carta.obtenerCarta());
           return;
        }
		
		else
		{
			
			MenuItem ataqueArriba = new MenuItem("Invocar Boca Arriba en Ataque");
			opcionesSecundariasMonstruo.getItems().add(ataqueArriba);
			ataqueArriba.setOnAction(new InvocarMonstruoBocaArribaEnAtaque(controladorPartida, carta, sacrificios));
			
			MenuItem defensaArriba = new MenuItem("Invocar Boca Arriba en Defensa");
			opcionesSecundariasMonstruo.getItems().add(defensaArriba);
			defensaArriba.setOnAction(new InvocarMonstruoBocaArribaEnDefensa(controladorPartida, carta, sacrificios));

			MenuItem ataqueAbajo = new MenuItem("Invocar Boca Abajo en Ataque");
			opcionesSecundariasMonstruo.getItems().add(ataqueAbajo);
			ataqueAbajo.setOnAction(new InvocarMonstruoBocaAbajoEnAtaqueEvent(controladorPartida, carta, sacrificios));
			
			MenuItem defensaAbajo = new MenuItem("Invocar Boca Abajo en Defensa");
			opcionesSecundariasMonstruo.getItems().add(defensaAbajo);
			defensaAbajo.setOnAction(new InvocarMonstruoBocaAbajoEnDefensaEvent(controladorPartida, carta, sacrificios));

			carta.obtenerVisualizacion().setContextMenu(opcionesSecundariasMonstruo);
						
		}
		
	}

}
