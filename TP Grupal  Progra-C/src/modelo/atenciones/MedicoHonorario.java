package modelo.atenciones;

import modelo.IMedico;

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
