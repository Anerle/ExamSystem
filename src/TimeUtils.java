import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {
	/*
	 * ����ǰʱ����һ����ʽ�ַ������
	 */
	public static String GetTime(int i){//iΪ0ʱ�����ʽΪ ��/��/�� Сʱ:����:��;Ϊ1ʱ��ʽΪ ������Сʱ������
		Date now=new Date();
		if(i==0) {
			SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//����ʱ���ʽ
			return s.format(now);
		}
		if(i==1) {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");//����ʱ���ʽ
			return s.format(now);
		}
		else return null;
	}

}
