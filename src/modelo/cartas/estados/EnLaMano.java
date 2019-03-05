package modelo.cartas.estados;

public class EnLaMano implements EstadoCartaAlGoOh {
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
    	return true;
    }
    
    
    public boolean estaEnElCementerio() {
    	return false;
    }
   
}
