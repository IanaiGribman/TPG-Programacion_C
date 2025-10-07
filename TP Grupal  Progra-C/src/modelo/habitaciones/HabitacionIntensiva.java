package modelo.habitaciones;

public class HabitacionIntensiva extends Habitacion {
	private static double costoHabitacionIntensiva;

	/**
	 * por defecto las habitaciones privadas pueden hospedar a una sola persona
	 */
	public HabitacionIntensiva() {
		super(1);
	}

	@Override
	public String getTipoHabitacion() {
		return "Habitacion Intensiva";
	}

	/**
	 * precondicion: los dias son mayores a 0 precondicion: se han asignado valores
	 * al costo de la habitacion y a la asignacion
	 */
	@Override
	public double getCostoTotal(int dias) {
		double costoTotal = Math.pow(costoHabitacionIntensiva, dias);
		costoTotal += Habitacion.getCostoAsignacion();
		return costoTotal;
	}

	public static double getCostoHabitacionIntensiva() {
		return costoHabitacionIntensiva;
	}

	public static void setCostoHabitacionIntensiva(double costoHabitacionIntensiva) {
		HabitacionIntensiva.costoHabitacionIntensiva = costoHabitacionIntensiva;
	}

}
