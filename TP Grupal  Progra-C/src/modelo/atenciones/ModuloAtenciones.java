package modelo.atenciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.ConsultaPacienteReporte;
import modelo.Factura;
import modelo.IMedico;
import modelo.ReporteMedico;
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
	 * @param atenciones lista vacia.
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
	 * Post: la lista atenciones contendra� una nueva atencion.
	 * @param paciente que sera atendido.
	 * @param fechaIngreso fecha cuando ingresa el paciente.
	 */
	public void agregarAtencion(Paciente paciente, LocalDate fechaIngreso)
	{
		Atencion nuevaAtencion = new Atencion(paciente, fechaIngreso);
			atenciones.add(nuevaAtencion);
	}
	
	
	/**
	 * Pre: paciente != null, todas las atenciones con fecha de egreso null van al final.
	 * @param paciente
	 * @return atencion del paciente con fecha de egreso nula o null si no se encuentra.
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
	 * Post: a la atencion del paciente se le agrega un medico consultado.
	 * @param paciente 
	 * @param medico que atendio al paciente.
	 * @throws PacienteNoIngresadoException si no hay ninguna atencion en la lista de atenciones que contenga a 
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
	 * @param paciente que se interna�.
	 * @param habitacion en la que se internara el paciente.
	 * @throws HabitacionLlenaException si la habitacion esta� llena.
	 * @throws PacienteNoIngresadoException 
	 * @throws PacienteYaInternadoException si la atencion ya contiene una habitacion.
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
	 * @param paciente que egresara.
	 * @param cantDias que estuvo internado.
	 * @return factura de la estadia del paciente (desde que ingresa hasta que egresa).
	 * @throws PacienteNoIngresadoException si el paciente no esta ingresado.
	 * @throws EgresoSinMedicoException si el paciente no fue atendido por ningun medico.
	 */
	public Atencion egresarPaciente(Paciente paciente, int cantDias) throws PacienteNoIngresadoException, EgresoSinMedicoException
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
		
		return ultAtencion;
	}
	
	
	/**
	 * Pre: desde != null, hasta != null, la lista atenciones esta� ordenada por fecha de egreso, desde <= hasta.
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
	 * @param medico del cual se desea buscar los pacientes que atendio en un periodo de tiempo.
	 * @param desde
	 * @param hasta
	 * @return lista con los pacientes que atendio en un periodo de tiempo (cronologicamente)
	 */
	public List<Paciente> getPacientesAtendidosPorMedicoXDesdeHasta(IMedico medico, LocalDate desde, LocalDate hasta)
	{
		List<Atencion> atencionesPeriodo = this.getAtencionesDesdeHasta(desde, hasta);
		List<Paciente> pacientesAtendidosPorMedicoX = new ArrayList<>();
		
		for (Atencion atencion: atencionesPeriodo)
			if (atencion.fueAtendidoPorMedicoX(medico))
				pacientesAtendidosPorMedicoX.add(atencion.getPaciente());
		
		return pacientesAtendidosPorMedicoX;
	}
	
	/**
	 * medico != null, desde != null, hasta != null, desde <= hasta
	 * @param medico del cual se precisa buscar las consultas que brindo
	 * @param desde
	 * @param hasta
	 * @return lista con la informacion de cada consulta que dio un medico desde una fecha a otra
	 */
	public List<ConsultaPacienteReporte> getConsultasPacientePorMedicoXDesdeHasta(IMedico medico, LocalDate desde, LocalDate hasta){
		List<Atencion> atencionesPeriodo = this.getAtencionesDesdeHasta(desde, hasta);
		List<ConsultaPacienteReporte> consultasPacientePorMedicoX = new ArrayList<>();
		
		for(Atencion atencion : atencionesPeriodo)
			for(MedicoHonorario consulta : atencion.getMedicosConsultados())
				if(consulta.getMedico().equals(medico))
					consultasPacientePorMedicoX.add(new ConsultaPacienteReporte(atencion.getPaciente(), atencion.getFechaEgreso(), consulta.getHonorario()));
		
		return consultasPacientePorMedicoX;
	}
	
	
	/**
	 * medico != null, desde != null, hasta != null, desde <= hasta
	 * @param medico
	 * @param desde
	 * @param hasta
	 * @return reporte medico de las consultas que realizo desde una fecha hasta otra
	 */
	public ReporteMedico getReporteMedico(IMedico medico, LocalDate desde, LocalDate hasta) {
		return new ReporteMedico(medico, getConsultasPacientePorMedicoXDesdeHasta(medico, desde, hasta));
	}
}
