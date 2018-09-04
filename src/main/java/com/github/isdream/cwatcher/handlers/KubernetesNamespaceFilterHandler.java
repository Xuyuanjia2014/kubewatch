/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher.handlers;

import java.util.Properties;

import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;

import io.fabric8.kubernetes.api.model.HasMetadata;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public class KubernetesNamespaceFilterHandler extends Handler {

	public static final String KUBE_SYSTEM = "kube-system";
	
	@Override
	public void doHandle(Properties props) {
		Object obj = props.get(KubernetesListener.PROPERTY_RESOURCE);
		String ns = ((HasMetadata) obj).getMetadata().getNamespace();
		System.out.println(ns);
		if (KUBE_SYSTEM.equals(ns)) {
			return;
		} 
	}

}
