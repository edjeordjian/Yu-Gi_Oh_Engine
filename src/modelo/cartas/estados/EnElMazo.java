package modelo.cartas.estados;

public class EnElMazo implements EstadoCartaAlGoOh {
	public boolean estaInvocadaBocaAbajo() {
        return false;
    }
    
    
    public boolean estaInvocadaBocaArriba() {
        return false;
    }
    
    
    public boolean estaEnElMazo() {
    	return true;
    }

    
    public boolean estaEnLaMano() {
    	return false;
    }
    
    
    public boolean estaEnElCementerio() {
    	return false;
    }
   
    
}
