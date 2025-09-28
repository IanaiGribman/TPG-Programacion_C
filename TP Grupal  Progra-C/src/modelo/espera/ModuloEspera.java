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
	
	private boolean pacienteEstaEnListaDeEspera(Paciente paciente)
	{
		return this.listaDeEspera.contains(paciente);
	}
	/**
	 * Pre: el paciente no está en la lista de espera
	 * @param paciente
	 */
	private void ponerPacienteEnListaDeEspera(Paciente paciente)
	{
		this.listaDeEspera.add(paciente);
		System.out.println("se ha colocado a " + paciente.getNombre() + " en la lista de espera");
	}
	
	private void ponerPacienteEnSalaOPatio(Paciente paciente)
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
	 * Pre: paciente no es null y debería estar ya registrado en el sitema
	 * @param paciente
	 * @throws PacienteYaIngresadoException
	 */
	public void ingresaPaciente(Paciente paciente) throws PacienteYaIngresadoException
	{
		if (!this.pacienteEstaEnListaDeEspera(paciente))
		{
			this.ponerPacienteEnListaDeEspera(paciente);
			this.ponerPacienteEnSalaOPatio(paciente);
		}
		else
			throw new PacienteYaIngresadoException(paciente);
	}
}
