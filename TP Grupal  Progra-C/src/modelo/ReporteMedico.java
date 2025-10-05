package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que representa un reporte medico, contiene una lista de consultas a
 * distintos pacientes realizadas por algun medico
 */
public class ReporteMedico {
	private String nombreMedico;
	private ArrayList<Consulta> consultas;

	/**
	 * Pre: nombreMedico != null && nombreMedico != ""
	 * @param nombreMedico
	 */
	public ReporteMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
		this.consultas = new ArrayList<Consulta>();
	}

	/**
	 * Agrega una consulta a la lista de consultas
	 * Pre: consulta != null
	 * @param consulta
	 */
	public void agregarConsulta(Consulta consulta) {
		this.consultas.add(consulta);
		this.ordenarConsultas();
	}

	/**
	 * Ordena consultas por fecha (cronologicamente)
	 */
	private void ordenarConsultas() {
		this.consultas.sort((a, b) -> {
			return a.getFecha().compareTo(b.getFecha());
		});
	}
	
	/**
	 * Devuelve el reporte correspondiente al medico desde cierta fecha hasta otra
	 * Pre: desde != null && hasta != null
	 * @param desde 
	 * @param hasta 
	 * @return String con el reporte
	 */
	public String getReporte(LocalDate desde, LocalDate hasta) {
		String retorno = "Reporte medico para " + this.nombreMedico + ":\n\n";
		double total = 0;
		int i = 0;
		
		while(i < this.consultas.size() && this.consultas.get(i).getFecha().compareTo(desde) < 0)
			i++;
		
		while(i < this.consultas.size() && this.consultas.get(i).getFecha().compareTo(hasta) <= 0) {
			retorno += "  " + this.consultas.get(i).toString() + "\n";
			total += this.consultas.get(i).getHonorario();
			i++;
		}
		
		retorno += "Total: " + total;
		
		return retorno;
	}
}
