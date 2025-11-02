package patrones;

import modelo.Ambulancia;

public class EstadoEnTaller implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoEnTaller(Ambulancia ambulancia) {
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
