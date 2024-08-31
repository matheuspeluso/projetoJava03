package factories;
/*
 * Classe para fabricar conexões para banco de dados 
 * Design patter gof : Factory method
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	/*
	 * Método estático para retornar uma conexão
	 * com um banco de dados do postgresql
	 */
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_aula02","postgres","coti");
		}
		catch(Exception e ) {
			System.out.println("\nFalha ao abrir conexão com o banco de dados!");
			System.out.println(e.getMessage());
		}
		
		return connection;
	}
}
