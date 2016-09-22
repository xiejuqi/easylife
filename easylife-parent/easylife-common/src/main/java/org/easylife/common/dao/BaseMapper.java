package org.easylife.common.dao;

import java.util.List;
import java.util.Map;

import org.easylife.common.entity.BaseEntity;
import org.easylife.common.entity.BaseQueryEntity;

/**
 * 
 * @Filename BaseMapper.java
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
 * @Date: 2016年4月8日 下午12:01:45
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public interface BaseMapper {
	/**
	 * 增加
	 * 
	 * @param t
	 */
	public int createEntity(BaseEntity bt);

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public int updateEntity(BaseEntity bt);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public int deleteById(BaseEntity bt);

	/**
	 * 根据ID数组删除对象
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByIds(Map<String, Object> params);

	/**
	 * 根据实体删除对象
	 * 
	 * @param t
	 * @return
	 */
	public int deleteByObject(BaseEntity bt);

	/**
	 * 根据id找到对象
	 * 
	 * @param id
	 * @return
	 */
	public BaseQueryEntity queryById(BaseQueryEntity bt);

	/**
	 * 根据参数对象查询对象
	 * 
	 * @param t
	 * @return
	 */
	public BaseQueryEntity queryByEntity(BaseQueryEntity bt);

	/**
	 * 根据Map查询对象
	 * 
	 * @param t
	 * @return
	 */
	public BaseQueryEntity queryByMap(Map<String, String> map);

	/**
	 * 查询所有的出列表
	 * 
	 * @return
	 */
	public List queryListByEntity(BaseQueryEntity bt);

	/**
	 * 根据Map参数来查询
	 * 
	 * @param map
	 * @return
	 */
	public List queryListByMap(Map<String, String> map);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List pageQueryEntity(BaseQueryEntity bt);

	/**
	 * 查询统计
	 * 
	 * @return
	 */
	public int pageQueryEntityCount(BaseQueryEntity bt);
}
