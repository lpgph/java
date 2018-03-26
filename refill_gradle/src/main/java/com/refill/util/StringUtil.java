package com.refill.util;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串解析
 *
 * @author 王天培QQ:78750478
 * @version 版本号：100-000-000<br/>
 *          创建日期：2012-03-15<br/>
 *          历史修订：<br/>
 */
public final class StringUtil {

    /**
     * 验证邮箱格式是否正确
     *
     * @param email 邮箱地址
     * @return 如果正确，则返回true，否则返回false
     */
    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    /**
     * 过滤html，将一些字符进行转义
     *
     * @param html 网页源码
     * @return 返回过滤后的html
     */
    public static String formatHTMLIn(String html) {
        html = html.replaceAll("&", "&amp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        html = html.replaceAll("\"", "&quot;");
        return html;
    }

    /**
     * 解析html
     *
     * @param html 网页源码
     * @return 返回解析过的html
     */
    public static String formatHTMLOut(String html) {
        html = html.replaceAll("&amp;", "&");
        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        html = html.replaceAll("&quot;", "\"");
        return html;
    }

    /**
     * 截取字符长度
     *
     * @param str    需截取的字符串
     * @param length 截取长度
     * @return 返回截取后的字符串
     */
    public static String subString(String str, int length) {
        if (isBlank(str))
            return "";
        if (str.getBytes().length <= length)
            return str;
        char ch[] = null;
        if (str.length() >= length)
            ch = str.substring(0, length).toCharArray();
        else
            ch = str.toCharArray();
        int readLen = 0;
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < ch.length; i++) {
            String c = String.valueOf(ch[i]);
            readLen += c.getBytes().length;
            if (readLen > length)
                return sb.toString();
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 验证长度
     *
     * @param str       需验证的字符串
     * @param minLength 字符串的最小长度
     * @param maxLength 字符串的最大长度
     * @return 如果验证通过，则返回true，否则返回false
     */
    public static boolean checkLength(String str, int minLength, int maxLength) {
        if (str != null) {
            int len = str.length();
            if (minLength == 0)
                return len <= maxLength;
            else if (maxLength == 0)
                return len >= minLength;
            else
                return (len >= minLength && len <= maxLength);
        }
        return false;
    }

    /**
     * 解码，将字符串解码成UTF-8编码
     *
     * @param str 需要解码的字符串
     * @return 返回解码后的字符串
     */
    public static String decodeStringByUTF8(String str) {
        if (isBlank(str))
            return "";
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    /**
     * 转码，将字符串转码成UTF-8编码
     *
     * @param str 需要转码的字符串
     * @return 返回转码后的字符串
     */
    public static String encodeStringByUTF8(String str) {
        if (isBlank(str))
            return "";
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    /**
     * 程序内部字符串转码，将ISO-8859-1转换成utf-8
     *
     * @param str 需要转码的字符串
     * @return 返回utf8编码字符串
     */
    public static String isoToUTF8(String str) {
        if (isBlank(str))
            return "";
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    /**
     * 程序内部字符串转码，将utf-8转换成ISO-8859-1
     *
     * @param str 需要转码的字符串
     * @return 返回转码后的字符串
     */
    public static String utf8ToISO(String str) {
        if (isBlank(str))
            return "";
        try {
            return new String(str.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    /**
     * 程序内部字符串转码，将utf-8转换成gb2312
     *
     * @param str 需要转码的字符串
     * @return 返回转码后的字符串
     */
    public static String utf8Togb2312(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '+':
                    sb.append(' ');
                    break;
                case '%':
                    try {
                        sb.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                    i += 2;
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        // Undo conversion to external encoding
        String result = sb.toString();
        String res = null;
        try {
            byte[] inputBytes = result.getBytes("8859_1");
            res = new String(inputBytes, "UTF-8");
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * 格式化时间，转换成字符串
     *
     * @param date    要转换格式的时间
     * @param pattern 要转换成的时间格式 eg: yy/MM/dd HH:mm
     * @return 返回转换后的时间字符串
     */
    public static String getFormatDateStr(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 判断对象是否为空,对数组无效
     *
     * @param obj object
     * @return 为空返回true，否则返回false
     */
    public static boolean isBlank(Object obj) {
        //如果是字符串
        if (obj instanceof String) obj = ((String) obj).trim();
        return Objects.hashCode(obj) == 0;
    }

//	/**
//	 * 字段串是否为空
//	 *
//	 * @param str
//	 *            要判断是否为空的字符串
//	 * @return 如果为空，则返回true，否则返回false
//	 */
//	public static boolean isBlank(String str) {
//		return (str == null || str.trim().equals("") || str.length() < 0);
//	}
//
//	/**
//	 * 对象是否为空
//	 *
//	 * @param str
//	 *            object对象
//	 * @return 如果为空，则返回true，否则返回false
//	 */
//	public static boolean isBlank(Object str) {
//		return (str == null || str.toString().trim().equals("") || str.toString().length() < 0);
//	}
//
//	/**
//	 * 判断数组是否为空
//	 *
//	 * @param args
//	 * @return 数组为空返回true,不为空返回false
//	 */
//	public static boolean isBlank(String[] args) {
//		return args == null || args.length == 0 ? true : false;
//	}
//
//	/**
//	 * 判断集合,是否为空
//	 *
//	 * @param args
//	 * @return 集合为空返回true,不为空返回false
//	 */
//	public static boolean isBlank(Collection args) {
//		return args == null || args.isEmpty() ? true : false;
//	}
//
//	/**
//	 * 判断散列集,是否为空
//	 *
//	 * @param args
//	 * @return map为空返回true,不为空返回false
//	 */
//	public static boolean isBlank(Map args) {
//		return args == null || args.isEmpty() ? true : false;
//	}


    /**
     * 判断字符串是否是数字类型
     *
     * @param str 字符串
     * @return 如果是数字类型，则返回true，否则返回false
     */
    public static boolean isInteger(String str) {
        if (isBlank(str))
            return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断对象是否是数字类型
     *
     * @param str 对象
     * @return 如果是数字类型，则返回true，否则返回false
     */
    public static boolean isInteger(Object str) {
        String temp = str + "";
        if (isBlank(str))
            return false;
        try {
            Integer.parseInt(temp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 当传入的值为NULL是将其转化为String
     *
     * @param str 字符串
     * @return 返回字符串
     */
    public static String null2String(String str) {
        if (str == null || str.equals("") || str.trim().length() == 0) {
            return str = "";
        }
        return str;
    }

    /**
     * 将String型转换成Int型并判断String是否是NULL
     *
     * @param str 字符串
     * @return 返回数字
     */
    public static int string2Int(String str) {
        int valueInt = 0;
        if (!StringUtil.isBlank(str)) {
            valueInt = Integer.parseInt(str);
        }
        return valueInt;
    }

    /**
     * 变量形态转换 int型转为String型
     *
     * @param comment 整型数字
     * @return 返回字符串
     */
    public static String int2String(int comment) {
        String srt = "";
        srt = Integer.toString(comment);
        return srt;
    }

    /**
     * 判断是否是大于0的参数
     *
     * @param str 字符串参数
     * @return 如果是大于，则返回true，否则返回false
     */
    public static boolean isMaxZeroInteger(Object str) {
        if (isBlank(str))
            return false;
        try {
            int temp = Integer.parseInt(str.toString());
            return temp > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否是大于等于0的参数
     *
     * @param str 字符串参数
     * @return 如果是大于，则返回true，否则返回false
     */
    public static boolean isGreaterEqualZeroInteger(Object str) {
        if (isBlank(str))
            return false;
        try {
            int temp = Integer.parseInt(str.toString());
            return temp >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断长整型
     *
     * @param str 字符串
     * @return 如果是长整型，则返回true，否则返回false
     */
    public static boolean isLong(String str) {
        if (isBlank(str))
            return false;
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断长整型数组
     *
     * @param str String数组
     * @return 如果是长整型，则返回true，否则返回false
     */
    public static boolean isLongs(String str[]) {
        try {
            for (int i = 0; i < str.length; i++)
                Long.parseLong(str[i]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断数字数组
     *
     * @param str String数组
     * @return 如果是数字，则返回true，否则返回false
     */
    public static boolean isIntegers(String str[]) {
        try {
            for (int i = 0; i < str.length; i++)
                Integer.parseInt(str[i]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断数字数组
     *
     * @param str String数组
     * @return 如果是数组，则返回true，否则返回false
     */
    public static boolean isDoubles(String str[]) {
        try {
            for (int i = 0; i < str.length; i++)
                Double.parseDouble(str[i]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * md5加密
     *
     * @param plainText 文本内容
     * @return 返回加密后的字符串
     */
    public static String Md5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i = 0;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 字符串转长整型数组
     *
     * @param str String字符串
     * @return 返回长整型数组
     */
    public static long[] stringsToLongs(String str[]) {
        long lon[] = new long[str.length];
        for (int i = 0; i < lon.length; i++)
            lon[i] = Long.parseLong(str[i]);
        return lon;
    }

    /**
     * 字符串转数字型数组
     *
     * @param str String字符串
     * @return 返回数字型数组
     */
    public static Integer[] stringsToIntegers(String str[]) {
        Integer array[] = new Integer[str.length];
        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt(str[i]);
        return array;
    }

    /**
     * 字符串转数字型数组
     *
     * @param str String字符串
     * @return 返回数字型数组
     */
    public static int[] stringsToInts(String str[]) {
        int array[] = new int[str.length];
        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt(str[i]);
        return array;
    }

    /**
     * 除去字符串数组中相同的值
     *
     * @param str String数组
     * @return 返回除去相同值后的数组
     */
    @SuppressWarnings("unchecked")
    public static String[] delLopStrings(String str[]) {
        @SuppressWarnings("rawtypes")
        ArrayList list = new ArrayList();
        for (int i = 0; i < str.length; i++) {
            if (!list.contains(str[i]))
                list.add(str[i]);
        }
        String array[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = (String) list.get(i);
        }
        return array;
    }

    /**
     * 字符串转布尔型数组
     *
     * @param str 字符串数组
     * @return 返回布尔型数组
     */
    public static boolean[] stringsToBooleans(String str[]) {
        boolean array[] = new boolean[str.length];
        for (int i = 0; i < array.length; i++)
            array[i] = Boolean.parseBoolean(str[i]);
        return array;
    }

    /**
     * 判断字符串是否是日期类型
     *
     * @param str 字符串
     * @return 如果是日期类型，则返回true，否则返回false
     */
    public static boolean isTimestamp(String str) {
        try {
            @SuppressWarnings("unused")
            Date d = java.sql.Date.valueOf(str);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 获取当前页
     *
     * @param pageNo 页数
     * @return 返回当前页数
     */
    public static int getPageStart(String pageNo) {
        int istart = 1;
        if (isBlank(pageNo)) {
            return istart;
        }
        try {
            istart = Integer.parseInt(pageNo) < 0 ? istart : Integer.parseInt(pageNo);
        } catch (NumberFormatException e) {
        }
        return istart;
    }

    /**
     * 获取时间戳
     *
     * @return 返回获取当前系统时间字符串
     */
    public static String getDateSimpleStr() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 字符串TO长整型
     *
     * @param args String数组
     * @return 返回长整型数组
     */
    public static Long[] StrToLong(String[] args) {
        if (args == null)
            return null;
        Long[] _ref = new Long[args.length];
        for (int i = 0; i < args.length; i++) {
            _ref[i] = new Long(args[i]);
        }
        return _ref;
    }

    /**
     * 字符串TO整型
     *
     * @param args String数组
     * @return 返回整型数组
     */
    public static Integer[] StrToInteger(String[] args) {
        if (args == null)
            return null;
        Integer[] _ref = new Integer[args.length];
        for (int i = 0; i < args.length; i++) {
            _ref[i] = new Integer(args[i]);
        }
        return _ref;
    }

    /**
     * 获取日期字符串
     *
     * @param day    日期
     * @param fomStr 日期格式
     * @return 返回日期字符串
     */
    public static String getSimpleDateStr(Date day, String fomStr) {
        SimpleDateFormat format = new SimpleDateFormat(fomStr);
        return format.format(day);
    }

    /**
     * 字符串返回时间
     *
     * @param str 字符串类型的时间
     * @return 返回时间
     */
    public static Date getDateForStr(String str) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(str);
        return sqlDate;
    }

    /**
     * 字符串返回时间
     *
     * @param str 字符串类型的时间
     * @return 返回时间
     */
    public static Date getDateForStr(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 组合ip
     *
     * @param ip ip的字节数组形式
     * @return 返回字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
        StringBuilder sb = new StringBuilder();
        sb.delete(0, sb.length());
        sb.append(ip[0] & 0xFF);
        sb.append('.');
        sb.append(ip[1] & 0xFF);
        sb.append('.');
        sb.append(ip[2] & 0xFF);
        sb.append('.');
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }

    /**
     * 判断IP是否相等
     *
     * @param ip1 IP的字节数组形式
     * @param ip2 IP的字节数组形式
     * @return 如果相等，则返回true，否则返回false
     */
    public static boolean isIpEquals(byte[] ip1, byte[] ip2) {
        return (ip1[0] == ip2[0] && ip1[1] == ip2[1] && ip1[2] == ip2[2] && ip1[3] == ip2[3]);
    }

    /**
     * 根据某种编码方式将字节数组转换成字符串
     *
     * @param b        字节数组
     * @param offset   要转换的起始位置
     * @param len      要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }

    /**
     * 字符转换二进制数据
     *
     * @param src 字节数组
     * @return 返回字符串
     */
    public static String stringToBinary(byte[] src) {
        StringBuffer sb = new StringBuffer();
        byte[][] des = new byte[src.length][16];
        for (int i = 0; i < src.length; i++)
            for (int j = 0; j < 16; j++)
                des[i][j] = (byte) ((src[i] >> j) & 0x1);

        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < 16; j++)
                sb.append(des[i][j]);
        }
        return sb.toString();
    }

    /**
     * 生成随机数
     *
     * @param len 随机数长度
     * @return 返回随机数
     */
    public static String randomNumber(int len) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(Math.abs(random.nextInt()) % 10);
        }
        return sb.toString();
    }

    /**
     * 系统时间秒
     *
     * @return 返回系统时间
     */
    public static String timeForString() {
        Long l = System.currentTimeMillis();
        return String.valueOf(Math.abs(l.intValue()));
    }

    /**
     * 获取参数
     *
     * @param str 字符串
     * @return 如果字符串为null 则返回一个空字符串
     */
    public static String getParString(String str) {
        if (StringUtil.isBlank(str))
            return "";
        return str;
    }

    /**
     * 判断是否是中文字符
     *
     * @param chChar 字符
     * @return 如果中文字符，则返回true，否则返回false
     */
    public static boolean isChinese(char chChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(chChar);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 验证手机号码
     *
     * @param phoneNumber 手机号码
     * @return 如果是手机号，则返回true，否则返回false
     */
    public static boolean isMobile(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        String pattern = "^[1][1-8][0-9]{9}";
        return phoneNumber.matches(pattern);
    }

    public static String formatResource(Object[] info, String require) {
        require = require.replaceAll("\'", "\"");
        String result = MessageFormat.format(require, info);
        return result.replaceAll("\"", "\'");
    }

    /**
     * 计算两个时间间隔天数
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 返回间隔天数
     */
    public static int getDaysBetween(Calendar beginDate, Calendar endDate) {
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int days = endDate.get(Calendar.DAY_OF_YEAR) - beginDate.get(Calendar.DAY_OF_YEAR);
        int y2 = endDate.get(Calendar.YEAR);
        if (beginDate.get(Calendar.YEAR) != y2) {
            beginDate = (Calendar) beginDate.clone();
            do {
                days += beginDate.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                beginDate.add(Calendar.YEAR, 1);
            } while (beginDate.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 读取文件后缀名称
     *
     * @param filePath 文件路径 格式如:/../a.txt
     * @return 返回文件后缀名
     */
    public static String getFileFix(String filePath) {
        String temp = "";
        if (filePath != null) {
            temp = filePath.substring(filePath.indexOf("."), filePath.length());
        }
        return temp;
    }

    /**
     * 将数据流转换成字符串
     *
     * @param dataFlow 数据流
     * @return 返回字符串
     */
    public static String convertStreamToString(InputStream dataFlow) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(dataFlow));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataFlow.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();

    }

    /**
     * 检测字符串,处理utf-8的4个字节的问题
     *
     * @param str 需要处理的字符串
     * @return 返回处理后的字符串
     */
    public static String checkStr(String str) {
        String s = null;
        char[] cc = str.toCharArray();
        for (int i = 0; i < cc.length; i++) {
            boolean b = isValidChar(cc[i]);
            if (!b)
                cc[i] = ' ';
        }
        s = String.valueOf(cc);
        return s;
    }

    /**
     * 判断是否是有效的中文字
     */
    /**
     * @param ch 字符
     * @return 如果是有效中文，则返回true，否则返回false
     */
    private static boolean isValidChar(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
            return true;
        if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
            return true;// 简体中文汉字编码
        return false;
    }

    /**
     * 除去字符窜中重复的字符
     *
     * @param content 原始内容
     * @param target  重复内容
     * @return 返回除去后的字符串
     */
    public static String removeRepeatStr(String content, String target) {
        StringBuffer sb = new StringBuffer(content);
        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.substring(i, i + target.length()).equals(target)
                    && sb.substring(i, i + target.length()).equals(sb.substring(i + 1, i + target.length() + 1))) {
                sb.delete(i, i + target.length());
                if (i + target.length() + 1 > sb.length()) {
                    break;
                } else {
                    i--;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 验证邮箱格式
     *
     * @param email 邮箱
     * @return 如果是邮箱，则返回true，否则返回false
     */
    public static Boolean isEmail(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 组织url 的get请求地址
     *
     * @param url  原地址
     * @param parm 参数 推荐格式:参数=值
     * @return 饭胡新的地址
     */
    public static String buildUrl(String url, String parm) {
        if (url.indexOf("?") > 0) {
            return url += "&" + parm;
        } else {
            return url += "?" + parm;
        }
    }

    /**
     * 组织path路径, 例如:buildPath(a,b,c); 返回:a/b/c
     *
     * @param params 所有对象
     * @return 返回新的路径地址
     */
    public static String buildPath(Object... params) {
        String temp = "";
        for (Object o : params) {
            temp += File.separator + o;
        }
        return temp;
    }

    /**
     * 组织url 的get请求地址
     *
     * @param url   原地址
     * @param parms 参数集合 格式:key参数=值value
     * @return 返回新的地址
     */
    public static String buildUrl(String url, Map<String, String> parms) {
        Iterator<String> key = parms.keySet().iterator();
        String paramsStr = "";
        while (key.hasNext()) {
            Object temp = key.next();
            if (isBlank(parms.get(temp))) {
                continue;
            }
            if (paramsStr != "") {
                paramsStr += "&";
            }
            paramsStr += (temp + "=" + parms.get(temp));
        }

        if (paramsStr != "") {
            if (url.indexOf("?") > 0) {
                return url += "&" + paramsStr;
            } else {
                return url += "?" + paramsStr;
            }
        }
        return url;
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map 需要排序的Map
     * @return 返回排序后的Map
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 使用 Map按value进行排序
     *
     * @param map 需要排序的Map
     * @return 返回排序后的Map
     */
    public static Map<String, String> sortMapByValue(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Entry<String, String>> entryList = new ArrayList<Entry<String, String>>(map.entrySet());
        Collections.sort(entryList, new MapValueComparator());
        Iterator<Entry<String, String>> iter = entryList.iterator();
        Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * 验证字符串是否符合单号规则， 申通单号由12位数字组成，常见以268**、368**、58**等开头
     * EMS单号由13位字母和数字组成，开头和结尾二位是字母，中间是数字 顺丰单号由12位数字组成，常见以电话区号后三位开头
     * 圆通单号由10位字母数字组成，常见以1*、2*、6*、8*、D*及V*等开头 中通单号由12位数字组成，常见以2008**、6**、010等开头
     * 韵达单号由13位数字组成，常见以10*、12*、19*等开头 天天单号由14位数字组成，常见以6**、5*、00*等开头
     * 汇通快递查询单号由13位数字编码组成，常见以0*或者B*、H*开头 速尔的快递单号由12位数字组成的
     * 德邦的货运单号现在是以1或2开头的8位数字组成 宅急送单号由10位数字组成，常见以7**、6**、5**等开头
     *
     * @param str 需要验证的字符串
     * @return 如果是单号，则返回true，否则返回false
     */
    public static boolean isExpressNo(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        // 根据长度来判断
        if (str.length() == 13) { // 邮政EMS
            return true;
        } else if (str.length() == 12) { // 中通
            return true;
        }
        return true;
    }

    /**
     * 将传入的字符串转换为double类型</br>
     * \ 若为null则返回 0
     *
     * @param str 需要转换成double类型的字符串
     * @return 转换之后的数据
     */
    public static Double toDouble(String str) {
        if (str == null || str.trim().equals("") || str.length() < 0) {
            return 0.00;
        } else {
            return Double.valueOf(str);
        }
    }

    /**
     * 生成一段带有unix时间格式的字符串,一般当订单编号使用</br>
     * 生成规则:begin(自定义开头字符串)+格式化当前时间(当前时间的秒数)+lenth(自定义随机数的位数)</br>
     *
     * @param begin  开始字符串
     * @param length 后面接的随机字符串的长度
     * @return 生成的编号
     */
    public static String getRandomNum(String begin, int length) {
        Random random = new Random();
        String[] str = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String result = "";
        for (int i = 0; i < length; i++) {
            result += str[random.nextInt(str.length)];
        }
        return begin + String.valueOf(System.currentTimeMillis()).substring(1, 10) + result;
    }

    /**
     * 获取行错误，适用于erp错误集，返回map
     *
     * @param index 错误行索引
     * @param Code  错误码
     * @param name  对象的get方法，如:materials.getMaterialsId()
     * @return 错误的map集合
     */
    public static Map<String, String> getErrorMap(int index, String code, String name) {
        if (StringUtil.isBlank(index) || StringUtil.isBlank(code) || StringUtil.isBlank(name)) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("index", index + "");
        map.put("code", code);
        map.put("name", name);
        return map;
    }

    /**
     * 将父类的值传递给子类
     *
     * @param father
     * @param child
     */
    public static boolean faterToChild(Object father, Object child) {
        //检验child是否是father的子类
        if (!(child.getClass().getSuperclass() == father.getClass())) {
            return false;
        }
        //获取父类中的所有字段
        Field[] faterFieldArray = father.getClass().getDeclaredFields();
        for (Field field : faterFieldArray) {
            //获取父类的get方法
            Object obj;
            try {
                field.setAccessible(true);//取消java语言访问检查
                obj = field.get(father);//获取父类在该字段上的值
                //取出属性值
                field.set(child, obj);//设置子类的属性
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是大于0的double类型参数
     *
     * @param str 字符串参数
     * @return 如果是大于，则返回true，否则返回false
     */
    public static boolean isMaxZeroDouble(Double str) {
        if (isBlank(str))
            return false;
        try {

            //转换传入的数组
            BigDecimal _str = new BigDecimal(str);
            BigDecimal zero = new BigDecimal(0);
            //比较是否大于0,如果大于0,返回1,如果小于0,返回0
            int result = _str.compareTo(zero);
            //判断
            if (!StringUtil.isMaxZeroInteger(result)) {
                return false;
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 去除字符串中所有空格
     *
     * @param str 需要修改的字符串
     * @return afterStr 去除空格后的字符串
     */
    public static String trimAllBlank(String str) {
        //验证字符串是否为空
        if (isBlank(str)) {
            return "";
        }
        String afterStr = "";
        try {
            //删除字符串中的所有空格
            afterStr = str.replaceAll("\\s*", "");
        } catch (Exception e) {
            return afterStr;
        }
        return afterStr;
    }

    /**
     * 比较str与str1的大小
     *
     * @param str double类型数据
     * @param str1 double类型数据
     * @return 如果是大于，则返回1，等于返回0,小于返回-1
     */
    public static int comparativeDouble(double str,double str1) {

        BigDecimal _str = new BigDecimal(str);
        BigDecimal _str1 = new BigDecimal(str1);
        //比较str与str1,如果大于,返回1,如果小于,返回0
        int result = _str.compareTo(_str1);
        return result;

    }

}

class MapKeyComparator implements Comparator<String> {
    /**
     * 根据字典排序比较字符串
     *
     * @param str1 需要比较的字符串1
     * @param str2 需要比较的字符串2
     * @return 为0相等，为负数则Str1< str2, 大于0 则str1>str2
     */
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}

class MapValueComparator implements Comparator<Entry<String, String>> {
    /**
     * 根据字典排序比较Map
     *
     * @param str1 需要比较的Map1
     * @param str2 需要比较的Map2
     * @return 为0相等，为负数则Str1< str2, 大于0 则str1>str2
     */
    public int compare(Entry<String, String> str1, Entry<String, String> str2) {
        return str1.getValue().compareTo(str2.getValue());
    }
}
