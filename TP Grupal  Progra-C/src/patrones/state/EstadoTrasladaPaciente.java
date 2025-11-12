package patrones.state;

import modelo.Ambulancia;

public class EstadoTrasladaPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoTrasladaPaciente(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public boolean atencionADomicilio() {
		return false;
	} 
	
	public boolean trasladoAClinica() {
		return false;
	}
	
	public boolean retorno() {
		ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
		return true;
	}
	
	public boolean mantenimiento() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Trasladando paciente";
	}

}
