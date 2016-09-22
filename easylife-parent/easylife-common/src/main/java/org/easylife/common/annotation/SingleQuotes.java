package org.easylife.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @Filename SingleQuotes.java
 * 
 * @Description	标注该注解的String属性被格式化为带单引号的属性，用于SQL语句中的Where条件字段添加单引号。    account ==> 'account'
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月13日 下午3:18:18
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingleQuotes {
	
}
