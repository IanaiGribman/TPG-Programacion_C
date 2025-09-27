package modelo.excepciones;

public class DniRepetidoException extends Exception {
	private String dni;

	public DniRepetidoException(String dni) {
		super("no se pueden registrar a la clinica 2 personas con el mismo DNI");
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
}
