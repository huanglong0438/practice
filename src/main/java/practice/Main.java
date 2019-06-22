package practice;

import sun.awt.CharsetString;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String string = new String("aaa://ds://d");
        String ste1 = new String("{{}}AS");
        boolean contains = string.contains("://");
        System.out.println("string contains: " + contains);

        String str = new String("hello");
        String str2 = new String("oola!");
        String str3 = str + str2;
        System.out.println(str3);

        int a = 2;
        int b = 3;
        int c = a > b ? a : b;
        System.out.println(c);
        String mystring = new String("ssdhsah");
        byte ptext[] = new byte[0];
        try {
            ptext = mystring.getBytes("UTF-8");
            String value = new String(ptext, "UTF-8");
            System.out.println(getEncoding(value));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
}
