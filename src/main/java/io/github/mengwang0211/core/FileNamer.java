package io.github.mengwang0211.core;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * File namer
 */
public class FileNamer {

    /**
     * Name file string
     *
     * @param suffix suffix
     * @return the string
     */
    public static Map<String,String> nameFile(String suffix){
        String dNow = format(new Date(),"yyyyMMdd");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Map<String,String> map = new HashMap();
        map.put("filePath",dNow);
        String fileName = new StringBuilder(uuid).append(".").append(suffix).toString();
        map.put("fileName",fileName);
        return map;
    }

    /**
     * Format string
     *
     * @param date   date
     * @param format format
     * @return the string
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

}
