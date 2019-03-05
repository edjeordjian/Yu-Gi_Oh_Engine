package modelo.cartas.estados;

public class EnElCementerio implements EstadoCartaAlGoOh {
	public boolean estaInvocadaBocaAbajo() {
        return false;
    }
    
    
    public boolean estaInvocadaBocaArriba() {
        return false;
    }
    
    
    public boolean estaEnElMazo() {
    	return false;
    }

    
    public boolean estaEnLaMano() {
    	return false;
    }
    
    
    public boolean estaEnElCementerio() {
    	return true;
    }
}
