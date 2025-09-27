package modelo;

public class PacienteMayor extends Paciente {

	protected PacienteMayor(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono, dni);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean tengoPrioridad(Paciente otro) {
		return !otro.prioridadAnteMayor();
	}

	@Override
	public boolean prioridadAnteJoven() {
		return false; // la sala queda para el joven
	}

	@Override
	public boolean prioridadAnteNinio() {
		return true; // la sala queda para el mayor 
	}

	@Override
	public boolean prioridadAnteMayor() {
		return false; // la sala queda para el que ya estaba
	}

}
