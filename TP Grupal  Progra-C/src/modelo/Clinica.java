package modelo;

import java.time.LocalDate;


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
	private int sigNumHistoriaClinica = 0;

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
		moduloEspera = new ModuloEspera();
	}
	
	
	public void registraPaciente(Paciente paciente) throws DniRepetidoException
	{
		this.moduloRegistro.registraPaciente(paciente);
		paciente.setNroHistoriaClinica(this.sigNumHistoriaClinica);
		this.sigNumHistoriaClinica++;
	}
	
	/**
	 * 
	 * @param medico no es null
	 * @throws DniRepetidoException
	 */
	public void registraMedico(IMedico medico) throws DniRepetidoException
	{
		this.moduloRegistro.registraMedico(medico);
	}
	
	
	/**
	 * 
	 * @param paciente no es nul
	 * @throws PacienteYaIngresadoException
	 */
	public void ingresaPaciente(Paciente paciente) throws PacienteYaIngresadoException
	{
		if (this.moduloRegistro.pacienteIsRegistrado(paciente))
		{
			LocalDate fechaIngreso = LocalDate.now();
			paciente.setFechaIngreso(fechaIngreso);
			this.moduloEspera.ingresaPaciente(paciente);
		}
		//else el paciente no esta registrado
		
	}
	
	/**
	 * 
	 * @param medico no es null
	 * @param paciente no es null
	 */
	public void atiendePaciente(IMedico medico, Paciente paciente)
	{
		if (this.moduloRegistro.medicoIsRegistrado(medico))
		{
			//si el paciente esta en espera o tuvo una consulta (lista de atencion implicita)
			if (this.moduloEspera.isEnEspera(paciente) || !paciente.getMedicosConsultados().isEmpty())
			{
				if (paciente.getMedicosConsultados().isEmpty())
					this.moduloEspera.sacarDeEspera(paciente);
				paciente.agregarConsulta(medico);
			}	
			//else el paciente no esta ingresdo
		}
		//else el medico no es parte de la clinica
	}
	
	public void internaPaciente(Paciente paciente, Habitacion habitacion)
	{
		
	}
	
	/**
	 * 
	 * @param paciente no es null
	 * @return
	 */
	public Factura egresaPaciente(Paciente paciente)
	{
		return this.egresaPaciente(paciente, 0);
	}
	
	/**
	 * 
	 * @param paciente no es null
	 * @param cantDias
	 * @return
	 */
	public Factura egresaPaciente(Paciente paciente, int cantDias)
	{
		if (!paciente.getMedicosConsultados().isEmpty())
		{
			Factura nuevaFactura = new Factura(paciente.getNombre(), paciente.getFechaIngreso(), paciente.getMedicosConsultados(), paciente.getHabitacion(), cantDias);
			paciente.olvidarIngreso();
			//FALTA PONER LAS CONSULTAS EN UNA LISTA DE TODAS LAS CONSULTAS DE LA CLINICA
			//HAY QUE HACER UNA CLASE ATENCION QUE TENGA FECHA DE EGRESO, MEDICO Y PACIENTE
			return nuevaFactura;
		}
		//else el paciente no fue atendido
	}
	
	
}
