package patrones;

import modelo.Ambulancia;

public class EstadoEnTaller implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoEnTaller(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {} // permanece en taller
	
	public void trasladoAClinica() {
		// informa PASARLE AL CONTROLADOR
	}
	
	public void retorno() {} // permanece en taller
	
	public void mantenimiento() {
		this.ambulancia.setEstado(new EstadoRegresaDeTaller(this.ambulancia));
	}

}
