/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher;

import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public abstract class Listener {

	/**
	 * 启动监听
	 * 
	 * @param client 连接的客户端，如果是Kubernetes，则为io.fabric8.kubernetes.client.DefaultKubernetesClient
	 * @param config 配置信息
	 * @throws Exception 抛出其它异常
	 */
	public abstract void start(Object client, Configure config) throws Exception;
	
	/**
	 * 停止监听
	 * 
	 * @throws Exception 抛出其它异常 
	 */
	public abstract void stop();

	public abstract KubernetesListener.KubernetesWatcher getSelfWatch();

	public abstract void setSelfWatch(KubernetesListener.KubernetesWatcher watch);

	public abstract KubernetesClient getClient();

	public abstract void setClient(KubernetesClient client);

}
