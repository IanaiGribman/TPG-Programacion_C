package patrones.state;
import modelo.Ambulancia;

public class EstadoRegresaSinPaciente implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoRegresaSinPaciente(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public boolean atencionADomicilio() {
		ambulancia.setEstado(new EstadoAtiendeDomicilio(this.ambulancia));
		return true;
	}
	
	public boolean trasladoAClinica() {
		ambulancia.setEstado(new EstadoTrasladaPaciente(this.ambulancia));
		return true;
	}
	
	public boolean retorno() {
		ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
		return true;
	}
	
	public boolean mantenimiento() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Regresando sin paciente";
	}

}
