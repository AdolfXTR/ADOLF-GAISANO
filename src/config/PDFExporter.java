
package Config;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;

public class PDFExporter {

   public static void exportSelectedRowsToPDF(JTable table, int[] selectedRows) {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Save PDF");
    chooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));

    int result = chooser.showSaveDialog(null);
    if (result != JFileChooser.APPROVE_OPTION) {
        return;
    }

    File file = chooser.getSelectedFile();
    String filePath = file.getAbsolutePath();
    if (!filePath.toLowerCase().endsWith(".pdf")) {
        filePath += ".pdf";
    }

    Document document = new Document(PageSize.A4);

    try {
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Add logo if exists
        try {
            Image logo = Image.getInstance("Images/logo.png");
            logo.scaleToFit(80, 80);
            logo.setAlignment(Image.LEFT);
            document.add(logo);
        } catch (Exception e) {
            System.out.println("Logo not found or failed to load.");
        }

        // Add Title
        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Room Booking Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        TableModel model = table.getModel();

        // Format each selected row as a block
        Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font valueFont = new Font(Font.HELVETICA, 12);

        for (int rowIndex : selectedRows) {
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingBefore(10f);
            infoTable.setSpacingAfter(10f);

            for (int col = 0; col < model.getColumnCount(); col++) {
                String label = model.getColumnName(col);
                Object valueObj = model.getValueAt(rowIndex, col);
                String value = valueObj != null ? valueObj.toString() : "";

                PdfPCell labelCell = new PdfPCell(new Phrase(label + ":", labelFont));
                PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));

                labelCell.setBorder(Rectangle.NO_BORDER);
                valueCell.setBorder(Rectangle.NO_BORDER);

                infoTable.addCell(labelCell);
                infoTable.addCell(valueCell);
            }

            document.add(infoTable);
            document.add(new Paragraph("--------------------------------------------------"));
        }

        // Footer
        document.add(Chunk.NEWLINE);
        Paragraph footer = new Paragraph("Generated on: " + java.time.LocalDate.now());
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.add(footer);

        document.close();

        JOptionPane.showMessageDialog(null, "PDF exported successfully to:\n" + filePath);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage(), "PDF Export Error", JOptionPane.ERROR_MESSAGE);
    }
}

    }

