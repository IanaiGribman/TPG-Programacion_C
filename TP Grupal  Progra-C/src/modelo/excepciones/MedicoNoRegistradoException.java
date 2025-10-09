package modelo.excepciones;

/**
 * Excepcion lanzada cuando se intenta asignar un medico que no esta registrado en la clinica
 */

public class MedicoNoRegistradoException extends DniException {

	public MedicoNoRegistradoException(String message, String dni) {
		super(message, dni);
	}
}
