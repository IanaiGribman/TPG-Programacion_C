package vista;

import java.util.Collection;
import java.util.Observer;

import persistencia.AsociadoDTO;

public interface IVista extends Observer{
	public static final String SIMULACION = "SIMULACION";
	public static final String REGISTRAR = "REGISTRAR";
	public static final String ELIMINAR = "ELIMINAR";
	public static final String CARGAR = "CARGAR";
	public static final String GUARDAR = "GUARDAR";
	public static final String INICIALIZAR = "INICIALIZAR";
	
	public static final String MANTENIMIENTO = "MANTENIMIENTO";
	public static final String GESTION = "GESTION";
	
	public AsociadoDTO getNewAsociado();
	public String getDniAEliminar();
	public void mostrarGestion();
	public void mostrarSimulacion();
	public void actualizarTablaAsociados(Collection<AsociadoDTO> asociados);
	
}
