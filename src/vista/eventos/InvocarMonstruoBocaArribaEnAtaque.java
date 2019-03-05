package vista.eventos;

import controlador.ControladorPartidaAlGoOh;
import controlador.cartas.ControladorCartaMonstruo;
import javafx.event.ActionEvent;
import modelo.MazoAlGoOh;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;

public class InvocarMonstruoBocaArribaEnAtaque extends vista.eventos.InvocacionDeMonstruoEvent {

	
		public InvocarMonstruoBocaArribaEnAtaque(ControladorPartidaAlGoOh controladorPartida,
			ControladorCartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) {
		super(controladorPartida, carta, sacrificios);
	}

		@Override
		public void handle(ActionEvent event){
			try {
				this.controladorPartida.obtenerPartida().invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo( this.carta.obtenerCarta(), this.sacrificios);
				controladorPartida.obtenerJugadorActivo().invocarCartaMonstruoEnPosicionDeAtaque(carta);
                controladorPartida.actualizarCartasEnElCementerio();
				
			} 
			catch (CartaAlGoOhInexistenteException
					| MazoVacioException  e) {
				controladorPartida.mensajePorCartaAjena();
			}
			catch (FuncionNoAccesibleException e1) {
				controladorPartida.mensajePorFuncionNoAccesible();
				}
			
			catch (SacrificioInvalidoException e) {
				controladorPartida.mensajePorSacrificioInvalido();
				}
			

			catch (MonstruoYaInvocadoException e) {
				controladorPartida.mensajeMonstruoYaInvocado();
				}
			
			catch (MazoLlenoException e6) {
				controladorPartida.mensajePorExcesoDeCartasMonstruo();
				}
			
			controladorPartida.actualizarSacrificiosParaJugadorActivo();
			controladorPartida.actualizarCartasEnElCementerio();
			
		}
	
}
