package patrones;

import modelo.Ambulancia;

public class EstadoTrasladaPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoTrasladaPaciente(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {} // permanece trasladando paciente
	
	public void trasladoAClinica() {
		// informar que no puede MANDARLE A CONTROLADOR ?
	}
	
	public void retorno() {} // permanece trasladando paciente
	
	public void mantenimiento() {
		// informar MANDARLE A CONTROLADOR
	}

}
