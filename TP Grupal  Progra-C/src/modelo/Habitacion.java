package modelo;

public abstract class Habitacion {
	private static double costoAsignacion;
	private int capacidad,ocupacion;

	public Habitacion(int capacidad) {
		super();
		this.capacidad = capacidad;
		this.ocupacion = 0;
	}

	public abstract String getTipoHabitacion();

	public abstract double getCostoTotal(int dias);

	public static double getCostoAsignacion() {
		return costoAsignacion;
	}

	public static void setCostoAsignacion(double costoAsignacion) {
		Habitacion.costoAsignacion = costoAsignacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	
	
	

}
