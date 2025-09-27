package modelo;

public class PacienteNinio extends Paciente {

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

}
