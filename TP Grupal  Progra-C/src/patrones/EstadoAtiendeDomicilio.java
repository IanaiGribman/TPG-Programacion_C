package patrones;

import modelo.Ambulancia;

public class EstadoAtiendeDomicilio implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoAtiendeDomicilio(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {
		
	}
	
	public void trasladoAClinica() {
		
	}
	
	public void retorno() {
		
	}
	
	public void mantenimiento() {
		
	}

}
