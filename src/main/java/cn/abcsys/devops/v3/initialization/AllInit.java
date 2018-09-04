/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 * Copyright (2017, ) Bocloud Co,. Lmt
 */
package cn.abcsys.devops.v3.initialization;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyj on 2016/4/19.
 */
public class AllInit{ // 实现了这个接口的会在程序服务前执行这个 contextInitialized 方法，结束时执行 contextDestroyed
    static Logger logger = Logger.getLogger(AllInit.class);
    private Log4JInit lji;
    public static String baseFilePath = "E:";
    public static Map<String, HandlerInit> contextMap = new HashMap<>();

    public Log4JInit getLji() {
        return lji;
    }

    static {
        contextMap.put("47.104.16.133",new HandlerInit("http","AAAA","BBBB","kubernetes",0,1));
    }


}
