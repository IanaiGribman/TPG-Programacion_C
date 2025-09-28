package modelo.excepciones;

import modelo.Paciente;

public class PacienteYaIngresadoException extends Exception {

	private Paciente paciente;
	
	public PacienteYaIngresadoException(Paciente paciente)
	{
		super("el paciente ya esta esperando en la lista de espera");
		this.paciente = paciente;
	}
	
	public Paciente getPaciente()
	{
		return this.paciente;
	}
}
