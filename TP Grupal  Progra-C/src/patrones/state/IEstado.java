package patrones.state;

public interface IEstado {
	boolean atencionADomicilio();
	boolean trasladoAClinica();
	boolean retorno();
	boolean mantenimiento();
}
