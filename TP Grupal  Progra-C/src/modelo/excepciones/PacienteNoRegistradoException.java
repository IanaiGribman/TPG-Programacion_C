package modelo.excepciones;

/**
 * Excepcion para cuando se intenta ingresar o atender un paciente que no ha sido previamente registrado en la clinica
 */

public class PacienteNoRegistradoException extends DniException {

	public PacienteNoRegistradoException(String message, String dni) {
		super(message, dni);
	}
}
