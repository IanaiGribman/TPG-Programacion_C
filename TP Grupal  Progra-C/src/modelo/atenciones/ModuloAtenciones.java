package modelo.atenciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.Factura;
import modelo.IMedico;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionLlenaException;
import modelo.excepciones.PacienteNoIngresadoException;
import modelo.excepciones.PacienteYaInternadoException;
import modelo.habitaciones.Habitacion;
import modelo.paciente.Paciente;

public class ModuloAtenciones {
	private List<Atencion> atenciones;
	
	/**
	 * Pre: atenciones != null.
	 * @param atenciones lista vacía.
	 */
	public ModuloAtenciones(List<Atencion> atenciones)
	{
		this.atenciones = atenciones;
	}
	
	public ModuloAtenciones()
	{
		this(new ArrayList<Atencion>());
	}
	
	/**
	 * Pre: paciente != null, fechaIngreso != null.
	 * Post: la lista atenciones contendrá una nueva atención.
	 * @param paciente que será atendido.
	 * @param fechaIngreso fecha cuando ingresó el paciente.
	 */
	public void agregarAtencion(Paciente paciente, LocalDate fechaIngreso)
	{
		Atencion nuevaAtencion = new Atencion(paciente, fechaIngreso);
			atenciones.add(nuevaAtencion);
	}
	
	
	/**
	 * Pre: paciente != null, todas las atenciones con fecha de egreso null van al final.
	 * @param paciente
	 * @return atención del paciente con fecha de egreso nula o null si no se encuentra.
	 */
	protected Atencion getAtencionActualPaciente(Paciente paciente)
	{
		int i = this.atenciones.size() - 1;
		
		while (i >= 0 &&  atenciones.get(i).getFechaEgreso() == null && !atenciones.get(i).getPaciente().equals(paciente))
			i--;
		
		return (i >= 0 && atenciones.get(i).getFechaEgreso() == null)? atenciones.get(i): null;
	}
	
	
	/**
	 * Pre: paciente != null, medico != null.
	 * Post: a la atención del paciente se le agrega un médico consultado.
	 * @param paciente 
	 * @param medico que atendió al paciente.
	 * @throws PacienteNoIngresadoException si no hay ninguna atención en la lista de atenciones que contenga a 
	 * 		   ese paciente y tenga fecha de egreso null.
	 */
	public void agregarConsulta(Paciente paciente, IMedico medico) throws PacienteNoIngresadoException
	{
		Atencion ultAtencion = this.getAtencionActualPaciente(paciente);
		
		if (ultAtencion == null)
			throw new PacienteNoIngresadoException("el paciente no fue ingresado", paciente.getDni());
		
		ultAtencion.agregarConsulta(medico);
	}
	
	
	/**
	 * Pre: paciente != null, habitacion != null.
	 * @param paciente que se internará.
	 * @param habitacion en la que se internará el paciente.
	 * @throws HabitacionLlenaException si la habitación está llena.
	 * @throws PacienteNoIngresadoException 
	 * @throws PacienteYaInternadoException si la atención ya contiene una habitación.
	 */
	public void setHabitacion(Paciente paciente, Habitacion habitacion) throws HabitacionLlenaException, PacienteNoIngresadoException, PacienteYaInternadoException
	{
		if (!habitacion.hayEspacio())
			throw new HabitacionLlenaException("la habitacion esta llena", habitacion.getOcupacion(), habitacion.getCapacidad());
		
		Atencion ultAtencion = this.getAtencionActualPaciente(paciente);
		
		if (ultAtencion == null)
			throw new PacienteNoIngresadoException("el paciente no fue ingresado", paciente.getDni());
		
		if (ultAtencion.fueInternado())
			throw new PacienteYaInternadoException("el paciente ya ha sido internado en un habitacion", paciente.getDni());
		
		ultAtencion.setHabitacion(habitacion);
		habitacion.agregarHuesped();
	}
	
	
	/**
	 * Pre: paciente != null, cantDias >= 0.
	 * @param paciente que egresará.
	 * @param cantDias que estuvo internado.
	 * @return factura de la estadía del paciente (desde que ingresó hasta que egresó).
	 * @throws PacienteNoIngresadoException si el paciente no está ingresado.
	 * @throws EgresoSinMedicoException si el paciente no fue atendido por ningún médico.
	 */
	public Factura egresarPaciente(Paciente paciente, int cantDias) throws PacienteNoIngresadoException, EgresoSinMedicoException
	{
		Atencion ultAtencion = this.getAtencionActualPaciente(paciente);
		Factura nuevaFactura;
		
		if (ultAtencion == null)
			throw new PacienteNoIngresadoException("el paciente no fue ingresado", paciente.getDni());
		
		if (ultAtencion.getMedicosConsultados().isEmpty())
			throw new EgresoSinMedicoException("el paciente no puede egresar porque no fue atendido por ningun medico", paciente.getDni());
		
		if (ultAtencion.fueInternado())
			ultAtencion.getHabitacion().sacarHuesped();
		ultAtencion.setFechaEgreso(cantDias);
		Collections.sort(atenciones);
		nuevaFactura = new Factura(ultAtencion.getMedicosConsultados(), ultAtencion.getHabitacion(), paciente.getNombre(), ultAtencion.getFechaIngreso(), ultAtencion.getFechaEgreso());
		
		return nuevaFactura;
	}
	
	
	/**
	 * Pre: desde != null, hasta != null, la lista atenciones está ordenada por fecha de egreso, desde <= hasta.
	 * @param desde
	 * @param hasta
	 * @return lista con las atenciones finalizadas en un periodo de tiempo
	 */
	public List<Atencion> getAtencionesDesdeHasta(LocalDate desde, LocalDate hasta)
	{
		List<Atencion> nuevaLista = new ArrayList<>();
		int i = 0;
		int n = atenciones.size();
		
		hasta = hasta.plusDays(1);
		
		while (i < n && atenciones.get(i).getFechaEgreso() != null && atenciones.get(i).getFechaEgreso().isBefore(desde))
			i++;
		while (i < n && atenciones.get(i).getFechaEgreso() != null && atenciones.get(i).getFechaEgreso().isBefore(hasta))
		{
			nuevaLista.add(atenciones.get(i));
			i++;
		}
		
		return nuevaLista;
	}
	
	
	/**
	 * desde != null, hasta != null, desde <= hasta.
	 * @param medico del cual se desea buscar los pacientes que atendió en un periodo de tiempo.
	 * @param desde
	 * @param hasta
	 * @return lista con los pacientes que atendió en un periodo de tiempo
	 */
	public List<Paciente> getPacientesAtendidosPorMedicoXDesdeHssta(IMedico medico, LocalDate desde, LocalDate hasta)
	{
		List<Atencion> atencionesPeriodo = this.getAtencionesDesdeHasta(desde, hasta);
		List<Paciente> pacientesAtendidosPorMedicoX = new ArrayList<>();
		
		for (Atencion atencion: atencionesPeriodo)
			if (atencion.fueAtendidoPorMedicoX(medico))
				pacientesAtendidosPorMedicoX.add(atencion.getPaciente());
		
		return pacientesAtendidosPorMedicoX;
	}
}
