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
import modelo.habitaciones.Habitacion;
import modelo.habitaciones.HabitacionFactory;
import modelo.medicos.MedicoFactory;
import modelo.paciente.Paciente;
import modelo.paciente.PacientesFactory;

public class Prueba1 {
	public static void main(String[] args) {
		try {
			Clinica clin = new Clinica("Clin", new Domicilio("Pais", "Cale", 100), "123345",1500,600,1400,50);
			MedicoFactory mf = new MedicoFactory();
			PacientesFactory pf = new PacientesFactory();
			HabitacionFactory hf = new HabitacionFactory();
			IMedico med1 = null, med2 = null;
			Paciente pJoven = null, pNinio = null, pMayor = null;


			med1 = mf.crearMedico("Mario", new Domicilio("Argentina", "Av avenida", 123), "22353245390", "123456573",
					"1235", "pediatria", "doctorado", "residente");
			System.out.println(med1);

			med2 = mf.crearMedico("Mario2", new Domicilio("Argentina", "Av avenida", 123), "22353235390", "123523573",
					"1215", "cirujia", "doctorado", "permanente");

			pNinio = pf.creaPaciente("ninio", new Domicilio("Mar del plata", "San Luis", 123), "46029714", "Juan",
					"Gomez", "---");
			pJoven = pf.creaPaciente("joven", new Domicilio("Mar del plata", "San martin", 1243), "15326614", "Maria",
					"Gimenez", "2233522352");
			pMayor = pf.creaPaciente("mayor", new Domicilio("Mar del plata", "Luro", 1123), "1250631", "Martin", "Prat",
					"22363026521");
			System.out.println(pNinio);
			System.out.println(pJoven);
			System.out.println(pMayor);
			

			clin.registraMedico(med1);

			clin.registraMedico(med2);

			clin.registraPaciente(pNinio);

			clin.registraPaciente(pJoven);

			clin.ingresaPaciente(pJoven, LocalDate.now());

			clin.ingresaPaciente(pNinio, LocalDate.now());

			clin.atiendePaciente(med1, pNinio);

			clin.atiendePaciente(med2, pNinio);

			Factura f1 = null;
			Habitacion h1 = hf.crearHabitacion("compartida");
			System.out.println(h1);
			clin.internaPaciente(pNinio, h1);
			System.out.println(h1);


			f1 = clin.egresaPaciente(pNinio, 3);
			System.out.println(h1);
			System.out.println(pJoven);


			System.out.println(f1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
