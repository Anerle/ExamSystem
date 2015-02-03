import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IOImage {
private java.sql.Connection conn = null;
private java.sql.PreparedStatement pstmt = null;
private ResultSet rs = null;
/**
 * ��ͼƬ
 * @param bytes
 * @param id
 * @return
 * @throws ClassNotFoundException
 * @throws SQLException
 */
public int blobInsert(byte[] bytes,String id) throws ClassNotFoundException, SQLException{
  
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test3?useUnicode=true&characterEncoding=GBK",
			"root", "222222");
    pstmt = conn.prepareStatement("update question set picture =? where id=?");
    pstmt.setBytes(1, bytes);
    pstmt.setString(2, id);
    int count=pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return count;
   }
/**
 * ȡͼƬ
 * @param id
 * @return
 * @throws Exception
 */
public byte[] readBolb(String id) throws Exception{
   java.io.InputStream is = null;
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test3?useUnicode=true&characterEncoding=GBK",
			"root", "222222");
    pstmt = conn.prepareStatement("select picture from question where id =?");
    pstmt.setString(1, id);
    rs = pstmt.executeQuery();
    rs.next();
    java.sql.Blob blob = rs.getBlob("picture");
    try {
		is = blob.getBinaryStream();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return null;
	}
    byte[] buffer = new byte[is.available()];
    is.read(buffer);
    is.close();
    pstmt.close();
    conn.close();
    return buffer;
}
}

