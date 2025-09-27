package modelo;

public class PacientesFactory {
	public Paciente creaPaciente(String rangoEtario, Domicilio domicilio, String dni, String nombre, String apellido, String telfono) {
		Paciente paciente = null;
		switch (rangoEtario.toLowerCase()) {
		case "ninio": new PacienteNinio(); break;
		case "joven": new PacienteJoven(); break;
		case "mayor": new PacienteMayor(); break;
		}
		return paciente;
		
	}

}
