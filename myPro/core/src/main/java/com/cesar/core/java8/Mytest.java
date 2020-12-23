package com.cesar.core.java8;

import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;

/**
 * @ClassName Mytest
 * @Description TODO
 * @Author Cesar
 * @Data 2019/6/28
 **/
public class Mytest {

//    public static final String FONT = "d:/test/pdf/华庚少女字体.ttf";
    public static final String FONT = "c:\\windows\\fonts\\simkai.ttf";

    private String outPath = "D:\\test\\pdf\\";

    //    private String outPath = "D:\\test\\pdf";
    @Test
    public void test001() throws Exception {
        FileInputStream is = new FileInputStream("D:\\test\\word\\test.docx");
        XWPFDocument docx = new XWPFDocument(is);
        List<XWPFParagraph> paragraphs = docx.getParagraphs();

        List<XWPFTable> xtables = docx.getTables();

        List<PdfPTable> tables = new ArrayList<>();


        // 使用语言包字体
        BaseFont abf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        // 外部字体
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, true); // 生成文件大小与编码有关，如果你没有中文，那么编码用BaseFont.WINANSI就节约很多资源了。
        bf.setSubset(true);
        Font font = new Font(bf, 12);

        Font titleFont = new Font(bf, 12, Font.BOLD);
        titleFont.setColor(new BaseColor(76, 175, 80));


