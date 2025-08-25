package com.simple_official_spring_template.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {
    public static String unUnicodeAndRemoveSpace(String s, String replacement, String acceptChars) {
        String resultStr = "";
        s = removeSpace(s.toLowerCase());
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        s = pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d").replace(" ", replacement).replace("&", replacement);
        for (int i=0;i<s.length();i++)
        {
            if (s.charAt(i)>=65 && s.charAt(i)<=122 || s.charAt(i)>=48 && s.charAt(i)<=57 || (acceptChars != null && acceptChars.indexOf(s.charAt(i)) != -1) || s.charAt(i) == replacement.charAt(0)) //returns true if both conditions returns true
            {
                resultStr += s.charAt(i);
            }
        }

        return resultStr;
    }

    public static String unUnicode(String s) {
        s = removeSpace(s);
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }

    public static String removeSpace(String s){
        s = s.trim();
        while (s.contains("  ")){
            s = s.replace("  "," ");
        }
        return s;
    }
}
