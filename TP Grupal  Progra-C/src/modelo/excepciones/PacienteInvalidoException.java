package modelo.excepciones;
/**
* Excepcion que se lanza si se intenta crear un paciente en un rango etario no reconocido
* @param mensaje: guarda el mensaje con el error especifico
*/
public class PacienteInvalidoException extends Exception {
	private String mensaje;
	public PacienteInvalidoException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
}