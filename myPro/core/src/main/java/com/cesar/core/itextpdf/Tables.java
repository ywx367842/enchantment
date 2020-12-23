package com.cesar.core.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 一些示例
 */
public class Tables {
    public static final String FONT = "D:/test/pdf/华庚少女字体.ttf";
    public static final String DEST = "D:/test/pdf/tables.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Tables().createPdf(DEST);
    }

    public Font setFont(Float fontsize, String font) {
//        "wingding.ttf"
        Font result = new Font();
        try {
//            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\" + font, BaseFont.IDENTITY_H, false);
            BaseFont bf = BaseFont.createFont("D:\\test\\pdf\\" + font, BaseFont.IDENTITY_H, false);
            bf.setSubset(true);
            result = new Font(bf, fontsize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // 使用语言包字体
        BaseFont abf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        // 外部字体
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font titleFont = new Font(bf, 12, Font.BOLD);
        titleFont.setColor(new BaseColor(76, 175, 80));
        Font font = new Font(bf, 10);

        Paragraph p = new Paragraph("个人简历表", new Font(abf, 12, Font.BOLD));
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        //字体家族测试
//        com.itextpdf.text.Font font = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.SYMBOL, 10);

        Paragraph title1 = new Paragraph("标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1标题1" +
                "标题1标题1标题1标题1标题1标题1标题1标题1", setFont(12f, "simsun.ttf"));
        title1.setFirstLineIndent(12f);
//        title1.setAlignment(Paragraph.ALIGN_CENTER);
//        title1.setFont();
        Chapter chapter1 = new Chapter(title1, 0);
        chapter1.setNumberDepth(-1);
        chapter1.setTriggerNewPage(false);
        document.add(chapter1);

        Paragraph title2 = new Paragraph("标题2", setFont(12f, "simsun.ttf"));
        Chapter chapter2 = new Chapter(title2, 0);
        chapter2.setNumberDepth(-1);
        chapter2.setTriggerNewPage(false);
        document.add(chapter2);

        Paragraph title3 = new Paragraph("标题3", setFont(12f, "simsun.ttf"));
        title3.setIndentationLeft(-12f);
        Chapter chapter3 = new Chapter(title2, 0);
        chapter3.setNumberDepth(-1);
        chapter3.setTriggerNewPage(false);
        document.add(chapter3);

//        Paragraph title1 = new Paragraph("标题1", setFont(12f, "simsun.ttf"));
//        Section section1 = new Section(title1, 0);
//        chapter1.setNumberDepth(-1);
//        document.add(chapter1);
//
//        Paragraph title2 = new Paragraph("标题1", setFont(12f, "simsun.ttf"));
//        Chapter chapter2 = new Chapter(title2, 0);
//        chapter2.setNumberDepth(-1);
//        document.add(chapter2);

        String str = "固定总价合同：指本合同价款是包含在本合同附件的图纸目录中的工程图纸及招标文件规定范围内所包含的全部工程内容的总价款，合同中的综合单价、总价是指按照本合同附件的图纸目录中的工程图纸、规范和招标文件要求而包干的完全价格，增值税税率随国家政策调整而调整、暂估价(材料、设备)、暂定金额项目、设计变更及经甲方书面确认的现场签证、洽商等增减帐和预备费（如有）及本合同约定可调整的部分以外的费用均为固定价格性质。合同价款中除已包括增值税销项税税额、人工费、材料费、机械费、企业管理费、利润、规费（含农民工工伤保险费等）、风险费、措施项目费（含组织措施和技术措施费）、总包管理配合服务费、水电费、检验试验费、维保费、成品保护费、培训费、甲供材保管费、专用维修工具和检测设备（该费用还包括供甲方抽检使用的费用）、相关政府部门报验费等政策性文件规定及调整所需的所有费用、税金等，应包括招标文件反映的所有内容和一个有经验的施工方应该预测到的为达验收合格及其他常规所须要发生的费用，并考虑了风险因素等一切费用。本合同价为已根据招标文件、现行设计、图集、质量验收等规范、相关行业和质检部门的验收要求（有冲突时以较严格者为准，以下简称“相关质量要求”）对所有施工图纸中不明确、错漏碰缺、低参数和低标准部分进行修改完善后的包干总价，包括但不限于合同报价书中的内容，结算时不予调整；今后若有因施工图纸的不明确或错漏碰缺或因其低于相关质量要求的，乙方应按要求免费调整到位；若合同中标报价书中有漏报、少报、规格型号或性能参数、内容特征描述低于相关质量要求的误报项目，均已经综合考虑在对应子目全";
        Paragraph para1 = new Paragraph(str, setFont(12f, "simsun.ttf"));
        para1.setLeading(18f);
        para1.setSpacingBefore(100f);
        document.add(para1);
        document.add(new Paragraph(" "));
        Paragraph para2 = new Paragraph("234");
        document.add(para2);

        PdfPTable table = new PdfPTable(8);
        table.setSpacingBefore(116f);

        PdfPCell cell = new PdfPCell(new Phrase("姓名", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        char checked = '\u00FE';
        char unchecked = '\u00A8';
// Here is how to add a checked checkbox
//        document.add(new Paragraph(String.valueOf(checked), font));
// Here is an unchecked checkbox
//        document.add(new Paragraph(String.valueOf(unchecked), font));


        cell = new PdfPCell(new Phrase(String.valueOf(checked), setFont(12f, "wingding.ttf")));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("性别", setFont(12f, "wingding.ttf")));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("女", font));
        cell = new PdfPCell(new Phrase("呵呵¥", setFont(12f, "simsun.ttf")));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("出生年月", setFont(12f, "simsun.ttf")));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("3月28日", font));
        cell = new PdfPCell(new Phrase("呵呵哒¥", setFont(12f, "simsun.ttf")));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("民族", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("天命→休伯利安", font));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("个人简历", titleFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(7);
        cell.addElement(new Paragraph("卡斯兰娜家族一贯的白发蓝眸，长发扎成马尾披于左肩。常穿天命早期使用的女武神统一制服。德丽莎所穿的制服是特别定制款，由主教奥托亲手缝制，在衣服胸口处别着阿波卡利斯家族的家徽。", font));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("家庭成员", titleFont));
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setRowspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("称呼", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("姓名", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("关系", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("爷爷", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("奥托·阿波卡利斯", font));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("德丽莎的造物主，奥托赐予了德丽莎名字，认其为孙女。因为德丽莎温柔而又坚强的性格，让奥托多次产生德丽莎是卡莲的转世的想法", font));
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("侄女", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("琪亚娜·卡斯兰娜", font));
        table.addCell(cell);
        cell = new PdfPCell();
        cell.addElement(new Paragraph("塞西莉亚和齐格飞的女儿，两人让德丽莎当琪亚娜的教母，琪亚娜这个名字也是德丽莎起的。齐格飞逃离天命的行动中，本想一起逃离天命的德丽莎为保护备天命回收的琪亚娜，回到天命。", font));
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("他人评价", titleFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(7);
        cell.setPaddingLeft(8f);
        cell.setPaddingRight(8f);
        cell.setPaddingBottom(16f);
        // 配置行间距
        cell.addElement(new Paragraph(24, "“即使离开了天命，您也依然是我们所尊敬的学园长。”——雷电芽衣\n" +
                "“虽然看起来很小，倒也有点本事。”——西琳 \n" +
                "“诶~德丽莎看起来小小的，意外地很能干嘛。”——萝莎莉娅·阿琳", font));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("其它", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("···"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(32f);
        cell.setColspan(7);
        table.addCell(cell);
        document.add(table);

        // ------------------------------------------------

        table = new PdfPTable(4);
        table.setSpacingBefore(32f);

        Font titleFont2 = new Font(bf, 14, Font.BOLD);
        titleFont2.setColor(new BaseColor(255, 255, 255));

        cell = new PdfPCell(new Phrase("账单", titleFont2));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(4);
        cell.setFixedHeight(32f);
        cell.setBackgroundColor(new BaseColor(96, 125, 139));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("日期", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("项目", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("金额", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("说明", titleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        LocalDateTime dt = LocalDateTime.now();
        for (int i = 0; i < 10; i++) {
            dt = dt.plusDays(1L);
            cell = new PdfPCell(new Phrase(dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("花呗还款", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("$100.0", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("——", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8f);
            table.addCell(cell);
        }
        document.add(table);

        // ------------------------------------------------

        document.newPage();
        document.add(new Paragraph("芒种\n\n" +
                "一想到你我就\n" +
                "wu~~~~\n" +
                "空恨别梦久\n" +
                "wu~~~~\n" +
                "烧去纸灰埋烟柳\n" +
                "\n" +
                "于鲜活的枝丫\n" +
                "凋零下的无暇\n" +
                "是收获谜底的代价\n" +
                "\n" +
                "余晖沾上 远行人的发\n" +
                "他洒下手中牵挂\n" +
                "于桥下\n" +
                "前世迟来者~~~（擦肩而过）\n" +
                "掌心刻~~~~~（来生记得）\n" +
                "你眼中烟波滴落一滴墨 wo~~~\n" +
                "若佛说~~~~~（无牵无挂）\n" +
                "放下执着~~~~~（无相无色）\n" +
                "我怎能 波澜不惊 去附和\n" +
                "\n" +
                "一想到你我就\n" +
                "wu~~~~~\n" +
                "恨情不寿 总于苦海囚\n" +
                "wu~~~~~\n" +
                "新翠徒留 落花影中游\n" +
                "wu~~~~~\n" +
                "相思无用 才笑山盟旧\n" +
                "wu~~~~~\n" +
                "谓我何求\n" +
                "\n" +
                "种一万朵莲花\n" +
                "在众生中发芽\n" +
                "等红尘一万种解答\n" +
                "\n" +
                "念珠落进 时间的泥沙\n" +
                "待 割舍诠释慈悲\n" +
                "的读法\n" +
                "\n" +
                "前世迟来者~~~（擦肩而过）\n" +
                "掌心刻~~~~~（来生记得）\n" +
                "你眼中烟波滴落一滴墨 wo~~~\n" +
                "若佛说~~~~~（无牵无挂）\n" +
                "放下执着~~~~~（无相无色）\n" +
                "我怎能 波澜不惊 去附和", font));


        document.close();
    }


    //创建单元格
    public static PdfPCell createCell(Element element) {
        PdfPCell cell = new PdfPCell();
        // 设置内容水平居中显示
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorderWidth(0);
        cell.addElement(element);
        return cell;
    }

    //创建单元格
    public static PdfPCell createCell2(PdfPCell cell) {
        // 边框颜色
        cell.setBorderColor(BaseColor.BLUE);
        // 设置背景颜色
        cell.setBackgroundColor(BaseColor.ORANGE);
        // 设置跨两行
        cell.setRowspan(2);
        // 设置距左边的距离
        cell.setPaddingLeft(10);
        // 设置高度
        cell.setFixedHeight(20);
        // 设置内容水平居中显示
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}