package modelo.excepciones;

/**
 * Excepcion para cuando se intenta ingresar un paciente que ya ha sido guardado en la lista de ingreso correspondiente
 */

public class PacienteYaIngresadoException extends DniException {

	public PacienteYaIngresadoException(String message, String dni) {
		super(message, dni);
	}
}
