package aed.hibernate.Query;

import java.util.Scanner;

import org.hibernate.Session;

import aed.hibernate.Clases.habitaciones;
import aed.hibernate.Clases.hoteles;
import aed.hibernate.Clases.regimenes;
import aed.hibernate.Main.Menu;

public class Update {

	public static void update_habitacion_entrada(Session sesion) {
		Scanner in = new Scanner(System.in);

		System.out.println("\n// Pablo Suarez Romero //");
		System.out.println("// Actualizar Habitacion //");

		System.out.println("codhabitacion: ");
		int codhabitacion = in.nextInt();

		boolean hay_habitaciones = Chekers.hay_habitacion(sesion, codhabitacion);

		if (hay_habitaciones == true) {
			System.out.println("Vas a actualizar...");
			Consultas.consulta_habitacion(sesion, codhabitacion);
			Chekers.espera(2);
			System.out.println("1 para continuar con la actualizacion: ");
			if (in.nextInt() == 1) {
				update_habitacion_datos(sesion, codhabitacion);
			} else {
				System.out.println("Volviendo al menu");
				Menu.Menu_entrada(sesion);
			}
		} else {
			System.out.println("No existe ninguna habitacion con ese codigo");
			Menu.Menu_entrada(sesion);
		}

	}

	public static void update_habitacion_datos(Session sesion, int codhabitacion) {
		Scanner in = new Scanner(System.in);

		System.out.println("Actualizar codHotel? 1 para editar 0 para continuar: ");
		if (in.nextInt() == 1) {
			System.out.println("Introduce el nuevo valor para CodHotel: ");

			String codHotel = in.next();
			if (Chekers.hay_hotel(sesion, codHotel) == false) {
				System.err.println("El hotel no existe");
			}else {
				update_codHotel(sesion, codHotel, codhabitacion);
			}
		}

		System.out.println("Actualizar NumHabitacion? 1 para editar 0 para continuar: ");
		if (in.nextInt() == 1) {
			System.out.println("Introduce el nuevo valor para numHabitacion: ");
			String numHabitacion = in.next();
			update_numHabitacion(sesion, numHabitacion, codhabitacion);
		}

		System.out.println("Actualizar Capacidad? 1 para editar 0 para continuar: ");
		if (in.nextInt() == 1) {
			System.out.println("Introduce el nuevo valor para Capacidad: ");
			int capacidad = in.nextInt();
			update_capacidad(sesion, capacidad, codhabitacion);
		}

		System.out.println("Actualizar PrecioDia? 1 para editar 0 para continuar: ");
		if (in.nextInt() == 1) {
			System.out.println("Introduce el nuevo valor para PrecioDia: ");
			int preciodia = in.nextInt();
			update_preciodia(sesion, preciodia, codhabitacion);
		}

		System.out.println("Actualizar Activa? 1 para editar 0 para continuar: ");
		if (in.nextInt() == 1) {
			System.out.println("Introduce el nuevo valor para activa: ");
			int activa = in.nextInt();
			if (Chekers.checkActiva(activa) == true) {
				update_activa(sesion, activa, codhabitacion);
			} else {
				System.out.println("Activa solo puede ser o 0 o 1");
			}

		}
		
		Menu.Menu_entrada(sesion);

	}


	public static void update_codHotel(Session sesion, String codHotel, int codhabitacion) {
		sesion.beginTransaction();

		habitaciones p1 = sesion.get(habitaciones.class, codhabitacion);
		hoteles hotel = sesion.get(hoteles.class, codHotel);
		p1.setCodHotel(hotel);
		
		sesion.save(p1);
		sesion.getTransaction().commit();
		System.out.println("codHotel actualizado..");

	}
	public static void update_numHabitacion(Session sesion, String numHabitacion, int codhabitacion) {
		sesion.beginTransaction();

		habitaciones p1 = sesion.get(habitaciones.class, codhabitacion);
		p1.setNumHabitacion(numHabitacion);
		
		sesion.save(p1);
		sesion.getTransaction().commit();
		System.out.println("numHabitacion actualizado..");

	}
	public static void update_capacidad(Session sesion, int capacidad, int codhabitacion) {
		sesion.beginTransaction();

		habitaciones p1 = sesion.get(habitaciones.class, codhabitacion);
		p1.setCapacidad(capacidad);
		
		sesion.save(p1);
		sesion.getTransaction().commit();
		System.out.println("Capacidad actualizado..");

	}
	public static void update_preciodia(Session sesion, int preciodia, int codhabitacion) {
		sesion.beginTransaction();

		habitaciones p1 = sesion.get(habitaciones.class, codhabitacion);
		p1.setPreciodia(preciodia);
		
		sesion.save(p1);
		sesion.getTransaction().commit();
		System.out.println("Preciodia actualizado..");
	}
	public static void update_activa(Session sesion, int activa, int codhabitacion) {
		sesion.beginTransaction();

		habitaciones p1 = sesion.get(habitaciones.class, codhabitacion);
		p1.setActiva(activa);
		
		sesion.save(p1);
		sesion.getTransaction().commit();
		System.out.println("Activa actualizado..");
	}
}
