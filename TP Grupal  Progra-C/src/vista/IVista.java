package vista;

import java.util.Observer;

import persistencia.AsociadoDTO;

public interface IVista extends Observer{
	public AsociadoDTO getNewAsociado();
	public String getDniAEliminar();
	
}
