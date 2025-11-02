package patrones;

import modelo.Ambulancia;

public class EstadoRegresaSinPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoRegresaSinPaciente(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {
		this.ambulancia.setEstado(new EstadoAtiendeDomicilio(this.ambulancia));
	}
	
	public void trasladoAClinica() {
		this.ambulancia.setEstado(new EstadoTrasladaPaciente(this.ambulancia));
	}
	
	public void retorno() {
		this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
	}
	
	public void mantenimiento() {
		// informa PASARLE AL CONTROLADOR
	}

}
