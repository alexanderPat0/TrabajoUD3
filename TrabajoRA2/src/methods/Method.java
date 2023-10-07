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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Action;
import models.Product;
import models.Provider;
import services.Conexion;
import test.Test;
import viewsProviders.SeeProv;

public class Method {

	public static TableModel UploadProductList() {
		String[] col = { "id_prov", "Name", "Description", "Price", "Amount", "Category", "Image", "Expire_Date" };
		DefaultTableModel model = new DefaultTableModel(col, 0);

		for (Product p : Test.productList) {
			Object[] row = { p.getId_prov(), p.getName(), p.getDescription(), p.getPrice(), p.getAmount(), p.getCategory(),
					p.getImage(), p.getExpire_date() };
			model.addRow(row);
		}
		return model;
	}

	public static TableModel UploadProviderList() {
		String[] col = { "Name", "Location", "Mail", "Phone" };
		DefaultTableModel model = new DefaultTableModel(col, 0);

		for (Provider p : Test.providerList) {
			Object[] row = { p.getName(), p.getLocation(), p.getMail(), p.getPhone() };
			model.addRow(row);
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
				model.addRow(new Object[] { p.getName(), p.getLocation(), p.getMail(), p.getPhone() });
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		SeeProv.table.setModel(model);
	}

	public static DefaultTableModel refreshTableProduct() {

		String[] cols = {"Id_prov", "Name", "Description", "Price", "Amount", "Category", "Expire_Date" };
		DefaultTableModel model = new DefaultTableModel(cols, 0);

		for (Product p : Test.productList) {
			Object[] row = { p.getId_prov(), p.getName(), p.getDescription(), p.getPrice(),p.getAmount(), p.getCategory(),
					p.getExpire_date() };
			model.addRow(row);
		}
		return model;
	}

	public static DefaultTableModel showLog() {

		DefaultTableModel model = new DefaultTableModel();
		return model;
		
	}
	
	public static List<String> getActionString() throws ClassNotFoundException, SQLException {
		for (Action a : Test.actionList) {
			String stringAction;
			
			
			stringAction = a.getDate()+": User "+Test.user.getUser(Conexion.obtain(), a.getId_user()).getName()+" has  ";
			
			if(a.getAction_tipe() == 1) {
				stringAction  += "added ";
			}else if(a.getAction_tipe() == 2) {
				stringAction  += "sold ";
			}else if(a.getAction_tipe() == 3) {
				stringAction  += "deleted ";
			}else if(a.getAction_tipe() == 4) {
				stringAction  += "bought ";

			}else 
			if(a.getId_product() == 0)
				stringAction  += "the product "+Test.product.getProduct(Conexion.obtain(), a.getId_product()).getName()+".";
			else
				stringAction  += "the provider "+Test.provider.getProvider(Conexion.obtain(), a.getId_provider()).getName()+".";
			
			
		}
		return null;
	}
	
	public static String FileChooserImage() {
		JFileChooser fc=new JFileChooser();
		String path="";
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG and GIF images", "JPG", "GIF","PNG"); 
	    fc.setFileFilter(imgFilter);
	    int result = fc.showOpenDialog(null);
	    
	    File file=fc.getSelectedFile();
	    
	    if(result!=JFileChooser.CANCEL_OPTION) {
	    	
	    	if(file==null || file.getName().equalsIgnoreCase("")) {
	    		JOptionPane.showMessageDialog(null, "Choose an image");
	    }else {
	    	
	    	
	    	String pathImage = "images/products/"+file.getName();
	    	Path destino=Path.of(pathImage).toAbsolutePath();
	    	
	    	path=pathImage;
	    	
	    	try {
				Files.copy(file.toPath(), destino,StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    	}
	    return path;
	}

}
