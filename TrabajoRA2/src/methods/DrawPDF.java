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

	private static PDDocument pdf = new PDDocument();
	private static PDPage page = new PDPage(PDRectangle.A4);
	private static PDPage page2 = new PDPage(PDRectangle.A4);
	private static PDImageXObject image;
	private static PDPageContentStream transactions;
	private static PDPageContentStream transactions2;
	private static float salesMony;
	private static float buyMony;

	@SuppressWarnings("unused")
	public static void writePDF() {

		try {

			writeHeader1();

			transactions.addRect(50, 605, 140, 50);
			transactions.addRect(225, 605, 140, 50);
			transactions.addRect(405, 605, 140, 50);

			

			transactions.setFont(PDType1Font.TIMES_BOLD, 23);
			transactions.beginText();
			transactions.newLineAtOffset(60, 570);
			transactions.showText("Sales:");
			transactions.endText();

			float margin = 50;
			float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
			float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

			boolean drawContent = true;
			float yStart = yStartNewPage;
			float bottomMargin = 70;
			float yPosition = 560;

			BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, pdf, page, true,
					drawContent);

			Row<PDPage> headerRow = table.createRow(17f);
			Cell<PDPage> cell;
			cell = headerRow.createCell(25, "Product");
			cell = headerRow.createCell(25, "Unit price");
			cell = headerRow.createCell(25, "Quantity");
			cell = headerRow.createCell(25, "Total price");

			table.addHeaderRow(headerRow);

			for (Transaction t : Test.transactionList) {
				if (t.getType() == 1) {
					float totalPrice = (Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getPrice())
							* t.getAmount();
					salesMony += totalPrice;
					Row<PDPage> row = table.createRow(12);
					cell = row.createCell(25, Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getName());
					cell = row.createCell(25,
							String.valueOf(Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getPrice()));
					cell = row.createCell(25, String.valueOf(t.getAmount()));
					cell = row.createCell(25, String.valueOf(totalPrice));
				}
			}
			table.draw();
			
			

			writeHeader2();
			
			transactions2.setFont(PDType1Font.TIMES_BOLD, 23);
			transactions2.beginText();
			transactions2.newLineAtOffset(60, 570);
			transactions2.showText("Buys:");
			transactions2.endText();

			float margin2 = 50;
			float yStartNewPage2 = page2.getMediaBox().getHeight() - (2 * margin);
			float tableWidth2 = page2.getMediaBox().getWidth() - (2 * margin);

			boolean drawContent2 = true;
			float yStart2 = yStartNewPage2;
			float bottomMargin2 = 70;
			float yPosition2 = 560;

			BaseTable table2 = new BaseTable(yPosition2, yStartNewPage2, bottomMargin2, tableWidth2, margin2, pdf, page2, true,
					drawContent2);

			Row<PDPage> headerRow2 = table2.createRow(17f);
			Cell<PDPage> cell2;
			cell2 = headerRow2.createCell(25, "Product");
			cell2 = headerRow2.createCell(25, "Unit price");
			cell2 = headerRow2.createCell(25, "Quantity");
			cell2 = headerRow2.createCell(25, "Total price");

			table2.addHeaderRow(headerRow2);

			for (Transaction t : Test.transactionList) {
				if (t.getType() == 2) {
					float totalPrice = (float) (((Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getPrice())/1.8)
							* t.getAmount());
					buyMony += totalPrice;
					Row<PDPage> row = table2.createRow(12);
					cell2 = row.createCell(25, Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getName());
					cell2 = row.createCell(25,
							String.valueOf(Test.product.getProduct(Conexion.obtain(), t.getId_prod()).getPrice()));
					cell2 = row.createCell(25, String.valueOf(t.getAmount()));
					cell2 = row.createCell(25, String.valueOf(totalPrice));
				}
			}
			table2.draw();
			
			transactions.setFont(PDType1Font.HELVETICA_BOLD, 15);
			transactions.beginText();
			transactions.newLineAtOffset(60, 640);
			transactions.showText("Total Sales:                       Bought:                            Profits:");
			transactions.newLineAtOffset(0, -20);
			transactions.showText(salesMony + "$                              "+ buyMony +"$                           "
					+ (salesMony - buyMony) + "$");
			transactions.endText();

			transactions.close();

			transactions2.close();

			JFrame parentFrame = new JFrame();

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select location and name to save the PDF");

			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				pdf.save(fileToSave.getAbsolutePath() + ".pdf");
				pdf.close();
				JOptionPane.showMessageDialog(null, "PDF created", "Success!", JOptionPane.PLAIN_MESSAGE);
			} else {
				System.out.println("Download cancelled");
			}

		} catch (Exception excepcionEpicarda) {
			excepcionEpicarda.printStackTrace();
		}
	}

	public static void writeHeader1() {

		try {
			pdf.addPage(page);

			transactions = new PDPageContentStream(pdf, page);

			image = PDImageXObject.createFromFile("images/MercadonaLogo.png", pdf);
			transactions.drawImage(image, 400, 673, 100, 100);

			transactions.setFont(PDType1Font.TIMES_BOLD, 30);
			transactions.beginText();
			transactions.newLineAtOffset(50, 750);
			transactions.showText("Bills");
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void writeHeader2() {

		try {
			pdf.addPage(page2);

			transactions2 = new PDPageContentStream(pdf, page2);

			image = PDImageXObject.createFromFile("images/MercadonaLogo.png", pdf);
			transactions2.drawImage(image, 400, 673, 100, 100);

			transactions2.setFont(PDType1Font.TIMES_BOLD, 30);
			transactions2.beginText();
			transactions2.newLineAtOffset(50, 750);
			transactions2.showText("Bills");
			transactions2.endText();

			transactions2.setFont(PDType1Font.HELVETICA_BOLD, 12);
			transactions2.beginText();
			transactions2.newLineAtOffset(50, 730);
			transactions2.showText("Mercadona S.A");
			transactions2.newLineAtOffset(0, -20);
			transactions2.showText("St. John Street");
			transactions2.newLineAtOffset(0, -20);
			transactions2.showText("856 03 51 07");
			transactions2.newLineAtOffset(0, -20);
			transactions2.showText("09:30 - 22:00");
			transactions2.newLineAtOffset(0, -10);
			transactions2.showText(
					"...................................................................................................................................................");
			transactions2.endText();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
