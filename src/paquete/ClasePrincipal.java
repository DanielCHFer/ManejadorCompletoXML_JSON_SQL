package paquete;

import java.util.ArrayList;
import java.util.Scanner;

public class ClasePrincipal {

	public static void main(String[] args) {
		
		ManejadorArchivos manejador = new ManejadorArchivos();
		ArrayList<Reserva> reservasLeidas = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el tipo de archivo del que desea leer las reservas");
		System.out.println("1 - XML");
		System.out.println("2 - JSON");
		System.out.println("3 - SQL");
		System.out.print("> ");
		
		String tipoArchivo = sc.nextLine();
		
		String nombreArchivo = null;
		
		if (!tipoArchivo.equals("3")) {
			System.out.println("Introduzca la ruta del archivo a leer");
			System.out.print("> ");
			nombreArchivo = sc.nextLine();
		}
		
		switch (tipoArchivo) {
		case "1": 
			reservasLeidas = manejador.leerXml(nombreArchivo);
			break;
		case "2":
			reservasLeidas = manejador.leerJson(nombreArchivo);
			break;
		case "3":
			reservasLeidas = manejador.leerSql();
			break;
		default:
			System.out.println("Opción no válida");
		}
		
		tipoArchivo = "";
		nombreArchivo = null;
		
		System.out.println("Introduzca el tipo de archivo en el que desea guardar las reservas");
		System.out.println("1 - XML");
		System.out.println("2 - JSON");
		System.out.println("3 - SQL");
		System.out.print("> ");
		
		tipoArchivo = sc.nextLine();
		
		if (!tipoArchivo.equals("3")) {
			System.out.println("Introduzca el nombre del archivo resultante");
			System.out.print("> ");
			nombreArchivo = sc.nextLine();
		}
		
		switch (tipoArchivo) {
		case "1": 
			manejador.escribirXml(nombreArchivo, reservasLeidas);
			break;
		case "2":
			manejador.escribirJson(nombreArchivo, reservasLeidas);
			break;
		case "3":
			manejador.escribirSql(reservasLeidas);
			break;
		default:
			System.out.println("Opción no válida");
		}
	}
}
