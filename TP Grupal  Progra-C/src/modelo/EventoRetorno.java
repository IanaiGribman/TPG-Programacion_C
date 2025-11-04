package modelo;

public class EventoRetorno extends Solicitante {

	private int cantSolicitudes;
	
	public EventoRetorno(Ambulancia ambulancia, int cantSolicitudes) {
		super(ambulancia);
		this.cantSolicitudes = cantSolicitudes;
	}

	@Override
	public String getNombre() {
		return "Sistema";
	}

	@Override
	public void run() {
		for (int i = 0; i < this.cantSolicitudes; i++)
		{
			//llamar ambulancia;
		}
	}

}
