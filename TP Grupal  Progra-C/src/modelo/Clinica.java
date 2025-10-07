package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.atencion.ModuloAtencion;
import modelo.espera.ModuloEspera;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionLlenaException;
import modelo.excepciones.MedicoNoRegistradoException;
import modelo.excepciones.PacienteNoIngresadoException;
import modelo.excepciones.PacienteNoRegistradoException;
import modelo.excepciones.PacienteYaIngresadoException;
import modelo.habitaciones.Habitacion;
import modelo.paciente.Paciente;
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
	private ModuloAtencion moduloAtencion;
	private int sigNumHistoriaClinica = 0;

	/**Pre: los par�metros son distintos de null
	 * Instancia los m�dulos
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
		moduloAtencion = new ModuloAtencion();
	}
	
	
	/**
	 * * Agrega al paciente al hashmap de todos los pacientes de la cl�nica si ya no estaba previamente registrado.
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
	 * Agrega al m�dico al hashmap de todos los m�dicos de la cl�nica si ya no estaba previamente registrado.
	 * Pre: medico no es null.
	 * @param medico
	 * @throws DniRepetidoException
	 */
	public void registraMedico(IMedico medico) throws DniRepetidoException
	{
		this.moduloRegistro.registraMedico(medico);
	}
	
	
	/**
	 * Pone al paciente en el m�dulo de espera si est� registrado y no se encontraba ya esperando.
	 * Pre: paciente no es null, fechaIngreso no es null
	 * @param paciente
	 * @param fechaIngreso
	 * @throws PacienteYaIngresadoException
	 * @throws PacienteNoRegistradoException
	 */
	public void ingresaPaciente(Paciente paciente, LocalDate fechaIngreso) throws PacienteYaIngresadoException, PacienteNoRegistradoException
	{
		if (this.moduloRegistro.pacienteIsRegistrado(paciente))
		{
			this.moduloAtencion.agregarPaciente(paciente.getDni(), fechaIngreso);
			this.moduloEspera.ingresaPaciente(paciente);
		}
		else
			throw new PacienteNoRegistradoException("el paciente no ha sido registrado", paciente.getDni());
		
	}
	

	/**
	 * El paciente guarda la referencia al m�dico que lo atendi� si es que estaba en la lista de espera o ya fue
	 * atendido por un m�dico ("lista de atenci�n"). Si estaba en la lista de espera, se lo quita de ella.
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
			if(this.moduloAtencion.isPacienteEnAtencion(paciente.getDni())) {
				if(!this.moduloEspera.isEnEspera(paciente))
					this.moduloEspera.sacarDeEspera(paciente);
				this.moduloAtencion.agregarConsultaMedica(paciente.getDni(), medico);
			}	
			else
				throw new PacienteNoIngresadoException("el paciente no ha sido ingresado", paciente.getDni());
		}
		else
			throw new MedicoNoRegistradoException("el medico no ha sido registrado", medico.getDni());
	}
	
	
	public void internaPaciente(Paciente paciente, Habitacion habitacion) throws HabitacionLlenaException {
		this.moduloAtencion.internaPaciente(paciente.getDni(), habitacion);
	}
	
	
	/**
	 * Sobrecarga de la funci�n egresaPaciente. Esta se usa cuando su estad�a dur� 0 d�as.
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
	 * Genera la factura, libera la habitaci�n si es que el paciente se alojaba en una y hace que el paciente olvide
	 * las consultas que tuvo con m�dicos, la habitaci�n donde se aloj� y le fecha de ingreso.
	 * Pre: paciente no es null.
	 * @param paciente
	 * @param cantDias
	 * @return factura de la estad�a del paciente
	 * @throws EgresoSinMedicoException
	 */
	public Factura egresaPaciente(Paciente paciente, int cantDias) throws EgresoSinMedicoException
	{
		ArrayList<IMedico> medicos = this.moduloAtencion.getMedicosAtencion(paciente.getDni());
		Factura factura = this.moduloAtencion.egresaPaciente(paciente, cantDias);
		this.moduloReportes.agregarConsultasPaciente(medicos, factura.getFechaEgreso(), paciente.getNombre());
		
		return factura;
	}
	
	
}
