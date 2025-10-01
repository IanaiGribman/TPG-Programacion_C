package modelo;

import modelo.espera.ModuloEspera;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.PacienteYaIngresadoException;
import modelo.registro.ModuloRegistro;

/**
 * pacientes: hashmap con clave dni del paciente y valor objeto Paciente
 */
public class Clinica extends Entidad {
	private ModuloRegistro moduloRegistro;
	private ModuloEspera moduloEspera;

	/**Pre: los parámetros son distintos de null
	 * Instancia las listas, además de inicializar los atributos no listas
	 * @param nombre de la clinica
	 * @param domicilio de la clinica
	 * @param telefono de la clinica
	 
	 */
	public Clinica(String nombre, Domicilio domicilio, String telefono) //habria que lanzar excepciones si algunos de estos datos es null?
	{
		super(nombre, domicilio, telefono);
		moduloRegistro = new ModuloRegistro();
		moduloEspera = new ModuloEspera();
	}
	
	
	public void registraPaciente(Paciente paciente) throws DniRepetidoException
	{
		this.moduloRegistro.registraPaciente(paciente);
	}
	
	
	//hay que chequear que el paciente ya esté registrado??
	public void ingresaPaciente(Paciente paciente) throws PacienteYaIngresadoException
	{
		this.moduloEspera.ingresaPaciente(paciente);
	}
	
	public void sacarDeEspera(Paciente paciente)
	{
		this.moduloEspera.sacarDeEspera(paciente);
	}
}
