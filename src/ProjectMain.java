import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ProjectMain {
	/*
	 * �������
	 * ����ȫ������
	 */
	public static void main(String[] args) {
	/*	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//ʹ��ϵͳUI���
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginAndSignUpFrame loginSignUpFrame=new LoginAndSignUpFrame();
		loginSignUpFrame.FrameLoginSignUp();*/
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					//�������⣬����substance����Դ�������
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
