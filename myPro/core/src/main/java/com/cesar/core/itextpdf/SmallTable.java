package com.cesar.core.itextpdf;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 条形码
 */
public class SmallTable {
    public static final String FONT = "D:/test/pdf/华庚少女字体.ttf";
    public static final String DEST = "D:/test/pdf/small_table.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SmallTable().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Rectangle small = new Rectangle(290,100);
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(bf, 12);
        Document document = new Document(small, 5, 5, 5, 5);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 160, 120 });
        table.setLockedWidth(true);
        PdfContentByte cb = writer.getDirectContent();
        // first row
        PdfPCell cell = new PdfPCell(new Phrase("条形码", font));
        cell.setFixedHeight(30);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);
        // second row
        Barcode128 code128 = new Barcode128();
        code128.setCode("14785236987541");
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        cell = new PdfPCell(code128Image, true);
        cell.setColspan(2);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(30);
        table.addCell(cell);

        document.add(table);
        document.close();
    }
}