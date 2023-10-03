package test;

import java.sql.SQLException;

import services.Conexion;
import views.LoadingScreen;

public class Test {

	public static void main(String[] args) {
		
		//HEHE, SIUUUUUUUUUUUUUUU
		new LoadingScreen();
		try {
			Conexion.obtain();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
