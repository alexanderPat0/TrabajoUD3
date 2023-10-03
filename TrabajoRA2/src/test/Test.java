package test;

import java.util.List;

import models.Product;
import services.Conexion;
import services.ProductService;
import views.LoadingScreen;

public class Test {
	
	public static List<Product>productList;
	public static ProductService product=new ProductService();

	public static void main(String[] args) {
		
		//HEHE, SIUUUUUUUUUUUUUUU
		
		try {
			// int id_prov, String name, String description,float price, String category, String image, Date expire_date
			productList=product.getAllProducts(Conexion.obtain());
			for(Product p:productList) {
				System.out.println(p);
			}
			Conexion.obtain();
			new LoadingScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
