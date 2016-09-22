package org.easylife.test.uc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.easylife.common.utils.XStreamUtil;
import org.easylife.test.domain.Lesson;
import org.easylife.test.domain.Student;
import org.easylife.test.domain.StudentWithAnnotation;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @Filename XtreamTestCase.java
 * 
 * @Description
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月30日 下午12:08:03
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class XtreamTestCase {

	@Test
	@Ignore
	public void test1() {

		Student student = generateStudent();

		XStream xstream = new XStream();

		/** 根据对象中的注解生成XML,没有注解默认使用属性名和全限定类名 */
		xstream.processAnnotations(Student.class);

		/** 东8区时间 */
		xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null, TimeZone.getTimeZone("GMT+8")));

		/** 设置引用ID */
		/* xstream.setMode(XStream.ID_REFERENCES); */

		/** 为某个类起别名 */
		/* xstream.alias("aaa", Student.class); */

		/** 为字段起别名 */
		/* xstream.aliasField("bbb", Student.class, "address"); */

		/** 将某个属性作为XML属性，并且为之起别名 */
		/* xstream.aliasAttribute(Student.class, "address", "ccc"); */

		/** 忽略这个list节点元素 */
		xstream.addImplicitCollection(Student.class, "lessons");

		/** 将属性写到XML头中 */
		xstream.useAttributeFor(Student.class, "address");

		String xml = xstream.toXML(student);

		System.out.println("xml=" + xml);

		System.out.println(XStreamUtil.objectToXml(student));

	}

	/**
	 * 使用注解
	 */
	@Test
	@Ignore
	public void test2() {

		StudentWithAnnotation student = generateStudent2();

		XStream xstream = new XStream();

		/** 根据对象中的注解生成XML,没有注解默认使用属性名和全限定类名 */
		xstream.processAnnotations(StudentWithAnnotation.class);

		String xml = xstream.toXML(student);

		System.out.println("xml=" + xml);

		System.out.println(XStreamUtil.objectToXml(student));

	}

	/**
	 * 转换Map对象
	 */
	@Test
	@Ignore
	public void writeMap2XML() {

		XStream xstream = new XStream();

		Map<String, Student> map = generateMap();

		xstream.alias("student", Student.class);

		xstream.alias("key", String.class);

		xstream.useAttributeFor(Student.class, "id");

		xstream.useAttributeFor("birthday", String.class);

		System.out.println(xstream.toXML(map));

	}

	/**
	 * 流操作
	 */
	@Test
	@Ignore
	public void writeXML4OutStream() {

		try {

			XStream xstream = new XStream();

			ObjectOutputStream out = xstream.createObjectOutputStream(System.out);

			Student stu = new Student();

			stu.setName("jack");

			out.writeObject(stu);

			out.write(22);// byte

			out.writeBoolean(true);

			out.writeFloat(22.f);

			out.writeUTF("hello");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() throws Exception {

		XStream xStream = new XStream(new DomDriver("utf-8",new NoNameCoder()));
		xStream.registerConverter(new DateConverter());
		
		xStream.processAnnotations(Student.class);
		xStream.alias("student", Student.class);

		String xml = "<student>" + "<address>北京市朝阳区鹏润大厦</address>" + "<email>497049106@qq.com</email>" + "<id>1</id>"
				+ "<name>解巨琦</name>" + "<birthday>2016-05-30 06:42:12.681 UTC</birthday>" + "<lessons>" + "<lesson>"
				+ "<id>1</id>" + "<lessoName>语文</lessoName>" + "</lesson>" + "<lesson>" + "<id>2</id>"
				+ "<lessoName>数学</lessoName>" + "</lesson>" + "</lessons>" + "</student>";

		String xml2 = "<?xml version="+"'1.0'"+" encoding="+"'utf-8'"+"?>"+"<student>" + "<address>北京市朝阳区鹏润大厦</address>" + "<email>497049106@qq.com</email>" + "<id>1</id>"
				+ "<name>解巨琦</name>" + "<birthday>2016-05-30 06:51:32.135 UTC</birthday>" + "<lessons>" + "<lesson>"
				+ "<id>1</id>" + "<lessoName>语文</lessoName>" + "</lesson>" + "</lessons>" + "</student>";

		Student stu = (Student) xStream.fromXML(xml2);
		
		Student stu1 = (Student) XStreamUtil.xmlToObject(xml2, new Object[] { new Student() });
		
	}

	private Map generateMap() {
		Map<String, Student> map = new HashMap<String, Student>();

		Student bean = generateStudent();

		map.put("No.1", bean);// put

		bean = new Student();

		bean.setAddress("china");

		bean.setEmail("tom@125.com");

		bean.setId(2);

		bean.setName("tom");

		bean.setBirthday(new Date());

		map.put("No.2", bean);// put

		bean = new Student();

		bean.setName("jack");

		map.put("No.3", bean);// put

		return map;
	}

	private Student generateStudent() {

		Student student = new Student();

		student.setAddress("北京市朝阳区鹏润大厦");
		student.setBirthday(new Date());
		student.setEmail("497049106@qq.com");
		student.setId(1);
		student.setName("解巨琦");

		List<Lesson> lessons = new ArrayList<Lesson>();

		Lesson lesson1 = new Lesson();
		lesson1.setId(1);
		lesson1.setLessoName("语文");

		Lesson lesson2 = new Lesson();
		lesson2.setId(2);
		lesson2.setLessoName("数学");

		lessons.add(lesson1);
		lessons.add(lesson2);

		student.setLessons(lessons);

		return student;
	}

	private StudentWithAnnotation generateStudent2() {

		StudentWithAnnotation student = new StudentWithAnnotation();

		student.setAddress("北京市朝阳区鹏润大厦");
		student.setEmail("497049106@qq.com");
		student.setId(1);
		student.setName("解巨琦");

		List<Lesson> lessons = new ArrayList<Lesson>();

		Lesson lesson1 = new Lesson();
		lesson1.setId(1);
		lesson1.setLessoName("语文");

		Lesson lesson2 = new Lesson();
		lesson2.setId(2);
		lesson2.setLessoName("数学");

		lessons.add(lesson1);
		lessons.add(lesson2);

		student.setLessons(lessons);

		return student;
	}
}
