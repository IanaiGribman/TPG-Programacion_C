package modelo.espera;

import java.util.ArrayList;

import modelo.Paciente;
import modelo.excepciones.PacienteYaIngresadoException;

public class ModuloEspera {
	private ArrayList<Paciente> listaDeEspera;
	private Patio patio;
	private SalaEsperaPrivada salaEsperaPrivada;
	
	public ModuloEspera()
	{
		listaDeEspera = new ArrayList<>();
		patio = new Patio();
		salaEsperaPrivada = new SalaEsperaPrivada();
	}
	
	protected boolean isEnListaDeEspera(Paciente paciente)
	{
		return this.listaDeEspera.contains(paciente);
	}
	/**
	 * Pre: el paciente no est� en la lista de espera
	 * @param paciente
	 */
	protected void ponerEnListaDeEspera(Paciente paciente)
	{
		this.listaDeEspera.add(paciente);
		System.out.println("se ha colocado a " + paciente.getNombre() + " en la lista de espera");
	}
	
	/**
	 * Pre: el paciente DEBE estar en la lista de espera
	 * @param paciente
	 */
	protected void sacarDeListaDeEspera(Paciente paciente)
	{
		System.out.println("se ha sacado a " + paciente.getNombre() + " de la lista de espera");
		this.listaDeEspera.remove(paciente);
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
	 * pre: el paciente simpre est� en alguno de los dos lugares
	 * @param paciente
	 */
	protected void sacarDeSalaOPatio(Paciente paciente)
	{
		if (!salaEsperaPrivada.isLibre() && salaEsperaPrivada.getHuesped().equals(paciente))
			salaEsperaPrivada.vaciar();
		else
			patio.sacarPaciente(paciente);
	}
	
	/**
	 * Pre: paciente no es null y deber�a estar ya registrado en el sitema
	 * @param paciente
	 * @throws PacienteYaIngresadoException
	 */
	public void ingresaPaciente(Paciente paciente) throws PacienteYaIngresadoException
	{
		if (!this.isEnListaDeEspera(paciente))
		{
			this.ponerEnListaDeEspera(paciente);
			this.ponerEnSalaOPatio(paciente);
		}
		else
			throw new PacienteYaIngresadoException(paciente);
	}
	
	/**
	 * Pre: el paciente esta en el modulo de espera
	 * @param paciente
	 */
	public void sacarDeEspera(Paciente paciente)
	{
		this.sacarDeListaDeEspera(paciente);
		this.sacarDeSalaOPatio(paciente);
	}
}
