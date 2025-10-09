package modelo.excepciones;

public class PacienteYaInternadoException extends DniException {

	public PacienteYaInternadoException(String message, String dni) {
		super(message, dni);
	}
	
}
