package datos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.mysql.cj.x.protobuf.MysqlxCrud.DataModel;

import database.AsignacionDB;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import objetos.Asignatura;
import objetos.Profesor;

public class GenerarPDF {

    public static void generarPDF(TableView<AsignacionDB> tableView, String rutaArchivo) {
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(rutaArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Table table = new Table(tableView.getColumns().size());

            // Agregar encabezados de columna al PDF
            for (TableColumn<AsignacionDB, ?> column : tableView.getColumns()) {
                table.addCell(new Paragraph(column.getText()).setTextAlignment(TextAlignment.CENTER));
            }

            // Agregar datos de filas al PDF
            ObservableList<AsignacionDB> items = tableView.getItems();
            for (AsignacionDB item : items) {
                for (TableColumn<AsignacionDB, ?> column : tableView.getColumns()) {
                    Object cellValue = column.getCellData(item);
                    table.addCell(new Paragraph(String.valueOf(cellValue)).setTextAlignment(TextAlignment.CENTER));
                }
            }

            document.add(table);
            document.close();

            System.out.println("PDF generado correctamente en: " + rutaArchivo);
        } catch (Exception e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
        }
    }
}