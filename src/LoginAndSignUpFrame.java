import javax.swing.*;
import javax.swing.border.LineBorder;


import com.sun.awt.AWTUtilities;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginAndSignUpFrame{

	private JFrame frame=new JFrame();
	private JPanel mainPanel;
	private int xMousePosition;
	private int yMousePosition;
	private int i;
	private JLayeredPane layerpanel;
	
	public void FrameLoginSignUp() {//��½��ע�����
		frame.setSize(339,250);
		frame.setUndecorated(true);//�����ޱ߿�
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-339)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-250)/2);//��Ļ����
		//�����϶�����
		frame.addMouseListener(new MouseAdapter(){//��õ�ǰ���λ��
			public void mousePressed(MouseEvent arg0) {
				xMousePosition=arg0.getX();
				yMousePosition=arg0.getY();
			}
		});
		frame.addMouseMotionListener(new MouseAdapter(){//����϶�ʱ����������϶������������λ�ã��ﵽ�����϶�Ч��
			public void mouseDragged(MouseEvent arg0) {
				frame.setLocation(arg0.getXOnScreen()-xMousePosition,arg0.getYOnScreen()-yMousePosition);
			}
		});
		//���������
		mainPanel=new BackgroundImagePanel();//LoginPanel();
		mainPanel.setBorder(new LineBorder(Color.GRAY));
		mainPanel.setBackground(new Color(19,179,253));//(240, 240, 240));
		mainPanel.setLayout(null);
		//��ӵ�½���
		layerpanel=new login(null);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
		//���ô�������
		frame.getContentPane().add(mainPanel);//������������
		frame.setResizable(false);//����Ϊ���ɸı��С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���ʱ�˳�����
		frame.setVisible(true);//��Ϊ�ɼ�
		//���ڵ���Ч����ʵ�ַ�ʽΪ��Ӷ�ʱ��
		i=0;
		final Timer timer=new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				AWTUtilities.setWindowOpacity(frame, (float) ((float) i/600.0));//���ô���͸����
				i++;
				if(i==600) timer.cancel();
				}
			},0,1);
	}
	private void addLogin(String Name){
		mainPanel.remove(layerpanel);
		layerpanel=new login(Name);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	private void addSignUp(String Name){
		mainPanel.remove(layerpanel);
		layerpanel=new SignUp(Name);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	//===================================================================================================================
	//������½���
	class login extends JLayeredPane{
		private JRadioButton student;
		private JRadioButton teacher;
		private JTextField txtName;
		private JPasswordField txtPwd;
		public login(String Name){
			setBounds(0,3,339,250);
			//������ǩ
			JLabel lblPwd=new JLabel("��  ��");//���������롱��ǩ
			lblPwd.setBounds(84, 141, 40, 25);
			final JLabel lblNumber=new JLabel("ѧ  ��");//������ѧ�š����û�������ǩ����ʼ��Ϊ��ѧ�š�
			lblNumber.setBounds(84, 106, 40, 25);
			//��ѡ��ť��1
			ButtonGroup group1=new ButtonGroup();
			student=new JRadioButton("ѧ��",true);//������ѧ������ѡ��ť��Ĭ��ѡ��
			student.setOpaque(false);
			student.setBounds(99, 74, 50, 26);
			student.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					lblNumber.setText("ѧ  ��");
					updateUI();
				}
			});
			group1.add(student);
			teacher=new JRadioButton("��ʦ",false);//��������ʦ����ѡ��ť��Ĭ�ϲ�ѡ��
			teacher.setOpaque(false);
			teacher.setBounds(184, 74, 50, 26);
			teacher.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					lblNumber.setText("�û���");
					updateUI();
				}
			});
			group1.add(teacher);
			//�ı���
			txtName=new JTextField(Name);//��ʼ�����û������ı���
			txtName.setBounds(134, 106, 120, 25);
			txtPwd=new JPasswordField(16);//��ʼ�������롱�ı���
			txtPwd.setEchoChar('*');
			txtPwd.setBounds(134, 141, 120, 25);
			//��ť
			JButton btnLogin = new JButton("��½");//����½����ť
			btnLogin.setOpaque(false);
			btnLogin.setBounds(59, 210, 80, 26);
			btnLogin.setContentAreaFilled(false);
			btnLogin.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					LoginAnalysis();//��Ӷ�����������ť����ʱִ��LoginAnalysis������
				}
			});
			JButton btnSignup = new JButton("ע��");//��ע�ᡱ��ť
			btnSignup.setOpaque(false);
			btnSignup.setBounds(198, 210, 80, 26);
			btnSignup.setContentAreaFilled(false);
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addSignUp(null);
				}
			});
			JButton btnExit = new JButton();//���˳�����ť
			btnExit.setBounds(305, 0, 17, 26);
			btnExit.setFocusable(false);
			btnExit.setIcon(new ImageIcon(LoginAndSignUpFrame.class.getClassLoader().getResource("res/btnExit.png")));
			btnExit.setContentAreaFilled(false);
			btnExit.setBorderPainted(false);
			btnExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(1);
				}
			});
			//���������ǩ
			JLabel logo = new JLabel("\u300a\u7535\u5b50\u7ebf\u8def\u8bbe\u8ba1\u4e0e\u6d4b\u8bd5\u300b\u8bd5\u9898\u5e93\u7cfb\u7edf");
		//	logo.setForeground(Color.WHITE);
			logo.setFont(new Font("����", Font.PLAIN, 19));
			logo.setForeground(Color.DARK_GRAY);
			logo.setBounds(10, 26, 313, 42);
			//��Ӹ���������login
			add(student);
			add(teacher);
			add(lblNumber);
			add(txtName);
			add(lblPwd);
			add(txtPwd);
			add(btnLogin);
			add(btnSignup);
			add(btnExit);
			add(logo);
			//����Ĭ�ϰ�ť
			frame.getRootPane().setDefaultButton(btnLogin);
			frame.setTitle("��½");
		}
		public void DialogLoginFail(){//�������ݴ���Ի���
			JOptionPane.showMessageDialog(null, "�������û������������ѡ������", "��½ʧ��", JOptionPane.ERROR_MESSAGE);
			txtPwd.setText(null);
		}
		
		public void DialogLoginNameNotExist(String sUserName){//�û��������ڣ�sUserNameΪ�����ı�������ʾ��Ĭ���û���
			if(JOptionPane.showConfirmDialog(null, "��ѧ�Ż��û���δע�ᣬ�Ƿ�ע�᣿", "�û���������", JOptionPane.YES_NO_OPTION)==0){
				//ת����ע�ᡱ���
				addSignUp(sUserName);
			}
			else {
				txtName.requestFocus();
				txtName.selectAll();
				txtPwd.setText(null);
			}
		}
		
		public void DialogNoEnterName(){//δ�����û���
			if(student.isSelected()) JOptionPane.showMessageDialog(null, "������ѧ�ţ�", "��½ʧ��", JOptionPane.ERROR_MESSAGE);
			else JOptionPane.showMessageDialog(null, "�������û�����", "��½ʧ��", JOptionPane.ERROR_MESSAGE);
			txtName.requestFocus();
		}
		
		public void DialogNoEnterPwd(){//δ��������
			JOptionPane.showMessageDialog(null, "���������룡", "��½ʧ��", JOptionPane.ERROR_MESSAGE);
			txtPwd.requestFocus();
		}
		
		public void DialogNumberWrong(){//ѧ�Ŵ���
			JOptionPane.showMessageDialog(null, "ѧ�ű���Ϊ9λ��", "��½ʧ��", JOptionPane.ERROR_MESSAGE);
			txtName.requestFocus();
			txtName.selectAll();
		}
		
		public void LoginSuccess(String sNumber,String sPrivilege){//�ɹ�
		//	JOptionPane.showMessageDialog(null, "��½�ɹ���", "��½�ɹ�", JOptionPane.INFORMATION_MESSAGE);
			if(sPrivilege.equals("student")){
				MainMenuFrame_student studentframe=new MainMenuFrame_student();
				studentframe.FrameMain(sNumber);
			}
			else if(sPrivilege.equals("teacher")) {
				MainMenuFrame_teacher teacherframe=new MainMenuFrame_teacher();
				teacherframe.FrameMain(sNumber);
			}
			frame.dispose();
		}
		//��½����
		public void LoginAnalysis(){
			String sName=txtName.getText();
			String sPassword=String.valueOf(txtPwd.getPassword());
			String sPrivilege;
			int i=0;
			//����Ϊ��
			if(sName.equals("")){
				DialogNoEnterName();
				return;
			}
			//����Ϊ��
			if(sPassword.equals("")){
				DialogNoEnterPwd();
				return;
			}
			if(student.isSelected()){
				//ѧ������
				if(txtName.getText().length()!=9){
					DialogNumberWrong();
				return;
				}
				if(!TestConnect()) return;
				StudentAccount studentAccount=new StudentAccount(sName, sPassword, null, null);//����ѧ���࣬����ʼ��
				i=studentAccount.Login();//i��ŵ�½���
				sPrivilege="student";
			}
			else {
				if(!TestConnect()) return;
				TeacherAccount teacherAccount=new TeacherAccount(sName, sPassword, null);//�����ʦ�࣬����ʼ��
				i=teacherAccount.Login();//i��ŵ�½���
				sPrivilege="teacher";
			}
			switch (i) {
			case 0://ʧ��
				DialogLoginFail();
				break;
			case 1://�ɹ�
				LoginSuccess(sName,sPrivilege);
				break;
			case -1://�û�����ѧ�Ų�����
				DialogLoginNameNotExist(sName);
				break;
			}
		}
	}
		
	//==============================================================================================================
	//����ע�����
	class SignUp extends JLayeredPane{
		private JRadioButton student_Sign;
		private JRadioButton teacher_Sign;
		private JTextField txtNumberS;
		private JTextField txtNameS;
		private JPasswordField txtPwdS;
		private JPasswordField txtPwdAgainS;
		private JTextField txtClassS;
		private JTextField txtNameT;
		private JPasswordField txtPwdT;
		private JPasswordField txtPwdAgainT;
		private JTextField txtNumberT;
		public SignUp(String Name){
			setBounds(0,3,339,250);
//			mainPanel.add(signup);
			//��ѡ��ť��2
			ButtonGroup group2=new ButtonGroup();
			student_Sign=new JRadioButton("ѧ��",true);//������ѧ������ѡ��ť��Ĭ��ѡ��
			student_Sign.setOpaque(false);
			student_Sign.setBounds(102, 16, 49, 26);
			final JLayeredPane StudentLayeredPane = new JLayeredPane();//����ѧ��ע�������
			final JLayeredPane TeacherLayeredPane = new JLayeredPane();//������ʦע�������
			student_Sign.addActionListener(new ActionListener() {//��Ӷ�������������ʱ
				public void actionPerformed(ActionEvent e) {
					if (TeacherLayeredPane.isShowing()) {
						txtNumberS.setText(txtNameT.getText());//����ʦ����û��������ݶ���ѧ�����
						add(StudentLayeredPane);//StudentLayeredPane�������������signup
						remove(TeacherLayeredPane);//TeacherLayeredPane���������signup�Ƴ�
						updateUI();//ˢ�����
					}
				}
			});
			group2.add(student_Sign);//�������ť��
			teacher_Sign=new JRadioButton("��ʦ",false);//��������ʦ����ѡ��ť��Ĭ�ϲ�ѡ��
			teacher_Sign.setOpaque(false);
			teacher_Sign.setBounds(187, 16, 49, 26);
			teacher_Sign.addActionListener(new ActionListener() {//��Ӷ�������������ʱ
				public void actionPerformed(ActionEvent e) {
					if (StudentLayeredPane.isShowing()) {
						txtNameT.setText(txtNumberS.getText());//��ѧ������û��������ݶ�����ʦ���
						add(TeacherLayeredPane);//TeacherLayeredPane�������������signup
						remove(StudentLayeredPane);//StudentLayeredPane���������signup�Ƴ�
						updateUI();//ˢ�����
					}
				}
			});
			group2.add(teacher_Sign);//�������ť��
			
			//������ť
			JButton btnSignup_Sign = new JButton("ע��");//��ע�ᡱ��ť
			btnSignup_Sign.setOpaque(false);
			btnSignup_Sign.setBounds(59, 210, 80, 26);
			btnSignup_Sign.setContentAreaFilled(false);
			btnSignup_Sign.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					SignUpAnalysis();//��Ӷ�����������ť����ʱִ��SignUpAnalysis�����ݽ���ע��������ش���
				}
			});
			JButton btnCancle = new JButton("ȡ��");//��ȡ������ť
			btnCancle.setOpaque(false);
			btnCancle.setBounds(198, 210, 80, 26);
			btnCancle.setContentAreaFilled(false);
			btnCancle.addActionListener(new ActionListener(){//��Ӷ��������������ڲ���
				public void actionPerformed(ActionEvent e){
					addLogin(null);
					//ת����¼���
				}
			});
			JButton btnExit_Sign = new JButton();//���˳�����ť
			btnExit_Sign.setIcon(new ImageIcon(LoginAndSignUpFrame.class.getClassLoader().getResource("res/btnExit.png")));
			btnExit_Sign.setContentAreaFilled(false);
			btnExit_Sign.setFocusable(false);
			btnExit_Sign.setBounds(305, 0, 17, 26);
			btnExit_Sign.setBorderPainted(false);
			btnExit_Sign.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(1);
				}
			});
			//����Ĭ�ϰ�ť
			frame.getRootPane().setDefaultButton(btnSignup_Sign);
			frame.setTitle("ע��");
			//==================================================================================================================
			//ѧ��ע�������
		
			StudentLayeredPane.setBounds(39, 48, 270, 152);
			//������ǩ
			JLabel lblNumberS = new JLabel("ѧ  ��");//��ѧ�š���ǩ
			lblNumberS.setBounds(42, 4, 60, 25);
			StudentLayeredPane.add(lblNumberS);
			JLabel lblName=new JLabel("�û���");//���û�������ǩ
			lblName.setBounds(42, 33, 60, 25);
			StudentLayeredPane.add(lblName);
			JLabel lblPwd=new JLabel("��  ��");//�����롱��ǩ
			lblPwd.setBounds(42, 62, 60, 25);
			StudentLayeredPane.add(lblPwd);
			JLabel lblPwdAgain = new JLabel("�ٴ�ȷ������");//���ٴ�ȷ�����롱��ǩ
			lblPwdAgain.setBounds(6, 91, 72, 25);
			StudentLayeredPane.add(lblPwdAgain);
			JLabel lblClassS = new JLabel("��  ��");//���༶����ǩ
			lblClassS.setBounds(42, 120, 60, 25);
			StudentLayeredPane.add(lblClassS);
					
			//�����ı���
			txtNumberS = new JTextField(Name);//��ѧ�š��ı���
			txtNumberS.setBounds(105, 4, 120, 25);
			StudentLayeredPane.add(txtNumberS);		
			txtNameS=new JTextField(10);//���û������ı���
			txtNameS.setBounds(105, 33, 120, 25);
			StudentLayeredPane.add(txtNameS);

			final JTextField txtPwdTip1=new JTextField(16);//�������ʾ
			txtPwdTip1.setForeground(Color.GRAY);
			txtPwdTip1.setText("6~16λ");
			txtPwdTip1.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {//�������������л������������
					StudentLayeredPane.add(txtPwdS);
					StudentLayeredPane.remove(txtPwdTip1);
					mainPanel.updateUI();
				}
				public void focusLost(FocusEvent e) {
				}
			});
			txtPwdTip1.setBounds(105, 62, 120, 25);
			StudentLayeredPane.add(txtPwdTip1);
			final JTextField txtPwdTip2=new JTextField(16);//�ٴ������������ʾ
			txtPwdTip2.setForeground(Color.GRAY);
			txtPwdTip2.setText("6~16λ");
			txtPwdTip2.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {//�������������л����ٴ����������
					StudentLayeredPane.add(txtPwdAgainS);
					StudentLayeredPane.remove(txtPwdTip2);
					mainPanel.updateUI();
				}
				public void focusLost(FocusEvent e) {
				}
			});
			txtPwdTip2.setBounds(105, 91, 120, 25);
			StudentLayeredPane.add(txtPwdTip2);
			
			txtPwdS=new JPasswordField(16);//�����롱�ı���
			txtPwdS.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//�����ƿ�����������������Ϊ����������ʾ��
					if (String.valueOf(txtPwdS.getPassword()).isEmpty()) {
						StudentLayeredPane.add(txtPwdTip1);
						StudentLayeredPane.remove(txtPwdS);
						mainPanel.updateUI();
					}
				}
			});
			txtPwdS.setEchoChar('*');
			txtPwdS.setBounds(105, 62, 120, 25);
