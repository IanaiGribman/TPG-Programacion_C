package modelo.excepciones;

public class DniException extends Exception{
	private String dni;

	public DniException(String message, String dni) {
		super(message);
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
}
