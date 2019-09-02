package io.github.mengwang0211.core;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import io.github.mengwang0211.adapter.CNChunkCssApplier;

import java.io.*;

/**
 * X html 2 pdf
 */
public class XHtml2Pdf {

    /**
     * 转化方法
     *
     * @param html html文件输入路径(带文件名称)
     * @param pdf  pdf文件输出路径(带文件名称)
     * @throws FileNotFoundException file not found exception
     * @throws IOException           io exception
     * @throws DocumentException     document exception
     * @throws CssResolverException  css resolver exception
     */
    public static void XHtml2Pdf(String html, String pdf)
            throws FileNotFoundException, IOException, DocumentException,
            CssResolverException {

        int i = html.lastIndexOf(".html");
        String xhtml = null;
        xhtml = html.substring(0, i) + ".xhtml";
        xhtml = Html2Xhtml.html2Xhtml(html, xhtml);

        if (xhtml != null) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(pdf));
            document.open();
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFontSubstitute("lowagie", "garamond");
            fontProvider.setUseUnicode(true);
            // 使用我们的字体提供器，并将其设置为unicode字体样式
            CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
            cssAppliers.setChunkCssAplier(new CNChunkCssApplier());
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(
                    cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            CSSResolver cssResolver = XMLWorkerHelper.getInstance()
                    .getDefaultCssResolver(true);
            Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
                    new HtmlPipeline(htmlContext, new PdfWriterPipeline(
                            document, writer)));
            XMLWorker worker = new XMLWorker(pipeline, true);
            XMLParser p = new XMLParser(worker);

            File input = new File(xhtml);
            p.parse(new InputStreamReader(new FileInputStream(input), "UTF-8"));
            document.close();
        }

    }


}
