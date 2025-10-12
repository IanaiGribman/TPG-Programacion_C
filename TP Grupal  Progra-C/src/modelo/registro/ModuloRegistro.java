package modelo.registro;

import java.util.HashMap;

import modelo.IMedico;
import modelo.excepciones.DniRepetidoException;
import modelo.paciente.Paciente;

public class ModuloRegistro 
{
	private HashMap<String, Paciente> pacientes;
	private HashMap<String, IMedico> medicos;
	
	public ModuloRegistro() {
		super();
		pacientes = new HashMap<>();
		medicos = new HashMap<>();
	}

	
	/**
	 * Pre: paciente no es null
	 * Post: devuelve el paciente si existe o null
	 * @param paciente
	 * @return
	 */
	public boolean pacienteIsRegistrado(Paciente paciente)
	{
		return this.pacientes.containsKey(paciente.getDni());
	}
	
	public boolean medicoIsRegistrado(IMedico medico)
	{
		return this.medicos.containsKey(medico.getDni());
	}
	
	/**
	 *Pre: paciente no es null ni su dni
	 *Post: se agrega el paciente a la lista de pacientes
	 * @param paciente que se quiere registrar
	 * @throws DniRepetidoException
	 */
	public void registraPaciente(Paciente paciente) throws DniRepetidoException
	{
		if (!pacienteIsRegistrado(paciente)) {
			this.pacientes.put(paciente.getDni(), paciente);
			//System.out.println("se ha registrado al paciente " + paciente.getNombre());
		}
		else
			throw new DniRepetidoException("no se pueden registrar 2 pacientes con el mismo DNI", paciente.getDni());
	}
	
	public void registraMedico(IMedico medico) throws DniRepetidoException
	{
		if (!medicoIsRegistrado(medico))
		{
			this.medicos.put(medico.getDni(), medico);
			//System.out.println("se ha registrado al medico " + medico.getNombre());
		}
		else
			throw new DniRepetidoException("no se pueden registrar 2 medicos con el mismo DNI", medico.getDni());
	}
	
}
