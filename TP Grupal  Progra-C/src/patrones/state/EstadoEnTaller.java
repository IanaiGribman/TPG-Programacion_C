package patrones.state;
import modelo.simulacion.Ambulancia;

public class EstadoEnTaller implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoEnTaller(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	@Override
	public void atencionADomicilio() {}
	
	@Override
	public void trasladoAClinica() {}
	
	@Override
	public void retorno() {}
	
	@Override
	public void mantenimiento() {
		ambulancia.setEstado(new EstadoRegresaDeTaller(this.ambulancia));
	}
	
	@Override
	public String toString() {
		return "En taller";
	}

	@Override
	public boolean puedeAtencionADomicilio() {
		return false;
	}

	@Override
	public boolean puedeTrasladoAClinica() {
		return false;
	}

	@Override
	public boolean puedeRetorno() {
		return false;
	}

	@Override
	public boolean puedeMantenimiento() {
		return true;
	}

}
