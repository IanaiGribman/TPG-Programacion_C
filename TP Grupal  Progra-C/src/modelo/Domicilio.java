package modelo;

/*
 * Clase concreta que representa al domicilio de la persona
 * Inv: Parametros constantes
 */
public class Domicilio {
	private String ciudad;
	private String calle;
	private int numero;
	
	/**
	 * Constructor
	 * @param ciudad del domicilio
	 * @param calle del domicilio
	 * @param numero del domicilio
	 * Pre: Strings != null y != "", numero > 0
	 */
	public Domicilio(String ciudad, String calle, int numero) {
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public String getCalle() {
		return this.calle;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	@Override
	public String toString() {
		return this.ciudad + ", " + this.calle + " " + this.numero;
	}
}
