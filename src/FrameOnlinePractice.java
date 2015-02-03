import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.util.Random;


public class FrameOnlinePractice  {
	JFrame frame;
	JLabel Xnumber;
	JLabel Xanswer;
	String xanswer="";
	JLabel Xanswer_1;
	public static  String xanswer_1;
	public static JTextPane Xtext;
	public static JTextPane Ptext;
	JLabel Pnumber;
	JLabel Panswer;
	String panswer="";
	JLabel Panswer_1;
	public static String panswer_1;
	public static JTextPane Jtext;
	JTextArea Janswer;
	JTextPane Janswer_1;
	public static String janswer_1;
	JLabel Jnumber;
    JLayeredPane Xpanel;
    JLayeredPane Ppanel;
    JLayeredPane Tpanel;
    JLayeredPane Jpanel;
	ButtonGroup Pgroup;
	ButtonGroup Xgroup;
    JPanelWithPic contentPane;
 	public static JTextPane Ttext;
 	JTextField Tanswer;
 	JLabel Tanswer_1;
 	JLabel Tnumber;
 	public static String tanswer_1;
	PracticeSelect practice=new PracticeSelect();
	int c;
	int d;
	int t;
	int flag;
	public static int number=0;
	public static String[] Question=new String[255];
	IOImage ioimage=new IOImage();
	PictureUtils picture=new PictureUtils();
	Random random=new Random();
	DButil dbutil=new DButil();

