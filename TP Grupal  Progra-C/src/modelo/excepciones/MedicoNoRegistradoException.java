package modelo.excepciones;

public class MedicoNoRegistradoException extends DniException {

	public MedicoNoRegistradoException(String message, String dni) {
		super(message, dni);
	}
}
