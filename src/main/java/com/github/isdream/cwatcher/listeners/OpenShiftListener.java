/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher.listeners;

import com.github.isdream.cdispatcher.ModelParamtersGenerator;
import com.github.isdream.cdispatcher.generators.OpenShiftModelParametersGenerator;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public class OpenShiftListener extends KubernetesListener {

	@Override
	public ModelParamtersGenerator getModelParamtersGenerator() {
		return new OpenShiftModelParametersGenerator();
	}

	
}
