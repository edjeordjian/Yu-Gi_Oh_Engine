package modelo.fases;

import java.util.ArrayList;
import java.util.List;

import modelo.Jugador;
import modelo.MazoAlGoOh;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.SacrificioInvalidoException;

public class FaseDeAtaque implements Fase {

    private List<CartaMonstruo> monstruosQueAtacaron = new ArrayList<>();

	@Override
	public String toString() {
		return "FASE DE ATAQUE";
	}
	
    @Override
    public void ponerCartaEnLaMano(Jugador jugadorActivo) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaMonstruoEnPosicionAtaque(Jugador jugadorActivo, Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaMonstruoEnPosicionDefensa(Jugador jugadorActivo,  Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaMonstruoEnPosicionDefensaBocaAbajo(Jugador jugadorActivo,  Jugador jugadorPasivo,  CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaTrampaBocaAbajo(Jugador jugadorActivo, CartaTrampa cartaTrampa) throws CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void atacarCartaMonstruoConCartaMonstruo(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, MonstruoYaAtacoException, MonstruoNoPuedeAtacarException {
    	if (cartaAtacante.estaInvocadaBocaAbajo()) 
    		throw new MonstruoNoPuedeAtacarException();
    	if (monstruosQueAtacaron.contains(cartaAtacante)) 
    		throw new MonstruoYaAtacoException();
	    jugadorActivo.atacarCartaMonstruoConCartaMonstruo(cartaAtacante, cartaAtacada, jugadorPasivo);
	    monstruosQueAtacaron.add(cartaAtacante);
	    if(cartaAtacada.estaInvocadaBocaAbajo()) 
	    	cartaAtacada.activarInvocacionBocaArriba(null);
    }

    @Override
    public void invocarCartaCampoBocaArriba(CartaCampo cartaCampo, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaMagiaBocaArriba(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void atacarALosPuntosDeVida(CartaMonstruo carta, Jugador jugadorActivo, Jugador jugadorPasivo) throws AtaqueALosPuntosDeVidaInvalidoException, MazoLlenoException, CartaAlGoOhInexistenteException, MonstruoYaAtacoException, MonstruoNoPuedeAtacarException {
        if (monstruosQueAtacaron.contains(carta)) throw new MonstruoYaAtacoException();
        if (carta.estaEnPosicionDeDefensa() || carta.estaInvocadaBocaAbajo()) throw new MonstruoNoPuedeAtacarException();
        jugadorActivo.atacarALosPuntosDeVida(carta, jugadorPasivo);
        monstruosQueAtacaron.add(carta);
    }

    @Override
    public Fase cambiarDeFase() {
        return new FaseFinal();
    }

    @Override
    public void activarCartaMagiaDesdeLaMano(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(Jugador jugadorActivo, Jugador jugadorPasivo,  CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void cambiarCartaMonstruoABocaArriba(CartaMonstruo carta, Jugador jugadorActivo) throws FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void cambiarCartaMonstruoDePosicion(CartaMonstruo carta) throws FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void invocarCartaMagiaBocaAbajo(CartaMagia cartaMagia, Jugador jugadorActivo) throws CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void cambiarCartaMagiaABocaArriba(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {

    }

	@Override
	public void cambiarCartaMonstruoABocaAbajo(CartaMonstruo carta) throws FuncionNoAccesibleException {
		 throw new FuncionNoAccesibleException();
	}

	@Override
	public void cambiarCartaMonstruoAModoAtaque(CartaMonstruo carta) throws FuncionNoAccesibleException {
		 throw new FuncionNoAccesibleException();
	}

	@Override
	public void cambiarCartaMonstruoAModoDefensa(CartaMonstruo carta) throws FuncionNoAccesibleException {
		 throw new FuncionNoAccesibleException();
		
	}


}
