package prueba;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import modelo.Clinica;
import modelo.Domicilio;
import modelo.IMedico;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.HabitacionInvalidaException;
import modelo.excepciones.MedicoInvalidoException;
import modelo.excepciones.PacienteNoRegistradoException;
import modelo.excepciones.PacienteYaIngresadoException;
import modelo.habitaciones.Habitacion;
import modelo.habitaciones.HabitacionFactory;
import modelo.medicos.MedicoFactory;
import modelo.paciente.Paciente;
import modelo.paciente.PacientesFactory;

public class Prueba {
	public static void main(String []args) {
		Domicilio todos = new Domicilio("Ciudad de todos", "Callse de todos", 1);
		
		Clinica clinica = new Clinica("Clean", new Domicilio("Yugoslavia", "zizek", 1000), "457732");
		
		MedicoFactory medicoFactory = new MedicoFactory();
		IMedico cirujanoDoctoradoPermanente = null;
		IMedico cirujanoMagisterResidente = null;
		IMedico pediatraMagisterPermanente = null;
		IMedico clinicoDoctoradoResidente = null;
		try {
			cirujanoDoctoradoPermanente = medicoFactory.crearMedico("Raul", todos, "123", "567", "1", "cirujia", "doctorado", "permanente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		try {
			cirujanoMagisterResidente = medicoFactory.crearMedico("Raul", todos, "123", "567", "1", "cirujia", "magister", "residente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		try {
			pediatraMagisterPermanente = medicoFactory.crearMedico("Raul", todos, "123", "567", "1", "pediatria", "magister", "permanente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		try {
			clinicoDoctoradoResidente = medicoFactory.crearMedico("Raul", todos, "123", "567", "1", "clinica", "magister", "residente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		
		PacientesFactory pacientesFactory = new PacientesFactory();
		
		Paciente pacienteJoven = pacientesFactory.creaPaciente("joven", todos, "123", "Pablo", "Tartaria", "678");
		Paciente pacienteMayor = pacientesFactory.creaPaciente("mayor", todos, "123", "Pablo", "Tartaria", "678");
		Paciente pacienteNinio = pacientesFactory.creaPaciente("ninio", todos, "123", "Pablo", "Tartaria", "678");
		
		HabitacionFactory habitacionFactory = new HabitacionFactory();
		Habitacion habitacionCompartida = null;
		Habitacion habitacionPrivada = null;
		Habitacion habitacionIntensiva = null;
		
		
		try {
			habitacionCompartida = habitacionFactory.crearHabitacion("compartida");
		} catch (HabitacionInvalidaException e) {
			System.out.println(e.getMensaje());
		}
		try {
			habitacionPrivada = habitacionFactory.crearHabitacion("privada");
		} catch (HabitacionInvalidaException e) {
			System.out.println(e.getMensaje());
		}
		try {
			habitacionIntensiva = habitacionFactory.crearHabitacion("intensiva");
		} catch (HabitacionInvalidaException e) {
			System.out.println(e.getMensaje());
		}
		
		
		
		try {
			clinica.registraMedico(clinicoDoctoradoResidente);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.registraMedico(cirujanoDoctoradoPermanente);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.registraMedico(cirujanoMagisterResidente);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		
		
		try {
			clinica.registraMedico(pediatraMagisterPermanente);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.registraPaciente(pacienteNinio);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.registraPaciente(pacienteJoven);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.registraPaciente(pacienteMayor);
		} catch (DniRepetidoException e) {
			System.out.println(e.getDni());
		}
		
		
		
		try {
			clinica.ingresaPaciente(pacienteNinio, LocalDate.now());
		} catch (PacienteYaIngresadoException | PacienteNoRegistradoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.ingresaPaciente(pacienteJoven, LocalDate.now());
		} catch (PacienteYaIngresadoException | PacienteNoRegistradoException e) {
			System.out.println(e.getDni());
		}
		
		try {
			clinica.ingresaPaciente(pacienteMayor, LocalDate.now());
		} catch (PacienteYaIngresadoException | PacienteNoRegistradoException e) {
			System.out.println(e.getDni());
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
