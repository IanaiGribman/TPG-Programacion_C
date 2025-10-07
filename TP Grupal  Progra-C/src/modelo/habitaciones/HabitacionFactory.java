package modelo.habitaciones;

import modelo.excepciones.HabitacionInvalidaException;

/**
 * Clase que crea una habitacion con su tipo
 */
public class HabitacionFactory {
	/**
	 * Crea una habitacion con su tipo
	 * Arroja excepcion si el tipo es invalido
	 * @param tipo
	 * @return habitacion con tipo (base del decorator)
	 */
	@SuppressWarnings("unused")
	private Habitacion crearHabitacion(String tipo) throws HabitacionInvalidaException {
		Habitacion retorno = null;
		switch (tipo.toLowerCase()) {
		case "privada":
			retorno = new HabitacionPrivada();
			break;
		case "compartida":
			retorno = new HabitacionCompartida();
			break;
		case "intensiva":
			retorno = new HabitacionIntensiva();
			break;
		default:
			throw new HabitacionInvalidaException("Tipo de habitacion invalido: " + tipo);
		}

		return retorno;
	}
}