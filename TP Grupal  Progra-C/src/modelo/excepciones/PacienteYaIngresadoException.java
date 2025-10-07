package modelo.excepciones;

import modelo.paciente.Paciente;

public class PacienteYaIngresadoException extends DniException {

	public PacienteYaIngresadoException(String message, String dni) {
		super(message, dni);
	}
}
