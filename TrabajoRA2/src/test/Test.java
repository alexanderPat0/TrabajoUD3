package test;

import java.util.List;

import models.Action;
import models.Product;
import models.Provider;
import models.User;
import services.ActionService;
import services.Conexion;
import services.ProductService;
import services.ProviderService;
import services.UserService;
import views.LoadingScreen;

public class Test {

	public static List<Product> productList;
	public static List<Provider> providerList;
	public static List<User> userList;
	public static List<String> listCategories;
	public static List<Action> actionList;

	public static ProductService product = new ProductService();
	public static ProviderService provider = new ProviderService();
	public static UserService user = new UserService();
	public static ActionService action = new ActionService();


	public static User LogedInUser;

	public static void main(String[] args) {
				
		try {
			System.out.println("Products:");

			productList = product.getAllProducts(Conexion.obtain());
			for (Product p : productList) {
				System.out.println(p);
			}
			System.out.println("Providers:");
			providerList = provider.getAllProviders(Conexion.obtain());
			for (Provider p : providerList) {
				System.out.println(p);
			}
			System.out.println("Users:");
			userList = user.getAllUsers(Conexion.obtain());
			for (User p : userList) {
				System.out.println(p);
			}
			
			System.out.println("Actions:");
			
			actionList = action.getAllActions(Conexion.obtain());
			for (Action a : actionList) {
				System.out.println(a);
			}
			Conexion.obtain();
			new LoadingScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
