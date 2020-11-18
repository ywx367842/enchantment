package com.cesar.core.poi.read;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XWPFWordExtractorXwpfTest {

    /**
     * 通过XWPFWordExtractor访问XWPFDocument的内容
     *
     * @throws Exception
     */
    @Test
    public void testReadByExtractor() throws Exception {
        InputStream is = new FileInputStream("D:\\test\\word\\write.docx");
        XWPFDocument doc = new XWPFDocument(is);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        String text = extractor.getText();
        System.out.println(text);
        POIXMLProperties.CoreProperties coreProps = extractor.getCoreProperties();
        this.printCoreProperties(coreProps);
        this.close(is);
    }

    /**
     * 输出CoreProperties信息
     *
     * @param coreProps
     */
    private void printCoreProperties(POIXMLProperties.CoreProperties coreProps) {
        System.out.println("----------分类---------------");
        System.out.println(coreProps.getCategory());   //分类
        System.out.println("----------创建者---------------");
        System.out.println(coreProps.getCreator()); //创建者
        System.out.println("----------创建时间---------------");
        System.out.println(coreProps.getCreated()); //创建时间
        System.out.println("----------标题---------------");
        System.out.println(coreProps.getTitle());   //标题
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