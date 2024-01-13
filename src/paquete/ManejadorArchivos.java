package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManejadorArchivos {
	
	public ManejadorArchivos() {
		
	}
	
	public ArrayList<Reserva> leerXml(String rutaArchivo) {
		ArrayList<Reserva> reservasLeidas = new ArrayList<>();
		
		FileReader fr;
		try {
			fr = new FileReader(rutaArchivo);
			BufferedReader br = new BufferedReader(fr);
			String lineaActual;
			
			ArrayList<String> lineasReservaActual = new ArrayList<>();
			
			while ((lineaActual = br.readLine()) != null && !lineaActual.equals("</reservas>")) {
				
				while ((lineaActual = br.readLine()) != null && !lineaActual.trim().equals("</reserva>")) {
					lineasReservaActual.add(lineaActual);
				}
				reservasLeidas.add(leerReservaXml(lineasReservaActual));
				lineasReservaActual = new ArrayList<>();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			
		}
		return reservasLeidas;
	}
	
	private Reserva leerReservaXml(ArrayList<String> lineasReserva) {
		
		Reserva reservaLeida = new Reserva(leerElementoXml("nombre", lineasReserva), leerElementoXml("telefono", lineasReserva), leerElementoXml("fechaEvento", lineasReserva)
				, leerElementoXml("tipo", lineasReserva), leerElementoXml("asistentes", lineasReserva), leerElementoXml("tipoCocina", lineasReserva)
				, leerElementoXml("tipoMesa", lineasReserva), leerElementoXml("comensalesMesa", lineasReserva), leerElementoXml("numeroJornadas", lineasReserva)
				, leerElementoXml("requiereHabitaciones", lineasReserva));
		return reservaLeida;
	}
	
	private String leerElementoXml(String elementoBuscar, ArrayList<String> lineasReserva) {
		String elementoLeido = null;
		
		for (String linea: lineasReserva) {
			if (linea.contains("<"+elementoBuscar+">")) {
				elementoLeido = linea.replace("<"+elementoBuscar+">", "");
				elementoLeido = elementoLeido.replace("</"+elementoBuscar+">", "");
				elementoLeido = elementoLeido.trim();
			}
		}
		return elementoLeido;
	}
	
	public void escribirXml(String rutaArchivo, ArrayList<Reserva> listaReservas) {
		try{
		    BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
		    bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n");
		    bw.write("<reservas> \n");
		    for (Reserva reserva: listaReservas) {
		    	escribirReservaXml(reserva, bw);
		    }
		    bw.write("</reservas> \n");
		    bw.close();
		} catch (IOException ioe){
		    ioe.printStackTrace();
		}
	}
	
	private void escribirReservaXml(Reserva reserva, BufferedWriter bw) {
		try {
			bw.write("\t <reserva> \n");
			escribirElementoReservaXml(reserva.getNombre(), "nombre", bw);
			escribirElementoReservaXml(reserva.getTelefono(), "telefono", bw);
			escribirElementoReservaXml(reserva.getFechaEvento(), "fechaEvento", bw);
			escribirElementoReservaXml(reserva.getTipo(), "tipo", bw);
			escribirElementoReservaXml(reserva.getAsistentes(), "asistentes", bw);
			escribirElementoReservaXml(reserva.getTipoCocina(), "tipoCocina", bw);
			escribirElementoReservaXml(reserva.getTipoMesa(), "tipoMesa", bw);
			escribirElementoReservaXml(reserva.getComensalesMesa(), "comensalesMesa", bw);
			escribirElementoReservaXml(reserva.getNumeroJornadas(), "numeroJornadas", bw);
			escribirElementoReservaXml(reserva.getRequiereHabitaciones(), "requiereHabitaciones", bw);
			bw.write("\t </reserva> \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void escribirElementoReservaXml(String elemento, String etiqueta, BufferedWriter bw) {
		if (elemento != null) {
			try {
				bw.write("\t \t <"+etiqueta+">"+elemento+"</"+etiqueta+"> \n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Reserva> leerJson(String rutaArchivo) {
		ArrayList<Reserva> reservasLeidas = new ArrayList<>();
		
		FileReader fr;
		
		try {
			fr = new FileReader(rutaArchivo);
			BufferedReader br = new BufferedReader(fr);
			String lineaActual;
			
			ArrayList<String> lineasReservaActual = new ArrayList<>();
			
			while ((lineaActual = br.readLine()) != null && !lineaActual.trim().equals("]")) {
				
				while ((lineaActual = br.readLine()) != null && !lineaActual.trim().startsWith("}")) {
					lineasReservaActual.add(lineaActual);
				}
				reservasLeidas.add(leerReservaJson(lineasReservaActual));
				lineasReservaActual = new ArrayList<>();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
		
		}
		return reservasLeidas;
	}
	
	private Reserva leerReservaJson(ArrayList<String> lineasReserva) {
		Reserva reservaLeida = new Reserva(leerElementoJson("nombre", lineasReserva), leerElementoJson("telefono", lineasReserva), leerElementoJson("fechaEvento", lineasReserva)
				, leerElementoJson("tipo", lineasReserva), leerElementoJson("asistentes", lineasReserva), leerElementoJson("tipoCocina", lineasReserva)
				, leerElementoJson("tipoMesa", lineasReserva), leerElementoJson("comensalesMesa", lineasReserva), leerElementoJson("numeroJornadas", lineasReserva)
				, leerElementoJson("requiereHabitaciones", lineasReserva));
		return reservaLeida;
	}
	
	private String leerElementoJson(String elementoBuscar, ArrayList<String> lineasReserva) {
		String elementoLeido = null;
		
		for (String linea: lineasReserva) {
			if (linea.contains("\""+elementoBuscar+"\":")) {
				elementoLeido = linea.replace("\""+elementoBuscar+"\":", "");
				elementoLeido = elementoLeido.replace("\"", "");
				elementoLeido = elementoLeido.replace(",", "");
				elementoLeido = elementoLeido.trim();
			}
		}
		return elementoLeido;
	}
	
	public void escribirJson(String rutaArchivo, ArrayList<Reserva> listaReservas) {
		
		try{
		    BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
		    bw.write("{\n");
		    bw.write("\t\"reservas\": {\n");
		    bw.write("\t\t\"reserva\": [\n");
		    
		    for (Reserva reserva: listaReservas) {
		    	bw.write("\t\t\t{");
		    	escribirReservaJson(reserva, bw);
		    	if (listaReservas.indexOf(reserva) + 1 == listaReservas.size()) {
		    		bw.write("\n\t\t\t}\n");
		    	} else {
		    		bw.write("\n\t\t\t},\n");
		    	}
		    }
		    
		    bw.write("\t\t]\n");
		    bw.write("\t}\n");
		    bw.write("}");
		    bw.close();
		    
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	private void escribirReservaJson(Reserva reserva, BufferedWriter bw) {
		
		boolean primerElementoEscrito = false;
		
		primerElementoEscrito = escribirElementoReservaJson(reserva.getNombre(), "nombre", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getTelefono(), "telefono", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getFechaEvento(), "fechaEvento", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getTipo(), "tipo", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getAsistentes(), "asistentes", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getTipoCocina(), "tipoCocina", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getTipoMesa(), "tipoMesa", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getComensalesMesa(), "comensalesMesa", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getNumeroJornadas(), "numeroJornadas", bw, primerElementoEscrito);
		primerElementoEscrito = escribirElementoReservaJson(reserva.getRequiereHabitaciones(), "requiereHabitaciones", bw, primerElementoEscrito);
	}
	
	private boolean escribirElementoReservaJson(String elemento, String etiqueta, BufferedWriter bw, boolean primerElementoEscrito) {
		
		boolean elementoEscrito = false;
		
		if (elemento != null) {
			if (primerElementoEscrito) {
				try {
					bw.write(",\n\t\t\t\t\""+etiqueta+"\": \""+elemento+"\"");
					elementoEscrito = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					bw.write("\n\t\t\t\t\""+etiqueta+"\": \""+elemento+"\"");
					elementoEscrito = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return elementoEscrito; 
	}
	
	public void escribirSql(ArrayList<Reserva> listaReservas) {
		ConexionSQL manejadorSQL = new ConexionSQL();
		
		for (Reserva reserva: listaReservas) {
			manejadorSQL.ejecutarSentencia(obtenerSentenciaSQL(reserva));
		}
	}
	
	private String obtenerSentenciaSQL(Reserva reserva) {
		return "INSERT INTO reservas VALUES ("+comprobarNull(reserva.getNombre())+", "+comprobarNull(reserva.getTelefono())+", "+comprobarNull(reserva.getFechaEvento())+", "
				+comprobarNull(reserva.getTipo())+", "+comprobarNull(reserva.getAsistentes())+", "+comprobarNull(reserva.getTipoCocina())+", "+comprobarNull(reserva.getTipoMesa())+", "
				+comprobarNull(reserva.getComensalesMesa())+", "+comprobarNull(reserva.getNumeroJornadas())+", "+comprobarNull(reserva.getRequiereHabitaciones())+")";
	}
	
	private String comprobarNull(String valor) {
		String valorNuevo;
		if (!(valor ==(null))) {
			valorNuevo = "'"+valor+"'";
		} else {
			valorNuevo = valor;
		}
		return valorNuevo;
	}
	
	public ArrayList<Reserva> leerSql() {
		
		ConexionSQL manejadorSQL = new ConexionSQL();
		
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		
		ArrayList<ArrayList<String>> listaTuplas = manejadorSQL.ejecutarSentencia("SELECT * FROM reservas");
		
		for (ArrayList<String> tupla: listaTuplas) {
			listaReservas.add(new Reserva(tupla.get(0).trim(),tupla.get(1).trim(),tupla.get(2).trim(),tupla.get(3).trim(),tupla.get(4).trim(),tupla.get(5).trim(),tupla.get(6).trim(),tupla.get(7).trim(),tupla.get(8).trim(),tupla.get(9).trim()));
		}
		return listaReservas;
	}
	
}
