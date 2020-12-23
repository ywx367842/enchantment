package com.cesar.core.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 页眉页脚
 */
public class PageNumber {
    class Header extends PdfPageEventHelper {

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(1);
            try {
                Font font = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
                table.setTotalWidth(PageSize.A4.getWidth());    // A4大小
                table.getDefaultCell().setFixedHeight(20);
                PdfPCell cell = new PdfPCell(new Phrase("XXX生活支出明细", font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setPaddingBottom(6f);
                table.addCell(cell);

                PdfContentByte canvas = writer.getDirectContent();
                table.writeSelectedRows(0, -1, ((document.right() + document.rightMargin())-PageSize.A4.getWidth())/2, document.top()+25, canvas);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(1);
            try {
                Font font = new Font(Font.FontFamily.SYMBOL, 10);
                table.setTotalWidth(PageSize.A4.getWidth());    // A4大小
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                table.addCell(new Phrase(writer.getPageNumber()+"", font));

                PdfContentByte canvas = writer.getDirectContent();
                table.writeSelectedRows(0, -1, ((document.right() + document.rightMargin())-PageSize.A4.getWidth())/2, document.bottom(), canvas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final String DEST = "D:/test/pdf/pdf_with_head_foot.pdf";
    public static final String FONT = "D:/test/pdf/华庚少女字体.ttf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PageNumber().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Font font = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPageEvent(new Header());
        document.open();
        PdfDictionary parameters = new PdfDictionary();
        parameters.put(PdfName.MODDATE, new PdfDate());
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(PageSize.A4.getWidth());
        // 锁定宽度
        table.setLockedWidth(true);
        table.setWidths(new int[]{4, 4, 2});
        PdfPCell cell = new PdfPCell(new Phrase("去向", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("金额", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("备注", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8f);
        table.addCell(cell);

        Random random = new Random();
        for (int i = 0; i < 30; i++){
            cell = new PdfPCell(new Phrase("花呗还款", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("$" + random.nextInt(500), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("——", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
        }
        document.add(table);
        document.close();
    }

}