package patrones.state;

import modelo.Ambulancia;

public class EstadoTrasladaPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoTrasladaPaciente(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {} // permanece trasladando paciente
	
	public void trasladoAClinica() {
		this.ambulancia.firePropertyChange("Error",null,"No es posible realizar la solicitud en este momento, se esta traslandando a otro paciente");
	}
	
	public void retorno() {
		this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
	}
	
	public void mantenimiento() {
		this.ambulancia.firePropertyChange("Error",null,"No es posible realizar la solicitud en este momento, se esta traslandando a un paciente");
	}

}
