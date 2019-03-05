package modelo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import modelo.cartas.Carta;
import modelo.excepciones.CartaAlGoOhInexistenteException;
import modelo.excepciones.MazoLlenoException;
import modelo.excepciones.MazoVacioException;


public class MazoAlGoOh <T extends Carta> implements Iterable<T> {

	private ArrayList<T> cartas;
	private int maxCantidadCartas;
	
	
	public MazoAlGoOh(int maxCantidadCartas) {
		this.maxCantidadCartas = maxCantidadCartas;
		this.cartas = new ArrayList<T>();
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return cartas.iterator();
	}
		

	public void agregarCarta(T carta) throws MazoLlenoException{
		
		if ( ( this.cartas.size() + 1 ) > this.maxCantidadCartas )
			throw new MazoLlenoException();
		
		this.cartas.add(carta);
	}
	
	
	public void removerCarta(T carta) throws CartaAlGoOhInexistenteException{
		if ( ! cartas.remove(carta) )
		{throw new CartaAlGoOhInexistenteException();}
	}

	
	public boolean tieneLaCarta(Carta carta) {
		return cartas.contains(carta);
	}

	
	public boolean tieneLaCarta(String nombreDeCarta) {
		for(T carta: this.cartas) {
			if(carta.toString().equals(nombreDeCarta))
				return true;
		}
		return false;
	}

	
	
	public T obtenerCartaTope() throws MazoVacioException {
		if (this.cartas.size() == 0)
			throw new MazoVacioException();
		
		int posCartaADevolver = cartas.size() - 1;
		T cartaADevolver = cartas.get( posCartaADevolver );
		cartas.remove(posCartaADevolver);
		return cartaADevolver;
	}


	public T mostrarCartaTope() throws MazoVacioException {
		if (this.cartas.size() == 0)
			throw new MazoVacioException();
		
		return cartas.get( cartas.size() - 1 );
	}
	

	public boolean tieneLaCantidadDeCartas(int unaCantidad) {
		return cartas.size() == unaCantidad;
	}
	
	
	public boolean estaVacio() {
		return cartas.size() == 0;
	}
	

	public boolean todasLasCartasSon(String nombreDeLaCarta) {
		
		for (T carta : this.cartas) {
			if (!carta.tieneNombre(nombreDeLaCarta))
				return false;
		}
		
		return true;
	}
	
	public MazoAlGoOh<T> obtenerMonstruosInvocadosBocaArriba() throws MazoLlenoException {
		MazoAlGoOh<T> mazoADevoler = new MazoAlGoOh<T>(this.cartas.size());
		
		for (T carta : this.cartas) {
			if (carta.estaInvocadaBocaArriba())
				mazoADevoler.agregarCarta(carta);
		}
		
		return mazoADevoler;
	}


	public void agregarCartasAleatoriamente(MazoAlGoOh<Carta> cartasPosibles, int cantidad) throws MazoLlenoException, CloneNotSupportedException {
		for (int i = 0; i < cantidad; i++) {
			agregarCarta( (T) cartasPosibles.obtenerCartaAleatoria() );
		}
	}


	public T obtenerCartaAleatoria() throws CloneNotSupportedException {
		int pos = new Random().nextInt(this.cartas.size());
		return (T) (this.cartas.get(pos)).newInstance();
	}

    public ArrayList getCartas() {
	    return cartas;
    }


	public void quitarCarta(Carta carta) {
		this.cartas.remove(carta);
	}
}

