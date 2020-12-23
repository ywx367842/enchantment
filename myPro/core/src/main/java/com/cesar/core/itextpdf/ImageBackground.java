package com.cesar.core.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 单元格图片背景
 */
public class ImageBackground {
    class ImageBackgroundEvent implements PdfPCellEvent {

        protected Image image;

        public ImageBackgroundEvent(Image image) {
            this.image = image;
        }

        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            try {
                PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
                image.scaleAbsolute(position);
                image.setAbsolutePosition(position.getLeft(), position.getBottom());
                cb.addImage(image);
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            }
        }
    }

    public static final String DEST = "pdf/image_background.pdf";
    public static final String IMG1 = "pdf/bruno.jpg";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImageBackground().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(400);
        table.setLockedWidth(true);
        PdfPCell cell = new PdfPCell();
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.GRAYWHITE);
        Paragraph p = new Paragraph("A cell with an image as background.", font);
        cell.addElement(p);
        Image image = Image.getInstance(IMG1);
        cell.setCellEvent(new ImageBackgroundEvent(image));
        cell.setFixedHeight(600 * image.getScaledHeight() / image.getScaledWidth());
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}