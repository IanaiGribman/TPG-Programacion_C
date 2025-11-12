package patrones.state;

import modelo.Ambulancia;

public class EstadoDisponible implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoDisponible(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}
	
	@Override
	public void atencionADomicilio() {
		ambulancia.setEstado(new EstadoAtiendeDomicilio(this.ambulancia));
	}
	
	@Override
	public void trasladoAClinica() {
		ambulancia.setEstado(new EstadoTrasladaPaciente(this.ambulancia));
	}
	
	@Override
	public void retorno() {}
	
	@Override
	public void mantenimiento() {
		ambulancia.setEstado(new EstadoEnTaller(this.ambulancia));
	}
	
	@Override
	public String toString() {
		return "Disponible";
	}

	@Override
	public boolean puedeAtencionADomicilio() {
		return true;
	}

	@Override
	public boolean puedeTrasladoAClinica() {
		return true;
	}

	@Override
	public boolean puedeRetorno() {
		return true;
	}

	@Override
	public boolean puedeMantenimiento() {
		return true;
	}

}
