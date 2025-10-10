package modelo.atenciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.IMedico;
import modelo.habitaciones.Habitacion;
import modelo.paciente.Paciente;

/**
 * Almacena la fecha de ingreso, egreso, habitacion de internacion, medicos consultados de un paciente
 * en una estada (desde que ingresa hasta que egresa) y gestiona la insercion de dichos datos.
 */

public class Atencion implements Comparable<Atencion> 
{
	private LocalDate fechaIngreso;
	private LocalDate fechaEgreso;
	private Habitacion habitacion;
	private Paciente paciente;
	private int cantDias;
	private List<MedicoHonorario> medicosConsultados;
	
	
	/**
	 * Pre:
	 *  -paciente != null.
	 *  -fechaIngreso != null.
	 *  -medicosConsultados != null.
	 * @param paciente que recibira� algun tipo de atencion.
	 * @param fechaIngreso fecha que ingresa el paciente a la clinica.
	 * @param medicosConsultados lista vacia.
	 */
	public Atencion(Paciente paciente, LocalDate fechaIngreso, List<MedicoHonorario> medicosConsultados)
	{
		this.paciente = paciente;
		this.fechaIngreso = fechaIngreso;
		this.medicosConsultados = medicosConsultados;
	}
	
	public Atencion(Paciente paciente, LocalDate fechaIngreso)
	{
		this(paciente, fechaIngreso, new ArrayList<MedicoHonorario>());
	}
	
	/**
	 * Pre: medico != null.
	 * Post: la lista medicosConsultados contiene a medico despues de la ejecucipn.
	 * @param medico que tuvo una consulta con el paciente referencia por el campo paciente.
	 */
	public void agregarConsulta(IMedico medico)
	{
		
		MedicoHonorario medHon = new MedicoHonorario(medico);
		this.medicosConsultados.add(medHon);
	}
	
	/**
	 * Pre: habitacion != null, tiene espacio disponible y this.habitacion == null
	 * Post: el campo habitacion contiene la misma referencia que el parametro habitacion.
	 * @param habitacion donde se interna el paciente.
	 */
	public void setHabitacion(Habitacion habitacion)
	{
		this.habitacion = habitacion;
	}
	
	/**
	 * Pre: fechaIngreso != null, cantDias >= 0
	 * Post: el campo fechaEgreso queda con fechaIngreso + cantDias.
	 * @param cantDias que el paciente estuvo internado.
	 */
	public void setFechaEgreso(int cantDias)
	{
		this.cantDias = cantDias;
		this.fechaEgreso = this.fechaIngreso.plusDays(cantDias);
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public List<MedicoHonorario> getMedicosConsultados() {
		return medicosConsultados;
	}
	
	
	public int getCantDias() {
		return cantDias;
	}

	public boolean fueInternado()
	{
		return this.habitacion != null;
	}
	
	public boolean fueAtendidoPorMedicoX(IMedico medico)
	{
		Iterator<MedicoHonorario> it = this.medicosConsultados.iterator();
		boolean encontrado = false;
		while (it.hasNext() && !encontrado)
		{
			encontrado = it.next().getMedico().equals(medico);
		}
		return encontrado;
	}
	
	@Override
	public int compareTo(Atencion otro)
	{
		int comparacion;
		
		if (this.fechaEgreso == null &&  otro.fechaEgreso == null)
			comparacion = 0;
		else
			if (this.fechaEgreso == null)
				comparacion = 1;
			else
				if (otro.fechaEgreso == null)
					comparacion = -1;
				else
					comparacion = this.fechaEgreso.compareTo(otro.fechaEgreso);
		return comparacion;		
	}

	@Override
	public String toString() {
		return "fechaIngreso=" + fechaIngreso + ", fechaEgreso=" + fechaEgreso + ", paciente=" + paciente
				+ ", medicosConsultados=" + medicosConsultados;
	}
	
	
}
