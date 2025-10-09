package modelo.excepciones;

/**
 * Excepcion para cuando se intenta atender un paciente que no fue ingresado (en lista de espera)
 */

public class PacienteNoIngresadoException extends DniException {

	public PacienteNoIngresadoException(String message, String dni) {
		super(message, dni);
	}	
}
