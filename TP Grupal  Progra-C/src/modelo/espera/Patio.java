package modelo.espera;

import java.util.HashMap;

import modelo.Paciente;

public class Patio {
	private HashMap<String, Paciente> pacientesPatio;

	public Patio() {
		super();
		pacientesPatio = new HashMap<>();
	}
	
	public void sacarPaciente(Paciente paciente)
	{
		this.pacientesPatio.remove(paciente.getDni());
	}
	
	public void ponerPaciente(Paciente paciente)
	{
		this.pacientesPatio.put(paciente.getDni(), paciente);
	}
}
