package prueba;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import modelo.Clinica;
import modelo.Domicilio;
import modelo.IMedico;
import modelo.excepciones.DniRepetidoException;
import modelo.excepciones.EgresoSinMedicoException;
import modelo.excepciones.HabitacionInvalidaException;
import modelo.excepciones.HabitacionLlenaException;
import modelo.excepciones.MedicoInvalidoException;
import modelo.excepciones.MedicoNoRegistradoException;
import modelo.excepciones.PacienteInvalidoException;
import modelo.excepciones.PacienteNoIngresadoException;
import modelo.excepciones.PacienteNoRegistradoException;
import modelo.excepciones.PacienteYaIngresadoException;
import modelo.excepciones.PacienteYaInternadoException;
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
			cirujanoDoctoradoPermanente = medicoFactory.crearMedico("RaulCirujanoDoctorado", todos, "123", "567", "1", "cirujia", "doctorado", "permanente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		try {
			cirujanoMagisterResidente = medicoFactory.crearMedico("RaulCirujanoMagister", todos, "123", "568", "1", "cirujia", "magister", "residente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		try {
			pediatraMagisterPermanente = medicoFactory.crearMedico("RaulPediatraMagister", todos, "123", "569", "1", "pediatria", "magister", "permanente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		try {
			clinicoDoctoradoResidente = medicoFactory.crearMedico("RaulClinicoDoctorado", todos, "123", "560", "1", "clinica", "magister", "residente");
		} catch (MedicoInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		
		PacientesFactory pacientesFactory = new PacientesFactory();
		
		Paciente pacienteJoven = null;
		try {
			pacienteJoven = pacientesFactory.creaPaciente("joven", todos, "123", "Pablojoven", "Tartaria", "678");
		} catch (PacienteInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		Paciente pacienteMayor = null;
		try {
			pacienteMayor = pacientesFactory.creaPaciente("mayor", todos, "124", "Pablomayor", "Tartaria", "678");
		} catch (PacienteInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		Paciente pacienteNinio = null;
		try {
			pacienteNinio = pacientesFactory.creaPaciente("ninio", todos, "125", "Pablopetit", "Tartaria", "678");
		} catch (PacienteInvalidoException e) {
			System.out.println(e.getMensaje());
		}
		
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
		
		
		
		try {
			clinica.atiendePaciente(pediatraMagisterPermanente, pacienteNinio);
		} catch (PacienteNoIngresadoException | MedicoNoRegistradoException e) {
			System.out.println(e.getDni());
		}
		
		
		try {
			clinica.atiendePaciente(clinicoDoctoradoResidente, pacienteNinio);
		} catch (PacienteNoIngresadoException | MedicoNoRegistradoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			clinica.internaPaciente(pacienteNinio, habitacionIntensiva);
		} catch (PacienteNoIngresadoException | PacienteYaInternadoException | HabitacionLlenaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(clinica.egresaPaciente(pacienteNinio, 2));
		} catch (EgresoSinMedicoException | PacienteNoIngresadoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			clinica.atiendePaciente(clinicoDoctoradoResidente, pacienteJoven);
		} catch (PacienteNoIngresadoException | MedicoNoRegistradoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(clinica.egresaPaciente(pacienteJoven));
		} catch (EgresoSinMedicoException | PacienteNoIngresadoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			clinica.atiendePaciente(clinicoDoctoradoResidente, pacienteMayor);
		} catch (PacienteNoIngresadoException | MedicoNoRegistradoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			clinica.internaPaciente(pacienteNinio, habitacionCompartida);
		} catch (PacienteNoIngresadoException | PacienteYaInternadoException | HabitacionLlenaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			clinica.internaPaciente(pacienteMayor, habitacionCompartida);
		} catch (PacienteNoIngresadoException | PacienteYaInternadoException | HabitacionLlenaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(clinica.egresaPaciente(pacienteMayor));
		} catch (EgresoSinMedicoException | PacienteNoIngresadoException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(clinica.getReporteMedico(clinicoDoctoradoResidente, LocalDate.now(), LocalDate.now().plusDays(1)));
		System.out.println(clinica.getReporteMedico(cirujanoDoctoradoPermanente, LocalDate.now(), LocalDate.now().plusDays(1)));
		System.out.println(clinica.getReporteMedico(cirujanoMagisterResidente, LocalDate.now(), LocalDate.now().plusDays(1)));
		System.out.println(clinica.getReporteMedico(pediatraMagisterPermanente, LocalDate.now(), LocalDate.now().plusDays(1)));
		
		
		
		
		
		
	}

}
