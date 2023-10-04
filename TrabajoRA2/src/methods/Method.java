package methods;

import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Product;
import models.Provider;
import services.Conexion;
import test.Test;
import viewsProviders.SeeProv;

public class Method {
	
	public static TableModel UploadProductList() {
		String[] col= {"id_prov","Name","Description","Price","Category","Image","Expire_Date"};
		DefaultTableModel model=new DefaultTableModel(col,0);
		
		for(Product p:Test.productList) {
			Object[] row= {p.getId_prov(),p.getName(),p.getDescription(),p.getPrice(),p.getCategory(),p.getImage(),p.getExpire_date()};
			model.addRow(row);
		}
		return model;
	}
	
	public static TableModel UploadProviderList() {
		String[] col= {"Name","Location","Mail","Phone"};
		DefaultTableModel model=new DefaultTableModel(col,0);
		
		for(Provider p:Test.providerList) {
			Object[] row= {p.getName(),p.getLocation(),p.getMail(),p.getPhone()};
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
		
		String[] cols = { "Name","Description","Price","Category","Expire_Date"};
		DefaultTableModel model=new DefaultTableModel(cols,0);
		
		for(Product p:Test.productList) {
			Object[] row= {p.getId_prov(),p.getName(),p.getDescription(),p.getPrice(),p.getCategory(),p.getExpire_date()};
			model.addRow(row);
		}
		return model;
	}

}
