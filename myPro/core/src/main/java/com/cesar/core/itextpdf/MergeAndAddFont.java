package com.cesar.core.itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 合并文档 & 嵌入字体
 */
public class MergeAndAddFont {
    public static final String FONT = "d:/test/pdf/华庚少女字体.ttf";
    public static final Integer FILE_NUM = 2;   // 合并两个文件
    public static final String[] FILE_A = {
            "pdf/testA0.pdf", "pdf/testA1.pdf"
    };
    public static final String[] FILE_B = {
            "pdf/testB0.pdf", "pdf/testB1.pdf"
    };
    public static final String[] FILE_C = {
            "pdf/testC0.pdf", "pdf/testC1.pdf"
    };
    public static final String[] CONTENT = {
            "琪亚娜·卡斯兰娜", "德丽莎·阿波卡利斯"
    };
    public static final String MERGED_A1 = "pdf/testA_merged1.pdf";
    public static final String MERGED_A2 = "pdf/testA_merged2.pdf";
    public static final String MERGED_B1 = "pdf/testB_merged1.pdf";
    public static final String MERGED_B2 = "pdf/testB_merged2.pdf";
    public static final String MERGED_C1 = "pdf/testC_merged1.pdf";
    public static final String MERGED_C2 = "pdf/testC_merged2.pdf";

    public static void main(String[] args) throws DocumentException, IOException {

        File file = new File(MERGED_A1);
        file.getParentFile().mkdirs();
        MergeAndAddFont app = new MergeAndAddFont();

        // 测试一：嵌入字体；生成的文件仅仅包含用到的字形；智能合并；非智能合并
        for (int i = 0; i < FILE_A.length; i++) {
            app.createPdf(FILE_A[i], CONTENT[i], true, true);
        }
        app.mergeFiles(FILE_A, MERGED_A1,false);
        app.mergeFiles(FILE_A, MERGED_A2, true);

        // 测试二：嵌入字体；生成的文件包含完整字体；智能合并；非智能合并
        for (int i = 0; i < FILE_B.length; i++) {
            app.createPdf(FILE_B[i], CONTENT[i], true, false);
        }
        app.mergeFiles(FILE_B, MERGED_B1,false);
        app.mergeFiles(FILE_B, MERGED_B2, true);

        // 测试三：不嵌入字体；生成的文件包含完整字体；智能合并；手动嵌入字体
        for (int i = 0; i < FILE_C.length; i++) {
            app.createPdf(FILE_C[i], CONTENT[i], false, false);
        }
        app.mergeFiles(FILE_C, MERGED_C1, true);
        app.embedFont(MERGED_C1, FONT, MERGED_C2);
    }

    /**
     *
     * @param filename
     * @param text
     * @param embedded  true在PDF中嵌入字体，false不嵌入
     * @param subset    true仅仅包含用到的字形，false包含完整字体
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(String filename, String text, boolean embedded, boolean subset) throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, embedded); // 生成文件大小与编码有关，如果你没有中文，那么编码用BaseFont.WINANSI就节约很多资源了。
        bf.setSubset(subset);
        Font font = new Font(bf, 12);
        document.add(new Paragraph(text, font));
        // step 5
        document.close();
    }

    /**
     * 合并文件
     * @param files
     * @param result
     * @param smart 智能Copy
     * @throws IOException
     * @throws DocumentException
     */
    public void mergeFiles(String[] files, String result, boolean smart) throws IOException, DocumentException {
        Document document = new Document();
        PdfCopy copy;
        if (smart)
            copy = new PdfSmartCopy(document, new FileOutputStream(result));
        else
            copy = new PdfCopy(document, new FileOutputStream(result));
        document.open();
        PdfReader[] reader = new PdfReader[FILE_NUM];
        for (int i = 0; i < files.length; i++) {
            reader[i] = new PdfReader(files[i]);
            copy.addDocument(reader[i]);
            copy.freeReader(reader[i]);
            reader[i].close();
        }
        document.close();
    }

    /**
     * 嵌入字体
     * @param merged
     * @param fontfile
     * @param result
     * @throws IOException
     * @throws DocumentException
     */
    private void embedFont(String merged, String fontfile, String result) throws IOException, DocumentException {
        // the font file
        RandomAccessFile raf = new RandomAccessFile(fontfile, "r");
        byte fontbytes[] = new byte[(int)raf.length()];
        raf.readFully(fontbytes);
        raf.close();
        // create a new stream for the font file
        PdfStream stream = new PdfStream(fontbytes);
        stream.flateCompress();
        stream.put(PdfName.LENGTH1, new PdfNumber(fontbytes.length));
        // create a reader object
        PdfReader reader = new PdfReader(merged);
        int n = reader.getXrefSize();
        PdfObject object;
        PdfDictionary font;
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(result));
        PdfName fontname = new PdfName(BaseFont.createFont(fontfile, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED).getPostscriptFontName());
        for (int i = 0; i < n; i++) {
            object = reader.getPdfObject(i);
            if (object == null || !object.isDictionary())
                continue;
            font = (PdfDictionary)object;
            if (PdfName.FONTDESCRIPTOR.equals(font.get(PdfName.TYPE))
                    && fontname.equals(font.get(PdfName.FONTNAME))) {
                PdfIndirectObject objref = stamper.getWriter().addToBody(stream);
                font.put(PdfName.FONTFILE2, objref.getIndirectReference());
            }
        }
        stamper.close();
        reader.close();
    }
}
