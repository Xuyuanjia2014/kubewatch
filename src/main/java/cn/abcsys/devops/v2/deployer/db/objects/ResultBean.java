/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 */
package cn.abcsys.devops.v2.deployer.db.objects;
/**
 * @Author Xuyuanjia xuyuanjia2017@otcaix.iscas.ac.cn
 * @Date 2017/9/14 0014 17:36
 */
public class ResultBean {

	private boolean success;
	private String message;
	private Object data;// 操作返回的数据
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	public ResultBean(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	/**
	 * @param success
	 * @param data
	 * @param message
	 */
	public ResultBean(boolean success, Object data, String message) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	public ResultBean() {
		super();
	}
	
	@Override
	public String toString() {
		return "Result [success=" + success + ", message=" + message + "]";
	}
	
}
