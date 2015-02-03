import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class PictureUtils {
	/*
	 * byte[]转ImageIcon
	 */
	ImageIcon BytesToIcon(byte[] PicBytes){
		ByteArrayInputStream PicStream=new ByteArrayInputStream(PicBytes);
		ImageIcon PicIcon=null;
		try {
			Image Pic=ImageIO.read(PicStream);
			PicIcon = new ImageIcon(Pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PicIcon;
	}
	/*
	 * 等比缩放
	 */
	ImageIcon PreviewPicture(ImageIcon icon,int conWidth,int conHeight){
		int imgWidth=icon.getIconWidth();
		int imgHeight=icon.getIconHeight();
		int reImgWidth;
		int reImgHeight;
		if((double)imgWidth/(double)imgHeight>=(double)conWidth/(double)conHeight){
			if(imgWidth>conWidth){
				reImgWidth=conWidth;
				reImgHeight=reImgWidth*imgHeight/imgWidth;
			}else{
				reImgWidth=imgWidth;
				reImgHeight=imgHeight;
			} 
		}
		else{  
			if(imgHeight>conHeight){  
				reImgHeight=conHeight;
				reImgWidth=reImgHeight*imgWidth/imgHeight;  
			}else{  
				reImgWidth=imgWidth;
				reImgHeight=imgHeight;  
			}  
		}  
		ImageIcon NewIcon=new ImageIcon(icon.getImage().getScaledInstance(reImgWidth, reImgHeight, Image.SCALE_SMOOTH));
		return NewIcon;
	}
	
}