	/**
	 * Create the frame.
	 * @wbp.parser.entryPoint
	 */
	public  void OnlinePracticeFrame(String sName,int chapters,int difficulty,int types) {
		c=chapters;
		d=difficulty;
		t=types;
		//����������ϰ������
		frame = new JFrame();
		frame.setTitle("������ϰ");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-960)/2,//����������ϰ������λ������Ļ�м�
				(Toolkit.getDefaultToolkit().getScreenSize().height-720)/2);
		frame.setSize(960, 720);
		frame.setResizable(false);//�������С���ɵ���
		frame.setVisible(false);
		//��Ӵ��ڼ����������û��رմ��ڰ�ťʱ������ʾ
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int confirm = JOptionPane.showConfirmDialog(null,"�رմ��ڽ�������ϰ","��ʾ",JOptionPane.OK_CANCEL_OPTION);//������ʾ��
				if(confirm==0){
					frame.dispose();//�ر�������
					MainMenuFrame_student.frame.setVisible(true);//����ѧ���û�����
					for(int i=0;i<Question.length;i++){//����ϰ����ر�����ʼ��
						Question[i]=null;
					}
					number=0;
				}
				else{}
			}
		});
		
		contentPane = new JPanelWithPic();//�ڽ����������岢����
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
        //===================================================================================================================
		//ѡ���ⲿ�ֽ��洴��
		Xgroup = new ButtonGroup();
		Xpanel = new JLayeredPane();   
		Xpanel.setBounds(89, 85, 741, 540);
		Xpanel.setBorder(null);
		Xpanel.setLayout(null);
		//�����������ʾѡ����
		Xtext = new JTextPane();
		Xtext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(Xtext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(35, 64, 702, 280);
		Xpanel.add(scrollPane);
		//������ǩ��ʶ���
		Xnumber = new JLabel("��"+String.valueOf(number+1)+"��"); //��ʾѡ�������
		Xnumber.setFont(new Font("Monospaced", Font.BOLD, 15));
		Xnumber.setBounds(35, 24, 72, 30);
		Xpanel.add(Xnumber);
		JCheckBox chckbxA = new JCheckBox("A");//ѡ��A����
		chckbxA.setFont(new Font("Monospaced", Font.BOLD, 15));
		chckbxA.setBounds(101, 396, 70, 23);
		Xpanel.add(chckbxA);
		Xgroup.add(chckbxA);
		chckbxA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="A";
				Xanswer.setText("��ѡ��Ϊ��"+xanswer);
			}
		});
		JCheckBox chckbxB = new JCheckBox("B");//ѡ��B����
		chckbxB.setFont(new Font("Monospaced", Font.BOLD, 15));
		chckbxB.setBounds(367, 396, 70, 23);
		Xpanel.add(chckbxB);
		Xgroup.add(chckbxB);
		chckbxB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="B";
				Xanswer.setText("��ѡ��Ϊ��"+xanswer);
			}
		});
		JCheckBox chckbxC = new JCheckBox("C");//ѡ��C����
		chckbxC.setFont(new Font("Monospaced", Font.BOLD, 15));
		chckbxC.setBounds(101, 459, 70, 23);
		Xpanel.add(chckbxC);
		Xgroup.add(chckbxC);
		chckbxC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="C";
				Xanswer.setText("��ѡ��Ϊ��"+xanswer);
			}
		});
		JCheckBox chckbxD = new JCheckBox("D");//ѡ��D����
		chckbxD.setFont(new Font("Monospaced", Font.BOLD, 15));
		chckbxD.setBounds(367, 459, 70, 23);
		Xpanel.add(chckbxD);
		Xgroup.add(chckbxD);
		chckbxD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="D";
				Xanswer.setText("��ѡ��Ϊ��"+xanswer);
			}
		});
		//������ǩ��ʶ��ѡ�Ĵ�
		Xanswer = new JLabel("��ѡ��Ϊ��"+xanswer);
		Xanswer.setForeground(Color.RED);
		Xanswer.setFont(new Font("Monospaced", Font.BOLD, 15));
		Xanswer.setBounds(101, 500, 151, 30);
		Xpanel.add(Xanswer);
		//��ӱ�ǩ��ʶ������
		JLabel label_8 = new JLabel("ѡ�");
		label_8.setFont(new Font("Monospaced", Font.BOLD, 15));
		label_8.setBounds(35, 366, 63, 23);
		Xpanel.add(label_8);
		
		JButton button_1 = new JButton("�ο���");
		button_1.setBounds(367, 500, 93, 30);
		Xpanel.add(button_1);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Xanswer_1.setText(xanswer_1);
			}
		});
		
		Xanswer_1 = new JLabel("");
		Xanswer_1.setForeground(Color.RED);
		Xanswer_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		Xanswer_1 .setBounds(497, 500, 54, 30);
		Xpanel.add(Xanswer_1 );
		//=====================================================================================================================
		//�ж��ⲿ�ֽ��洴��
		Ppanel = new JLayeredPane();
		Ppanel.setLayout(null);
		Ppanel.setBounds(89, 85, 741, 540);
		Ptext = new JTextPane();
		Ptext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		JScrollPane scrollPane_1 = new JScrollPane(Ptext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(35, 64, 702, 280);
		Ppanel.add(scrollPane_1);
		//��ӱ�ǩ��ʶ���
		Pnumber = new JLabel("��"+(number+1)+"��");
		Pnumber.setFont(new Font("Monospaced", Font.BOLD, 15));
		Pnumber.setBounds(35, 24, 72, 30);
		Ppanel.add(Pnumber);
		//��ӵ�ѡ����Ϊ�ж���ѡ��
		Pgroup = new ButtonGroup();
		JRadioButton Pyes = new JRadioButton("��");//�ж���ѡ��
		Pyes.setFont(new Font("Monospaced", Font.BOLD, 15));
		Pyes.setBounds(136, 397, 121, 23);
		Pgroup.add(Pyes);
		Ppanel.add(Pyes);
		Pyes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panswer="��";
				Panswer.setText("��ѡ�𰸣�"+panswer);
				}
		});
		JRadioButton Pwrong = new JRadioButton("��");//�ж���ѡ��
		Pwrong.setFont(new Font("Monospaced", Font.BOLD, 15));
		Pwrong.setBounds(136, 446, 121, 23);
		Pgroup.add(Pwrong);
		Ppanel.add(Pwrong);
		Pwrong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panswer="��";
				Panswer.setText("��ѡ�𰸣�"+panswer);
				}
		});
		//��ӱ�ǩ��ʶ��ѡ��
		Panswer = new JLabel("��ѡ�𰸣�"+panswer);//��ʾ��ѡ��
		Panswer.setForeground(Color.RED);
		Panswer.setFont(new Font("Monospaced", Font.BOLD, 15));
		Panswer.setBounds(61, 496, 114, 30);
		Ppanel.add(Panswer);
		//��ӱ�ǩ��ʶ������
		JLabel label_9 = new JLabel("ѡ�");
		label_9.setFont(new Font("Monospaced", Font.BOLD, 15));
		label_9.setBounds(61, 365, 67, 23);
		Ppanel.add(label_9);
		
		JButton button_2 = new JButton("�ο���");
		button_2.setBounds(370, 496, 93, 30);
		Ppanel.add(button_2);
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(panswer_1);
				double pans= Double.parseDouble(panswer_1);
				if(pans==0){
				Panswer_1.setText("��");
				}
				else if(pans==1){ 
					Panswer_1.setText("��");
				}
			}
		});
		
		Panswer_1 = new JLabel("");
		Panswer_1.setForeground(Color.RED);
		Panswer_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		Panswer_1.setBounds(507, 496, 54, 30);
		Ppanel.add(Panswer_1);
		//======================================================================================================================
		//�������洴��
		Tpanel = new JLayeredPane();
		Tpanel.setLayout(null);
		Tpanel.setBounds(89, 85, 727, 529);
		Ttext = new JTextPane();
		Ttext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		JScrollPane scrollPane_5 = new JScrollPane(Ttext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_5.setBounds(35, 64, 682, 266);
		Tpanel.add(scrollPane_5);
		//��ӱ�ǩ��ʶ���
		Tanswer = new JTextField("");
		Tanswer.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Tanswer.setBounds(99, 363, 482, 37);
		Tpanel.add(Tanswer);
		Tanswer.setColumns(10);
		
		Tnumber = new JLabel("��"+(number+1)+"��");
		Tnumber.setFont(new Font("Monospaced", Font.BOLD, 15));
		Tnumber.setBounds(35, 24, 72, 30);
		Tpanel.add(Tnumber);
		
		JLabel label = new JLabel("���");
		label.setFont(new Font("Monospaced", Font.BOLD, 15));
		label.setBounds(35, 366, 54, 30);
		Tpanel.add(label);
		
		JButton button_4 = new JButton("�ο���");
		button_4.setBounds(138, 455, 93, 30);
		Tpanel.add(button_4);
		button_4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Tanswer_1.setText(tanswer_1);
			}
		});
		Tanswer_1 = new JLabel("");
		Tanswer_1.setForeground(Color.RED);
		Tanswer_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Tanswer_1.setBounds(272, 455, 419, 30);
		Tpanel.add(Tanswer_1);
		//======================================================================================================================
		//�������洴��
		Jpanel = new JLayeredPane();
		Jpanel.setLayout(null);
		Jpanel.setBounds(89, 85, 739, 583);
		//��ӿ����������ı�
		Jtext = new JTextPane();
		Jtext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		JScrollPane scrollPane_2 = new JScrollPane(Jtext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_2.setBounds(73, 43, 663, 185);
		Jpanel.add(scrollPane_2);
		//����ı����������
		Janswer = new JTextArea();
		Janswer.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Janswer.setEditable(true);
		JScrollPane scrollPane_3 = new JScrollPane(Janswer,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_3.setBounds(88, 276, 648, 152);
		Jpanel.add(scrollPane_3);
		//��ӱ�ǩ��ʶ�����
		JLabel label_1 = new JLabel("���");
		label_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		label_1.setBounds(56, 235, 54, 30);
		Jpanel.add(label_1);
		//��ӱ�ǩ��ʶ��������
		Jnumber = new JLabel("��"+String.valueOf(number+1)+"��");
		Jnumber.setFont(new Font("Monospaced", Font.BOLD, 15));
		Jnumber.setBounds(73, 10, 54, 23);
		Jpanel.add(Jnumber);
		
		 Janswer_1 = new JTextPane();
	 		Janswer_1.setEditable(false);
	 		Janswer_1.setFont(new Font("Monospaced",Font.BOLD,15));
	 		Janswer_1.setForeground(Color.RED);
	 		JScrollPane scrollPane_4 = new JScrollPane(Janswer_1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	 		scrollPane_4.setBounds(120, 466, 596, 102);
	 		Jpanel.add(scrollPane_4);
		
		JButton button_3 = new JButton("�ο���");
		button_3.setBounds(17, 438, 93, 30);
		Jpanel.add(button_3);
		button_3.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent e){
			    Janswer_1.setText(janswer_1);
			}
		});
        //===============================================================================================================================
		//��ʼ��������ʾ
		if(t==0){                     //�ж����������Ƿ���ѡ����
			contentPane.add(Xpanel);  //����ѡ�����Ӧ���
			Xtext.setEditable(true);
			try {
				practice.Xpractice(c, d, t);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Xtext.setEditable(false);
		}
		else if(t==1){            //�ж����������Ƿ�Ϊ�����
			contentPane.add(Tpanel);//����������Ӧ���
			Ttext.setEditable(true);
			try {
				practice.Tpractice(c, d, t);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Ttext.setEditable(false);
		}
		else if(t==2){             //�ж����������Ƿ�Ϊ�ж���
			contentPane.add(Ppanel);//�����ж����Ӧ���
			Ptext.setEditable(true);
			try {
				practice.Ppractice(c, d, t);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Ptext.setEditable(false);
		}
		else if(t==3){            //�ж����������Ƿ�Ϊ�����
			contentPane.add(Jpanel);//���ؼ�����Ӧ���
			Jtext.setEditable(true);
			try {
				practice.Jpractice(c, d, t);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Jtext.setEditable(false);
		}
		else{                     //��������Ϊ���
			flag=random.nextInt(4);//ͨ�������ȷ�����غ������
			if(flag==0){
				contentPane.add(Xpanel);
				Xtext.setEditable(true);
				try {
					practice.Xpractice(c, d, t);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Xtext.setEditable(false);
			}
			else if(flag==1){
				contentPane.add(Tpanel);
				Ttext.setEditable(true);
				try {
					practice.Tpractice(c, d, t);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Ttext.setEditable(false);
			}
			else if(flag==2){
				contentPane.add(Ppanel);
				Ptext.setEditable(true);
				try {
					practice.Ppractice(c, d, t);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Ptext.setEditable(false);
			}
			else {
				contentPane.add(Jpanel);
				Jtext.setEditable(true);
				try {
					practice.Jpractice(c, d, t);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Jtext.setEditable(false);
			}
		}
		//��ӱ�ǩ��ʾ��������
		JLabel Jname = new JLabel("��������:"+sName);//��ʾ�����������Ը�ʽ��������
		Jname.setForeground(Color.BLUE);
		Jname.setFont(new Font("Monospaced", Font.BOLD, 16));
		Jname.setBounds(178, 32, 175, 29);
		contentPane.add(Jname);                
		//��Ӱ�ťʵ�ֽ�����ϰ
		JButton button = new JButton("������ϰ");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int confirm = JOptionPane.showConfirmDialog(null,"��ȷ��������ϰ��","������ϰ",JOptionPane.OK_CANCEL_OPTION);//�����ʾ�Ի���
				if(confirm==0){
					frame.dispose();                        //�ر�������ϰ������
					MainMenuFrame_student.frame.setVisible(true);//����ѧ���û�������
					for(int i=0;i<Question.length;i++){     //ѧ���������Ӧ������ʼ��
						Question[i]=null;
					}
					number=0;
				}
			}
		});
		button.setBounds(786, 29, 108, 35);
		contentPane.add(button);
		
		JButton button_7 = new JButton("ʹ�ü�����");//��Ӽ�������ť��������Ӧ����
		button_7.setBounds(500, 29, 107, 35);
		contentPane.add(button_7);
		button_7.addActionListener(new ActionListener(){//��Ӱ�ť����������������
			public void actionPerformed(ActionEvent e){
				CaculatorFrame cacu =new CaculatorFrame();
				cacu.Caculator();
			}
		});
	//===========================================================================================================================	
		JButton button_5 = new JButton("��һ��");//��������л���ť�������ò���
		button_5.setBounds(831, 532, 93, 30);
		contentPane.add(button_5);
		button_5.addActionListener(new ActionListener(){//��Ӽ�������ʵ������һ����л�
			public void actionPerformed(ActionEvent e){
				
				if(t==0){                              //�жϵ�ǰ��������Ƿ�Ϊѡ�������
					Xgroup.clearSelection();           //��ʼ��ѡ����������
					xanswer="";
					Xanswer.setText("��ѡ��Ϊ��"+xanswer);
					Xanswer_1.setText("");
					if(number>0){                       //�жϵ�ǰ����Ƿ����һ
						number=number-1;
						Xnumber.setText("��"+ String.valueOf(number+1) + "��");//�������
						try{                           //�����ݿ��ȡ��Ŀ����ʾ
						 Object[] xquestion = dbutil.selectById(Question[number]);
							String xquestion1=(String) xquestion[0];
						    String xanswer=(String) xquestion[4];
						    xanswer_1=xanswer;
						byte[] buffer = ioimage.readBolb(Question[number]);//��ȡ����ͼƬ
						if(buffer==null){                      //�ж�ͼƬ�Ƿ����
						Xtext.setText(xquestion1);
						}
						else{
						ImageIcon image=picture.BytesToIcon(buffer);
						ImageIcon image1;
						if(image.getIconHeight()>200||image.getIconWidth()>270){//����ͼƬ��С�ͽ�����ʾ
							image1=picture.PreviewPicture(image, 200, 270);
						}
						else{
							image1=image;
						}
						Xtext.setText(xquestion1+"\n");             //������ɺ��в���ͼƬ
						Xtext.insertIcon(image1);}
					}catch (Exception e4){
						e4.printStackTrace();
					}
						}
					else{
						JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","������Ϣ",JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(t==1){      //�ж�����Ƿ���������
					Tanswer.setText("");//��ʼ�������������
					Tanswer_1.setText("");
					if(number>0){      //�������
					 number=number-1;
					 Tnumber.setText("��"+String.valueOf(number+1)+"��");
					try{
						Object[] tquestion=dbutil.selectById(Question[number]);
						String tquestion1=(String) tquestion[0];
						String tanswer=(String) tquestion[4];
					    FrameOnlinePractice.tanswer_1=tanswer;
						byte[] buffer=ioimage.readBolb(Question[number]);
						if(buffer==null){
							FrameOnlinePractice.Ttext.setText(tquestion1);
						}
						else{
						ImageIcon image=picture.BytesToIcon(buffer);
						ImageIcon image1;
						if(image.getIconHeight()>200||image.getIconWidth()>270){
							image1=picture.PreviewPicture(image, 200, 270);
						}
						else{
							image1=image;
						}
						FrameOnlinePractice.Ttext.setText(tquestion1+"\n");
						FrameOnlinePractice.Ttext.insertIcon(image1);}
					}catch (Exception e6){
						e6.printStackTrace();
					}
					}
					else{
						JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","������Ϣ",JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(t==2){
					Pgroup.clearSelection();
					panswer="";
					Panswer.setText("��ѡ�𰸣�"+panswer);
					Panswer_1.setText("");
					if(number>0){
						number=number-1;
						Pnumber.setText("��"+ String.valueOf(number+1) + "��");
						try{
							Object[] pquestion=dbutil.selectById(Question[number]);
							String pquestion1=(String) pquestion[0];
							String panswer=(String) pquestion[4];
							FrameOnlinePractice.panswer_1=panswer;
							byte[] buffer=ioimage.readBolb(Question[number]);
							if(buffer==null){
								FrameOnlinePractice.Ptext.setText(pquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							FrameOnlinePractice.Ptext.setText(pquestion1+"\n");
							FrameOnlinePractice.Ptext.insertIcon(image1);}
						}catch (Exception e5){
							e5.printStackTrace();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","������Ϣ",JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(t==3){
					Janswer.setText("");
					Janswer_1.setText("");
					if(number>0){
					 number=number-1;
					 Jnumber.setText("��"+String.valueOf(number+1)+"��");
					 try{Object[] jquestion=dbutil.selectById(Question[number]);
						String jquestion1=(String) jquestion[0];
					    String janswer=(String) jquestion[4];
					    FrameOnlinePractice.janswer_1=janswer;
						byte[] buffer=ioimage.readBolb(Question[number]);
						if(buffer==null){
							FrameOnlinePractice.Jtext.setText(jquestion1);
						}
						else{
						ImageIcon image=picture.BytesToIcon(buffer);
						ImageIcon image1;
						if(image.getIconHeight()>200||image.getIconWidth()>270){
							image1=picture.PreviewPicture(image, 200, 270);
						}
						else{
							image1=image;
						}
						FrameOnlinePractice.Jtext.setText(jquestion1+"\n");
						FrameOnlinePractice.Jtext.insertIcon(image1);}}catch (Exception e7){
							e7.printStackTrace();
						 
					 }
					}
					else{
						JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","������Ϣ",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					if(number==0){
						JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","������Ϣ",JOptionPane.ERROR_MESSAGE);
					}
					else{
					if(flag==0){
						Xgroup.clearSelection();
						xanswer="";
						Xanswer.setText("��ѡ��Ϊ��"+xanswer);
						Xanswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();}
					else if(flag==2){
						Pgroup.clearSelection();
						panswer="";
						Panswer.setText("��ѡ�𰸣�"+panswer);
						Panswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();
					}
					else if(flag==1){
						Tanswer.setText("");
						Tanswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();
					}
					else{
						Janswer.setText("");
						Janswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();
					}
					number=number-1;
					try {
						Object[] question = dbutil.selectById(Question[number]);
						int type=(int)question[3];
						if(type==0){
							contentPane.add(Xpanel);
							Xnumber.setText("��"+ String.valueOf(number+1) + "��");
							String xquestion1=(String) question[0];
						    String xanswer=(String) question[4];
						    xanswer_1=xanswer;
						    byte[] buffer = ioimage.readBolb(Question[number]);
						    if(buffer==null){
						     Xtext.setText(xquestion1);
						}
						else{
						ImageIcon image=picture.BytesToIcon(buffer);
						ImageIcon image1;
						if(image.getIconHeight()>200||image.getIconWidth()>270){
							image1=picture.PreviewPicture(image, 200, 270);
						}
						else{
							image1=image;
						}
						Xtext.setText(xquestion1+"\n");
						Xtext.insertIcon(image1);}
						}
						else if(type==1){
							contentPane.add(Tpanel);
							Tnumber.setText("��"+ String.valueOf(number+1) + "��");
							String tquestion1=(String) question[0];
							String tanswer=(String) question[4];
						    FrameOnlinePractice.tanswer_1=tanswer;
							byte[] buffer=ioimage.readBolb(Question[number]);
							if(buffer==null){
								FrameOnlinePractice.Ttext.setText(tquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							FrameOnlinePractice.Ttext.setText(tquestion1+"\n");
							FrameOnlinePractice.Ttext.insertIcon(image1);}
						}
						else if(type==2){
							contentPane.add(Ppanel);
							Pnumber.setText("��"+ String.valueOf(number+1) + "��");
							String pquestion1=(String) question[0];
							String panswer=(String) question[4];
							FrameOnlinePractice.panswer_1=panswer;
							byte[] buffer=ioimage.readBolb(Question[number]);
							if(buffer==null){
								FrameOnlinePractice.Ptext.setText(pquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							FrameOnlinePractice.Ptext.setText(pquestion1+"\n");
							FrameOnlinePractice.Ptext.insertIcon(image1);}
						}
						else{
							contentPane.add(Jpanel);
							Jnumber.setText("��"+ String.valueOf(number+1) + "��");
							String jquestion1=(String) question[0];
						    String janswer=(String) question[4];
						    FrameOnlinePractice.janswer_1=janswer;
							byte[] buffer=ioimage.readBolb(Question[number]);
							if(buffer==null){
								FrameOnlinePractice.Jtext.setText(jquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							FrameOnlinePractice.Jtext.setText(jquestion1+"\n");
							FrameOnlinePractice.Jtext.insertIcon(image1);}
						}
					} catch (Exception e7) {
						// TODO Auto-generated catch block
						e7.printStackTrace();
					}
				}}
			}
		});
       //=====================================================================================================================
		JButton button_6 = new JButton("��һ��");
		button_6.setBounds(831, 624, 93, 30);
		contentPane.add(button_6);
		button_6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Question[number+1]==null){
				if(t==0){
					Xgroup.clearSelection();
					xanswer="";
					Xanswer.setText("��ѡ��Ϊ��"+xanswer);
					Xanswer_1.setText("");
					number=number+1;
					Xnumber.setText("��"+String.valueOf(number+1)+"��");
					try {
						Xtext.setEditable(true);
						practice.Xpractice(c, d, t);
						Xtext.setEditable(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(t==2){
					Pgroup.clearSelection();
					panswer="";
					Panswer.setText("��ѡ�𰸣�"+panswer);
					Panswer_1.setText("");
					number=number+1;
					Pnumber.setText("��"+String.valueOf(number+1)+"��");
					try {
						practice.Ppractice(c, d, t);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(t==1){
					Tanswer.setText("");
					Tanswer_1.setText("");
					number=number+1;
					Tnumber.setText("��"+String.valueOf(number+1)+"��");
					try{
						Ttext.setEditable(true);
					practice.Tpractice(c, d, t);
					Ttext.setEditable(false);
					}catch (Exception e2){
						e2.printStackTrace();
					}
				}
				else if(t==3){
					Janswer.setText("");
					Janswer_1.setText("");
					number=number+1;
					Jnumber.setText("��"+String.valueOf(number+1)+"��");
					try {
						Jtext.setEditable(true);
						practice.Jpractice(c, d, t);
						Jtext.setEditable(false);
					}catch (Exception e3){
						e3.printStackTrace();
					}
				}
				else{
					if(flag==0){
						Xgroup.clearSelection();
						xanswer="";
						Xanswer.setText("��ѡ��Ϊ��"+xanswer);
						Xanswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();}
					else if(flag==2){
						Pgroup.clearSelection();
						panswer="";
						Panswer.setText("��ѡ�𰸣�"+panswer);
						Panswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();
					}
					else if(flag==1){
						Tanswer.setText("");
						Tanswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();
					}
					else{
						Janswer.setText("");
						Janswer_1.setText("");
						contentPane.remove(Ppanel);
						contentPane.remove(Xpanel);
						contentPane.remove(Tpanel);
						contentPane.remove(Jpanel);
						contentPane.updateUI();
					}
					flag=random.nextInt(4);
					if(flag==0){
						contentPane.add(Xpanel);
						contentPane.updateUI();
						number=number+1;
						Xnumber.setText("��"+String.valueOf(number+1)+"��");
						Xtext.setEditable(true);
						try {
							practice.Xpractice(c, d, t);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Xtext.setEditable(false);
					}
					else if(flag==1){
						contentPane.add(Tpanel);
						contentPane.updateUI();
						number=number+1;
						Tnumber.setText("��"+String.valueOf(number+1)+"��");
						Ttext.setEditable(true);
						try {
							practice.Tpractice(c, d, t);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Ttext.setEditable(false);
					}
					else if(flag==2){
						contentPane.add(Ppanel);
						contentPane.updateUI();
						number=number+1;
						Pnumber.setText("��"+String.valueOf(number+1)+"��");
						Ptext.setEditable(true);
						try {
							practice.Ppractice(c, d, t);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Ptext.setEditable(false);
					}
					else{
						contentPane.add(Jpanel);
						contentPane.updateUI();
						number=number+1;
						Jnumber.setText("��"+String.valueOf(number+1)+"��");
						Jtext.setEditable(true);
						try {
							practice.Jpractice(c, d, t);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Jtext.setEditable(false);
					}
					}
				}
				else{
					number=number+1;
					if(t==0){
						Xgroup.clearSelection();
						xanswer="";
						Xanswer.setText("��ѡ��Ϊ��"+xanswer);
						Xanswer_1.setText("");
						Xnumber.setText("��"+ String.valueOf(number+1) + "��");
						try{
							Object[] xquestion = dbutil.selectById(Question[number]);
							String xquestion1=(String) xquestion[0];
							String xanswer=(String) xquestion[4];
							xanswer_1=xanswer;
							byte[] buffer = ioimage.readBolb(Question[number]);
							if(buffer==null){
							Xtext.setText(xquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							Xtext.setText(xquestion1+"\n");
							Xtext.insertIcon(image1);}
						}catch (Exception e4){
							e4.printStackTrace();
							}
					}
					else if(t==1){
						Tanswer.setText("");
						Tanswer_1.setText("");
						 Tnumber.setText("��"+String.valueOf(number+1)+"��");
						try{
							Object[] tquestion=dbutil.selectById(Question[number]);
							String tquestion1=(String) tquestion[0];
							String tanswer=(String) tquestion[4];
						    FrameOnlinePractice.tanswer_1=tanswer;
							byte[] buffer=ioimage.readBolb(Question[number]);
							if(buffer==null){
								FrameOnlinePractice.Ttext.setText(tquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							FrameOnlinePractice.Ttext.setText(tquestion1+"\n");
							FrameOnlinePractice.Ttext.insertIcon(image1);}
						}catch (Exception e6){
							e6.printStackTrace();
						}
					}
					else if(t==2){
						Pgroup.clearSelection();
						panswer="";
						Panswer.setText("��ѡ�𰸣�"+panswer);
						Panswer_1.setText("");
						Pnumber.setText("��"+ String.valueOf(number+1) + "��");
						try{
							Object[] pquestion=dbutil.selectById(Question[number]);
							String pquestion1=(String) pquestion[0];
							String panswer=(String) pquestion[4];
							FrameOnlinePractice.panswer_1=panswer;
							byte[] buffer=ioimage.readBolb(Question[number]);
							if(buffer==null){
							FrameOnlinePractice.Ptext.setText(pquestion1);
							}
					else if(t==1){
						ImageIcon image=picture.BytesToIcon(buffer);
						ImageIcon image1;
						if(image.getIconHeight()>200||image.getIconWidth()>270){
							image1=picture.PreviewPicture(image, 200, 270);
						}
						else{
							image1=image;
						}
						FrameOnlinePractice.Ptext.setText(pquestion1+"\n");
						FrameOnlinePractice.Ptext.insertIcon(image1);}
						}catch (Exception e5){
							e5.printStackTrace();
							}
					}
					else if(t==3){
						Janswer.setText("");
						Janswer_1.setText("");
						 Jnumber.setText("��"+String.valueOf(number+1)+"��");
						 try{
							 Object[] jquestion=dbutil.selectById(Question[number]);
						     String jquestion1=(String) jquestion[0];
						     String janswer=(String) jquestion[4];
						     FrameOnlinePractice.janswer_1=janswer;
							 byte[] buffer=ioimage.readBolb(Question[number]);
							 if(buffer==null){
								FrameOnlinePractice.Jtext.setText(jquestion1);
							}
							 else{
							 ImageIcon image=picture.BytesToIcon(buffer);
							 ImageIcon image1;
								if(image.getIconHeight()>200||image.getIconWidth()>270){
									image1=picture.PreviewPicture(image, 200, 270);
								}
								else{
									image1=image;
								}
							 FrameOnlinePractice.Jtext.setText(jquestion1+"\n");
							 FrameOnlinePractice.Jtext.insertIcon(image1);}}catch (Exception e7){
								e7.printStackTrace();
							 }
					}
					else{
						if(flag==0){
							Xgroup.clearSelection();
							xanswer="";
							Xanswer.setText("��ѡ��Ϊ��"+xanswer);
							Xanswer_1.setText("");
							contentPane.remove(Ppanel);
							contentPane.remove(Xpanel);
							contentPane.remove(Tpanel);
							contentPane.remove(Jpanel);
							contentPane.updateUI();}
						else if(flag==2){
							Pgroup.clearSelection();
							panswer="";
							Panswer.setText("��ѡ�𰸣�"+panswer);
							Panswer_1.setText("");
							contentPane.remove(Ppanel);
							contentPane.remove(Xpanel);
							contentPane.remove(Tpanel);
							contentPane.remove(Jpanel);
							contentPane.updateUI();
						}
						else if(flag==1){
							Tanswer.setText("");
							Tanswer_1.setText("");
							contentPane.remove(Ppanel);
							contentPane.remove(Xpanel);
							contentPane.remove(Tpanel);
							contentPane.remove(Jpanel);
							contentPane.updateUI();
						}
						if(flag==3){
							Janswer.setText("");
							Janswer_1.setText("");
							contentPane.remove(Ppanel);
							contentPane.remove(Xpanel);
							contentPane.remove(Tpanel);
							contentPane.remove(Jpanel);
							contentPane.updateUI();
						}
						try {
							Object[] question = dbutil.selectById(Question[number]);
							int type=(int)question[3];
							if(type==0){
								contentPane.add(Xpanel);
								Xnumber.setText("��"+ String.valueOf(number+1) + "��");
								String xquestion1=(String) question[0];
							    String xanswer=(String) question[4];
							    xanswer_1=xanswer;
							    byte[] buffer = ioimage.readBolb(Question[number]);
							    if(buffer==null){
							     Xtext.setText(xquestion1);
							}
							else{
							ImageIcon image=picture.BytesToIcon(buffer);
							ImageIcon image1;
							if(image.getIconHeight()>200||image.getIconWidth()>270){
								image1=picture.PreviewPicture(image, 200, 270);
							}
							else{
								image1=image;
							}
							Xtext.setText(xquestion1+"\n");
							Xtext.insertIcon(image1);}
							}
							else if(type==1){
								contentPane.add(Tpanel);
								Tnumber.setText("��"+ String.valueOf(number+1) + "��");
								String tquestion1=(String) question[0];
								String tanswer=(String) question[4];
							    FrameOnlinePractice.tanswer_1=tanswer;
								byte[] buffer=ioimage.readBolb(Question[number]);
								if(buffer==null){
									FrameOnlinePractice.Ttext.setText(tquestion1);
								}
								else{
								ImageIcon image=picture.BytesToIcon(buffer);
								ImageIcon image1;
								if(image.getIconHeight()>200||image.getIconWidth()>270){
									image1=picture.PreviewPicture(image, 200, 270);
								}
								else{
									image1=image;
								}
								FrameOnlinePractice.Ttext.setText(tquestion1+"\n");
								FrameOnlinePractice.Ttext.insertIcon(image1);}
							}
							else if(type==2){
								contentPane.add(Ppanel);
								Pnumber.setText("��"+ String.valueOf(number+1) + "��");
								String pquestion1=(String) question[0];
								String panswer=(String) question[4];
								FrameOnlinePractice.panswer_1=panswer;
								byte[] buffer=ioimage.readBolb(Question[number]);
								if(buffer==null){
									FrameOnlinePractice.Ptext.setText(pquestion1);
								}
								else{
								ImageIcon image=picture.BytesToIcon(buffer);
								ImageIcon image1;
								if(image.getIconHeight()>200||image.getIconWidth()>270){
									image1=picture.PreviewPicture(image, 200, 270);
								}
								else{
									image1=image;
								}
								FrameOnlinePractice.Ptext.setText(pquestion1+"\n");
								FrameOnlinePractice.Ptext.insertIcon(image1);}
							}
							else{
								contentPane.add(Jpanel);
								Jnumber.setText("��"+ String.valueOf(number+1) + "��");
								String jquestion1=(String) question[0];
							    String janswer=(String) question[4];
							    FrameOnlinePractice.janswer_1=janswer;
								byte[] buffer=ioimage.readBolb(Question[number]);
								if(buffer==null){
									FrameOnlinePractice.Jtext.setText(jquestion1);
								}
								else{
								ImageIcon image=picture.BytesToIcon(buffer);
								ImageIcon image1;
								if(image.getIconHeight()>200||image.getIconWidth()>270){
									image1=picture.PreviewPicture(image, 200, 270);
								}
								else{
									image1=image;
								}
								FrameOnlinePractice.Jtext.setText(jquestion1+"\n");
								FrameOnlinePractice.Jtext.insertIcon(image1);}
							}
						} catch (Exception e7) {
							// TODO Auto-generated catch block
							e7.printStackTrace();
						}
					}}
				}
			});
		frame.setVisible(true);
		}
	}
class PracticeSelect {
	IOImage ioimage=new IOImage();
	PictureUtils picture=new PictureUtils();
	Random random=new Random();
	DButil dbutil=new DButil();
	private static String[] question;
	public void Xpractice(int chapters,int difficulty,int types) throws Exception{
		if(chapters==10){
			int chap=random.nextInt(10);
			question=dbutil.selectByDegree( difficulty,chap, 0);
		}
		else{
			question=dbutil.selectByDegree(difficulty, chapters, 0);
		}
		if(question.length==0){}
		else{
		int number=random.nextInt(question.length);
		FrameOnlinePractice.Question[FrameOnlinePractice.number]=question[number];
		Object[] xquestion=dbutil.selectById(question[number]);
		String xquestion1=(String) xquestion[0];
		String xanswer=(String) xquestion[4];
		FrameOnlinePractice.xanswer_1=xanswer;
		byte[] buffer=ioimage.readBolb(question[number]);
		if(buffer==null){
			FrameOnlinePractice.Xtext.setText(xquestion1);
		}
		else{
		ImageIcon image=picture.BytesToIcon(buffer);
		ImageIcon image1;
		if(image.getIconHeight()>200||image.getIconWidth()>270){
			image1=picture.PreviewPicture(image, 200, 270);
		}
		else{
			image1=image;
		}
		FrameOnlinePractice.Xtext.setText(xquestion1+"\n");
		FrameOnlinePractice.Xtext.insertIcon(image1);}
		}
	}
	public void Ppractice(int chapters,int difficulty,int types) throws Exception{
		if(chapters==10){
			int chap=random.nextInt(10);
			question=dbutil.selectByDegree(difficulty, chap, 2);
		}
		else{
			question=dbutil.selectByDegree(difficulty, chapters, 2);
		}
		if(question.length==0){}
		else{
		int number=random.nextInt(question.length);
		FrameOnlinePractice.Question[FrameOnlinePractice.number]=question[number];
		Object[] pquestion=dbutil.selectById(question[number]);
		String pquestion1=(String) pquestion[0];
		String panswer=(String) pquestion[4];
		FrameOnlinePractice.panswer_1=panswer;
		byte[] buffer=ioimage.readBolb(question[number]);
		if(buffer==null){
			FrameOnlinePractice.Ptext.setText(pquestion1);
		}
		else{
		ImageIcon image=picture.BytesToIcon(buffer);
		ImageIcon image1;
		if(image.getIconHeight()>200||image.getIconWidth()>270){
			image1=picture.PreviewPicture(image, 200, 270);
		}
		else{
			image1=image;
		}
		FrameOnlinePractice.Ptext.setText(pquestion1+"\n");
		FrameOnlinePractice.Ptext.insertIcon(image1);}
		}
	}
	public void Tpractice(int chapters,int difficulty,int types) throws Exception{
		if(chapters==10){
			int chap=random.nextInt(10);
			question=dbutil.selectByDegree(difficulty, chap, 1);
		}
		else{
			question=dbutil.selectByDegree(difficulty, chapters, 1);
		}
		if(question.length==0){}
		else{
		int number=random.nextInt(question.length);
		FrameOnlinePractice.Question[FrameOnlinePractice.number]=question[number];
		Object[] tquestion=dbutil.selectById(question[number]);
		String tquestion1=(String) tquestion[0];
		String tanswer=(String) tquestion[4];
	    FrameOnlinePractice.tanswer_1=tanswer;
		byte[] buffer=ioimage.readBolb(question[number]);
		if(buffer==null){
			FrameOnlinePractice.Ttext.setText(tquestion1);
		}
		else{
		ImageIcon image=picture.BytesToIcon(buffer);
		ImageIcon image1;
		if(image.getIconHeight()>200||image.getIconWidth()>270){
			image1=picture.PreviewPicture(image, 200, 270);
		}
		else{
			image1=image;
		}
		FrameOnlinePractice.Ttext.setText(tquestion1+"\n");
		FrameOnlinePractice.Ttext.insertIcon(image1);}
		}
	}
	public void Jpractice(int chapters,int difficulty,int types) throws Exception{
		if(chapters==10){
			int chap=random.nextInt(10);
			question=dbutil.selectByDegree(difficulty, chap, 3);
		}
		else{
			question=dbutil.selectByDegree(difficulty, chapters, 3);
		}
		if(question.length==0){}
		else{
		int number=random.nextInt(question.length);
		FrameOnlinePractice.Question[FrameOnlinePractice.number]=question[number];
		Object[] jquestion=dbutil.selectById(question[number]);
		String jquestion1=(String) jquestion[0];
	    String janswer=(String) jquestion[4];
	    FrameOnlinePractice.janswer_1=janswer;
		byte[] buffer=ioimage.readBolb(question[number]);
		if(buffer==null){
			FrameOnlinePractice.Jtext.setText(jquestion1);
		}
		else{
		ImageIcon image=picture.BytesToIcon(buffer);
		ImageIcon image1;
		if(image.getIconHeight()>200||image.getIconWidth()>270){
			image1=picture.PreviewPicture(image, 200, 270);
		}
		else{
			image1=image;
		}
		FrameOnlinePractice.Jtext.setText(jquestion1+"\n");
		FrameOnlinePractice.Jtext.insertIcon(image1);}
		}
	}
	
}



