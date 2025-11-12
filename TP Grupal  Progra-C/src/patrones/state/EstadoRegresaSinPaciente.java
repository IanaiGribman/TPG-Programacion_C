package patrones.state;
import modelo.simulacion.Ambulancia;

public class EstadoRegresaSinPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoRegresaSinPaciente(Ambulancia ambulancia) {
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
	public void retorno() {
		ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
	}
	
	@Override
	public void mantenimiento() {}
	
	@Override
	public String toString() {
		return "Regresando sin paciente";
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
		return false;
	}

}
