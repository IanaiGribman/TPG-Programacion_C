package modelo.atencion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Factura;
import modelo.IMedico;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionLlenaException;
import modelo.excepciones.InternacionSinMedicoException;
import modelo.habitaciones.Habitacion;
import modelo.paciente.Paciente;

public class ModuloAtencion {
	private HashMap<String, Prestaciones> pacientePrestaciones;

	public ModuloAtencion() {
		super();
		this.pacientePrestaciones = new HashMap<>();
	}
	
	public void agregarPaciente(String dni, LocalDate fecha) {
		this.pacientePrestaciones.put(dni, new Prestaciones(fecha));
	}
	
	/**
	 * 
	 * @param dni (es de un paciente existente)
	 */
	protected void eliminarPaciente(String dni) {
		this.pacientePrestaciones.remove(dni);
	}
	
	public boolean isPacienteEnAtencion(String dni) {
		return this.pacientePrestaciones.containsKey(dni);
	}
	
	public void agregarConsultaMedica(String dni, IMedico medico) {
		this.pacientePrestaciones.get(dni).agregarConsulta(medico);
	}
	
	private void asignarHabitacion(String dni, Habitacion habitacion) {
		this.pacientePrestaciones.get(dni).setHabitacion(habitacion);
	}
	
	public boolean pacienteFueAtendido(String dni) {
		return this.pacientePrestaciones.get(dni).fueAtendido();
	}
	
	public ArrayList<IMedico> getMedicosAtencion(String dni){
		return this.pacientePrestaciones.get(dni).getMedicosConsultados();
	}
	
	/**
	 * El paciente guarda la referencia a la habitaci�n donde se queda y la ocupaci�n de la habitaci�n se incrementa,
	 * si esta tiene espacio disponible y el paciente fue atendido por un m�dico previamente.
	 * Pre: paciente no es null y habitaci�n no es null.
	 * @param paciente
	 * @param habitacion
	 * @throws InternacionSinMedicoException
	 * @throws HabitacionLlenaException
	 */
	public void internaPaciente(String dni, Habitacion habitacion) throws HabitacionLlenaException
	{
		if (!habitacion.hayEspacio())
			throw new HabitacionLlenaException("la habitacion esta llena", habitacion.getOcupacion(), habitacion.getCapacidad());
		
		habitacion.agregarHuesped();
		this.asignarHabitacion(dni, habitacion);
	}
	
	public Factura egresaPaciente(Paciente paciente, int cantDias) throws EgresoSinMedicoException
	{
		String dni = paciente.getDni();
		Prestaciones p = this.pacientePrestaciones.get(dni);
		if (!this.pacienteFueAtendido(dni))
			throw new EgresoSinMedicoException("el paciente no puede ser egresado porque no ha sido atendido por ningun medico", paciente.getDni());
	
		Habitacion habitacionPaciente = p.getHabitacion();
		if (habitacionPaciente != null)
			habitacionPaciente.sacarHuesped();
		
		Factura nuevaFactura = new Factura(paciente.getNombre(), p.getFechaIngreso(), p.getMedicosConsultados(), habitacionPaciente, cantDias);
		
		this.eliminarPaciente(dni);
		
		return nuevaFactura;
	}
}
