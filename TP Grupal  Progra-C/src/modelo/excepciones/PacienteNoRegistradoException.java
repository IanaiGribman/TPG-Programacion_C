package modelo.excepciones;

public class PacienteNoRegistradoException extends DniException {

	public PacienteNoRegistradoException(String message, String dni) {
		super(message, dni);
	}
}
