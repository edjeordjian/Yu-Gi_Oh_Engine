package modelo.cartas.estados;


public class InvocacionBocaAbajo implements EstadoCartaAlGoOh {
    public boolean estaInvocadaBocaAbajo() {
        return true;
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
    	return false;
   }
}
