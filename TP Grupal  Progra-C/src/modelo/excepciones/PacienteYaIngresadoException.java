package modelo.excepciones;

import modelo.Paciente;

public class PacienteYaIngresadoException extends DniException {

	public PacienteYaIngresadoException(String message, String dni) {
		super(message, dni);
	}
}
