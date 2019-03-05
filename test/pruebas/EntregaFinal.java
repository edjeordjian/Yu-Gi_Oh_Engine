package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import modelo.MazoAlGoOh;
import modelo.PartidaAlGoOh;
import modelo.Puntos;
import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasCampo.Wasteland;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;
import modelo.excepciones.AtaqueALosPuntosDeVidaInvalidoException;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.FuncionNoAccesibleException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;
import modelo.excepciones.MonstruoNoPuedeAtacarException;
import modelo.excepciones.MonstruoYaAtacoException;
import modelo.excepciones.MonstruoYaInvocadoException;
import modelo.excepciones.SacrificioInvalidoException;

public class EntregaFinal {


    private void agregarCartaInicialALosMazos(PartidaAlGoOh partida) throws MazoLlenoException {
        // para que no pierda de entrada
        Carta carta = new CartaMonstruoGenerica("", 0, 0, 1);
        partida.agregarCartaAlMazoDeJugadorActivo(carta);
        partida.terminarTurno();
        partida.agregarCartaAlMazoDeJugadorActivo(carta);
        partida.terminarTurno();
    }

    @Test
    public void unJugadorQueNoTienePuntosPierde() {
    	
    	try{
	    	PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
	    	agregarCartaInicialALosMazos(partida);
	    	CartaMonstruo carta = new CartaMonstruoGenerica("a",8000,8000,1);
	    	partida.agregarCartaAlMazoDeJugadorActivo(carta);
			partida.ponerCartaEnLaManoDeJugadorActivo();
	        partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta, new MazoAlGoOh<CartaMonstruo>(0));
			partida.cambiarDeFase();
			assertFalse(partida.jugadorPasivoPerdioLaPartida());
			assertFalse(partida.jugadorActivoGanoLaPartida());
			partida.atacarJugadorPasivoALosPuntosDeVidaCon(carta);
			assertTrue(partida.jugadorPasivoPerdioLaPartida());
			assertTrue(partida.jugadorActivoGanoLaPartida());
	    }
    
		catch(MazoLlenoException | MazoVacioException | CartaAlGoOhInexistenteException | SacrificioInvalidoException | AtaqueALosPuntosDeVidaInvalidoException | FuncionNoAccesibleException | MonstruoYaInvocadoException | MonstruoYaAtacoException | MonstruoNoPuedeAtacarException e){
			e.printStackTrace();
			fail();
		}

	}

    @Test
    public void elMazoSeCargaCorretamenteAlInicialLaPartida()  {

    	try{
	    	PartidaAlGoOh partida = new PartidaAlGoOh("j1", "j2");
	    	partida.iniciar();
	    	assertTrue(partida.tieneLaCantidadDeCartasEnLaManoElJugadorActivo(5));
	    	assertTrue(partida.tieneLaCantidadDeCartasEnLaManoElJugadorPasivo(5));
	    	assertTrue(partida.tieneLaCantidadDeCartasEnElMazoElJugadorActivo(35));
	    	assertTrue(partida.tieneLaCantidadDeCartasEnElMazoElJugadorPasivo(35));
	    }

		catch(Exception e){
			e.printStackTrace();
			fail();
		}

    }

    @Test
    public void pruebaConCartasDeCampo() throws MazoLlenoException {

		try {
			PartidaAlGoOh partida = new PartidaAlGoOh("1","2");
			agregarCartaInicialALosMazos(partida);
			CartaMonstruo carta1 = new CartaMonstruoGenerica("a",100,150,1);
			CartaMonstruo carta2 = new CartaMonstruoGenerica("b",200,300,2);
			CartaCampo wasteland = new Wasteland();
			
			partida.agregarCartaAlMazoDeJugadorActivo(carta1);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta1, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			
			partida.agregarCartaAlMazoDeJugadorActivo(carta2);
			partida.ponerCartaEnLaManoDeJugadorActivo();
		    partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta2, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
				
			partida.agregarCartaAlMazoDeJugadorActivo(wasteland);
			partida.ponerCartaEnLaManoDeJugadorActivo();
		    partida.cambiarDeFase();
			partida.invocarCartaCampoDeJugadorActivoBocaArriba(wasteland);
			partida.terminarTurno();
			partida.terminarTurno();
			assertTrue(carta1.tienePuntosDeAtaque(new Puntos(100 + 200)));
			assertTrue(carta2.tienePuntosDeDefensa(new Puntos(300 + 300)));
			CartaMonstruo carta3 = new CartaMonstruoGenerica("a",1000,150,1);
			CartaMonstruo carta4 = new CartaMonstruoGenerica("b",200,3000,2);
			
			partida.agregarCartaAlMazoDeJugadorActivo(carta3);
			partida.ponerCartaEnLaManoDeJugadorActivo();
			partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta3, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			
			partida.agregarCartaAlMazoDeJugadorActivo(carta4);
			partida.ponerCartaEnLaManoDeJugadorActivo();
		    partida.cambiarDeFase();
			partida.invocarCartaMonstruoEnPosicionAtaqueDeJugadorActivo(carta4, new MazoAlGoOh<CartaMonstruo>(0));
			partida.terminarTurno();
			
			assertTrue(carta3.tienePuntosDeAtaque(new Puntos(1000 + 200)));
			assertTrue(carta4.tienePuntosDeDefensa(new Puntos(3000 + 300)));
			
			partida.terminarTurno();
			partida.terminarTurno();
			partida.terminarTurno();
			
			partida.agregarCartaAlMazoDeJugadorActivo(wasteland);
			partida.ponerCartaEnLaManoDeJugadorActivo();
		    partida.cambiarDeFase();
			partida.invocarCartaCampoDeJugadorActivoBocaArriba(wasteland);

			assertTrue(carta2.tienePuntosDeAtaque(new Puntos(200 + 200)));
			assertTrue(carta4.tienePuntosDeAtaque(new Puntos(200 + 200)));
			assertTrue(carta1.tienePuntosDeDefensa(new Puntos(150 + 300)));
			assertTrue(carta3.tienePuntosDeDefensa(new Puntos(150 + 300)));
			
			assertTrue(carta1.tienePuntosDeAtaque(new Puntos(100 + 200)));
			assertTrue(carta2.tienePuntosDeDefensa(new Puntos(300 + 300)));
			assertTrue(carta3.tienePuntosDeAtaque(new Puntos(1000 + 200)));
			assertTrue(carta4.tienePuntosDeDefensa(new Puntos(3000 + 300)));
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
    }
    
}
