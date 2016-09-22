package org.easylife.common.resultset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.easylife.common.entity.BaseQueryEntity;
import org.easylife.common.enumeration.ResultSetEnum;
/**
 * 
 * @Filename ResultSet.java
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
 * @Date: 2016年4月8日 下午12:25:53
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class ResultSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7305865113577350616L;
	
	/**处理结果编码*/
	private boolean status;
	
	/**处理结果Map,键为处理结果编码，值为处理结果信息*/
	private Map<Boolean,String> statusMap;
	
	/**处理结果的结果集*/
	private List resultList;
	
	/**
	 * 返回一个成功结果(含结果集)
	 * @param res
	 * @return
	 */
	public static ResultSet successResult(ResultSetEnum res,List resultList){
		
		Map<Boolean,String> map = new HashMap<Boolean, String>();
		
		map.put(true, ResultSetEnum.getStatusName(res));
		
		return new ResultSet(true,map,resultList);
	}
	
	/**
	 * 返回一个成功结果(不含结果集)
	 * @param res
	 * @return
	 */
	public static ResultSet successResult(ResultSetEnum res){
		
		Map<Boolean,String> map = new HashMap<Boolean, String>();
		
		map.put(true, ResultSetEnum.getStatusName(res));
		
		return new ResultSet(true,map);	
	}
	
	/**
	 * 返回一个失败结果(含结果集)
	 * @param res
	 * @return
	 */
	public static ResultSet failResult(ResultSetEnum res,List resultList){
		
		if(resultList == null || resultList.size() ==0){
			resultList = new ArrayList();
		}
		
		Map<Boolean,String> map = new HashMap<Boolean, String>();
		
		map.put(false, ResultSetEnum.getStatusName(res));
		
		return new ResultSet(false,map,resultList);
	}
	
	/**
	 * 返回一个失败结果(不含结果集)
	 * @param res
	 * @return
	 */
	public static ResultSet failResult(ResultSetEnum res){
		
		Map<Boolean,String> map = new HashMap<Boolean, String>();
		
		map.put(false, ResultSetEnum.getStatusName(res));
		
		return new ResultSet(false,map);
	}
	
	public ResultSet (Boolean status,Map<Boolean,String> statusMap,List resultList){
		this.statusMap=statusMap;
		this.resultList=resultList;
		this.status=status;
	}
	
	public ResultSet (Boolean status,Map<Boolean,String> statusMap){
		this.statusMap=statusMap;
		this.status=status;
	}
	
	/**
	 * 不提供无参构造器会反序列化报错
	 * http://hittyt.iteye.com/blog/1691772
	 */
	public ResultSet (){
		
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Map<Boolean, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<Boolean, String> statusMap) {
		this.statusMap = statusMap;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
