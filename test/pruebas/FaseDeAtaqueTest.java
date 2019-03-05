package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modelo.Jugador;
import modelo.MazoAlGoOh;
import modelo.Puntos;
import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasCampo.Sogen;
import modelo.cartas.cartasMagia.AgujeroNegro;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
import modelo.cartas.cartasMonstruo.Jinzo7;
import modelo.cartas.cartasTrampa.CartaTrampa;
import modelo.cartas.cartasTrampa.Refuerzos;
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;
import modelo.fases.Fase;
import modelo.fases.FaseDeAtaque;

public class FaseDeAtaqueTest {

    private Fase fase;
    private Jugador jugadorActivo;
    private Jugador jugadorPasivo;

    @Before
    public void setUp() throws Exception {
        this.fase = new FaseDeAtaque();
        this.jugadorActivo = new Jugador("");
        this.jugadorPasivo = new Jugador("");
    }

    @Test
    public void testPonerCartaEnLaManoNoEstaPermitido() {
        Carta carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            fase.ponerCartaEnLaMano(this.jugadorActivo);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
        fail();
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionAtaqueNoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionAtaque(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | MonstruoYaInvocadoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 
        fail();
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionDefensaNoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionDefensa(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | MonstruoYaInvocadoException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        fail();
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionDefensaBocaAbajoNoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionDefensaBocaAbajo(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException |  MonstruoYaInvocadoException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        fail();
    }

    @Test
    public void testInvocarCartaTrampaBocaAbajoNoEstaPermitido() {
        CartaTrampa carta = new Refuerzos();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaTrampaBocaAbajo(this.jugadorActivo, carta);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
        fail();
    }

    @Test
    public void testAtacarCartaMonstruoConCartaMonstruoEstaPermitido() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 0, 0, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            this.jugadorActivo.ponerCartaEnLaMano();
            this.jugadorPasivo.agregarCartaAlMazo(carta2);
            this.jugadorPasivo.ponerCartaEnLaMano();
            jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            jugadorPasivo.invocarCartaMonstruoEnPosicionAtaque(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            fase.atacarCartaMonstruoConCartaMonstruo(carta1, carta2, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            fail();
        } catch (MazoVacioException | MonstruoYaAtacoException | MonstruoNoPuedeAtacarException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 
        assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(0) && jugadorPasivo.tieneLaCantidadDeMonstruos(0));
    }

    @Test
    public void testInvocarCartaCampoBocaArribaNoEstaPermitido() {
        CartaCampo carta = new Sogen();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaCampoBocaArriba(carta, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
        fail();
    }

    @Test
    public void testInvocarCartaMagiaBocaArribaNoEstaPermitido() {
        CartaMagia carta = new AgujeroNegro();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMagiaBocaArriba(carta, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
        fail();
    }

    @Test
    public void testAtacarALosPuntosDeVidaEstaPermitido() {
        CartaMonstruo carta1 = new Jinzo7();
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            this.jugadorActivo.ponerCartaEnLaMano();
            this.jugadorPasivo.agregarCartaAlMazo(carta2);
            this.jugadorPasivo.ponerCartaEnLaMano();
            this.jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            this.jugadorPasivo.invocarCartaMonstruoEnPosicionAtaque(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            fase.atacarALosPuntosDeVida(carta1, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            fail();
        } catch (MazoVacioException | MonstruoYaAtacoException | MonstruoNoPuedeAtacarException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException | AtaqueALosPuntosDeVidaInvalidoException e) {
            e.printStackTrace();
            fail();
        } 
        assertTrue(this.jugadorPasivo.puntosDeVidaSon(new Puntos(7500)));
    }

    @Test
    public void testActivarCartaMagiaDesdeLaMano() {
        CartaMagia cartaMagia = new AgujeroNegro();
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            this.jugadorActivo.ponerCartaEnLaMano();
            this.jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            this.jugadorActivo.agregarCartaAlMazo(cartaMagia);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.activarCartaMagiaDesdeLaMano(cartaMagia, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        }
        fail();
    }

    @Test
    public void testElMismoMonstruoNoPuedeAtacarDosVeces() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 100, 0, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        CartaMonstruo carta3 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            this.jugadorActivo.ponerCartaEnLaMano();
            this.jugadorPasivo.agregarCartaAlMazo(carta2);
            this.jugadorPasivo.ponerCartaEnLaMano();
            this.jugadorPasivo.agregarCartaAlMazo(carta3);
            this.jugadorPasivo.ponerCartaEnLaMano();
            jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            jugadorPasivo.invocarCartaMonstruoEnPosicionAtaque(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            jugadorPasivo.invocarCartaMonstruoEnPosicionAtaque(carta3, new MazoAlGoOh<CartaMonstruo>(0));
            fase.atacarCartaMonstruoConCartaMonstruo(carta1, carta2, this.jugadorActivo, this.jugadorPasivo);
            fase.atacarCartaMonstruoConCartaMonstruo(carta1, carta3, this.jugadorActivo, this.jugadorPasivo);
        } catch (MonstruoNoPuedeAtacarException | FuncionNoAccesibleException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } catch (MonstruoYaAtacoException e) {
            assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(1) && jugadorPasivo.tieneLaCantidadDeMonstruos(1));
        }


    }
}