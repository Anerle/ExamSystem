import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ProjectMain {
	/*
	 * 程序入口
	 * 设置全局主题
	 */
	public static void main(String[] args) {
	/*	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//使用系统UI风格
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginAndSignUpFrame loginSignUpFrame=new LoginAndSignUpFrame();
		loginSignUpFrame.FrameLoginSignUp();*/
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					//设置主题，采用substance（开源）主题包
					UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel");
				//	JDialog.setDefaultLookAndFeelDecorated(true);
					LoginAndSignUpFrame loginSignUpFrame=new LoginAndSignUpFrame();
					loginSignUpFrame.FrameLoginSignUp();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"SubstanceGraphitefailedtoinitialize");
				}
			}
		});
	}
}
