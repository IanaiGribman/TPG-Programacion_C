package controladores;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.AsociadoDTO;

/**
 * Clase para la carga y lectura del archivo xml que contiene datos de asociados
 * para la inicializacion de la BD
 */
public class ManagerXMLInicializacion {	
	static public Collection<AsociadoDTO> leerAsociadosInicializacionXML(String direccionArchivoXMLConfig) {
		Collection<AsociadoDTO> asociados = null;
		try {
			XMLDecoder decoder = new XMLDecoder(
					new BufferedInputStream(new FileInputStream(direccionArchivoXMLConfig)));
			asociados = (ArrayList<AsociadoDTO>) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);// debug
			e.printStackTrace();
		}
		return asociados;
	}
}
