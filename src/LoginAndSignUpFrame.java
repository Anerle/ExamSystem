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
	
	public void FrameLoginSignUp() {//登陆及注册界面
		frame.setSize(339,250);
		frame.setUndecorated(true);//窗口无边框
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-339)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-250)/2);//屏幕居中
		//窗口拖动功能
		frame.addMouseListener(new MouseAdapter(){//获得当前鼠标位置
			public void mousePressed(MouseEvent arg0) {
				xMousePosition=arg0.getX();
				yMousePosition=arg0.getY();
			}
		});
		frame.addMouseMotionListener(new MouseAdapter(){//鼠标拖动时，根据鼠标拖动距离调整窗口位置，达到窗口拖动效果
			public void mouseDragged(MouseEvent arg0) {
				frame.setLocation(arg0.getXOnScreen()-xMousePosition,arg0.getYOnScreen()-yMousePosition);
			}
		});
		//构建主面板
		mainPanel=new BackgroundImagePanel();//LoginPanel();
		mainPanel.setBorder(new LineBorder(Color.GRAY));
		mainPanel.setBackground(new Color(19,179,253));//(240, 240, 240));
		mainPanel.setLayout(null);
		//添加登陆面板
		layerpanel=new login(null);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
		//设置窗体属性
		frame.getContentPane().add(mainPanel);//添加面板至窗体
		frame.setResizable(false);//设置为不可改变大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时退出程序
		frame.setVisible(true);//设为可见
		//窗口淡入效果，实现方式为添加定时器
		i=0;
		final Timer timer=new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				AWTUtilities.setWindowOpacity(frame, (float) ((float) i/600.0));//设置窗口透明度
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
	//构建登陆面板
	class login extends JLayeredPane{
		private JRadioButton student;
		private JRadioButton teacher;
		private JTextField txtName;
		private JPasswordField txtPwd;
		public login(String Name){
			setBounds(0,3,339,250);
			//创建标签
			JLabel lblPwd=new JLabel("密  码");//构建“密码”标签
			lblPwd.setBounds(84, 141, 40, 25);
			final JLabel lblNumber=new JLabel("学  号");//构建“学号”或“用户名”标签，初始化为“学号”
			lblNumber.setBounds(84, 106, 40, 25);
			//单选按钮组1
			ButtonGroup group1=new ButtonGroup();
			student=new JRadioButton("学生",true);//构建“学生”单选按钮，默认选中
			student.setOpaque(false);
			student.setBounds(99, 74, 50, 26);
			student.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					lblNumber.setText("学  号");
					updateUI();
				}
			});
			group1.add(student);
			teacher=new JRadioButton("教师",false);//构建“教师”单选按钮，默认不选中
			teacher.setOpaque(false);
			teacher.setBounds(184, 74, 50, 26);
			teacher.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					lblNumber.setText("用户名");
					updateUI();
				}
			});
			group1.add(teacher);
			//文本框
			txtName=new JTextField(Name);//初始化“用户名”文本框
			txtName.setBounds(134, 106, 120, 25);
			txtPwd=new JPasswordField(16);//初始化“密码”文本框
			txtPwd.setEchoChar('*');
			txtPwd.setBounds(134, 141, 120, 25);
			//按钮
			JButton btnLogin = new JButton("登陆");//“登陆”按钮
			btnLogin.setOpaque(false);
			btnLogin.setBounds(59, 210, 80, 26);
			btnLogin.setContentAreaFilled(false);
			btnLogin.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					LoginAnalysis();//添加动作监听，按钮按下时执行LoginAnalysis中内容
				}
			});
			JButton btnSignup = new JButton("注册");//“注册”按钮
			btnSignup.setOpaque(false);
			btnSignup.setBounds(198, 210, 80, 26);
			btnSignup.setContentAreaFilled(false);
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addSignUp(null);
				}
			});
			JButton btnExit = new JButton();//“退出”按钮
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
			//创建标题标签
			JLabel logo = new JLabel("\u300a\u7535\u5b50\u7ebf\u8def\u8bbe\u8ba1\u4e0e\u6d4b\u8bd5\u300b\u8bd5\u9898\u5e93\u7cfb\u7edf");
		//	logo.setForeground(Color.WHITE);
			logo.setFont(new Font("仿宋", Font.PLAIN, 19));
			logo.setForeground(Color.DARK_GRAY);
			logo.setBounds(10, 26, 313, 42);
			//添加各组件至面板login
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
			//设置默认按钮
			frame.getRootPane().setDefaultButton(btnLogin);
			frame.setTitle("登陆");
		}
		public void DialogLoginFail(){//密码或身份错误对话框
			JOptionPane.showMessageDialog(null, "密码与用户名不符或身份选择有误！", "登陆失败", JOptionPane.ERROR_MESSAGE);
			txtPwd.setText(null);
		}
		
		public void DialogLoginNameNotExist(String sUserName){//用户名不存在，sUserName为将在文本框中显示的默认用户名
			if(JOptionPane.showConfirmDialog(null, "该学号或用户名未注册，是否注册？", "用户名不存在", JOptionPane.YES_NO_OPTION)==0){
				//转至“注册”面板
				addSignUp(sUserName);
			}
			else {
				txtName.requestFocus();
				txtName.selectAll();
				txtPwd.setText(null);
			}
		}
		
		public void DialogNoEnterName(){//未输入用户名
			if(student.isSelected()) JOptionPane.showMessageDialog(null, "请输入学号！", "登陆失败", JOptionPane.ERROR_MESSAGE);
			else JOptionPane.showMessageDialog(null, "请输入用户名！", "登陆失败", JOptionPane.ERROR_MESSAGE);
			txtName.requestFocus();
		}
		
		public void DialogNoEnterPwd(){//未输入密码
			JOptionPane.showMessageDialog(null, "请输入密码！", "登陆失败", JOptionPane.ERROR_MESSAGE);
			txtPwd.requestFocus();
		}
		
		public void DialogNumberWrong(){//学号错误
			JOptionPane.showMessageDialog(null, "学号必须为9位！", "登陆失败", JOptionPane.ERROR_MESSAGE);
			txtName.requestFocus();
			txtName.selectAll();
		}
		
		public void LoginSuccess(String sNumber,String sPrivilege){//成功
		//	JOptionPane.showMessageDialog(null, "登陆成功！", "登陆成功", JOptionPane.INFORMATION_MESSAGE);
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
		//登陆分析
		public void LoginAnalysis(){
			String sName=txtName.getText();
			String sPassword=String.valueOf(txtPwd.getPassword());
			String sPrivilege;
			int i=0;
			//姓名为空
			if(sName.equals("")){
				DialogNoEnterName();
				return;
			}
			//密码为空
			if(sPassword.equals("")){
				DialogNoEnterPwd();
				return;
			}
			if(student.isSelected()){
				//学号有误
				if(txtName.getText().length()!=9){
					DialogNumberWrong();
				return;
				}
				if(!TestConnect()) return;
				StudentAccount studentAccount=new StudentAccount(sName, sPassword, null, null);//构造学生类，并初始化
				i=studentAccount.Login();//i存放登陆结果
				sPrivilege="student";
			}
			else {
				if(!TestConnect()) return;
				TeacherAccount teacherAccount=new TeacherAccount(sName, sPassword, null);//构造教师类，并初始化
				i=teacherAccount.Login();//i存放登陆结果
				sPrivilege="teacher";
			}
			switch (i) {
			case 0://失败
				DialogLoginFail();
				break;
			case 1://成功
				LoginSuccess(sName,sPrivilege);
				break;
			case -1://用户名或学号不存在
				DialogLoginNameNotExist(sName);
				break;
			}
		}
	}
		
	//==============================================================================================================
	//构建注册面板
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
			//单选按钮组2
			ButtonGroup group2=new ButtonGroup();
			student_Sign=new JRadioButton("学生",true);//构建“学生”单选按钮，默认选中
			student_Sign.setOpaque(false);
			student_Sign.setBounds(102, 16, 49, 26);
			final JLayeredPane StudentLayeredPane = new JLayeredPane();//创建学生注册子面板
			final JLayeredPane TeacherLayeredPane = new JLayeredPane();//创建老师注册子面板
			student_Sign.addActionListener(new ActionListener() {//添加动作监听，单击时
				public void actionPerformed(ActionEvent e) {
					if (TeacherLayeredPane.isShowing()) {
						txtNumberS.setText(txtNameT.getText());//将老师面板用户名框内容读入学生面板
						add(StudentLayeredPane);//StudentLayeredPane子面板添加至面板signup
						remove(TeacherLayeredPane);//TeacherLayeredPane子面板从面板signup移除
						updateUI();//刷新面板
					}
				}
			});
			group2.add(student_Sign);//添加至按钮组
			teacher_Sign=new JRadioButton("教师",false);//构建“教师”单选按钮，默认不选中
			teacher_Sign.setOpaque(false);
			teacher_Sign.setBounds(187, 16, 49, 26);
			teacher_Sign.addActionListener(new ActionListener() {//添加动作监听，单击时
				public void actionPerformed(ActionEvent e) {
					if (StudentLayeredPane.isShowing()) {
						txtNameT.setText(txtNumberS.getText());//将学生面板用户名框内容读入老师面板
						add(TeacherLayeredPane);//TeacherLayeredPane子面板添加至面板signup
						remove(StudentLayeredPane);//StudentLayeredPane子面板从面板signup移除
						updateUI();//刷新面板
					}
				}
			});
			group2.add(teacher_Sign);//添加至按钮组
			
			//构建按钮
			JButton btnSignup_Sign = new JButton("注册");//“注册”按钮
			btnSignup_Sign.setOpaque(false);
			btnSignup_Sign.setBounds(59, 210, 80, 26);
			btnSignup_Sign.setContentAreaFilled(false);
			btnSignup_Sign.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					SignUpAnalysis();//添加动作监听，按钮按下时执行SignUpAnalysis中内容进行注册请求相关处理
				}
			});
			JButton btnCancle = new JButton("取消");//“取消”按钮
			btnCancle.setOpaque(false);
			btnCancle.setBounds(198, 210, 80, 26);
			btnCancle.setContentAreaFilled(false);
			btnCancle.addActionListener(new ActionListener(){//添加动作监听，匿名内部类
				public void actionPerformed(ActionEvent e){
					addLogin(null);
					//转至登录面板
				}
			});
			JButton btnExit_Sign = new JButton();//“退出”按钮
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
			//设置默认按钮
			frame.getRootPane().setDefaultButton(btnSignup_Sign);
			frame.setTitle("注册");
			//==================================================================================================================
			//学生注册子面板
		
			StudentLayeredPane.setBounds(39, 48, 270, 152);
			//创建标签
			JLabel lblNumberS = new JLabel("学  号");//“学号”标签
			lblNumberS.setBounds(42, 4, 60, 25);
			StudentLayeredPane.add(lblNumberS);
			JLabel lblName=new JLabel("用户名");//“用户名”标签
			lblName.setBounds(42, 33, 60, 25);
			StudentLayeredPane.add(lblName);
			JLabel lblPwd=new JLabel("密  码");//“密码”标签
			lblPwd.setBounds(42, 62, 60, 25);
			StudentLayeredPane.add(lblPwd);
			JLabel lblPwdAgain = new JLabel("再次确认密码");//“再次确认密码”标签
			lblPwdAgain.setBounds(6, 91, 72, 25);
			StudentLayeredPane.add(lblPwdAgain);
			JLabel lblClassS = new JLabel("班  级");//“班级”标签
			lblClassS.setBounds(42, 120, 60, 25);
			StudentLayeredPane.add(lblClassS);
					
			//创建文本框
			txtNumberS = new JTextField(Name);//“学号”文本框
			txtNumberS.setBounds(105, 4, 120, 25);
			StudentLayeredPane.add(txtNumberS);		
			txtNameS=new JTextField(10);//“用户名”文本框
			txtNameS.setBounds(105, 33, 120, 25);
			StudentLayeredPane.add(txtNameS);

			final JTextField txtPwdTip1=new JTextField(16);//密码框提示
			txtPwdTip1.setForeground(Color.GRAY);
			txtPwdTip1.setText("6~16位");
			txtPwdTip1.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {//如果被点击，则切换到密码输入框
					StudentLayeredPane.add(txtPwdS);
					StudentLayeredPane.remove(txtPwdTip1);
					mainPanel.updateUI();
				}
				public void focusLost(FocusEvent e) {
				}
			});
			txtPwdTip1.setBounds(105, 62, 120, 25);
			StudentLayeredPane.add(txtPwdTip1);
			final JTextField txtPwdTip2=new JTextField(16);//再次输入密码框提示
			txtPwdTip2.setForeground(Color.GRAY);
			txtPwdTip2.setText("6~16位");
			txtPwdTip2.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {//如果被点击，则切换到再次输入密码框
					StudentLayeredPane.add(txtPwdAgainS);
					StudentLayeredPane.remove(txtPwdTip2);
					mainPanel.updateUI();
				}
				public void focusLost(FocusEvent e) {
				}
			});
			txtPwdTip2.setBounds(105, 91, 120, 25);
			StudentLayeredPane.add(txtPwdTip2);
			
			txtPwdS=new JPasswordField(16);//“密码”文本框
			txtPwdS.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//焦点移开后如果密码框中内容为空则切至提示框
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
			txtPwdAgainS = new JPasswordField(16);//“再次确认密码”文本框
			txtPwdAgainS.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//焦点移开后如果密码框中内容为空则切至提示框
					if (String.valueOf(txtPwdAgainS.getPassword()).isEmpty()) {
						StudentLayeredPane.add(txtPwdTip2);
						StudentLayeredPane.remove(txtPwdAgainS);
						mainPanel.updateUI();
					}
				}
			});
			txtPwdAgainS.setEchoChar('*');
			txtPwdAgainS.setBounds(105, 91, 120, 25);
			txtClassS = new JTextField(10);//“班级”文本框
			txtClassS.setBounds(105, 120, 120, 25);
			StudentLayeredPane.add(txtClassS);
			//=======================================================================================================================		
			//教师注册子面板
			TeacherLayeredPane.setBounds(39, 48, 270, 152);
			//创建标签
			lblName = new JLabel("用户名");