        for (int i = 0; i < xtables.size(); i++) {
            XWPFTable xtable = xtables.get(i);

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(16f);

            List<XWPFTableRow> xrows = xtable.getRows();

            for (int j = 0; j < xrows.size(); j++) {
                XWPFTableRow xrow = xrows.get(j);
                List<XWPFTableCell> xcells = xrow.getTableCells();
                for (int k = 0; k < xcells.size(); k++) {
                    XWPFTableCell xcell = xcells.get(k);
                    PdfPCell cell = new PdfPCell(new Phrase(xcell.getText(), titleFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

            }
            tables.add(table);
//            XWPFTableRow xrow = xrows.get(0);
        }


        String filename = outPath + "1234.pdf";
//        String str = "☑ □";
//        System.out.println(str);

//        Document document = new Document();
        Document document = new Document(PageSize.A4, 48, 48, 60, 65);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

        writer.setPageEvent(new Header());

        // step 3
        document.open();

        //目录
        List<Chapter> chapterList = new ArrayList<Chapter>();
        int fristLevel = 1;
        Chapter chapter = null;
        int sizeParagraph = paragraphs.size();

        // step 4
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
//            String style = paragraph.getStyle();
            if ("$【表格-1】".equals(text)) {
                document.add(tables.get(0));
            }
            document.add(new Paragraph(text, font));
        }
        // step 5
        document.close();


    }


    public void test() {

        /*Document doc = new Document(PageSize.A4, 48, 48, 60, 65);
        PdfWriter contentWriter = PdfWriter.getInstance(doc, new ByteArrayOutputStream());
        //设置事件
        ContentEvent event = new ContentEvent();
        contentWriter.setPageEvent(event);

        //存目录监听 开始
        doc.open();
        List<Chapter> chapterList = new ArrayList<Chapter>();

        //获取段落内容
        QueryWrapper<CmsModelParagraph> query = Wrappers.<CmsModelParagraph>query();
        query.eq("contract_model_id", modelId).orderByAsc("paragraph_position");


        QueryWrapper<CmsModelRelaField> query1 = Wrappers.<CmsModelRelaField>query();
        query1.eq("contract_model_id", modelId);
        List<CmsModelRelaField> cmsModelRelaFields = cmsModelRelaFieldMapper.selectList(query1);

        Map<String, String> wordValue = new HashMap<>();
        for (CmsModelRelaField cmsModelRelaField : cmsModelRelaFields) {
            wordValue.put(cmsModelRelaField.getInputId(), cmsModelRelaField.getDefaultValue());
        }
        List<CmsModelParagraph> cmsModelParagraphs = cmsContractModelParagraphMapper.selectList(query);
        for (CmsModelParagraph cmsModelParagraph : cmsModelParagraphs) {
            String text = cmsModelParagraph.getText();
            String str = officeWebUtils.stagToRealValue(text, wordValue);
            cmsModelParagraph.setText(str);
        }

        int fristLevel = 1;
        Chapter chapter = null;
        int sizeParagraph = cmsModelParagraphs.size();
        for (int i = 0; i < cmsModelParagraphs.size(); i++) {
            CmsModelParagraph cmsModelParagraph = cmsModelParagraphs.get(i);
            //System.out.println("cmsModelParagraph.getStyle()" + cmsModelParagraph.getStyle() + " -- " + cmsModelParagraph.getText());
            //1、判断段落是否是目录标题---待补充段落内文字补全
            //1.1、判断是否是1级目录 style = 2
            if ("2".equals(cmsModelParagraph.getStyle())) {
                if (null != chapter) {
                    doc.add(chapter);
                    chapterList.add(chapter);
                }
                chapter = new Chapter(new Paragraph(cmsModelParagraph.getText(), setFristCatalogFont()), fristLevel);
                fristLevel++;
                continue;
            }
            if (null == chapter) {
                continue;
            }
            //1.2、判断是否是2级目录 style = 3
            if ("3".equals(cmsModelParagraph.getStyle())) {
                Section section = chapter.addSection(new Paragraph(cmsModelParagraph.getText(), setSecondCatalogFont()));
                section.setIndentationLeft(10);
                section.add(new Paragraph("\n"));
                continue;
            }
            //1.3、判断条款

            //1.4、判断表格
            if ("table".equals(cmsModelParagraph.getStyle())) {
                System.out.println("Style:table");
                //创建表格列
                PdfPTable table = createTable(2);
                Paragraph paragraphCell = new Paragraph("单元格内容");
                table.addCell(createCell(paragraphCell));
                table.addCell(createCell(paragraphCell));
                table.addCell(createCell(paragraphCell));
                table.addCell(createCell(paragraphCell));
                chapter.add(table);
                continue;
            }
            //1.3、正文
            //("Style:正文");
            Paragraph paragraph = new Paragraph(cmsModelParagraph.getText(), setFont(10f));
            chapter.add(paragraph);

            //最后添加对象
            if (i == sizeParagraph - 1) {
                doc.add(chapter);
                chapterList.add(chapter);
            }
        }

        doc.close();
        //存目录监听 结束

        Document document = new Document(PageSize.A4, 48, 48, 60, 65);

        FileOutputStream os = new FileOutputStream(outPath);
        PdfWriter writer = PdfWriter.getInstance(document, os);
        IndexEvent indexEvent = new IndexEvent();
        writer.setPageEvent(indexEvent);
        document.open();

        //添加章节目录
        Chapter indexChapter = new Chapter(new Paragraph("目录：", setFont(20f)), 0);
        indexChapter.setNumberDepth(-1);                              // 设置数字深度
        for (Map.Entry<String, Integer> index : event.index.entrySet()) {
            String key = index.getKey();
            String keyValue = key;
            float size = 18f;
            if (countInString(key.substring(0, 5), ".") == 1) {
                //根据第一个点的位置，截取 字符串。得到结果 result
                key = key.substring(key.indexOf(".") + 1);
                keyValue = key;
                size = 18f;
            } else if (countInString(key.substring(0, 5), ".") == 2) { //小标题缩进判断, 如有三级标题自己添加判断
                //根据第二个点的位置，截取 字符串。得到结果 result
                key = key.substring(key.indexOf(".", key.indexOf(".") + 1) + 1);
                keyValue = "    " + key;
                size = 15f;
            }
            Paragraph paragraph = new Paragraph(keyValue, setFont(size));

            Chunk chunk0101 = new Chunk(new DottedLineSeparator());

            Chunk pageno = new Chunk(index.getValue() + "");

            Chunk chunk0102 = new Chunk(pageno);

            //加入点点
            paragraph.add(chunk0101);
            //加入页码
            paragraph.add(chunk0102);

            indexChapter.add(paragraph);

        }

        document.add(indexChapter);

        document.newPage();

        //添加内容
        for (Chapter c : chapterList) {
            indexEvent.body = true;
            document.add(c);
        }
        document.close();
        os.close();

        //加水印-生成到应用服务器
        String waterMarkPath = creatPdfPath + modelId + "Mark.pdf";
        String font = winFont + File.separator + "simkai.ttf";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(waterMarkPath)));
        WaterMarkUtils.setWatermark(bos, outPath, "测试用水印", font);*/
    }

    public static void main(String[] args) {
        IntBinaryOperator intBinaryOperator = (int a, int b) -> a + b;

        MathOperation add = (int a, int b) -> a + b;

        int operation = add.operation(1, 2);
        System.out.println(operation);
    }
    class Header extends PdfPageEventHelper {

        private int page;
        Map<String, Integer> index = new LinkedHashMap<String, Integer>();

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            /*PdfPTable table = new PdfPTable(1);
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
            }*/
            page++;

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
}


interface MathOperation {
    int operation(int a, int b);
}