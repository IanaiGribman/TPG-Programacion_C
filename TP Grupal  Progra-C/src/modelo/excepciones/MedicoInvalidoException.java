package modelo.excepciones;

/**
 * Excepcion para cuando se quiere crear un medico de algun tipo invalido, 
 * guarda un mensaje con el error
 */
@SuppressWarnings("serial")
public class MedicoInvalidoException extends Exception{
	private String mensaje;
	
	public MedicoInvalidoException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
