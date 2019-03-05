package vista.eventos;

import java.util.List;

import controlador.ControladorPartidaAlGoOh;
import controlador.cartas.ControladorCartaAlGoOh;
import controlador.cartas.ControladorCartaMonstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;

public class ClicEnUnoDeLosMonstruosInvocadosEvent implements EventHandler<MouseEvent>{

	private  ControladorCartaMonstruo carta;
	private ControladorPartidaAlGoOh controladorPartida;
	private final EventHandler<ActionEvent> agregarSacrificio = new EventHandler<ActionEvent>(){		
		@Override
		public void handle(ActionEvent event){
			try {
			controladorPartida.agregarSacrificioParaJugadorActivo(carta);
			}
			catch (NullPointerException e) {
				controladorPartida.mensajePorCartaAjena();
			}
		}
	};
	
	public ClicEnUnoDeLosMonstruosInvocadosEvent(ControladorPartidaAlGoOh controladorPartida) {
		this.controladorPartida = controladorPartida;
	}
	
	@Override
	public void handle(MouseEvent e) {
		
		ContextMenu opcionesSecundariasMonstruo = new ContextMenu();
		MenuItem sacrificar = new MenuItem("Agregar a Sacrificios");
		
		try {
			carta = (ControladorCartaMonstruo) controladorPartida.obtenerJugadorActivo().obtenerControladorEnMonstruosInvocados((Button) e.getTarget());
			
			if(carta == null) {
				//si toco una carta del oponente
				ControladorCartaAlGoOh cartaOponente = (ControladorCartaMonstruo) controladorPartida.obtenerJugadorPasivo().obtenerControladorEnMonstruosInvocados((Button) e.getTarget());

				opcionesSecundariasMonstruo.getItems().add(sacrificar);
				
				if (cartaOponente.obtenerCarta().estaInvocadaBocaArriba())
						controladorPartida.setInfoCarta(cartaOponente.obtenerCarta());
				
				return;
			} else controladorPartida.setInfoCarta(carta.obtenerCarta());
			
		}
		
		catch(ClassCastException e3){
			return;
		}

		List<ControladorCartaAlGoOh> monstruosEnemigos =
				controladorPartida.obtenerJugadorPasivo().obtenerControladoresDeMonstruosInvocados();

		for(ControladorCartaAlGoOh monstruoEnemigo : monstruosEnemigos) {

			final ControladorCartaMonstruo enemigo = (ControladorCartaMonstruo) monstruoEnemigo;
			MenuItem opcionDeAtaque;
			
			if(enemigo.obtenerCarta().estaInvocadaBocaArriba())
				opcionDeAtaque = new MenuItem("Atacar a " + enemigo.toString());
			else 
				opcionDeAtaque = new MenuItem("Atacar a Monstruo Boca Abajo");
				
			opcionesSecundariasMonstruo.getItems().add(opcionDeAtaque);

			opcionDeAtaque.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event){
					try {
						controladorPartida.obtenerPartida().atacarCartaMonstruoConCartaMonstruo(carta.obtenerCarta(), enemigo.obtenerCarta());
						controladorPartida.obtenerJugadorActivo().cambiarCartaMonstruoABocaArriba(carta);
				        controladorPartida.actualizarCartasEnElCementerio();
						controladorPartida.actualizarPuntosDeVida();
						controladorPartida.verificarFinDelJuego();
						controladorPartida.actualizarCartasEnMano();
						controladorPartida.actualizarCartasMonstruoInvocadas();
					} 
					
					catch(MonstruoNoPuedeAtacarException e2)
					{
						controladorPartida.mensajePorAtaqueInvalido();
						}
					
					catch(FuncionNoAccesibleException e)
					{
						controladorPartida.mensajePorAtaqueInvalido();
					}

					catch (MonstruoYaAtacoException e) {
						controladorPartida.mensajePorAtaqueDobleDeMonstruo();
					}

					catch (MazoLlenoException e) {
						controladorPartida.mensajePorExcesoDeCartasMonstruo();
					}
					catch( CartaAlGoOhInexistenteException | MazoVacioException e) {
						controladorPartida.mensajePorCartaAjena();
					}
				}
			});
			
			
		}

		MenuItem atacarPuntosDeVida = new MenuItem("Atacar a puntos de vida");
		opcionesSecundariasMonstruo.getItems().add(atacarPuntosDeVida);
		atacarPuntosDeVida.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event){
			try {
				controladorPartida.obtenerPartida().atacarJugadorPasivoALosPuntosDeVidaCon(carta.obtenerCarta());
				controladorPartida.actualizarPuntosDeVida();
				controladorPartida.verificarFinDelJuego();
				controladorPartida.actualizarCartasEnElCementerio();
			}

			catch (MazoLlenoException e) {
				controladorPartida.mensajePorExcesoDeCartasMonstruo();
			}
			catch( CartaAlGoOhInexistenteException  | NullPointerException e) {
				controladorPartida.mensajePorCartaAjena();
			}
			
			catch (MonstruoYaAtacoException e2) {
				controladorPartida.mensajePorAtaqueDobleDeMonstruo();
			}
			
			catch (AtaqueALosPuntosDeVidaInvalidoException e3){
				controladorPartida.mensajePorMonstruosEnElCampo();
			  }
			
			catch (MonstruoNoPuedeAtacarException | FuncionNoAccesibleException e3){
				controladorPartida.mensajePorAtaqueInvalido();
			}
	
		}
		});
		
		
		opcionesSecundariasMonstruo.getItems().add(sacrificar);
		sacrificar.setOnAction(agregarSacrificio);
		
		
		MenuItem noSacrificar = new MenuItem("Quitar de Sacrificios");
		opcionesSecundariasMonstruo.getItems().add(noSacrificar);
		noSacrificar.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try {
				controladorPartida.quitarSacrificioParaJugadorActivo(carta);
				}
				catch(NullPointerException e) {
					controladorPartida.mensajePorCartaAjena();
				}
			}
		});
		


		MenuItem ponerBocaArriba = new MenuItem("Poner Boca Arriba");
		opcionesSecundariasMonstruo.getItems().add(ponerBocaArriba);
		ponerBocaArriba.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				
				try{
				if (carta.obtenerCarta().estaInvocadaBocaArriba())
					controladorPartida.mensajePorCartaYaBocaArriba();
				
				else {
					
						controladorPartida.obtenerPartida().cambiarCartaMonstruoABocaArriba(carta.obtenerCarta());
						controladorPartida.obtenerJugadorActivo().cambiarCartaMonstruoABocaArriba(carta);
				        controladorPartida.actualizarCartasEnElCementerio();
						controladorPartida.actualizarCartasMonstruoInvocadas();

						controladorPartida.obtenerJugadorActivo().actualizarCartasEnElCementerio();
						controladorPartida.obtenerJugadorPasivo().actualizarCartasEnElCementerio();

					}
					
				}
					catch(FuncionNoAccesibleException e) {
						controladorPartida.mensajePorCambioInvalido();
					}
					
					catch(NullPointerException e) {
						controladorPartida.mensajePorCartaAjena();
					}
				
			}
		});
		
		
		MenuItem ponerBocaAbajo = new MenuItem("Poner Boca Abajo");
		opcionesSecundariasMonstruo.getItems().add(ponerBocaAbajo);
		ponerBocaAbajo.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				
				try{
					if (carta.obtenerCarta().estaInvocadaBocaAbajo())
					controladorPartida.mensajePorCartaYaBocaAbajo();
				
				
				else {
						controladorPartida.obtenerPartida().cambiarCartaMonstruoABocaAbajo(carta.obtenerCarta());
						controladorPartida.obtenerJugadorActivo().cambiarCartaMonstruoABocaAbajo(carta);
						//controladorPartida.actualizarCartas();
					}
				}
				
				catch(FuncionNoAccesibleException e) {
						controladorPartida.mensajePorCambioInvalido();
					}
					
				catch(NullPointerException e) {
						controladorPartida.mensajePorCartaAjena();
					}
				
			}
		});
		

		
		MenuItem ponerEnAtaque = new MenuItem("Poner en Modo Ataque");
		opcionesSecundariasMonstruo.getItems().add(ponerEnAtaque);
		ponerEnAtaque.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
				if (carta.obtenerCarta().estaEnPosicionDeAtaque())
					controladorPartida.mensajePorCartaYaEnModoAtaque();
				
				else {
					
						controladorPartida.obtenerPartida().cambiarCartaMonstruoAModoAtaque(carta.obtenerCarta());
						controladorPartida.obtenerJugadorActivo().cambiarCartaMonstruoAModoAtaque(carta);
					}
					
				}
					catch(FuncionNoAccesibleException e) {
						controladorPartida.mensajePorCambioInvalido();
						}
					
					catch(NullPointerException e) {
						controladorPartida.mensajePorCartaAjena();
					}
				
			}
		});
		

		MenuItem ponerEnDefensa= new MenuItem("Poner en Modo Defensa");
		opcionesSecundariasMonstruo.getItems().add(ponerEnDefensa);
		ponerEnDefensa.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				
				try{
				if (carta.obtenerCarta().estaEnPosicionDeDefensa())
					controladorPartida.mensajePorCartaYaEnModoDefensa();
				
				else {
					
						controladorPartida.obtenerPartida().cambiarCartaMonstruoAModoDefensa(carta.obtenerCarta());
						controladorPartida.obtenerJugadorActivo().cambiarCartaMonstruoAModoDefensa(carta);
				   }
				}
					
					catch(FuncionNoAccesibleException e) {
						controladorPartida.mensajePorCambioInvalido();
						}
					
					catch(NullPointerException e) {
						controladorPartida.mensajePorCartaAjena();
					}
				
			}
		});

		carta.obtenerVisualizacion().setContextMenu(opcionesSecundariasMonstruo);
	}
	

}
