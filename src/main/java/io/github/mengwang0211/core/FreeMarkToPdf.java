package io.github.mengwang0211.core;

import com.itextpdf.text.DocumentException;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Free mark to pdf
 */
@Slf4j
public class FreeMarkToPdf {

    /**
     * Freemark to html string
     *
     * @param workPath         工作目录
     * @param templateFileName 加载的模板文件名
     * @param replaceData      replace data
     * @return 成功 ，返回文件名；失败，返回null。
     */
    public static String convert(String workPath, String templateFileName, Map<String, Object> replaceData) {
        Map<String, String> htmlFileName = FileNamer.nameFile("html");

        String fileName = htmlFileName.get("fileName");

        File path = new File(workPath);
        if (!path.exists()) {
            path.mkdir();
            log.debug("mkdir dir : {}", workPath);
        }

        String realHtml = new StringBuilder(workPath)
                .append("/").append(fileName).toString();
        File html = new File(realHtml);
        if (!html.exists()) {
            try {
                html.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        FreeMarkToHtml.freemarkToHtml(workPath, templateFileName, replaceData, realHtml);
        try {

            Map<String, String> pdfFileName = FileNamer.nameFile("pdf");
            String pdfName = pdfFileName.get("fileName");

            String realPDF = new StringBuilder(workPath)
                    .append("/").append(pdfName).toString();
            File pdf = new File(realPDF);
            if (!pdf.exists()) {
                try {
                    pdf.createNewFile();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }

            XHtml2Pdf.XHtml2Pdf(realHtml, realPDF);

            // del html file
            delFile(realHtml);

            // del xhtml file
            int i = realHtml.lastIndexOf(".html");
            String xhtml = null;
            xhtml = realHtml.substring(0, i) + ".xhtml";
            delFile(xhtml);
            return realPDF;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (DocumentException e) {
            log.error(e.getMessage());
        } catch (CssResolverException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * Del file *
     *
     * @param filePath file path
     */
    public static void delFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

}
