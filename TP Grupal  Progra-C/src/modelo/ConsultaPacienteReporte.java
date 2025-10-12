package modelo;

import java.time.LocalDate;

import modelo.paciente.Paciente;

/**
 * Clase que representa la consulta de un paciente para un medico
 * Utilizada para encapsular el formato de tal consulta para realizar el reporte del medico
 */
public class ConsultaPacienteReporte {
	private Paciente paciente;
	private LocalDate fecha;
	private double honorario;
	
	/**
	 * paciente != null, fecha !- null, honorario > 0
	 * @param paciente
	 * @param fecha
	 * @param honorario
	 */
	public ConsultaPacienteReporte(Paciente paciente, LocalDate fecha, double honorario) {
		super();
		this.paciente = paciente;
		this.fecha = fecha;
		this.honorario = honorario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public double getHonorario() {
		return honorario;
	}
	
	
}
