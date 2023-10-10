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

import models.Transaction;
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
		transactions.showText("..........................................................................................................................................");
		transactions.endText();
		
		transactions.setFont(PDType1Font.HELVETICA_BOLD, 15);
		transactions.beginText();
		transactions.newLineAtOffset(50, 635);
		transactions.showText("Product:");
		transactions.newLineAtOffset(90, 635);
		transactions.showText("PPU:");
		transactions.newLineAtOffset(130, 635);
		transactions.showText("Quantity:");
		transactions.newLineAtOffset(170, 635);
		transactions.showText("Price:");
		
		
		
		
		transactions.endText();

		transactions.setFont(PDType1Font.HELVETICA, 12);
		transactions.beginText();
		transactions.newLineAtOffset(50, 650);
		
		for (Transaction t : Test.transactionList) {
			
		}
		

		transactions.endText();
		transactions.close();

		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select location and name to save the PDF");

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			pdf.save(fileToSave.getAbsolutePath() + ".pdf");
			JOptionPane.showMessageDialog(null , "PDF created" , "Success!", JOptionPane.PLAIN_MESSAGE);
		} else {
			System.out.println("Download cancelled");
		}

	} catch (Exception excepcionEpicarda) {
		excepcionEpicarda.printStackTrace();
	}
}}
