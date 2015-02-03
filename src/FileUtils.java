import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
	/**
	 * 将图片文件转成二进制数组
	 * @param sFileName
	 * @return
	 * @throws IOException
	 */
	public byte[] ReadPicture(String sFileName) throws IOException{
		if(sFileName.isEmpty()) return null;//文件不存在或为空，返回null
		FileInputStream in = new FileInputStream(sFileName);
		byte[] bytePicture = new byte[in.available()];
		in.read(bytePicture);//输入流读到二进制数组中
		in.close();
		return bytePicture;
	}
}
