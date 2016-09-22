package org.easylife.common.entity;


/**
 * 
 * @Filename BaseQueryEntity.java
 * 
 * @Description	需要进行分页查询的Domain可以继承这个类
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月8日 上午11:47:03
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class BaseQueryEntity extends BaseEntity  {

	private static final long serialVersionUID = 15551525262649259L;
	
	/** 每页显示的条数*/
	private int pageSize = 10;

	/**总条数*/
	private int totalCount = 0;

	/**游标位置(查询结果不含此位置)*/
	private int posStart = 0;
	
	/**页码*/
	private int page;
	
	/**是否需要分页*/
	private boolean isPage = true;
	
	/**是否需要排序*/
	private boolean isOrderBy = true;
	
	/**排序类型*/
	private String orderByType;
	
	/**排序字段*/
	private String orderCols;

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 ){
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * @return the orderCols
	 */
	public String getOrderCols() {
		return orderCols;
	}

	/**
	 * @param orderCols
	 *            the orderCols to set
	 */
	public void setOrderCols(String orderCols) {
		this.orderCols = orderCols;
	}
	
	/**
	 * @return the posStart
	 */
	public int getPosStart() {
		calculatePosStart();
		return posStart;
	}
	
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @param posStart
	 *            the posStart to set
	 */
	public void setPosStart(int posStart) {
		this.posStart = posStart;
	}

	public boolean getIspage() {
		return isPage;
	}

	public void setIspage(boolean isPage) {
		this.isPage = isPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private void calculatePosStart() {
		
		int totalPage = totalCount / pageSize;
		
		if (totalPage == 0 || totalCount % pageSize != 0) {
			/**总页数*/
			totalPage++;
		}
		
		/**第一页*/
		if (page <= 0) {
			this.posStart = 0;
		} else if (page > totalPage) {
			/**看最后一页*/
			this.posStart = (totalPage - 1) * pageSize;
		} else {
			/**正常页*/
			this.posStart = (page - 1) * pageSize;
		}
	}

	public boolean isOrderBy() {
		return isOrderBy;
	}

	public void setOrderBy(boolean isOrderBy) {
		this.isOrderBy = isOrderBy;
	}

	public String getOrderByType() {
		if(orderByType == null || orderByType.length() == 0){
			orderByType = "ASC";
		}
		return orderByType;
	}

	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}
}
