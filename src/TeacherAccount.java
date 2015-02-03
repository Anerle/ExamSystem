import java.sql.SQLException;
/*
 * 本类构造方法为TeacherAccount((String)用户名,(String)密码,(String)邀请码)
 */
public class TeacherAccount {
	private String sName;//用户名
	private String sPassword;//密码（未加密）
	private String sCode;//邀请码
	/*
	 * 构造方法
	 */
	public TeacherAccount(String Name, String Password, String Code){
		this.sName=Name;
		this.sPassword=Password;
		this.sCode=Code;
	}
	/*
	 * 登陆方法
	 */
	public int Login() {
		//返回值1代表登陆成功，-1代表用户名不存在
		//返回0代表用户名密码不匹配，登录失败
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.teacherLogin(sName, EncryptAndDecrypt(sPassword));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	/*
	 * 注册方法
	 */
	public int SignUp(){
		//返回值1代表注册成功，-1代表用户名已存在
		//返回0代表用户名或密码不符合规范，注册失败
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.teacherSignUp(sName, EncryptAndDecrypt(sPassword), sCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	/*
	 * 加密方法
	 */
	private String EncryptAndDecrypt(String sContent){//为保密，将此设为private
		byte[] Byte1;
		Byte1=sContent.getBytes();//将sContent转换为二进制
		byte[] ByteKey;
		ByteKey=sName.getBytes();//将帐号名转换为二进制
		byte[] Byte2 = new byte[Byte1.length];//定义新变量放转换后的二进制
		//加密方式为原二进制与二进制密钥周期按位异或。二进制密钥由账号名+01合成，
		//用账号名是出于随机的考虑，使两个人即使密码相同，得到的加密后密码也不相同，防止试出密码
		//加上01是为了干扰访问者，防止其看出密钥为账户名
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
