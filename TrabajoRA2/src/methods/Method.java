package methods;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Product;
import test.Test;

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

}
