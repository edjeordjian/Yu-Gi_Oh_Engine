package modelo.cartas.estados;


public class InvocacionBocaArriba implements EstadoCartaAlGoOh {

	
    public boolean estaInvocadaBocaAbajo() {
        return false;
    }
    
    
    public boolean estaInvocadaBocaArriba() {
        return true;
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
