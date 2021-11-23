package aed.hibernate.Query;

import java.util.Scanner;

import org.hibernate.Session;

import aed.hibernate.Clases.estancias;
import aed.hibernate.Clases.habitaciones;
import aed.hibernate.Clases.habitacionesobservaciones;
import aed.hibernate.Main.Menu;
//PABLO SUAREZ ROMERO

public class Eliminar {

	
	public static void eliminar_entrada(Session sesion) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n// Pablo Suarez Romero //");
		System.out.println("// Eliminar Habitacion //");
		
		System.out.println("codhabitacion: ");
		int codhabitacion = in.nextInt();
		
		boolean hay_habitaciones = Chekers.hay_habitacion(sesion, codhabitacion);

		if(hay_habitaciones == true ) {
			System.out.println("Vas a eliminar...");
			Consultas.consulta_habitacion(sesion, codhabitacion);
			Chekers.espera(2);
			System.out.println("1 para continuar con la eliminacion: ");
			if(in.nextInt() == 1) {
				eliminar_habitacion(sesion, codhabitacion);
			}else {
				System.out.println("Volviendo al menu");
				Menu.Menu_entrada(sesion);
			}
		}else {
			System.out.println("No existe ninguna habitacion con ese codigo");
			Menu.Menu_entrada(sesion);
		}
		
		
	}
	
	public static void eliminar_habitacion(Session sesion, int codhabitacion) {


		try {
			habitaciones p1 = sesion.get(habitaciones.class, codhabitacion);
			sesion.delete(p1);
			sesion.getTransaction().commit();
		} catch (Exception e) {
			System.err.println("Error al eliminar habitacion");
		}
	}
	public static void eliminar_observacion(Session sesion, int codhabitacion) {
		try {
			habitacionesobservaciones p1 = sesion.get(habitacionesobservaciones.class, codhabitacion);
			sesion.delete(p1);
			sesion.getTransaction().commit();
		} catch (Exception e) {
			System.err.println("Error al eliminar observacion");
		}
	}
	public static void eliminar_estancias(Session sesion, int codhabitacion) {
		try {
			estancias p1 = sesion.get(estancias.class, codhabitacion);
			sesion.delete(p1);
			sesion.getTransaction().commit();
		} catch (Exception e) {
			System.err.println("Error al eliminar estancias");
		}
	}

}
