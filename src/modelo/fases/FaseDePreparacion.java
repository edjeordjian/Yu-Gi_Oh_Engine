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
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;

public class FaseDePreparacion implements Fase {

    private CartaMonstruo cartaMonstruoInvocadaEnEstaFase = null;

    
	@Override
	public String toString() {
		return "FASE DE PREPARACION";
	}

	@Override
    public void ponerCartaEnLaMano(Jugador jugadorActivo) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
	}
	
	public void darEfectoAOponentePorCartaCampo(Jugador jugadorInvocadorDeCartaCampo, CartaMonstruo cartaOponente) throws CartaAlGoOhInexistenteException, MazoLlenoException, MazoVacioException {
		jugadorInvocadorDeCartaCampo.darEfectoAOponentePorCartaCampo(cartaOponente);
	}
	

	@Override
    public void invocarCartaMonstruoEnPosicionAtaque(Jugador jugadorActivo,  Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException, MonstruoYaInvocadoException {
        if (cartaMonstruoInvocadaEnEstaFase != null) {
            throw new MonstruoYaInvocadoException();
        } 
        
        else {
    	    jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta, cartasASacrificar);
    	    darEfectoAOponentePorCartaCampo(jugadorPasivo, carta);
        	cartaMonstruoInvocadaEnEstaFase = carta;
        }
		
	}

	
	@Override
    public void invocarCartaMonstruoEnPosicionDefensa(Jugador jugadorActivo,  Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, MonstruoYaInvocadoException {
        if (cartaMonstruoInvocadaEnEstaFase != null) {
            throw new MonstruoYaInvocadoException();
        } 
        
        else {
        	jugadorActivo.invocarCartaMonstruoEnPosicionDefensa(carta, cartasASacrificar);
            darEfectoAOponentePorCartaCampo(jugadorPasivo, carta);
        	cartaMonstruoInvocadaEnEstaFase = carta;
        }
        
	}


	@Override
    public void invocarCartaMonstruoEnPosicionDefensaBocaAbajo(Jugador jugadorActivo, Jugador jugadorPasivo,  CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, MonstruoYaInvocadoException {
        if (cartaMonstruoInvocadaEnEstaFase != null) {
            throw new MonstruoYaInvocadoException();
        } 
        else {
            jugadorActivo.invocarCartaMonstruoEnPosicionDefensaBocaAbajo(carta, cartasASacrificar);
            darEfectoAOponentePorCartaCampo(jugadorPasivo, carta);
        	cartaMonstruoInvocadaEnEstaFase = carta;
        }
	}

    @Override
    public void invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(Jugador jugadorActivo, Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MonstruoYaInvocadoException {
        if (cartaMonstruoInvocadaEnEstaFase != null) {
            throw new MonstruoYaInvocadoException();
        } 
        
        else {
        	jugadorActivo.invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(carta, sacrificios);
        	darEfectoAOponentePorCartaCampo(jugadorPasivo, carta);
        	cartaMonstruoInvocadaEnEstaFase = carta;
        }
        
    }
	
	@Override
    public void invocarCartaTrampaBocaAbajo(Jugador jugadorActivo, CartaTrampa cartaTrampa) throws CartaAlGoOhInexistenteException, MazoLlenoException {
		jugadorActivo.invocarCartaTrampaBocaAbajo(cartaTrampa);
	}
	

    @Override
    public void atacarCartaMonstruoConCartaMonstruo(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
	}


    @Override
    public void invocarCartaCampoBocaArriba(CartaCampo cartaCampo, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
        jugadorActivo.invocarCartaCampoBocaArriba(cartaCampo, jugadorPasivo);
    }

    @Override
    public void invocarCartaMagiaBocaArriba(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException {
        jugadorActivo.invocarCartaMagiaBocaArriba(cartaMagia, jugadorPasivo);
    }

    @Override
    public void invocarCartaMagiaBocaAbajo(CartaMagia cartaMagia, Jugador jugadorActivo) throws CartaAlGoOhInexistenteException, MazoLlenoException {
        jugadorActivo.invocarCartaMagiaBocaAbajo(cartaMagia);
    }

    @Override
    public void cambiarCartaMagiaABocaArriba(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void atacarALosPuntosDeVida(CartaMonstruo carta, Jugador jugadorActivo, Jugador jugadorPasivo) throws AtaqueALosPuntosDeVidaInvalidoException, MazoLlenoException, CartaAlGoOhInexistenteException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public Fase cambiarDeFase() {
        return new FaseDeAtaque();
    }

    @Override
    public void activarCartaMagiaDesdeLaMano(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException {
        throw new FuncionNoAccesibleException();
    }

    @Override
    public void cambiarCartaMonstruoABocaArriba(CartaMonstruo carta, Jugador jugadorPasivo) throws FuncionNoAccesibleException {
	    if (carta == cartaMonstruoInvocadaEnEstaFase) throw new FuncionNoAccesibleException();
	    	carta.activarInvocacionBocaArriba(jugadorPasivo);
    }

    @Override
    public void cambiarCartaMonstruoDePosicion(CartaMonstruo carta) throws FuncionNoAccesibleException {
        if (carta == cartaMonstruoInvocadaEnEstaFase) throw new FuncionNoAccesibleException();
        carta.cambiarDePosicion();
    }

	@Override
	public void cambiarCartaMonstruoABocaAbajo(CartaMonstruo carta) throws FuncionNoAccesibleException {
	    if (carta == cartaMonstruoInvocadaEnEstaFase) throw new FuncionNoAccesibleException();
    		carta.activarInvocacionBocaAbajo();
	}

	@Override
	public void cambiarCartaMonstruoAModoAtaque(CartaMonstruo carta) throws FuncionNoAccesibleException {
	    if (carta == cartaMonstruoInvocadaEnEstaFase) throw new FuncionNoAccesibleException();
		carta.cambiarAPosicionDeAtaque();
	}

	@Override
	public void cambiarCartaMonstruoAModoDefensa(CartaMonstruo carta) throws FuncionNoAccesibleException {
	    if (carta == cartaMonstruoInvocadaEnEstaFase) throw new FuncionNoAccesibleException();
			carta.cambiarAPosicionDeDefensa();
	}

}