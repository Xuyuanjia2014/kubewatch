/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 */
package cn.abcsys.devops.v2.deployer.db.objects;

import java.util.List;

/**
 * @Author Xuyuanjia xuyuanjia2017@otcaix.iscas.ac.cn
 * @Date 2017/9/14 0014 18:29
 */
public class GridBean {

	private int page; //当前页
	private int total; //总页数
	private int records;//总条数
	private List<?> rows;//当前页数据
	private Object data; //错误提示信息、特殊的返回数据类型

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	private boolean success;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public GridBean(){

	}

	public GridBean(int page, int total, int records, List<?> rows, boolean issuccess) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = rows;
		this.success = issuccess;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
