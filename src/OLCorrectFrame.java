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


public class OLCorrectFrame extends JFrame{
	private JButton btnLast = new JButton("\u4E0A\u4E00\u4E2A");
	private JButton btnNext = new JButton("\u786E\u8BA4");
	private String Question_ID;
	private boolean Finished;
	private int Total=0;
	private int Progress=1;
	private JTextField txtTotal;
	private JTextField txtScore;
	private String[] ID;
	private String[] StudentID;
	private String Paper;
	private String[][] Question;
	private int Type;
	private int QuestionNumber;
	private int StudentNumber=0;
	private int type_all=0;
	private JTextPane txtQuestion;
	private JTextPane txtAnswer;
	private JTextPane txtStudentAns;
	private JLabel lblPicture;
	private JLabel lblProgress;
	private JLabel lblStudentid;
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
	public OLCorrectFrame(String[] id,String[] Stu_ID,String Paper_ID,String[][] Ques,int Mode,int Ques_Number){
		super("试卷评改");
		ID=id;
		StudentID=Stu_ID;
		Paper=Paper_ID;
		Question=Ques;
		Type=Mode;
		QuestionNumber=Ques_Number;
		
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
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(674, 52, 132, 142);
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
		txtScore.setBounds(56, 41, 66, 21);
		txtScore.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e) {
				try {
					if(Double.parseDouble(txtScore.getText())<=Double.parseDouble(txtTotal.getText())
							&&Double.parseDouble(txtScore.getText())>=0) btnNext.setEnabled(true);
					else btnNext.setEnabled(false);
				} catch (NumberFormatException e1) {
					txtScore.requestFocus();
					btnNext.setEnabled(false);
				}
			}
		});
		layeredPane.add(txtScore);
		txtScore.setColumns(10);
		
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!MainMenuFrame_teacher.TestConnect()) return;
				switch(Type){
				case -1:
					if(QuestionNumber>1) QuestionNumber--;
					else if(type_all==1&&Question[0].length!=0){
						type_all=0;
						QuestionNumber=Question[type_all].length;
					}
					else if(StudentNumber>0){
						StudentNumber--;
						type_all=1;
						QuestionNumber=Question[type_all].length;
					}
					if(QuestionNumber==1&&type_all==0&&StudentNumber==0) btnLast.setEnabled(false);
					if(QuestionNumber==1&&type_all==1&&StudentNumber==0&&Question[0].length==0) btnLast.setEnabled(false);
					break;
				case 0:
				case 1:
					if(StudentNumber>0) StudentNumber--;
					if(StudentNumber==0) btnLast.setEnabled(false);
					break;
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
				DButil dbutil=new DButil();
				try {
					dbutil.recordPoint(StudentID[StudentNumber], Paper, Question_ID, Double.parseDouble(txtScore.getText()), Double.parseDouble(txtTotal.getText()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			//	System.out.println(StudentID[StudentNumber]);
			//	System.out.println(Paper);
			//	System.out.println(Question_ID);
			//	System.out.println(Double.parseDouble(txtScore.getText()));
				
				switch(Type){
				case -1:
					if(QuestionNumber<Question[type_all].length) QuestionNumber++;
					else if(type_all==0&&Question[1].length!=0){
						type_all=1;
						QuestionNumber=1;
					}
					else if(StudentNumber<StudentID.length-1){
						StudentNumber++;
						type_all=0;
						QuestionNumber=1;
					}
					else{
						Finished=true;
						JOptionPane.showMessageDialog(null, "批改完成！", "批改完成", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					break;
				case 0:
				case 1:
					if(StudentNumber<StudentID.length-1) StudentNumber++;
					else{
						Finished=true;
						JOptionPane.showMessageDialog(null, "批改完成！", "批改完成", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					break;
				}
				Progress++;
				Refresh();
		//		btnNext.r
			}
		});
		btnNext.setBounds(9, 105, 113, 23);
		btnNext.setContentAreaFilled(false);
		layeredPane.add(btnNext);
		
		JButton btnBack = new JButton("\u9000\u51FA");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Finished==false){
					if(JOptionPane.showConfirmDialog(null, "批改未完成，确认退出？已批改的部分已记录", "确认退出", JOptionPane.YES_NO_OPTION)==0){
						MainMenuFrame_teacher.RefreshState=1;
						MainMenuFrame_teacher.frame.setVisible(true);
						dispose();
					}
				}
				else{
					MainMenuFrame_teacher.RefreshState=1;
					MainMenuFrame_teacher.frame.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(674, 197, 132, 23);
		btnBack.setContentAreaFilled(false);
		panel.add(btnBack);
		
		lblProgress = new JLabel("Progress");
		lblProgress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProgress.setBounds(762, 27, 44, 15);
		panel.add(lblProgress);
		
		lblStudentid = new JLabel("StudentID");
		lblStudentid.setBounds(671, 27, 113, 15);
		panel.add(lblStudentid);
		
		if(Question[0].length==0) type_all=1;
		Refresh();
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				if(Finished==false){
					if(JOptionPane.showConfirmDialog(null, "批改未完成，确认退出？已批改的部分已记录", "确认退出", JOptionPane.YES_NO_OPTION)==0){
						MainMenuFrame_teacher.RefreshState=1;
						MainMenuFrame_teacher.frame.setVisible(true);
						dispose();
					}
				}
				else{
					MainMenuFrame_teacher.RefreshState=1;
					MainMenuFrame_teacher.frame.setVisible(true);
					dispose();
				}
			}
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getRootPane().setDefaultButton(btnNext);
		setVisible(true);
		setResizable(false);
	}
	private void Refresh(){
		DButil dbutil=new DButil();
		Object[] AnsPane=null;
		if(Type==-1){
			Total=StudentID.length*(Question[0].length+Question[1].length);
			if(Total==0){
				JOptionPane.showMessageDialog(null, "该试卷没有可批改的试题！（无填空题，简答题）", "批改完成", JOptionPane.INFORMATION_MESSAGE);
				MainMenuFrame_teacher.frame.setVisible(true);
				dispose();
			}
			Question_ID=Question[type_all][QuestionNumber-1];
		}
		else{
			Question_ID=Question[Type][QuestionNumber-1];
			Total=StudentID.length;
		}
		try {
			AnsPane=dbutil.showStuAnswer(StudentID[StudentNumber], Paper, Question_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		txtQuestion.setText((String) AnsPane[0]);
		txtAnswer.setText((String) AnsPane[1]);
		txtStudentAns.setText((String) AnsPane[2]);
		txtTotal.setText((double) AnsPane[3]+"");
		if((double) AnsPane[4]!=-1){
			txtScore.setText((double) AnsPane[4]+"");
			btnNext.setEnabled(true);
		}
		else{
			txtScore.setText(null);
			btnNext.setEnabled(false);
		}
		IOImage ioimage=new IOImage();
		byte[] PicBytes=null;
		try {
			PicBytes = ioimage.readBolb(Question_ID);
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
		lblStudentid.setText("学生编号："+ID[StudentNumber]);
		lblProgress.setText(Progress+"/"+Total);
		if(Progress==1) btnLast.setEnabled(false);
		else btnLast.setEnabled(true);
	}
}
