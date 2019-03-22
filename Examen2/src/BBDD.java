import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BBDD {
	private String cadenaConexion;
	private String driver;
	private Connection cn;

	//Constructor
	public BBDD(){
		cadenaConexion="jdbc:mysql://localhost:3306/examen2";
		driver="com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Metodos
	public void conectar(){
		try {
			cn=DriverManager.getConnection(cadenaConexion, "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insertarRegistros(int numero, String mensaje){
		int cont = 0;
		String sentSql = "INSERT INTO mensajes VALUES (?, ?)";
		PreparedStatement sentencia;
		try {
			sentencia = cn.prepareStatement(sentSql);
			sentencia.setInt(1, numero);
			sentencia.setString(2, mensaje);
			cont= sentencia.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cont;
	}

}
