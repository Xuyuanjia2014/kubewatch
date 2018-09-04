/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.yaml.snakeyaml.Yaml;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 *
 */
public class Parser {

	/**
	 * @param file 文件位置
	 * @return 返回Config对象
	 * @throws FileNotFoundException 文件不存在异常
	 */
	public Configure parse(String file) throws FileNotFoundException {
		return new Yaml().loadAs(
				new FileInputStream(file), Configure.class);
	}
}
