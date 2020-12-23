package com.cesar.core.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 跨页表格，每一页都有相同的页脚页眉行。
 */
public class HeaderRowsTable {
    public static final String DEST = "D:/test/pdf/header_row_table.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HeaderRowsTable().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, BaseColor.YELLOW);


        float[] columnWidths = {1, 5, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        PdfPCell cell = new PdfPCell(new Phrase("Header Row And Footer Row", f));
        cell.setBackgroundColor(GrayColor.GRAYBLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        // 每个页面出现的页眉+页脚行数
        for (int i = 0; i < 2; i++) {
            table.addCell("#");
            table.addCell("Key");
            table.addCell("Value");
        }
        // 设置标题行数。只有将表添加到文档并且表跨页时，此配置才有意义。
        table.setHeaderRows(3);
        // 设置页脚行数。页眉行数 = 标题行数 - 页脚行数。比如此例子：每一页表格都有两行页眉行（3-1=2）和一行页脚行。
        table.setFooterRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (int counter = 1; counter < 51; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("—— ");
            table.addCell("—— ");
        }
        document.add(table);


        document.newPage();
        table = new PdfPTable(columnWidths);
        table.setSpacingBefore(32f);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        cell = new PdfPCell(new Phrase("Only Have Header Row", f));
        cell.setBackgroundColor(GrayColor.GRAYBLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        // 每个页面出现的页眉+页脚行数
        for (int i = 0; i < 1; i++) {
            table.addCell("#");
            table.addCell("Key");
            table.addCell("Value");
        }
        // 也可以仅仅设置页眉行
        table.setHeaderRows(2);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (int counter = 1; counter < 51; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("—— ");
            table.addCell("—— ");
        }
        document.add(table);


        document.newPage();
        table = new PdfPTable(columnWidths);
        table.setSpacingBefore(32f);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(new BaseColor(133, 148, 6));
        // 每个页面出现的页眉+页脚行数（数量与setHeaderRows(2)一致。为什么前面示例都是少1呢？因为前面示例已经单独添加了一行主标题）
        for (int i = 0; i < 2; i++) {
            table.addCell(new Phrase("#", f));
            table.addCell(new Phrase("Key", f));
            table.addCell(new Phrase("Value", f));
        }
        table.setHeaderRows(2);
        table.setFooterRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (int counter = 1; counter < 51; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("—— ");
            table.addCell("—— ");
        }
        document.add(table);

        document.close();
    }
}