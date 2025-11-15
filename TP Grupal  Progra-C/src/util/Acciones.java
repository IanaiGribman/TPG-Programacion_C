package util;

public class Acciones {
	//Action commands de la gestion
	public static final String SIMULACION = "SIMULACION";
	public static final String REGISTRAR = "REGISTRAR";
	public static final String ELIMINAR = "ELIMINAR";
	public static final String INICIALIZAR_CONSULTA = "INI_CONSULTA"; //pregunta si se quiere inicializar
	public static final String INICIALIZAR_CONFIRMADO = "INI_CONFIRMA"; //se confirma que se quiere inicializar
	
	//Action commands de la simulacion
	public static final String MANTENIMIENTO = "MANTENIMIENTO";
	public static final String FINALIZAR_SIMULACION = "FINALIZAR";
	public static final String VOLVER_A_GESTION = "VOLVER";
	
	public static final String ERROR = "ERROR";
	public static final String NUEVO_LLAMADO = "NUEVO_LLAMADO";
	public static final String QUITAR_LLAMADO_NUEVOS_LLAMADOS = "QUITAR_LLAMADO";
	public static final String ESTADO = "ESTADO";
	public static final String INFORMAR = "INFORMAR";
	public static final String AGREGAR_SIMULACION = "AGREGAR_SIMULACION";
	public static final String NO_HAY_HILOS = "NO_HAY_HILOS";
	
	
	public static final String PONER_ATENDIDOS = "PONER LISTA ATENDIDOS";//no se para que se usaria, se pone en la lista al crearse el thread
	public static final String QUITAR_SOLICITANTE_ACTIVO = "SACAR LISTA ATENDIDOS";
	public static final String OPERARIO_ATENDIDO = "OPERARIO_ATENDIDO";

}
