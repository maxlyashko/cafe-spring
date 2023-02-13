package ua.lyashko.cafe.pdf;

import java.awt.Color;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import ua.lyashko.cafe.model.Bill;
import ua.lyashko.cafe.model.Product;

public class BillPDFExporter {
    private Bill bill;

    public BillPDFExporter ( Bill bill ) {
        this.bill = bill;
    }

    private void writeTableHeader ( PdfPTable table ) {
        PdfPCell cell = new PdfPCell ( );
        cell.setBackgroundColor ( Color.LIGHT_GRAY );
        cell.setPadding ( 5 );

        Font font = FontFactory.getFont ( FontFactory.HELVETICA );
        font.setColor ( Color.WHITE );

        cell.setPhrase ( new Phrase ( "Product name" , font ) );

        table.addCell ( cell );

        cell.setPhrase ( new Phrase ( "Product category" , font ) );
        table.addCell ( cell );

        cell.setPhrase ( new Phrase ( "Product price" , font ) );
        table.addCell ( cell );
    }

    private void writeTableData ( PdfPTable table ) {
        for (Product product : bill.getProducts ( )) {
            table.addCell ( product.getName ( ) );
            table.addCell ( product.getCategory ( ).getName ( ) );
            table.addCell ( String.valueOf ( product.getPrice ( ) ) );
        }
    }

    public void export ( HttpServletResponse response ) throws DocumentException, IOException {
        Document document = new Document ( PageSize.A4 );
        PdfWriter.getInstance ( document , response.getOutputStream ( ) );

        document.open ( );
        Font font = FontFactory.getFont ( FontFactory.HELVETICA );
        font.setSize ( 18 );
        font.setColor ( Color.BLACK );

        String billId = "Bill id: " + bill.getId ( );
        String billDate = "Bill date: " + bill.getDate ( );
        String totalPrice = "Bill total price: " + bill.getTotalPrice ( );

        Paragraph idParagraph = new Paragraph ( billId , font );
        Paragraph dateParagraph = new Paragraph ( billDate , font );
        Paragraph totalPriceParagraph = new Paragraph ( totalPrice , font );
        Paragraph tableNameParagraph = new Paragraph("List of Products", font);

        idParagraph.setAlignment ( Paragraph.ALIGN_LEFT );
        dateParagraph.setAlignment ( Paragraph.ALIGN_LEFT );
        totalPriceParagraph.setAlignment ( Paragraph.ALIGN_RIGHT );
        tableNameParagraph.setAlignment ( Paragraph.ALIGN_CENTER );

        document.add ( idParagraph );
        document.add ( dateParagraph );
        document.add ( tableNameParagraph );

        PdfPTable table = new PdfPTable ( 3 );
        table.setWidthPercentage ( 100f );
        table.setWidths ( new float[]{2.0f , 2.0f , 2.0f} );
        table.setSpacingBefore ( 10 );

        writeTableHeader ( table );
        writeTableData ( table );

        document.add ( table );
        document.add ( totalPriceParagraph );

        document.close ( );
    }
}
