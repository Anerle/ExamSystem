import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {
	/*
	 * 将当前时刻以一定格式字符串输出
	 */
	public static String GetTime(int i){//i为0时输出格式为 年/月/日 小时:分钟:秒;为1时格式为 年月日小时分钟秒
		Date now=new Date();
		if(i==0) {
			SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//定义时间格式
			return s.format(now);
		}
		if(i==1) {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");//定义时间格式
			return s.format(now);
		}
		else return null;
	}

}