//			StudentLayeredPane.add(txtPwdS);
			txtPwdAgainS = new JPasswordField(16);//���ٴ�ȷ�����롱�ı���
			txtPwdAgainS.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//�����ƿ�����������������Ϊ����������ʾ��
					if (String.valueOf(txtPwdAgainS.getPassword()).isEmpty()) {
						StudentLayeredPane.add(txtPwdTip2);
						StudentLayeredPane.remove(txtPwdAgainS);
						mainPanel.updateUI();
					}
				}
			});
			txtPwdAgainS.setEchoChar('*');
			txtPwdAgainS.setBounds(105, 91, 120, 25);
			txtClassS = new JTextField(10);//���༶���ı���
			txtClassS.setBounds(105, 120, 120, 25);
			StudentLayeredPane.add(txtClassS);
			//=======================================================================================================================		
			//��ʦע�������
			TeacherLayeredPane.setBounds(39, 48, 270, 152);
			//������ǩ
			lblName = new JLabel("�û���");
//			JLabel lblNameT = new JLabel("�û���");//���û�������ǩ
			lblName.setBounds(42, 10, 60, 25);
			TeacherLayeredPane.add(lblName);
			lblPwd = new JLabel("��  ��");//�����롱��ǩ
			lblPwd.setBounds(42, 45, 60, 25);
			TeacherLayeredPane.add(lblPwd);
			lblPwdAgain = new JLabel("�ٴ�ȷ������");//���ٴ�ȷ�����롱��ǩ
			lblPwdAgain.setBounds(6, 80, 78, 25);
			TeacherLayeredPane.add(lblPwdAgain);
			JLabel lblNumberT = new JLabel("������");//�������롱��ǩ
			lblNumberT.setBounds(42, 115, 60, 25);
			TeacherLayeredPane.add(lblNumberT);
			//�����ı���
			txtNameT = new JTextField(Name);//���û������ı���
			txtNameT.setBounds(105, 10, 120, 25);
			TeacherLayeredPane.add(txtNameT);

			final JTextField txtPwdTip3=new JTextField(16);//�������ʾ
			txtPwdTip3.setForeground(Color.GRAY);
			txtPwdTip3.setText("6~16λ");
			txtPwdTip3.addFocusListener(new FocusAdapter(){
				public void focusGained(FocusEvent e) {//�������������л������������
					TeacherLayeredPane.add(txtPwdT);
					TeacherLayeredPane.remove(txtPwdTip3);
					mainPanel.updateUI();
				}
			});
			txtPwdTip3.setBounds(105, 45, 120, 25);
			TeacherLayeredPane.add(txtPwdTip3);
			
			final JTextField txtPwdTip4=new JTextField(16);//�ٴ������������ʾ
			txtPwdTip4.setForeground(Color.GRAY);
			txtPwdTip4.setText("6~16λ");
			txtPwdTip4.addFocusListener(new FocusAdapter(){
				public void focusGained(FocusEvent e) {//�������������л����ٴ����������
					TeacherLayeredPane.add(txtPwdAgainT);
					TeacherLayeredPane.remove(txtPwdTip4);
					mainPanel.updateUI();
				}
			});
			txtPwdTip4.setBounds(105, 80, 120, 25);
			TeacherLayeredPane.add(txtPwdTip4);
			
			txtPwdT = new JPasswordField(16);//�����롱�ı���
			txtPwdT.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//�����ƿ�����������������Ϊ����������ʾ��
					if (String.valueOf(txtPwdT.getPassword()).isEmpty()) {
						TeacherLayeredPane.add(txtPwdTip3);
						TeacherLayeredPane.remove(txtPwdT);
						mainPanel.updateUI();
					}
				}
			});
			txtPwdT.setEchoChar('*');
			txtPwdT.setBounds(105, 45, 120, 25);
