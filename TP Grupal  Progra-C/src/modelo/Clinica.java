package modelo;

import modelo.excepciones.DniRepetidoException;
import modelo.registro.ModuloRegistro;

/**
 * pacientes: hashmap con clave dni del paciente y valor objeto Paciente
 */
public class Clinica extends Entidad {
	private ModuloRegistro moduloRegistro;

	/**Pre: los parámetros son distintos de null
	 * Instancia las listas, además de inicializar los atributos no listas
	 * @param nombre de la clinica
	 * @param domicilio de la clinica
	 * @param telefono de la clinica
	 
	 */
	public Clinica(String nombre, Domicilio domicilio, String telefono) 
	{
		super(nombre, domicilio, telefono);
		moduloRegistro = new ModuloRegistro();
	}
	
	
	public void registraPaciente(Paciente paciente) throws DniRepetidoException
	{
		this.moduloRegistro.registra(paciente);
	}
	
}
