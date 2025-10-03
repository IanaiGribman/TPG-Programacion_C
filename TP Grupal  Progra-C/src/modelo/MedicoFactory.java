package modelo;

import modelo.excepciones.MedicoInvalidoException;

/**
 * Clase que crea un medico con especialidad, posgrado y contrato de acuerdo a su decorator
 */
public class MedicoFactory {
	/**
	 * Crea un medico con especialidad, la base del decorator
	 * Arroja excepcion si la especialidad es invalida
	 * @param nombre
	 * @param domicilio
	 * @param telefono
	 * @param dni
	 * @param nroMatricula
	 * @param especialidad
	 * @return medico con especialidad (base del decorator)
	 */
	private IMedico crearMedicoEspecialidad(String nombre, Domicilio domicilio, String telefono, String dni, String nroMatricula,
			String especialidad) throws MedicoInvalidoException {
		IMedico retorno = null;
		switch (especialidad.toLowerCase()) {
		case "clinica":
			retorno = new MedicoClinico(nombre, domicilio, telefono, dni, nroMatricula);
			break;
		case "cirujia":
			retorno = new MedicoCirujano(nombre, domicilio, telefono, dni, nroMatricula);
			break;
		case "pediatria":
			retorno = new MedicoPediatra(nombre, domicilio, telefono, dni, nroMatricula);
			break;
		default:
			throw new MedicoInvalidoException("Especialidad invalida: " + especialidad);
		}

		return retorno;
	}
	
	
	/**
	 * Con un medico ya creado crea un decorator con este encapsulado que le agrega el posgrado
	 * @param medico (medico base)
	 * @param posgrado
	 * @return decorator con medico encapsulado con posgrado agregado
	 * @throws MedicoInvalidoException (cuando el posgrado es invalido)
	 */
	private IMedico crearMedicoPosgrado(IMedico medico, String posgrado) throws MedicoInvalidoException {
		IMedico retorno = null;
		
		switch(posgrado.toLowerCase()) {
		case "doctorado":
			retorno = new DecoratorDoctorado(medico);
			break;
		case "magister":
			retorno = new DecoratorMagister(medico);
			break;
		default:
			throw new MedicoInvalidoException("Posgrado invalido: " + posgrado);
		}
		
		return retorno;
	}
	
	/**
	 * Crea un decorator con un decorator con posgrado como encapsulado, agregandole un tipo de contrato
	 * @param medico (decorator con posgrado con medico encapsulado)
	 * @param contrato
	 * @return Un decorator con un decorator con posgrado como encapsulado, agregado el tipo de contrato
	 * @throws MedicoInvalidoException (cuando el contrato es invalido)
	 */
	private IMedico crearMedicoContrato(IMedico medico, String contrato) throws MedicoInvalidoException {
		IMedico retorno = null;
		
		switch(contrato.toLowerCase()) {
		case "residente":
			retorno = new DecoratorMagister(medico);
			break;
		case "permanente":
			retorno = new DecoratorPermanente(medico);
			break;
		default:
			throw new MedicoInvalidoException("Contrato invalido: " + contrato);
		}
		
		return retorno;
	}
	
	/**
	 * Crea un medico segun el decorator con especialidad, posgrado y contrato
	 * @param nombre != ""
	 * @param domicilio != ""
	 * @param telefono != ""
	 * @param dni != ""
	 * @param nroMatricula != ""
	 * @param especialidad
	 * @param posgrado
	 * @param contrato
	 * @throws MedicoInvalidoException (cuando la especialidad, el posgrado o el contrato son invalidos) 
	 */
	public IMedico crearMedico(String nombre, Domicilio domicilio, String telefono, String dni, String nroMatricula,
			String especialidad, String posgrado, String contrato) throws MedicoInvalidoException{
		IMedico medicoEspecialidad = crearMedicoEspecialidad(nombre, domicilio, telefono, dni, nroMatricula, especialidad);
		IMedico medicoposgrado = crearMedicoPosgrado(medicoEspecialidad, posgrado);
		IMedico medicoContrato = crearMedicoContrato(medicoposgrado, contrato);
		
		return medicoContrato;
	}
}
