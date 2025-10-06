package modelo.excepciones;

public class EgresoSinMedicoException extends DniException {

	public EgresoSinMedicoException(String message, String dni) {
		super(message, dni);
	}
}
