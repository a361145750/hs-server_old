package com.hs.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by work_tl on 2016/4/6.
 */
public class StringUtil extends StringUtils {

    public static final char UNDERLINE='_';

    /** 驼峰字符转下划线 */
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /** 下划线字符转驼峰 */
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        param = param.toLowerCase();
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
