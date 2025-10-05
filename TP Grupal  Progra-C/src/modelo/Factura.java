package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	private Habitacion habitacion;
	private ArrayList<IMedico> medicosConsultados;
	private LocalDate fechaIngreso;
	private String nombrePaciente;
	private int nroFactura;
	private static int sigNum = 1;
	private int cantDias;
	
	/**
	 * 
	 * @param nombrePaciente no puede ser null
	 * @param fechaIngreso no puede ser null
	 * @param medicosConsultados no puede ser null
	 * @param habitacion
	 * @param cantDias
	 */
	public Factura(String nombrePaciente, LocalDate fechaIngreso, ArrayList<IMedico> medicosConsultados, Habitacion habitacion,  int cantDias)
	{
		this.fechaIngreso = fechaIngreso;
		this.nombrePaciente = nombrePaciente;
		this.habitacion = habitacion;
		this.cantDias = cantDias;
		this.medicosConsultados = medicosConsultados;
		this.nroFactura = sigNum;
		sigNum++;
	}

	@Override
	public String toString() {
		String texto = "";
		double costoTotal = 0;
		texto = texto.concat("N° Factura: " + this.nroFactura + "\n");
		texto = texto.concat("Nombre Paciente: " + this.nombrePaciente + "\n");
		texto = texto.concat("Fecha Ingreso: " + this.fechaIngreso.toString() + "\n");
		texto = texto.concat("Fecha Egreso: " + getFechaEgreso().toString() + "\n");
		texto = texto.concat("Cantidad de dias: " + this.cantDias + "\n");
		if (this.habitacion != null) {
			double costoHabitacion = habitacion.getCostoTotal(this.cantDias);
			costoTotal += costoHabitacion;
			texto = texto.concat("Habitacion tipo: " + habitacion.getTipoHabitacion() + "         Costo:         $" + costoHabitacion + "\n");
		}
		texto = texto.concat("");
		for (IMedico medico : medicosConsultados) {
			double honorario = medico.getHonorario();
			costoTotal += honorario;
			texto = texto.concat("Nombre Médico: " + medico.getNombre() + "       Especialidad: " + medico.getEspecialidad() + "      Subtotal:     $" + honorario + "\n");
		}
		texto = texto.concat("                                         Total:       $" + costoTotal);
		return texto;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public ArrayList<IMedico> getMedicosConsultados() {
		return medicosConsultados;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	
	public LocalDate getFechaEgreso() {
		return fechaIngreso.plusDays(cantDias);
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
	
	
	
	
}
	
	
	

