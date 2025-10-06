package modelo;

import java.time.LocalDate;


import modelo.espera.ModuloEspera;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionLlenaException;
import modelo.excepciones.InternacionSinMedicoException;
import modelo.excepciones.MedicoNoRegistradoException;
import modelo.excepciones.PacienteNoIngresadoException;
import modelo.excepciones.PacienteNoRegistradoException;
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
	public void ingresaPaciente(Paciente paciente) throws PacienteYaIngresadoException, PacienteNoRegistradoException
	{
		if (this.moduloRegistro.pacienteIsRegistrado(paciente))
		{
			LocalDate fechaIngreso = LocalDate.now();
			paciente.setFechaIngreso(fechaIngreso);
			this.moduloEspera.ingresaPaciente(paciente);
		}
		else
			throw new PacienteNoRegistradoException("el paciente no ha sido registrado", paciente.getDni());
		
	}
	
	/**
	 * 
	 * @param medico no es null
	 * @param paciente no es null
	 */
	public void atiendePaciente(IMedico medico, Paciente paciente) throws PacienteNoIngresadoException, MedicoNoRegistradoException
	{
		if (this.moduloRegistro.medicoIsRegistrado(medico))
		{
			//si el paciente esta en espera o tuvo una consulta (lista de atencion implicita)
			if (this.moduloEspera.isEnEspera(paciente) || paciente.fueAtendidoPorMedico())
			{
				if (!paciente.fueAtendidoPorMedico())
					this.moduloEspera.sacarDeEspera(paciente);
				paciente.agregarConsulta(medico);
			}	
			else
				throw new PacienteNoIngresadoException("el paciente no ha sido ingresado", paciente.getDni());
		}
		else
			throw new MedicoNoRegistradoException("el medico no ha sido registrado", medico.getDni());
	}
	
	
	/**
	 * 
	 * @param paciente
	 * @param habitacion
	 * @throws InternacionSinMedicoException
	 * @throws HabitacionLlenaException
	 */
	public void internaPaciente(Paciente paciente, Habitacion habitacion) throws InternacionSinMedicoException, HabitacionLlenaException
	{
		if (!paciente.fueAtendidoPorMedico())
			throw new InternacionSinMedicoException("no se puede internar a un paciente sin justificacion medica", paciente.getDni());
		
		if (!habitacion.hayEspacio())
			throw new HabitacionLlenaException("la habitacion esta llena", habitacion.getOcupacion(), habitacion.getCapacidad());
		
		habitacion.agregarHuesped();
		paciente.setHabitacion(habitacion);
	}
	
	/**
	 * 
	 * @param paciente no es null
	 * @return
	 */
	public Factura egresaPaciente(Paciente paciente) throws EgresoSinMedicoException
	{
		return this.egresaPaciente(paciente, 0);
	}
	
	/**
	 * 
	 * @param paciente no es null
	 * @param cantDias
	 * @return
	 */
	public Factura egresaPaciente(Paciente paciente, int cantDias) throws EgresoSinMedicoException
	{
		if (!paciente.fueAtendidoPorMedico())
			throw new EgresoSinMedicoException("el paciente no puede ser egresado porque no has sido atendido por ningun mendico", paciente.getDni());
	
		Factura nuevaFactura = new Factura(paciente.getNombre(), paciente.getFechaIngreso(), paciente.getMedicosConsultados(), paciente.getHabitacion(), cantDias);
		paciente.olvidarIngreso();
		//FALTA PONER LAS CONSULTAS EN UNA LISTA DE TODAS LAS CONSULTAS DE LA CLINICA
		//HAY QUE HACER UNA CLASE ATENCION QUE TENGA FECHA DE EGRESO, MEDICO Y PACIENTE
		return nuevaFactura;
	}
	
	
}
