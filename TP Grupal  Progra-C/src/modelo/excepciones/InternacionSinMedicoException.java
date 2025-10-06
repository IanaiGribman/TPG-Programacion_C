package modelo.excepciones;

public class InternacionSinMedicoException extends DniException {

	public InternacionSinMedicoException(String message, String dni) {
		super(message, dni);
	}
	
}
