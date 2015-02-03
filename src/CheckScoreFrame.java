import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class CheckScoreFrame extends JFrame{
	private int QuestionNumber=1;
	private int type_all=0;
	private String[][] QuestionID=null;
	private int Progress=1;
	private String StudentID;
	private String Paper;
	private JTextPane txtQuestion;
	private JTextPane txtAnswer;
	private JTextPane txtStudentAns;
	private JLabel lblPicture;
	private JTextField txtTotal;
	private JTextField txtScore;
	private JLabel lblProgress;
	private JButton btnLast = new JButton("\u4E0A\u4E00\u9898");
	private JButton btnNext = new JButton("\u4E0B\u4E00\u9898");
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//使用系统UI风格
		} catch (Exception e) {
			e.printStackTrace();
		}
		OLCorrectFrame a=new OLCorrectFrame(null,null,null,1,0);
	}*/
	public CheckScoreFrame(String Stu_ID,String Paper_ID){
		setTitle("\u67E5\u5206");
		StudentID = Stu_ID;
		Paper = Paper_ID;
		DButil dbutil=new DButil();
		try {
			QuestionID = dbutil.manualMark(Paper_ID);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		final JButton btnRevise = new JButton("\u4FEE\u6539\u5206\u6570");
		final JButton btnOk = new JButton("\u786E\u8BA4\u4FEE\u6539");
		
		setSize(835,560);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-835)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-560)/2);
		BackgroundImagePanel panel = new BackgroundImagePanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane QuestionText = new JScrollPane();
		QuestionText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		QuestionText.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BD5\u9898\u5185\u5BB9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		QuestionText.setBounds(12, 10, 391, 249);
		panel.add(QuestionText);
		
		txtQuestion = new JTextPane();
		txtQuestion.setBackground(new Color(255, 255, 245));
		txtQuestion.setEditable(false);
		txtQuestion.setBorder(new LineBorder(Color.GRAY));
		QuestionText.setViewportView(txtQuestion);
		
		JScrollPane Answer = new JScrollPane();
		Answer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Answer.setBounds(12, 269, 391, 249);
		panel.add(Answer);
		Answer.setBorder(new TitledBorder(null, "\u53C2\u8003\u7B54\u6848", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		txtAnswer = new JTextPane();
		txtAnswer.setBackground(new Color(255, 255, 245));
		txtAnswer.setEditable(false);
		txtAnswer.setBorder(new LineBorder(Color.GRAY));
		Answer.setViewportView(txtAnswer);
		
		JScrollPane StudentAns = new JScrollPane();
		StudentAns.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		StudentAns.setBorder(new TitledBorder(null, "\u8003\u751F\u7B54\u6848", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		StudentAns.setBounds(407, 229, 409, 289);
		panel.add(StudentAns);
		
		txtStudentAns = new JTextPane();
		txtStudentAns.setBackground(new Color(255, 255, 245));
		txtStudentAns.setEditable(false);
		txtStudentAns.setBorder(new LineBorder(Color.GRAY));
		StudentAns.setViewportView(txtStudentAns);
		
		lblPicture = new JLabel("No Picture");
		lblPicture.setBorder(new LineBorder(Color.GRAY));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBounds(413, 27, 252, 192);
		panel.add(lblPicture);
		
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(674, 27, 132, 167);
		panel.add(layeredPane);
		
		JLabel lblTotal = new JLabel("\u5206  \u503C");
		lblTotal.setBounds(10, 13, 36, 15);
		layeredPane.add(lblTotal);
		
		JLabel lblScore = new JLabel("\u5F97  \u5206");
		lblScore.setBounds(10, 44, 36, 15);
		layeredPane.add(lblScore);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(56, 10, 66, 21);
		layeredPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtScore = new JTextField();
		txtScore.setEditable(false);
		txtScore.setBounds(56, 41, 66, 21);
		txtScore.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e) {
				try {
					if(Double.parseDouble(txtScore.getText())<=Double.parseDouble(txtTotal.getText())
							&&Double.parseDouble(txtScore.getText())>=0) btnOk.setEnabled(true);
					else btnOk.setEnabled(false);
				} catch (NumberFormatException e1) {
					txtScore.requestFocus();
					btnOk.setEnabled(false);
				}
			}
		});
		layeredPane.add(txtScore);
		txtScore.setColumns(10);
		
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!MainMenuFrame_teacher.TestConnect()) return;
				if(QuestionNumber>1) QuestionNumber--;
				else if(type_all==1&&QuestionID[0].length!=0){
					type_all=0;
					QuestionNumber=QuestionID[type_all].length;
				}
				Progress--;
				Refresh();
			}
		});
		btnLast.setBounds(9, 72, 113, 23);
		btnLast.setContentAreaFilled(false);
		layeredPane.add(btnLast);
		
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!MainMenuFrame_teacher.TestConnect()) return;
				if(QuestionNumber<QuestionID[type_all].length) QuestionNumber++;
				else if(type_all==0&&QuestionID[1].length!=0){
					type_all=1;
					QuestionNumber=1;
				}
				Progress++;
				Refresh();
			}
		});
		btnNext.setBounds(9, 105, 113, 23);
		btnNext.setContentAreaFilled(false);
		layeredPane.add(btnNext);
		
		JButton btnBack = new JButton("\u9000\u51FA");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DButil dbutil=new DButil();
				try {
					dbutil.countGrade(StudentID, Paper);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				MainMenuFrame_teacher.RefreshState=2;
				MainMenuFrame_teacher.frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(674, 197, 132, 23);
		btnBack.setContentAreaFilled(false);
		panel.add(btnBack);
		
		lblProgress = new JLabel("Progress");
		lblProgress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProgress.setBounds(762, 10, 44, 15);
		panel.add(lblProgress);
		
		JLabel lblStudentid = new JLabel("学号:"+StudentID);
		lblStudentid.setBounds(671, 10, 113, 15);
		panel.add(lblStudentid);
		
		btnRevise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtScore.setEditable(true);
				btnLast.setEnabled(false);
				btnNext.setEnabled(false);
				layeredPane.add(btnOk);
				layeredPane.remove(btnRevise);
			}
		});
		btnRevise.setBounds(9, 138, 113, 23);
		btnRevise.setContentAreaFilled(false);
		layeredPane.add(btnRevise);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!MainMenuFrame_teacher.TestConnect()) return;
				DButil dbutil=new DButil();
				try {
					dbutil.recordPoint(StudentID, Paper, QuestionID[type_all][QuestionNumber-1], Double.parseDouble(txtScore.getText()), Double.parseDouble(txtTotal.getText()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				txtScore.setEditable(false);
				if(Progress==(QuestionID[0].length+QuestionID[1].length));
				else btnNext.setEnabled(true);
				if(Progress==0);
				else btnLast.setEnabled(true);
				layeredPane.add(btnRevise);
				layeredPane.remove(btnOk);
			}
		});
		btnOk.setBounds(9, 138, 113, 23);
		btnOk.setContentAreaFilled(false);
		
		if(QuestionID[0].length==0) type_all=1;
		Refresh();
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				DButil dbutil=new DButil();
				try {
					dbutil.countGrade(StudentID, Paper);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				MainMenuFrame_teacher.RefreshState=2;
				MainMenuFrame_teacher.frame.setVisible(true);
			}
		});
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(btnNext);
		setVisible(true);
		setResizable(false);
	}
	private void Refresh(){
		DButil dbutil=new DButil();
		Object[] AnsPane=null;
		if((QuestionID[0].length+QuestionID[1].length)==0) {
			JOptionPane.showMessageDialog(null, "无可查试题（无填空题，简答题）！", "无法查卷", JOptionPane.INFORMATION_MESSAGE);
			MainMenuFrame_teacher.frame.setVisible(true);
			dispose();
		}
		try {
			AnsPane=dbutil.showStuAnswer(StudentID, Paper, QuestionID[type_all][QuestionNumber-1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		txtQuestion.setText((String) AnsPane[0]);
		txtAnswer.setText((String) AnsPane[1]);
		txtStudentAns.setText((String) AnsPane[2]);
		txtTotal.setText((double) AnsPane[3]+"");
		txtScore.setText((double) AnsPane[4]+"");
		IOImage ioimage=new IOImage();
		byte[] PicBytes=null;
		try {
			PicBytes = ioimage.readBolb(QuestionID[type_all][QuestionNumber-1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(PicBytes!=null)
		{
			PictureUtils PicUtils=new PictureUtils();
			lblPicture.setIcon(PicUtils.PreviewPicture(PicUtils.BytesToIcon(PicBytes),
					lblPicture.getWidth(),lblPicture.getHeight()));
			lblPicture.setText(null);
		}
		else {
			lblPicture.setIcon(null);
			lblPicture.setText("No Picture");
		}
		lblProgress.setText(Progress+"/"+(QuestionID[0].length+QuestionID[1].length));
		if(Progress==1) btnLast.setEnabled(false);
		else btnLast.setEnabled(true);
		if(Progress==(QuestionID[0].length+QuestionID[1].length)) btnNext.setEnabled(false);
		else btnNext.setEnabled(true);
	}
}
