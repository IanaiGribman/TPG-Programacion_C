package modelo.espera;

import java.util.ArrayList;

import modelo.paciente.Paciente;

/**
 * Esta clase tiene una lista de los pacientes en el patio y tiene metodos para sacarlos o colocarlos en el patio
 */

public class Patio {
	private ArrayList<Paciente> pacientesPatio;

	public Patio() {
		super();
		pacientesPatio = new ArrayList<>();
	}
	
	/**
	 * Pre: el paciente no debe ser null, debe estar en el patio
	 * Post: el paciente ya no esta en el patio
	 * @param paciente
	 */
	public void sacarPaciente(Paciente paciente)
	{
		//System.out.println("se ha sacado a " + paciente.getNombre() + " del patio");
		this.pacientesPatio.remove(paciente);
	}
	
	/**
	 * Pre: el paciente debe estar en la lista de espera y no debe ser null
	 * Post: el paciente se agrega al patio
	 * @param paciente
	 */
	public void ponerPaciente(Paciente paciente)
	{
		this.pacientesPatio.add(paciente);
		//System.out.println("se ha colocado a " + paciente.getNombre() + " en el patio");
	}
	
	public boolean isInPatio(Paciente paciente)
	{
		return this.pacientesPatio.contains(paciente);
	}
	
	
}
