package cn.abcsys.devops.v3.handler.utils;
/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 * Copyright (2017, ) Bocloud Co,. Lmt
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author xuyuanjia2017@otcaix.iscsa.ac.cn
 * @date June 2,2017
 * xyj config javaDoc
 */
public class DateUtil {
    public static String getSimpleCurrentDateTIme() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return LocalDateTime.now().format(dtf);
    }

    public static String format(Date d, String timePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
        String formatedDate;
        if (d == null)
            formatedDate = sdf.format(new Date());
        else
            formatedDate = sdf.format(d);
        System.out.println(formatedDate);
        return formatedDate;
    }

    public static String utilDateConverter(Date d) {
        String timePattern = "yyyy-MM-dd HH:mm:ss";
        return format(d, timePattern);
    }

    public static String rawUtilDateConverter(Date d) {
        String timePattern = "yyyyMMddHHmmss";
        return format(d, timePattern);
    }
	/**
	 * @Description:获取当前日期
	 * @return
	 * @version v1.0.0
	 * @author xzg
	 * @date 2017年9月5日 下午2:43:02
	 * @Modify:
	*/
	public static Date getCurrentDate(){
		Clock clock = Clock.systemDefaultZone();
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);   // legacy java.util.Date
		return legacyDate;
	}

    public static String parseDateStr(String str, String timePatter) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(timePatter);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(str);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
