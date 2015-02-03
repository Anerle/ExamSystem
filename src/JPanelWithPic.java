import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JPanel;

public class JPanelWithPic extends JPanel { 
 @Override
 protected void paintComponent(Graphics g) {
  try {
   java.net.URL url=getClass().getClassLoader().getResource("res/9.jpg");
   BufferedImage img=ImageIO.read(url);
   g.drawImage(img, 0, 0, 960, 720,null);
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}