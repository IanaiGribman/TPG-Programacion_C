package prueba;

import java.time.LocalDate;

import modelo.Clinica;
import modelo.Domicilio;
import modelo.Factura;
import modelo.IMedico;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionInvalidaException;
import modelo.excepciones.MedicoInvalidoException;
import modelo.excepciones.MedicoNoRegistradoException;
import modelo.excepciones.PacienteNoIngresadoException;
import modelo.excepciones.PacienteNoRegistradoException;
import modelo.excepciones.PacienteYaIngresadoException;
import modelo.habitaciones.HabitacionFactory;
import modelo.medicos.MedicoFactory;
import modelo.paciente.Paciente;
import modelo.paciente.PacientesFactory;

public class Prueba1 {
	public static void main(String[] args) {
		Clinica clin = new Clinica("Clin", new Domicilio("Pais", "Cale", 100), "123345");
		MedicoFactory mf = new MedicoFactory();
		PacientesFactory pf = new PacientesFactory();
		HabitacionFactory hf = new HabitacionFactory();
		clin.setCostoAsignacionHabitacion(1500);
		try {
			clin.setCostoHabitacion("intensiva", 1000);
		} catch (HabitacionInvalidaException e) {
			e.printStackTrace();
		}
		try {
			clin.setCostoHabitacion("compartida", 600);
		} catch (HabitacionInvalidaException e) {
			e.printStackTrace();
		}
		try {
			clin.setCostoHabitacion("privada", 1400);
		} catch (HabitacionInvalidaException e) {
			e.printStackTrace();
		}
		IMedico med1 = null, med2 = null;
		Paciente pJoven = null, pNinio = null, pMayor = null;

		// (String nombre, Domicilio domicilio, String telefono, String dni, String
		// nroMatricula, String especialidad
		try {
			med1 = mf.crearMedico("Mario", new Domicilio("Argentina", "Av avenida", 123), "22353245390", "123456573",
					"1235", "pediatria", "doctorado", "residente");
		} catch (MedicoInvalidoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMensaje());
		}
		try {
			med2 = mf.crearMedico("Mario2", new Domicilio("Argentina", "Av avenida", 123), "22353235390", "123523573",
					"1215", "cirujia", "doctorado", "permanente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		pNinio = pf.creaPaciente("ninio", new Domicilio("Mar del plata", "San Luis", 123), "46029714", "Juan", "Gomez", "---");
		pJoven = pf.creaPaciente("joven", new Domicilio("Mar del plata", "San martin", 1243), "15326614", "Maria", "Gimenez", "2233522352");
		pMayor = pf.creaPaciente("mayor", new Domicilio("Mar del plata", "Luro", 1123), "1250631", "Martin", "Prat", "22363026521");

		try {
			clin.registraMedico(med1);
		} catch (DniRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.registraMedico(med2);
		} catch (DniRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.registraPaciente(pNinio);
		} catch (DniRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.registraPaciente(pJoven);
		} catch (DniRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.ingresaPaciente(pJoven, LocalDate.now());
		} catch (PacienteYaIngresadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PacienteNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.ingresaPaciente(pNinio, LocalDate.now());
		} catch (PacienteYaIngresadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PacienteNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.atiendePaciente(med1, pNinio);
		} catch (PacienteNoIngresadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MedicoNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clin.atiendePaciente(med2, pNinio);
		} catch (PacienteNoIngresadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MedicoNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Factura f1 = null;
		try {
			f1 = clin.egresaPaciente(pNinio);
		} catch (EgresoSinMedicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PacienteNoIngresadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(f1);		

		// avenida", 123), , null, null, null)
		//TODO toString medico, toString pacientes muestre tipo paciente
	}

}
