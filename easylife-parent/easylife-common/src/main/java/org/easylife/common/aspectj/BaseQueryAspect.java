package org.easylife.common.aspectj;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.easylife.common.annotation.Percent;
import org.easylife.common.annotation.SingleQuotes;
import org.easylife.common.entity.BaseQueryEntity;
import org.springframework.stereotype.Component;

/**
 * 
 * @Filename BaseQueryAspect.java
 * 
 * @Description	在MyBatis执行数据库操作前格式化BaseQueryEntity中标注@SingleQuotes和@Percent注解的属性
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月13日 下午4:25:41
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Aspect
@Component
public class BaseQueryAspect {
	
	@Before("within(org.easylife.common.dao.BaseMapper+)")
	public void bindField(JoinPoint joinPoint) throws IllegalArgumentException, IllegalAccessException{
		Object[] args = joinPoint.getArgs();
		for(Object o : args){
			if(o instanceof BaseQueryEntity){
				BaseQueryEntity baseQueryEntity = (BaseQueryEntity)o;
				Field[] fields = baseQueryEntity.getClass().getDeclaredFields();
				for(Field field : fields){
					field.setAccessible(true);
					Annotation singleQuotes = field.getAnnotation(SingleQuotes.class);
					Annotation percent = field.getAnnotation(Percent.class);
					if(singleQuotes != null){
						bindParameters(baseQueryEntity, field,"\'","\'%s\'");
					}else if(percent != null){
						bindParameters(baseQueryEntity, field,"%","%%%s%%");
					}
				}
			}
		}
	}

	/**
	 * 格式化属性，重新赋值
	 * @param baseQueryEntity
	 * @param field
	 * @param indexStr
	 * @param formatStr
	 * @throws IllegalAccessException
	 */
	private void bindParameters(BaseQueryEntity baseQueryEntity, Field field,String indexStr,String formatStr) throws IllegalAccessException {
		String original = (String) field.get(baseQueryEntity);
		if(StringUtils.isNotEmpty(original) && original.indexOf(indexStr)==-1 ){
			String fs = String.format(formatStr, field.get(baseQueryEntity));
			field.set(baseQueryEntity, fs);
		}
	}
}
