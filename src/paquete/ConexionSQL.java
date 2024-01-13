package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionSQL {

	public ConexionSQL() {
	}
	
	private Connection abrirConexion(String user, String password) {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String urlCon = "jdbc:mysql://localhost:3306/accesodatos";
			conexion = DriverManager.getConnection(urlCon, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}
	
	public ArrayList<ArrayList<String>> ejecutarSentencia(String sentencia) {
		Connection conexion = abrirConexion("root", "admin");
		boolean error = false;
		ResultSet resultado = null;
		
		ArrayList<ArrayList<String>> listaTuplas = new ArrayList<>();
		
		try {
			Statement stmt = conexion.createStatement();
			if (sentencia.toUpperCase().startsWith("SELECT")) {
				resultado = stmt.executeQuery(sentencia);
				ResultSetMetaData resultadoMetadata = resultado.getMetaData();
				int numeroColumnas = resultadoMetadata.getColumnCount();
				
				while (resultado.next()) {
				    ArrayList<String> tuplaActual = new ArrayList<>();
					
				    for (int i = 1; i <= numeroColumnas; i++) {
				    	tuplaActual.add(resultado.getString(i)+" ");
				    }
				    listaTuplas.add(tuplaActual);
				}
				stmt.close();
			} else {
				stmt.executeUpdate(sentencia);
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
		}
		cerrarConexion(conexion);
		return listaTuplas;
	}
	
	private void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
