package com.cesar.core.poi.read;

import com.cesar.core.poi.read.xword.Div;
import com.cesar.core.poi.read.xword.Paragraph;
import com.cesar.core.poi.read.xword.Word;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class XWPFDocumentXwpfTest {

    public static void main(String[] args) throws Exception {
        XWPFDocumentXwpfTest test = new XWPFDocumentXwpfTest();
        test.testReadDoc();
    }
    /**
     * 通过XWPFDocument对内容进行访问。对于XWPF文档而言，用这种方式进行读操作更佳。
     *
     * @throws Exception
     */
    public void testReadDoc() throws Exception {
        FileInputStream is = new FileInputStream("D:\\test\\word\\write.docx");
        XWPFDocument doc = new XWPFDocument(is);

        List<XWPFParagraph> paras = doc.getParagraphs();


        /*for (int i = 0; i < paras.size(); i++) {
            XWPFParagraph paragraph = paras.get(i);
            String text = paragraph.getText();
            if(StringUtils.isEmpty(text)){
                continue;
            }


            String style = paragraph.getStyle();
//            String styleId = paragraph.getStyleID();
//            System.out.println("第" + i + "段落:"+style+" : "+styleId);
//            System.out.println(paragraph.getText());
            if("79".equals(style)||"2".equals(style)||"3".equals(style)||"4".equals(style)){
                div = new Div();
                list = new ArrayList<>();
                div.setParagraphs(list);
                words.add(div);
            }

            Paragraph paragraph1 = new Paragraph();
            paragraph1.setText(text);
            list.add(paragraph1);

        }
        System.out.println(JSON.toJSONString(words));*/

        //获取文档中所有的表格
        /*List<XWPFTable> tables = doc.getTables();
//        List<XWPFTable> tables = new ArrayList<>();
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        for (XWPFTable table : tables) {
            //表格属性
//       CTTblPr pr = table.getCTTbl().getTblPr();
            //获取表格对应的行
            rows = table.getRows();
            for (int i = 0; i < rows.size(); i++) {
                XWPFTableRow row = rows.get(i);

                //获取行对应的单元格
                cells = row.getTableCells();

                for (int j = 0; j < cells.size(); j++) {
                    XWPFTableCell cell = cells.get(j);
                    CTTcPr tcPr = cell.getCTTc().getTcPr();
                    CTTblWidth tcW = tcPr.getTcW();
                    BigInteger w = tcW.getW();
                    System.out.println("我的宽度是：" + w);
                    *//*List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        System.out.println(paragraph.getText());
                    }*//*
                    System.out.print("第" + i + "行，第" + j + "列：");
//                    cell.
//                    CTTcPr tcPr = cell.getCTTc().getTcPr();
//                    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange cellMerge = tcPr.getCellMerge();

                    System.out.println(cell.getText());
                    ;
                }
            }
        }*/
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < paras.size(); i++) {
            XWPFParagraph paragraph = paras.get(i);
            String text = paragraph.getText().trim();
            if (StringUtils.isEmpty(text)) {
                continue;
            }


            String style = paragraph.getStyle();
//            String styleId = paragraph.getStyleID();
            System.out.println("第" + i + "段落:" + style + " : " + text);

//            paragraph.get
            int firstLineIndent = paragraph.getIndentationFirstLine();
            int fontSize = 12;
            List<XWPFRun> runs = paragraph.getRuns();
            if (!CollectionUtils.isEmpty(runs)) {
                XWPFRun xwpfRun = runs.get(0);
                fontSize = xwpfRun.getFontSize();
            }

            ParagraphAlignment alignment = paragraph.getAlignment();
            int value = alignment.getValue();

            //转换成空格数量
            float spaceNum = firstLineIndent / 240f;
            int round = Math.round(spaceNum);

            System.out.println(firstLineIndent);
            System.out.println(round);
            System.out.println(fontSize);
            System.out.println(alignment);
            System.out.println(value);


        }
//        System.out.println(builder.toString());


        this.close(is);
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}  