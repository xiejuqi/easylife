package org.easylife.test.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @Filename Lesson.java
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
 * @Date: 2016年5月30日 下午2:04:19
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@XStreamAlias("lesson")
public class Lesson {
	
	@XStreamAlias("id")
	private Integer id;
	
	@XStreamAlias("lessoName")
	private String lessoName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLessoName() {
		return lessoName;
	}

	public void setLessoName(String lessoName) {
		this.lessoName = lessoName;
	}
}
