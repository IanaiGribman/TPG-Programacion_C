package vista;

import java.util.Observer;

import persistencia.AsociadoDTO;

public interface IVista extends Observer{
	public static String SIMULACION = "SIMULACION";
	public static String GESTION = "GESTION";
	
	public AsociadoDTO getNewAsociado();
	public String getDniAEliminar();
	public void mostrarGestion();
	public void mostrarSimulacion();
	
}
