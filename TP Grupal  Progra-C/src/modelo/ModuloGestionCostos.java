package modelo;

import modelo.excepciones.HabitacionInvalidaException;
import modelo.habitaciones.Habitacion;
import modelo.habitaciones.HabitacionCompartida;
import modelo.habitaciones.HabitacionIntensiva;
import modelo.habitaciones.HabitacionPrivada;
import modelo.medicos.Medico;

public class ModuloGestionCostos {

	public void setCostoAsignacionHabitacion(double costo) {
		Habitacion.setCostoAsignacion(costo);
	}
	public void setCostoHabitacion(String tipoHabitacion, double costo) throws HabitacionInvalidaException
	{
		switch (tipoHabitacion.toLowerCase()) {
		case "privada":
			HabitacionPrivada.setCostoHabitacionPrivada(costo);
			break;
		case "intensiva":
			HabitacionIntensiva.setCostoHabitacionIntensiva(costo);
			break;
		case "compartida":
			HabitacionCompartida.setCostoHabitacionCompartida(costo);
			break;
		default:
			throw new HabitacionInvalidaException(tipoHabitacion);
		}
		
	}
	public void setHonorarioBasicoMedico(double honorario) {
		Medico.setHonorarioBasico(honorario);		
	}

}
