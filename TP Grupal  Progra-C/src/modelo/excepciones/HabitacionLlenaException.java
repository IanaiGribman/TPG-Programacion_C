package modelo.excepciones;

/**
 * Excepcion lanzada cuando se quiere asignar un paciente en una habitacion que ya completo su capacidad
 */

public class HabitacionLlenaException extends Exception {
	private int ocupacion;
	private int capacidad;
	
	
	public HabitacionLlenaException(String message, int ocupacion, int capacidad) {
		super(message);
		this.ocupacion = ocupacion;
		this.capacidad = capacidad;
	}


	public int getOcupacion() {
		return ocupacion;
	}


	public int getCapacidad() {
		return capacidad;
	}
	
	
}
