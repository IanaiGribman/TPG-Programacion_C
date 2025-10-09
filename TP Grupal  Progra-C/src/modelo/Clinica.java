package modelo;

import java.time.LocalDate;

import modelo.atenciones.ModuloAtenciones;
import modelo.espera.ModuloEspera;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionInvalidaException;
import modelo.excepciones.HabitacionLlenaException;
import modelo.excepciones.MedicoNoRegistradoException;
import modelo.excepciones.PacienteNoIngresadoException;
import modelo.excepciones.PacienteNoRegistradoException;
import modelo.excepciones.PacienteYaIngresadoException;
import modelo.excepciones.PacienteYaInternadoException;
import modelo.habitaciones.Habitacion;
import modelo.paciente.Paciente;
import modelo.registro.ModuloRegistro;

/**
 * Clase que porporciona una interfaz para el acceso de las funcionalidades que el cliente tiene permitido
 * acceder.}
 * La clase posee metodos para la asignacion de costo para las habitaciones que deben ser llamados al menos una vez antes de internar pacientes
 */
public class Clinica extends Entidad {
	private ModuloRegistro moduloRegistro;
	private ModuloEspera moduloEspera;
	private ModuloAtenciones moduloAtenciones;
	private ModuloGestionCostos moduloGestionCostos;
	private int sigNumHistoriaClinica = 0;

	/**Pre: los parametros son distintos de null
	 * Instancia los modulos
	 * @param nombre de la clinica
	 * @param domicilio de la clinica
	 * @param telefono de la clinica
	 
	 */
	public Clinica(String nombre, Domicilio domicilio, String telefono) 
	{
		super(nombre, domicilio, telefono);
		moduloRegistro = new ModuloRegistro();
		moduloEspera = new ModuloEspera();
		moduloAtenciones = new ModuloAtenciones();
		moduloGestionCostos = new ModuloGestionCostos();
	}
	
	
	/**
	 * * Agrega al paciente al hashmap de todos los pacientes de la clinica si ya no estaba previamente registrado.
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
	 * Agrega al medico al hashmap de todos los medicos de la clinica si ya no estaba previamente registrado.
	 * Pre: medico no es null.
	 * @param medico
	 * @throws DniRepetidoException
	 */
	public void registraMedico(IMedico medico) throws DniRepetidoException
	{
		this.moduloRegistro.registraMedico(medico);
	}
	
	
	/**
	 * Pone al paciente en el modulo de espera si esta registrado y no se encontraba ya esperando.
	 * Pre: paciente != null, fechaIngreso != null
	 * @param paciente
	 * @param fechaIngreso
	 * @throws PacienteYaIngresadoException
	 * @throws PacienteNoRegistradoException
	 */
	public void ingresaPaciente(Paciente paciente, LocalDate fechaIngreso) throws PacienteYaIngresadoException, PacienteNoRegistradoException
	{
		if (this.moduloRegistro.pacienteIsRegistrado(paciente))
		{
			this.moduloEspera.ingresaPaciente(paciente);
			this.moduloAtenciones.agregarAtencion(paciente, fechaIngreso);
		}
		else
			throw new PacienteNoRegistradoException("el paciente no ha sido registrado", paciente.getDni());
		
	}
	

	/**
	 * Pre: medico != null y paciente != null
	 * @param medico
	 * @param paciente
	 * @throws PacienteNoIngresadoException
	 * @throws MedicoNoRegistradoException
	 */
	public void atiendePaciente(IMedico medico, Paciente paciente) throws PacienteNoIngresadoException, MedicoNoRegistradoException
	{
		if (!this.moduloRegistro.medicoIsRegistrado(medico))
			throw new MedicoNoRegistradoException("el medico no esta registrado", medico.getDni());
		
		if (this.moduloEspera.isEnEspera(paciente))
			this.moduloEspera.sacarDeEspera(paciente);
		
		this.moduloAtenciones.agregarConsulta(paciente, medico);
		
	}
	
	
	public void internaPaciente(Paciente paciente, Habitacion habitacion) throws HabitacionLlenaException, PacienteNoIngresadoException, PacienteYaInternadoException
	{
		this.moduloAtenciones.setHabitacion(paciente, habitacion);
	}
	
	
	/**
	 * Sobrecarga de la funcion egresaPaciente. Esta se usa cuando su estadia duro 0 dias.
	 * Pre: paciente no es null.
	 * @param paciente
	 * @return
	 * @throws EgresoSinMedicoException
	 * @throws PacienteNoIngresadoException 
	 */
	public Factura egresaPaciente(Paciente paciente) throws EgresoSinMedicoException, PacienteNoIngresadoException
	{
		return this.egresaPaciente(paciente, 0);
	}
	
	/**
	 * Pre: paciente != null, cantDias >= 0
	 * @param paciente
	 * @param cantDias
	 * @return factura de la estadia del paciente
	 * @throws EgresoSinMedicoException
	 * @throws PacienteNoIngresadoException 
	 */
	public Factura egresaPaciente(Paciente paciente, int cantDias) throws EgresoSinMedicoException, PacienteNoIngresadoException
	{
		return this.moduloAtenciones.egresarPaciente(paciente, cantDias);
	}
	/**
	 * Se debe llamar a este metodo al menos una vez para saber cuanto cobrarle a un paciente por la asignacion de una habitacion
	 * @param costo precondicion: el costo debe ser positivo
	 */
	public void setCostoAsignacionHabitacion(double costo)
	{
		this.moduloGestionCostos.setCostoAsignacionHabitacion(costo);
	}
	/**
	 * Se debe llamar a este metodo por cada tipo distinto de habitacion al menos una vez antes de egresar a un paciente, ya que sino no hay forma de saber cuanto cobrarle
	 * @param tipoHabitacion tipos soportados: privada, intensiva, compartida
	 * @param costo precondicion: el costo será mayor a 0
	 * @throws HabitacionInvalidaException de ser ingresado un tipo invalido se lanza esta excepcion
	 */
	public void setCostoHabitacion(String tipoHabitacion, double costo) throws HabitacionInvalidaException
	{
		this.moduloGestionCostos.setCostoHabitacion(tipoHabitacion, costo);
	}
	/**
	 * De no llamarse a este metodo por defecto los medicos tienen un honorario basico de 20.000$
	 * @param honorario pre: debe ser valido
	 */
	public void setHonorarioBasicoMedico(double honorario) {
		this.moduloGestionCostos.setHonorarioBasicoMedico(honorario);
	}
	
	
}
