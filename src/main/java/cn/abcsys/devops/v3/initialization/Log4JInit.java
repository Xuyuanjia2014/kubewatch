/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 * Copyright (2017, ) Bocloud Co,. Lmt
 */
package cn.abcsys.devops.v3.initialization;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletContextEvent;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by xyj on 2016/10/19 0019.
 */
public class Log4JInit {
    private static Logger logger = Logger.getLogger(Log4JInit.class);
    public void setLog4JPath(){
        String filePath = "classpath:config/log4j.properties";
        Properties props = new Properties();
        try {
            FileInputStream istream = new FileInputStream(ResourceUtils.getFile(filePath));
            props.load(istream);
            istream.close();
            String logFile = "C:\\" + props.getProperty("log4j.appender.File.File");//设置路径
            props.setProperty("log4j.appender.File.File",logFile);
            PropertyConfigurator.configure(props);//装入log4j配置信息
            logger.info("kubewatch log4JFilePath:" + logFile);
        } catch (Exception e) {
            logger.error("Could not read configuration file:" + filePath );
            return;
        }
    }
}
