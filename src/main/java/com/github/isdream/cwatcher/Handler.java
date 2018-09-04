/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher;

import java.util.Properties;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public abstract class Handler {

	protected Handler nextHandler;
	
	public Handler() {
		super();
	}

	public void handle(Properties props) {
		doHandle(props);
		//System.out.println("this: "+this.getClass());
		if (nextHandler != null) {
			//System.out.println("next handler: "+nextHandler.getClass());
			nextHandler.handle(props);
		}
	}
	
	public abstract void doHandle(Properties props);

	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
}
