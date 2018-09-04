/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public class Configure {

	protected Map<String, String> properties = new HashMap<String, String>();
	
	protected List<String> handlers = new ArrayList<String>();
	
	protected String apiVersion = "kubernetes";
	
	protected String kind;
	
	/**
	 * @return 属性列表
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @param properties 设置属性
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * @return 所有handlers
	 */
	public List<String> getHandlers() {
		return handlers;
	}

	/**
	 * @param handlers 设置所有handlers
	 */
	public void setHandlers(List<String> handlers) {
		this.handlers = handlers;
	}

	/**
	 * @return 返回Kubernetes版本
	 */
	public String getApiVersion() {
		return apiVersion;
	}

	/**
	 * @param apiVersion 设置kubernetes版本
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	/**
	 * @return 获取类型
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind 设置类型
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
	
}
