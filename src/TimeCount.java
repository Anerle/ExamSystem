import java.awt.Color;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class TimeCount implements Runnable{
	public void run(){
		 long nowTime,leftTime,leftMin,leftSec;
		 int time=FrameOnlineTest.time;
		 Calendar startCalendar = Calendar.getInstance();
		 long startTime=startCalendar.getTime().getTime();
		 long endTime=startTime+time*60*1000;
		 while(FrameOnlineTest.flag==1){
			 Calendar now =  Calendar.getInstance();
			 nowTime=now.getTime().getTime();
			 leftTime=endTime-nowTime;
			 leftMin=leftTime/(60*1000);
			 leftSec=(leftTime/1000)-leftMin*60;
			 String LeftTime=String.valueOf(leftMin) +":"+String.valueOf(leftSec);
			 FrameOnlineTest.lablefttime.setText("ʣ��ʱ��: "+LeftTime);
			 if(leftMin==14&&leftSec==59){
				 JOptionPane.showMessageDialog(null,"�뿼�Խ�������15���ӣ�","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			 }
			 if(leftMin<=14){
				FrameOnlineTest.lablefttime.setForeground(Color.RED); 
			 };
            if(leftMin==0&&leftSec==0){
             JOptionPane.showMessageDialog(null,"����ʱ��������ϵͳ���Զ��ύ�Ծ�","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
             FrameOnlineTest.frame.dispose();
           	 break;
            }
			 try{
				 Thread.sleep(1000);
			 }catch(InterruptedException e){
				 e.printStackTrace();
			 }
			 }
		 while(FrameOnlineTest.flag==0){
		 }
		}
	}
