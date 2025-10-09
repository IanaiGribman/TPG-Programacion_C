package modelo;

import java.time.LocalDate;

import modelo.paciente.Paciente;

public class ConsultaPacienteReporte {
	private Paciente paciente;
	private LocalDate fecha;
	private double honorario;
	
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
