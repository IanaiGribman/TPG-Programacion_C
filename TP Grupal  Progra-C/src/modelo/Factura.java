package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.atenciones.Atencion;
import modelo.atenciones.MedicoHonorario;

/**
 * todos los datos de una factura son inmutables
 */
public class Factura {
	private ArrayList<ConsultaMedicaFactura> consultasMedicas;
	private LocalDate fechaIngreso, fechaEgreso;
	private String nombrePaciente;
	private String tipoHabitacion;
	private double costoHabitacion;
	private int nroFactura;
	private static int sigNum = 1;
	private int cantDias;

	/**
	 * @param medicosConsultados distinto de null y al menos un medico
	 * @param habitacion la habitacion puede ser null, de serlo el paciente no se interno
	 * @param nombrePaciente distinto de null
	 * @param fechaIngreso distinto de null
	 * @param fechaEgreso distindo de null 
	 */
	public Factura(Atencion atencion) 
	{
		this.fechaIngreso = atencion.getFechaIngreso();
		this.fechaEgreso = atencion.getFechaEgreso();
		this.cantDias = atencion.getCantDias();
		this.nombrePaciente = atencion.getPaciente().getNombre();
		if (atencion.fueInternado()) {
			this.tipoHabitacion = atencion.getHabitacion().getTipoHabitacion();
			this.costoHabitacion = atencion.getHabitacion().getCostoTotal(this.cantDias);
		}
		else {
			this.tipoHabitacion = null;
			this.costoHabitacion = 0;
		}
		this.consultasMedicas = new ArrayList<>();
		
		for (MedicoHonorario medHon : atencion.getMedicosConsultados()) 
		{
			this.consultasMedicas.add(new ConsultaMedicaFactura(medHon));
		}
		this.nroFactura = sigNum;
		sigNum++;
	}

	@Override
	public String toString() {
		String texto = "";
		double costoTotal = 0;
		texto = texto.concat("Nro Factura: " + this.nroFactura + "\n");
		texto = texto.concat("Nombre Paciente: " + this.nombrePaciente + "\n");
		texto = texto.concat("Fecha Ingreso: " + this.fechaIngreso.toString() + "\n");
		texto = texto.concat("Fecha Egreso: " + this.fechaEgreso.toString() + "\n");
		texto = texto.concat("Cantidad de dias: " + this.cantDias + "\n");
		if (this.tipoHabitacion != null) {
			costoTotal += this.costoHabitacion;
			texto = texto.concat("Habitacion tipo: " + this.tipoHabitacion + "         Costo:         $"
					+ String.format("%.2f", this.costoHabitacion)  + "\n");
		}
		for (ConsultaMedicaFactura consulta : this.consultasMedicas) {
			costoTotal += consulta.getHonorario();
			texto = texto.concat(consulta.toString() + "\n");
		}
		texto = texto.concat("                                         Total:       $" + String.format("%.2f",costoTotal));
		return texto;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public int getNroFactura() {
		return nroFactura;
	}

	public int getCantDias() {
		return cantDias;
	}

	public ArrayList<ConsultaMedicaFactura> getConsultasMedicas() {
		return consultasMedicas;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public double getCostoHabitacion() {
		return costoHabitacion;
	}
	
	
}
