package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Paciente extends Persona {

	private int nroHistoriaClinica;
	private ArrayList<IMedico> medicosConsultados;
	private Habitacion habitacion;
	private LocalDate fechaIngreso;
	
	protected Paciente(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono, dni);
		medicosConsultados = new ArrayList<>();
		this.habitacion = null;
	}

	public void setNroHistoriaClinica(int nroHistoriaClinica) {
		this.nroHistoriaClinica = nroHistoriaClinica;
	}

	
	/**
	 * 
	 * @param medico es parte de la clinica y no es null. la lista de medicos consultados no es null
	 */
	public void agregarConsulta(IMedico medico)
	{
		medicosConsultados.add(medico);
	}
	
	/**
	 * 
	 * @param habitacion es parte de la clinica y no es null
	 */
	public void setHabitacion(Habitacion habitacion)
	{
		this.habitacion = habitacion;
	}
	
	/**
	 * 
	 * @param fechaIngreso no es null
	 */
	public void setFechaIngreso(LocalDate fechaIngreso)
	{
		this.fechaIngreso = fechaIngreso;
	}
	
	public ArrayList<IMedico> getMedicosConsultados()
	{
		return medicosConsultados;
	}
	
	public LocalDate getFechaIngreso()
	{
		return this.fechaIngreso;
	}
	
	public Habitacion getHabitacion()
	{
		return this.habitacion;
	}
	
	public void olvidarIngreso()
	{
		this.fechaIngreso = null;
		this.medicosConsultados.clear();
		this.habitacion = null;
	}

	/**
	 * Precondiciones: El otro paciente no debe ser nulo
	 * 
	 * @param otro El otro paciente con el cual se compara.
	 * @return Devuelve si el paciente actual tiene prioridad ante el otro paciente
	 *         pasado por parametro. El paciente con prioridad deberia ser el que se
	 *         quede con la sala privada. Ante pacientes del mismo rango etario el
	 *         que llama a la funcion tiene prioridad
	 */
	public abstract boolean tengoPrioridad(Paciente otro);

	/**
	 * @return devuelve que tipo de prioridad tiene el paciente ante otro paciente
	 *         joven
	 */
	public abstract boolean prioridadAnteJoven();

	/**
	 * @return devuelve que tipo de prioridad tiene el paciente ante otro paciente
	 *         niño
	 */
	public abstract boolean prioridadAnteNinio();

	/**
	 * @return devuelve que tipo de prioridad tiene el paciente ante otro paciente
	 *         mayor
	 */
	public abstract boolean prioridadAnteMayor();
}
