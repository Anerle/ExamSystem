import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.lowagie.text.DocumentException;
import com.mysql.jdbc.PacketTooBigException;

public class MainMenuFrame_teacher {
	private JLayeredPane layerpanel;
	public static JFrame frame;
	private BackgroundImagePanel mainPanel;
	public byte[] PicBytes;
	public static int RefreshState=0;//需刷新的状态，为0为不用刷新，为1为需刷新答卷表，为2为需刷新成绩表
	private boolean Admission=false;//查卷许可
	
/*	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel");
				//	JDialog.setDefaultLookAndFeelDecorated(true);
					MainMenuFrame_teacher a=new MainMenuFrame_teacher();
					a.FrameMain("zhou");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"SubstanceGraphitefailedtoinitialize");
				}
			}
		});
	}*/
	//教师主界面
	public void FrameMain(String Name){
		frame=new JFrame("《电子线路设计与测试》试题库系统");
		frame.setSize(680, 420);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-680)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-420)/2);
		//主面板
		mainPanel = new BackgroundImagePanel();
		mainPanel.setLayout(null);
		addDefaultLayeredPane();//添加默认panel至操作区
		JLabel lblWelcome = new JLabel("\u6B22\u8FCE\uFF01"+Name+" | 权限为教师");//欢迎标签
		lblWelcome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWelcome.setBounds(110, 10, 445, 15);
		mainPanel.add(lblWelcome);
		//注销按钮
		JButton btnLogout = new JButton("\u6CE8\u9500");
		btnLogout.setContentAreaFilled(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAndSignUpFrame loginsignup=new LoginAndSignUpFrame();
				loginsignup.FrameLoginSignUp();
				frame.dispose();
			}
		});
		btnLogout.setBounds(572, 6, 80, 23);
		mainPanel.add(btnLogout);
		//分割线
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(98, 47, 2, 335);
		mainPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 35, 654, 2);
		mainPanel.add(separator_1);
		//试卷管理按钮
		JButton btnPapersManage = new JButton("\u8BD5\u5377\u7BA1\u7406");
		btnPapersManage.setContentAreaFilled(false);
		btnPapersManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admission=false;//查卷许可重新置为否
				addPapersManagementPane();
			}
		});
		btnPapersManage.setBounds(10, 57, 80, 26);
		mainPanel.add(btnPapersManage);
		//试题管理按钮
		JButton btnQuestionsManage = new JButton("\u8BD5\u9898\u7BA1\u7406");
		btnQuestionsManage.setContentAreaFilled(false);
		btnQuestionsManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admission=false;//查卷许可重新置为否
				addQuestionsManagementPane();
			}
		});
		btnQuestionsManage.setBounds(10, 140, 80, 26);
		mainPanel.add(btnQuestionsManage);
		//在线阅卷按钮
		JButton btnOnlineCorrect = new JButton("\u5728\u7EBF\u6279\u6539");
		btnOnlineCorrect.setContentAreaFilled(false);
		btnOnlineCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admission=false;//查卷许可重新置为否
				addOnlineCorrectPane();
			}
		});
		btnOnlineCorrect.setBounds(10, 223, 80, 26);
		mainPanel.add(btnOnlineCorrect);
		//分数表按钮
		JButton btnScoreTable = new JButton("\u5206\u6570\u8868");
		btnScoreTable.setContentAreaFilled(false);
		btnScoreTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addTranscriptTablePane();
			}
		});
		btnScoreTable.setBounds(10, 306, 80, 26);
		mainPanel.add(btnScoreTable);
		//窗体属性
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.setResizable(false);
		frame.setVisible(true);
		//设置刷新时机
		frame.addHierarchyListener(new HierarchyListener(){
			public void hierarchyChanged(HierarchyEvent arg0) {
				if(RefreshState==1){//如果是从在线批改界面切换回来，答卷列表刷新
					addOnlineCorrectPane();
					RefreshState=0;//置零
				}
				if(RefreshState==2){//如果是从查卷界面切换回来，成绩表刷新
					addTranscriptTablePane();
					RefreshState=0;//置零
				}
			}
		});
	}
	//测试网络连接
	public static boolean TestConnect(){
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
	public void addDefaultLayeredPane(){//添加默认面板至主界面
//		mainPanel.remove(layerpanel);
		layerpanel=new DefaultLayeredPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addPapersManagementPane(){//添加试卷管理面板至主界面
		mainPanel.remove(layerpanel);
		layerpanel=new PapersManagementPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addBuildPaperPane(){//添加组卷界面
		mainPanel.remove(layerpanel);
		layerpanel=new BuildPaperPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addQuestionsManagementPane(){//添加试题管理界面
		mainPanel.remove(layerpanel);
		layerpanel=new QuestionsManagementPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addAddQuestionPane(int AddorEdit,String id,String Question,String Question_Ans,int Difficulty,int Point,int Type,int Mode){//添加试题添加或编辑界面
		mainPanel.remove(layerpanel);
		layerpanel=new AddQuestionPane(AddorEdit,id,Question,Question_Ans,Difficulty,Point,Type,Mode);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addOnlineCorrectPane(){//添加阅卷界面
		mainPanel.remove(layerpanel);
		layerpanel=new OnlineCorrectPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addTranscriptTablePane(){//添加成绩表界面
		mainPanel.remove(layerpanel);
		layerpanel=new TranscriptTablePane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}

	//=======================================================================================================
	//默认面板
	class DefaultLayeredPane extends JLayeredPane{
		//构造方法
		public DefaultLayeredPane(){
			setBounds(100, 35, 564, 347);
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
		//	txtIntroduction.set
			txtIntroduction.setCaretPosition(0);
			scrollPane.setBounds(10, 5, 544, 342);
	//		add(txtIntroduction);
		}
	}
	//===============================================================================================================
	//试卷管理界面
	class PapersManagementPane extends JLayeredPane{
		public int xMousePosition;
		public int yMousePosition;
		private JDialog Paper_Filter;
		private JTextField txtIDPaperFilter;
		private JComboBox boxPaperFilterDifficulty;
		private JComboBox boxPaperFilterPoint;
		private JTextField txtPaperFilterSumScore;
		private TableRowSorter<TableModel> PaperSorter;
		private MyTable tablePaper;
		private DefaultTableModel PaperModel;
		//构造方法
		public PapersManagementPane(){
			setBounds(100, 35, 564, 347);
			
			JToolBar toolBar_Paper = new JToolBar();
			toolBar_Paper.setFloatable(false);
			toolBar_Paper.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			toolBar_Paper.setBounds(376, 9, 178, 19);
			add(toolBar_Paper);
			JButton btnAdd_Paper = new JButton();//组卷按钮
			btnAdd_Paper.setContentAreaFilled(false);
			btnAdd_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnAdd.png")));
		//	btnAdd_Paper.setFocusable(false);
			btnAdd_Paper.setToolTipText("\u6DFB\u52A0");
			btnAdd_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addBuildPaperPane();//转至组卷界面
				}
			});
			toolBar_Paper.add(btnAdd_Paper);
			
			JButton btnDelete_Paper = new JButton();//删除试卷按钮;
			btnDelete_Paper.setContentAreaFilled(false);
			btnDelete_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnDelete.png")));
		//	btnDelete_Paper.setFocusable(false);
			btnDelete_Paper.setToolTipText("\u5220\u9664");
			btnDelete_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablePaper.getSelectedRow()==-1){//判断选择的个数是否大于0
						NoRowSelected();
						return;
					}
					else if(JOptionPane.showConfirmDialog(null, "您确认要删除选中试题吗？", "确认删除", JOptionPane.YES_NO_OPTION)==1) 
						return;
					DButil dbutils=new DButil();
					if(!TestConnect()) return;
					String UsedPaper="";//存放已被使用的试卷号
					int m=0;
					try {
						for(int i=0;i<tablePaper.getSelectedRowCount();i++){
							if(dbutils.deletePaper((String)tablePaper.getValueAt(tablePaper.getSelectedRows()[i], 1))==-1){
								//如果已被使用，将试卷号添加至UsedPaper
								UsedPaper+=(String)tablePaper.getValueAt(tablePaper.getSelectedRows()[i], 1)+"、";
								m=1;
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(m==1){
						//需去掉UsedPaper末尾的“、”号
						char[] n=new char[UsedPaper.length()-1];//先转成字符数组
						UsedPaper.getChars(0, UsedPaper.length()-1, n, 0);//去掉末尾的“、”号
						JOptionPane.showMessageDialog(null, new String(n)+"已被使用，无法删除！", null, JOptionPane.WARNING_MESSAGE);//显示
					}
					GetPapers();
				}
			});
			toolBar_Paper.add(btnDelete_Paper);
			
			JSeparator separatorToolBar_Paper = new JSeparator();
			separatorToolBar_Paper.setOrientation(SwingConstants.VERTICAL);
			toolBar_Paper.add(separatorToolBar_Paper);
			
			JButton btnFilter_Paper = new JButton();//查找按钮
			btnFilter_Paper.setContentAreaFilled(false);
			btnFilter_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSearch.png")));
		//	btnFilter_Paper.setFocusable(false);
			btnFilter_Paper.setToolTipText("\u67E5\u627E");
			btnFilter_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Paper_Filter();
				}
			});
			toolBar_Paper.add(btnFilter_Paper);
			
			JButton btnPreview_Paper = new JButton();//"预览"按钮
			btnPreview_Paper.setContentAreaFilled(false);
			btnPreview_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnPreview.png")));
		//	btnPreview_Paper.setFocusable(false);
			btnPreview_Paper.setToolTipText("预览");
			btnPreview_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablePaper.getSelectedRowCount()==0){//判断是否有选择一行
						NoRowSelected();
						return;
					}
					if(tablePaper.getSelectedRowCount()>1){//判断是否选择多行
						JOptionPane.showMessageDialog(null, "只能选定一个试卷预览！", "无法执行", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!TestConnect()) return;
					PreviewPaper((String) tablePaper.getValueAt(tablePaper.getSelectedRow(),1));//预览
				}
			});
			toolBar_Paper.add(btnPreview_Paper);
			
			JButton btnRefresh_Paper = new JButton();//刷新按钮
			btnRefresh_Paper.setContentAreaFilled(false);
			btnRefresh_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnRefresh.png")));
		//	btnRefresh_Paper.setFocusable(false);
			btnRefresh_Paper.setToolTipText("\u5237\u65B0");
			btnRefresh_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GetPapers();//刷新
				}
			});
			toolBar_Paper.add(btnRefresh_Paper);
			
			JSeparator separatorToolBar2_Paper = new JSeparator();
			separatorToolBar2_Paper.setOrientation(SwingConstants.VERTICAL);
			toolBar_Paper.add(separatorToolBar2_Paper);
			
			JButton btnSetPaper_Paper = new JButton();//"设为考试试卷"按钮
			btnSetPaper_Paper.setContentAreaFilled(false);
			btnSetPaper_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSetAs.png")));
		//	btnSetPaper_Paper.setFocusable(false);
			btnSetPaper_Paper.setToolTipText("设为考试试卷");
			btnSetPaper_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablePaper.getSelectedRowCount()==0){//是否选择一行
						NoRowSelected();
						return;
					}
					if(tablePaper.getSelectedRowCount()>1){//是否选择多行
						NotOneRowSelected();
						return;
					}
					if(!TestConnect()) return;
					DButil dbutil=new DButil();
					dbutil.addExamPaper((String) tablePaper.getValueAt(tablePaper.getSelectedRow(),1));//设为考试试卷
					GetPapers();
				}
			});
			toolBar_Paper.add(btnSetPaper_Paper);
			
			JScrollPane scrollPane_Paper = new JScrollPane();
			scrollPane_Paper.setBounds(10, 37, 544, 310);
			add(scrollPane_Paper);
			
			tablePaper = new MyTable();//试卷表
			tablePaper.getTableHeader().setReorderingAllowed(false);
			PaperModel=new DefaultTableModel(
				null,
				new String[] {
					"考试试卷", "\u8BD5\u5377\u7F16\u53F7", "\u96BE\u5EA6", "\u5305\u542B\u77E5\u8BC6\u70B9", "时间", "\u6EE1\u5206"
				}	
			);
			tablePaper.setModel(PaperModel);
			PaperSorter = new TableRowSorter<TableModel>(PaperModel);
			tablePaper.setRowSorter(PaperSorter);
			GetPapers();//刷新试卷表
			tablePaper.getColumnModel().getColumn(0).setPreferredWidth(63);
			tablePaper.getColumnModel().getColumn(1).setPreferredWidth(100);
			tablePaper.getColumnModel().getColumn(2).setPreferredWidth(44);
			tablePaper.getColumnModel().getColumn(3).setPreferredWidth(281);
			tablePaper.getColumnModel().getColumn(4).setPreferredWidth(50);
			tablePaper.getColumnModel().getColumn(5).setPreferredWidth(50);
			tablePaper.addMouseListener(new MouseAdapter(){//添加双击预览功能
				public void mouseClicked(MouseEvent e){
					if (e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {//鼠标左键双击
						if(tablePaper.getSelectedRowCount()==1){
							if(!TestConnect()) return;
							PreviewPaper((String) tablePaper.getValueAt(tablePaper.getSelectedRow(),1));
						}
					}
				}
			});
			scrollPane_Paper.setViewportView(tablePaper);
		}
		//刷新试卷表
		private void GetPapers(){
			if(!TestConnect()) return;
			DButil dbutil=new DButil();
			try {
				PaperModel=new DefaultTableModel(
					dbutil.readAllPaper(),
					new String[] {
						"考试试卷", "\u8BD5\u5377\u7F16\u53F7", "\u96BE\u5EA6", "\u5305\u542B\u77E5\u8BC6\u70B9", "时间", "\u6EE1\u5206"
					}
				);
				tablePaper.setModel(PaperModel);
				PaperSorter = new TableRowSorter<TableModel>(PaperModel);
				tablePaper.setRowSorter(PaperSorter);
				tablePaper.getColumnModel().getColumn(0).setPreferredWidth(63);
				tablePaper.getColumnModel().getColumn(1).setPreferredWidth(100);
				tablePaper.getColumnModel().getColumn(2).setPreferredWidth(44);
				tablePaper.getColumnModel().getColumn(3).setPreferredWidth(281);
				tablePaper.getColumnModel().getColumn(4).setPreferredWidth(50);
				tablePaper.getColumnModel().getColumn(5).setPreferredWidth(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//预览试卷
		private void PreviewPaper(final String PaperID){
			final JDialog preview=new JDialog(frame,"预览 试卷号："+PaperID);
		//	preview.setUndecorated(true);
			preview.setSize(380, 520);
			preview.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-380)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-520)/2);
			//布局
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setBackground(Color.WHITE);
			scrollPane.setViewportView(textPane);
			//定义字体格式
			SimpleAttributeSet BOLD = new SimpleAttributeSet();//粗体
			StyleConstants.setBold(BOLD, true);
			SimpleAttributeSet KAIFONT = new SimpleAttributeSet();//楷体
			StyleConstants.setFontFamily(KAIFONT,"楷体");
			SimpleAttributeSet GRAY = new SimpleAttributeSet();//灰色字体
			StyleConstants.setForeground(GRAY, Color.DARK_GRAY);
			SimpleAttributeSet FANGFONT = new SimpleAttributeSet();//仿宋体
			StyleConstants.setFontFamily(FANGFONT,"仿宋");
			//文本框内容
			DButil dbutil=new DButil();
			Document doc = textPane.getDocument();
			try {
				String[] IDs=dbutil.checkPaper(PaperID);
				double[] Scores=dbutil.showTypeScore(PaperID);
				
				for(int i=0;i<IDs.length;i++){
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//获取试题信息
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==0) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//试题编号
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "分) ",
								KAIFONT);//分值
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//试题内容
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//有图片则显示
							doc.insertString(doc.getLength(), "\n", null);
							continue;
						} else {
							ImageIcon image = picture.BytesToIcon(buffer);
							textPane.insertIcon(picture.PreviewPicture(image,
									270, 270));
						}
						doc.insertString(doc.getLength(), "\n\n", null);
						//	doc.insertString(doc.getLength(), (int) QuestionInformation[3]+"\n"+"\n", YAFONT);
					}
				}
				
				for(int i=0;i<IDs.length;i++){
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//获取试题信息
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==1) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//试题编号
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "分) ",
								KAIFONT);//分值
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//试题内容
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//有图片则显示
							doc.insertString(doc.getLength(), "\n", null);
							continue;
						} else {
							ImageIcon image = picture.BytesToIcon(buffer);
							textPane.insertIcon(picture.PreviewPicture(image,
									270, 270));
						}
						doc.insertString(doc.getLength(), "\n\n", null);
						//	doc.insertString(doc.getLength(), (int) QuestionInformation[3]+"\n"+"\n", YAFONT);
					}
				}
				
				for(int i=0;i<IDs.length;i++){
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//获取试题信息
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==2) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//试题编号
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "分) ",
								KAIFONT);//分值
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//试题内容
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//有图片则显示
							doc.insertString(doc.getLength(), "\n", null);
							continue;
						} else {
							ImageIcon image = picture.BytesToIcon(buffer);
							textPane.insertIcon(picture.PreviewPicture(image,
									270, 270));
						}
						doc.insertString(doc.getLength(), "\n\n", null);
						//	doc.insertString(doc.getLength(), (int) QuestionInformation[3]+"\n"+"\n", YAFONT);
					}
				}
				
				for(int i=0;i<IDs.length;i++){
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//获取试题信息
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==3) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//试题编号
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "分) ",
								KAIFONT);//分值
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//试题内容
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//有图片则显示
							doc.insertString(doc.getLength(), "\n", null);
							continue;
						} else {
							ImageIcon image = picture.BytesToIcon(buffer);
							textPane.insertIcon(picture.PreviewPicture(image,
									270, 270));
						}
						doc.insertString(doc.getLength(), "\n\n", null);
						//	doc.insertString(doc.getLength(), (int) QuestionInformation[3]+"\n"+"\n", YAFONT);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			textPane.setCaretPosition(0);//光标移至顶部
			textPane.setFont(new Font("宋体", Font.PLAIN, 15));
			JButton btnExport=new JButton("保存为Word文档");
			btnExport.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DocumentExporter exp=new DocumentExporter();
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
					fileChooser.setFileFilter(new FileNameExtensionFilter("Word文档(*.doc)","doc"));
					fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
					fileChooser.setApproveButtonText("保存");
					int option=fileChooser.showSaveDialog(null);
					if(option==JFileChooser.CANCEL_OPTION) return;
					if(option==JFileChooser.APPROVE_OPTION){
						try {
							if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".doc")){
								exp.exportWord(PaperID,new File(fileChooser.getSelectedFile().getAbsolutePath()));
							}
							else exp.exportWord(PaperID,new File(fileChooser.getSelectedFile().getAbsolutePath()+".doc"));
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			preview.getContentPane().add("Center",scrollPane);
			preview.getContentPane().add("South",btnExport);
			preview.setVisible(true);
		}
		//试卷查找对话框
		private void Paper_Filter(){
			Paper_Filter=new JDialog(frame,"查找",true);
			Paper_Filter.setSize(245, 232);
			Paper_Filter.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-245)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-232)/2);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			Paper_Filter.getContentPane().add(panel);
			
			JLabel lblIDFilter = new JLabel("\u8BD5\u5377\u7F16\u53F7");
			lblIDFilter.setBounds(33, 12, 54, 15);
			panel.add(lblIDFilter);
			
			JButton btnFilterFind = new JButton("\u67E5\u627E");//查找按钮
			btnFilterFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
					filters.add(RowFilter.regexFilter(txtIDPaperFilter.getText(),1));//试卷编号添加至筛选列
					if(boxPaperFilterDifficulty.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter("^"+(String)boxPaperFilterDifficulty.getSelectedItem()+"$",2));//难度添加至筛选列
					if(boxPaperFilterPoint.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter((String)boxPaperFilterPoint.getSelectedItem(),3));//知识点添加至筛选列
					if(!txtPaperFilterSumScore.getText().isEmpty()) filters.add(RowFilter.regexFilter("^"+txtPaperFilterSumScore.getText()+"$",5));//满分添加至筛选列
					PaperSorter.setRowFilter(RowFilter.andFilter(filters));
					Paper_Filter.dispose();
				}
			});
			btnFilterFind.setBounds(73, 169, 93, 23);
			panel.add(btnFilterFind);
			
			txtIDPaperFilter = new JTextField();//试卷编号
			txtIDPaperFilter.setBounds(91, 9, 104, 21);
			panel.add(txtIDPaperFilter);
			txtIDPaperFilter.setColumns(10);
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(new TitledBorder(null, "\u7B5B\u9009\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			layeredPane.setBounds(10, 39, 218, 119);
			panel.add(layeredPane);
			
			JLabel lblDifficultyFilter = new JLabel("\u96BE    \u5EA6");
			lblDifficultyFilter.setBounds(23, 24, 54, 15);
			layeredPane.add(lblDifficultyFilter);
			
			JLabel lblPointFilter = new JLabel("\u542B\u77E5\u8BC6\u70B9");
			lblPointFilter.setBounds(23, 57, 54, 15);
			layeredPane.add(lblPointFilter);
			
			JLabel lblScoreFilter = new JLabel("\u6EE1    \u5206");
			lblScoreFilter.setBounds(23, 90, 54, 15);
			layeredPane.add(lblScoreFilter);
			
			boxPaperFilterDifficulty = new JComboBox();//难度
			boxPaperFilterDifficulty.setModel(new DefaultComboBoxModel(new String[] {"全部", "\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxPaperFilterDifficulty.setSelectedIndex(0);
			boxPaperFilterDifficulty.setBounds(81, 21, 121, 21);
			layeredPane.add(boxPaperFilterDifficulty);
			
			boxPaperFilterPoint = new JComboBox();//包含知识点
			boxPaperFilterPoint.setModel(new DefaultComboBoxModel(new String[] {"(无)", "\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxPaperFilterPoint.setSelectedIndex(0);
			boxPaperFilterPoint.setBounds(81, 54, 121, 21);
			layeredPane.add(boxPaperFilterPoint);
			
			txtPaperFilterSumScore = new JTextField();//满分
			txtPaperFilterSumScore.setBounds(81, 87, 66, 21);
			layeredPane.add(txtPaperFilterSumScore);
			txtPaperFilterSumScore.setColumns(10);
			
			Paper_Filter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Paper_Filter.getRootPane().setDefaultButton(btnFilterFind);//设置默认按钮
			Paper_Filter.setResizable(false);
			Paper_Filter.setVisible(true);
		}
		private void NoRowSelected(){//未选择任何行
			JOptionPane.showMessageDialog(null, "未选择任何行！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		private void NotOneRowSelected(){//设置考试试卷时选择多行
			JOptionPane.showMessageDialog(null, "只能设置一个试卷为考试试卷！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
	}
	//================================================================================================================
	//组卷界面
	class BuildPaperPane extends JLayeredPane{
		private JTextField txtTotal;
		private JTextField txtTime;
		private JComboBox boxDifficulty;
		private JButton btnBuild;
		private JSlider sliderType1;
		private JSlider sliderType2;
		private JSlider sliderType3;
		private JSlider sliderType4;
		private JTextField txtType1;
		private JTextField txtType2;
		private JTextField txtType3;
		private JTextField txtType4;
		private JCheckBox[] Points=new JCheckBox[10];
		private JComboBox[] boxPointsPercent=new JComboBox[10];
		//构造方法
		public BuildPaperPane(){
			setBounds(100, 35, 564, 347);
		//	mainPanel.add(BuildPaperPane);
			
			JLabel lblTotal = new JLabel("\u8BD5\u5377\u603B\u5206");
			lblTotal.setBounds(63, 15, 54, 15);
			add(lblTotal);
			
			JLabel lblTime = new JLabel("\u8003\u8BD5\u65F6\u95F4");
			lblTime.setBounds(321, 15, 54, 15);
			add(lblTime);
			
			JLabel lblDifficulty = new JLabel("\u96BE    \u5EA6");
			lblDifficulty.setBounds(63, 48, 54, 15);
			add(lblDifficulty);
			
			JLabel lblPoint = new JLabel("\u77E5 \u8BC6 \u70B9");
			lblPoint.setBounds(63, 81, 54, 15);
			add(lblPoint);
			
			JLabel lblPercentage = new JLabel("\u5206\u503C\u6BD4\u4F8B");
			lblPercentage.setBounds(63, 256, 54, 15);
			add(lblPercentage);
			
			txtTotal = new JTextField();
			txtTotal.setBounds(127, 12, 54, 21);
			add(txtTotal);
			txtTotal.setColumns(3);
			
			final JPopupMenu TotalpopupMenu = new JPopupMenu();
			
			JLabel label = new JLabel("\u8bf7\u8f93\u5165\u6b63\u6574\u6570\uff01");//“请输入正整数”弹出框
			label.setForeground(Color.RED);
			TotalpopupMenu.add(label);
			txtTotal.addKeyListener(new KeyAdapter(){
				@Override
				public void keyReleased(KeyEvent e) {
					try{
						if(Integer.parseInt(txtTotal.getText())>0){//判断总分是否为大于零
							//如果是
							if(TotalpopupMenu.isShowing()) {//弹出框消失
							TotalpopupMenu.setVisible(false);
							}
							try{
								//再判断时间是否为正整数，如果是，组卷按钮可点
								if(Integer.parseInt(txtTime.getText())>0) btnBuild.setEnabled(true);
							}catch(Exception NotInt){
							}
						}
						else{//如果不是，弹出提示
							TotalpopupMenu.show(txtTotal, 0, 20);
							txtTotal.requestFocus();
							btnBuild.setEnabled(false);
						}
					}catch(Exception NotInt){//如果不是整数，弹出框
						TotalpopupMenu.show(txtTotal, 0, 20);
						txtTotal.requestFocus();
						btnBuild.setEnabled(false);
					}
				}
			});
			
			txtTime = new JTextField();
			txtTime.setBounds(385, 12, 54, 21);
			add(txtTime);
			txtTime.setColumns(10);
			
			final JPopupMenu TimepopupMenu = new JPopupMenu();
			
			JLabel label_1 = new JLabel("\u8bf7\u8f93\u5165\u6b63\u6574\u6570\uff01");//“请输入正整数”弹出框
			label_1.setForeground(Color.RED);
			TimepopupMenu.add(label_1);
			txtTime.addKeyListener(new KeyAdapter(){
				public void keyReleased(KeyEvent e) {
					try{
						if(Integer.parseInt(txtTime.getText())>0){//判断时间是否大于零
							//如果是
							if(TimepopupMenu.isShowing()) {//弹出框消失
							TimepopupMenu.setVisible(false);
							}
							try{
								//再判断总分是否为正整数，如果是，组卷按钮可点
								if(Integer.parseInt(txtTotal.getText())>0) btnBuild.setEnabled(true);
							}catch(Exception NotInt){
							}
						}
						else {//如果不是，弹出提示
							TimepopupMenu.show(txtTime, 0, 20);
							txtTime.requestFocus();
							btnBuild.setEnabled(false);
						}
					}catch(Exception NotInt){//如果不是整数，弹出框
						TimepopupMenu.show(txtTime, 0, 20);
						txtTime.requestFocus();
						btnBuild.setEnabled(false);
					}
				}
			});
			boxDifficulty = new JComboBox();
			boxDifficulty.setBounds(127, 45, 102, 21);
			boxDifficulty.setModel(new DefaultComboBoxModel(new String[] {"\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			add(boxDifficulty);
			
			btnBuild = new JButton("\u7EC4\u5377");//组卷按钮
			btnBuild.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BuildPaper();
				}
			});
			btnBuild.setEnabled(false);
			btnBuild.setBounds(126, 310, 93, 23);
			add(btnBuild);
			
			JButton btnBack1 = new JButton("\u8FD4\u56DE");//返回按钮
			btnBack1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addPapersManagementPane();
				}
			});
			btnBack1.setBounds(345, 310, 93, 23);
			add(btnBack1);
			
			JLabel lblType1 = new JLabel("\u9009\u62E9\u9898");
			lblType1.setBounds(127, 256, 54, 15);
			add(lblType1);
			
			JLabel lblType2 = new JLabel("\u586B\u7A7A\u9898");
			lblType2.setBounds(127, 283, 54, 15);
			add(lblType2);
			
			JLabel lblType3 = new JLabel("\u5224\u65AD\u9898");
			lblType3.setBounds(312, 256, 54, 15);
			add(lblType3);
			
			JLabel lblType4 = new JLabel("\u7B80\u7B54\u9898");
			lblType4.setBounds(312, 283, 54, 15);
			add(lblType4);
			//题型比例滑动条
			sliderType1 = new JSlider();
			sliderType1.setBounds(164, 252, 102, 22);
			sliderType1.setValue(10);
			sliderType1.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent arg0) {
					if(sliderType1.getValueIsAdjusting()) txtType1.setText(String.valueOf((sliderType1.getValue()+5)/10*10));
				}
			});
			add(sliderType1);
			
			sliderType2 = new JSlider();
			sliderType2.setValue(10);
			sliderType2.setBounds(164, 279, 102, 22);
			add(sliderType2);
			sliderType2.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent arg0) {
					if(sliderType2.getValueIsAdjusting()) txtType2.setText(String.valueOf((sliderType2.getValue()+5)/10*10));
				}
			});
			
			sliderType3 = new JSlider();
			sliderType3.setBounds(349, 252, 102, 22);
			sliderType3.setValue(10);
			sliderType3.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent arg0) {
					if(sliderType3.getValueIsAdjusting()) txtType3.setText(String.valueOf((sliderType3.getValue()+5)/10*10));
				}
			});
			add(sliderType3);
			
			sliderType4 = new JSlider();
			sliderType4.setBounds(349, 279, 102, 22);
			sliderType4.setValue(70);
			sliderType4.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent arg0) {
					if(sliderType4.getValueIsAdjusting()) txtType4.setText(String.valueOf((sliderType4.getValue()+5)/10*10));
				}
			});
			add(sliderType4);
			//显示题型比例滑动条的数值
			txtType1 = new JTextField("10");
			txtType1.setBorder(null);
			txtType1.setHorizontalAlignment(SwingConstants.TRAILING);
			txtType1.setEditable(false);
			txtType1.setBounds(267, 256, 18, 15);
			add(txtType1);
			txtType1.setColumns(3);
			
			txtType2 = new JTextField("10");
			txtType2.setBorder(null);
			txtType2.setHorizontalAlignment(SwingConstants.TRAILING);
			txtType2.setEditable(false);
			txtType2.setColumns(3);
			txtType2.setBounds(267, 283, 18, 15);
			add(txtType2);
			
			txtType3 = new JTextField("10");
			txtType3.setBorder(null);
			txtType3.setHorizontalAlignment(SwingConstants.TRAILING);
			txtType3.setEditable(false);
			txtType3.setBounds(452, 256, 18, 15);
			add(txtType3);
			txtType3.setColumns(3);
			
			txtType4 = new JTextField("70");
			txtType4.setBorder(null);
			txtType4.setHorizontalAlignment(SwingConstants.TRAILING);
			txtType4.setEditable(false);
			txtType4.setBounds(452, 283, 18, 15);
			add(txtType4);
			txtType4.setColumns(3);
			
			JLabel label1 = new JLabel("%");
			label1.setBounds(286, 256, 13, 15);
			add(label1);
			
			JLabel label2 = new JLabel("%");
			label2.setBounds(286, 283, 13, 15);
			add(label2);
			
			JLabel label3 = new JLabel("%");
			label3.setBounds(471, 256, 13, 15);
			add(label3);
			
			JLabel label4 = new JLabel("%");
			label4.setBounds(471, 283, 13, 15);
			add(label4);
			
			JLabel label5 = new JLabel("\uFF08\u5206\u949F\uFF09");
			label5.setBounds(436, 15, 48, 15);
			add(label5);
			
			JLayeredPane layeredPanePoints = new JLayeredPane();
			layeredPanePoints.setBounds(127, 78, 343, 166);
			add(layeredPanePoints);
			//知识点多选框
			Points[0]=new JCheckBox("\u6570\u5B57\u949F");
			Points[0].setBounds(10, 4, 103, 23);
			
			Points[1]=new JCheckBox("\u7535\u5B50\u6D4B\u91CF\u539F\u7406");
			Points[1].setBounds(10, 31, 103, 23);
			
			Points[2]=new JCheckBox("\u5355\u7BA1\u653E\u5927");
			Points[2].setBounds(10, 58, 103, 23);
			
			Points[3]=new JCheckBox("\u6570\u7535\u57FA\u7840");
			Points[3].setBounds(10, 85, 103, 23);
			
			Points[4]=new JCheckBox("\u7535\u8DEF\u8C03\u8BD5\u539F\u7406");
			Points[4].setBounds(10, 112, 103, 23);
			
			Points[5]=new JCheckBox("\u51FD\u6570\u53D1\u751F\u5668");
			Points[5].setBounds(168, 4, 103, 23);
			
			Points[6]=new JCheckBox("\u97F3\u54CD\u653E\u5927\u5668");
			Points[6].setBounds(168, 31, 103, 23);
			
			Points[7]=new JCheckBox("MSI\u65F6\u5E8F\u903B\u8F91");
			Points[7].setBounds(168, 58, 103, 23);
			
			Points[8]=new JCheckBox("\u51FD\u6570\u53D1\u751F\u5668");
			Points[8].setBounds(168, 85, 103, 23);
			
			Points[9]=new JCheckBox("\u8FD0\u653E\u5E94\u7528");
			Points[9].setBounds(168, 112, 103, 23);
			//添加按下监听，根据多选按钮状态更改比例下拉框的可用状态
			Points[0].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[0].isSelected()) boxPointsPercent[0].setEnabled(true);
					else{
						boxPointsPercent[0].setEnabled(false);
					}
				}
			});
			Points[1].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[1].isSelected()) boxPointsPercent[1].setEnabled(true);
					else{
						boxPointsPercent[1].setEnabled(false);
					}
				}
			});		
			Points[2].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[2].isSelected()) boxPointsPercent[2].setEnabled(true);
					else{
						boxPointsPercent[2].setEnabled(false);
					}
				}
			});	
			Points[3].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[3].isSelected()) boxPointsPercent[3].setEnabled(true);
					else{
						boxPointsPercent[3].setEnabled(false);
					}
				}
			});	
			Points[4].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[4].isSelected()) boxPointsPercent[4].setEnabled(true);
					else{
						boxPointsPercent[4].setEnabled(false);
					}
				}
			});	
			Points[5].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[5].isSelected()) boxPointsPercent[5].setEnabled(true);
					else{
						boxPointsPercent[5].setEnabled(false);
					}
				}
			});	
			Points[6].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[6].isSelected()) boxPointsPercent[6].setEnabled(true);
					else{
						boxPointsPercent[6].setEnabled(false);
					}
				}
			});	
			Points[7].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[7].isSelected()) boxPointsPercent[7].setEnabled(true);
					else{
						boxPointsPercent[7].setEnabled(false);
					}
				}
			});	
			Points[8].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[8].isSelected()) boxPointsPercent[8].setEnabled(true);
					else{
						boxPointsPercent[8].setEnabled(false);
					}
				}
			});	
			Points[9].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(Points[9].isSelected()) boxPointsPercent[9].setEnabled(true);
					else{
						boxPointsPercent[9].setEnabled(false);
					}
				}
			});
			//定义知识点比例下拉框
			for(int i=0;i<boxPointsPercent.length;i++){
				boxPointsPercent[i] = new JComboBox();
	//			boxPointsPercent[i].setColumns(3);
				boxPointsPercent[i].setModel(new DefaultComboBoxModel(new String[] {"10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%"}));
				boxPointsPercent[i].setEnabled(false);
				layeredPanePoints.add(boxPointsPercent[i]);
			}
	
			boxPointsPercent[0].setBounds(113, 5, 50, 21);
			boxPointsPercent[1].setBounds(113, 32, 50, 21);
			boxPointsPercent[2].setBounds(113, 59, 50, 21);
			boxPointsPercent[3].setBounds(113, 86, 50, 21);
			boxPointsPercent[4].setBounds(113, 113, 50, 21);
			boxPointsPercent[5].setBounds(271, 5, 50, 21);
			boxPointsPercent[6].setBounds(271, 32, 50, 21);
			boxPointsPercent[7].setBounds(271, 59, 50, 21);
			boxPointsPercent[8].setBounds(271, 86, 50, 21);
			boxPointsPercent[9].setBounds(271, 113, 50, 21);
			
			layeredPanePoints.add(Points[0]);
			layeredPanePoints.add(Points[1]);
			layeredPanePoints.add(Points[2]);
			layeredPanePoints.add(Points[3]);
			layeredPanePoints.add(Points[4]);
			layeredPanePoints.add(Points[5]);
			layeredPanePoints.add(Points[6]);
			layeredPanePoints.add(Points[7]);
			layeredPanePoints.add(Points[8]);
			layeredPanePoints.add(Points[9]);
			//全选按钮
			JButton btnSelectAll = new JButton("\u5168\u9009");
			btnSelectAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int i=0;i<Points.length;i++){
						Points[i].setSelected(true);
						boxPointsPercent[i].setEnabled(true);
					}
				}
			});
			btnSelectAll.setBounds(41, 139, 93, 23);
			layeredPanePoints.add(btnSelectAll);
			//反选按钮
			JButton btnSelectInvert = new JButton("\u53CD\u9009");
			btnSelectInvert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int i=0;i<Points.length;i++){
						if(Points[i].isSelected()){
							Points[i].setSelected(false);
							boxPointsPercent[i].setEnabled(false);
						}
						else{
							Points[i].setSelected(true);
							boxPointsPercent[i].setEnabled(true);
						}
					}
				}
			});
			btnSelectInvert.setBounds(175, 139, 93, 23);
			layeredPanePoints.add(btnSelectInvert);
		}
		//组卷前的分析算法
		private void BuildPaper(){
			try {
				int TotalScore=Integer.parseInt(txtTotal.getText());
				int Time=Integer.parseInt(txtTime.getText());
				int Difficulty=boxDifficulty.getSelectedIndex();
				double Point[]=new double[10];
				double Type[]=new double[4];
				double PointsTotal=0;
				double TypesTotal=0;
				//计算知识点总比例
				for(int i=0;i<10;i++){
					if(Points[i].isSelected()) Point[i]=(double)(boxPointsPercent[i].getSelectedIndex()+1.0)/10.0;
					else Point[i]=0;
			//		System.out.println(Point[i]+"");
					PointsTotal+=Point[i];
				}
			//	System.out.println(PointsTotal+"");
				//如果知识点总比例不为一，提示
				if(Math.abs(1.0-PointsTotal)>0.01){
					PointsWrong();
					return;
				}
				Type[0]=(double)Integer.parseInt(txtType1.getText())/100.0;
				Type[1]=(double)Integer.parseInt(txtType2.getText())/100.0;
				Type[2]=(double)Integer.parseInt(txtType3.getText())/100.0;
				Type[3]=(double)Integer.parseInt(txtType4.getText())/100.0;
				TypesTotal=Type[0]+Type[1]+Type[2]+Type[3];
			//	System.out.println(TypesTotal+"");
				//如果题型总比例不为一，提示
				if(Math.abs(1.0-TypesTotal)>0.01){
					TypesWrong();
					return;
				}
				if(!TestConnect()) return;
				MakeupPapers makeuppapers=new MakeupPapers();
				int count=makeuppapers.getQdetails(Point, Type, Difficulty, TotalScore, Time);
				if(count>0){
					JOptionPane.showMessageDialog(null, "组卷成功！","提示消息",JOptionPane.INFORMATION_MESSAGE);
					addPapersManagementPane();
				}
				else{
					JOptionPane.showMessageDialog(null,"组卷失败，请重试！","提示消息",JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private void PointsWrong(){//知识点比例有误
			JOptionPane.showMessageDialog(null, "知识点比例设置有误！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		private void TypesWrong(){//题型比例有误
			JOptionPane.showMessageDialog(null, "题型比例设置有误！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
	}
	//===============================================================================================================
	//添加试题界面
	class AddQuestionPane extends JLayeredPane{
		private JTextArea txtQuestion;
		private JTextArea txtAns;
		private JTextField txtPath;
		private JComboBox boxPropertyDifficulty;
		private JComboBox boxPropertyPoint;
		private JComboBox boxPropertyType;
		private JLabel PicPreview;
		//构造方法
		public AddQuestionPane(final int AddorEdit,final String QuestionID,String Question,String Question_Ans,int Difficulty,int Point,int Type,int Mode){
			setBounds(100, 35, 574, 347);
		//	mainPanel.add(AddQuestionPane);
			JScrollPane scrollQuestion = new JScrollPane();
			scrollQuestion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollQuestion.setBounds(10, 10, 311, 163);
			add(scrollQuestion);
			
			txtQuestion = new JTextArea();//试题内容框
			txtQuestion.setWrapStyleWord(true);
			txtQuestion.setLineWrap(true);
			txtQuestion.setForeground(Color.GRAY);
			scrollQuestion.setViewportView(txtQuestion);
			txtQuestion.addFocusListener(new FocusListener(){
	
				public void focusGained(FocusEvent arg0) {
					if(txtQuestion.getText().equals("\u5728\u6B64\u8F93\u5165\u8BD5\u9898")){
						txtQuestion.setText(null);
						txtQuestion.setForeground(Color.BLACK);
					}
				}
	
				public void focusLost(FocusEvent arg0) {
					if(txtQuestion.getText().isEmpty()){
						txtQuestion.setText("\u5728\u6B64\u8F93\u5165\u8BD5\u9898");
						txtQuestion.setForeground(Color.GRAY);
					}
				}
			});
			txtQuestion.setText("\u5728\u6B64\u8F93\u5165\u8BD5\u9898");
			
			JScrollPane scrollAnswer = new JScrollPane();
			scrollAnswer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollAnswer.setBounds(10, 183, 311, 164);
			add(scrollAnswer);
			
			txtAns = new JTextArea();//答案框
			txtAns.setWrapStyleWord(true);
			txtAns.setLineWrap(true);
			txtAns.setForeground(Color.GRAY);
			txtAns.setText("\u5728\u6B64\u8F93\u5165\u7B54\u6848");
			scrollAnswer.setViewportView(txtAns);
			txtAns.addFocusListener(new FocusListener(){
	
				public void focusGained(FocusEvent arg0) {
					if(txtAns.getText().equals("\u5728\u6B64\u8F93\u5165\u7B54\u6848")){
						txtAns.setText(null);
						txtAns.setForeground(Color.BLACK);
					}
				}
	
				public void focusLost(FocusEvent arg0) {
					if(txtAns.getText().isEmpty()){
						txtAns.setText("\u5728\u6B64\u8F93\u5165\u7B54\u6848");
						txtAns.setForeground(Color.GRAY);
					}
				}
			});
			
			JLayeredPane PicUploadlayeredPane = new JLayeredPane();
			PicUploadlayeredPane.setBorder(new TitledBorder(null, "\u4E0A\u4F20\u56FE\u7247"+"（<500KB）", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			PicUploadlayeredPane.setBounds(331, 145, 233, 58);
			add(PicUploadlayeredPane);
			
			JButton btnExplore = new JButton("\u6D4F\u89C8");//图片浏览按钮
			btnExplore.setBounds(172, 17, 58, 23);
			PicUploadlayeredPane.add(btnExplore);
			btnExplore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("图片文件(*.jpg,*.png)","jpg","png"));
					fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
					int option=fileChooser.showOpenDialog(null);
					if(option==JFileChooser.CANCEL_OPTION) return;
					if(option==JFileChooser.APPROVE_OPTION) txtPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
					RefreshPicture();
				}
			});
			
			txtPath = new JTextField();//路径文本框
			txtPath.setBounds(41, 18, 131, 21);
			PicUploadlayeredPane.add(txtPath);
			txtPath.setColumns(10);
			
			JLabel lblPath = new JLabel("\u8DEF\u5F84:");
			lblPath.setBounds(10, 21, 30, 15);
			PicUploadlayeredPane.add(lblPath);
			
			
			JLayeredPane QuestionUploadOptions = new JLayeredPane();
			QuestionUploadOptions.setBorder(new TitledBorder(null, "\u5C5E\u6027\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			QuestionUploadOptions.setBounds(331, 203, 233, 115);
			add(QuestionUploadOptions);
			
			boxPropertyDifficulty = new JComboBox();//难度下拉框
			boxPropertyDifficulty.setModel(new DefaultComboBoxModel(new String[] {"\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxPropertyDifficulty.setSelectedIndex(0);
			boxPropertyDifficulty.setBounds(81, 14, 121, 21);
			QuestionUploadOptions.add(boxPropertyDifficulty);
			
			boxPropertyPoint = new JComboBox();//知识点下拉框
			boxPropertyPoint.setModel(new DefaultComboBoxModel(new String[] {"\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxPropertyPoint.setSelectedIndex(0);
			boxPropertyPoint.setBounds(81, 46, 121, 21);
			QuestionUploadOptions.add(boxPropertyPoint);
			
			boxPropertyType = new JComboBox();//题型下拉框
			boxPropertyType.setModel(new DefaultComboBoxModel(new String[] {"\u9009\u62E9\u9898", "\u586B\u7A7A\u9898", "\u5224\u65AD\u9898", "\u7B80\u7B54\u9898"}));
			boxPropertyType.setSelectedIndex(0);
			boxPropertyType.setBounds(81, 78, 121, 21);
			QuestionUploadOptions.add(boxPropertyType);
			
			JLabel lblPropertyDifficulty = new JLabel("\u96BE  \u5EA6");
			lblPropertyDifficulty.setBounds(33, 17, 54, 15);
			QuestionUploadOptions.add(lblPropertyDifficulty);
			
			JLabel lblPropertyPoint = new JLabel("\u77E5\u8BC6\u70B9");
			lblPropertyPoint.setBounds(33, 49, 54, 15);
			QuestionUploadOptions.add(lblPropertyPoint);
			
			JLabel lblPropertyType = new JLabel("\u9898  \u578B");
			lblPropertyType.setBounds(33, 81, 54, 15);
			QuestionUploadOptions.add(lblPropertyType);
			
			JButton btnBack = new JButton("\u8FD4\u56DE");//返回按钮
			btnBack.setBounds(461, 320, 93, 23);
			add(btnBack);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addQuestionsManagementPane();
				}
			});
			
			JButton btnUpload = new JButton("\u786E\u5B9A");//确认按钮
			btnUpload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(txtQuestion.getText().equals("\u5728\u6B64\u8F93\u5165\u8BD5\u9898")){
						addQuestion_QuestionNotEnter();
						return;
					}
					if(txtAns.getText().equals("\u5728\u6B64\u8F93\u5165\u7B54\u6848")){
						addQuestion_AnsNotEnter();
						return;
					}
					
					File QPicture=new File(txtPath.getText());
					if(!QPicture.exists()&&!txtPath.getText().isEmpty()){
						addQuestion_PicNotExist();
						return;
					}
					FileUtils fileutils=new FileUtils();
					DButil dbutils=new DButil();
					IOImage ioimage=new IOImage();
					if(!TestConnect()) return;
					try {
						if(!txtPath.getText().isEmpty()) PicBytes=fileutils.ReadPicture(txtPath.getText());
						if(AddorEdit==0){
							if(dbutils.addQuestion(QuestionID,boxPropertyType.getSelectedIndex(),txtQuestion.getText(),txtAns.getText(),
									boxPropertyDifficulty.getSelectedIndex(),boxPropertyPoint.getSelectedIndex())==1&&
									ioimage.blobInsert(PicBytes, QuestionID)==1)
								addQuestion_Success();
							else addQuestion_Failure();
						}
						else{
							if(dbutils.updateQuestion(QuestionID,boxPropertyType.getSelectedIndex(),txtQuestion.getText(),txtAns.getText(),
									boxPropertyDifficulty.getSelectedIndex(),boxPropertyPoint.getSelectedIndex())==1&&
									ioimage.blobInsert(PicBytes, QuestionID)==1)
								editQuestion_Success();
							else editQuestion_Failure();
						}
					} 
					catch (PacketTooBigException e) {
						JOptionPane.showMessageDialog(null, "图片太大，上传失败！", "添加失败", JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnUpload.setBounds(343, 320, 93, 23);
			add(btnUpload);
			
			PicPreview = new JLabel("\u65E0\u9884\u89C8\u56FE");//图片预览框
			PicPreview.setBorder(new LineBorder(Color.LIGHT_GRAY));
			PicPreview.setHorizontalAlignment(SwingConstants.CENTER);
			PicPreview.setBounds(331, 10, 233, 125);
			add(PicPreview);
			
			final JPopupMenu PopPicMenu=new JPopupMenu();
			JToolBar PicBar=new JToolBar();
			PicBar.setFloatable(false);
			JButton PicRefresh=new JButton("刷新");
			PicRefresh.setContentAreaFilled(false);
			PicRefresh.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					RefreshPicture();
				}
			});
			PicBar.add(PicRefresh);
			JSeparator separator_2 = new JSeparator();
			separator_2.setOrientation(SwingConstants.VERTICAL);
			PicBar.add(separator_2);
			JButton PicDelete=new JButton("移除图片");
			PicDelete.setContentAreaFilled(false);
			PicDelete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					PicBytes=null;
					txtPath.setText(null);
					RefreshPicture();
				}
			});
			PicBar.add(PicDelete);
			JButton PicRestore=new JButton("还原");
			PicRestore.setContentAreaFilled(false);
			PicRestore.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(!TestConnect()) return;
					txtPath.setText(null);
					IOImage ioimage=new IOImage();
					try {
						PicBytes=ioimage.readBolb(QuestionID);
					} catch (Exception e) {
						e.printStackTrace();
					}
					RefreshPicture();
				}
			});
			if(Mode==1) PicBar.add(PicRestore);
			PopPicMenu.add(PicBar);
			PicPreview.add(PopPicMenu);
			addMouseMotionListener(new MouseAdapter(){
				public void mouseMoved(MouseEvent e){
					if(e.getX()<564&&e.getX()>331&&e.getY()>10&&e.getY()<135) PopPicMenu.show(PicPreview,0,0);
					else PopPicMenu.setVisible(false);
				}
			});
			//赋初值
			if(!(Question_Ans==null)){
				txtAns.setText(Question_Ans);
				txtAns.setForeground(Color.BLACK);
			}
			if(!(Question==null)){
				txtQuestion.setText(Question);
				txtQuestion.setForeground(Color.BLACK);
			}
			boxPropertyDifficulty.setSelectedIndex(Difficulty);
			boxPropertyType.setSelectedIndex(Type);
			boxPropertyPoint.setSelectedIndex(Point);
			RefreshPicture();	
		}
		//预览图片函数
		private void RefreshPicture(){
			FileUtils fileutils=new FileUtils();
			try {
				File QPicture=new File(txtPath.getText());
				if(!QPicture.exists()&&!txtPath.getText().isEmpty()){
					addQuestion_PicNotExist();
					return;
				}
				if(!txtPath.getText().isEmpty()) PicBytes=fileutils.ReadPicture(txtPath.getText());
				if(PicBytes!=null)
				{
					PictureUtils PicUtils=new PictureUtils();
					PicPreview.setIcon(PicUtils.PreviewPicture(PicUtils.BytesToIcon(PicBytes),
							PicPreview.getWidth(),PicPreview.getHeight()));
					PicPreview.setText(null);
				}
				else {
					PicPreview.setIcon(null);
					PicPreview.setText("\u65E0\u9884\u89C8\u56FE");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void addQuestion_QuestionNotEnter(){//请输入题目
			JOptionPane.showMessageDialog(null, "请输入题目！", "添加失败", JOptionPane.ERROR_MESSAGE);
			txtQuestion.requestFocus();
		}
		private void addQuestion_AnsNotEnter(){//请输入答案
			JOptionPane.showMessageDialog(null, "请输入答案！", "添加失败", JOptionPane.ERROR_MESSAGE);
			txtAns.requestFocus();
		}
		private void addQuestion_PicNotExist(){//图片不存在
			JOptionPane.showMessageDialog(null, "图片不存在，请检查文件路径！", "添加失败", JOptionPane.ERROR_MESSAGE);
			txtPath.requestFocus();
			txtPath.selectAll();
		}
		private void addQuestion_Success(){//添加试题成功
			JOptionPane.showMessageDialog(null, "添加成功！", "添加成功", JOptionPane.INFORMATION_MESSAGE);
			addQuestionsManagementPane();
		}
		private void addQuestion_Failure(){//添加试题失败
			JOptionPane.showMessageDialog(null, "添加失败，请重试！", "添加失败", JOptionPane.ERROR_MESSAGE);
		}
		private void editQuestion_Success(){//修改试题成功
			JOptionPane.showMessageDialog(null, "修改成功！", "修改成功", JOptionPane.INFORMATION_MESSAGE);
			addQuestionsManagementPane();
		}
		private void editQuestion_Failure(){//修改试题失败
			JOptionPane.showMessageDialog(null, "修改失败，请重试！", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
	}
	//===============================================================================================
	//试题管理界面
	class QuestionsManagementPane extends JLayeredPane{
		private MyTable tableQuestion = new MyTable();
		private DefaultRowSorter<TableModel, Integer> QuestionSorter;
		private DefaultTableModel QuestionModel;
		private JTextField txtIDQuestionFilter;
		private JComboBox boxQuestionFilterDifficulty;
		private JComboBox boxQuestionFilterPoint;
		private JComboBox boxQuestionFilterType;
		private JDialog Question_Filter;
		//构造方法
		public QuestionsManagementPane(){
			setBounds(100, 35, 564, 347);
		//	mainPanel.add(QuestionsManagementPane);
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			toolBar.setBounds(410, 9, 144, 19);
			add(toolBar);
			
			JButton btnEdit = new JButton();//"\u7F16\u8F91");
			btnEdit.setContentAreaFilled(false);
			btnEdit.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnEdit.png")));
		//	btnEdit.setFocusable(false);
			btnEdit.setToolTipText("\u7F16\u8F91");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableQuestion.getSelectedRowCount()==0){
						NoRowSelected();
						return;
					}
					if(tableQuestion.getSelectedRowCount()>1){
						NotOneRowSelected();
						return;
					}
					if(!TestConnect()) return;
					DButil dbutil=new DButil();
					try {
						Object[] EditInformation = dbutil.selectById((String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0));
						IOImage ioimage=new IOImage();
						PicBytes=ioimage.readBolb((String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0));
						addAddQuestionPane(1,(String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0), (String)EditInformation[0]
								, (String)EditInformation[4], (int)EditInformation[1], (int)EditInformation[2], (int)EditInformation[3], 1);
				//		QuestionID=(String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			toolBar.add(btnEdit);
			
			JButton btnAdd = new JButton();//"\u6DFB\u52A0");
			btnAdd.setContentAreaFilled(false);
			btnAdd.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnAdd.png")));
		//	btnAdd.setFocusable(false);
			btnAdd.setToolTipText("\u6DFB\u52A0");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PicBytes=null;
					addAddQuestionPane(0,TimeUtils.GetTime(1),null,null,0,0,0,0);
				}
			});
			toolBar.add(btnAdd);
			
			JButton btnDelete = new JButton();//"\u5220\u9664");
			btnDelete.setContentAreaFilled(false);
			btnDelete.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnDelete.png")));
		//	btnDelete.setFocusable(false);
			btnDelete.setToolTipText("\u5220\u9664");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableQuestion.getSelectedRow()==-1){
						NoRowSelected();
						return;
					}
					else if(JOptionPane.showConfirmDialog(null, "您确认要删除选中试题吗？", "确认删除", JOptionPane.YES_NO_OPTION)==1) 
						return;
					DButil dbutils=new DButil();
					if(!TestConnect()) return;
					String UsedQuestion="";
					int m=0;
					try {
						for(int i=0;i<tableQuestion.getSelectedRowCount();i++){
							if(dbutils.deleteQuestion((String)tableQuestion.getValueAt(tableQuestion.getSelectedRows()[i], 0))==-1){
								UsedQuestion+=(String)tableQuestion.getValueAt(tableQuestion.getSelectedRows()[i], 0)+"、";
								m=1;
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(m==1){
						char[] n=new char[UsedQuestion.length()-1];
						UsedQuestion.getChars(0, UsedQuestion.length()-1, n, 0);
						JOptionPane.showMessageDialog(null, new String(n)+"已被使用，无法删除！", "无法删除", JOptionPane.WARNING_MESSAGE);
					}
					GetQuestions();
				}
			});
			toolBar.add(btnDelete);
			
			JSeparator separatorToolBar = new JSeparator();
			separatorToolBar.setOrientation(SwingConstants.VERTICAL);
			toolBar.add(separatorToolBar);
			
			JButton btnFilter = new JButton();//"\u67E5\u627E");
			btnFilter.setContentAreaFilled(false);
			btnFilter.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSearch.png")));
		//	btnFilter.setFocusable(false);
			btnFilter.setToolTipText("\u67E5\u627E");
			btnFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Question_Filter();
				}
			});
			toolBar.add(btnFilter);
			
			JButton btnRefresh = new JButton();//"\u5237\u65B0");
			btnRefresh.setContentAreaFilled(false);
			btnRefresh.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnRefresh.png")));
		//	btnRefresh.setFocusable(false);
			btnRefresh.setToolTipText("\u5237\u65B0");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GetQuestions();
				}
			});
			toolBar.add(btnRefresh);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 37, 544, 310);
			add(scrollPane);
			tableQuestion.getTableHeader().setReorderingAllowed(false);
			QuestionModel=new DefaultTableModel(
				null,
				new String[] {
					"\u8BD5\u9898\u7F16\u53F7", "\u8BD5\u9898\u5185\u5BB9\u9884\u89C8", "\u96BE\u5EA6", "\u77E5\u8BC6\u70B9", "\u9898\u578B", "\u6B63\u786E\u7387"
				}
			);
			tableQuestion.setModel(QuestionModel);
			tableQuestion.getColumnModel().getColumn(0).setPreferredWidth(108);
			tableQuestion.getColumnModel().getColumn(1).setPreferredWidth(272);
			tableQuestion.getColumnModel().getColumn(2).setPreferredWidth(48);
			tableQuestion.getColumnModel().getColumn(3).setPreferredWidth(90);
			tableQuestion.getColumnModel().getColumn(4).setPreferredWidth(48);
			tableQuestion.getColumnModel().getColumn(5).setPreferredWidth(52);
			tableQuestion.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if (e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {
						if(tableQuestion.getSelectedRowCount()==1){
							if(!TestConnect()) return;
							DButil dbutil=new DButil();
							try {
								Object[] EditInformation = dbutil.selectById((String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0));
								IOImage ioimage=new IOImage();
								PicBytes=ioimage.readBolb((String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0));
								addAddQuestionPane(1,(String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0), (String)EditInformation[0]
										, (String)EditInformation[4], (int)EditInformation[1], (int)EditInformation[2], (int)EditInformation[3], 1);
						//		QuestionID=(String)tableQuestion.getValueAt(tableQuestion.getSelectedRow(), 0);
							} catch (Exception arg0) {
								arg0.printStackTrace();
							}
						}
					}
				}
			});
			QuestionSorter = new TableRowSorter<TableModel>(QuestionModel);
			tableQuestion.setRowSorter(QuestionSorter);
			GetQuestions();
			scrollPane.setViewportView(tableQuestion);
		}
		//刷新试题表
		private void GetQuestions(){
			if(!TestConnect()) return;
			DButil dbutil=new DButil();
			try {
				QuestionModel=new DefaultTableModel(
					dbutil.selectAll(),
					new String[] {
						"\u8BD5\u9898\u7F16\u53F7", "\u8BD5\u9898\u5185\u5BB9\u9884\u89C8", "\u96BE\u5EA6", "\u77E5\u8BC6\u70B9", "\u9898\u578B", "\u6B63\u786E\u7387"
					}
				);
				tableQuestion.setModel(QuestionModel);
				tableQuestion.getColumnModel().getColumn(0).setPreferredWidth(108);
				tableQuestion.getColumnModel().getColumn(1).setPreferredWidth(272);
				tableQuestion.getColumnModel().getColumn(2).setPreferredWidth(48);
				tableQuestion.getColumnModel().getColumn(3).setPreferredWidth(90);
				tableQuestion.getColumnModel().getColumn(4).setPreferredWidth(48);
				tableQuestion.getColumnModel().getColumn(5).setPreferredWidth(52);
				QuestionSorter = new TableRowSorter<TableModel>(QuestionModel);
				tableQuestion.setRowSorter(QuestionSorter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//试题查找对话框
		private void Question_Filter(){
			Question_Filter = new JDialog(frame,"查找",true);
			Question_Filter.setSize(245, 232);
			Question_Filter.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-245)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-232)/2);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			Question_Filter.getContentPane().add(panel);
			
			JLabel lblIDFilter = new JLabel("\u8BD5\u9898\u7F16\u53F7");
			lblIDFilter.setBounds(33, 12, 54, 15);
			panel.add(lblIDFilter);
			
			JButton btnQuestionFilterFind = new JButton("\u67E5\u627E");
			
			btnQuestionFilterFind.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
					filters.add(RowFilter.regexFilter(txtIDQuestionFilter.getText(),0));
					if(boxQuestionFilterDifficulty.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter("^"+(String)boxQuestionFilterDifficulty.getSelectedItem()+"$",2));
					if(boxQuestionFilterPoint.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter("^"+(String)boxQuestionFilterPoint.getSelectedItem()+"$",3));
					if(boxQuestionFilterType.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter("^"+(String)boxQuestionFilterType.getSelectedItem()+"$",4));
					QuestionSorter.setRowFilter(RowFilter.andFilter(filters));
					Question_Filter.dispose();
				}
			});
			btnQuestionFilterFind.setBounds(73, 169, 93, 23);
			panel.add(btnQuestionFilterFind);
			
			txtIDQuestionFilter = new JTextField();
			txtIDQuestionFilter.setBounds(91, 9, 104, 21);
			panel.add(txtIDQuestionFilter);
			txtIDQuestionFilter.setColumns(10);
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(new TitledBorder(null, "\u7B5B\u9009\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			layeredPane.setBounds(10, 39, 218, 119);
			panel.add(layeredPane);
			
			JLabel lblDifficultyFilter = new JLabel("\u96BE    \u5EA6");
			lblDifficultyFilter.setBounds(23, 24, 54, 15);
			layeredPane.add(lblDifficultyFilter);
			
			JLabel lblPointFilter = new JLabel("\u77E5 \u8BC6 \u70B9");
			lblPointFilter.setBounds(23, 57, 54, 15);
			layeredPane.add(lblPointFilter);
			
			JLabel lblTypeFilter = new JLabel("\u9898    \u578B");
			lblTypeFilter.setBounds(23, 90, 54, 15);
			layeredPane.add(lblTypeFilter);
			
			boxQuestionFilterDifficulty=new JComboBox();
			boxQuestionFilterDifficulty.setModel(new DefaultComboBoxModel(new String[] {"全部", "\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxQuestionFilterDifficulty.setSelectedIndex(0);
			boxQuestionFilterDifficulty.setBounds(81, 21, 121, 21);
			layeredPane.add(boxQuestionFilterDifficulty);
			
			boxQuestionFilterPoint=new JComboBox();
			boxQuestionFilterPoint.setModel(new DefaultComboBoxModel(new String[] {"全部", "\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxQuestionFilterPoint.setSelectedIndex(0);
			boxQuestionFilterPoint.setBounds(81, 54, 121, 21);
			layeredPane.add(boxQuestionFilterPoint);
			
			boxQuestionFilterType=new JComboBox();
			boxQuestionFilterType.setModel(new DefaultComboBoxModel(new String[] {"全部", "\u9009\u62E9\u9898", "\u586B\u7A7A\u9898", "\u5224\u65AD\u9898", "\u7B80\u7B54\u9898"}));
			boxQuestionFilterType.setSelectedIndex(0);
			boxQuestionFilterType.setBounds(81, 87, 121, 21);
			layeredPane.add(boxQuestionFilterType);
			
			Question_Filter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Question_Filter.getRootPane().setDefaultButton(btnQuestionFilterFind);//设置默认按钮
			Question_Filter.setResizable(false);
			Question_Filter.setVisible(true);
		}
		private void NoRowSelected(){//未选择任何行
			JOptionPane.showMessageDialog(null, "未选择任何行！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		private void NotOneRowSelected(){//编辑时选择多行
			JOptionPane.showMessageDialog(null, "只能选择一行进行编辑！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
	}
	//=================================================================================================
	//答卷表界面
	class OnlineCorrectPane extends JLayeredPane{
		private MyTable tableCorrect;
		private DefaultTableModel CorrectModel;
		private TableRowSorter<TableModel> CorrectSorter;
		private Object[][] Information=null;
		private Object[][] List=null;
		//构造方法
		public OnlineCorrectPane(){
			setBounds(100, 35, 564, 347);
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			toolBar.setBounds(437, 9, 117, 19);
			add(toolBar);
			
			JButton btnCorrect = new JButton();//"\u6279\u6539");
			btnCorrect.setContentAreaFilled(false);
			btnCorrect.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnCheck.png")));
		//	btnCorrect.setFocusable(false);
			btnCorrect.setToolTipText("\u6279\u6539");
			btnCorrect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(tableCorrect.getSelectedRowCount()==0){
						NoRowSelected();
						return;
					}
					for(int i=1;i<tableCorrect.getSelectedRowCount();i++){
						if(!tableCorrect.getValueAt(tableCorrect.getSelectedRows()[i],1).equals
								(tableCorrect.getValueAt(tableCorrect.getSelectedRows()[i-1],1))){
							PaperIDNotEqual();
							return;
						}
					}
					
					Correct_Mode();
				}
			});
			toolBar.add(btnCorrect);
			
			JButton btnSumScore = new JButton();//"\u7EDF\u8BA1\u5206\u6570");
			btnSumScore.setContentAreaFilled(false);
			btnSumScore.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSum.png")));
		//	btnSumScore.setFocusable(false);
			btnSumScore.setToolTipText("\u7EDF\u8BA1\u5206\u6570");
			btnSumScore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!TestConnect()) return;
					DButil dbutil=new DButil();
					int successNum=0;
					int totalNum=0;
					try {
						Information=dbutil.markOrNot();
						for(int i=0;i<Information.length;i++){
							if(Information[i][3].equals("完成")){
								successNum+=dbutil.countGrade((String)Information[i][0],(String)Information[i][1]);
								totalNum+=1;
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(totalNum==0) 
						JOptionPane.showMessageDialog(null, "统分失败！无可统分对象", "统分失败", JOptionPane.ERROR_MESSAGE);
					else if(successNum==totalNum)
						JOptionPane.showMessageDialog(null, "统分成功！共"+successNum+"个", "统分成功", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "统分失败！成功"+successNum+"个，失败"+(totalNum-successNum)+"个。请稍后重试", "统分失败", JOptionPane.ERROR_MESSAGE);
				}
			});
			toolBar.add(btnSumScore);
			
			JSeparator separatorToolBar = new JSeparator();
			separatorToolBar.setOrientation(SwingConstants.VERTICAL);
			toolBar.add(separatorToolBar);
			
			JButton btnFilter = new JButton();//"\u67E5\u627E");
			btnFilter.setContentAreaFilled(false);
			btnFilter.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSearch.png")));
		//	btnFilter.setFocusable(false);
			btnFilter.setToolTipText("\u67E5\u627E");
			btnFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Correct_Filter();
				}
			});
			toolBar.add(btnFilter);
			
			JButton btnRefresh = new JButton();//"\u5237\u65B0");
			btnRefresh.setContentAreaFilled(false);
			btnRefresh.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnRefresh.png")));
		//	btnRefresh.setFocusable(false);
			btnRefresh.setToolTipText("\u5237\u65B0");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GetCorrectList();
				}
			});
			toolBar.add(btnRefresh);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 37, 544, 310);
			add(scrollPane);
			
			tableCorrect = new MyTable();
			tableCorrect.getTableHeader().setReorderingAllowed(false);
			CorrectModel=new DefaultTableModel(
				null,
				new String[] {
					"\u8003\u751F\u7F16\u53F7", "\u8BD5\u5377\u7F16\u53F7", "\u73ED\u7EA7", "\u6279\u6539\u8FDB\u5EA6"
				}	
			);
			tableCorrect.setModel(CorrectModel);
			tableCorrect.getColumnModel().getColumn(0).setPreferredWidth(107);
			tableCorrect.getColumnModel().getColumn(1).setPreferredWidth(229);
			tableCorrect.getColumnModel().getColumn(2).setPreferredWidth(103);
			tableCorrect.getColumnModel().getColumn(3).setPreferredWidth(60);
			CorrectSorter = new TableRowSorter<TableModel>(CorrectModel);
			tableCorrect.setRowSorter(CorrectSorter);
			GetCorrectList();
			scrollPane.setViewportView(tableCorrect);
			
		}
		//刷新答卷表
		private void GetCorrectList(){
			if(!TestConnect()) return;
			DButil dbutil=new DButil();
			try {
				Information=dbutil.markOrNot();
				List=new Object[Information.length][];
				for(int i=0;i<Information.length;i++){
					List[i]=Information[i].clone();
				}
				for (int i=0;i<List.length;i++){
					DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();
					df.setMinimumIntegerDigits(6);
					df.setGroupingUsed(false);
					List[i][0]=df.format(i);
				}
				CorrectModel=new DefaultTableModel(
					List,
					new String[] {
						"\u8003\u751F\u7F16\u53F7", "\u8BD5\u5377\u7F16\u53F7", "\u73ED\u7EA7", "\u6279\u6539\u8FDB\u5EA6"
					}
				);
				tableCorrect.setModel(CorrectModel);
				CorrectSorter = new TableRowSorter<TableModel>(CorrectModel);
				tableCorrect.setRowSorter(CorrectSorter);
				tableCorrect.getColumnModel().getColumn(0).setPreferredWidth(107);
				tableCorrect.getColumnModel().getColumn(1).setPreferredWidth(229);
				tableCorrect.getColumnModel().getColumn(2).setPreferredWidth(103);
				tableCorrect.getColumnModel().getColumn(3).setPreferredWidth(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//答卷过滤对话框
		private void Correct_Filter(){
			final JDialog Correct_Filter = new JDialog(frame,"查找",true);
			final JTextField txtIDPaperFilter = new JTextField();
			final JTextField txtClassFilter = new JTextField();
			final JCheckBox checkBoxYes = new JCheckBox("\u5DF2\u5B8C\u6210");
			final JCheckBox checkBoxNo = new JCheckBox("\u672A\u5B8C\u6210");
			
			Correct_Filter.setSize(245, 210);
			Correct_Filter.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-245)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-210)/2);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			Correct_Filter.getContentPane().add(panel);
			
			JLabel lblIDFilter = new JLabel("\u8BD5\u5377\u7F16\u53F7");
			lblIDFilter.setBounds(33, 16, 54, 15);
			panel.add(lblIDFilter);
			
			JButton btnFilterFind = new JButton("\u67E5\u627E");
			btnFilterFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
					filters.add(RowFilter.regexFilter(txtIDPaperFilter.getText(),1));
					filters.add(RowFilter.regexFilter(txtClassFilter.getText(),2));
					if(!checkBoxNo.isSelected()) filters.add(RowFilter.regexFilter("完成",3));
					if(!checkBoxYes.isSelected()) filters.add(RowFilter.notFilter(RowFilter.regexFilter("完成",3)));
					CorrectSorter.setRowFilter(RowFilter.andFilter(filters));
					Correct_Filter.dispose();
				}
			});
			btnFilterFind.setBounds(73, 147, 93, 23);
			panel.add(btnFilterFind);
			
			txtIDPaperFilter.setBounds(91, 13, 104, 21);
			panel.add(txtIDPaperFilter);
			txtIDPaperFilter.setColumns(10);
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(new TitledBorder(null, "\u7B5B\u9009\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			layeredPane.setBounds(10, 44, 218, 95);
			panel.add(layeredPane);
			
			JLabel lblClassFilter = new JLabel("\u73ED    \u7EA7");
			lblClassFilter.setBounds(23, 24, 54, 15);
			layeredPane.add(lblClassFilter);
			
			JLabel lblMarkedFilter = new JLabel("\u6279\u6539\u8FDB\u5EA6");
			lblMarkedFilter.setBounds(23, 60, 54, 15);
			layeredPane.add(lblMarkedFilter);
			
			
			txtClassFilter.setBounds(81, 21, 76, 21);
			layeredPane.add(txtClassFilter);
			txtClassFilter.setColumns(10);
			
			checkBoxYes.setSelected(true);
			checkBoxYes.setBounds(77, 56, 66, 23);
			layeredPane.add(checkBoxYes);
			
			checkBoxNo.setSelected(true);
			checkBoxNo.setBounds(140, 56, 66, 23);
			layeredPane.add(checkBoxNo);
			
			Correct_Filter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Correct_Filter.getRootPane().setDefaultButton(btnFilterFind);//设置默认按钮
			Correct_Filter.setResizable(false);
			Correct_Filter.setVisible(true);
		}
		//选择阅卷模式对话框
		private void Correct_Mode(){
			final JDialog Mode=new JDialog(frame,"批改模式",true);
			Mode.setSize(228,210);
			Mode.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-228)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-213)/2);
			
			JPanel panel = new JPanel();
			Mode.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BF7\u9009\u62E9\u8981\u6279\u6539\u7684\u9898\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			layeredPane.setBounds(10, 13, 192, 113);
			panel.add(layeredPane);
			
			ButtonGroup buttonGroup=new ButtonGroup();
			final JSpinner spinnerTianNumber = new JSpinner();
			final JSpinner spinnerJianNumber = new JSpinner();
			
			final JRadioButton radioButtonALL = new JRadioButton("\u5168  \u90E8",true);
			radioButtonALL.setBounds(24, 26, 70, 23);
			radioButtonALL.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					spinnerTianNumber.setEnabled(false);
					spinnerJianNumber.setEnabled(false);
				}
			});
			layeredPane.add(radioButtonALL);
			
			final JRadioButton radioButtonTian = new JRadioButton("\u586B\u7A7A\u9898  \u7B2C",false);
			radioButtonTian.setBounds(24, 51, 85, 23);
			radioButtonTian.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					spinnerTianNumber.setEnabled(true);
					spinnerJianNumber.setEnabled(false);
				}
			});
			layeredPane.add(radioButtonTian);
			
			final JRadioButton radioButtonJian = new JRadioButton("\u7B80\u7B54\u9898  \u7B2C", false);
			radioButtonJian.setBounds(24, 76, 85, 23);
			radioButtonJian.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					spinnerTianNumber.setEnabled(false);
					spinnerJianNumber.setEnabled(true);
				}
			});
			layeredPane.add(radioButtonJian);
			
			buttonGroup.add(radioButtonALL);
			buttonGroup.add(radioButtonTian);
			buttonGroup.add(radioButtonJian);
			
			spinnerTianNumber.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			spinnerTianNumber.setBounds(108, 51, 40, 22);
			spinnerTianNumber.setEnabled(false);
			layeredPane.add(spinnerTianNumber);
			
			JLabel label_1 = new JLabel("\u9898");
			label_1.setBounds(155, 55, 25, 15);
			layeredPane.add(label_1);
			
			spinnerJianNumber.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			spinnerJianNumber.setEnabled(false);
			spinnerJianNumber.setBounds(108, 76, 40, 22);
			layeredPane.add(spinnerJianNumber);
			
			JLabel label = new JLabel("\u9898");
			label.setBounds(155, 80, 25, 15);
			layeredPane.add(label);
			
			JButton btnOk = new JButton("\u786E\u5B9A");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int Type=-1;
					int QuestionNumber=1;
					String[][] Question=null;
					String Paper=(String) tableCorrect.getValueAt(tableCorrect.getSelectedRow(),1);
					DButil dbutil=new DButil();
					try {
						Question=dbutil.manualMark(Paper);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(radioButtonALL.isSelected());
					if(radioButtonTian.isSelected()){
						Type=0;
						QuestionNumber=(int) spinnerTianNumber.getValue();
						if(QuestionNumber>Question[0].length){
							NoThisQuestion();
							return;
						}
					}
					if(radioButtonJian.isSelected()){
						Type=1;
						QuestionNumber=(int) spinnerJianNumber.getValue();
						if(QuestionNumber>Question[1].length){
							NoThisQuestion();
							return;
						}
					}
					String[] id=new String[tableCorrect.getSelectedRowCount()];
					for(int i=0;i<tableCorrect.getSelectedRowCount();i++){
						id[i]=(String) List[tableCorrect.getSelectedRows()[i]][0];
					}
					String[] StudentID=new String[tableCorrect.getSelectedRowCount()];
					for(int i=0;i<tableCorrect.getSelectedRowCount();i++){
						StudentID[i]=(String) Information[tableCorrect.getSelectedRows()[i]][0];
					}
					new OLCorrectFrame(id,StudentID,Paper,Question,Type,QuestionNumber);
					Mode.dispose();
					frame.setVisible(false);
				}
			});
			btnOk.setBounds(59, 136, 93, 23);
			panel.add(btnOk);
			Mode.getRootPane().setDefaultButton(btnOk);//设置默认按钮
			Mode.setVisible(true);
			Mode.setResizable(false);
			Mode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		private void NoRowSelected(){//未选择任何行
			JOptionPane.showMessageDialog(null, "至少选择一份考卷进行批改！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		private void PaperIDNotEqual(){//试卷编号不同
			JOptionPane.showMessageDialog(null, "只能选择试卷编号相同的考卷进行批改！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		private void NoThisQuestion(){//无此题
			JOptionPane.showMessageDialog(null, "无此题，请检查题号！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
	}
	//===============================================================================================
	//成绩表界面
	class TranscriptTablePane extends JLayeredPane{
		private MyTable tableTranscript;
		private DefaultTableModel TranscriptModel;
		private TableRowSorter<TableModel> TranscriptSorter;
		private Object[][] Transcripts=null;
		//构造方法
		public TranscriptTablePane(){
			setBounds(100, 35, 564, 347);
			
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			toolBar.setBounds(410, 9, 144, 19);
			add(toolBar);
			
			JButton btnAverage = new JButton();//"统计");
			btnAverage.setContentAreaFilled(false);
			btnAverage.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSum.png")));
		//	btnAverage.setFocusable(false);
			btnAverage.setToolTipText("统计");
			btnAverage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			/*		if(tableTranscript.getSelectedRowCount()==0){
						NoRowSelected();
						return;
					}
					double SUM=0;
					for(int i=0;i<tableTranscript.getSelectedRowCount();i++){
						SUM+=(double)tableTranscript.getValueAt(tableTranscript.getSelectedRows()[i], 5);
					}
					JOptionPane.showMessageDialog(null, "平均分"+(SUM/tableTranscript.getSelectedRowCount()), "平均分", JOptionPane.PLAIN_MESSAGE);*/
					if(Transcripts==null){
						JOptionPane.showMessageDialog(null, "无成绩信息！", "无法统计", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Object[][][][] Average=new Object[20][50][50][5];//学期，试卷，班级，{总分，人数，学期，试卷，班级}
			/*		for(int i=0;i<Transcripts.length;i++){
						if(Transcripts[i][0]
					}*/
			//		String[] Last=new String[3];
					int a=0,b[]=new int[20],c[][]=new int[20][50];//大小
					int x=0,y=0,z=0;//位置
					Average[0][0][0][0]=(double) Transcripts[0][5];
					Average[0][0][0][1]=1;
					Average[0][0][0][2]=Transcripts[0][0];
					Average[0][0][0][3]=Transcripts[0][1];
					Average[0][0][0][4]=Transcripts[0][2];
					for(int i=1;i<Transcripts.length;i++){
						x=isTermExist((String)Transcripts[i][0], Average);
						if(x!=-1){
							System.out.println(Transcripts[i][0]+"存在");
							y=isPaperExist((String)Transcripts[i][1], Average[x]);
							if(y!=-1){
								System.out.println(Transcripts[i][1]+"存在");
								z=isClassExist((String)Transcripts[i][2], Average[x][y]);
								if(z==-1){
									System.out.println(Transcripts[i][2]+"不存在");
									c[x][y]++;
									Average[x][y][c[x][y]][0]=(double) Transcripts[i][5];
									Average[x][y][c[x][y]][1]=1;
									Average[x][y][c[x][y]][4]=Transcripts[i][2];
								}
								else{
									System.out.println(Transcripts[i][2]+"存在");
									Average[x][y][z][0]=(double) Average[x][y][z][0]+(double) Transcripts[i][5];
									Average[x][y][z][1]=(int)Average[x][y][z][1]+1;
								}
							}
							else{
								System.out.println(Transcripts[i][1]+"不存在");
								b[x]++;
								Average[x][b[x]][0][0]=(double) Transcripts[i][5];
								Average[x][b[x]][0][1]=1;
								Average[x][b[x]][0][3]=Transcripts[i][1];
								Average[x][b[x]][0][4]=Transcripts[i][2];
							}
						}
						else{
							System.out.println(Transcripts[i][0]+"不存在");
							a++;
							Average[a][0][0][0]=(double) Transcripts[i][5];
							Average[a][0][0][1]=1;
							Average[a][0][0][2]=Transcripts[i][0];
							Average[a][0][0][3]=Transcripts[i][1];
							Average[a][0][0][4]=Transcripts[i][2];
						}
					}
					String Print="";
					for(int d=0;d<=a;d++){
						Print=Print+Average[d][0][0][2]+"\n";
						for(int e=0;e<=b[d];e++){
							Print=Print+"试卷号 "+Average[d][e][0][3]+"\n";
							for(int f=0;f<=c[d][e];f++){
								Print=Print+"  "+Average[d][e][f][4]+" 平均分："+(double)((double)Average[d][e][f][0]/(int)Average[d][e][f][1])
										+"（人数 "+Average[d][e][f][1]+"人）\n";
							}
						}
					}
				//	System.out.println(Average[0][0][0][0]);
					JOptionPane.showMessageDialog(null, Print, "统计", JOptionPane.PLAIN_MESSAGE);
				}
			});
			toolBar.add(btnAverage);
			
			JButton btnCheck = new JButton();//"查卷");
			btnCheck.setContentAreaFilled(false);
			btnCheck.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnCheck.png")));
		//	btnCheck.setFocusable(false);
			btnCheck.setToolTipText("查卷");
			btnCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableTranscript.getSelectedRowCount()==0){
						NoRowSelected();
						return;
					}
					if(tableTranscript.getSelectedRowCount()>1){
						NotOneRowSelected();
						return;
					}
					if(Admission==false) CheckPassword();
					else{
						if(!TestConnect()) return;
						new CheckScoreFrame((String)tableTranscript.getValueAt(tableTranscript.getSelectedRow(),3),
								(String)tableTranscript.getValueAt(tableTranscript.getSelectedRow(),1));
						frame.setVisible(false);
					}
				}
			});
			toolBar.add(btnCheck);
			
			JSeparator separatorToolBar = new JSeparator();
			separatorToolBar.setOrientation(SwingConstants.VERTICAL);
			toolBar.add(separatorToolBar);
			
			JButton btnExport = new JButton();
			btnExport.setContentAreaFilled(false);
			btnExport.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnExport.png")));
		//	btnExport.setFocusable(false);
			btnExport.setToolTipText("导出为Excel表格");
			btnExport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						DocumentExporter exp = new DocumentExporter();
						JFileChooser fileChooser=new JFileChooser();
						fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
						fileChooser.setFileFilter(new FileNameExtensionFilter("Excel表格(*.xls)","xls"));
						fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
						fileChooser.setApproveButtonText("保存");
						int option=fileChooser.showSaveDialog(null);
						if(option==JFileChooser.CANCEL_OPTION) return;
						if(option==JFileChooser.APPROVE_OPTION){
							System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
							if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".xls")){
								exp.exportTable(tableTranscript, new File(fileChooser.getSelectedFile().getAbsolutePath()));
							}
							else exp.exportTable(tableTranscript, new File(fileChooser.getSelectedFile().getAbsolutePath()+".xls"));
						}
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
				}
			});
			toolBar.add(btnExport);
			
			JButton btnFilter = new JButton();//"\u67E5\u627E");
			btnFilter.setContentAreaFilled(false);
			btnFilter.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSearch.png")));
		//	btnFilter.setFocusable(false);
			btnFilter.setToolTipText("\u67E5\u627E");
			btnFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Transcripts_Filter();
				}
			});
			toolBar.add(btnFilter);
			
			JButton btnRefresh = new JButton();//"\u5237\u65B0");
			btnRefresh.setContentAreaFilled(false);
			btnRefresh.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnRefresh.png")));
		//	btnRefresh.setFocusable(false);
			btnRefresh.setToolTipText("\u5237\u65B0");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GetTranscripts();
				}
			});
			toolBar.add(btnRefresh);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 37, 544, 310);
			add(scrollPane);
			
			tableTranscript = new MyTable();
			tableTranscript.getTableHeader().setReorderingAllowed(false);
			TranscriptModel=new DefaultTableModel(
				null,
				new String[] {
					"\u5B66\u671F", "\u8BD5\u5377\u7F16\u53F7", "\u73ED\u7EA7", "\u5B66\u53F7", "\u59D3\u540D", "\u6210\u7EE9"
				}			
			);
			tableTranscript.setModel(TranscriptModel);
			TranscriptSorter = new TableRowSorter<TableModel>(TranscriptModel);
			tableTranscript.setRowSorter(TranscriptSorter);
			GetTranscripts();
			scrollPane.setViewportView(tableTranscript);
		}
		//返回b[][][][]中a元素的位置，不存在返回-1
		private int isTermExist(String a,Object b[][][][]){
			for(int i=0;i<b.length;i++){
				if(b[i]==null) break;
				if(a.equals(b[i][0][0][2])) return i;
			}
			return -1;
		}
		//返回b[][][]中a元素的位置，不存在返回-1
		private int isPaperExist(String a,Object b[][][]){
			for(int i=0;i<b.length;i++){
				if(b[i]==null) break;
				if(a.equals(b[i][0][3])) return i;
			}
			return -1;
		}
		//返回b[][]中a元素的位置，不存在返回-1
		private int isClassExist(String a,Object b[][]){
			for(int i=0;i<b.length;i++){
				if(b[i]==null) break;
				if(a.equals(b[i][4])) return i;
			}
			return -1;
		}
		//刷新成绩表
		private void GetTranscripts(){
			if(!TestConnect()) return;
			DButil dbutil=new DButil();
			try {
				Transcripts = dbutil.inquiryGradeTeacher();
				if(Transcripts.length==0) return;
				TranscriptModel=new DefaultTableModel(
					Transcripts,
					new String[] {
						"\u5B66\u671F", "\u8BD5\u5377\u7F16\u53F7", "\u73ED\u7EA7", "\u5B66\u53F7", "\u59D3\u540D", "\u6210\u7EE9"
						}
					){
					/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
					public Class<?> getColumnClass(int column) {
				        return getValueAt(0, column).getClass();  
				    }
				};
				tableTranscript.setModel(TranscriptModel);
				DefaultTableCellRenderer r=new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.LEADING);
				tableTranscript.setDefaultRenderer(TranscriptModel.getColumnClass(5), r);
				TranscriptSorter = new TableRowSorter<TableModel>(TranscriptModel);
				tableTranscript.setRowSorter(TranscriptSorter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//成绩查找对话框
		private void Transcripts_Filter(){
			final JDialog Transcripts_Filter = new JDialog(frame,"查找",true);
			final JTextField txtClassFilter = new JTextField();
			
			Transcripts_Filter.setSize(245, 255);
			Transcripts_Filter.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-245)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-255)/2);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			Transcripts_Filter.getContentPane().add(panel);
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7B5B\u9009\u6761\u4EF6\uFF08\u53EF\u9009\uFF09", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			layeredPane.setBounds(10, 37, 218, 155);
			panel.add(layeredPane);
			
			JLabel lblIDFilter = new JLabel("\u8BD5\u5377\u7F16\u53F7");
			lblIDFilter.setBounds(21, 19, 54, 15);
			layeredPane.add(lblIDFilter);
			
			JLabel lblClassFilter = new JLabel("\u73ED    \u7EA7");
			lblClassFilter.setBounds(21, 53, 54, 15);
			layeredPane.add(lblClassFilter);
			
			JLabel lblStudentID = new JLabel("\u5B66    \u53F7");
			lblStudentID.setBounds(21, 87, 54, 15);
			layeredPane.add(lblStudentID);
			
			JLabel lblScore = new JLabel("\u6210\u7EE9\u533A\u95F4");
			lblScore.setBounds(21, 121, 54, 15);
			layeredPane.add(lblScore);
			final JTextField txtIDPaperFilter = new JTextField();
			txtIDPaperFilter.setBounds(79, 16, 115, 21);
			layeredPane.add(txtIDPaperFilter);
			txtIDPaperFilter.setColumns(10);
			
			txtClassFilter.setBounds(79, 50, 115, 21);
			layeredPane.add(txtClassFilter);
			txtClassFilter.setColumns(10);
			
			final JTextField txtStudentNum = new JTextField();
			txtStudentNum.setBounds(79, 84, 115, 21);
			layeredPane.add(txtStudentNum);
			txtStudentNum.setColumns(10);
			
			final JTextField txtMin = new JTextField();
			txtMin.setBounds(79, 118, 35, 21);
			layeredPane.add(txtMin);
			txtMin.setColumns(10);
			
			final JTextField txtMax = new JTextField();
			txtMax.setColumns(10);
			txtMax.setBounds(134, 118, 35, 21);
			layeredPane.add(txtMax);
			
			JLabel label = new JLabel("~");
			label.setBounds(121, 121, 13, 15);
			layeredPane.add(label);
			
			JLabel label_1 = new JLabel("(\u4E0D\u542B)");
			label_1.setBounds(169, 121, 39, 15);
			layeredPane.add(label_1);
			
	//		JLabel lblTime = new JLabel("\u8003\u8BD5\u65F6\u95F4");
	//		lblTime.setBounds(31, 12, 54, 15);
	//		panel.add(lblTime);
			
			final JSpinner spinnerYear = new JSpinner();
			spinnerYear.setEnabled(false);
			spinnerYear.setModel(new SpinnerNumberModel(2012, 2012, 9999, 1));
			spinnerYear.setEditor(new JSpinner.NumberEditor(spinnerYear, "####"));
			spinnerYear.setBounds(89, 8, 54, 22);
			panel.add(spinnerYear);
			
			JLabel label_2 = new JLabel("\u5E74");
			label_2.setBounds(143, 12, 12, 15);
			panel.add(label_2);
			
			final JComboBox comboBoxTerm = new JComboBox();
			comboBoxTerm.setEnabled(false);
			comboBoxTerm.setModel(new DefaultComboBoxModel(new String[] {"春季", "秋季"}));
			comboBoxTerm.setBounds(157, 9, 65, 21);
			panel.add(comboBoxTerm);
			
			final JCheckBox checkBoxTerm = new JCheckBox("\u8003\u8BD5\u65F6\u95F4");
			checkBoxTerm.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(checkBoxTerm.isSelected()){
						spinnerYear.setEnabled(true);
						comboBoxTerm.setEnabled(true);
					}
					else{
						spinnerYear.setEnabled(false);
						comboBoxTerm.setEnabled(false);
					}
				}
			});
			checkBoxTerm.setBounds(6, 8, 79, 23);
			panel.add(checkBoxTerm);
			
			JButton btnFilterFind = new JButton("\u67E5\u627E");
			btnFilterFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
					if(checkBoxTerm.isSelected()) filters.add(RowFilter.regexFilter(spinnerYear.getValue()+"年"+comboBoxTerm.getSelectedItem(),0));
					filters.add(RowFilter.regexFilter(txtIDPaperFilter.getText(),1));
					filters.add(RowFilter.regexFilter(txtClassFilter.getText(),2));
					filters.add(RowFilter.regexFilter(txtStudentNum.getText(),3));
					if(!txtMin.getText().isEmpty()){
						try {
							filters.add(RowFilter.numberFilter(ComparisonType.AFTER, Double.parseDouble(txtMin.getText()), 5));
							} 
						catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "分数区间输入有误！", "分数区间有误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					if(!txtMax.getText().isEmpty()){
						try {
							filters.add(RowFilter.numberFilter(ComparisonType.BEFORE, Double.parseDouble(txtMax.getText()), 5));
							} 
						catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "分数区间输入有误！", "分数区间有误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					TranscriptSorter.setRowFilter(RowFilter.andFilter(filters));
					Transcripts_Filter.dispose();
				}
			});
			btnFilterFind.setBounds(10, 196, 219, 23);
			panel.add(btnFilterFind);
			
			Transcripts_Filter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Transcripts_Filter.getRootPane().setDefaultButton(btnFilterFind);//设置默认按钮
			Transcripts_Filter.setResizable(false);
			Transcripts_Filter.setVisible(true);
		}
		private void NoRowSelected(){//未选择任何行
			JOptionPane.showMessageDialog(null, "未选择任何行！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		private void NotOneRowSelected(){//查卷时选择多行
			JOptionPane.showMessageDialog(null, "只能选择一份试卷查卷！", "无法执行", JOptionPane.ERROR_MESSAGE);
		}
		//查卷前的身份认证
		private void CheckPassword(){
			final JDialog checkPwd=new JDialog(frame,"身份验证",true);
			checkPwd.setSize(210,150);
			checkPwd.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-210)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-150)/2);
			JPanel panel = new JPanel();
			checkPwd.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			final JPasswordField txtPwd = new JPasswordField();
			txtPwd.setBounds(16, 43, 170, 21);
			panel.add(txtPwd);
			
			JLabel label = new JLabel("\u8BF7\u8F93\u5165\u67E5\u5377\u53E3\u4EE4\uFF1A");
			label.setBounds(16, 14, 109, 15);
			panel.add(label);
			
			JButton btnOk = new JButton("\u786E\u8BA4");
			btnOk.setBounds(55, 84, 93, 23);
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DButil dbutil=new DButil();
					try {
						if(!dbutil.canCheckPaper(String.valueOf(txtPwd.getPassword()))){
							JOptionPane.showMessageDialog(null, "口令不正确！", "认证失败", JOptionPane.ERROR_MESSAGE);
							checkPwd.dispose();
							return;
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Admission=true;
					if(!TestConnect()) {
						checkPwd.dispose();
						return;
					}
					new CheckScoreFrame((String)tableTranscript.getValueAt(tableTranscript.getSelectedRow(),3),
							(String)tableTranscript.getValueAt(tableTranscript.getSelectedRow(),1));
					checkPwd.dispose();
					frame.setVisible(false);
				}
			});
			panel.add(btnOk);
			checkPwd.getRootPane().setDefaultButton(btnOk);//设置默认按钮
			checkPwd.setResizable(false);
			checkPwd.setVisible(true);
			
		}
	}
}