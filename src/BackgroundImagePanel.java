import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * 有背景图片的JPanel（平铺）
 */
public class BackgroundImagePanel extends JPanel {
	//重写绘图函数，绘制平铺图片
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//平铺图片背景
		ImageIcon icon=new ImageIcon(getClass().getClassLoader().getResource("res/1.png"));
		//每次绘制时，图像的位置坐标
		int x=0;
		int y=0;
		//平铺背景图片
		while(true) {
			//如果绘制完毕，退出循环
			if(y>getSize().height) break;
			//如果绘完一行，换行
			if(x>getSize().width) {
				x=0;
				y+=icon.getIconHeight();
			}
			else {
				g.drawImage(icon.getImage(), x, y, this);//绘制图片
				x+=icon.getIconWidth();
			}
			
		}
	}
}
