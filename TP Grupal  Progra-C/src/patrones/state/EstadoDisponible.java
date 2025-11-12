package patrones.state;

import modelo.Ambulancia;

public class EstadoDisponible implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoDisponible(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public boolean atencionADomicilio() {
		ambulancia.setEstado(new EstadoAtiendeDomicilio(this.ambulancia));
		return true;
	}
	
	public boolean trasladoAClinica() {
		ambulancia.setEstado(new EstadoTrasladaPaciente(this.ambulancia));
		return true;
	}
	
	public boolean retorno() {
		return true;  // permanece disponible
	}
	
	public boolean mantenimiento() {
		ambulancia.setEstado(new EstadoEnTaller(this.ambulancia));
		return true;
	}
	
	@Override
	public String toString() {
		return "Disponible";
	}

}
