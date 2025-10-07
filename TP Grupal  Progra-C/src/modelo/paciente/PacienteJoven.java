package modelo.paciente;

import modelo.Domicilio;

public class PacienteJoven extends Paciente {

	protected PacienteJoven(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono, dni);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean tengoPrioridad(Paciente otro) {
		return !otro.prioridadAnteJoven();
	}

	@Override
	public boolean prioridadAnteJoven() {
		return false; // si son del mismo tipo le doy prioridad al que ya estaba 
	}

	@Override
	public boolean prioridadAnteNinio() {
		return false;
	}

	@Override
	public boolean prioridadAnteMayor() {
		return true;
	}

}
