package conexionJava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class prueba {

	public static void main(String[] args) {
		try {
			Connection db = ConectionDB.conectionDB();
			Statement statement = db.createStatement();

			ResultSet resultado = statement.executeQuery("SELECT * FROM hoteles");

			while (resultado.next()) {

				String nombre = resultado.getString("codHotel");

				System.out.println(nombre);

			}
			
			resultado.close();
			statement.close();
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