//			TeacherLayeredPane.add(txtPwdT);
			txtPwdAgainT = new JPasswordField(16);//���ٴ�ȷ�����롱�ı���
			txtPwdAgainT.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//�����ƿ�����������������Ϊ����������ʾ��
					if (String.valueOf(txtPwdAgainT.getPassword()).isEmpty()) {
						TeacherLayeredPane.add(txtPwdTip4);
						TeacherLayeredPane.remove(txtPwdAgainT);
						mainPanel.updateUI();
					}
				}
			});
			txtPwdAgainT.setEchoChar('*');
			txtPwdAgainT.setBounds(105, 80, 120, 25);
//			TeacherLayeredPane.add(txtPwdAgainT);
			txtNumberT = new JTextField(10);//�������롱�ı���
			txtNumberT.setBounds(105, 115, 120, 25);
			TeacherLayeredPane.add(txtNumberT);
			//��Ӹ���������signup
			add(StudentLayeredPane);//��ʼ��ʱĬ�Ͻ�ѧ���������������
//			add(TeacherLayeredPane);
			add(student_Sign);
			add(teacher_Sign);
			add(btnCancle);
			add(btnSignup_Sign);
			add(btnExit_Sign);
		}
		public void DialogSignUpFail(){//ע��ʧ�ܶԻ���
			JOptionPane.showMessageDialog(null, "����ϵͳԭ��ע��ʧ�ܣ�����ϵ����Ա��", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		
		public void DialogInviteCodeNotExist(){//���������Ի���
			JOptionPane.showMessageDialog(null, "�����������˶Ժ��ٴ�����", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
			txtNumberT.requestFocus();
		}
		
		public void DialogPwdNotEqual(){//�����������벻һ�¶Ի���
			JOptionPane.showMessageDialog(null, "�����������벻һ�£�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
			if(student_Sign.isSelected()){
				txtPwdS.requestFocus();
				txtPwdS.setText(null);
				txtPwdAgainS.setText(null);
			}
			else {
				txtPwdT.requestFocus();
				txtPwdT.setText(null);
				txtPwdAgainT.setText(null);
			}
		}
		
		public void DialogPwdLenthWrong(){//���볤�Ȳ���Ҫ��Ի���
			JOptionPane.showMessageDialog(null, "���볤�Ȳ���Ҫ�󣡣�6~16λ��", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
			if(student_Sign.isSelected()){
				txtPwdS.requestFocus();
				txtPwdS.setText(null);
				txtPwdAgainS.setText(null);
			}
			else {
				txtPwdT.requestFocus();
				txtPwdT.setText(null);
				txtPwdAgainT.setText(null);
			}
		}
		
		public void DialogSignUpNameExist(String sUserName){//�û����Ѵ��ڣ�sUserNameΪ�����ı�������ʾ��Ĭ���û���
			if(JOptionPane.showConfirmDialog(null, "��ѧ�Ż��û�����ע�ᣬ�Ƿ��½��", "�û����Ѵ���", JOptionPane.YES_NO_OPTION)==0){
				//ת����¼���
				addLogin(sUserName);
			}
			else if(student_Sign.isSelected()){
				txtNumberS.requestFocus();
				txtNumberS.selectAll();
			}
			else {
				txtNameT.requestFocus();
				txtNameT.selectAll();
			}
		}
		
		public void DialogNoEnterName_Sign(){//δ�����û���
			if(student_Sign.isSelected()){
				JOptionPane.showMessageDialog(null, "������ѧ�ţ�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				txtNumberS.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "�������û�����", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				txtNameT.requestFocus();
			}
		}
		
		public void DialogNumberWrong_Sign(){//ѧ�Ŵ���
			JOptionPane.showMessageDialog(null, "ѧ�ű���Ϊ9λ��", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
			txtNumberS.requestFocus();
			txtNumberS.selectAll();
		}
		
		public void DialogNoEnterPwd_Sign(){//δ��������
			JOptionPane.showMessageDialog(null, "���������룡", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
			if(student_Sign.isSelected()) txtPwdS.requestFocus();
			else txtPwdT.requestFocus();
		}
		
		public void DialogSuccess_Sign(String sNumber,String sPrivilege){//�ɹ�
			JOptionPane.showMessageDialog(null, "ע��ɹ���", "ע��ɹ�", JOptionPane.INFORMATION_MESSAGE);
			if(sPrivilege.equals("student")){
				MainMenuFrame_student studentframe=new MainMenuFrame_student();
				studentframe.FrameMain(sNumber);//ת��ѧ������
			}
			else if(sPrivilege.equals("teacher")){
				MainMenuFrame_teacher teacherframe=new MainMenuFrame_teacher();
				teacherframe.FrameMain(sNumber);//ת����ʦ����
			}
			frame.dispose();
		}
		//ע�����
		public void SignUpAnalysis() {
			String sName;
			String sPrivilege;
			int i=0;
			if(student_Sign.isSelected()){
				//ѧ��Ϊ��
				if(txtNumberS.getText().equals(""))
				{
					DialogNoEnterName_Sign();
					return;
				}
				//ѧ�ų��Ȳ���ȷ
				if(txtNumberS.getText().length()!=9)
				{
					DialogNumberWrong_Sign();
					return;
				}
				//����Ϊ��
				if(String.valueOf(txtPwdS.getPassword()).equals(""))
				{
					DialogNoEnterPwd_Sign();
					return;
				}
				//���볤�Ȳ���Ҫ��
				if(String.valueOf(txtPwdS.getPassword()).length()>16||String.valueOf(txtPwdS.getPassword()).length()<6)
				{
					DialogPwdLenthWrong();
					return;
				}
				//�������벻һ��
				if(!String.valueOf(txtPwdS.getPassword()).equals(String.valueOf(txtPwdAgainS.getPassword()))){
					DialogPwdNotEqual();
					return;
				}
				if(!TestConnect()) return;
				StudentAccount studentAccount=new StudentAccount(txtNumberS.getText(),
						String.valueOf(txtPwdS.getPassword()),txtNameS.getText(),
						txtClassS.getText());//����ѧ���࣬����ʼ��
				sName=txtNumberS.getText();
				i=studentAccount.SignUp();//���ע����
				sPrivilege="student";
			}
			else {
				//�û���Ϊ��
				if(txtNameT.getText().equals(""))
				{
					DialogNoEnterName_Sign();
					return;
				}
				//����Ϊ��
				if(String.valueOf(txtPwdT.getPassword()).equals(""))
				{
					DialogNoEnterPwd_Sign();
					return;
				}
				//���볤�Ȳ���Ҫ��
				if(String.valueOf(txtPwdT.getPassword()).length()>16||String.valueOf(txtPwdT.getPassword()).length()<6)
				{
					DialogPwdLenthWrong();
					return;
				}
				//�������벻һ��
				if(!String.valueOf(txtPwdT.getPassword()).equals(String.valueOf(txtPwdAgainT.getPassword()))){
					DialogPwdNotEqual();
					return;
				}
				if(!TestConnect()) return;
				TeacherAccount teacherAccount=new TeacherAccount(txtNameT.getText(),
						String.valueOf(txtPwdT.getPassword()),txtNumberT.getText());//�����ʦ�࣬����ʼ��
				sName=txtNameT.getText();	
				i=teacherAccount.SignUp();//���ע����
				sPrivilege="teacher";
			}
			switch (i) {
			case 0://ʧ��
				DialogSignUpFail();
				break;
			case 1://�ɹ�
				DialogSuccess_Sign(sName,sPrivilege);
				break;
			case -1://�û�����ѧ���Ѵ���
				DialogSignUpNameExist(sName);
				break;
			case 2://����ʦ������������
				DialogInviteCodeNotExist();
				break;
			}	
		}
	}
		
	//================================================================================================================================*/
	//�������
	public boolean TestConnect(){
		GetConn getconn=new GetConn();
		if(getconn.getConnection()==null){
			JOptionPane.showMessageDialog(null, "��������Ͽ����ӣ����������Ƿ�ͨ��", "��������ʧ��", JOptionPane.INFORMATION_MESSAGE);
			getconn.close();
			return false;
		}
		getconn.close();
		return true;
	}
}
