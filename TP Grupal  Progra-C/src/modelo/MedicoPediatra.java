package modelo;

/**
 * Clase concreta que representa al medico de especialidad pediatria
 */
public class MedicoPediatra extends Medico {
	private String especialidad = "Clinico";
	
	public MedicoPediatra(String nombre, Domicilio domicilio, String telefono, String dni, String nroMatricula) {
		super(nombre, domicilio, telefono, dni, nroMatricula);
	}


	/**
	 * Devuelve el honorario para un medico pediatra
	 */
	@Override
	public double getHonorario() {
		return Medico.HonorarioBasico * 1.07;
	}

	@Override
	public String getEspecialidad() {
		return this.especialidad;
	}	
}
