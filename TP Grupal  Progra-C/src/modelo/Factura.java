package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import modelo.habitaciones.Habitacion;

/**
 * todos los datos de una factura son inmutables
 */
public class Factura {
	private ArrayList<ConsultaMedicaFactura> consultasMedicas;
	private LocalDate fechaIngreso, fechaEgreso;
	private String nombrePaciente;
	private String tipoHabitacion;
	private double costoHabitacion;
	private int nroFactura;
	private static int sigNum = 1;
	private int cantDias;

	/**
	 * @param medicosConsultados distinto de null y al menos un medico
	 * @param habitacion la habitacion puede ser null, de serlo el paciente no se interno
	 * @param nombrePaciente distinto de null
	 * @param fechaIngreso distinto de null
	 * @param fechaEgreso distindo de null 
	 */
	public Factura(ArrayList<IMedico> medicosConsultados, Habitacion habitacion, String nombrePaciente,
			LocalDate fechaIngreso, LocalDate fechaEgreso) {
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.cantDias = (int) ChronoUnit.DAYS.between(fechaIngreso, fechaEgreso);
		this.nombrePaciente = nombrePaciente;
		if (habitacion != null) {
			this.tipoHabitacion = habitacion.getTipoHabitacion();
			this.costoHabitacion = habitacion.getCostoTotal(cantDias);
		}
		else {
			this.tipoHabitacion = null;
			this.costoHabitacion = 0;
		}
		for (IMedico medico : medicosConsultados) {
			this.consultasMedicas.add(new ConsultaMedicaFactura(medico));
		}
		this.nroFactura = sigNum;
		sigNum++;
	}

	@Override
	public String toString() {
		String texto = "";
		double costoTotal = 0;
		texto = texto.concat("Nro Factura: " + this.nroFactura + "\n");
		texto = texto.concat("Nombre Paciente: " + this.nombrePaciente + "\n");
		texto = texto.concat("Fecha Ingreso: " + this.fechaIngreso.toString() + "\n");
		texto = texto.concat("Fecha Egreso: " + this.fechaEgreso.toString() + "\n");
		texto = texto.concat("Cantidad de dias: " + this.cantDias + "\n");
		if (this.tipoHabitacion != null) {
			costoTotal += this.costoHabitacion;
			texto = texto.concat("Habitacion tipo: " + this.tipoHabitacion + "         Costo:         $"
					+ this.costoHabitacion + "\n");
		}
		for (ConsultaMedicaFactura consulta : this.consultasMedicas) {
			costoTotal += consulta.getHonorario();
			texto = texto.concat(consulta.toString() + "\n");
		}
		texto = texto.concat("                                         Total:       $" + costoTotal);
		return texto;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public int getNroFactura() {
		return nroFactura;
	}

	public int getCantDias() {
		return cantDias;
	}

	public ArrayList<ConsultaMedicaFactura> getConsultasMedicas() {
		return consultasMedicas;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public double getCostoHabitacion() {
		return costoHabitacion;
	}
	
	
}
