package methods;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import models.Transaction;
import services.Conexion;
import test.Test;

public class DrawPDF {

	public static void writePDF() {

		try {

			PDDocument pdf = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A4);

			pdf.addPage(page);

			PDPageContentStream transactions = new PDPageContentStream(pdf, page);

			PDImageXObject image = PDImageXObject.createFromFile("images/MercadonaLogo.png", pdf);
			transactions.drawImage(image, 400, 673, 100, 100);

			transactions.setFont(PDType1Font.TIMES_BOLD, 30);
			transactions.beginText();
			transactions.newLineAtOffset(50, 750);
			transactions.showText("Sales");
			transactions.endText();

			transactions.setFont(PDType1Font.HELVETICA_BOLD, 12);
			transactions.beginText();
			transactions.newLineAtOffset(50, 730);
			transactions.showText("Mercadona S.A");
			transactions.newLineAtOffset(0, -20);
			transactions.showText("St. John Street");
			transactions.newLineAtOffset(0, -20);
			transactions.showText("856 03 51 07");
			transactions.newLineAtOffset(0, -20);
			transactions.showText("09:30 - 22:00");
			transactions.newLineAtOffset(0, -10);
			transactions.showText(
					"...................................................................................................................................................");
			transactions.endText();

			transactions.setFont(PDType1Font.HELVETICA_BOLD, 15);
			transactions.beginText();
			transactions.newLineAtOffset(0, -10);

			float margin = 50;
			float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
			float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

			boolean drawContent = true;
			float yStart = yStartNewPage;
			float bottomMargin = 70;
			float yPosition = 600;

			BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, pdf, page, true,
					drawContent);

			Row<PDPage> headerRow = table.createRow(15f);
			Cell<PDPage> cell;
			cell = headerRow.createCell(25, "Product");
			cell = headerRow.createCell(25, "Unit price");
			cell = headerRow.createCell(25, "Quantity");
			cell = headerRow.createCell(25, "Total price");

			table.addHeaderRow(headerRow);

			float finalPrice = 0;
			for (Transaction t : Test.transactionList) {
				float totalPrice = Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getPrice()
						* t.getAmount();
				finalPrice += totalPrice;
				Row<PDPage> row = table.createRow(12);
				cell = row.createCell(25, Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getName());
				cell = row.createCell(25,
						String.valueOf(Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getPrice()));
				cell = row.createCell(25, String.valueOf(t.getAmount()));
				cell = row.createCell(25, String.valueOf(totalPrice));
			}
			table.draw();
			transactions.endText();
			
			transactions.close();

			JFrame parentFrame = new JFrame();

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select location and name to save the PDF");

			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				pdf.save(fileToSave.getAbsolutePath() + ".pdf");
				JOptionPane.showMessageDialog(null, "PDF created", "Success!", JOptionPane.PLAIN_MESSAGE);
			} else {
				System.out.println("Download cancelled");
			}

		} catch (Exception excepcionEpicarda) {
			excepcionEpicarda.printStackTrace();
		}
	}
}
