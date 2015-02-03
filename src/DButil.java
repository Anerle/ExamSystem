import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

import org.omg.CORBA.portable.InputStream;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;


public class DButil {
	/**
	 * 选出指定难度、题型、考点的试题
	 * @param d
	 * @param c
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public  String[] selectByDegree(int d,int c,int t) throws Exception{
		ResultSet rs = null;
		LinkedList<String> ll=new LinkedList<String>(); 
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from question where degree='"+d+"' AND chapter ='"+c+"' AND typeid='"+t+"'");
		while(rs.next()){
			ll.add(rs.getString("id"));
		}
		int i;
		String[] b=new String[ll.size()];
		for(i=0;i<ll.size();i++){
			b[i]=ll.get(i);
		}
		gc.close();
		return b;
	}
	/**
	 *  查看flag,返回1代表已选,返回0代表未选
	 * @param id
	 * @return
	 */
	public int getFlag(String id){
		int q=1;
		try{
		GetConn gc=new GetConn();
		ResultSet rs=gc.executeQuery("select flag from question where id='"+id+"'");
		rs.next();
		q=rs.getInt("flag");
		gc.close();
	}catch(Exception e){System.out.println(e);}
		return q;
}
	/**
	 * 给指定id的题flag置0/1 
	 * @param a
	 * @param b
	 */
	public void setFlag(String a,int b){
		try{
			GetConn gc=new GetConn();
		gc.executeUpdate("update question set flag='"+b+"' where id='"+a+"'");
		gc.close();
		}catch(Exception e){System.out.println(e);}
	}
	
