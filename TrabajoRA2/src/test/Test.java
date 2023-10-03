package test;

import java.sql.SQLException;

import services.Conexion;
import views.Login;

public class Test {

	public static void main(String[] args) {
		
		//HEHE, SIUUUUUUUUUUUUUUU
		new Login();
		try {
			Conexion.obtain();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
