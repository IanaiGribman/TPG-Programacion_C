package modelo;

/**
 * Clase concreta que representa al medico de especialidad cirujia
 */
public class MedicoCirujano extends Medico {
	private String especialidad = "Clinico";
	
	public MedicoCirujano(String nombre, Domicilio domicilio, String telefono, String dni, String nroMatricula) {
		super(nombre, domicilio, telefono, dni, nroMatricula);
	}


	/**
	 * Devuelve el honorario para un medico cirujano
	 */
	@Override
	public double getHonorario() {
		return Medico.HonorarioBasico * 1.1;
	}

	@Override
	public String getEspecialidad() {
		return this.especialidad;
	}	
}
