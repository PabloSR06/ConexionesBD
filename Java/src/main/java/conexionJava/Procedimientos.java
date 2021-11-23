package conexionJava;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Procedimientos {

	public static void main(String[] args) {
		funcion1Entrada(args);
	}

	public static void procedimiento1Entrada(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("\nProcedimiento 1\n");

		System.out.print("nomHotel: ");
		String nomHotel = in.nextLine();

		if (Checkers.checkNomHotel(nomHotel) == true) {
			procedimiento1(nomHotel, args);
		} else {
			System.out.println("El nombre del hotel no existe");
			Checkers.espera(3);
			Main.goMenu(1, args);
		}
	}

	public static void procedimiento1(String nomHotel, String[] args) {
		try {
			Connection db = ConectionDB.conectionDB();
			CallableStatement consulta;
			ResultSet resultado;

			consulta = db.prepareCall("{ CALL EJ_01(?) }");
			consulta.setString(1, nomHotel);
			resultado = consulta.executeQuery();

			System.out.println("Comienzo");
			while (resultado.next()) {
				String numHabitacion = resultado.getString("numHabitacion");
				int capacidad = resultado.getInt("capacidad");
				int preciodia = resultado.getInt("preciodia");
				int activa = resultado.getInt("activa");

				System.out.println(String.format("numHabitacion: %s, capacidad: %s, preciodia: %d, activa: %d",
						numHabitacion, capacidad, preciodia, activa));
				System.out.println("----- # -----");

			}
			System.out.println("Fin");
			Checkers.espera(5);
			
			consulta.close();
			resultado.close();
			db.close();

			Main.goMenu(1, args);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error inesperado con procedimiento 1");
		}

	}

	
	public static void procedimiento2Entrada(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("\nProcedimiento 2\n");

		String codHotel;
		String numHabitacion;
		int capacidad;
		int preciodia;
		int activa;
		
		int error = 0;

		System.out.print("CodHotel: ");
		codHotel = in.nextLine();

		System.out.print("numHabitacion: ");
		numHabitacion = in.next();

		System.out.print("capacidad: ");
		capacidad = in.nextInt();

		System.out.print("preciodia: ");
		preciodia = in.nextInt();

		System.out.print("activa: ");
		activa = in.nextInt();
		
		if (Checkers.checkCodHotel(codHotel) == false) {
			error++;
			System.out.println("\nEl codHotel no existe\n");
		}
		
		if (Checkers.checkNumHabitacion(numHabitacion) == true) {
			error++;
			System.out.println("\nLa habitacion ya existe\n");
		}
		
		if (Checkers.checkActiva(activa) == false) {
			error++;
			System.out.println("\nActiva solo puede ser 0 o 1\n");
		}
		

		if(error == 0) {
			procedimiento2(codHotel, numHabitacion, capacidad, preciodia, activa, args);
		} else {
			Checkers.espera(3);
			Main.goMenu(1, args);
		}
	}
	
	public static void procedimiento2(String nomHotel, String numHabitacion, int capacidad, int preciodia, int activa, String[] args) {
		try {
			Connection db = ConectionDB.conectionDB();
			CallableStatement consulta;

			consulta = db.prepareCall("{Call EJ_02(?,?,?,?,?,?,?)}");
			consulta.setString(1, nomHotel);
			consulta.setString(2, numHabitacion);
			consulta.setInt(3, capacidad);
			consulta.setInt(4, capacidad);
			consulta.setInt(5, activa);
			
			consulta.registerOutParameter(6, Types.INTEGER);
			consulta.registerOutParameter(7, Types.INTEGER);
			
			consulta.execute();

			System.out.println("Comienzo");
			
			System.out.println("0 = hotel no existe | 1 = hotel existe: ////salida: " + consulta.getInt(6));
			System.out.println("0 = habitacion no insertada | 1 = insercion correcta: ////salida: " + consulta.getInt(7));
			
			System.out.println("Fin");
			Checkers.espera(5);
			consulta.close();
			db.close();

			Main.goMenu(1, args);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error inesperado con procedimiento 1");
		}

	}


	
	
	public static void procedimiento3Entrada(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("\nProcedimiento 3\n");

		System.out.print("nomHotel: ");
		String nomHotel = in.nextLine();

		System.out.print("Preciodia: ");
		int preciodia = in.nextInt();

		int error = 0;

		if (Checkers.checkNomHotel(nomHotel) == false) {
			error++;
			System.out.println("El nombre del hotel no existe");
		}

		if (error > 0) {
			Checkers.espera(3);
			Main.goMenu(1, args);
		} else {
			procedimiento3(nomHotel, preciodia, args);
		}
	}

	public static void procedimiento3(String nomHotel, int preciodia, String[] args) {
		try {
			Connection db = ConectionDB.conectionDB();
			CallableStatement consulta;

			consulta = db.prepareCall("{Call EJ_03(?,?,?,?)}");
			consulta.setString(1, nomHotel);
			consulta.setInt(2, preciodia);
			consulta.registerOutParameter(3, Types.INTEGER);
			consulta.registerOutParameter(4, Types.INTEGER);

			
			consulta.execute();

			System.out.println("Comienzo");

			System.out.println("Habitaciones totales: "  + consulta.getInt(3));
			System.out.println("Habitaciones disponibles: " + consulta.getInt(4));

			System.out.println("Fin");
			Checkers.espera(4);

			consulta.close();
			db.close();

			Main.goMenu(1, args);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
			System.out.println("Error inesperado con procedimiento 3");
		}

	}

	public static void funcion1Entrada(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("\nFuncion 1\n");
		
		System.out.println("Quieres mostrar la consulta de DNI: 1 SI/0 NO: ");
		int x = in.nextInt();
		if (x == 1) {
			Consulta.consultaDNI();
		}

		System.out.print("DNI: ");
		String coddni = in.next();

		int error = 0;

		if (Checkers.checkCodDni(coddni) == false) {
			error++;
			System.out.println("El dni del cliente no existe");
		}

		if (error > 0) {
			Checkers.espera(3);
			Main.goMenu(1, args);
		} else {
			funcion1(coddni, args);
		}
	}

	public static void funcion1(String coddni, String[] args) {
		try {
			Connection db = ConectionDB.conectionDB();
			CallableStatement consulta;
			
			if (ConectionDB.leerFichero()==1) {
				consulta = db.prepareCall("SELECT EJ_04(?)");
			}else {
				consulta = db.prepareCall("SELECT dbo.EJ_04(?)");
			}
			
			consulta.setString(1, coddni);
			
			ResultSet resultado = consulta.executeQuery();

			System.out.println("Comienzo");
			resultado.next();
			int salida = resultado.getInt(1);
			System.out.println(String.format("Suma total pagado: %s", salida));

			
			System.out.println("Fin");
			Checkers.espera(4);

			consulta.close();
			db.close();

			Main.goMenu(1, args);

		} catch (SQLException e) {
			System.out.println("Error inesperado con funcion 1");
		}

	}
}
