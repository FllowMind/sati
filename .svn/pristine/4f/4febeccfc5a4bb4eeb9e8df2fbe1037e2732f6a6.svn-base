package com.jyu.sati.common.pageUtil;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 分页
 * 
 * @author 淋雨又调皮
 *
 * @param <T>
 */
public class Page<T> {

	public Integer totalNo = 0;// 总条数
	public Integer pageNo = 0;// 当前页
	public Integer pageSize = 20;// 每页条数
	public Integer totalPage = 0;// 总页数
	public List<T> resultList = new ArrayList<>();// 返回数据
	public Integer pageIndex = 0;

	/**
	 * @param totalNo
	 *            总页数
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            每页条数
	 */
	public Page(Integer totalNo, Integer pageNo, Integer pageSize) {
		super();
		this.totalNo = totalNo;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Integer getTotalNo() {
		return totalNo;
	}

	public void setTotalNo(int totalNo) {
		if (totalNo < 0) {
			totalNo = 0;
		}
		this.totalNo = totalNo;
	}

	public Integer getPageNo() {
		if (pageNo < 0) {
			pageNo = 0;
		} else if (pageNo > getTotalPage()) {
			pageNo = getTotalPage();
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize <= 0 ? 10 : pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else {
			this.pageSize = pageSize;
		}
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 查询起始位置
	 * 
	 * @return
	 */
	@JsonIgnore
	public Integer getPageIndex() {
		if (pageNo < 1) {
			pageNo = 1;
		}
		pageIndex = (pageNo - 1) * pageSize;
		return pageIndex;
	}

	public Integer getTotalPage() {
		if (totalNo > 0) {
			if (totalNo < getPageSize()) {
				totalPage = 1;
			} else if (totalNo % getPageSize() > 0) {
				totalPage = totalNo / getPageSize() + 1;
			} else {
				totalPage = totalNo / getPageSize();
			}
		}
		return totalPage;
	}

	@Override
	public String toString() {
		return "Page [totalNo=" + totalNo + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", result=" + resultList + "]";
	}

}
