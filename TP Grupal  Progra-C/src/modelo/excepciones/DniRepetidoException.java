package modelo.excepciones;

public class DniRepetidoException extends DniException {

	public DniRepetidoException(String mensaje, String dni) {
		super(mensaje, dni);
	}
	
}
