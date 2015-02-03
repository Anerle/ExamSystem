import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * �б���ͼƬ��JPanel��ƽ�̣�
 */
public class BackgroundImagePanel extends JPanel {
	//��д��ͼ����������ƽ��ͼƬ
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//ƽ��ͼƬ����
		ImageIcon icon=new ImageIcon(getClass().getClassLoader().getResource("res/1.png"));
		//ÿ�λ���ʱ��ͼ���λ������
		int x=0;
		int y=0;
		//ƽ�̱���ͼƬ
		while(true) {
			//���������ϣ��˳�ѭ��
			if(y>getSize().height) break;
			//�������һ�У�����
			if(x>getSize().width) {
				x=0;
				y+=icon.getIconHeight();
			}
			else {
				g.drawImage(icon.getImage(), x, y, this);//����ͼƬ
				x+=icon.getIconWidth();
			}
			
		}
	}
}
