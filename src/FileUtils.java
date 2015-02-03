import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
	/**
	 * ��ͼƬ�ļ�ת�ɶ���������
	 * @param sFileName
	 * @return
	 * @throws IOException
	 */
	public byte[] ReadPicture(String sFileName) throws IOException{
		if(sFileName.isEmpty()) return null;//�ļ������ڻ�Ϊ�գ�����null
		FileInputStream in = new FileInputStream(sFileName);
		byte[] bytePicture = new byte[in.available()];
		in.read(bytePicture);//����������������������
		in.close();
		return bytePicture;
	}
}
