package conexionJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Insertar {
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Pablo Suarez Romero");
		System.out.println("Insertar\n");
		
		System.out.println("Quieres mostrar la consulta general: 1 SI/0 NO: ");

		int x = in.nextInt();
		if (x == 1) {
			Consulta.consultas();
		}
		
		
		
		String codHotel;
		String numHabitacion;
		int capacidad;
		int preciodia;
		int activa;
		
		int error = 0;

		System.out.print("CodHotel: ");
		codHotel = in.next();

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
			insertar(codHotel, numHabitacion, capacidad, preciodia, activa, args);
			in.close();
		}else {
			Checkers.espera(5);
			Main.goMenu(1,args);
		}
		
		
		
	}
	
	public static void insertar(String codHotel,String numHabitacion,int capacidad,int preciodia,int activa, String[] args){
		try {
			
			Connection db = ConectionDB.conectionDB();

			PreparedStatement sql = db.prepareStatement(
					"INSERT INTO habitaciones (codHotel, numHabitacion, capacidad, preciodia, activa) VALUES (?,?,?,?,?)");
			sql.setString(1, codHotel);
			sql.setString(2, numHabitacion);
			sql.setInt(3, capacidad);
			sql.setInt(4, preciodia);
			sql.setInt(5, activa);

			int retorno = sql.executeUpdate();
			if (retorno > 0)
				System.out.println("\nInsertado correctamente \n");
			

			db.commit();
			db.close();
			
			Consulta.consultaUno(codHotel, numHabitacion);
			
			Main.goMenu(1, args);

		} catch (SQLException e) {
			System.out.println("No se ha podido insertar");
		}
	}

}
