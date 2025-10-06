package modelo.reporte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.IMedico;

/**
 * Clase que contiene a los reportes de cada medico, 
 * agregando consultas para ellos y obteniendolos cuando son solicitados
 * Inv: reportes != null && el medico debe tener al menos una consulta registrada para tener un reporte
 */
public class ModuloReportes {
	private HashMap<String, ReporteMedico> reportes;
	
	public ModuloReportes() {
		reportes = new HashMap<String, ReporteMedico>();
	}
	
	/**
	 * Agrega una consulta para cierto medico, 
	 * si el medico no estaba registrado se crea un nuevo lugar para este con un reporte con esta unica consulta 
	 * Pre: medico != null && consulta != null
	 * @param medico 
	 * @param consulta
	 */
	public void agregarConsulta(IMedico medico, Consulta consulta) {
		if(!this.reportes.containsKey(medico.getDni()))
			this.reportes.put(medico.getDni(), new ReporteMedico(medico.getNombre()));
		this.reportes.get(medico.getDni()).agregarConsulta(consulta);
	}
	
	
	/**
	 * Agrega la lista de medicos consultados de un paciente al hashmap de reportes
	 * pre: medicosComsultados != null && fecha != null && nombrePaciente != null
	 * @param medicosConsultados
	 * @param fecha
	 * @param nombrePaciente
	 */
	public void agregarConsultas(ArrayList<IMedico> medicosConsultados, LocalDate fecha, String nombrePaciente)
	{
		Consulta nuevaConsulta;
		for (IMedico medico: medicosConsultados)
		{
			nuevaConsulta = new Consulta(nombrePaciente, fecha, medico.getHonorario());
			this.agregarConsulta(medico, nuevaConsulta);
		}
	}
	
	/**
	 * Obtiene el reporte de las consultas registradas para un medico desde una fecha hasta otra
	 * Pre: medico != null && desde != null && hasta != null
	 * @param medico
	 * @param desde
	 * @param hasta
	 * @return String con el reporte si el medico tiene consultas, sino un mensaje propio de que no posee consultas
	 */
	public String getReporte(IMedico medico, LocalDate desde, LocalDate hasta) {
		String retorno = "";
		if(!this.reportes.containsKey(medico.getDni()))
			retorno = "El medico " + medico.getNombre() + " no tiene consultas registradas";
		else
			retorno = this.reportes.get(medico.getDni()).getReporte(desde, hasta);
		return retorno;
	}
}
