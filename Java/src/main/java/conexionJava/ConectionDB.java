package conexionJava;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConectionDB {

	private static File FICHERO = new File("valor.txt");

	public static void main(String[] args) {

	}

	public static Connection conectionDB() {
		int entrada = leerFichero();
		switch (entrada) {
		case 1:
			return conectionDBMysql();
		case 2:
			return conectionDBSqlServer();
		case 3:
			return conectionDBAccess();
		default:
			break;
		}
		return null;
	}

	public static Connection conectionDBMysql() {
		try {
			String url = "jdbc:mysql://localhost:3306/bdhoteles";
			String username = "root";
			String password = "";

			Connection db = DriverManager.getConnection(url, username, password);
			db.setAutoCommit(false);
			return db;

		} catch (Exception e) {

			System.out.println("Error con la conexion");
			return null;

		}
	}

	public static Connection conectionDBSqlServer() {
		try {
			String url = "jdbc:sqlserver://DESKTOP-FUVTEJT;DataBaseName=bdhoteles";
			String username = "sa_cliente";
			String password = "admin";

			Connection db = DriverManager.getConnection(url, username, password);
			db.setAutoCommit(false);
			return db;

		} catch (Exception e) {

			System.out.println("Error con la conexion");
			return null;

		}

	}

	public static Connection conectionDBAccess() {
		
		try {
			String strConnectionString = "jdbc:ucanaccess://access-bdHoteles_PabloSuarezRomero.accdb";
			

			Connection db = DriverManager.getConnection(strConnectionString);
			db.setAutoCommit(false);
			return db;

		} catch (Exception e) {

			System.out.println("Error con la conexion");
			return null;

		}

	}

	public static int leerFichero() {
		try {
			int entero = 0;
			if (FICHERO.exists()) {
				String contenido;
				contenido = Files.readString(FICHERO.toPath(), StandardCharsets.UTF_8);
				entero = Integer.parseInt(contenido);

			} else {
			}
			return entero;
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
			return 0;
		}
	}

	public static void escribirFichero(int num) {

		try {
			Files.writeString(FICHERO.toPath(), "" + num, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
