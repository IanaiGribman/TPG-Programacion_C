package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	private Habitacion habitacion;
	private ArrayList<IMedico> medicosConsultados;
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	private String nombrePaciente;
	private int nroFactura;
	private static int sigNum = 1;
	
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
		this.fechaSalida = fechaIngreso.plusDays(cantDias);
		this.nombrePaciente = nombrePaciente;
		this.habitacion = habitacion;
		this.medicosConsultados = medicosConsultados;
		this.nroFactura = sigNum;
		sigNum++;
	}
}
	
	
	

