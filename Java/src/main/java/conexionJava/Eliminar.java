package conexionJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Eliminar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Pablo Suarez Romero");
		System.out.println("Eliminar\n");
		
		System.out.println("Quieres mostrar la consulta general: 1 SI/0 NO: ");

		int x = in.nextInt();
		if (x == 1) {
			Consulta.consultas();
		}
		

		String codHotel;
		String numHabitacion;

		int error = 0;

		System.out.print("CodHotel: ");
		codHotel = in.next();

		System.out.print("numHabitacion: ");
		numHabitacion = in.next();

		if (Checkers.checkCodHotel(codHotel) == false) {
			error++;
			System.out.println("\nEl codHotel no existe\n");
		}

		if (Checkers.checkNumHabitacion(numHabitacion) == false) {
			error++;
			System.out.println("\nLa habitacion no existe\n");
		}

		if (error == 0) {
			eliminar(codHotel, numHabitacion, args);
			in.close();
		} else {
			Checkers.espera(2);
			Main.goMenu(1, args);
		}
	}

	private static void eliminar(String codHotel, String numHabitacion, String[] args) {
		try {

			Connection db = ConectionDB.conectionDB();
			PreparedStatement sql;
			Scanner in = new Scanner(System.in);
			int salida = 0;

			System.out.println("Vas a eliminar:");
			Consulta.consultaUno(codHotel, numHabitacion);
			System.out.println("1 para continuar: ");
			if (in.nextInt() == 1) {
				if (Checkers.checkHabitacionEstancia(codHotel, numHabitacion) == true) {
					System.out.println("Quieres eliminar las estancias?");
					System.out.println("1 si, 0 no");
					System.out.println("Numero: ");
					salida = in.nextInt();
					switch (salida) {
					case 1:
						System.out.println("Eliminando estancias");
						eliminarEstancia(codHotel, numHabitacion);
						System.out.println("Eliminando habitacion");
						eliminarHabitacion(codHotel, numHabitacion);
						System.out.println("Eliminado");
						break;

					default:
						break;
					}

				} else {
					System.out.println("Eliminando habitacion");
					eliminarHabitacion(codHotel, numHabitacion);
					System.out.println("Eliminado");

				}
				Checkers.espera(5);
			}
			Main.goMenu(1, args);
		} catch (Exception e) {
			System.out.println("Error inesperado al eliminar");
		}
	}

	public static void eliminarEstancia(String codHotel, String numHabitacion) {
		try {
			Connection db = ConectionDB.conectionDB();
			PreparedStatement sql;

			Consulta.consultaUno(codHotel, numHabitacion);
			sql = db.prepareStatement("DELETE FROM estancias WHERE numHabitacion = ?");
			sql.setString(1, numHabitacion);
			sql.executeUpdate();

			db.commit();
			db.close();
		} catch (SQLException e) {
			System.out.println("Error inesperado al eliminar Estancia");
		}
	}

	public static void eliminarHabitacion(String codHotel, String numHabitacion) {
		try {
			Connection db = ConectionDB.conectionDB();
			PreparedStatement sql;

			sql = db.prepareStatement("DELETE FROM habitaciones WHERE codHotel = ? AND numHabitacion = ?");
			sql.setString(1, codHotel);
			sql.setString(2, numHabitacion);
			sql.executeUpdate();

			db.commit();
			db.close();
		} catch (SQLException e) {
			System.out.println("Error inesperado al habitacion");
		}
	}

}
