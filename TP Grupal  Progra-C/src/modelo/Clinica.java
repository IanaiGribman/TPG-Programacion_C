package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * pacientes: hashmap con clave dni del paciente y valor objeto Paciente
 */
public class Clinica extends Entidad {
	private HashMap<String, Paciente> pacientes;

	/**Pre: los parámetros son distintos de null
	 * Instancia las listas, además de inicializar los atributos no listas
	 * @param nombre de la clinica
	 * @param domicilio de la clinica
	 * @param telefono de la clinica
	 
	 */
	public Clinica(String nombre, Domicilio domicilio, String telefono) {
		super(nombre, domicilio, telefono);
		pacientes = new HashMap<>();
	}
	
	/**
	 * 
	 * @param paciente
	 * @param listaDePacientes
	 * @return
	 */
	private Paciente buscarPaciente(Paciente paciente, Map<String, Paciente> listaDePacientes)
	{
		return listaDePacientes.get(paciente.getDni());
	}
}
