package conexionJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consulta {
	
	
	private static PreparedStatement sql;
	
	public static void main(String[] args) {
		System.out.println("Pablo Suarez Romero");
		System.out.println("Consultas\n");
		
		consultas();
		
		Main.goMenu(1,args);
	}
	
	public static void consultas() {
		try {
			Connection db = ConectionDB.conectionDB();
			Statement statement = db.createStatement();

			ResultSet resultado = statement.executeQuery(
					"SELECT * FROM habitaciones INNER JOIN hoteles ON habitaciones.codHotel = hoteles.codHotel");

			while (resultado.next()) {

				String codHotel = resultado.getString("codHotel");
				String nomHotel = resultado.getString("NomHotel");
				String numHabitacion = resultado.getString("numHabitacion");
				int capacidad = resultado.getInt("capacidad");
				int preciodia = resultado.getInt("preciodia");
				int activa = resultado.getInt("activa");

				System.out.println(String.format(
						"codHotel: %s, nomHotel: %s, numHabitacion: %s, capacidad: %d, preciodia: %d, activa: %d",
						codHotel, nomHotel, numHabitacion, capacidad, preciodia, activa));
				System.out.println("----- # -----");
			}
			Checkers.espera(5);
			

			resultado.close();
			statement.close();
			db.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public static void consultaUno(String codHotel, String numHabitacion){
		try {
			Connection db = ConectionDB.conectionDB();
			sql = db.prepareStatement("SELECT * FROM habitaciones WHERE codHotel = ? AND numHabitacion = ?");
			sql.setString(1, codHotel);
			sql.setString(2, numHabitacion);
			ResultSet resultado = sql.executeQuery();
			resultado.next();
			
			String codHotel1 = resultado.getString("codHotel");
			String numHabitacion1 = resultado.getString("numHabitacion");
			int capacidad1 = resultado.getInt("capacidad");
			int preciodia1 = resultado.getInt("preciodia");
			int activa1 = resultado.getInt("activa");
	
			System.out.println("----- # -----");
			System.out.println(String.format(
					"codHotel: %s, numHabitacion: %s, capacidad: %d, preciodia: %d, activa: %d",
					codHotel1,  numHabitacion1, capacidad1, preciodia1, activa1));
			System.out.println("----- # -----");
			
			Checkers.espera(2);
			
			
			//sql.close();
			db.close();
		} catch (SQLException e) {
			System.out.println("A ocurrido un error inesperado en la consulta unica");

		}
	}
	
	public static void consultaDNI(){
		try {
			Connection db = ConectionDB.conectionDB();
			sql = db.prepareStatement("SELECT * FROM clientes");

			ResultSet resultado = sql.executeQuery();

			while (resultado.next()) {
				String coddnionie = resultado.getString("coddnionie");
				String nombre = resultado.getString("nombre");
				String nacionalidad = resultado.getString("nacionalidad");
		
				System.out.println(String.format(
						"coddnionie: %s, nombre: %s, nacionalidad: %s",
						coddnionie,  nombre, nacionalidad));
				System.out.println("----- # -----");
			}

			Checkers.espera(4);

			//sql.close();
			db.close();
		} catch (SQLException e) {
			System.out.println("A ocurrido un error inesperado en la consulta DNI");
		}
	}
	

}
