package patrones.state;

import modelo.Ambulancia;

public class EstadoDisponible implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoDisponible(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {
		this.ambulancia.setEstado(new EstadoAtiendeDomicilio(this.ambulancia));
	}
	
	public void trasladoAClinica() {
		this.ambulancia.setEstado(new EstadoTrasladaPaciente(this.ambulancia));
	}
	
	public void retorno() {} // permanece disponible
	
	public void mantenimiento() {
		this.ambulancia.setEstado(new EstadoEnTaller(this.ambulancia));
		
	}
	
	@Override
	public String toString() {
		return "Disponible";
	}

}
