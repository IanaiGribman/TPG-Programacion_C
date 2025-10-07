package modelo.habitaciones;

public abstract class Habitacion {
	private static double costoAsignacion;
	private int capacidad,ocupacion;

	/**
	 * 
	 * @param capacidad debe ser mayor a 0
	 */
	public Habitacion(int capacidad) {
		super();
		this.capacidad = capacidad;
		this.ocupacion = 0;
	}
	/**
	 * 
	 * @return el tipo de la habitacion
	 */
	public abstract String getTipoHabitacion();

	/**
	 * precondicion: se han asignado valores al costo de la habitacion y a la asignacion
	 * @param dias debe ser mayor a 0
	 * @return costo total para los dias internados
	 */
	public abstract double getCostoTotal(int dias);

	public static double getCostoAsignacion() {
		return costoAsignacion;
	}
	/**
	 * @param costoAsignacion el costo debe ser positivo
	 */
	public static void setCostoAsignacion(double costoAsignacion) {
		Habitacion.costoAsignacion = costoAsignacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getOcupacion() {
		return ocupacion;
	}
	
	public boolean hayEspacio()
	{
		return this.ocupacion < this.capacidad;
	}
	
	public void agregarHuesped()
	{
		this.ocupacion++;
	}
	
	public void sacarHuesped()
	{
		this.ocupacion--;
	}

	/**
	 * 
	 * @param ocupacion debe ser un numero positivo menor o igual a la capacidad
	 */
	public void setOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	
	
	

}
