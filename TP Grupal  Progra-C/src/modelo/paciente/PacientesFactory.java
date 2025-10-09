package modelo.paciente;

import modelo.Domicilio;
public class PacientesFactory {
	/**
	 * los parametros deben estar validados y ser distinto de null
	 * @param rangoEtario 
	 * @param domicilio
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @return la referencia al paciente, de ingresar un rango etario invalido devuelve null
	 */
	public Paciente creaPaciente(String rangoEtario, Domicilio domicilio, String dni, String nombre, String apellido, String telefono) {
		Paciente paciente = null;
		switch (rangoEtario.toLowerCase()) {
		case "ninio": paciente = new PacienteNinio(nombre, domicilio, telefono, dni); break;
		case "joven": paciente = new PacienteJoven(nombre, domicilio, telefono, dni); break;
		case "mayor": paciente = new PacienteMayor(nombre, domicilio, telefono, dni); break;
		}
		return paciente;
	}

}
