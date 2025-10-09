package modelo.habitaciones;

public class HabitacionCompartida extends Habitacion {
	private static double costoHabitacionCompartida;

	/**
	 * este constructor no es usado por el factory pero lo dejo por si en un futuro se quiere extender el factory
	 */
	public HabitacionCompartida(int capacidad) {
		super(capacidad);
	}
	public HabitacionCompartida() {
		this(3);
	}

	public static double getCostoHabitacionCompartida() {
		return costoHabitacionCompartida;
	}

	public static void setCostoHabitacionCompartida(double costoHabitacionCompartida) {
		HabitacionCompartida.costoHabitacionCompartida = costoHabitacionCompartida;
	}

	@Override
	public String getTipoHabitacion() {
		return "Habitacion Compartida";
	}

	@Override
	public double getCostoTotal(int dias) {
		if (dias <= 0)
			dias = 1;
		double costoTotal = dias * costoHabitacionCompartida;
		costoTotal += Habitacion.getCostoAsignacion();
		return costoTotal;
	}
	
	
	
	

	

}
