package modelo;

import java.util.List;

public class ReporteMedico {
	private List<ConsultaPacienteReporte> consultasPacientes;
	private IMedico medico;
	
	public ReporteMedico(IMedico medico, List<ConsultaPacienteReporte> consultasPacientes) {
		super();
		this.medico = medico;
		this.consultasPacientes = consultasPacientes;
	}

	public List<ConsultaPacienteReporte> getConsultasPacientes() {
		return consultasPacientes;
	}

	public IMedico getMedico() {
		return medico;
	}
	
	@Override
	public String toString() {
		String retorno = "Reporte para " + this.medico.getNombre() + "\n";
		double total = 0;
		
		for(ConsultaPacienteReporte consulta : this.consultasPacientes) {
			retorno += "Fecha: " + consulta.getFecha().toString() + "    Paciente: " + consulta.getPaciente().getNombre() + "    Honorario: $" + String.format("%.2f",consulta.getHonorario()) + "\n";
			total += consulta.getHonorario();
		}
		
		retorno += "                                             Total: $" + String.format("%.2f",total);
		
		return retorno;
	}
}
