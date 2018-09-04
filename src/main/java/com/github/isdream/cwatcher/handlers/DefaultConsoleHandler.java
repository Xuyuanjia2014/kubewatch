/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher.handlers;

import java.util.Properties;

import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public class DefaultConsoleHandler extends Handler {

	@Override
	public void doHandle(Properties props) {
		Object obj = props.get(KubernetesListener.PROPERTY_RESOURCE);
		System.out.println(obj);
	}
}
