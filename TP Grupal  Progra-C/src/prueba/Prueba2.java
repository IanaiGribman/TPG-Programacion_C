package prueba;

import java.time.LocalDate;

import modelo.Clinica;
import modelo.Domicilio;
import modelo.Factura;
import modelo.IMedico;
import modelo.habitaciones.HabitacionFactory;
import modelo.medicos.MedicoFactory;
import modelo.paciente.Paciente;
import modelo.paciente.PacientesFactory;

public class Prueba2 {
	public static void main(String []args) {
		try
		{
			Clinica clin = new Clinica("Clin", new Domicilio("Pais", "Cale", 100), "123345");
			MedicoFactory mf = new MedicoFactory();
			PacientesFactory pf = new PacientesFactory();
			HabitacionFactory hf = new HabitacionFactory();
			clin.setCostoAsignacionHabitacion(1500);
			
			clin.setCostoHabitacion("intensiva", 1000);
			clin.setCostoHabitacion("compartida", 600);
			clin.setCostoHabitacion("privada", 1400);
			
			IMedico med1 = null, med2 = null;
			Paciente pJoven = null, pNinio = null, pMayor = null;
			
			med1 = mf.crearMedico("Mario", new Domicilio("Argentina", "Av avenida", 123), "22353245390", "123456573",
					"1235", "pediatria", "doctorado", "residente");
			med2 = mf.crearMedico("Mario2", new Domicilio("Argentina", "Av avenida", 123), "22353235390", "123523573",
					"1215", "cirujia", "doctorado", "permanente");
			
			pNinio = pf.creaPaciente("ninio", new Domicilio("Mar del plata", "San Luis", 123), "46029714", "Juan", "Gomez", "---");
			pJoven = pf.creaPaciente("joven", new Domicilio("Mar del plata", "San martin", 1243), "15326614", "Maria", "Gimenez", "2233522352");
			pMayor = pf.creaPaciente("mayor", new Domicilio("Mar del plata", "Luro", 1123), "1250631", "Martin", "Prat", "22363026521");
			
			System.out.println(med1.toString());
			System.out.println(med2.toString());
			System.out.println(pJoven.toString());
			System.out.println(pNinio.toString());
			System.out.println(pMayor.toString());
			
			clin.registraMedico(med1);
			clin.registraMedico(med2);
			
			clin.registraPaciente(pNinio);
			clin.registraPaciente(pJoven);
			
			clin.ingresaPaciente(pJoven, LocalDate.now());
			clin.ingresaPaciente(pNinio, LocalDate.now());
			
			clin.atiendePaciente(med1, pNinio);
			clin.atiendePaciente(med2, pNinio);
			
			Factura f1 = null, f2 = null;
			f1 = clin.egresaPaciente(pNinio);
			System.out.println(f1);	
			
			f2 = clin.egresaPaciente(pMayor);
			System.out.println(f2);
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());		
		}
		
	}

}
