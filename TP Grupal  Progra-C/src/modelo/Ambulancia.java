package modelo;

import patrones.IEstado;

public class Ambulancia {
	IEstado estado;
	
	public void atencionADomicilio()
	{
		this.estado.atencionADomicilio();
	}
	
	public void trasladoAClinica()
	{
		this.estado.trasladoAClinica();
	}
	
	public void retorno()
	{
		this.estado.retorno();
	}
	
	public void mantenimiento()
	{
		this.estado.mantenimiento();
	}
}
