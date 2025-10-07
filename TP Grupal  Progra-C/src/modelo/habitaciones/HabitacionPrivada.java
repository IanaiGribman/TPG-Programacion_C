package modelo.habitaciones;

public class HabitacionPrivada extends Habitacion {
	private static double costoHabitacionPrivada;

	/**
	 * por defecto las habitaciones privadas pueden hospedar a una sola persona
	 */
	public HabitacionPrivada() {
		super(1);
	}

	@Override
	public String getTipoHabitacion() {
		return "Habitacion Privada";
	}

	/**
	 * precondicion: los dias son mayores a 0 precondicion: se han asignado valores
	 * al costo de la habitacion y a la asignacion
	 */
	@Override
	public double getCostoTotal(int dias) {
		double costoTotal = dias * costoHabitacionPrivada;
		if (dias >= 2)
			if (dias >= 6)
				costoTotal *= 2;
			else
				costoTotal *= 1.3;
		costoTotal += Habitacion.getCostoAsignacion();
		return costoTotal;
	}

	public static double getCostoHabitacionPrivada() {
		return costoHabitacionPrivada;
	}

	public static void setCostoHabitacionPrivada(double costoHabitacionPrivada) {
		HabitacionPrivada.costoHabitacionPrivada = costoHabitacionPrivada;
	}

}
