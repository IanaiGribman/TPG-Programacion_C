package modelo;

import modelo.atenciones.MedicoHonorario;

public class ConsultaMedicaFactura {
	private String nombreMedico, especialidadMedico;
	private double honorario;
	private static double incrementoHonorario = 1.2;
	
	public ConsultaMedicaFactura(MedicoHonorario medHon) {
		this.nombreMedico = medHon.getMedico().getNombre();
		this.especialidadMedico = medHon.getMedico().getEspecialidad();
		this.honorario = medHon.getHonorario() * incrementoHonorario;
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
		return"Nombre Medico: " + this.nombreMedico + "       Especialidad: " + this.especialidadMedico + "      Subtotal:     $" + String.format("%.2f",this.honorario);
	}
	
	

}
