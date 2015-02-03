import java.sql.SQLException;
/*
 * ���๹�췽��ΪTeacherAccount((String)�û���,(String)����,(String)������)
 */
public class TeacherAccount {
	private String sName;//�û���
	private String sPassword;//���루δ���ܣ�
	private String sCode;//������
	/*
	 * ���췽��
	 */
	public TeacherAccount(String Name, String Password, String Code){
		this.sName=Name;
		this.sPassword=Password;
		this.sCode=Code;
	}
	/*
	 * ��½����
	 */
	public int Login() {
		//����ֵ1�����½�ɹ���-1�����û���������
		//����0�����û������벻ƥ�䣬��¼ʧ��
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
	 * ע�᷽��
	 */
	public int SignUp(){
		//����ֵ1����ע��ɹ���-1�����û����Ѵ���
		//����0�����û��������벻���Ϲ淶��ע��ʧ��
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
	 * ���ܷ���
	 */
	private String EncryptAndDecrypt(String sContent){//Ϊ���ܣ�������Ϊprivate
		byte[] Byte1;
		Byte1=sContent.getBytes();//��sContentת��Ϊ������
		byte[] ByteKey;
		ByteKey=sName.getBytes();//���ʺ���ת��Ϊ������
		byte[] Byte2 = new byte[Byte1.length];//�����±�����ת����Ķ�����
		//���ܷ�ʽΪԭ���������������Կ���ڰ�λ��򡣶�������Կ���˺���+01�ϳɣ�
		//���˺����ǳ�������Ŀ��ǣ�ʹ�����˼�ʹ������ͬ���õ��ļ��ܺ�����Ҳ����ͬ����ֹ�Գ�����
		//����01��Ϊ�˸��ŷ����ߣ���ֹ�俴����ԿΪ�˻���
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
