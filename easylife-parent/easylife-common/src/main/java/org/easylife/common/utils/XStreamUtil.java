/**
 * 
 */
package org.easylife.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;



/**
 * 
 * @Filename XStreamUtil.java
 * 
 * @Description Object和XML互转
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月28日 上午9:49:04
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class XStreamUtil {

	/**
	 * 把对象转换成XML
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToXml(Object obj) {
		XStream xStream = new XStream(new DomDriver("utf-8",new NoNameCoder()));
		xStream.registerConverter(new DateConverter());
		xStream.processAnnotations(obj.getClass());
		return xStream.toXML(obj);

	}
	
	/**
	 * 把对象转换成XML
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToXml(Object obj, Object[] objs)
			throws Exception {
		XStream xStream = new XStream(new DomDriver());
		xStream.registerConverter(new DateConverter());
		if (objs != null && objs.length > 0) {
			for (Object obj1 : objs) {
				xStream.processAnnotations(obj1.getClass());
			}
		}
		return xStream.toXML(obj);
	}

	/**
	 * 把对象转换成XML
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static void objectToXml(Object obj, FileWriter writer)
			throws IOException {
		try {
			XStream xStream = new XStream(new DomDriver());
			xStream.toXML(obj, writer);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * xml 转换成对象
	 * 
	 * @param xml
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Object xmlToObject(String xml) throws FileNotFoundException {
		FileReader reader = null;
		try {
			reader = new FileReader(new File(
					"print_template_path", xml));
			XStream xStream = new XStream(new DomDriver());
			return xStream.fromXML(reader);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * xml 转换成对象
	 * 
	 * @param xml
	 * @return
	 */
	public static Object xmlToObject(String xml, Object[] objs)
			throws Exception {
		XStream xStream = new XStream(new DomDriver()){
			/**
			 *  解决xml中多节点问题
			 */
	        @Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn,String fieldName) {
						if (definedIn == Object.class) {
							try {
								return this.realClass(fieldName) != null;
							} catch (Exception e) {
								return false;
							}
						} else {
							return super.shouldSerializeMember(definedIn,fieldName);
						}
					}
                 };
	        }
		};
		xStream.registerConverter(new DateConverter());
		if (objs != null && objs.length > 0) {
			for (Object obj1 : objs) {
				xStream.processAnnotations(obj1.getClass());
			}
		}
		return xStream.fromXML(xml);
	}
	
	/**
	 * 构造XStream对象
	 * @return
	 */
	public static XStream newXStream(){
		XStream xStream = new XStream(new DomDriver()){
			/**
			 *  解决xml中多节点问题
			 */
	        @Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn,String fieldName) {
						if (definedIn == Object.class) {
							try {
								return this.realClass(fieldName) != null;
							} catch (Exception e) {
								return false;
							}
						} else {
							return super.shouldSerializeMember(definedIn,fieldName);
						}
					}
                 };
	        }
		};
		xStream.registerConverter(new DateConverter());
		return xStream;
	}
}
