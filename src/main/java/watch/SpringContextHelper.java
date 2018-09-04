/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 */
package watch;

import cn.abcsys.devops.v3.initialization.Log4JInit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Author Xuyuanjia xuyuanjia2017@otcaix.iscas.ac.cn
 * @Date 2017/10/9 1:11
 * @File SpringContextHelper.java
 */
@Service
public class SpringContextHelper implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
        (new Log4JInit()).setLog4JPath();

    }

    public static Object getBean(String name){
        System.out.println(context);
        return context.getBean(name);
    }

}
