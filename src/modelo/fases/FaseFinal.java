package modelo.fases;


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
import modelo.excepciones.SacrificioInvalidoException;

public class FaseFinal implements Fase {

	@Override
	public String toString() {
		return "FASE FINAL";
	}

	@Override
    public void ponerCartaEnLaMano(Jugador jugadorActivo) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
	}


    @Override
    public void invocarCartaMonstruoEnPosicionAtaque(Jugador jugadorActivo,  Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException {
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
    public void atacarCartaMonstruoConCartaMonstruo(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
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
    public void atacarALosPuntosDeVida(CartaMonstruo carta, Jugador jugadorActivo, Jugador jugadorPasivo) throws AtaqueALosPuntosDeVidaInvalidoException, MazoLlenoException, CartaAlGoOhInexistenteException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public Fase cambiarDeFase() throws FuncionNoAccesibleException{
       throw new FuncionNoAccesibleException();
    }

    @Override
    public void activarCartaMagiaDesdeLaMano(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
        jugadorActivo.activarCartaMagia(cartaMagia, jugadorPasivo);
    }

    @Override
    public void invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(Jugador jugadorActivo,  Jugador jugadorPasivo,  CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws FuncionNoAccesibleException {
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
        cartaMagia.activarInvocacionBocaArriba(jugadorPasivo);
	    jugadorActivo.activarCartaMagia(cartaMagia, jugadorPasivo);
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