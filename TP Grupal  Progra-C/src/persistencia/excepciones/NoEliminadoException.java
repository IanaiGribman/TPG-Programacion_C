package persistencia.excepciones;

@SuppressWarnings("serial")
public class NoEliminadoException extends Exception {

	public NoEliminadoException() {
		super("No se encontro el DNI ingresado");
	}

}