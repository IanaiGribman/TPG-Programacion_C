package modelo.excepciones;

/**
 * Excepcion lanzada si se repite el DNI de medico o persona en los registros/ingresos
 */

public class DniRepetidoException extends DniException {

	public DniRepetidoException(String mensaje, String dni) {
		super(mensaje, dni);
	}
	
}
