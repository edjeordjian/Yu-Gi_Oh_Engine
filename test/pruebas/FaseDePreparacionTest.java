package pruebas;

import static org.junit.Assert.assertFalse;
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
import modelo.fases.FaseDePreparacion;

public class FaseDePreparacionTest {

    private Fase fase;
    private Jugador jugadorActivo;
    private Jugador jugadorPasivo;

    @Before
    public void setUp() throws Exception {
        this.fase = new FaseDePreparacion();
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
    public void testInvocarCartaMonstruoEnPosicionAtaqueEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionAtaque(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } 
        
        catch (MazoVacioException | MonstruoYaInvocadoException | FuncionNoAccesibleException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 
        assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(1));
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionDefensaEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionDefensa(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } 
        
        catch (MazoVacioException | MonstruoYaInvocadoException | FuncionNoAccesibleException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 
        assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(1));
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionDefensaBocaAbajoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionDefensaBocaAbajo(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        } 
        
        catch (MonstruoYaInvocadoException | FuncionNoAccesibleException| MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(1));
    }

    @Test
    public void testInvocarCartaMonstruoEnPosicionAtaqueBocaAbajoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
        }

        catch (MonstruoYaInvocadoException | FuncionNoAccesibleException |MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        assertTrue(jugadorActivo.tieneLaCantidadDeMonstruos(1));
    }

    @Test
    public void testInvocarCartaTrampaBocaAbajoEstaPermitido() {
        CartaTrampa carta = new Refuerzos();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaTrampaBocaAbajo(this.jugadorActivo, carta);
            MazoAlGoOh<CartaTrampa> cartasTrampa = this.jugadorActivo.obtenerCartasTrampaPorAtaque();
            assertFalse(cartasTrampa.estaVacio());
        } catch (FuncionNoAccesibleException e) {
            fail();
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAtacarCartaMonstruoConCartaMonstruoNoEstaPermitido() {
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
            assertTrue(true);
            return;
        } 
        catch (MonstruoNoPuedeAtacarException | MonstruoYaAtacoException | MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } 

        fail();
    }

    @Test
    public void testInvocarCartaCampoBocaArribaEstaPermitido() {
        CartaCampo carta = new Sogen();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaCampoBocaArriba(carta, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            fail();
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(carta.estaInvocadaBocaArriba());
    }

    @Test
    public void testInvocarCartaMagiaBocaArribaEstaPermitido() {
        CartaMagia carta = new AgujeroNegro();
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMagiaBocaArriba(carta, this.jugadorActivo, this.jugadorPasivo);
        } catch (FuncionNoAccesibleException e) {
            fail();
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(carta.estaEnElCementerio());
    }

    @Test
    public void testAtacarALosPuntosDeVidaNoEstaPermitido() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 0, 0, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            this.jugadorActivo.ponerCartaEnLaMano();
            this.jugadorPasivo.agregarCartaAlMazo(carta2);
            this.jugadorPasivo.ponerCartaEnLaMano();
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
    public void testSoloSePuedeInvocarUnMonstruo() {
        CartaMonstruo carta1 = new CartaMonstruoGenerica("", 0, 0, 1);
        CartaMonstruo carta2 = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta1);
            this.jugadorActivo.ponerCartaEnLaMano();
            this.jugadorActivo.agregarCartaAlMazo(carta2);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionAtaque(this.jugadorActivo, this.jugadorPasivo, carta1, new MazoAlGoOh<CartaMonstruo>(0));
            fase.invocarCartaMonstruoEnPosicionAtaque(this.jugadorActivo, this.jugadorPasivo, carta2, new MazoAlGoOh<CartaMonstruo>(0));
        } catch (FuncionNoAccesibleException e) {
            fail();
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        } catch (MonstruoYaInvocadoException e) {
            assertTrue(true);
            return;
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
            assertTrue(true);
            return;
        } catch (MazoVacioException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException e) {
            e.printStackTrace();
            fail();
        }
        fail();
    }

    @Test
    public void testInvocarUnaCartaMonstruoYCambiarlaDePosicionEnElMismoTurnoNoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionAtaque(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
            fase.cambiarCartaMonstruoDePosicion(carta);

        } catch (MonstruoYaInvocadoException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException | MazoVacioException e) {
            e.printStackTrace();
            fail();
        } catch (FuncionNoAccesibleException e) {
            assertTrue(this.jugadorActivo.tieneLaCantidadDeMonstruos(1));
            return;
        } 

        fail();
    }

    @Test
    public void testInvocarUnaCartaMonstruoYVoltearlaEnElMismoTurnoNoEstaPermitido() {
        CartaMonstruo carta = new CartaMonstruoGenerica("", 0, 0, 1);
        try {
            this.jugadorActivo.agregarCartaAlMazo(carta);
            this.jugadorActivo.ponerCartaEnLaMano();
            fase.invocarCartaMonstruoEnPosicionAtaqueBocaAbajo(this.jugadorActivo, this.jugadorPasivo, carta, new MazoAlGoOh<CartaMonstruo>(0));
            fase.cambiarCartaMonstruoABocaArriba(carta, null);

        } catch (MonstruoYaInvocadoException | CartaAlGoOhInexistenteException | MazoLlenoException | SacrificioInvalidoException | MazoVacioException e) {
            e.printStackTrace();
            fail();
        } catch (FuncionNoAccesibleException e) {
            assertTrue(this.jugadorActivo.tieneLaCantidadDeMonstruos(1));
            return;
        }

        fail();
    }
}