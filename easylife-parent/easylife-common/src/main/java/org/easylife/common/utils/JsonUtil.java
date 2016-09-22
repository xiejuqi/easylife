package org.easylife.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 
 * @Filename JsonUtil.java
 * 
 * @Description	Json工具类
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月18日 上午10:30:29
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class JsonUtil {

	public JsonUtil() {

	}

	/**
	 * @Title: toJavaObject
	 * @Description: TODO从json字符串中解析出java对象
	 * @param
	 * @param jsonStr
	 *            json格式
	 * @param
	 * @param clazz
	 *            类
	 * @param
	 * @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public static Object toJavaObject(String json, Class<?> clazz) {

		return JSON.toJavaObject(JSON.parseObject(json), clazz);
	}
	

	/**
	 * TODO从json字符串中解析出List对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static List<?> jsonToList(String json, Class<?> clazz) {
		return JSON.parseArray(json, clazz);
	}

	/**
	 * 
	 * @Title: javaObjectToJson
	 * @Description: TODO 对象转化为JSON
	 * @param
	 * @param object
	 *            转化对象
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String objectToJSON(Object object) {
		return JSON.toJSON(object).toString();
	}

	/**
	 * 
	 * @Title: objectToJSON
	 * @Description: TODO 对象转化为JSON
	 * @param
	 * @param object
	 *            转化对象
	 * @param
	 * @param mapping
	 *            过滤掉的字段
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String objectToJSON(Object object, ParserConfig mapping) {
		return JSON.toJSON(object, mapping).toString();
	}
}
