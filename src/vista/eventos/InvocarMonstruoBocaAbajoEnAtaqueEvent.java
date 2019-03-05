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

public class InvocarMonstruoBocaAbajoEnAtaqueEvent extends InvocacionDeMonstruoEvent{

	public InvocarMonstruoBocaAbajoEnAtaqueEvent(ControladorPartidaAlGoOh controladorPartida,
		ControladorCartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) {
	super(controladorPartida, carta, sacrificios);
	}
	
	@Override
	public void handle(ActionEvent event){
			
				try {
					controladorPartida.obtenerPartida().invocarCartaMonstruoEnPosicionAtaqueBocaAbajoDeJugadorActivo( carta.obtenerCarta(), sacrificios);
					controladorPartida.obtenerJugadorActivo().invocarCartaMonstruoEnPosicionDeAtaqueBocaAbajo(carta);
					controladorPartida.actualizarCartasEnElCementerio();
				} 

				catch (FuncionNoAccesibleException e1) {
					controladorPartida.mensajePorFuncionNoAccesible();
				}
				
				catch (CartaAlGoOhInexistenteException 
						| MazoVacioException e) {
					controladorPartida.mensajePorCartaAjena();
				}
				

				catch (SacrificioInvalidoException e) {
					controladorPartida.mensajePorSacrificioInvalido();
				}
				
				catch (MonstruoYaInvocadoException e) {
					controladorPartida.mensajeMonstruoYaInvocado();
				}

				catch (MazoLlenoException e) {
					controladorPartida.mensajePorExcesoDeCartasMonstruo();
				}
				
				controladorPartida.actualizarSacrificiosParaJugadorActivo();
				controladorPartida.actualizarCartasEnElCementerio();
	}
	

}
