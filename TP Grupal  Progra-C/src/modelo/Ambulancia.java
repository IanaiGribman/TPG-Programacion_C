package modelo;

import patrones.IEstado;
import patrones.observer.ObservableAbstracto;
import patrones.EstadoDisponible;

public class Ambulancia extends ObservableAbstracto {
	IEstado estado; // representa estado actual de la ambulancia
	
	public Ambulancia() {
		super();
		this.estado = new EstadoDisponible(this); // estado inicial de la ambulancia
	}

	
	public void setEstado (IEstado estado) {
		this.estado = estado;
	}
	
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


	@Override
	public String toString() {
		return "Ambulancia [estado=" + estado + "]";
	}
	
	
}