	/**
	 * 增加试题
	 * @param id
	 * @param typeid
	 * @param q_subject
	 * @param q_answer
	 * @param degree
	 * @param chapter
	 * @return
	 */
	public int addQuestion(String id,int typeid,String q_subject,String q_answer
			,int degree,int chapter){
		GetConn gc=new GetConn();
		String strInsert= "insert into question(id,typeid,q_subject,q_answer,degree,chapter) values('"+id+"','"+typeid+"','"+q_subject+"','"+q_answer+"','"+degree+"','"+chapter+"')";
		int numberOfRows=gc.executeUpdate(strInsert);
		gc.close();
		return numberOfRows;
	}
	/**
	 * 删除试题
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteQuestion(String id) throws SQLException{
		ResultSet rs=null;
		int numberOfRows;
		String strInsert= "delete from question where id='"+id+"'";
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select id from paper where id='"+id+"'");
		if(rs.next()){
			return -1;
		}
		else{
			numberOfRows=gc.executeUpdate(strInsert);
		}
		gc.close();
		return numberOfRows;
	}
	/**
	 * 选出全部试题并存入二维数组
	 * @return
	 * @throws Exception
	 */
	public  Object[][] selectAll() throws Exception{
		ResultSet rs = null;
		GetConn gc=new GetConn();
		int c=0;
		int i=0;
		rs=gc.executeQuery("select * from question ");
		while(rs.next()){
			c++;
		}
		Object[][] b=new Object[c][6];
		rs.beforeFirst();
		while(rs.next()){
			b[i][0]=rs.getString("id");
			b[i][1]=rs.getString("q_subject");
			b[i][2]=GetConn.toDegree(rs.getInt("degree"));
			b[i][3]=GetConn.toChapter(rs.getInt("chapter"));
			b[i][4]=GetConn.toType(rs.getInt("typeid"));
			if(rs.getDouble("selectedCount")==0.00){
				b[i][5]="";
			}
			else{
			DecimalFormat df=(DecimalFormat)NumberFormat.getNumberInstance();
			df.setMinimumFractionDigits(3);
			df.setMaximumFractionDigits(3);
			b[i][5]=df.format((float)rs.getDouble("correctCount")/rs.getDouble("selectedCount"));
			}
		i=i+1;
		}
		gc.close();
		return b;
	}
	/**
	 * 输出指定id的试题信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public  Object[] selectById(String id) throws Exception{
		ResultSet rs = null;
		/*try{*/
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select q_subject,degree,chapter,typeid,q_answer from question where id='"+id+"'");
		Object[] b=new Object[5];
		if(rs.next()){
			b[0]=rs.getString("q_subject");
			b[1]=rs.getInt("degree");
			b[2]=rs.getInt("chapter");
			b[3]=rs.getInt("typeid");
			b[4]=rs.getString("q_answer");
		}
		gc.close();
		return b;
	}
	/**
	 * 试题修改
	 * @param id 
	 * @param typeid
	 * @param q_subject
	 * @param q_answer
	 * @param degree
	 * @param chapter
	 * @return
	 */
	public int updateQuestion(String id,int typeid,String q_subject,String q_answer
			,int degree,int chapter){
		GetConn gc=new GetConn();
		String strInsert= "update question set typeid='"+typeid+"',q_subject='"+q_subject+"',q_answer='"+q_answer+"',degree='"+degree+"',chapter='"+chapter+"' where id='"+id+"'";
		int numberOfRows=gc.executeUpdate(strInsert);
		gc.close();
		return numberOfRows;
	}
	/**
	 * 学生注册
	 * @param si
	 * @param sn
	 * @param cn
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public int studentSignUp(String si,String sn,String cn,String password) throws SQLException{
		GetConn gc=new GetConn();
		ResultSet rs = null;
		rs=gc.executeQuery("select * from student where stu_id='"+si+"'");
		if(rs.next()) return -1;
		String strInsert= "insert into student(stu_id,stu_name,class_name) values('"+si+"','"+sn+"','"+cn+"')";
		String strInsert2= "insert into user_stu values('"+password+"','"+si+"')";
		int numberOfRows=gc.executeUpdate(strInsert);
		int numberOfRows2=gc.executeUpdate(strInsert2);
		gc.close();
		return (numberOfRows+numberOfRows2)/2;
	}
	/**
	 * 教师注册,成功返回1，失败返回0
	 * @param uid
	 * @param pwd
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public int teacherSignUp(String uid,String pwd,String code) throws SQLException{
		ResultSet rs = null;
		int numberOfRows=0;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from user_teacher where uid='"+uid+"'");
		if(rs.next()){gc.close();return -1;}
		String strSelect="select * from code";
		rs=gc.executeQuery(strSelect);
		while(rs.next()){
			if(code.equals(rs.getString("identity"))){
				String strInsert= "insert into user_teacher(uid,pwd) values('"+uid+"','"+pwd+"')";
				numberOfRows=gc.executeUpdate(strInsert);
				return numberOfRows;
			}
		}
		gc.close();
		return 2;
	}
	/**
	 * 学生登录
	 * @param stu_id
	 * @param pwd
	 * @return
	 * @throws SQLException
	 */
	public int studentLogin(String stu_id,String pwd) throws SQLException{
		ResultSet rs = null;
		GetConn gc=new GetConn();
		String strSelect="select pwd from user_stu where stu_id='"+stu_id+"'";
		rs=gc.executeQuery(strSelect);
		if(!rs.next()){ gc.close();return -1;}
		if(pwd.equals(rs.getString("pwd"))) {gc.close();return 1;}
		gc.close();
		return 0;	
	}
	/**
	 * 教师登录
	 * @param uid
	 * @param pwd
	 * @return
	 * @throws SQLException
	 */
	public int teacherLogin(String uid,String pwd) throws SQLException{
		ResultSet rs = null;
		GetConn gc=new GetConn();
		String strSelect="select pwd from user_teacher where uid='"+uid+"'";
		rs=gc.executeQuery(strSelect);
		if(!rs.next()) {gc.close();return -1;}
		if(pwd.equals(rs.getString("pwd"))) return 1;
		gc.close();
		return 0;	
	}
	/**
	 * 添加试卷
	 * @param p
	 * @param t
	 * @param totalScore
	 * @param time
	 * @param score
	 * @param examDegree
	 * @param chapterid
	 * @return
	 */
	public int addPaper(String p,String[] t,int totalScore,int time,double[] score,int examDegree,int[] chapterid){
		GetConn gc=new GetConn();
		String chapter="";
		for(int j=0;j<chapterid.length;j++){
			chapter+=GetConn.toChapter(chapterid[j])+";";
		}
		String str= "insert into system values('"+chapter+"','"+examDegree+"','"+score[0]+"','"+score[1]+"','"+score[2]+"','"+score[3]+"','"+time+"','"+totalScore+"','"+p+"')";
		int numberOfRows1=gc.executeUpdate(str);
		int c=0;
		for(int i=0;i<t.length;i++){
			if(t[i]!=null){
		String strInsert= "insert into paper values('"+p+"','"+t[i]+"')";
		int numberOfRows2=gc.executeUpdate(strInsert);
		c+=numberOfRows2;
			}
		}
		gc.close();
		return numberOfRows1+c;
	}
	/**
	 * 根据学号输出学生基本信息
	 * @param stu_id
	 * @return
	 * @throws Exception
	 */
	public  String[] studentById(String stu_id) throws Exception{
		ResultSet rs = null;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from student where stu_id='"+stu_id+"'");
		String[] b=new String[2];
		if(rs.next()){
			b[0]=rs.getString("stu_name");
			b[1]=rs.getString("class_name");
		}
		gc.close();
		return b;
	}
	/**
	 * 修改学生信息
	 * @param stu_id
	 * @param stu_name
	 * @param class_name
	 * @return
	 */
	public int updateStudent(String stu_id,String stu_name,String class_name){
		GetConn gc=new GetConn();
		String strInsert= "update student set stu_name='"+stu_name+"',class_name='"+class_name+"' where stu_id='"+stu_id+"'";
		int numberOfRows=gc.executeUpdate(strInsert);
		gc.close();
		return numberOfRows;
	}
	/**
	 * 记录日志
	 * @param stu_id
	 * @param operation
	 * @param time
	 * @return
	 */
	public int addLog(String stu_id,String operation,String time){
		GetConn gc=new GetConn();
		String str= "insert into log values('"+stu_id+"','"+operation+"','"+time+"')";
		int numberOfRows=gc.executeUpdate(str);
		gc.close();
		return numberOfRows;
	}
	/**
	 * 输出日志，String[i][0]存operation,String[i][1]存time
	 * @param stu_id
	 * @return
	 * @throws SQLException
	 */
	public String[][] readLog(String stu_id) throws SQLException{
		ResultSet rs = null;
		int c=0;
		int i=0;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from log where stu_id='"+stu_id+"'");
		while(rs.next()){
			c++;
		}
		String[][] b=new String[c][2];
		rs.beforeFirst();
		while(rs.next()){
			b[i][1]=rs.getString("operation");
			b[i][0]=rs.getString("time");
			i=i+1;
		}
		gc.close();
		return b;
	}
	/**
	 * 输出试卷O[0][]存选择题id,O[1][]填空题id,O[2][]判断题id,O[3][]简答题id,O[4][]存x、p、t、j、time、totalScore
	 * return null表示未设考试试卷
	 * @return
	 * @throws SQLException
	 */
	public Object[][] readPaper() throws SQLException{
		ResultSet rs=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		String paper;
		int c;
		int j;
		int i;
		GetConn gc=new GetConn();
		rs3=gc.executeQuery("select paper_id from exampaper where exam_id=1");
		if(rs3.next()){
			paper=rs3.getString("paper_id");
		}
		else{
			return null;
		}
		Object[][] b=new Object[5][];
		for(i=0;i<4;i++){
			String str="select id from paper where paper_id='"+paper+"' and id in (select id from question where typeid='"+i+"')";
			rs=gc.executeQuery(str);
			c=0;
			while(rs.next()){c+=1;}
			b[i]=new String[c];
			j=0;
			rs.beforeFirst();
			while(rs.next()){b[i][j++]=rs.getString("id");}
		}
		String str="select * from system where paper_id='"+paper+"'";
		rs2=gc.executeQuery(str);
		rs2.next();
		b[4]=new Object[6];
		b[4][0]=rs2.getDouble("scorex");
		b[4][1]=rs2.getDouble("scorep");
		b[4][2]=rs2.getDouble("scoret");
		b[4][3]=rs2.getDouble("scorej");
		b[4][4]=rs2.getInt("time");
		b[4][5]=rs2.getInt("totalScore");
		gc.close();
		return b;
	}
	/**
	 * 设置考试试卷
	 * @param paper_id
	 * @return
	 */
	public int addExamPaper(String paper_id){
		GetConn gc=new GetConn();
		String strInsert= "update examPaper set paper_id='"+paper_id+"' where exam_id='"+1+"'";
		int numberOfRows=gc.executeUpdate(strInsert);
		gc.close();
		return numberOfRows;
	}
	/**
	 * 列表显示已有的所有试卷的试卷号、总分、难度、考试时间
	 * @return
	 * @throws Exception
	 */
	public  Object[][] readAllPaper() throws Exception{
		ResultSet rs = null;
		ResultSet rs1= null;
		int c=0;
		int i=0;
		GetConn gc=new GetConn();
		rs1=gc.executeQuery("select * from exampaper");
		rs1.next();
		String p=rs1.getString("paper_id");
		rs=gc.executeQuery("select * from system");
		while(rs.next()){
			c++;
		}
		Object[][] b=new Object[c][6];
		rs.beforeFirst();
		while(rs.next()){
			if(p.equals(rs.getString("paper_id"))){b[i][0]="考试试卷";}
			else{b[i][0]="";}
			b[i][1]=rs.getString("paper_id");
			b[i][2]=GetConn.toDegree(rs.getInt("examDegree"));
			b[i][3]=rs.getString("includedchapter");
			b[i][4]=rs.getInt("time");
			b[i][5]=rs.getInt("totalScore");
			i=i+1;
		}
		gc.close();
		return b;
	}
	/**
	 * 修改密码，返回-1表示原密码错误，返回0修改失败。返回1修改成功
	 * @param stu_id
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 * @throws SQLException
	 */
	public int updatePwd(String stu_id,String oldpassword,String newpassword) throws SQLException{
		ResultSet rs=null;
		int numberOfRows;
		String str="select pwd from user_stu where stu_id='"+stu_id+"' ";
		String strInsert= "update user_stu set pwd='"+newpassword+"' where stu_id='"+stu_id+"'";
		GetConn gc=new GetConn();
		rs=gc.executeQuery(str);
		rs.next();
		if(oldpassword.equals(rs.getString("pwd"))){
			numberOfRows=gc.executeUpdate(strInsert);
		}
		else{
			return -1;
		}
		gc.close();
		return numberOfRows;
	}
	/**
	 * 查看指定paper的试卷
	 * @param paper_id
	 * @return
	 * @throws Exception
	 */
	public  String[] checkPaper(String paper_id) throws Exception{
		ResultSet rs = null;
		int i=0;
		int c=0;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select id from paper where paper_id='"+paper_id+"'");
		while(rs.next()){
			c++;
		}
		String[] b=new String[c];
		rs.beforeFirst();
		while(rs.next()){
			b[i++]=rs.getString("id");
		}
		gc.close();
		return b;
	}
	/**
	 * 删除试卷
	 * @param paper_id
	 * @return
	 * @throws SQLException
	 */
	public int deletePaper(String paper_id) throws SQLException{
		ResultSet rs=null;
		int numberOfRows;
		int numberOfRows2;
		String str1= "delete from paper where paper_id='"+paper_id+"'";
		String str2= "delete from system where paper_id='"+paper_id+"'";
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select paper_id from stu_answer where paper_id='"+paper_id+"'");
		if(rs.next()){
			return -1;
		}
		else{
			numberOfRows=gc.executeUpdate(str1);
			numberOfRows2=gc.executeUpdate(str2);
		}
		gc.close();
		return (numberOfRows+numberOfRows2)/2;
	}
	/**
	 * 记录学生答案
	 * @param stu_id
	 * @param id
	 * @param answer
	 * @return
	 * @throws SQLException
	 */
	public int recordAnswer(String stu_id,String[] id ,String[] answer) throws SQLException{
		ResultSet rs=null;
		int numberOfRows = 0;
		int i;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select paper_id from exampaper where exam_id='1'");
		rs.next();
		String paper=rs.getString("paper_id");
		for(i=0;i<id.length&&id[i]!=null;i++){
		String strInsert= "insert into stu_answer(answer,id,paper_id,stu_id) values('"+answer[i]+"','"+id[i]+"','"+paper+"','"+stu_id+"')";
		numberOfRows=gc.executeUpdate(strInsert);
		}
		gc.close();
		return numberOfRows;
	}
	/**
	 * 记录学生每题得分填空题、简答题
	 * @param stu_id
	 * @param paper_id
	 * @param id
	 * @param point
	 * @param total
	 * @return
	 * @throws SQLException
	 */
	public int recordPoint(String stu_id,String paper_id,String id ,double point,double total) throws SQLException{
		ResultSet rs=null;
		ResultSet rs2=null;
		double c;
		double s;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select correctCount,selectedCount from question where id='"+id+"'");
		rs.next();
		s=rs.getDouble("selectedCount")+total;
		rs2=gc.executeQuery("select point from stu_answer where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+id+"'");
		rs2.next();
		if(rs2.getDouble("point")==-1){
			c=rs.getDouble("correctCount")+point;
		}
		else{
			c=rs.getDouble("correctCount")-rs2.getDouble("point")+point;
		}
	    int numberOfRows1=gc.executeUpdate("update question set correctCount='"+c+"',selectedCount='"+s+"' where id='"+id+"'");
		int numberOfRows2=gc.executeUpdate("update stu_answer set point='"+point+"'where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+id+"'");
		gc.close();
		return (numberOfRows1+numberOfRows2)/2;
	}
	/**
	 * 在线批改，列表显示学生试卷信息Object[][]存放stu_id,paper_id,class_name,是否批阅 
	 * @return
	 * @throws SQLException
	 */
	public Object[][] markOrNot() throws SQLException{
		ResultSet rs=null;
		ResultSet rs2=null;
		int c=0;
		int i=0;
		int y;
		int n;
		String stu_id;
		String str2="select distinct stu_id,paper_id from stu_answer";
		GetConn gc=new GetConn();
		rs=gc.executeQuery(str2);
		while(rs.next()){
			c++;
		}
		Object[][] spcm=new Object[c][4];
		rs.beforeFirst();
		while(rs.next()){
			spcm[i][0]=rs.getString("stu_id");
			spcm[i][1]=rs.getString("paper_id");
			stu_id=rs.getString("stu_id");
			rs2=gc.executeQuery("select class_name from student where stu_id='"+stu_id+"'");
			rs2.next();
			spcm[i][2]=rs2.getString("class_name");
			y=0;
			n=0;
			rs2=gc.executeQuery("select point from stu_answer where stu_id='"+stu_id+"'and paper_id='"+rs.getString("paper_id")+"'");
			while(rs2.next()){
				if(rs2.getDouble("point")!=-1){
					y++;
				}
				else{
					n++;
				}
			}
			if(n==0){
				spcm[i][3]="完成";
			}
			else{
				spcm[i][3]=new String(y+"/"+(y+n));
			}
			i++;
		}
		gc.close();
		return spcm;
	}
	/**
	 * 返回阅卷信息存q_subject,q_answer,answer,本题满分,上次得分
	 * @param stu_id
	 * @param paper_id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Object[] showStuAnswer(String stu_id,String paper_id,String id) throws SQLException{
		ResultSet rs=null;
		ResultSet rs2=null;
		int type;
		Object[] tj=new Object[5];
		String str="select q_subject,q_answer,typeid from question where id='"+id+"'";
		String str2="select answer,point from stu_answer where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+id+"'";
		System.out.println(stu_id);
		System.out.println(paper_id);
		System.out.println(id);
		GetConn gc=new GetConn();
		rs=gc.executeQuery(str);
		rs.next();
		tj[0]=rs.getString("q_subject");
		tj[1]=rs.getString("q_answer");
		type=rs.getInt("typeid");
		rs2=gc.executeQuery(str2);
		rs2.next();
		tj[2]=rs2.getString("answer");
		tj[4]=rs2.getDouble("point");
		if(type==1){
			rs=gc.executeQuery("select scoret from system where paper_id='"+paper_id+"'");
			rs.next();
			tj[3]=rs.getDouble("scoret");
		}
		else if(type==3){
			rs=gc.executeQuery("select scorej from system where paper_id='"+paper_id+"'");
			rs.next();
			tj[3]=rs.getDouble("scorej");
		}
		gc.close();
		return tj;
	}
	/**
	 * 返回id，其中tj[0][]存填空题，tj[1][]存简答题
	 * @param paper_id
	 * @return
	 * @throws SQLException
	 */
	public String[][] manualMark(String paper_id) throws SQLException{
		ResultSet rs=null;
		int i=0;
		int j=0;
		int c;
		String[][] tj=new String[2][];
		String strt="select id from paper where paper_id='"+paper_id+"' and id in(select id from question where typeid='"+1+"')";
		String strj="select id from paper where paper_id='"+paper_id+"' and id in(select id from question where typeid='"+3+"')";
		GetConn gc=new GetConn();
		rs=gc.executeQuery(strt);//
		c=0;
		while(rs.next()){
			c++;
		}
		tj[0]=new String[c];
		rs.beforeFirst();
	    while(rs.next()){
	    	tj[0][i++]=rs.getString("id");
	    }
	    rs=gc.executeQuery(strj);//
		c=0;
		while(rs.next()){
			c++;
		}
		tj[1]=new String[c];
		rs.beforeFirst();
	    while(rs.next()){
	    	tj[1][j++]=rs.getString("id");
	    }
	    gc.close();
	    return tj;
	}
	/**
	 * 自动阅卷选择题、判断题，
	 * @param stu_id
	 * @throws SQLException
	 */
	public void autoMark(String stu_id) throws SQLException{
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		int i=0;
		int j=0;
		double c;
		double s;
		LinkedList<String> xtihao=new LinkedList<String>();
		LinkedList<String> ptihao=new LinkedList<String>();
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select paper_id from exampaper where exam_id='1'");
		rs.next();
		String paper_id=rs.getString("paper_id");
		String strx="select id from stu_answer where stu_id='"+stu_id+"'and paper_id='"+paper_id+"' and id in(select id from question where typeid='0')";
		String strp="select id from stu_answer where stu_id='"+stu_id+"'and paper_id='"+paper_id+"' and id in(select id from question where typeid='2')";
		rs=gc.executeQuery("select scorex,scorep from system where paper_id='"+paper_id+"'");//每道题分数
		rs.next();
		double scorex=rs.getDouble("scorex");
		double scorep=rs.getDouble("scorep");
		rs=gc.executeQuery(strx);//
		while(rs.next()){
			xtihao.add(rs.getString("id"));
		}
		while(i<xtihao.size()){
			rs1=gc.executeQuery("select answer from stu_answer where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+xtihao.get(i)+"'");
			rs1.next();
			rs2=gc.executeQuery("select q_answer from question where id='"+xtihao.get(i)+"'");
			rs2.next();
			if(rs1.getString("answer").equals(rs2.getString("q_answer"))){
				gc.executeUpdate("update stu_answer set point ='"+scorex+"' where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+xtihao.get(i)+"'");
				rs3=gc.executeQuery("select correctCount,selectedCount from question where id='"+xtihao.get(i)+"'");
				rs3.next();
				c=rs3.getDouble("correctCount")+1;
				s=rs3.getDouble("selectedCount")+1;
				gc.executeUpdate("update question set correctCount='"+c+"',selectedCount='"+s+"' where id='"+xtihao.get(i)+"'");
			}
			else{
				gc.executeUpdate("update stu_answer set point ='"+0+"' where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+xtihao.get(i)+"'");
				rs3=gc.executeQuery("select selectedCount from question where id='"+xtihao.get(i)+"'");
				rs3.next();
				s=rs3.getDouble("selectedCount")+1;
				gc.executeUpdate("update question set selectedCount='"+s+"' where id='"+xtihao.get(i)+"'");
			}
			i++;
		}//
		rs=gc.executeQuery(strp);
		while(rs.next()){
			ptihao.add(rs.getString("id"));
		}
		while(j<ptihao.size()){
			
			rs1=gc.executeQuery("select answer from stu_answer where stu_id='"+stu_id+"' and paper_id='"+paper_id+"' and id='"+ptihao.get(j)+"'");
			rs1.next();
			rs2=gc.executeQuery("select q_answer from question where id='"+ptihao.get(j)+"'");
			rs2.next();
			if(rs1.getString("answer").equals(rs2.getString("q_answer"))){
				gc.executeUpdate("update stu_answer set point ='"+scorep+"' where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+ptihao.get(j)+"'");
				rs3=gc.executeQuery("select correctCount,selectedCount from question where id='"+ptihao.get(j)+"'");
				rs3.next();
				c=rs3.getDouble("correctCount")+1;
				s=rs3.getDouble("selectedCount")+1;
				gc.executeUpdate("update question set correctCount='"+c+"',selectedCount='"+s+"' where id='"+ptihao.get(j)+"'");
			}
			else{
				gc.executeUpdate("update stu_answer set point ='"+0+"' where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'and id='"+ptihao.get(j)+"'");
				rs3=gc.executeQuery("select selectedCount from question where id='"+ptihao.get(j)+"'");
				rs3.next();
				s=rs3.getDouble("selectedCount")+1;
				gc.executeUpdate("update question set selectedCount='"+s+"' where id='"+ptihao.get(j)+"'");
			}
			j++;
		}
		gc.close();
	}
	/**
	 * 计算总分,返回1计算成功
	 * @param stu_id
	 * @param paper_id
	 * @return
	 * @throws SQLException
	 */
	public int countGrade(String stu_id,String paper_id) throws SQLException{
		ResultSet rs=null;
		int num;
		double score=0;
		String str="select point from stu_answer where stu_id='"+stu_id+"'and paper_id='"+paper_id+"'";
		GetConn gc=new GetConn();
		rs=gc.executeQuery(str);
		while(rs.next()){
			score+=rs.getDouble("point");
		}
		rs=gc.executeQuery("select score from grade where paper_id='"+paper_id+"' and stu_id='"+stu_id+"'");
		if(rs.next()){
			num=gc.executeUpdate("update grade set score='"+score+"' where paper_id='"+paper_id+"' and stu_id='"+stu_id+"'");
		}
		else{
			num=gc.executeUpdate("insert into grade(score,paper_id,stu_id) values('"+score+"','"+paper_id+"','"+stu_id+"')");
		}
		gc.close();
		return num;
	}
	/**
	 * 教师查询成绩学期，试卷号，班级，学号，姓名，成绩
	 * @return
	 * @throws SQLException
	 */
	public Object[][] inquiryGradeTeacher() throws SQLException{
		ResultSet rs=null;
		ResultSet rs2=null;
		int c=0;
		int i=0;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from grade");
		while(rs.next()){
			c++;
		}
		Object[][] b=new Object[c][6];
		rs.beforeFirst();
		while(rs.next()){
			b[i][0]=rs.getString("semester");
			b[i][1]=rs.getString("paper_id");
			b[i][3]=rs.getString("stu_id");
			b[i][5]=rs.getDouble("score");
			rs2=gc.executeQuery("select stu_name,class_name from student where stu_id='"+b[i][3]+"'");
			rs2.next();
			b[i][2]=rs2.getString("class_name");
			b[i][4]=rs2.getString("stu_name");
			i++;
		}
		gc.close();
		return b;
	}
	/**
	 * 学生查询成绩
	 * @param stu_id
	 * @return
	 * @throws SQLException
	 */
	public Object[][] inquiryGradeStudent(String stu_id) throws SQLException{
		ResultSet rs=null;
		int c=0;
		int i=0;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from grade where stu_id='"+stu_id+"'");
		while(rs.next()){
			c++;
		}
		Object[][] b=new Object[c][6];
		rs.beforeFirst();
		while(rs.next()){
			b[i][0]=rs.getString("semester");
			b[i][1]=rs.getString("paper_id");
			b[i][2]=rs.getDouble("score");			
			i++;
		}
		gc.close();
		return b;
	}
	/**
	 * 判断学生是否已经考过当前试卷,返回0表示已经考过，返回1表示
	 * @param stu_id
	 * @return
	 * @throws SQLException
	 */
	public int examOrNot(String stu_id) throws SQLException{
		ResultSet rs=null;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select paper_id from exampaper where exam_id='1'");
		rs.next();
		String paper=rs.getString("paper_id");
		rs=gc.executeQuery("select paper_id from stu_answer where stu_id='"+stu_id+"' and paper_id='"+paper+"'");
		if(rs.next()){
			gc.close();
			return 0;
		}
		else{
			gc.close();
			return 1;
		}
		
	}
	/**
	 * 返回各题型分值
	 * @param Paper_id
	 * @return
	 * @throws SQLException
	 */
	public double[] showTypeScore(String Paper_id) throws SQLException{
		ResultSet rs=null;
		double[] Scores=new double[4];
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select scorex,scoret,scorep,scorej from system where paper_id='"+Paper_id+"'");
		rs.next();
		Scores[0]=rs.getDouble("scorex");
		Scores[1]=rs.getDouble("scoret");
		Scores[2]=rs.getDouble("scorep");
		Scores[3]=rs.getDouble("scorej");
		gc.close();
		return Scores;
	}
/**
 * 验证是否可以查卷//密码正确返回true，不正确false
 * @param checkpwd
 * @return
 * @throws SQLException
 */
	public boolean canCheckPaper(String checkpwd) throws SQLException{
		ResultSet rs=null;
		GetConn gc=new GetConn();
		rs=gc.executeQuery("select * from checkpaper where checkpwd='"+checkpwd+"'");
		if(rs.next()){
			gc.close();
			return true;
		}
		else{
			gc.close();
			return false;
		}	
	}
}



