package com.thinkgem.jeesite.modules.prho.utils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Double类型 并扩大10^num倍
	 */
	public static double toExpand(Object val,Integer num){
		return (toDouble(val))*Math.pow(10,num);
	}

	/**
	 * 转换为Double类型 并缩小10^num倍
	 */
	public static double toNarrow(Object val,Integer num){
		return (toDouble(val))/Math.pow(10,num);
	}
	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}

	/**
	 * 生成指定长度的数值编码字符串(前方0补齐)
	 * @param length 目标长度
	 * @param val 原先数值
	 * @return
	 */
	public static String formatCode(Integer length,Object val){
		return String.format("%0"+length+"d", toInteger(val));
	}

    public static Double formatDouble(Double d){
        DecimalFormat df   = new DecimalFormat("#.######");
        return toDouble(df.format(d));
    }
	/**
	 * 字符串转成字符串数组
	 * @param strs
	 * @return
	 */
	public static String[] getStringArray(String strs) {
		return strs.split(",");
	}





	/**
	 * 判断字符串是否为null及空�?
	 *
	 * @param str
	 * @return 如果字符串为null或�?空白,返回true,反之返回false
	 */
	public static boolean isNullOrBlank(String str) {
		if (null == str)
			return true;
		else if ("".equals(str.trim()))
			return true;
		else if("null".equals(str.trim()))
			return true;
		else
			return false;
	}

	public static boolean isNOtNullAndBlank(String str) {
		return !isNullOrBlank(str);
	}

	/**
	 * 首字母大�?
	 *
	 * @param s
	 * @return
	 */
	public static String firstCharToUpCase(String s) {
		// s = s.toLowerCase();
		s = s.substring(0, 1).toUpperCase() + s.substring(1);
		return s;
	}

	/**
	 * 首字母小�?
	 *
	 * @param s
	 * @return
	 */
	public static String firstCharToLowerCase(String s) {
		// s = s.toLowerCase();
		s = s.substring(0, 1).toLowerCase() + s.substring(1);
		return s;
	}

	/**
	 * 判断输入的字符串参数是否为空�?
	 *
	 * @param args
	 *            输入的字�?
	 * @return true/false
	 */
	public static boolean validateNull(String args) {
		if (args == null || args.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断输入的字符串参数是否为空或�?�?null"字符,如果�?就返回target参数,如果不是,就返回source参数�?
	 */
	public static String chanageNull(String source, String target) {
		if (source == null || source.length() == 0 || source.equalsIgnoreCase("null")) {
			return target;
		} else {
			return source;
		}
	}

	/**
	 * 过滤<, >,\n 字符的方法�?
	 *
	 * @param input
	 *            �?��过滤的字�?
	 * @return 完成过滤以后的字符串
	 */
	public static String filterHtml(String input) {
		if (input == null) {
			return null;
		}
		if (input.length() == 0) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;");
		input = input.replaceAll("\"", "&quot;");
		return input.replaceAll("\n", "<br>");
	}

	/**
	 * Object的toString方法
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != object) {
			if (object instanceof java.util.Date) {
				s = sdf.format(((java.util.Date) object).getTime());
			} else if (object instanceof java.sql.Date) {
				s = sdf.format(((java.sql.Date) object).getTime());
			} else if (object instanceof java.sql.Time) {
				s = sdf.format(((java.sql.Time) object).getTime());
			} else if (object instanceof java.sql.Timestamp) {
				s = sdf.format(((java.sql.Timestamp) object).getTime());
			} else if (object instanceof String) {
				s = object.toString();
			} else {
				s = object.toString() == "" ? "0" : object.toString();
			}
		} else {
			if (object instanceof java.util.Date) {
				s = sdf.format(new java.util.Date().getTime());
			} else if (object instanceof java.sql.Date) {
				s = sdf.format(new java.sql.Date(System.currentTimeMillis()).getTime());
			} else if (object instanceof java.sql.Time) {
				s = sdf.format(new java.sql.Time(System.currentTimeMillis()).getTime());
			} else if (object instanceof java.sql.Timestamp) {
				s = sdf.format(new java.sql.Timestamp(System.currentTimeMillis()).getTime());
			} else if (object instanceof String) {
				s = "";
			} else if (object instanceof Boolean) {
				s = "false";
			} else {
				s = "0";
			}
		}
		return s;
	}

	/**
	 * 处理ojbect对象，当为空是返回自定义参数
	 * @param object
	 * @param defaultStr
	 * @return
	 */
	public static String toString(Object object,String defaultStr) {
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != object) {
			if (object instanceof java.util.Date) {
				s = sdf.format(((java.util.Date) object).getTime());
			} else if (object instanceof java.sql.Date) {
				s = sdf.format(((java.sql.Date) object).getTime());
			} else if (object instanceof java.sql.Time) {
				s = sdf.format(((java.sql.Time) object).getTime());
			} else if (object instanceof java.sql.Timestamp) {
				s = sdf.format(((java.sql.Timestamp) object).getTime());
			} else if (object instanceof String) {
				s = object.toString();
			} else {
				s = object.toString() == "" ? "0" : object.toString();
			}
		} else {
			return defaultStr;
		}
		return s;
	}
	public static String valueOf(Object object) {
		return toString(object);
	}

	/**
	 * 获取字符串中 split 字符之前(不包split)的内
	 *
	 */
	public static String getSplitBefore(String sourceStr, int split) {
		return sourceStr.substring(0, sourceStr.lastIndexOf(split));
	}

	/**
	 * 获取字符串中split 字符 及其后面的内�?通常用于获取文件后缀"
	 *
	 */
	public static String getSplitWithAfter(String sourceStr, int split) {
		return sourceStr.substring(sourceStr.lastIndexOf(split));
	}

	/**
	 * 获取两个字符之间的内�?
	 *
	 */
	public static String getSplitsBetween(String sourceStr, int fSplit,
										  int sSplit) {
		return sourceStr.substring(sourceStr.lastIndexOf(fSplit) + 1, sourceStr
				.lastIndexOf(sSplit));
	}

	public static String getSelString(String s) {
		s = s.trim();
		String returnString = "%";
		for (int i = 0; i < s.length(); i++) {
			returnString = returnString + s.substring(i, i + 1) + "%";
		}
		return returnString;
	}

	public static String[] split(String s, String regex) {
		if (s.endsWith(regex))
			s = s.substring(0, s.length() - regex.length());
		if (s.startsWith(regex))
			s = s.substring(regex.length());
		return s.split(regex);
	}

	/**
	 * 从一个JSON对象取得Map
	 */
	@SuppressWarnings("unchecked")
	/*public static Map<String, Object> getMap4Json(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<String> keyIter = jsonObject.keys();

		String key;

		Object value;

		Map<String, Object> valueMap = new HashMap<String, Object>();

		while (keyIter.hasNext()) {

			key = (String) keyIter.next();

			value = jsonObject.get(key);

			valueMap.put(key, value);

		}

		return valueMap;
	}*/

	public static String regEnter(String s) {
		String reg = "[\n-\r]+";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(s);

		return m.replaceAll("<br>");
	}

	public static String urlForAndroid(String url) {
		if (isNOtNullAndBlank(url)) {
			String action = url.substring(0, url.indexOf("!"));
			String method = url.substring(url.indexOf("!") + 1, url.indexOf("."));
			return action + "!andr" + firstCharToUpCase(method) + ".action";
		} else {
			return "";
		}
	}

	public static String urlAddAndr(String url) {
		if (isNOtNullAndBlank(url)) {
			if (url.indexOf("?") > 0) {
				return url + "&andr=1";
			} else {
				return url + "?andr=1";
			}
		} else {
			return "";
		}
	}
	//格式字符串为long
	public static Long parseLong(Object lo) {
		if (null==lo) {
			return new Long(0);
		} else {
			return Long.parseLong(String.format("%s", lo));
		}
	}
	//格式字符串为Double
	public static double parseDouble(Object dou) {
		if (null==dou) {
			return Double.parseDouble(String.format("%.2f",0.00));
		} else {
			return Double.parseDouble(String.format("%.2f",dou));
		}
	}
	//格式字符串为int
	public static int parseInt(Object inter) {
		if (null==inter) {
			return new Integer(0);
		} else {
			return Integer.parseInt(String.format("%s", inter));
		}
	}

	public static String trimStart(String str, char startChar) {
		int len = str.length();//取得字符串的长度
		int index = 0;//预定义第一个非零字符串的位置

		char strs[] = str.toCharArray();// 将字符串转化成字符数组
		for (int i = 0; i < len; i++) {
			if (startChar == strs[i]) {
				index = i;// 找到非零字符串并跳出
				break;
			}
		}
		String strLast = str.substring(index);// 截取字符串
		return strLast;
	}

	//同步获取UUID,防止重复
	public static synchronized UUID GetGUID() {
		return UUID.randomUUID();
	}

	public static void main(String[] args) {
		String s = "ABC";
		System.out.println(urlForAndroid(s));
	}
}
