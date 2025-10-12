package modelo.atenciones;

import modelo.IMedico;

/**
 * Clase que encapsula el formato de un medico junto a su honorario en el momento de realizar la consulta (el egreso del paciente)
 * Almacena una referencia a un medico y su honorario en el momento de instanciarse el objeto.
 */
public class MedicoHonorario {
	private IMedico medico;
	private double honorario;
	
	/**
	 * Pre: medico != null
	 * @param medico
	 */
	public MedicoHonorario(IMedico medico)
	{
		this.medico = medico;
		this.honorario = medico.getHonorario();
	}

	public IMedico getMedico() {
		return medico;
	}

	public double getHonorario() {
		return honorario;
	}
}
