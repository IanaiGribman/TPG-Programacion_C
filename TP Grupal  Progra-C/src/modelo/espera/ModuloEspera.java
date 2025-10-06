package modelo.espera;


import modelo.Paciente;
import modelo.excepciones.PacienteYaIngresadoException;

public class ModuloEspera {
	private Patio patio;
	private SalaEsperaPrivada salaEsperaPrivada;
	
	public ModuloEspera()
	{
		patio = new Patio();
		salaEsperaPrivada = new SalaEsperaPrivada();
	}
	
	public boolean isEnEspera(Paciente paciente)
	{
		return salaEsperaPrivada.isInSalaPrivada(paciente) || patio.isInPatio(paciente);
	}
	
	
	protected void ponerEnSalaOPatio(Paciente paciente)
	{
		if (salaEsperaPrivada.isLibre())
			salaEsperaPrivada.setHuesped(paciente);
		else
		{
			Paciente pacienteEnSala = salaEsperaPrivada.getHuesped();
			if (pacienteEnSala.tengoPrioridad(paciente))
				patio.ponerPaciente(paciente);
			else
			{
				patio.ponerPaciente(pacienteEnSala);
				salaEsperaPrivada.setHuesped(paciente);
			}
		}
	}
	
	
	/**
	 * pre: el paciente simpre está en alguno de los dos lugares
	 * @param paciente
	 */
	protected void sacarDeSalaOPatio(Paciente paciente)
	{
		if (salaEsperaPrivada.isInSalaPrivada(paciente))
			salaEsperaPrivada.vaciar();
		else
			patio.sacarPaciente(paciente);
	}
	
	/**
	 * Pre: paciente no es null y debería estar ya registrado en el sitema
	 * @param paciente
	 * @throws PacienteYaIngresadoException
	 */
	public void ingresaPaciente(Paciente paciente) throws PacienteYaIngresadoException
	{
		if (!this.isEnEspera(paciente))
		{
			this.ponerEnSalaOPatio(paciente);
		}
		else
			throw new PacienteYaIngresadoException("el paciente ya esta en espera", paciente.getDni());
	}
	
	/**
	 * Pre: el paciente esta en el modulo de espera
	 * @param paciente
	 */
	public void sacarDeEspera(Paciente paciente)
	{
		this.sacarDeSalaOPatio(paciente);
	}
}
