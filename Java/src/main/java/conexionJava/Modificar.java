package conexionJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Modificar {

	private static Connection db = ConectionDB.conectionDB();

	public static void main(String[] args) {
		modificarEntradaHotel(args);
		
	}

	public static void modificarEntradaHotel(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Pablo Suarez Romero");
		System.out.println("Modificar\n");

		int error = 0;

		System.out.print("CodHotel: ");
		String codHotel = in.nextLine();

		System.out.print("numHabitacion: ");
		String numHabitacion = in.next();

		if (Checkers.checkCodHotel(codHotel) == false) {
			error++;
			System.out.println("\nEl codHotel no existe\n");
		}

		if (Checkers.checkNumHabitacion(numHabitacion) == false) {
			error++;
			System.out.println("\nLa habitacion no existe\n");
		}

		if (error == 0) {
			modificarEntradaModificar(codHotel, numHabitacion, args);
			
		} else {
			Checkers.espera(3);
			Main.goMenu(1, args);
		}
	}

	public static void modificarEntradaModificar(String codHotel, String numHabitacion, String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("\nVas a modificar: ");
		Consulta.consultaUno(codHotel, numHabitacion);
		int entrada = 0;

		
		System.out.println("Actualizar capacidad? 1 SI / 0 NO");
		System.out.println("Numero: ");
		entrada = in.nextInt();
		
		if (entrada == 1) {
			System.out.print("capacidad: ");
			int capacidad = in.nextInt();
			modificarCapacidad(codHotel, numHabitacion, capacidad, args);
		}
		entrada = 0;
		
		System.out.println("Actualizar preciodia? 1 SI / 0 NO");
		System.out.println("Numero: ");
		entrada = in.nextInt();
		
		if (entrada == 1) {
			System.out.print("preciodia: ");
			int preciodia = in.nextInt();
			modificarPrecioDia(codHotel, numHabitacion, preciodia, args);
		}
		
		entrada = 0;
		
		System.out.println("Actualizar activa? 1 SI / 0 NO");
		System.out.println("Numero: ");
		entrada = in.nextInt();
		
		if (entrada == 1) {
			System.out.print("activa: ");
			int activa = in.nextInt();
			if (Checkers.checkActiva(activa) == false) {
				System.out.println("\nActiva solo puede ser 0 o 1\n");
			}else {
				modificarActiva(codHotel, numHabitacion, activa, args);
			}
		}

		Consulta.consultaUno(codHotel, numHabitacion);
		
		Main.goMenu(1, args);


	}

	
	public static void modificarCapacidad(String codHotel, String numHabitacion, int capacidad, String[] args){
		try {
			PreparedStatement sql;

			sql = db.prepareStatement("UPDATE habitaciones SET capacidad =? WHERE codHotel = ? AND numHabitacion = ?");
			
			sql.setInt(1, capacidad);
			sql.setString(2, codHotel);
			sql.setString(3, numHabitacion);

			int retorno = sql.executeUpdate();
			if (retorno > 0)
				System.out.println("Actualizado correctamente");

			db.commit();
			db.close();

		} catch (SQLException e) {
			System.out.println("Error insperado mientras se actualizaba");
		}
	}
	public static void modificarPrecioDia(String codHotel, String numHabitacion, int preciodia, String[] args){
		try {
			PreparedStatement sql;

			sql = db.prepareStatement("UPDATE habitaciones SET preciodia =? WHERE codHotel = ? " + "AND numHabitacion = ?");
			
			sql.setInt(1, preciodia);
			sql.setString(2, codHotel);
			sql.setString(3, numHabitacion);

			int retorno = sql.executeUpdate();
			if (retorno > 0)
				System.out.println("Actualizado correctamente");

			db.commit();
			db.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error insperado mientras se actualizaba");
		}
	}
	public static void modificarActiva(String codHotel, String numHabitacion, int activa, String[] args){
		try {
			PreparedStatement sql;

			sql = db.prepareStatement("UPDATE habitaciones SET activa =? WHERE codHotel = ? " + "AND numHabitacion = ?");
			
			sql.setInt(1, activa);
			sql.setString(2, codHotel);
			sql.setString(3, numHabitacion);

			int retorno = sql.executeUpdate();
			if (retorno > 0)
				System.out.println("Actualizado correctamente");

			db.commit();
			db.close();

		} catch (SQLException e) {
			System.out.println("Error insperado mientras se actualizaba");
		}
	}
	
}
