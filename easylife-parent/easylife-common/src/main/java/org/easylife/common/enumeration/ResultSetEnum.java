package org.easylife.common.enumeration;
/**
 * 
 * @Filename ResultSetEnum.java
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
 * @Date: 2016年4月11日 上午9:45:18
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public enum ResultSetEnum {
	
	/**查询结果为空*/
	NULL_RESULT("ELIFE-001","查询结果为空！"), 
	
	/**查询结果多条*/
	MULTIPLE_RESULTS("ELIFE-002","查询结果不唯一！"),
	
	/**查询成功*/
	QUERY_SUCCESS("ELIFE-003","查询成功！"),
	
	/**主键为空*/
	PRIMARY_KEY_NULL("ELIFE-004","主键为空！"),
	
	/**更新成功*/
	UPDATE_SUCCESS("ELIFE-005","更新成功！"),
	
	UPDATE_FAIL("ELIFE-006","更新失败！");	
	
	private String statusCode;
	private String message;
	
	private ResultSetEnum(String statusCode,String message){
		this.statusCode=statusCode;
		this.message=message;
	}
	
	public static String getStatusName(ResultSetEnum statusCode) {
        for (ResultSetEnum c : ResultSetEnum.values()) {
            if (c.getStatusCode().equals(statusCode.statusCode)) {
                return c.message;
            }
        }
		return null;
    }
	
	@Override
	public String toString() {
		return String.valueOf(this.statusCode);
	}
	
	public String getStatusCode() {
		return statusCode;
	}
}
