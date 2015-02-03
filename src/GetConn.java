

import java.sql.*;

public class GetConn {
	public Connection conn = null;
	public ResultSet rs = null;
	public Statement stmt = null;
	public GetConn() { // 构造方法
	}
	/*
	 * 获取数据库连接方法
	 */
	public  Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test3?useUnicode=true&characterEncoding=GBK",
				//	"jdbc:mysql://222.20.74.111:3306/test3?useUnicode=true&characterEncoding=GBK",
					"root", "222222");
		} catch (Exception e) {System.out.println(e);return null;}// 异常处理
		return conn;// 按发法要求返回个Connection对象
	}
	/*
	 * 功能：执行查询语句
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {System.out.println(e);}
		return rs;
	}
	/*
	 * 功能:执行更新操作
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException ex) {result = 0;}
			try {
				stmt.close();
			} catch (SQLException e) {e.printStackTrace();}
		return result;
	}
	/*
	 * 功能:关闭数据库的连接
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {e.printStackTrace(System.err);}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {e.printStackTrace(System.err);}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {e.printStackTrace(System.err);}
	}
	/*
	 * 转码方法
	 */
	public static String tolatin1(String strvalue){ 
		try{
			if(strvalue==null){
				return "";
			}else{
				strvalue=new String(strvalue.getBytes("GBK"),"latin1").trim();
				return strvalue;
			}
		}catch (Exception e){return "";}
		}
	public static String toGBK(String strvalue){
		try{
			if(strvalue==null){
				return "";
			}else{
				strvalue=new String(strvalue.getBytes("latin1"),"GBK").trim();
				return strvalue;
			}
		}catch (Exception e){return "";}
	}
	public static String toDegree(int d){
		try{
			switch(d){
			case 0:{return "简单";}
			case 1:{return "较简单";}
			case 2:{return "中";}
			case 3:{return "较困难";}
			case 4:{return "困难";}
			}
		}catch (Exception e){return null;}
		return null;
	}
	public static String toChapter(int c){
		try{
			switch(c){
			case 0:{return "数字钟";}
			case 1:{return "电子测量原理";}
			case 2:{return "单管放大";}
			case 3:{return "数电基础";}
			case 4:{return "电路调试原理";}
			case 5:{return "函数发生器";}
			case 6:{return "音响放大器";}
			case 7:{return "MSI时序逻辑";}
			case 8:{return "函数发生器";}
			case 9:{return "运放应用";}
			}
		}catch (Exception e){return null;}
		return null;
	}
	public static String toType(int d){
		try{
			switch(d){
			case 0:{return "选择题";}
			case 1:{return "填空题";}
			case 2:{return "判断题";}
			case 3:{return "简答题";}
			}
		}catch (Exception e){return null;}
		return null;
	}
}
