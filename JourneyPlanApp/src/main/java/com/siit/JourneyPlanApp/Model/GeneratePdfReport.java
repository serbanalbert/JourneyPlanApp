package com.siit.JourneyPlanApp.Model;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream foaieTraseuReport(String dataSelectata, String route, String nrMasina, List<Reservation> reservations) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

//            PdfPTable firstTable = new PdfPTable(1);
//            firstTable.setWidthPercentage(70);
//            firstTable.setWidths(new int[]{ 1});
            PdfPCell hcell;
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);




            PdfPTable table = new PdfPTable(2);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(70);
            table.setWidths(new int[]{ 1, 1});


            hcell = new PdfPCell(new Phrase("Nume si prenume", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Numar telefon", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(hcell);


            for (Reservation reservation : reservations) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(reservation.getNumeClient() + " " + reservation.getPrenumeClient()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(reservation.getNrTelefon()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

            }

            PdfWriter.getInstance(document, out);

            document.open();
            document.add(new Paragraph("Data: " + dataSelectata ,headFont));
            document.add(new Paragraph("Ruta: " + route ,headFont));
            document.add(new Paragraph("Numar masina: " + nrMasina ,headFont));
            document.add(new Paragraph(" ", headFont));
            document.add(new Paragraph(" ", headFont));
            document.add(new Paragraph(" ", headFont));
            document.add(new Paragraph(" ", headFont));

            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
