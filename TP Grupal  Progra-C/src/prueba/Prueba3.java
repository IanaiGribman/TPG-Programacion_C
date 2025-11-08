package prueba;

import java.time.LocalDate;

import modelo.Clinica;
import modelo.Domicilio;
import modelo.Factura;
import modelo.IMedico;
import modelo.habitaciones.Habitacion;
import modelo.habitaciones.HabitacionFactory;
import modelo.medicos.MedicoFactory;
import modelo.paciente.Paciente;
import modelo.paciente.PacientesFactory;

public class Prueba3 {
	public static void main(String[] args) {
		try {
			Clinica clin = new Clinica("Clin", new Domicilio("Pais", "Cale", 100), "123345",1500,600,1400,50);
			MedicoFactory mf = new MedicoFactory();
			PacientesFactory pf = new PacientesFactory();
			HabitacionFactory hf = new HabitacionFactory();
			IMedico med1 = null, med2 = null;
			Paciente pJoven = null, pNinio = null, pMayor = null;
			LocalDate fechaBase = LocalDate.of(2025, 10, 10);

			med1 = mf.crearMedico("Mario", new Domicilio("Argentina", "Av avenida", 123), "22353245390", "123456573",
					"1235", "pediatria", "doctorado", "residente");

			med2 = mf.crearMedico("Mario2", new Domicilio("Argentina", "Av avenida", 123), "22353235390", "123523573",
					"1215", "cirujia", "doctorado", "permanente");

			pNinio = pf.creaPaciente("ninio", new Domicilio("Mar del plata", "San Luis", 123), "46029714", "Juan",
					"Gomez", "---");
			pJoven = pf.creaPaciente("joven", new Domicilio("Mar del plata", "San martin", 1243), "15326614", "Maria",
					"Gimenez", "2233522352");
			pMayor = pf.creaPaciente("mayor", new Domicilio("Mar del plata", "Luro", 1123), "1250631", "Martin", "Prat",
					"22363026521");

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
			
			
			
			System.out.println(clin.getReporteMedico(med2, fechaBase, fechaBase.plusDays(5)).toString());


			System.out.println(f1);
			
	
			/*ArrayList<MedicoHonorario> medicos = new ArrayList<MedicoHonorario>();
			MedicoHonorario medHon = new MedicoHonorario(med1);
			medicos.add(medHon);
			
			System.out.println("Si?" + med1.equals(medHon));
			
			
			
			Atencion atencion = new Atencion(pNinio, fechaBase, medicos);
			
			System.out.println(atencion.getFechaEgreso());
			System.out.println(atencion.fueAtendidoPorMedicoX(med1));
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
