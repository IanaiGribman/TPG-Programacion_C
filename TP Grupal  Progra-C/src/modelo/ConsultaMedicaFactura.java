package modelo;

import modelo.atenciones.MedicoHonorario;

/**
 * Clase que representa a una consulta medica para un paciente dentro de la factura
 * Es utilizada para encapsular el formato de una consulta medica para un paciente
 */
public class ConsultaMedicaFactura {
	private String nombreMedico, especialidadMedico;
	private double honorario;
	private static double incrementoHonorario = 1.2;
	
	/**
	 * medHon != null
	 * @param medHon
	 */
	public ConsultaMedicaFactura(MedicoHonorario medHon) {
		this.nombreMedico = medHon.getMedico().getNombre();
		this.especialidadMedico = medHon.getMedico().getEspecialidad();
		this.honorario = medHon.getHonorario();
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
