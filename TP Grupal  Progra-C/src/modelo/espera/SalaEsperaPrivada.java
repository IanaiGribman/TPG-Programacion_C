package modelo.espera;

import modelo.Paciente;

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
		this.huesped = null;
	}
	
	public boolean isOcupado()
	{
		return this.huesped != null;
	}
}
