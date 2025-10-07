package modelo.medicos;

import modelo.Domicilio;

/**
 * Clase concreta que representa al medico de especialidad clinica
 */
public class MedicoClinico extends Medico {
	private String especialidad = "Clinico";
	
	public MedicoClinico(String nombre, Domicilio domicilio, String telefono, String dni, String nroMatricula) {
		super(nombre, domicilio, telefono, dni, nroMatricula);
	}


	/**
	 * Devuelve el honorario para un medico clinico
	 */
	@Override
	public double getHonorario() {
		return Medico.HonorarioBasico * 1.05;
	}

	@Override
	public String getEspecialidad() {
		return this.especialidad;
	}	
}
