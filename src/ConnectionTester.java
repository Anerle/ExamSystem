import java.awt.Toolkit;
import java.util.concurrent.Callable;   
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
import java.util.concurrent.Future;   

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class ConnectionTester {
	JWindow ProgressWindow;
	class TestConnection implements Callable{
    	public Object call(){
    		GetConn getconn=new GetConn();
    		if(getconn.getConnection()==null){
    			JOptionPane.showMessageDialog(null, "与服务器断开连接！请检查网络是否通畅", "网络连接失败", JOptionPane.INFORMATION_MESSAGE);
    	//		ConnectionTester.ProgressWindow.dispose();
    			return false;
    		}
    	//	ConnectionTester.ProgressWindow.dispose();
    		return true;
    	}
    }
    public void ShowProgressWindow(){
		ProgressWindow = new JWindow();
		JProgressBar progBar=new JProgressBar(SwingConstants.HORIZONTAL,0,10);
		progBar.setIndeterminate(true);
	//	progBar.setBackground(Color.BLACK);
		progBar.setString("WAIT...");
		progBar.setStringPainted(true);
		ProgressWindow.getContentPane().add(progBar);
		ProgressWindow.setSize(50, 19);
		ProgressWindow.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-50)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-19)/2);
	//	ProgressWindow.setBounds(300, 200, 250, 18);
		ProgressWindow.setVisible(true);
	}
	public boolean TestConnect() {
		ShowProgressWindow();
		TestConnection Test=new TestConnection();
		ExecutorService es = Executors.newSingleThreadExecutor();
		boolean i=false;
		try {
			Future future1 = es.submit(Test);
			i=(boolean) future1.get();
		} catch (Exception e){
			System.out.println(e.toString());
		}
		es.shutdownNow();
		ProgressWindow.dispose();
		return i;
	//	return true;
	}
}