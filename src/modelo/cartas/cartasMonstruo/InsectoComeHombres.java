package modelo.cartas.cartasMonstruo;

import modelo.Jugador;
import modelo.cartas.cartasMonstruo.posiciones.PosicionCartaMonstruo;
import modelo.cartas.estados.InvocacionBocaArriba;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;

public class InsectoComeHombres extends CartaMonstruo{

	public InsectoComeHombres() {
		super("Insecto Come Hombres", 450, 600, 2);
	}

	
	@Override	
	public boolean destruyeA(CartaMonstruo cartaAtacada) {
		if  (this.estaInvocadaBocaAbajo() )
			return true;
		
		return super.destruyeA(cartaAtacada);
	}

	
	@Override
	public boolean esDestruidaPor(PosicionCartaMonstruo posicionAtacante) {
		if ( this.estaInvocadaBocaAbajo() )
			return false;
		
		return super.esDestruidaPor(posicionAtacante);
	}
	
	@Override
	public void activarInvocacionBocaArriba(Jugador jugador){
		
		this.estado = new InvocacionBocaArriba();
		
		if(jugador == null)
			return;
		
		try {
			jugador.eliminarUnMonstruoInvocado();
		} 
		catch (CartaAlGoOhInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MazoLlenoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
