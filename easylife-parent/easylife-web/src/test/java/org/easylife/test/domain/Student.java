package org.easylife.test.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
public class Student {
	
	@XStreamAlias("address")
	private String address;
	
	@XStreamAlias("email")
	private String email;
	
	@XStreamAlias("id")
	private Integer id;
	
	@XStreamAlias("name")
	private String name;
	
	@XStreamAlias("birthday")
	private Date birthday;
	
	@XStreamAlias("lessons")
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
