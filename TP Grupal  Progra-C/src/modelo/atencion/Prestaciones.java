package modelo.atencion;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.IMedico;
import modelo.habitaciones.Habitacion;

/**
 * Clase que representa la prestacion para un paciente: 
 * su fecha de ingreso, 
 * la habitacion en la que se interno,
 * los medicos que lo consultaron
 */
public class Prestaciones {
	private ArrayList<IMedico> medicosConsultados;
	private Habitacion habitacion;
	private LocalDate fechaIngreso;
	
	public Prestaciones(LocalDate fechaIngreso) {
		super();
		this.medicosConsultados = new ArrayList<IMedico>();
		this.habitacion = null;
		this.fechaIngreso = fechaIngreso;
	}
	
	public ArrayList<IMedico> getMedicosConsultados() {
		return medicosConsultados;
	}
	
	public void setHabitacion(Habitacion habitacion) {	
		this.habitacion = habitacion;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	
	/**
	 * 
	 * @param medico es parte de la clinica y no es null. la lista de medicos consultados no es null
	 */
	public void agregarConsulta(IMedico medico)
	{
		medicosConsultados.add(medico);
	}
	
	public boolean fueAtendido() {
		return !this.medicosConsultados.isEmpty();
	}
	
}
