package modelo.excepciones;

/**
 * Excepcion para cuando se intenta internar un paciente que ya ha sido asignado a una habitacion (internado)
 */

public class PacienteYaInternadoException extends DniException {

	public PacienteYaInternadoException(String message, String dni) {
		super(message, dni);
	}
	
}
