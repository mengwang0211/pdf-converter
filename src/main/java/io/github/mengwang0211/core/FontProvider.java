package io.github.mengwang0211.core;

import com.itextpdf.text.Font;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

/**
 * Font provider
 */
public class FontProvider extends XMLWorkerFontProvider {

    /**
     * Font provider
     */
    public FontProvider() {
        super(null, null);
    }

    @Override
    public Font getFont(final String fontname, String encoding, float size,
                        final int style) {

        String fntname = fontname;
        if (fntname == null) {
            fntname = "宋体";
        }
        return super.getFont(fntname, encoding, size, style);
    }
}
