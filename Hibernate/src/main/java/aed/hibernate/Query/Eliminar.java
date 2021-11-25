package aed.hibernate.Query;

import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.Session;

import aed.hibernate.Clases.HibernateUtil;
import aed.hibernate.Clases.estancias;
import aed.hibernate.Clases.habitaciones;
import aed.hibernate.Clases.habitacionesobservaciones;
import aed.hibernate.Main.Menu;
//PABLO SUAREZ ROMERO

public class Eliminar {

	public static void eliminar_entrada(Session sesion) {
		Scanner in = new Scanner(System.in);

		System.out.println("\n## Pablo Suarez Romero");
		System.out.println("## Eliminar Habitacion \n");

		System.out.println("CodHabitacion: ");
		int codhabitacion = in.nextInt();

		if (Chekers.hay_habitacion(sesion, codhabitacion) == true) {
			System.out.println("Vas a eliminar...");
			Consultas.consulta_habitacion(sesion, codhabitacion);
			Chekers.espera(2);
			System.out.println("1 para continuar con la eliminacion: ");
			if (in.nextInt() == 1) {

				if (Chekers.hay_estancias(sesion, codhabitacion) == true) {
					System.out.println("Hay estancias relacionadas con esta habitacion, quieres eliminarlas?");
					System.out.println("1 para continuar con la eliminacion: ");
					if (in.nextInt() == 1) {
						eliminar_estancias(sesion, codhabitacion);
					}
				}
				if (Chekers.hay_observacion(sesion, codhabitacion) == true) {
					System.out.println("Hay observaciones relacionadas con esta habitacion, quieres eliminarlas?");
					System.out.println("1 para continuar con la eliminacion: ");
					if (in.nextInt() == 1) {
						eliminar_observacion(sesion, codhabitacion);
					}
				}
				if (Chekers.hay_observacion(sesion, codhabitacion) == false
						&& Chekers.hay_estancias(sesion, codhabitacion) == false) {
					eliminar_habitacion(sesion, codhabitacion);
				} else {
					System.out.println("\n No se puede eliminar una habitacion con relaciones \n ");
				}

			} else {
				System.out.println("Volviendo al menu");
			}
		} else {
			System.out.println("No existe ninguna habitacion con ese codigo");
			
		}
		Menu.Menu_entrada(sesion);
		Chekers.espera(2);
	}

	public static void eliminar_habitacion(Session sesion, int codhabitacion) {

		try {

			sesion.beginTransaction();
			String sql = ("delete habitaciones hab where hab.codhabitacion = :codhabitacion");

			Query q = sesion.createQuery(sql);

			q.setParameter("codhabitacion", codhabitacion);
			q.executeUpdate();

			sesion.getTransaction().commit();

			System.out.println("\nEliminando habitacion\n");
		} catch (Exception e) {
			System.err.println("Error al eliminar habitacion");
		}
	}

	public static void eliminar_observacion(Session sesion, int codhabitacion) {
		try {
			sesion.beginTransaction();
			String sql = ("delete habitacionesobservaciones obs where obs.codhabitacionX = :codhabitacion");

			Query q = sesion.createQuery(sql);

			habitaciones hab = new habitaciones();
			hab = (habitaciones) sesion.get(habitaciones.class, codhabitacion);

			q.setParameter("codhabitacion", hab);
			q.executeUpdate();

			sesion.getTransaction().commit();

			System.out.println("\nEliminando observacion\n");
		} catch (Exception e) {
			System.err.println("Error al eliminar observacion");
		}
	}

	public static void eliminar_estancias(Session sesion, int codhabitacion) {
		try {
			sesion.beginTransaction();
			String sql = ("delete estancias est where est.codhabitacion = :codhabitacion");

			Query q = sesion.createQuery(sql);

			habitaciones hab = new habitaciones();
			hab = (habitaciones) sesion.get(habitaciones.class, codhabitacion);
			
			q.setParameter("codhabitacion", hab);
			q.executeUpdate();

			sesion.getTransaction().commit();

			System.out.println("\nEliminando estancias\n");
		} catch (Exception e) {
			System.err.println("Error al eliminar estancias");
		}
	}

}