//			JLabel lblNameT = new JLabel("用户名");//“用户名”标签
			lblName.setBounds(42, 10, 60, 25);
			TeacherLayeredPane.add(lblName);
			lblPwd = new JLabel("密  码");//“密码”标签
			lblPwd.setBounds(42, 45, 60, 25);
			TeacherLayeredPane.add(lblPwd);
			lblPwdAgain = new JLabel("再次确认密码");//“再次确认密码”标签
			lblPwdAgain.setBounds(6, 80, 78, 25);
			TeacherLayeredPane.add(lblPwdAgain);
			JLabel lblNumberT = new JLabel("邀请码");//“邀请码”标签
			lblNumberT.setBounds(42, 115, 60, 25);
			TeacherLayeredPane.add(lblNumberT);
			//创建文本框
			txtNameT = new JTextField(Name);//“用户名”文本框
			txtNameT.setBounds(105, 10, 120, 25);
			TeacherLayeredPane.add(txtNameT);

			final JTextField txtPwdTip3=new JTextField(16);//密码框提示
			txtPwdTip3.setForeground(Color.GRAY);
			txtPwdTip3.setText("6~16位");
			txtPwdTip3.addFocusListener(new FocusAdapter(){
				public void focusGained(FocusEvent e) {//如果被点击，则切换到密码输入框
					TeacherLayeredPane.add(txtPwdT);
					TeacherLayeredPane.remove(txtPwdTip3);
					mainPanel.updateUI();
				}
			});
			txtPwdTip3.setBounds(105, 45, 120, 25);
			TeacherLayeredPane.add(txtPwdTip3);
			
			final JTextField txtPwdTip4=new JTextField(16);//再次输入密码框提示
			txtPwdTip4.setForeground(Color.GRAY);
			txtPwdTip4.setText("6~16位");
			txtPwdTip4.addFocusListener(new FocusAdapter(){
				public void focusGained(FocusEvent e) {//如果被点击，则切换到再次输入密码框
					TeacherLayeredPane.add(txtPwdAgainT);
					TeacherLayeredPane.remove(txtPwdTip4);
					mainPanel.updateUI();
				}
			});
			txtPwdTip4.setBounds(105, 80, 120, 25);
			TeacherLayeredPane.add(txtPwdTip4);
			
			txtPwdT = new JPasswordField(16);//“密码”文本框
			txtPwdT.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//焦点移开后如果密码框中内容为空则切至提示框
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
			txtPwdAgainT = new JPasswordField(16);//“再次确认密码”文本框
			txtPwdAgainT.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent e) {
				}
				public void focusLost(FocusEvent e) {//焦点移开后如果密码框中内容为空则切至提示框
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
			txtNumberT = new JTextField(10);//“邀请码”文本框
			txtNumberT.setBounds(105, 115, 120, 25);
			TeacherLayeredPane.add(txtNumberT);
			//添加各组件至面板signup
			add(StudentLayeredPane);//初始化时默认将学生面板添加至主面板
//			add(TeacherLayeredPane);
			add(student_Sign);
			add(teacher_Sign);
			add(btnCancle);
			add(btnSignup_Sign);
			add(btnExit_Sign);
		}
		public void DialogSignUpFail(){//注册失败对话框
			JOptionPane.showMessageDialog(null, "由于系统原因注册失败，请联系管理员！", "注册失败", JOptionPane.ERROR_MESSAGE);
		}
		
		public void DialogInviteCodeNotExist(){//邀请码错误对话框
			JOptionPane.showMessageDialog(null, "邀请码错误，请核对后再次输入", "注册失败", JOptionPane.ERROR_MESSAGE);
			txtNumberT.requestFocus();
		}
		
		public void DialogPwdNotEqual(){//两次密码输入不一致对话框
			JOptionPane.showMessageDialog(null, "两次密码输入不一致！", "注册失败", JOptionPane.ERROR_MESSAGE);
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
		
		public void DialogPwdLenthWrong(){//密码长度不合要求对话框
			JOptionPane.showMessageDialog(null, "密码长度不合要求！（6~16位）", "注册失败", JOptionPane.ERROR_MESSAGE);
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
		
		public void DialogSignUpNameExist(String sUserName){//用户名已存在，sUserName为将在文本框中显示的默认用户名
			if(JOptionPane.showConfirmDialog(null, "该学号或用户名已注册，是否登陆？", "用户名已存在", JOptionPane.YES_NO_OPTION)==0){
				//转至登录面板
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
		
		public void DialogNoEnterName_Sign(){//未输入用户名
			if(student_Sign.isSelected()){
				JOptionPane.showMessageDialog(null, "请输入学号！", "注册失败", JOptionPane.ERROR_MESSAGE);
				txtNumberS.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "请输入用户名！", "注册失败", JOptionPane.ERROR_MESSAGE);
				txtNameT.requestFocus();
			}
		}
		
		public void DialogNumberWrong_Sign(){//学号错误
			JOptionPane.showMessageDialog(null, "学号必须为9位！", "注册失败", JOptionPane.ERROR_MESSAGE);
			txtNumberS.requestFocus();
			txtNumberS.selectAll();
		}
		
		public void DialogNoEnterPwd_Sign(){//未输入密码
			JOptionPane.showMessageDialog(null, "请输入密码！", "注册失败", JOptionPane.ERROR_MESSAGE);
			if(student_Sign.isSelected()) txtPwdS.requestFocus();
			else txtPwdT.requestFocus();
		}
		
		public void DialogSuccess_Sign(String sNumber,String sPrivilege){//成功
			JOptionPane.showMessageDialog(null, "注册成功！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
			if(sPrivilege.equals("student")){
				MainMenuFrame_student studentframe=new MainMenuFrame_student();
				studentframe.FrameMain(sNumber);//转至学生界面
			}
			else if(sPrivilege.equals("teacher")){
				MainMenuFrame_teacher teacherframe=new MainMenuFrame_teacher();
				teacherframe.FrameMain(sNumber);//转至老师界面
			}
			frame.dispose();
		}
		//注册分析
		public void SignUpAnalysis() {
			String sName;
			String sPrivilege;
			int i=0;
			if(student_Sign.isSelected()){
				//学号为空
				if(txtNumberS.getText().equals(""))
				{
					DialogNoEnterName_Sign();
					return;
				}
				//学号长度不正确
				if(txtNumberS.getText().length()!=9)
				{
					DialogNumberWrong_Sign();
					return;
				}
				//密码为空
				if(String.valueOf(txtPwdS.getPassword()).equals(""))
				{
					DialogNoEnterPwd_Sign();
					return;
				}
				//密码长度不合要求
				if(String.valueOf(txtPwdS.getPassword()).length()>16||String.valueOf(txtPwdS.getPassword()).length()<6)
				{
					DialogPwdLenthWrong();
					return;
				}
				//两次密码不一致
				if(!String.valueOf(txtPwdS.getPassword()).equals(String.valueOf(txtPwdAgainS.getPassword()))){
					DialogPwdNotEqual();
					return;
				}
				if(!TestConnect()) return;
				StudentAccount studentAccount=new StudentAccount(txtNumberS.getText(),
						String.valueOf(txtPwdS.getPassword()),txtNameS.getText(),
						txtClassS.getText());//构造学生类，并初始化
				sName=txtNumberS.getText();
				i=studentAccount.SignUp();//存放注册结果
				sPrivilege="student";
			}
			else {
				//用户名为空
				if(txtNameT.getText().equals(""))
				{
					DialogNoEnterName_Sign();
					return;
				}
				//密码为空
				if(String.valueOf(txtPwdT.getPassword()).equals(""))
				{
					DialogNoEnterPwd_Sign();
					return;
				}
				//密码长度不合要求
				if(String.valueOf(txtPwdT.getPassword()).length()>16||String.valueOf(txtPwdT.getPassword()).length()<6)
				{
					DialogPwdLenthWrong();
					return;
				}
				//两次密码不一致
				if(!String.valueOf(txtPwdT.getPassword()).equals(String.valueOf(txtPwdAgainT.getPassword()))){
					DialogPwdNotEqual();
					return;
				}
				if(!TestConnect()) return;
				TeacherAccount teacherAccount=new TeacherAccount(txtNameT.getText(),
						String.valueOf(txtPwdT.getPassword()),txtNumberT.getText());//构造教师类，并初始化
				sName=txtNameT.getText();	
				i=teacherAccount.SignUp();//存放注册结果
				sPrivilege="teacher";
			}
			switch (i) {
			case 0://失败
				DialogSignUpFail();
				break;
			case 1://成功
				DialogSuccess_Sign(sName,sPrivilege);
				break;
			case -1://用户名或学号已存在
				DialogSignUpNameExist(sName);
				break;
			case 2://（教师）邀请码有误
				DialogInviteCodeNotExist();
				break;
			}	
		}
	}
		
	//================================================================================================================================*/
	//断网检测
	public boolean TestConnect(){
		GetConn getconn=new GetConn();
		if(getconn.getConnection()==null){
			JOptionPane.showMessageDialog(null, "与服务器断开连接！请检查网络是否通畅", "网络连接失败", JOptionPane.INFORMATION_MESSAGE);
			getconn.close();
			return false;
		}
		getconn.close();
		return true;
	}
}
