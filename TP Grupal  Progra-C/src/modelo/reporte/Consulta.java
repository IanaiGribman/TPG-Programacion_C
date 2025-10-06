package modelo.reporte;

import java.time.LocalDate;

/**
 * Clase concreta que representa a una consulta dentro del reporte medico, 
 * contiene la informacion suficiente para generarlo con cada consulta a cada paciente
 * Inv: parametros constantes
 */
public class Consulta {
	private String nombrePaciente;
	private LocalDate fecha;
	private double honorario;
	
	/**
	 * pre: nombrePaciente != null , nombrePaciente != "" , fecha != null , honorario > 0
	 * @param nombrePaciente
	 * @param fecha
	 * @param honorario
	 */
	public Consulta(String nombrePaciente, LocalDate fecha, double honorario) {
		super();
		this.nombrePaciente = nombrePaciente;
		this.fecha = fecha;
		this.honorario = honorario;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public double getHonorario() {
		return honorario;
	}
	
	@Override
	public String toString() {
		return "Fecha: " + this.fecha.toString() + "  Paciente: " + this.nombrePaciente + "  Honorario: " + this.honorario;
	}
}
