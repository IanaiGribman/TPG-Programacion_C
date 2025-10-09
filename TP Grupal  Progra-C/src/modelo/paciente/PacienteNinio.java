package modelo.paciente;

import modelo.Domicilio;

public class PacienteNinio extends Paciente {


	protected PacienteNinio(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono, dni);
	}

	@Override
	public boolean tengoPrioridad(Paciente otro) {
		return !otro.prioridadAnteNinio();
	}

	@Override
	public boolean prioridadAnteJoven() {
		return true;
	}

	@Override
	public boolean prioridadAnteNinio() {
		return false;
	}

	@Override
	public boolean prioridadAnteMayor() {
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + ", rangoEtario=Ninio";
	}
	
	

}
