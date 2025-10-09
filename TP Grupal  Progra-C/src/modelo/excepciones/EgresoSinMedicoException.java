package modelo.excepciones;

/**
 * Excepcion para cuando se intenta egresar un paciente que no fue atendido por ningun medico
 */

public class EgresoSinMedicoException extends DniException {

	public EgresoSinMedicoException(String message, String dni) {
		super(message, dni);
	}
}
