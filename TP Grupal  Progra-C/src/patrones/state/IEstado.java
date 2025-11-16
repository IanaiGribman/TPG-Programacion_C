package patrones.state;

/**
 * Define los metodos que debe implementar cada estado
 */
public interface IEstado {
	void atencionADomicilio();
	void trasladoAClinica();
	void retorno();
	void mantenimiento();
	boolean puedeAtencionADomicilio();
	boolean puedeTrasladoAClinica();
	boolean puedeRetorno();
	boolean puedeMantenimiento();
}
