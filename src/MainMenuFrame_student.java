import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;


public class MainMenuFrame_student {

	public static JFrame frame;
	private BackgroundImagePanel mainPanel;
	public JLabel lblWelcome;
	private JLayeredPane layerpanel;
	private JTable tableTranscript;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void FrameMain(final String sNumber){
		final StudentAccount student=new StudentAccount(sNumber,null,null,null);
		student.ReadAccount();
		frame=new JFrame("《电子线路设计与测试》试题库系统");
		frame.setSize(541, 343);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-541)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-343)/2);
		
		mainPanel = new BackgroundImagePanel();
		mainPanel.setLayout(null);
		addDefaultLayeredPane_Student();
		lblWelcome = new JLabel("\u6B22\u8FCE\uFF01"+student.GetName()+" | 权限为学生");
		lblWelcome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWelcome.setBounds(10, 10, 435, 15);
		mainPanel.add(lblWelcome);
		
		JButton btnLogout = new JButton("\u6CE8\u9500");
		btnLogout.setContentAreaFilled(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAndSignUpFrame loginSignup=new LoginAndSignUpFrame();
				loginSignup.FrameLoginSignUp();
				frame.dispose();
			}
		});
		btnLogout.setBounds(455, 6, 70, 23);
		mainPanel.add(btnLogout);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(98, 35, 2, 270);
		mainPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 31, 515, 2);
		mainPanel.add(separator_1);
		
		JButton btnOLPractice = new JButton("\u5728\u7EBF\u7EC3\u4E60");
		btnOLPractice.setContentAreaFilled(false);
		btnOLPractice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addOLPractice(student.GetName());
			}
		});
		btnOLPractice.setBounds(10, 41, 80, 26);
		mainPanel.add(btnOLPractice);
		
		JButton btnOLTest = new JButton("\u5728\u7EBF\u8003\u8BD5");
		btnOLTest.setContentAreaFilled(false);
		btnOLTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DButil dbutil=new DButil();
					if(dbutil.examOrNot(sNumber)==0){
						JOptionPane.showMessageDialog(null,"您已经完成考试！不能重考！","错误消息",JOptionPane.ERROR_MESSAGE);
					}
					else{
					FrameOnlineTest frameOLTest=new FrameOnlineTest();
					frameOLTest.OnlineTestFrame(student.GetName(),sNumber);
					frame.setVisible(false);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnOLTest.setBounds(10, 108, 80, 26);
		mainPanel.add(btnOLTest);
		
		JButton btnScore = new JButton("\u5206\u6570\u67E5\u8BE2");
		btnScore.setContentAreaFilled(false);
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addtranscriptPane(sNumber);
			}
		});
		btnScore.setBounds(10, 175, 80, 26);
		mainPanel.add(btnScore);
		
		JButton btnInformation = new JButton("\u8D26\u6237\u4FE1\u606F");
		btnInformation.setContentAreaFilled(false);
		btnInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addInformationLayeredPane(sNumber);
			}
		});
		btnInformation.setBounds(10, 242, 80, 26);
		mainPanel.add(btnInformation);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public void addDefaultLayeredPane_Student(){
		layerpanel=new DefaultLayeredPane_Student();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addInformationLayeredPane(String sNumber){
		mainPanel.remove(layerpanel);
		layerpanel=new InformationLayeredPane(sNumber);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addOLPractice(String sName){
		mainPanel.remove(layerpanel);
		layerpanel=new OLPractice(sName);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addRevisePwd(String sNumber){
		mainPanel.remove(layerpanel);
		layerpanel=new RevisePwd(sNumber);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addtranscriptPane(String sNumber){
		mainPanel.remove(layerpanel);
		layerpanel=new transcriptPane(sNumber);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public boolean TestConnect(){
		GetConn getconn=new GetConn();
		if(getconn.getConnection()==null){
			ConnectFail();
			getconn.close();
			return false;
		}
		getconn.close();
		return true;
	}
	private static void ConnectFail(){//网络连接失败
		JOptionPane.showMessageDialog(null, "与服务器断开连接！请检查网络是否通畅", "网络连接失败", JOptionPane.ERROR_MESSAGE);
	}

	//==================================================================================================
	class DefaultLayeredPane_Student extends JLayeredPane{
		public DefaultLayeredPane_Student(){
			setBounds(100, 33, 434, 280);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			add(scrollPane);
			JTextPane txtIntroduction = new JTextPane();
			scrollPane.setViewportView(txtIntroduction);
			txtIntroduction.setEditable(false);
			txtIntroduction.setFont(txtIntroduction.getFont().deriveFont(13f));
			txtIntroduction.setText("本试题库系统的角色划分为学生和教师两类。\r" +
					"对于学生来说，具有登录、在线练习、在线考试、成绩查询、修改个人资料及密码等功能。\r" +
					"(1)登录：学生输入自己的学号和密码进行登录。管理员只提供给学生在线练习、在线考试、成绩查询、修改个人资料及密码等权限。\r" +
					"(2)在线练习：学生登录系统之后，可根据知识点、难度和题型做有针对性的考前练习工作，当学生做完题目后，系统启动自动阅卷功能并给出正解提示。\r" +
					"(3)在线考试：学生登录系统之后，可以开始计时考试。考试内容由老师指定。考试时只显示学生所选择或填写的答案，不给出对或错和答案提示。考完后提交试卷，可由系统启动客观题目的自动评分，主观题目则提交老师评分。对于一套试卷，一个学生只能考一次。\r" +
					"(4)成绩查询：学生考试或练习后，可启动成绩查询功能查询所参加的考试成绩情况。\r" +
					"(5)修改个人资料及密码：学生可自由修改个人资料及密码。个人资料包括姓名和班级。管理员可对学生资料进行审核和修改。\r" +
			//		"\r" +
					"对于教师来说，具有登录、试题管理、试卷管理、阅卷功能、成绩管理、查卷等功能。\r" +
					"(1)登录：教师根据注册的账号登录系统。注册时需输入管理员分配的邀请码。教师只能按照管理员分配的权限使用各功能选项。\r" +
					"(2)试题管理：教师对试题库进行试题录入、试题审核、增加试题、修改试题（答案）、删除试题等操作。\r" +
					"(3)试卷管理：教师可以给出对应题型、难度、考察知识点等一系列约束条件取值命令系统进行自动组卷。同时，还能够对已组试卷进行删除等操作。教师可以针对所组好的试卷进行预览、保存（Word文档）等操作。\r" +
					"(4)阅卷功能：教师可命令系统启动对学生答卷的自动阅卷功能，或根据试卷分析结果启动对特定试题及全部试题的手动阅卷。\r" +
					"(5)成绩管理：教师可以按一定检索规则查看学生成绩，并对满足特定检索条件的学生、试卷或试题启动成绩统计分析，还可按一定检索规则输出满足特定格式要求的学生成绩单（Excel表）。\r" +
					"(6)查卷功能：教师可根据学生要求进行查卷，并对批改有误的题目分数进行更正。及对学生重新统分。教师查卷时需输入管理员分配的密码。");
			txtIntroduction.setCaretPosition(0);
			scrollPane.setBounds(3, 3, 430, 270);
		}
	}
	//===========================================================================================================
	class InformationLayeredPane extends JLayeredPane{
		private JButton btnFinish;
		private JLayeredPane Information1;
		private JTextField txtName;
		private JTextField txtNumber;
		private JTextField txtClass;
		private JLayeredPane Information2;
		private String ThisName;
		private String ThisClass;
		private DefaultTableModel LogModel=new DefaultTableModel();
	
		public InformationLayeredPane(final String sNumber){
			setBounds(100, 35, 435, 278);
			
			JButton btnEdit = new JButton("\u4FEE\u6539\u8D44\u6599");
			JButton btnRevisePassword = new JButton("\u4FEE\u6539\u5BC6\u7801");
			
			if(!TestConnect()) {
				ThisName=null;
				ThisClass=null;
				btnEdit.setEnabled(false);
				btnRevisePassword.setEnabled(false);
			}
			else {
				StudentAccount student=new StudentAccount(sNumber,null,null,null);
				student.ReadAccount();
				ThisName=student.GetName();
				ThisClass=student.GetClass();
				DButil dbutil =new DButil();
				try {
					LogModel= new DefaultTableModel(
						dbutil.readLog(sNumber),
						new String[] {
							"时间", "事件"
						});
				} catch (SQLException e) {
					e.printStackTrace();
				}
				btnEdit.setEnabled(true);
				btnRevisePassword.setEnabled(true);
			}
			
			JLabel lblName = new JLabel("\u7528\u6237\u540D");
			lblName.setBounds(19, 13, 54, 15);
			add(lblName);
			
			JLabel lblNumber = new JLabel("\u5B66  \u53F7");
			lblNumber.setBounds(19, 46, 54, 15);
			add(lblNumber);
			
			JLabel lblClass = new JLabel("\u73ED  \u7EA7");
			lblClass.setBounds(19, 79, 54, 15);
			add(lblClass);
			
			JLabel lblLog = new JLabel("\u65E5  \u5FD7");
			lblLog.setBounds(19, 112, 54, 15);
			add(lblLog);
			
			txtName = new JTextField();
			txtName.setEditable(false);
			txtName.setBounds(63, 10, 120, 21);
			add(txtName);
			txtName.setText(ThisName);
			txtName.setColumns(10);
			
			txtNumber = new JTextField();
			txtNumber.setEditable(false);
			txtNumber.setBounds(63, 43, 120, 21);
			add(txtNumber);
			txtNumber.setText(sNumber);
			txtNumber.setColumns(10);
			
			txtClass = new JTextField();
			txtClass.setEditable(false);
			txtClass.setBounds(63, 76, 120, 21);
			add(txtClass);
			txtClass.setText(ThisClass);
			txtClass.setColumns(10);
			
			JScrollPane scrollpaneLog=new JScrollPane();
			scrollpaneLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollpaneLog.setBounds(63, 109, 269, 151);
			add(scrollpaneLog);
			
			final JTable LogTable=new JTable();
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setPreferredSize(new Dimension(0, 0));
			LogTable.getTableHeader().setDefaultRenderer(renderer);
			DButil dbutil=new DButil();
			LogTable.setModel(LogModel);
			scrollpaneLog.setViewportView(LogTable);
			
			Information1 = new JLayeredPane();
			Information1.setBounds(341, 197, 96, 71);
			add(Information1);
			
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtName.setEditable(true);
					txtClass.setEditable(true);
					addInformation2();
					frame.getRootPane().setDefaultButton(btnFinish);
				}
			});
			btnEdit.setBounds(1, 9, 83, 23);
			Information1.add(btnEdit);
			
			btnRevisePassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addRevisePwd(sNumber);
				}
			});
			btnRevisePassword.setBounds(1, 41, 83, 23);
			Information1.add(btnRevisePassword);
			
			Information2 = new JLayeredPane();
			Information2.setBounds(341, 197, 96, 85);
	//		InformationLayeredPane.add(Information2);
			
			btnFinish = new JButton("\u5B8C\u6210");
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!TestConnect()) return;
					StudentAccount student =new StudentAccount(sNumber,null,txtName.getText(),txtClass.getText());
					txtName.setEditable(false);
					txtName.setText(ThisName);
					txtClass.setEditable(false);
					txtClass.setText(ThisClass);
					addInformation1();
					if(student.EditAccount()!=1){
						JOptionPane.showMessageDialog(null, "修改失败！", "修改失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
					student.ReadAccount();
					txtName.setText(student.GetName());
					txtClass.setText(student.GetClass());
					lblWelcome.setText("\u6B22\u8FCE\uFF01"+student.GetName()+" | 权限为学生");
					DButil dbutil=new DButil();
					try {
						LogModel= new DefaultTableModel(
							dbutil.readLog(sNumber),
							new String[] {
								"时间", "事件"
							});
					} catch (SQLException e) {
						e.printStackTrace();
					}
					LogTable.setModel(LogModel);
				}
			});
			btnFinish.setBounds(1, 9, 83, 23);
			Information2.add(btnFinish);
			
			JButton btnBack = new JButton("\u8FD4\u56DE");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtName.setEditable(false);
					txtClass.setEditable(false);
					txtName.setText(ThisName);
					txtClass.setText(ThisClass);
					addInformation1();
				}
			});
			btnBack.setBounds(1, 41, 83, 23);
			Information2.add(btnBack);
			
		}
		private void addInformation1(){
			add(Information1);
			remove(Information2);
		}
		private void addInformation2(){
			add(Information2);
			remove(Information1);
		}
	}
	//==================================================================================================================================
	class RevisePwd extends JLayeredPane{
		private JPasswordField txtPwdOld;
		private JPasswordField txtPwdNew;
		private JPasswordField txtPwdNewAgain;
		public RevisePwd(final String sNumber){
			setBounds(100, 31, 445, 282);
			
			txtPwdOld = new JPasswordField();
			txtPwdOld.setBounds(193, 68, 93, 21);
			txtPwdOld.setEchoChar('*');
			add(txtPwdOld);
			txtPwdOld.setColumns(10);
			
			txtPwdNew = new JPasswordField();
			txtPwdNew.setBounds(193, 110, 93, 21);
			txtPwdNew.setEchoChar('*');
			add(txtPwdNew);
			txtPwdNew.setColumns(10);
			
			txtPwdNewAgain = new JPasswordField();
			txtPwdNewAgain.setBounds(193, 152, 93, 21);
			txtPwdNewAgain.setEchoChar('*');
			add(txtPwdNewAgain);
			txtPwdNewAgain.setColumns(10);
			
			JLabel lblPwdOld = new JLabel("\u539F\u5BC6\u7801");
			lblPwdOld.setBounds(147, 71, 36, 15);
			add(lblPwdOld);
			
			JLabel lblPwdNew = new JLabel("\u65B0\u5BC6\u7801");
			lblPwdNew.setBounds(147, 113, 36, 15);
			add(lblPwdNew);
			
			JLabel lblPwdNewAgain = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801");
			lblPwdNewAgain.setBounds(123, 155, 60, 15);
			add(lblPwdNewAgain);
			
			JButton btnOK = new JButton("\u786E\u5B9A");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(String.valueOf(txtPwdNew.getPassword()).length()>16||String.valueOf(txtPwdNew.getPassword()).length()<6)
					{
						NewPwdWrong();
						return;
					}
					if(!String.valueOf(txtPwdNew.getPassword()).equals(String.valueOf(txtPwdNewAgain.getPassword()))){
						PwdNotEqual();
						return;
					}
					if(String.valueOf(txtPwdOld.getPassword()).equals(String.valueOf(txtPwdNew.getPassword()))){
						OldEqualsNew();
						return;
					}
					if(!TestConnect()) return;
					int i=0;
					StudentAccount student=new StudentAccount(sNumber, null, null, null);
					i=student.RevisePwd(String.valueOf(txtPwdOld.getPassword()), 
							String.valueOf(txtPwdNew.getPassword()));
					switch(i){
					case 1: 
						ReviseSuccess();
						addInformationLayeredPane(sNumber);
						break;
					case 0: 
						ReviseFail();
						addInformationLayeredPane(sNumber);
						break;
					case -1:
						PwdNotCorrect();
						txtPwdOld.setText(null);
						txtPwdNew.setText(null);
						txtPwdNewAgain.setText(null);
						txtPwdOld.requestFocus();
						break;
					}
				}
			});
			btnOK.setBounds(96, 219, 93, 23);
			add(btnOK);
			
			JButton btnCancel = new JButton("\u8FD4\u56DE");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addInformationLayeredPane(sNumber);
				}
			});
			btnCancel.setBounds(247, 219, 93, 23);
			add(btnCancel);
			
			JLabel label_1 = new JLabel("6~16\u4F4D");
			label_1.setForeground(Color.GRAY);
			label_1.setBounds(286, 113, 54, 15);
			add(label_1);
			
			JLabel label_2 = new JLabel("6~16\u4F4D");
			label_2.setForeground(Color.GRAY);
			label_2.setBounds(286, 155, 54, 15);
			add(label_2);
			
			frame.getRootPane().setDefaultButton(btnOK);
		}
		public void PwdNotEqual(){//两次密码不同
			JOptionPane.showMessageDialog(null, "两次密码不同！", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
		public void OldEqualsNew(){//新旧密码相同
			JOptionPane.showMessageDialog(null, "新密码不能与旧密码相同！", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
		public void NewPwdWrong(){//新密码必须为6~16位
			JOptionPane.showMessageDialog(null, "新密码必须为6~16位！", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
		public void PwdNotCorrect(){//密码错误
			JOptionPane.showMessageDialog(null, "密码有误！", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
		public void ReviseFail(){//修改失败
			JOptionPane.showMessageDialog(null, "修改失败！请稍后重试", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
		public void ReviseSuccess(){//修改成功
			JOptionPane.showMessageDialog(null, "修改成功！", "修改成功", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//==================================================================================================================================
	class OLPractice extends JLayeredPane{
		public OLPractice(final String sName){
			setBounds(100, 31, 445, 282);
			//	mainPanel.add(OLPractice);
				
			JLabel lblDifficulty = new JLabel("\u96BE  \u5EA6");
			lblDifficulty.setBounds(118, 60, 54, 15);
			add(lblDifficulty);
				
			JLabel lblPoint = new JLabel("\u77E5\u8BC6\u70B9");
			lblPoint.setBounds(118, 111, 54, 15);
			add(lblPoint);
				
			JLabel lblType = new JLabel("\u9898  \u578B");
			lblType.setBounds(118, 160, 54, 15);
			add(lblType);
				
			final JComboBox boxDifficulty = new JComboBox();
			boxDifficulty.setModel(new DefaultComboBoxModel(new String[] {"\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxDifficulty.setBounds(169, 57, 122, 21);
			add(boxDifficulty);
				
			final JComboBox boxPoint = new JComboBox();
			boxPoint.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxPoint.setBounds(169, 108, 122, 21);
			add(boxPoint);
				
			final JComboBox boxType = new JComboBox();
			boxType.setModel(new DefaultComboBoxModel(new String[] {"\u968F\u673A", "\u9009\u62E9\u9898", "\u586B\u7A7A\u9898", "\u5224\u65AD\u9898", "\u7B80\u7B54\u9898"}));
			boxType.setBounds(169, 157, 122, 21);
			add(boxType);
				
			JButton btnStart = new JButton("\u5F00\u59CB");
			btnStart.setBounds(294, 217, 93, 23);
			add(btnStart);
			btnStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					FrameOnlinePractice a=new FrameOnlinePractice();
					int Point=boxPoint.getSelectedIndex()-1;
					if(Point==-1) Point=10;
					int Type=boxType.getSelectedIndex()-1;
					if(Type==-1) Type=4;
					a.OnlinePracticeFrame(sName,Point,boxDifficulty.getSelectedIndex()
							,Type);
					frame.setVisible(false);
				}
			});
		}
	}
	class transcriptPane extends JLayeredPane{
		private MyTable tableTranscript;
		private DefaultTableModel transcriptmodel;
		public transcriptPane(String sNumber){
			setBounds(91, 24, 442, 290);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 432, 280);
			add(scrollPane);
			
			tableTranscript=new MyTable();
			scrollPane.setViewportView(tableTranscript);
			transcriptmodel=new DefaultTableModel(
				null,
				new String[] {
					"\u5B66\u671F", "\u8BD5\u5377\u7F16\u53F7", "\u6210\u7EE9"
				});
			tableTranscript.setModel(transcriptmodel);
			GetTranscript(sNumber);
		}
		private void GetTranscript(String sNumber){
			if(!TestConnect()) return;
			DButil dbutil=new DButil();
			try {
				transcriptmodel=new DefaultTableModel(
					dbutil.inquiryGradeStudent(sNumber),
					new String[] {
						"\u5B66\u671F", "\u8BD5\u5377\u7F16\u53F7", "\u6210\u7EE9"
					}
				);
				tableTranscript.setModel(transcriptmodel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}