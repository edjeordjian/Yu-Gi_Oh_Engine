package modelo.cartas;

import modelo.Jugador;

public interface Carta extends Cloneable {

    void activarInvocacionBocaAbajo();
    boolean estaInvocadaBocaAbajo();
    void activarInvocacionBocaArriba(Jugador jugador);
    boolean estaInvocadaBocaArriba();
    boolean estaEnElCementerio();
    void pasarAlMazo();
    void pasarALaMano();
    void pasarAlCementerio();
    boolean tieneNombre(String unNombre);
    boolean estaEnLaMano();
    String getEstado();
    Carta newInstance() throws CloneNotSupportedException;
}
