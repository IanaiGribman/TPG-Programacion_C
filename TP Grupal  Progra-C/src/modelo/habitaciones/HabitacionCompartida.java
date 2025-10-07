package modelo.habitaciones;

public class HabitacionCompartida extends Habitacion {
	private static double costoHabitacionCompartida;

	public HabitacionCompartida() {
		super(3);
		// TODO Auto-generated constructor stub
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
		double costoTotal = dias * costoHabitacionCompartida;
		costoTotal += Habitacion.getCostoAsignacion();
		return costoTotal;
	}
	
	
	
	

	

}
