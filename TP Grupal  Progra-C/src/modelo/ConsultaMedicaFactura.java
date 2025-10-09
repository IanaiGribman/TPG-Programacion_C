package modelo;

public class ConsultaMedicaFactura {
	private String nombreMedico, especialidadMedico;
	private double honorario;
	private static double incrementoHonorario = 1.2;
	
	public ConsultaMedicaFactura(IMedico medico) {
		this.nombreMedico = medico.getNombre();
		this.especialidadMedico = medico.getEspecialidad();
		this.honorario = Math.round(medico.getHonorario() * incrementoHonorario * 100.0) / 100.0 ;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public String getEspecialidadMedico() {
		return especialidadMedico;
	}

	public double getHonorario() {
		return honorario;
	}

	@Override
	public String toString() {
		return"Nombre Medico: " + this.nombreMedico + "       Especialidad: " + this.especialidadMedico + "      Subtotal:     $" + this.honorario;
	}
	
	

}
