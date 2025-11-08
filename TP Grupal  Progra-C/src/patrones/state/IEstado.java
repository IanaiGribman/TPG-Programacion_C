package patrones.state;

public interface IEstado {
	void atencionADomicilio();
	void trasladoAClinica();
	void retorno();
	void mantenimiento();
}
