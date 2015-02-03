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
	public static int RefreshState=0;//��ˢ�µ�״̬��Ϊ0Ϊ����ˢ�£�Ϊ1Ϊ��ˢ�´���Ϊ2Ϊ��ˢ�³ɼ���
	private boolean Admission=false;//������
	
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
	//��ʦ������
	public void FrameMain(String Name){
		frame=new JFrame("��������·�������ԡ������ϵͳ");
		frame.setSize(680, 420);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-680)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-420)/2);
		//�����
		mainPanel = new BackgroundImagePanel();
		mainPanel.setLayout(null);
		addDefaultLayeredPane();//���Ĭ��panel��������
		JLabel lblWelcome = new JLabel("\u6B22\u8FCE\uFF01"+Name+" | Ȩ��Ϊ��ʦ");//��ӭ��ǩ
		lblWelcome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWelcome.setBounds(110, 10, 445, 15);
		mainPanel.add(lblWelcome);
		//ע����ť
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
		//�ָ���
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(98, 47, 2, 335);
		mainPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 35, 654, 2);
		mainPanel.add(separator_1);
		//�Ծ����ť
		JButton btnPapersManage = new JButton("\u8BD5\u5377\u7BA1\u7406");
		btnPapersManage.setContentAreaFilled(false);
		btnPapersManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admission=false;//������������Ϊ��
				addPapersManagementPane();
			}
		});
		btnPapersManage.setBounds(10, 57, 80, 26);
		mainPanel.add(btnPapersManage);
		//�������ť
		JButton btnQuestionsManage = new JButton("\u8BD5\u9898\u7BA1\u7406");
		btnQuestionsManage.setContentAreaFilled(false);
		btnQuestionsManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admission=false;//������������Ϊ��
				addQuestionsManagementPane();
			}
		});
		btnQuestionsManage.setBounds(10, 140, 80, 26);
		mainPanel.add(btnQuestionsManage);
		//�����ľ�ť
		JButton btnOnlineCorrect = new JButton("\u5728\u7EBF\u6279\u6539");
		btnOnlineCorrect.setContentAreaFilled(false);
		btnOnlineCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admission=false;//������������Ϊ��
				addOnlineCorrectPane();
			}
		});
		btnOnlineCorrect.setBounds(10, 223, 80, 26);
		mainPanel.add(btnOnlineCorrect);
		//������ť
		JButton btnScoreTable = new JButton("\u5206\u6570\u8868");
		btnScoreTable.setContentAreaFilled(false);
		btnScoreTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addTranscriptTablePane();
			}
		});
		btnScoreTable.setBounds(10, 306, 80, 26);
		mainPanel.add(btnScoreTable);
		//��������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.setResizable(false);
		frame.setVisible(true);
		//����ˢ��ʱ��
		frame.addHierarchyListener(new HierarchyListener(){
			public void hierarchyChanged(HierarchyEvent arg0) {
				if(RefreshState==1){//����Ǵ��������Ľ����л�����������б�ˢ��
					addOnlineCorrectPane();
					RefreshState=0;//����
				}
				if(RefreshState==2){//����ǴӲ������л��������ɼ���ˢ��
					addTranscriptTablePane();
					RefreshState=0;//����
				}
			}
		});
	}
	//������������
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
	private static void ConnectFail(){//��������ʧ��
		JOptionPane.showMessageDialog(null, "��������Ͽ����ӣ����������Ƿ�ͨ��", "��������ʧ��", JOptionPane.ERROR_MESSAGE);
	}
	public void addDefaultLayeredPane(){//���Ĭ�������������
//		mainPanel.remove(layerpanel);
		layerpanel=new DefaultLayeredPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addPapersManagementPane(){//����Ծ���������������
		mainPanel.remove(layerpanel);
		layerpanel=new PapersManagementPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addBuildPaperPane(){//���������
		mainPanel.remove(layerpanel);
		layerpanel=new BuildPaperPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addQuestionsManagementPane(){//�������������
		mainPanel.remove(layerpanel);
		layerpanel=new QuestionsManagementPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addAddQuestionPane(int AddorEdit,String id,String Question,String Question_Ans,int Difficulty,int Point,int Type,int Mode){//���������ӻ�༭����
		mainPanel.remove(layerpanel);
		layerpanel=new AddQuestionPane(AddorEdit,id,Question,Question_Ans,Difficulty,Point,Type,Mode);
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addOnlineCorrectPane(){//����ľ����
		mainPanel.remove(layerpanel);
		layerpanel=new OnlineCorrectPane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}
	public void addTranscriptTablePane(){//��ӳɼ������
		mainPanel.remove(layerpanel);
		layerpanel=new TranscriptTablePane();
		mainPanel.add(layerpanel);
		mainPanel.updateUI();
	}

	//=======================================================================================================
	//Ĭ�����
	class DefaultLayeredPane extends JLayeredPane{
		//���췽��
		public DefaultLayeredPane(){
			setBounds(100, 35, 564, 347);
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
		//	txtIntroduction.set
			txtIntroduction.setCaretPosition(0);
			scrollPane.setBounds(10, 5, 544, 342);
	//		add(txtIntroduction);
		}
	}
	//===============================================================================================================
	//�Ծ�������
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
		//���췽��
		public PapersManagementPane(){
			setBounds(100, 35, 564, 347);
			
			JToolBar toolBar_Paper = new JToolBar();
			toolBar_Paper.setFloatable(false);
			toolBar_Paper.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			toolBar_Paper.setBounds(376, 9, 178, 19);
			add(toolBar_Paper);
			JButton btnAdd_Paper = new JButton();//���ť
			btnAdd_Paper.setContentAreaFilled(false);
			btnAdd_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnAdd.png")));
		//	btnAdd_Paper.setFocusable(false);
			btnAdd_Paper.setToolTipText("\u6DFB\u52A0");
			btnAdd_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addBuildPaperPane();//ת��������
				}
			});
			toolBar_Paper.add(btnAdd_Paper);
			
			JButton btnDelete_Paper = new JButton();//ɾ���Ծ�ť;
			btnDelete_Paper.setContentAreaFilled(false);
			btnDelete_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnDelete.png")));
		//	btnDelete_Paper.setFocusable(false);
			btnDelete_Paper.setToolTipText("\u5220\u9664");
			btnDelete_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablePaper.getSelectedRow()==-1){//�ж�ѡ��ĸ����Ƿ����0
						NoRowSelected();
						return;
					}
					else if(JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��ѡ��������", "ȷ��ɾ��", JOptionPane.YES_NO_OPTION)==1) 
						return;
					DButil dbutils=new DButil();
					if(!TestConnect()) return;
					String UsedPaper="";//����ѱ�ʹ�õ��Ծ��
					int m=0;
					try {
						for(int i=0;i<tablePaper.getSelectedRowCount();i++){
							if(dbutils.deletePaper((String)tablePaper.getValueAt(tablePaper.getSelectedRows()[i], 1))==-1){
								//����ѱ�ʹ�ã����Ծ�������UsedPaper
								UsedPaper+=(String)tablePaper.getValueAt(tablePaper.getSelectedRows()[i], 1)+"��";
								m=1;
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(m==1){
						//��ȥ��UsedPaperĩβ�ġ�������
						char[] n=new char[UsedPaper.length()-1];//��ת���ַ�����
						UsedPaper.getChars(0, UsedPaper.length()-1, n, 0);//ȥ��ĩβ�ġ�������
						JOptionPane.showMessageDialog(null, new String(n)+"�ѱ�ʹ�ã��޷�ɾ����", null, JOptionPane.WARNING_MESSAGE);//��ʾ
					}
					GetPapers();
				}
			});
			toolBar_Paper.add(btnDelete_Paper);
			
			JSeparator separatorToolBar_Paper = new JSeparator();
			separatorToolBar_Paper.setOrientation(SwingConstants.VERTICAL);
			toolBar_Paper.add(separatorToolBar_Paper);
			
			JButton btnFilter_Paper = new JButton();//���Ұ�ť
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
			
			JButton btnPreview_Paper = new JButton();//"Ԥ��"��ť
			btnPreview_Paper.setContentAreaFilled(false);
			btnPreview_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnPreview.png")));
		//	btnPreview_Paper.setFocusable(false);
			btnPreview_Paper.setToolTipText("Ԥ��");
			btnPreview_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablePaper.getSelectedRowCount()==0){//�ж��Ƿ���ѡ��һ��
						NoRowSelected();
						return;
					}
					if(tablePaper.getSelectedRowCount()>1){//�ж��Ƿ�ѡ�����
						JOptionPane.showMessageDialog(null, "ֻ��ѡ��һ���Ծ�Ԥ����", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!TestConnect()) return;
					PreviewPaper((String) tablePaper.getValueAt(tablePaper.getSelectedRow(),1));//Ԥ��
				}
			});
			toolBar_Paper.add(btnPreview_Paper);
			
			JButton btnRefresh_Paper = new JButton();//ˢ�°�ť
			btnRefresh_Paper.setContentAreaFilled(false);
			btnRefresh_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnRefresh.png")));
		//	btnRefresh_Paper.setFocusable(false);
			btnRefresh_Paper.setToolTipText("\u5237\u65B0");
			btnRefresh_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GetPapers();//ˢ��
				}
			});
			toolBar_Paper.add(btnRefresh_Paper);
			
			JSeparator separatorToolBar2_Paper = new JSeparator();
			separatorToolBar2_Paper.setOrientation(SwingConstants.VERTICAL);
			toolBar_Paper.add(separatorToolBar2_Paper);
			
			JButton btnSetPaper_Paper = new JButton();//"��Ϊ�����Ծ�"��ť
			btnSetPaper_Paper.setContentAreaFilled(false);
			btnSetPaper_Paper.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSetAs.png")));
		//	btnSetPaper_Paper.setFocusable(false);
			btnSetPaper_Paper.setToolTipText("��Ϊ�����Ծ�");
			btnSetPaper_Paper.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablePaper.getSelectedRowCount()==0){//�Ƿ�ѡ��һ��
						NoRowSelected();
						return;
					}
					if(tablePaper.getSelectedRowCount()>1){//�Ƿ�ѡ�����
						NotOneRowSelected();
						return;
					}
					if(!TestConnect()) return;
					DButil dbutil=new DButil();
					dbutil.addExamPaper((String) tablePaper.getValueAt(tablePaper.getSelectedRow(),1));//��Ϊ�����Ծ�
					GetPapers();
				}
			});
			toolBar_Paper.add(btnSetPaper_Paper);
			
			JScrollPane scrollPane_Paper = new JScrollPane();
			scrollPane_Paper.setBounds(10, 37, 544, 310);
			add(scrollPane_Paper);
			
			tablePaper = new MyTable();//�Ծ��
			tablePaper.getTableHeader().setReorderingAllowed(false);
			PaperModel=new DefaultTableModel(
				null,
				new String[] {
					"�����Ծ�", "\u8BD5\u5377\u7F16\u53F7", "\u96BE\u5EA6", "\u5305\u542B\u77E5\u8BC6\u70B9", "ʱ��", "\u6EE1\u5206"
				}	
			);
			tablePaper.setModel(PaperModel);
			PaperSorter = new TableRowSorter<TableModel>(PaperModel);
			tablePaper.setRowSorter(PaperSorter);
			GetPapers();//ˢ���Ծ��
			tablePaper.getColumnModel().getColumn(0).setPreferredWidth(63);
			tablePaper.getColumnModel().getColumn(1).setPreferredWidth(100);
			tablePaper.getColumnModel().getColumn(2).setPreferredWidth(44);
			tablePaper.getColumnModel().getColumn(3).setPreferredWidth(281);
			tablePaper.getColumnModel().getColumn(4).setPreferredWidth(50);
			tablePaper.getColumnModel().getColumn(5).setPreferredWidth(50);
			tablePaper.addMouseListener(new MouseAdapter(){//���˫��Ԥ������
				public void mouseClicked(MouseEvent e){
					if (e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {//������˫��
						if(tablePaper.getSelectedRowCount()==1){
							if(!TestConnect()) return;
							PreviewPaper((String) tablePaper.getValueAt(tablePaper.getSelectedRow(),1));
						}
					}
				}
			});
			scrollPane_Paper.setViewportView(tablePaper);
		}
		//ˢ���Ծ��
		private void GetPapers(){
			if(!TestConnect()) return;
			DButil dbutil=new DButil();
			try {
				PaperModel=new DefaultTableModel(
					dbutil.readAllPaper(),
					new String[] {
						"�����Ծ�", "\u8BD5\u5377\u7F16\u53F7", "\u96BE\u5EA6", "\u5305\u542B\u77E5\u8BC6\u70B9", "ʱ��", "\u6EE1\u5206"
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
		//Ԥ���Ծ�
		private void PreviewPaper(final String PaperID){
			final JDialog preview=new JDialog(frame,"Ԥ�� �Ծ�ţ�"+PaperID);
		//	preview.setUndecorated(true);
			preview.setSize(380, 520);
			preview.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-380)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-520)/2);
			//����
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setBackground(Color.WHITE);
			scrollPane.setViewportView(textPane);
			//���������ʽ
			SimpleAttributeSet BOLD = new SimpleAttributeSet();//����
			StyleConstants.setBold(BOLD, true);
			SimpleAttributeSet KAIFONT = new SimpleAttributeSet();//����
			StyleConstants.setFontFamily(KAIFONT,"����");
			SimpleAttributeSet GRAY = new SimpleAttributeSet();//��ɫ����
			StyleConstants.setForeground(GRAY, Color.DARK_GRAY);
			SimpleAttributeSet FANGFONT = new SimpleAttributeSet();//������
			StyleConstants.setFontFamily(FANGFONT,"����");
			//�ı�������
			DButil dbutil=new DButil();
			Document doc = textPane.getDocument();
			try {
				String[] IDs=dbutil.checkPaper(PaperID);
				double[] Scores=dbutil.showTypeScore(PaperID);
				
				for(int i=0;i<IDs.length;i++){
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==0) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//������
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "��) ",
								KAIFONT);//��ֵ
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//��������
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//��ͼƬ����ʾ
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
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==1) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//������
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "��) ",
								KAIFONT);//��ֵ
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//��������
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//��ͼƬ����ʾ
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
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==2) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//������
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "��) ",
								KAIFONT);//��ֵ
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//��������
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//��ͼƬ����ʾ
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
					Object[] QuestionInformation = dbutil.selectById(IDs[i]);//��ȡ������Ϣ
					if (QuestionInformation[0] == null) continue;
					if ((int) QuestionInformation[3]==3) 
					{	
						doc.insertString(doc.getLength(), IDs[i] + "\n", null);//������
						doc.insertString(doc.getLength(), "("
								+ Scores[(int) QuestionInformation[3]] + "��) ",
								KAIFONT);//��ֵ
						doc.insertString(doc.getLength(),
								(String) QuestionInformation[0] + "\n",
								FANGFONT);//��������
						IOImage ioimage = new IOImage();
						PictureUtils picture = new PictureUtils();
						byte[] buffer = ioimage.readBolb(IDs[i]);
						if (buffer == null) {//��ͼƬ����ʾ
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
			
			textPane.setCaretPosition(0);//�����������
			textPane.setFont(new Font("����", Font.PLAIN, 15));
			JButton btnExport=new JButton("����ΪWord�ĵ�");
			btnExport.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DocumentExporter exp=new DocumentExporter();
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
					fileChooser.setFileFilter(new FileNameExtensionFilter("Word�ĵ�(*.doc)","doc"));
					fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
					fileChooser.setApproveButtonText("����");
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
		//�Ծ���ҶԻ���
		private void Paper_Filter(){
			Paper_Filter=new JDialog(frame,"����",true);
			Paper_Filter.setSize(245, 232);
			Paper_Filter.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-245)/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-232)/2);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			Paper_Filter.getContentPane().add(panel);
			
			JLabel lblIDFilter = new JLabel("\u8BD5\u5377\u7F16\u53F7");
			lblIDFilter.setBounds(33, 12, 54, 15);
			panel.add(lblIDFilter);
			
			JButton btnFilterFind = new JButton("\u67E5\u627E");//���Ұ�ť
			btnFilterFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
					filters.add(RowFilter.regexFilter(txtIDPaperFilter.getText(),1));//�Ծ��������ɸѡ��
					if(boxPaperFilterDifficulty.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter("^"+(String)boxPaperFilterDifficulty.getSelectedItem()+"$",2));//�Ѷ������ɸѡ��
					if(boxPaperFilterPoint.getSelectedIndex()!=0) filters.add(RowFilter.regexFilter((String)boxPaperFilterPoint.getSelectedItem(),3));//֪ʶ�������ɸѡ��
					if(!txtPaperFilterSumScore.getText().isEmpty()) filters.add(RowFilter.regexFilter("^"+txtPaperFilterSumScore.getText()+"$",5));//���������ɸѡ��
					PaperSorter.setRowFilter(RowFilter.andFilter(filters));
					Paper_Filter.dispose();
				}
			});
			btnFilterFind.setBounds(73, 169, 93, 23);
			panel.add(btnFilterFind);
			
			txtIDPaperFilter = new JTextField();//�Ծ���
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
			
			boxPaperFilterDifficulty = new JComboBox();//�Ѷ�
			boxPaperFilterDifficulty.setModel(new DefaultComboBoxModel(new String[] {"ȫ��", "\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxPaperFilterDifficulty.setSelectedIndex(0);
			boxPaperFilterDifficulty.setBounds(81, 21, 121, 21);
			layeredPane.add(boxPaperFilterDifficulty);
			
			boxPaperFilterPoint = new JComboBox();//����֪ʶ��
			boxPaperFilterPoint.setModel(new DefaultComboBoxModel(new String[] {"(��)", "\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxPaperFilterPoint.setSelectedIndex(0);
			boxPaperFilterPoint.setBounds(81, 54, 121, 21);
			layeredPane.add(boxPaperFilterPoint);
			
			txtPaperFilterSumScore = new JTextField();//����
			txtPaperFilterSumScore.setBounds(81, 87, 66, 21);
			layeredPane.add(txtPaperFilterSumScore);
			txtPaperFilterSumScore.setColumns(10);
			
			Paper_Filter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Paper_Filter.getRootPane().setDefaultButton(btnFilterFind);//����Ĭ�ϰ�ť
			Paper_Filter.setResizable(false);
			Paper_Filter.setVisible(true);
		}
		private void NoRowSelected(){//δѡ���κ���
			JOptionPane.showMessageDialog(null, "δѡ���κ��У�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		private void NotOneRowSelected(){//���ÿ����Ծ�ʱѡ�����
			JOptionPane.showMessageDialog(null, "ֻ������һ���Ծ�Ϊ�����Ծ�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
	}
	//================================================================================================================
	//������
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
		//���췽��
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
			
			JLabel label = new JLabel("\u8bf7\u8f93\u5165\u6b63\u6574\u6570\uff01");//����������������������
			label.setForeground(Color.RED);
			TotalpopupMenu.add(label);
			txtTotal.addKeyListener(new KeyAdapter(){
				@Override
				public void keyReleased(KeyEvent e) {
					try{
						if(Integer.parseInt(txtTotal.getText())>0){//�ж��ܷ��Ƿ�Ϊ������
							//�����
							if(TotalpopupMenu.isShowing()) {//��������ʧ
							TotalpopupMenu.setVisible(false);
							}
							try{
								//���ж�ʱ���Ƿ�Ϊ������������ǣ����ť�ɵ�
								if(Integer.parseInt(txtTime.getText())>0) btnBuild.setEnabled(true);
							}catch(Exception NotInt){
							}
						}
						else{//������ǣ�������ʾ
							TotalpopupMenu.show(txtTotal, 0, 20);
							txtTotal.requestFocus();
							btnBuild.setEnabled(false);
						}
					}catch(Exception NotInt){//�������������������
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
			
			JLabel label_1 = new JLabel("\u8bf7\u8f93\u5165\u6b63\u6574\u6570\uff01");//����������������������
			label_1.setForeground(Color.RED);
			TimepopupMenu.add(label_1);
			txtTime.addKeyListener(new KeyAdapter(){
				public void keyReleased(KeyEvent e) {
					try{
						if(Integer.parseInt(txtTime.getText())>0){//�ж�ʱ���Ƿ������
							//�����
							if(TimepopupMenu.isShowing()) {//��������ʧ
							TimepopupMenu.setVisible(false);
							}
							try{
								//���ж��ܷ��Ƿ�Ϊ������������ǣ����ť�ɵ�
								if(Integer.parseInt(txtTotal.getText())>0) btnBuild.setEnabled(true);
							}catch(Exception NotInt){
							}
						}
						else {//������ǣ�������ʾ
							TimepopupMenu.show(txtTime, 0, 20);
							txtTime.requestFocus();
							btnBuild.setEnabled(false);
						}
					}catch(Exception NotInt){//�������������������
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
			
			btnBuild = new JButton("\u7EC4\u5377");//���ť
			btnBuild.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BuildPaper();
				}
			});
			btnBuild.setEnabled(false);
			btnBuild.setBounds(126, 310, 93, 23);
			add(btnBuild);
			
			JButton btnBack1 = new JButton("\u8FD4\u56DE");//���ذ�ť
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
			//���ͱ���������
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
			//��ʾ���ͱ�������������ֵ
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
			//֪ʶ���ѡ��
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
			//��Ӱ��¼��������ݶ�ѡ��ť״̬���ı���������Ŀ���״̬
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
			//����֪ʶ�����������
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
			//ȫѡ��ť
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
			//��ѡ��ť
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
		//���ǰ�ķ����㷨
		private void BuildPaper(){
			try {
				int TotalScore=Integer.parseInt(txtTotal.getText());
				int Time=Integer.parseInt(txtTime.getText());
				int Difficulty=boxDifficulty.getSelectedIndex();
				double Point[]=new double[10];
				double Type[]=new double[4];
				double PointsTotal=0;
				double TypesTotal=0;
				//����֪ʶ���ܱ���
				for(int i=0;i<10;i++){
					if(Points[i].isSelected()) Point[i]=(double)(boxPointsPercent[i].getSelectedIndex()+1.0)/10.0;
					else Point[i]=0;
			//		System.out.println(Point[i]+"");
					PointsTotal+=Point[i];
				}
			//	System.out.println(PointsTotal+"");
				//���֪ʶ���ܱ�����Ϊһ����ʾ
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
				//��������ܱ�����Ϊһ����ʾ
				if(Math.abs(1.0-TypesTotal)>0.01){
					TypesWrong();
					return;
				}
				if(!TestConnect()) return;
				MakeupPapers makeuppapers=new MakeupPapers();
				int count=makeuppapers.getQdetails(Point, Type, Difficulty, TotalScore, Time);
				if(count>0){
					JOptionPane.showMessageDialog(null, "���ɹ���","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					addPapersManagementPane();
				}
				else{
					JOptionPane.showMessageDialog(null,"���ʧ�ܣ������ԣ�","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private void PointsWrong(){//֪ʶ���������
			JOptionPane.showMessageDialog(null, "֪ʶ�������������", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		private void TypesWrong(){//���ͱ�������
			JOptionPane.showMessageDialog(null, "���ͱ�����������", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
	}
	//===============================================================================================================
	//����������
	class AddQuestionPane extends JLayeredPane{
		private JTextArea txtQuestion;
		private JTextArea txtAns;
		private JTextField txtPath;
		private JComboBox boxPropertyDifficulty;
		private JComboBox boxPropertyPoint;
		private JComboBox boxPropertyType;
		private JLabel PicPreview;
		//���췽��
		public AddQuestionPane(final int AddorEdit,final String QuestionID,String Question,String Question_Ans,int Difficulty,int Point,int Type,int Mode){
			setBounds(100, 35, 574, 347);
		//	mainPanel.add(AddQuestionPane);
			JScrollPane scrollQuestion = new JScrollPane();
			scrollQuestion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollQuestion.setBounds(10, 10, 311, 163);
			add(scrollQuestion);
			
			txtQuestion = new JTextArea();//�������ݿ�
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
			
			txtAns = new JTextArea();//�𰸿�
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
			PicUploadlayeredPane.setBorder(new TitledBorder(null, "\u4E0A\u4F20\u56FE\u7247"+"��<500KB��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			PicUploadlayeredPane.setBounds(331, 145, 233, 58);
			add(PicUploadlayeredPane);
			
			JButton btnExplore = new JButton("\u6D4F\u89C8");//ͼƬ�����ť
			btnExplore.setBounds(172, 17, 58, 23);
			PicUploadlayeredPane.add(btnExplore);
			btnExplore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("ͼƬ�ļ�(*.jpg,*.png)","jpg","png"));
					fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
					int option=fileChooser.showOpenDialog(null);
					if(option==JFileChooser.CANCEL_OPTION) return;
					if(option==JFileChooser.APPROVE_OPTION) txtPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
					RefreshPicture();
				}
			});
			
			txtPath = new JTextField();//·���ı���
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
			
			boxPropertyDifficulty = new JComboBox();//�Ѷ�������
			boxPropertyDifficulty.setModel(new DefaultComboBoxModel(new String[] {"\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxPropertyDifficulty.setSelectedIndex(0);
			boxPropertyDifficulty.setBounds(81, 14, 121, 21);
			QuestionUploadOptions.add(boxPropertyDifficulty);
			
			boxPropertyPoint = new JComboBox();//֪ʶ��������
			boxPropertyPoint.setModel(new DefaultComboBoxModel(new String[] {"\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxPropertyPoint.setSelectedIndex(0);
			boxPropertyPoint.setBounds(81, 46, 121, 21);
			QuestionUploadOptions.add(boxPropertyPoint);
			
			boxPropertyType = new JComboBox();//����������
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
			
			JButton btnBack = new JButton("\u8FD4\u56DE");//���ذ�ť
			btnBack.setBounds(461, 320, 93, 23);
			add(btnBack);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addQuestionsManagementPane();
				}
			});
			
			JButton btnUpload = new JButton("\u786E\u5B9A");//ȷ�ϰ�ť
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
						JOptionPane.showMessageDialog(null, "ͼƬ̫���ϴ�ʧ�ܣ�", "���ʧ��", JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnUpload.setBounds(343, 320, 93, 23);
			add(btnUpload);
			
			PicPreview = new JLabel("\u65E0\u9884\u89C8\u56FE");//ͼƬԤ����
			PicPreview.setBorder(new LineBorder(Color.LIGHT_GRAY));
			PicPreview.setHorizontalAlignment(SwingConstants.CENTER);
			PicPreview.setBounds(331, 10, 233, 125);
			add(PicPreview);
			
			final JPopupMenu PopPicMenu=new JPopupMenu();
			JToolBar PicBar=new JToolBar();
			PicBar.setFloatable(false);
			JButton PicRefresh=new JButton("ˢ��");
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
			JButton PicDelete=new JButton("�Ƴ�ͼƬ");
			PicDelete.setContentAreaFilled(false);
			PicDelete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					PicBytes=null;
					txtPath.setText(null);
					RefreshPicture();
				}
			});
			PicBar.add(PicDelete);
			JButton PicRestore=new JButton("��ԭ");
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
			//����ֵ
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
		//Ԥ��ͼƬ����
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
		private void addQuestion_QuestionNotEnter(){//��������Ŀ
			JOptionPane.showMessageDialog(null, "��������Ŀ��", "���ʧ��", JOptionPane.ERROR_MESSAGE);
			txtQuestion.requestFocus();
		}
		private void addQuestion_AnsNotEnter(){//�������
			JOptionPane.showMessageDialog(null, "������𰸣�", "���ʧ��", JOptionPane.ERROR_MESSAGE);
			txtAns.requestFocus();
		}
		private void addQuestion_PicNotExist(){//ͼƬ������
			JOptionPane.showMessageDialog(null, "ͼƬ�����ڣ������ļ�·����", "���ʧ��", JOptionPane.ERROR_MESSAGE);
			txtPath.requestFocus();
			txtPath.selectAll();
		}
		private void addQuestion_Success(){//�������ɹ�
			JOptionPane.showMessageDialog(null, "��ӳɹ���", "��ӳɹ�", JOptionPane.INFORMATION_MESSAGE);
			addQuestionsManagementPane();
		}
		private void addQuestion_Failure(){//�������ʧ��
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ������ԣ�", "���ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		private void editQuestion_Success(){//�޸�����ɹ�
			JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "�޸ĳɹ�", JOptionPane.INFORMATION_MESSAGE);
			addQuestionsManagementPane();
		}
		private void editQuestion_Failure(){//�޸�����ʧ��
			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ������ԣ�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
	}
	//===============================================================================================
	//����������
	class QuestionsManagementPane extends JLayeredPane{
		private MyTable tableQuestion = new MyTable();
		private DefaultRowSorter<TableModel, Integer> QuestionSorter;
		private DefaultTableModel QuestionModel;
		private JTextField txtIDQuestionFilter;
		private JComboBox boxQuestionFilterDifficulty;
		private JComboBox boxQuestionFilterPoint;
		private JComboBox boxQuestionFilterType;
		private JDialog Question_Filter;
		//���췽��
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
					else if(JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��ѡ��������", "ȷ��ɾ��", JOptionPane.YES_NO_OPTION)==1) 
						return;
					DButil dbutils=new DButil();
					if(!TestConnect()) return;
					String UsedQuestion="";
					int m=0;
					try {
						for(int i=0;i<tableQuestion.getSelectedRowCount();i++){
							if(dbutils.deleteQuestion((String)tableQuestion.getValueAt(tableQuestion.getSelectedRows()[i], 0))==-1){
								UsedQuestion+=(String)tableQuestion.getValueAt(tableQuestion.getSelectedRows()[i], 0)+"��";
								m=1;
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(m==1){
						char[] n=new char[UsedQuestion.length()-1];
						UsedQuestion.getChars(0, UsedQuestion.length()-1, n, 0);
						JOptionPane.showMessageDialog(null, new String(n)+"�ѱ�ʹ�ã��޷�ɾ����", "�޷�ɾ��", JOptionPane.WARNING_MESSAGE);
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
		//ˢ�������
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
		//������ҶԻ���
		private void Question_Filter(){
			Question_Filter = new JDialog(frame,"����",true);
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
			boxQuestionFilterDifficulty.setModel(new DefaultComboBoxModel(new String[] {"ȫ��", "\u7B80\u5355", "\u8F83\u7B80\u5355", "\u4E2D", "\u8F83\u56F0\u96BE", "\u56F0\u96BE"}));
			boxQuestionFilterDifficulty.setSelectedIndex(0);
			boxQuestionFilterDifficulty.setBounds(81, 21, 121, 21);
			layeredPane.add(boxQuestionFilterDifficulty);
			
			boxQuestionFilterPoint=new JComboBox();
			boxQuestionFilterPoint.setModel(new DefaultComboBoxModel(new String[] {"ȫ��", "\u6570\u5B57\u949F", "\u7535\u5B50\u6D4B\u91CF\u539F\u7406", "\u5355\u7BA1\u653E\u5927", "\u6570\u7535\u57FA\u7840", "\u7535\u8DEF\u8C03\u8BD5\u539F\u7406", "\u51FD\u6570\u53D1\u751F\u5668", "\u97F3\u54CD\u653E\u5927\u5668", "MSI\u65F6\u5E8F\u903B\u8F91", "\u51FD\u6570\u53D1\u751F\u5668", "\u8FD0\u653E\u5E94\u7528"}));
			boxQuestionFilterPoint.setSelectedIndex(0);
			boxQuestionFilterPoint.setBounds(81, 54, 121, 21);
			layeredPane.add(boxQuestionFilterPoint);
			
			boxQuestionFilterType=new JComboBox();
			boxQuestionFilterType.setModel(new DefaultComboBoxModel(new String[] {"ȫ��", "\u9009\u62E9\u9898", "\u586B\u7A7A\u9898", "\u5224\u65AD\u9898", "\u7B80\u7B54\u9898"}));
			boxQuestionFilterType.setSelectedIndex(0);
			boxQuestionFilterType.setBounds(81, 87, 121, 21);
			layeredPane.add(boxQuestionFilterType);
			
			Question_Filter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Question_Filter.getRootPane().setDefaultButton(btnQuestionFilterFind);//����Ĭ�ϰ�ť
			Question_Filter.setResizable(false);
			Question_Filter.setVisible(true);
		}
		private void NoRowSelected(){//δѡ���κ���
			JOptionPane.showMessageDialog(null, "δѡ���κ��У�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		private void NotOneRowSelected(){//�༭ʱѡ�����
			JOptionPane.showMessageDialog(null, "ֻ��ѡ��һ�н��б༭��", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
	}
	//=================================================================================================
	//�������
	class OnlineCorrectPane extends JLayeredPane{
		private MyTable tableCorrect;
		private DefaultTableModel CorrectModel;
		private TableRowSorter<TableModel> CorrectSorter;
		private Object[][] Information=null;
		private Object[][] List=null;
		//���췽��
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
							if(Information[i][3].equals("���")){
								successNum+=dbutil.countGrade((String)Information[i][0],(String)Information[i][1]);
								totalNum+=1;
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(totalNum==0) 
						JOptionPane.showMessageDialog(null, "ͳ��ʧ�ܣ��޿�ͳ�ֶ���", "ͳ��ʧ��", JOptionPane.ERROR_MESSAGE);
					else if(successNum==totalNum)
						JOptionPane.showMessageDialog(null, "ͳ�ֳɹ�����"+successNum+"��", "ͳ�ֳɹ�", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "ͳ��ʧ�ܣ��ɹ�"+successNum+"����ʧ��"+(totalNum-successNum)+"�������Ժ�����", "ͳ��ʧ��", JOptionPane.ERROR_MESSAGE);
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
		//ˢ�´���
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
		//�����˶Ի���
		private void Correct_Filter(){
			final JDialog Correct_Filter = new JDialog(frame,"����",true);
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
					if(!checkBoxNo.isSelected()) filters.add(RowFilter.regexFilter("���",3));
					if(!checkBoxYes.isSelected()) filters.add(RowFilter.notFilter(RowFilter.regexFilter("���",3)));
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
			Correct_Filter.getRootPane().setDefaultButton(btnFilterFind);//����Ĭ�ϰ�ť
			Correct_Filter.setResizable(false);
			Correct_Filter.setVisible(true);
		}
		//ѡ���ľ�ģʽ�Ի���
		private void Correct_Mode(){
			final JDialog Mode=new JDialog(frame,"����ģʽ",true);
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
			Mode.getRootPane().setDefaultButton(btnOk);//����Ĭ�ϰ�ť
			Mode.setVisible(true);
			Mode.setResizable(false);
			Mode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		private void NoRowSelected(){//δѡ���κ���
			JOptionPane.showMessageDialog(null, "����ѡ��һ�ݿ���������ģ�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		private void PaperIDNotEqual(){//�Ծ��Ų�ͬ
			JOptionPane.showMessageDialog(null, "ֻ��ѡ���Ծ�����ͬ�Ŀ���������ģ�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		private void NoThisQuestion(){//�޴���
			JOptionPane.showMessageDialog(null, "�޴��⣬������ţ�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
	}
	//===============================================================================================
	//�ɼ������
	class TranscriptTablePane extends JLayeredPane{
		private MyTable tableTranscript;
		private DefaultTableModel TranscriptModel;
		private TableRowSorter<TableModel> TranscriptSorter;
		private Object[][] Transcripts=null;
		//���췽��
		public TranscriptTablePane(){
			setBounds(100, 35, 564, 347);
			
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			toolBar.setBounds(410, 9, 144, 19);
			add(toolBar);
			
			JButton btnAverage = new JButton();//"ͳ��");
			btnAverage.setContentAreaFilled(false);
			btnAverage.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnSum.png")));
		//	btnAverage.setFocusable(false);
			btnAverage.setToolTipText("ͳ��");
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
					JOptionPane.showMessageDialog(null, "ƽ����"+(SUM/tableTranscript.getSelectedRowCount()), "ƽ����", JOptionPane.PLAIN_MESSAGE);*/
					if(Transcripts==null){
						JOptionPane.showMessageDialog(null, "�޳ɼ���Ϣ��", "�޷�ͳ��", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Object[][][][] Average=new Object[20][50][50][5];//ѧ�ڣ��Ծ��༶��{�ܷ֣�������ѧ�ڣ��Ծ��༶}
			/*		for(int i=0;i<Transcripts.length;i++){
						if(Transcripts[i][0]
					}*/
			//		String[] Last=new String[3];
					int a=0,b[]=new int[20],c[][]=new int[20][50];//��С
					int x=0,y=0,z=0;//λ��
					Average[0][0][0][0]=(double) Transcripts[0][5];
					Average[0][0][0][1]=1;
					Average[0][0][0][2]=Transcripts[0][0];
					Average[0][0][0][3]=Transcripts[0][1];
					Average[0][0][0][4]=Transcripts[0][2];
					for(int i=1;i<Transcripts.length;i++){
						x=isTermExist((String)Transcripts[i][0], Average);
						if(x!=-1){
							System.out.println(Transcripts[i][0]+"����");
							y=isPaperExist((String)Transcripts[i][1], Average[x]);
							if(y!=-1){
								System.out.println(Transcripts[i][1]+"����");
								z=isClassExist((String)Transcripts[i][2], Average[x][y]);
								if(z==-1){
									System.out.println(Transcripts[i][2]+"������");
									c[x][y]++;
									Average[x][y][c[x][y]][0]=(double) Transcripts[i][5];
									Average[x][y][c[x][y]][1]=1;
									Average[x][y][c[x][y]][4]=Transcripts[i][2];
								}
								else{
									System.out.println(Transcripts[i][2]+"����");
									Average[x][y][z][0]=(double) Average[x][y][z][0]+(double) Transcripts[i][5];
									Average[x][y][z][1]=(int)Average[x][y][z][1]+1;
								}
							}
							else{
								System.out.println(Transcripts[i][1]+"������");
								b[x]++;
								Average[x][b[x]][0][0]=(double) Transcripts[i][5];
								Average[x][b[x]][0][1]=1;
								Average[x][b[x]][0][3]=Transcripts[i][1];
								Average[x][b[x]][0][4]=Transcripts[i][2];
							}
						}
						else{
							System.out.println(Transcripts[i][0]+"������");
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
							Print=Print+"�Ծ�� "+Average[d][e][0][3]+"\n";
							for(int f=0;f<=c[d][e];f++){
								Print=Print+"  "+Average[d][e][f][4]+" ƽ���֣�"+(double)((double)Average[d][e][f][0]/(int)Average[d][e][f][1])
										+"������ "+Average[d][e][f][1]+"�ˣ�\n";
							}
						}
					}
				//	System.out.println(Average[0][0][0][0]);
					JOptionPane.showMessageDialog(null, Print, "ͳ��", JOptionPane.PLAIN_MESSAGE);
				}
			});
			toolBar.add(btnAverage);
			
			JButton btnCheck = new JButton();//"���");
			btnCheck.setContentAreaFilled(false);
			btnCheck.setIcon(new ImageIcon(MainMenuFrame_teacher.class.getClassLoader().getResource("res/btnCheck.png")));
		//	btnCheck.setFocusable(false);
			btnCheck.setToolTipText("���");
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
			btnExport.setToolTipText("����ΪExcel���");
			btnExport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						DocumentExporter exp = new DocumentExporter();
						JFileChooser fileChooser=new JFileChooser();
						fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
						fileChooser.setFileFilter(new FileNameExtensionFilter("Excel���(*.xls)","xls"));
						fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
						fileChooser.setApproveButtonText("����");
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
		//����b[][][][]��aԪ�ص�λ�ã������ڷ���-1
		private int isTermExist(String a,Object b[][][][]){
			for(int i=0;i<b.length;i++){
				if(b[i]==null) break;
				if(a.equals(b[i][0][0][2])) return i;
			}
			return -1;
		}
		//����b[][][]��aԪ�ص�λ�ã������ڷ���-1
		private int isPaperExist(String a,Object b[][][]){
			for(int i=0;i<b.length;i++){
				if(b[i]==null) break;
				if(a.equals(b[i][0][3])) return i;
			}
			return -1;
		}
		//����b[][]��aԪ�ص�λ�ã������ڷ���-1
		private int isClassExist(String a,Object b[][]){
			for(int i=0;i<b.length;i++){
				if(b[i]==null) break;
				if(a.equals(b[i][4])) return i;
			}
			return -1;
		}
		//ˢ�³ɼ���
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
		//�ɼ����ҶԻ���
		private void Transcripts_Filter(){
			final JDialog Transcripts_Filter = new JDialog(frame,"����",true);
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
			comboBoxTerm.setModel(new DefaultComboBoxModel(new String[] {"����", "�＾"}));
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
					if(checkBoxTerm.isSelected()) filters.add(RowFilter.regexFilter(spinnerYear.getValue()+"��"+comboBoxTerm.getSelectedItem(),0));
					filters.add(RowFilter.regexFilter(txtIDPaperFilter.getText(),1));
					filters.add(RowFilter.regexFilter(txtClassFilter.getText(),2));
					filters.add(RowFilter.regexFilter(txtStudentNum.getText(),3));
					if(!txtMin.getText().isEmpty()){
						try {
							filters.add(RowFilter.numberFilter(ComparisonType.AFTER, Double.parseDouble(txtMin.getText()), 5));
							} 
						catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "����������������", "������������", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					if(!txtMax.getText().isEmpty()){
						try {
							filters.add(RowFilter.numberFilter(ComparisonType.BEFORE, Double.parseDouble(txtMax.getText()), 5));
							} 
						catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "����������������", "������������", JOptionPane.ERROR_MESSAGE);
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
			Transcripts_Filter.getRootPane().setDefaultButton(btnFilterFind);//����Ĭ�ϰ�ť
			Transcripts_Filter.setResizable(false);
			Transcripts_Filter.setVisible(true);
		}
		private void NoRowSelected(){//δѡ���κ���
			JOptionPane.showMessageDialog(null, "δѡ���κ��У�", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		private void NotOneRowSelected(){//���ʱѡ�����
			JOptionPane.showMessageDialog(null, "ֻ��ѡ��һ���Ծ���", "�޷�ִ��", JOptionPane.ERROR_MESSAGE);
		}
		//���ǰ�������֤
		private void CheckPassword(){
			final JDialog checkPwd=new JDialog(frame,"�����֤",true);
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
							JOptionPane.showMessageDialog(null, "�����ȷ��", "��֤ʧ��", JOptionPane.ERROR_MESSAGE);
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
			checkPwd.getRootPane().setDefaultButton(btnOk);//����Ĭ�ϰ�ť
			checkPwd.setResizable(false);
			checkPwd.setVisible(true);
			
		}
	}
}