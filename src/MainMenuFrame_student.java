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
		frame=new JFrame("��������·�������ԡ������ϵͳ");
		frame.setSize(541, 343);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-541)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-343)/2);
		
		mainPanel = new BackgroundImagePanel();
		mainPanel.setLayout(null);
		addDefaultLayeredPane_Student();
		lblWelcome = new JLabel("\u6B22\u8FCE\uFF01"+student.GetName()+" | Ȩ��Ϊѧ��");
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
						JOptionPane.showMessageDialog(null,"���Ѿ���ɿ��ԣ������ؿ���","������Ϣ",JOptionPane.ERROR_MESSAGE);
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
	private static void ConnectFail(){//��������ʧ��
		JOptionPane.showMessageDialog(null, "��������Ͽ����ӣ����������Ƿ�ͨ��", "��������ʧ��", JOptionPane.ERROR_MESSAGE);
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
			txtIntroduction.setText("�������ϵͳ�Ľ�ɫ����Ϊѧ���ͽ�ʦ���ࡣ\r" +
					"����ѧ����˵�����е�¼��������ϰ�����߿��ԡ��ɼ���ѯ���޸ĸ������ϼ�����ȹ��ܡ�\r" +
					"(1)��¼��ѧ�������Լ���ѧ�ź�������е�¼������Աֻ�ṩ��ѧ��������ϰ�����߿��ԡ��ɼ���ѯ���޸ĸ������ϼ������Ȩ�ޡ�\r" +
					"(2)������ϰ��ѧ����¼ϵͳ֮�󣬿ɸ���֪ʶ�㡢�ѶȺ�������������ԵĿ�ǰ��ϰ��������ѧ��������Ŀ��ϵͳ�����Զ��ľ��ܲ�����������ʾ��\r" +
					"(3)���߿��ԣ�ѧ����¼ϵͳ֮�󣬿��Կ�ʼ��ʱ���ԡ�������������ʦָ��������ʱֻ��ʾѧ����ѡ�����д�Ĵ𰸣��������Ի��ʹ���ʾ��������ύ�Ծ�����ϵͳ�����͹���Ŀ���Զ����֣�������Ŀ���ύ��ʦ���֡�����һ���Ծ�һ��ѧ��ֻ�ܿ�һ�Ρ�\r" +
					"(4)�ɼ���ѯ��ѧ�����Ի���ϰ�󣬿������ɼ���ѯ���ܲ�ѯ���μӵĿ��Գɼ������\r" +
					"(5)�޸ĸ������ϼ����룺ѧ���������޸ĸ������ϼ����롣�������ϰ��������Ͱ༶������Ա�ɶ�ѧ�����Ͻ�����˺��޸ġ�\r" +
			//		"\r" +
					"���ڽ�ʦ��˵�����е�¼����������Ծ�����ľ��ܡ��ɼ��������ȹ��ܡ�\r" +
					"(1)��¼����ʦ����ע����˺ŵ�¼ϵͳ��ע��ʱ���������Ա����������롣��ʦֻ�ܰ��չ���Ա�����Ȩ��ʹ�ø�����ѡ�\r" +
					"(2)���������ʦ��������������¼�롢������ˡ��������⡢�޸����⣨�𰸣���ɾ������Ȳ�����\r" +
					"(3)�Ծ������ʦ���Ը�����Ӧ���͡��Ѷȡ�����֪ʶ���һϵ��Լ������ȡֵ����ϵͳ�����Զ����ͬʱ�����ܹ��������Ծ����ɾ���Ȳ�������ʦ�����������õ��Ծ����Ԥ�������棨Word�ĵ����Ȳ�����\r" +
					"(4)�ľ��ܣ���ʦ������ϵͳ������ѧ�������Զ��ľ��ܣ�������Ծ��������������ض����⼰ȫ��������ֶ��ľ�\r" +
					"(5)�ɼ�������ʦ���԰�һ����������鿴ѧ���ɼ������������ض�����������ѧ�����Ծ�����������ɼ�ͳ�Ʒ��������ɰ�һ������������������ض���ʽҪ���ѧ���ɼ�����Excel����\r" +
					"(6)����ܣ���ʦ�ɸ���ѧ��Ҫ����в�����������������Ŀ�������и���������ѧ������ͳ�֡���ʦ���ʱ���������Ա��������롣");
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
							"ʱ��", "�¼�"
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
						JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
						return;
					}
					student.ReadAccount();
					txtName.setText(student.GetName());
					txtClass.setText(student.GetClass());
					lblWelcome.setText("\u6B22\u8FCE\uFF01"+student.GetName()+" | Ȩ��Ϊѧ��");
					DButil dbutil=new DButil();
					try {
						LogModel= new DefaultTableModel(
							dbutil.readLog(sNumber),
							new String[] {
								"ʱ��", "�¼�"
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
		public void PwdNotEqual(){//�������벻ͬ
			JOptionPane.showMessageDialog(null, "�������벻ͬ��", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		public void OldEqualsNew(){//�¾�������ͬ
			JOptionPane.showMessageDialog(null, "�����벻�����������ͬ��", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		public void NewPwdWrong(){//���������Ϊ6~16λ
			JOptionPane.showMessageDialog(null, "���������Ϊ6~16λ��", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		public void PwdNotCorrect(){//�������
			JOptionPane.showMessageDialog(null, "��������", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		public void ReviseFail(){//�޸�ʧ��
			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ����Ժ�����", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		public void ReviseSuccess(){//�޸ĳɹ�
			JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "�޸ĳɹ�", JOptionPane.INFORMATION_MESSAGE);
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