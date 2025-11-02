package patrones;

import modelo.Ambulancia;

public class EstadoRegresaSinPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoRegresaSinPaciente(Ambulancia ambulancia) {
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
