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
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;

public interface Fase {

	
	@Override
	public String toString();
	
    void ponerCartaEnLaMano(Jugador jugadorActivo) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException;

    void invocarCartaMonstruoEnPosicionAtaque(Jugador jugadorActivo, Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException, MonstruoYaInvocadoException;

    void invocarCartaMonstruoEnPosicionDefensa(Jugador jugadorActivo, Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException, MonstruoYaInvocadoException;

    void invocarCartaMonstruoEnPosicionDefensaBocaAbajo(Jugador jugadorActivo, Jugador jugadorPasivo, CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> cartasASacrificar) throws MazoLlenoException, CartaAlGoOhInexistenteException, SacrificioInvalidoException, MazoVacioException, FuncionNoAccesibleException, MonstruoYaInvocadoException;

    void invocarCartaTrampaBocaAbajo(Jugador jugadorActivo, CartaTrampa cartaTrampa) throws CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException;

    void atacarCartaMonstruoConCartaMonstruo(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, MonstruoYaAtacoException, FuncionNoAccesibleException, MonstruoNoPuedeAtacarException;

    void invocarCartaCampoBocaArriba(CartaCampo cartaCampo, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException;

    void invocarCartaMagiaBocaArriba(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException;

    void atacarALosPuntosDeVida(CartaMonstruo carta, Jugador jugadorActivo, Jugador jugadorPasivo) throws AtaqueALosPuntosDeVidaInvalidoException, MazoLlenoException, CartaAlGoOhInexistenteException, MonstruoYaAtacoException, MonstruoNoPuedeAtacarException, FuncionNoAccesibleException;

    Fase cambiarDeFase() throws FuncionNoAccesibleException;

    void activarCartaMagiaDesdeLaMano(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException;

    void invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(Jugador jugadorActivo,  Jugador jugadorPasivo,  CartaMonstruo carta, MazoAlGoOh<CartaMonstruo> sacrificios) throws MazoVacioException, CartaAlGoOhInexistenteException, MazoLlenoException, SacrificioInvalidoException, FuncionNoAccesibleException, MonstruoYaInvocadoException;

    void cambiarCartaMonstruoABocaArriba(CartaMonstruo carta, Jugador jugadorPasivo) throws FuncionNoAccesibleException;

    void cambiarCartaMonstruoDePosicion(CartaMonstruo carta) throws FuncionNoAccesibleException;

    void invocarCartaMagiaBocaAbajo(CartaMagia cartaMagia, Jugador jugadorActivo) throws CartaAlGoOhInexistenteException, MazoLlenoException, FuncionNoAccesibleException;

    void cambiarCartaMagiaABocaArriba(CartaMagia cartaMagia, Jugador jugadorActivo, Jugador jugadorPasivo) throws MazoLlenoException, CartaAlGoOhInexistenteException, MazoVacioException, FuncionNoAccesibleException;

    void cambiarCartaMonstruoAModoAtaque(CartaMonstruo carta) throws FuncionNoAccesibleException;

	public void cambiarCartaMonstruoABocaAbajo(CartaMonstruo carta) throws FuncionNoAccesibleException;

	public void cambiarCartaMonstruoAModoDefensa(CartaMonstruo carta) throws FuncionNoAccesibleException;
}
