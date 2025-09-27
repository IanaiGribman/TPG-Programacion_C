package modelo;

public class PacienteJoven extends Paciente {

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
