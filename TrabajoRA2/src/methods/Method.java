package methods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Action;
import models.Product;
import models.Provider;
import models.Transaction;
import services.Conexion;
import test.Test;
import viewsProducts.SeeProd;
import viewsProviders.SeeProv;

public class Method {

	public static TableModel UploadProductList() {
		String name = null;
		String[] col = { "Provider", "Name", "Description", "Price", "Amount", "Category", "Expire_Date" };
		DefaultTableModel model = new DefaultTableModel(col, 0);

		for (Product p : Test.productList) {
				try {
					name = Test.provider.getProvider(Conexion.obtain(), p.getId_prov()).getName();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Object[] row = { name, p.getName(), p.getDescription(), p.getPrice(), p.getAmount(), p.getCategory(),
						p.getExpire_date() };
				model.addRow(row);
		}
		return model;
	}

	public static TableModel UploadProviderList() {
		String[] col = { "Name", "Location", "Mail", "Phone" };
		DefaultTableModel model = new DefaultTableModel(col, 0);

		for (Provider p : Test.providerList) {
			if (p.getAvailable() == 1) {
				Object[] row = { p.getName(), p.getLocation(), p.getMail(), p.getPhone() };
				model.addRow(row);
			}
		}
		return model;
	}

	public static TableModel UploadTransactionList() {
		String name = null;
		String[] col = { "Date", "Product", "Price By Unit", "Amount", "Total Price" };
		DefaultTableModel model = new DefaultTableModel(col, 0);
		float totalPrice = 0;

		for (Transaction t : Test.transactionList) {
			try {
				name = Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getName();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (t.getType() == 1) {
				totalPrice = t.getPrice() * t.getAmount();
				Object[] row = { t.getDate(), name, t.getPrice() + " $", t.getAmount(), totalPrice + " $" };
				model.addRow(row);
			} else if (t.getType() == 2) {
				totalPrice = -1 * (t.getPrice() * t.getAmount());
				Object[] row = { t.getDate(), name, t.getPrice() + " $", t.getAmount(), totalPrice + " $" };
				model.addRow(row);
			}
		}
		return model;
	}

	public static void refreshTableProvider() {

		String[] cols = { "Name", "Location", "Mail", "Phone" };
		DefaultTableModel model = new DefaultTableModel(cols, 0);

		try {
			Iterator<Provider> it;
			it = Test.provider.getAllProviders(Conexion.obtain()).iterator();
			while (it.hasNext()) {
				Provider p = it.next();
				if (p.getAvailable() == 1)
					model.addRow(new Object[] { p.getName(), p.getLocation(), p.getMail(), p.getPhone() });
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		SeeProv.table.setModel(model);
	}

	public static void refreshTableProduct(List<Product> listP) {
		String name = null;
		String[] cols = { "Provider", "Name", "Description", "Price", "Amount", "Category", "Expire_Date" };
		DefaultTableModel model = new DefaultTableModel(cols, 0);
		Iterator<Product> it;
		it = listP.iterator();
		while (it.hasNext()) {

			Product p = it.next();
				try {
					name = Test.provider.getProvider(Conexion.obtain(), p.getId_prov()).getName();
				} catch (Exception e) {
					e.printStackTrace();
				}
				model.addRow(new Object[] { name, p.getName(), p.getDescription(), p.getPrice(), p.getAmount(),
						p.getCategory(), p.getExpire_date() });
		}
		SeeProd.table.setModel(model);
	}

	public static String getActionString(Action a) throws ClassNotFoundException, SQLException {
		String stringAction = "User " + Test.user.getUser(Conexion.obtain(), a.getId_user()).getName() + " has ";

		if (a.getAction_tipe() == 1) {
			stringAction += "added ";
		} else if (a.getAction_tipe() == 2) {
			stringAction += "sold ";
		} else if (a.getAction_tipe() == 3) {
			stringAction += "deleted ";
		} else if (a.getAction_tipe() == 4) {
			stringAction += "edited ";

		}
		if (a.getId_product() != 0)
			stringAction += "the product " + Test.product.getProduct(Conexion.obtain(), a.getId_product()).getName()
					+ ".";
		else
			stringAction += "the provider " + Test.provider.getProvider(Conexion.obtain(), a.getId_provider()).getName()
					+ ".";

		return stringAction;
	}

	public static String FileChooserImageEdit(String img) {
		JFileChooser fc = new JFileChooser();
		String path = "";

		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG and GIF images", "JPG", "GIF", "PNG");
		fc.setFileFilter(imgFilter);
		int result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			if (file == null || file.getName().equalsIgnoreCase("")) {
				path = "images/products/Image_not_available.png";
			} else {
				String pathImage = "images/products/" + file.getName();
				Path destino = Path.of(pathImage).toAbsolutePath();
				path = pathImage;

				try {
					Files.copy(file.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			path = img;
		}

		return path;
	}

	public static String FileChooserImageAdd() {
		JFileChooser fc = new JFileChooser();
		String path = "";

		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG and GIF images", "JPG", "GIF", "PNG");
		fc.setFileFilter(imgFilter);
		int result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			if (file == null || file.getName().equalsIgnoreCase("")) {
				path = "images/products/Image_not_available.png";
			} else {
				String pathImage = "images/products/" + file.getName();
				Path destino = Path.of(pathImage).toAbsolutePath();
				path = pathImage;

				try {
					Files.copy(file.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			path = "images/MercadonaLogo.png";
		}

		return path;
	}

}
