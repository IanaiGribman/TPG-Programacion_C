package modelo.espera;
import modelo.Paciente;

/**
 * Esta clase tiene una atributo huesped que representa al paciente que se encuentra en la sala privada, es null si no hay nadie
 * quedándose
 */

public class SalaEsperaPrivada {
	private Paciente huesped;

	public SalaEsperaPrivada() 
	{
		super();
		this.huesped = null;
	}

	public Paciente getHuesped() 
	{
		return huesped;
	}
	
	public void vaciar()
	{
		System.out.println("se ha sacado a " + this.huesped.getNombre() + " de la sala privada");
		this.huesped = null;
	}
	
	public boolean isLibre()
	{
		return this.huesped == null;
	}
	
	/**
	 * Pre: el paciente no debe ser null y debe estar en la lista de espera
	 * @param paciente
	 */
	public void setHuesped(Paciente paciente)
	{
		this.huesped = paciente;
		System.out.println("se ha colocado a " + paciente.getNombre() + " en la sala de espera privada");
	}
}
