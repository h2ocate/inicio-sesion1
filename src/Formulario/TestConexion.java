package Formulario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexion {
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM usuario");
			
			while (rs.next()) {
				String Nombre = rs.getString(1);
				String Apellido = rs.getString(2);
				int Telefono = rs.getInt(3);
				String Correo = rs.getString(4);
				String Usuario = rs.getString(5);
				String Contraseña = rs.getString(6);
				String ComprobarContra = rs.getString(7);

			
				System.out.println(Nombre + " - " + Apellido + " - " + Telefono+ " - " +Correo+ " - "+Usuario+ " - "+Contraseña+ " - " + ComprobarContra);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs!= null) {
					rs.close();
				}
				
				if (stm != null) {
					stm.close();
				}
				
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}