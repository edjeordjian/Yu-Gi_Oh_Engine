package pruebas;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.cartasMonstruo.CartaMonstruoGenerica;


public class CartaMonstruoTest {
	
	
	@Test
	public void testCartaMonstruoIniciaEnPosicionAtaque() {
		CartaMonstruo cartaMonstruo = new CartaMonstruoGenerica("a",0,0,1);
        assertTrue(cartaMonstruo.estaEnPosicionDeAtaque());
	}

	
	@Test
	public void testCartaMonstruoPuedeCambiarAPosicionDefensa() {
		CartaMonstruo cartaMonstruo= new CartaMonstruoGenerica("a",0,0,1);
        cartaMonstruo.activarPosicionDeDefensa();
        assertTrue(cartaMonstruo.estaEnPosicionDeDefensa());
	}
	
	
	@Test
	public void testCartaMonstruoPuedeCambiarAPosicionDeAtaque() {
		CartaMonstruo cartaMonstruo= new CartaMonstruoGenerica("a",0,0,1);
        cartaMonstruo.activarPosicionDeDefensa();
        cartaMonstruo.activarPosicionDeAtaque();
        assertTrue(cartaMonstruo.estaEnPosicionDeAtaque());
	}
}