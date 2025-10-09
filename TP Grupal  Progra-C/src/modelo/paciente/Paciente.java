package modelo.paciente;

import modelo.Domicilio;
import modelo.Persona;

public abstract class Paciente extends Persona {

	private int nroHistoriaClinica;
	
	
	protected Paciente(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono, dni);
	}
	
	public int getNroHistoriaClinica() {
		return nroHistoriaClinica;
	}

	public void setNroHistoriaClinica(int nroHistoriaClinica) {
		this.nroHistoriaClinica = nroHistoriaClinica;
	}


	/**
	 * Precondiciones: El otro paciente no debe ser nulo
	 * 
	 * @param otro El otro paciente con el cual se compara.
	 * @return Devuelve si el paciente actual tiene prioridad ante el otro paciente
	 *         pasado por parametro. El paciente con prioridad deberia ser el que se
	 *         quede con la sala privada. Ante pacientes del mismo rango etario el
	 *         que llama a la funcion tiene prioridad
	 */
	public abstract boolean tengoPrioridad(Paciente otro);

	/**
	 * @return devuelve que tipo de prioridad tiene el paciente ante otro paciente
	 *         joven
	 */
	public abstract boolean prioridadAnteJoven();

	/**
	 * @return devuelve que tipo de prioridad tiene el paciente ante otro paciente
	 *         ni�o
	 */
	public abstract boolean prioridadAnteNinio();

	/**
	 * @return devuelve que tipo de prioridad tiene el paciente ante otro paciente
	 *         mayor
	 */
	public abstract boolean prioridadAnteMayor();
}
