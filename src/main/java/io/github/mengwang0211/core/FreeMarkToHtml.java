package io.github.mengwang0211.core;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;

/**
 * Free mark to html
 */
@Slf4j
public class FreeMarkToHtml {


    /**
     * 通过路径 和文件名称后去魔板
     *
     * @param templateFilePath template file path
     * @param templateFileName template file name
     * @return template template
     */
    public static Template getTemplate(String templateFilePath, String templateFileName) {
        try {
            Configuration cfg = new Configuration();
            cfg.setClassicCompatible(true);

            TemplateLoader templateLoader = new FileTemplateLoader(new File(
                    templateFilePath));
            cfg.setTemplateLoader(templateLoader);
            return cfg.getTemplate(templateFileName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * Freemark to html string
     *
     * @param templateFilePath template file path
     * @param templateFileName 加载的模板文件名
     * @param replaceData      replace data
     * @param outFile          生成指定文件
     * @return 成功 ，返回文件名；失败，返回null。
     */
    public static String freemarkToHtml(String templateFilePath, String templateFileName, Map<String, Object> replaceData,
                                        String outFile) {

        String path = null;
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            Template temp = getTemplate(templateFilePath, templateFileName);
            temp.setEncoding("UTF-8");
            temp.process(replaceData, out);
            path = outFile;

        } catch (IOException e) {
            log.error(e.getMessage());
            path = null;
        } catch (TemplateException e) {
            log.error(e.getMessage());
            path = null;
        } catch (NullPointerException e){
            log.error(e.getMessage());
            path = null;
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return path;
    }
}
