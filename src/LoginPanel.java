import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class LoginPanel extends JPanel {
	URL imgURL = getClass().getClassLoader().getResource("res/LoginBackground.jpg");
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(imgURL);
		g.drawImage(icon.getImage(), 0, 0, this);
	}
}