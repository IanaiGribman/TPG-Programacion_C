package modelo.excepciones;

public class DniRepetidoException extends Exception {
	private String dni;

	public DniRepetidoException(String mensaje, String dni) {
		super(mensaje);
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
}
