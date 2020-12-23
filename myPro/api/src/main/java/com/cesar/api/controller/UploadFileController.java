package com.cesar.api.controller;

import com.cesar.core.common.HttpUtils;
import com.cesar.core.common.WordToPdf;
import com.cesar.core.common.entity.UploadFileStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping(value = "/")
public class UploadFileController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jacob.localStorePath}")
    private String path;

    @Value("${jacob.cmsServerUrl}")
    private String cmsServerUrl;

    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    public String upload(HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String remoteAddr = request.getRemoteAddr();
        System.out.println(requestURI);
        System.out.println(requestURL);
        System.out.println(remoteAddr);

        ServletInputStream sis = null;
        FileOutputStream fos = null;
        // 文件名
        String filename = request.getHeader("fileName");
        // 文件类型，例如：jpg、png、pdf...
        String fileType = request.getHeader("fileType");
        // 存储路径
        String filePath = request.getHeader("filePath");
        File file = new File(path + filename + "." + fileType);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            sis = request.getInputStream();
            fos = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int len = 0;
            while ((len = sis.read(content)) > -1) {
                fos.write(content, 0, len);
            }
            fos.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        } finally {
            try {
                if (sis != null) {
                    sis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //转化pdf
        WordToPdf d = new WordToPdf();
        String pdfPath = path + filename + ".pdf";
        d.wordToPDF(path + filename + "." + fileType, pdfPath);

        //上传文件至应用服务器
        // 要上传的文件
        File pdfFile = new File(pdfPath);
        UploadFileStatus fileStatus = new UploadFileStatus();
        // 上传到服务器后的文件名
        fileStatus.setFileName(filename);
        // 上传到服务器的哪个位置
        fileStatus.setFilePath(filePath);
        // 文件的大小
        fileStatus.setFileSize(file.length());
        // 文件的类型
        fileStatus.setFileType("static/pdf");
        try {
            fileStatus.setInputStream(new FileInputStream(new File(path + filename + ".pdf")));
        } catch (FileNotFoundException e) {
        }
        String result = HttpUtils.postFile(cmsServerUrl, fileStatus);
        System.out.println(result);
        return filePath + filename + "." + fileType;
    }
}