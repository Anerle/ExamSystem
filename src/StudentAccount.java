import java.sql.SQLException;

public class StudentAccount {
	private String sNumber;//学号
	private String sPassword;
	private String sName;//学生姓名
	private String sClass;
	public StudentAccount(String Number,String Password,String Name,String Class){
		this.sNumber=Number;
		this.sPassword=Password;
		this.sName=Name;
		this.sClass=Class;
	}
	public int Login(){
		//返回值1代表登陆成功，-1代表用户名不存在
		//返回0代表用户名密码不匹配，登录失败
		//登陆完毕后需 Log("登陆");
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.studentLogin(sNumber, EncryptAndDecrypt(sPassword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) Log("登陆");
		return i;
	}
	
	public int SignUp(){
		//返回值1代表注册成功，-1代表用户名已存在
		//返回0代表用户名或密码不符合规范，注册失败
		//登陆完毕后需 Log("注册");
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.studentSignUp(sNumber, sName, sClass, EncryptAndDecrypt(sPassword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) Log("注册");
		return i;
	}
	
	public int ReadAccount(){
		//读取账户信息
		String[] StudentInf=new String[2];
		DButil dbutil=new DButil();
		try {
			StudentInf=dbutil.studentById(sNumber);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return 0;
		}
		sName=StudentInf[0];
		sClass=StudentInf[1];
		return 1;
		
	}
	public String GetName(){
		return sName;
	}
	public String GetClass(){
		return sClass;
	}
	
	public int EditAccount(){//编辑账户信息
		//修改后需 Log("修改个人信息")
	//	NewAccount();
	//	Log("修改个人信息");//记录日志
		DButil dbutil=new DButil();
		if(dbutil.updateStudent(sNumber,sName,sClass)==1){
			Log("修改个人信息");
			return 1;
		}
		return 0;
	}
	public int RevisePwd(String oldPwd, String newPwd){
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.updatePwd(sNumber,EncryptAndDecrypt(oldPwd),EncryptAndDecrypt(newPwd));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1){
			Log("修改密码");
		}
		return i;
	}
	
	public int Log(String sBehavior){//存储用户登录、操作日志。文件名"log"，目录.//Accounts//用户名//
		//String sBehavior为事件。
		//格式为 Time +sBehavior\n
		DButil dbutil=new DButil();
		if(dbutil.addLog(sNumber, sBehavior, TimeUtils.GetTime(0))==1) return 1;
		return 0;
	}
	
	private String EncryptAndDecrypt(String sContent){//为保密，将此设为private
		byte[] Byte1;
		Byte1=sContent.getBytes();//将sContent转换为二进制
		byte[] ByteKey;
		ByteKey=sNumber.getBytes();//将帐号名转换为二进制
		byte[] Byte2 = new byte[Byte1.length];//定义新变量放转换后的二进制
		//加密方式为原二进制与二进制密钥周期按位异或。二进制密钥由账号名+01合成，
		//用账号名是出于随机的考虑，使两个人即使密码相同，得到的加密后密码也不相同，防止试出密码
		//加上01是为了迷惑破解者，防止其看出密钥为账户名
		for(int i=0,m=0;i<Byte1.length;i++,m++){
			if(m==ByteKey.length+1){
				Byte2[i]=(byte) (Byte1[i]^1);
				m=0;
			}
			if(m==ByteKey.length){
				Byte2[i]=(byte) (Byte1[i]^0);
			}
			else Byte2[i]=(byte) (Byte1[i]^ByteKey[m]);
		}
		String s=new String(Byte2);
		return s;
	}
}
