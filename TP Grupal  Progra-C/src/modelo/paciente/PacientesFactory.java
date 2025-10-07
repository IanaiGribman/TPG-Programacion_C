package modelo.paciente;

import modelo.Domicilio;

public class PacientesFactory {
	public Paciente creaPaciente(String rangoEtario, Domicilio domicilio, String dni, String nombre, String apellido, String telefono) {
		Paciente paciente = null;
		switch (rangoEtario.toLowerCase()) {
		case "ninio": new PacienteNinio(nombre, domicilio, telefono, dni); break;
		case "joven": new PacienteJoven(nombre, domicilio, telefono, dni); break;
		case "mayor": new PacienteMayor(nombre, domicilio, telefono, dni); break;
		}
		return paciente;
	}

}
