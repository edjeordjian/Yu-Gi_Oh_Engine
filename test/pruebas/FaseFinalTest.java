package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modelo.Jugador;
import modelo.MazoAlGoOh;
import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasCampo.Sogen;
import modelo.cartas.cartasMagia.AgujeroNegro;
import modelo.cartas.cartasMagia.CartaMagia;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
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
import modelo.fases.FaseFinal;

public class FaseFinalTest {

    private Fase fase;
    private Jugador jugadorActivo;
    private Jugador jugadorPasivo;

    @Before
    public void setUp() throws Exception {
        this.fase = new FaseFinal();
        this.jugadorActivo = new Jugador("");
        this.jugadorPasivo = new Jugador("");
    }

    @Test
    public void testPonerCartaEnLaManoEstaNoPermitido() {
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
            fase.ponerCartaEnLaMano(this.jugadorActivo);
            fase.invocarCartaMonstruoEnPosicionAtaque(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MonstruoYaInvocadoException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
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
            fase.ponerCartaEnLaMano(this.jugadorActivo);
            fase.invocarCartaMonstruoEnPosicionDefensa(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MonstruoYaInvocadoException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
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
            fase.ponerCartaEnLaMano(this.jugadorActivo);
            fase.invocarCartaMonstruoEnPosicionDefensaBocaAbajo(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MonstruoYaInvocadoException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
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
            fase.ponerCartaEnLaMano(this.jugadorActivo);
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
    public void testAtacarCartaMonstruoConCartaMonstruoNoEstaPermitido() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 0, 0, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            fase.ponerCartaEnLaMano(this.jugadorActivo);
            this.jugadorPasivo.agregarCartaAlMazo(carta2);
            fase.ponerCartaEnLaMano(this.jugadorPasivo);
            jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            jugadorPasivo.invocarCartaMonstruoEnPosicionAtaque(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            fase.atacarCartaMonstruoConCartaMonstruo(carta1, carta2, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MonstruoYaAtacoException | MonstruoNoPuedeAtacarException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        fail();
    }

    @Test
    public void testInvocarCartaCampoBocaArribaNoEstaPermitido() {
        CartaCampo carta = new Sogen();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            fase.ponerCartaEnLaMano(this.jugadorActivo);
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
            fase.ponerCartaEnLaMano(this.jugadorActivo);
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
    public void testAtacarALosPuntosDeVidaNoEstaPermitido() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 0, 0, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            fase.ponerCartaEnLaMano(this.jugadorActivo);
            this.jugadorPasivo.agregarCartaAlMazo(carta2);
            fase.ponerCartaEnLaMano(this.jugadorPasivo);
            jugadorActivo.invocarCartaMonstruoEnPosicionAtaque(carta1, new MazoAlGoOh<CartaMonstruo>(0));
            jugadorPasivo.invocarCartaMonstruoEnPosicionAtaque(carta2, new MazoAlGoOh<CartaMonstruo>(0));
            fase.atacarALosPuntosDeVida(carta1, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            assertTrue(true);
            return;
        } catch (MonstruoYaAtacoException | MonstruoNoPuedeAtacarException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException | AtaqueALosPuntosDeVidaInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        fail();
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
            fail();
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(0));
    }
}