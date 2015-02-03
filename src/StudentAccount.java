import java.sql.SQLException;

public class StudentAccount {
	private String sNumber;//ѧ��
	private String sPassword;
	private String sName;//ѧ������
	private String sClass;
	public StudentAccount(String Number,String Password,String Name,String Class){
		this.sNumber=Number;
		this.sPassword=Password;
		this.sName=Name;
		this.sClass=Class;
	}
	public int Login(){
		//����ֵ1�����½�ɹ���-1�����û���������
		//����0�����û������벻ƥ�䣬��¼ʧ��
		//��½��Ϻ��� Log("��½");
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.studentLogin(sNumber, EncryptAndDecrypt(sPassword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) Log("��½");
		return i;
	}
	
	public int SignUp(){
		//����ֵ1����ע��ɹ���-1�����û����Ѵ���
		//����0�����û��������벻���Ϲ淶��ע��ʧ��
		//��½��Ϻ��� Log("ע��");
		DButil dbutil=new DButil();
		int i=0;
		try {
			i=dbutil.studentSignUp(sNumber, sName, sClass, EncryptAndDecrypt(sPassword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) Log("ע��");
		return i;
	}
	
	public int ReadAccount(){
		//��ȡ�˻���Ϣ
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
	
	public int EditAccount(){//�༭�˻���Ϣ
		//�޸ĺ��� Log("�޸ĸ�����Ϣ")
	//	NewAccount();
	//	Log("�޸ĸ�����Ϣ");//��¼��־
		DButil dbutil=new DButil();
		if(dbutil.updateStudent(sNumber,sName,sClass)==1){
			Log("�޸ĸ�����Ϣ");
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
			Log("�޸�����");
		}
		return i;
	}
	
	public int Log(String sBehavior){//�洢�û���¼��������־���ļ���"log"��Ŀ¼.//Accounts//�û���//
		//String sBehaviorΪ�¼���
		//��ʽΪ Time +sBehavior\n
		DButil dbutil=new DButil();
		if(dbutil.addLog(sNumber, sBehavior, TimeUtils.GetTime(0))==1) return 1;
		return 0;
	}
	
	private String EncryptAndDecrypt(String sContent){//Ϊ���ܣ�������Ϊprivate
		byte[] Byte1;
		Byte1=sContent.getBytes();//��sContentת��Ϊ������
		byte[] ByteKey;
		ByteKey=sNumber.getBytes();//���ʺ���ת��Ϊ������
		byte[] Byte2 = new byte[Byte1.length];//�����±�����ת����Ķ�����
		//���ܷ�ʽΪԭ���������������Կ���ڰ�λ��򡣶�������Կ���˺���+01�ϳɣ�
		//���˺����ǳ�������Ŀ��ǣ�ʹ�����˼�ʹ������ͬ���õ��ļ��ܺ�����Ҳ����ͬ����ֹ�Գ�����
		//����01��Ϊ���Ի��ƽ��ߣ���ֹ�俴����ԿΪ�˻���
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
