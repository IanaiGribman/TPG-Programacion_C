package modelo.excepciones;

public class PacienteNoIngresadoException extends DniException {

	public PacienteNoIngresadoException(String message, String dni) {
		super(message, dni);
	}	
}
