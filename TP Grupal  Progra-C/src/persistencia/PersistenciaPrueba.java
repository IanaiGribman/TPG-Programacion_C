package persistencia;

import java.sql.SQLException;

import persistencia.excepciones.NoEliminadoException;
import persistencia.excepciones.SinConexionException;

public class PersistenciaPrueba {

	public static void main(String[] args) {
		String direccionArchivo = "src/persistencia/BaseDeDatosConfig.xml";
		ParametrosBaseDeDatos parametros = ManagerXMLConfig.leerParametrosXML(direccionArchivo);
		BaseDeDatosDAO bd = new BaseDeDatosDAO(parametros);
		
		try {
			bd.abrirConexion();
			System.out.println("Se ha conectado con la base de datos");
			//bd.crearTablaAsociados();
			int base = 1;
			int cant = 10;
			
			//for(int i = 0; i < cant; i++)
			//	bd.eliminarAsociado(String.valueOf(i+base));
			
			for(AsociadoDTO asociado : bd.leerAsociados())
				System.out.println(asociado);
			
			System.out.println("--------------------");
			
			for(int i = 0; i < cant; i++)
				bd.agregarAsociado(new AsociadoDTO("ahre loco era la tabla", String.valueOf(i+base)));
			
			for(AsociadoDTO asociado : bd.leerAsociados())
				System.out.println(asociado);
			
			bd.reiniciarTablaAsociados();
			
			System.out.println("--------------------reiniciado :");
			
			for(AsociadoDTO asociado : bd.leerAsociados())
				System.out.println(asociado);
			
			bd.cerrarConexion();
			System.out.println("Se ha desconectado de la base de datos");
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SinConexionException e) {
			System.out.println(e);
		}// catch (NoEliminadoException e) {
			
		//}
	}

}
