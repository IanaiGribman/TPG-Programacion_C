package modelo.registro;

import java.util.HashMap;

import modelo.Paciente;
import modelo.excepciones.DniRepetidoException;

public class ModuloRegistro 
{
	private HashMap<String, Paciente> pacientes;
	
	
	public ModuloRegistro() {
		super();
		pacientes = new HashMap<>();
	}

	
	/**
	 * Pre: paciente no es null
	 * Post: devuelve el paciente si existe o null
	 * @param paciente
	 * @return
	 */
	private Paciente buscarPaciente(Paciente paciente)
	{
		return this.pacientes.get(paciente.getDni());
	}
	
	
	/**
	 *Pre: paciente no es null ni su dni
	 *Post: se agrega el paciente a la lista de pacientes
	 * @param paciente que se quiere registrar
	 * @throws DniRepetidoException
	 */
	public void registra(Paciente paciente) throws DniRepetidoException
	{
		Paciente otro = buscarPaciente(paciente);
		if (otro == null)
			this.pacientes.put(paciente.getDni(), paciente);
		else
			throw new DniRepetidoException(paciente.getDni());
	}
	
}
