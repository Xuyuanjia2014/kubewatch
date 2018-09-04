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
public class KubernetesKindAnalyzerHandler extends Handler {

	@Override
	public void doHandle(Properties props) {
		Object obj = props.get(KubernetesListener.PROPERTY_RESOURCE);
		String name = ((HasMetadata) obj).getMetadata().getName();
		System.out.println(name);
	}

}
