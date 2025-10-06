package modelo.excepciones;

/**
 * Excepcion para cuando se quiere crear un medico de algun tipo invalido, 
 * guarda un mensaje con el error
 */
@SuppressWarnings("serial")
public class HabitacionInvalidaException extends Exception{
	private String mensaje;
	
	public HabitacionInvalidaException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}