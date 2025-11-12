package patrones.state;
import modelo.Ambulancia;

public class EstadoEnTaller implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoEnTaller(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public boolean atencionADomicilio() {
		return false;
	}
	
	public boolean trasladoAClinica() {
		return false;
	}
	
	public boolean retorno() {
		return false;
	}
	
	public boolean mantenimiento() {
		ambulancia.setEstado(new EstadoRegresaDeTaller(this.ambulancia));
		return true;
	}
	
	@Override
	public String toString() {
		return "En taller";
	}

}
