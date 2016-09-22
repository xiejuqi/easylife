package org.easylife.test.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
 * @Filename Student.java
 * 
 * @Description	测试Xtream
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月30日 下午12:12:00
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@XStreamAlias("student")
public class StudentWithAnnotation {
	
	@XStreamAlias("地址")
	@XStreamAsAttribute
	private String address;
	
	@XStreamAlias("email")
	@XStreamOmitField
	private String email;
	
	@XStreamAlias("id")
	private Integer id;
	
	@XStreamAlias("name")
	private String name;
	
	@XStreamConverter(SingleValueCalendarConverter.class)
	private Calendar birthday = new GregorianCalendar();
	
	@XStreamImplicit(itemFieldName = "lessons")
	List<Lesson> lessons;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
}
