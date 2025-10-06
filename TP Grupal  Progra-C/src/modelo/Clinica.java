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
import modelo.reporte.ModuloReportes;

/**
 * Clase que porporciona una interfaz para el acceso de las funcionalidades que el cliente tiene permitido
 * acceder.
 */
public class Clinica extends Entidad {
	private ModuloRegistro moduloRegistro;
	private ModuloEspera moduloEspera;
	private ModuloReportes moduloReportes;
	private int sigNumHistoriaClinica = 0;

	/**Pre: los parámetros son distintos de null
	 * Instancia los módulos
	 * @param nombre de la clinica
	 * @param domicilio de la clinica
	 * @param telefono de la clinica
	 
	 */
	public Clinica(String nombre, Domicilio domicilio, String telefono) 
	{
		super(nombre, domicilio, telefono);
		moduloRegistro = new ModuloRegistro();
		moduloEspera = new ModuloEspera();
		moduloReportes = new ModuloReportes();
	}
	
	
	/**
	 * * Agrega al paciente al hashmap de todos los pacientes de la clínica si ya no estaba previamente registrado.
	 * Pre: paciente no es null.
	 * @param paciente
	 * @throws DniRepetidoException
	 */
	public void registraPaciente(Paciente paciente) throws DniRepetidoException
	{
		this.moduloRegistro.registraPaciente(paciente);
		paciente.setNroHistoriaClinica(this.sigNumHistoriaClinica);
		this.sigNumHistoriaClinica++;
	}
	
	/**
	 * Agrega al médico al hashmap de todos los médicos de la clínica si ya no estaba previamente registrado.
	 * Pre: medico no es null.
	 * @param medico
	 * @throws DniRepetidoException
	 */
	public void registraMedico(IMedico medico) throws DniRepetidoException
	{
		this.moduloRegistro.registraMedico(medico);
	}
	
	
	/**
	 * Pone al paciente en el módulo de espera si está registrado y no se encontraba ya esperando.
	 * Pre: paciente no es null.
	 * @param paciente
	 * @throws PacienteYaIngresadoException
	 * @throws PacienteNoRegistradoException
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
	 * El paciente guarda la referencia al médico que lo atendió si es que estaba en la lista de espera o ya fue
	 * atendido por un médico ("lista de atención"). Si estaba en la lista de espera, se lo quita de ella.
	 * Pre: medico no es null y paciente no es null
	 * @param medico
	 * @param paciente
	 * @throws PacienteNoIngresadoException
	 * @throws MedicoNoRegistradoException
	 */
	public void atiendePaciente(IMedico medico, Paciente paciente) throws PacienteNoIngresadoException, MedicoNoRegistradoException
	{
		if (this.moduloRegistro.medicoIsRegistrado(medico))
		{
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
	 * El paciente guarda la referencia a la habitación donde se queda y la ocupación de la habitación se incrementa,
	 * si esta tiene espacio disponible y el paciente fue atendido por un médico previamente.
	 * Pre: paciente no es null y habitación no es null.
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
	 * Sobrecarga de la función egresaPaciente. Esta se usa cuando su estadía duró 0 días.
	 * Pre: paciente no es null.
	 * @param paciente
	 * @return
	 * @throws EgresoSinMedicoException
	 */
	public Factura egresaPaciente(Paciente paciente) throws EgresoSinMedicoException
	{
		return this.egresaPaciente(paciente, 0);
	}
	
	/**
	 * Genera la factura, libera la habitación si es que el paciente se alojaba en una y hace que el paciente olvide
	 * las consultas que tuvo con médicos, la habitación donde se alojó y le fecha de ingreso.
	 * Pre: paciente no es null.
	 * @param paciente
	 * @param cantDias
	 * @return factura de la estadía del paciente
	 * @throws EgresoSinMedicoException
	 */
	public Factura egresaPaciente(Paciente paciente, int cantDias) throws EgresoSinMedicoException
	{
		if (!paciente.fueAtendidoPorMedico())
			throw new EgresoSinMedicoException("el paciente no puede ser egresado porque no ha sido atendido por ningun medico", paciente.getDni());
	
		Habitacion habitacionPaciente = paciente.getHabitacion();
		if (habitacionPaciente != null)
			habitacionPaciente.sacarHuesped();
		
		Factura nuevaFactura = new Factura(paciente.getNombre(), paciente.getFechaIngreso(), paciente.getMedicosConsultados(), habitacionPaciente, cantDias);
		
		this.moduloReportes.agregarConsultasPaciente(paciente.getMedicosConsultados(), nuevaFactura.getFechaEgreso(), paciente.getNombre());
		
		paciente.olvidarIngreso();
		
		return nuevaFactura;
	}
	
	
}
