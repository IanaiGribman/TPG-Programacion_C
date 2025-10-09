package modelo.espera;


import modelo.excepciones.PacienteYaIngresadoException;
import modelo.paciente.Paciente;

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
	
	/**
	 * metodo de uso interno del modulo de espera para decidir donde mandar a los pacientes.
	 * este metodo es el que aplica el doble dispatch de los pacientes
	 * @param paciente pre: este paciente no es null
	 */
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
	 * pre: el paciente simpre esta en alguno de los dos lugares y es no null
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
	 * Pre: paciente no es null y deberia estar ya registrado en el sistema
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
