import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

public class FrameOnlineTest  {
	public static int flag;
	public static JFrame frame=new JFrame();
    public static JLabel lablefttime;
	JLabel Xnumber;
	int xnumber=1;
	JLabel Xanswer;
	String xanswer="";
	Object[][] examquestions;
	String[] stu_xanswer=new String[10];
	String[] question_id=new String[30];
	String[] stu_answer=new String[30];
	JTextPane Xtext;
	JTextPane Ptext;
	JLabel Pnumber;
	int pnumber=1;
	JLabel Panswer;
	String panswer;
	String[] stu_panswer=new String[10];
	JTextPane Ttext;
	JTextField Tanswer;
	JLabel Tnumber;
	int tnumber=1;
	String[] stu_tanswer=new String[10];
	JTextPane Jtext;
	JTextArea Janswer;
	JLabel Jnumber;
	int jnumber=1;
	String[] stu_janswer=new String[10];
	ButtonGroup Pgroup;
	ButtonGroup Xgroup;
	public static int time;
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public  void OnlineTestFrame(String name,String stu_id) throws Exception {
		IOImage ioimage=new IOImage();
		PictureUtils picture=new PictureUtils();
		final DButil dbutil=new DButil();
		final String id=stu_id;
		flag=1;
		try {
			examquestions=dbutil.readPaper();
		} catch (SQLException e1) {
			e1.printStackTrace();}
		if(examquestions==null){
			System.out.println("û��ָ���Ծ�");
			JOptionPane.showMessageDialog(null,"�����Ծ����ڣ�","������Ϣ",JOptionPane.ERROR_MESSAGE);
		}
		else{
			time=(int)examquestions[4][4];
		//���ڽ���
		
		frame.setTitle("���߿���ģ��");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-960)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-720)/2);
		frame.setSize(960, 720);
		frame.setResizable(false);
		frame.setVisible(false);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int confirm = JOptionPane.showConfirmDialog(null,"�رմ���ϵͳ���Զ��ύ�Ծ�","��ʾ",JOptionPane.OK_CANCEL_OPTION);
				if(confirm==0){
					flag=0;
					if(tnumber==examquestions[1].length){
						stu_tanswer[tnumber-1]=Tanswer.getText();
					}
					if(jnumber==examquestions[3].length){
						stu_janswer[jnumber-1]=Janswer.getText();
					}
					for(int i=0;i<examquestions[0].length;i++){
						stu_answer[i]=stu_xanswer[i];
					}
					for(int i=0;i<examquestions[1].length;i++){
						stu_answer[examquestions[0].length+i]=stu_tanswer[i];
					}
					for(int i=0;i<examquestions[2].length;i++){
						if(stu_panswer[i]=="��"){
						    stu_answer[examquestions[0].length+examquestions[1].length+i]="0";
						}
						else{
							stu_answer[examquestions[0].length+examquestions[1].length+i]="1";
						}
					}
					for(int i=0;i<examquestions[3].length;i++){
						stu_answer[examquestions[0].length+examquestions[1].length+examquestions[2].length+i]=stu_janswer[i];
					}
					if(stu_answer==null){}
					else{
					try {
						dbutil.recordAnswer(id, question_id, stu_answer);
						dbutil.autoMark(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
					frame.dispose();
					MainMenuFrame_student.frame.setVisible(true);
				}
			}
		});
		
		JPanelWithPic contentPane = new JPanelWithPic();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		//======================================================================================================================
		//ѡ���ⲿ�ֽ��洴
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 85, 952, 607);
		contentPane.add(tabbedPane);
		JPanelWithPic Xpanel = new JPanelWithPic();   
		Xpanel.setBounds(89, 85, 720, 540);
		Xpanel.setBorder(null);
		tabbedPane.addTab("ѡ���� "+"("+examquestions[0].length+")",Xpanel);    //���ѡ���⸴ѡ��
		Xpanel.setLayout(null);
		//�����������ʾѡ����
		Xtext = new JTextPane();
		Xtext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Xtext.setEditable(true);
		for(int i=0;i<examquestions[0].length;i++){
			if(examquestions[0][i]==null)break;
			else{
				String xquetions=(String)examquestions[0][i];
				question_id[i]=xquetions;
				Object[] xquestion=dbutil.selectById(xquetions);
				String xquestion1=(String) xquestion[0];
				byte[] buffer=ioimage.readBolb(xquetions);
				if(buffer==null){
					Xtext.replaceSelection(i+1+"��"+xquestion1);
					Xtext.replaceSelection("\n"+"\n");
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
				Xtext.replaceSelection(i+1+"��"+xquestion1);
				Xtext.replaceSelection("\n");
				Xtext.insertIcon(image1);
				Xtext.replaceSelection("\n"+"\n");}
			}
		}
		Xtext.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(Xtext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(254, 43, 683, 525);
		Xpanel.add(scrollPane);
		//������ǩ��ʶ������
		JLabel label_2 = new JLabel("������");    //
		label_2.setFont(new Font("����", Font.BOLD, 15));
		label_2.setBounds(222, 10, 80, 23);
		Xpanel.add(label_2);
		//������ǩ��ʶ���
		Xnumber = new JLabel("��"+String.valueOf(xnumber)+"��"); //��ʾѡ�������
		Xnumber.setFont(new Font("����", Font.BOLD, 15));
		Xnumber.setBounds(54, 133, 72, 24);
		Xpanel.add(Xnumber);
		for(int i=0;i<examquestions[0].length;i++){
			stu_xanswer[i]="";
		}
		//������ѡ���ʶѡ���
		Xgroup = new ButtonGroup();
		JCheckBox chckbxA = new JCheckBox("A");//ѡ��A����
		chckbxA.setFont(new Font("����", Font.BOLD, 15));
		chckbxA.setBounds(54, 175, 64, 23);
		Xpanel.add(chckbxA);
		Xgroup.add(chckbxA);
		chckbxA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="A";
				Xanswer.setText("ѡ����Ϊ��"+xanswer);
				stu_xanswer[xnumber-1]=xanswer;
			}
		});
		JCheckBox chckbxB = new JCheckBox("B");//ѡ��B����
		chckbxB.setFont(new Font("����", Font.BOLD, 15));
		chckbxB.setBounds(144, 175, 64, 23);
		Xpanel.add(chckbxB);
		Xgroup.add(chckbxB);
		chckbxB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="B";
				Xanswer.setText("ѡ����Ϊ��"+xanswer);
				stu_xanswer[xnumber-1]=xanswer;
			}
		});
		JCheckBox chckbxC = new JCheckBox("C");//ѡ��C����
		chckbxC.setFont(new Font("����", Font.BOLD, 15));
		chckbxC.setBounds(54, 235, 64, 23);
		Xpanel.add(chckbxC);
		Xgroup.add(chckbxC);
		chckbxC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="C";
				Xanswer.setText("ѡ����Ϊ��"+xanswer);
				stu_xanswer[xnumber-1]=xanswer;
			}
		});
		JCheckBox chckbxD = new JCheckBox("D");//ѡ��D����
		chckbxD.setFont(new Font("����", Font.BOLD, 15));
		chckbxD.setBounds(144, 235, 64, 23);
		Xpanel.add(chckbxD);
		Xgroup.add(chckbxD);
		chckbxD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xanswer="D";
				Xanswer.setText("ѡ����Ϊ��"+xanswer);
				stu_xanswer[xnumber-1]=xanswer;
			}
		});
		//������ǩ��ʶ��ѡ�Ĵ�
		Xanswer = new JLabel("ѡ����Ϊ��"+xanswer);
		Xanswer.setForeground(Color.RED);
		Xanswer.setFont(new Font("����", Font.BOLD, 15));
		Xanswer.setBounds(10, 325, 149, 30);
		Xpanel.add(Xanswer);
		//��Ӱ�ť�л�������
		JButton Xbutton_1 = new JButton("��һ��");
		Xbutton_1.setBounds(148, 463, 93, 30);
		Xpanel.add(Xbutton_1);
		Xbutton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(xnumber<examquestions[0].length){
					xnumber=xnumber+1;
					Xnumber.setText("��"+String.valueOf(xnumber)+"��");
					Xgroup.clearSelection();
					xanswer=stu_xanswer[xnumber-1];
					Xanswer.setText("��ѡ��Ϊ��"+xanswer);
					}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ������һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton Xbutton_2 = new JButton("��һ��");
		Xbutton_2.setBounds(10, 463, 93, 30);
		Xpanel.add(Xbutton_2);
		Xbutton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(xnumber>1){
					xnumber=xnumber-1;
					Xnumber.setText("��"+String.valueOf(xnumber)+"��");
					Xgroup.clearSelection();
					xanswer=stu_xanswer[xnumber-1];
					Xanswer.setText("��ѡ�𰸣�"+xanswer);
				}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//��ӱ�ǩ��ʶ������
		JLabel label_8 = new JLabel("������");
		label_8.setFont(new Font("����", Font.BOLD, 15));
		label_8.setBounds(10, 77, 93, 24);
		Xpanel.add(label_8);
		
		//=====================================================================================================================
		//�ж��ⲿ�ֽ��洴��
		JPanelWithPic Ppanel = new JPanelWithPic();
		tabbedPane.addTab("�ж� �� "+"("+examquestions[2].length+")",Ppanel);//�����ж��⸴ѡ��
		Ppanel.setLayout(null);
		Ppanel.setBounds(89, 85, 720, 540);
		Ptext = new JTextPane();
		Ptext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Ptext.setEditable(true);
		for(int i=0;i<examquestions[2].length;i++){
			if(examquestions[2][i]==null)break;
			else{
				String pquetions=(String)examquestions[2][i];
				question_id[examquestions[0].length+examquestions[1].length+i]=pquetions;
				Object[] pquestion=dbutil.selectById(pquetions);
				String pquestion1=(String) pquestion[0];
				byte[] buffer=ioimage.readBolb(pquetions);
				if(buffer==null){
					Ptext.replaceSelection(i+1+"��"+pquestion1);
					Ptext.replaceSelection("\n"+"\n");
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
				Ptext.replaceSelection(i+1+"��"+pquestion1);
				Ptext.replaceSelection("\n");
				Ptext.insertIcon(image1);
				Ptext.replaceSelection("\n"+"\n");}
			}
		}
		Ptext.setEditable(false);
		JScrollPane scrollPane_1 = new JScrollPane(Ptext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(289, 37, 648, 531);
		Ppanel.add(scrollPane_1);
		//��ӱ�ǩ��ʶ������
		JLabel label_5 = new JLabel("\u8BD5\u9898\u533A");
		label_5.setFont(new Font("����", Font.BOLD, 15));
		label_5.setBounds(245, 10, 72, 23);
		Ppanel.add(label_5);
		//��ӱ�ǩ��ʶ���
		Pnumber = new JLabel("�� "+String.valueOf(pnumber)+" ��");
		Pnumber.setFont(new Font("����", Font.BOLD, 15));
		Pnumber.setBounds(36, 177, 90, 23);
		Ppanel.add(Pnumber);
		for(int i=0;i<examquestions[2].length;i++){
			stu_panswer[i]="";
		}
		//��ӵ�ѡ����Ϊ�ж���ѡ��
		Pgroup = new ButtonGroup();
		JRadioButton Pyes = new JRadioButton("��");//�ж���ѡ��
		Pyes.setFont(new Font("����", Font.BOLD, 15));
		Pyes.setBounds(82, 243, 90, 23);
		Pgroup.add(Pyes);
		Ppanel.add(Pyes);
		Pyes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panswer="��";
				Panswer.setText("��ѡ�𰸣�"+panswer);
				stu_panswer[pnumber-1]=panswer;
				}
		});
		JRadioButton Pwrong = new JRadioButton("��");//�ж���ѡ��
		Pwrong.setFont(new Font("����", Font.BOLD, 15));
		Pwrong.setBounds(82, 294, 90, 23);
		Pgroup.add(Pwrong);
		Ppanel.add(Pwrong);
		Pwrong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panswer="��";
				Panswer.setText("��ѡ�𰸣�"+panswer);
				stu_panswer[pnumber-1]=panswer;
				}
		});
		//��ӱ�ǩ��ʶ��ѡ��
		Panswer = new JLabel("��ѡ�𰸣�");//��ʾ��ѡ��
		Panswer.setForeground(Color.RED);
		Panswer.setFont(new Font("����", Font.BOLD, 15));
		Panswer.setBounds(45, 352, 127, 30);
		Ppanel.add(Panswer);
		//��Ӱ�ť�л�������
		JButton Pbutton_1 = new JButton("��һ��");
		Pbutton_1.setBounds(167, 453, 93, 30);
		Ppanel.add(Pbutton_1);
		Pbutton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(pnumber<examquestions[2].length){
					pnumber=pnumber+1;
					Pnumber.setText("�� "+String.valueOf(pnumber)+" ��");
					Pgroup.clearSelection();
					panswer=stu_panswer[pnumber-1];
					Panswer.setText("��ѡ�𰸣�"+panswer);
					}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ������һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
				}
		});
		JButton Pbutton_2 = new JButton("��һ��");
		Pbutton_2.setBounds(30, 453, 93, 30);
		Ppanel.add(Pbutton_2);
		Pbutton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(pnumber>1){
					pnumber=pnumber-1;
					Pnumber.setText("��"+String.valueOf(pnumber)+"��");
					Pgroup.clearSelection();
					panswer=stu_panswer[pnumber-1];
					Panswer.setText("��ѡ�𰸣�"+panswer);
				}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//��ӱ�ǩ��ʶ������
		JLabel label_9 = new JLabel("\u7B54\u9898\u533A");
		label_9.setFont(new Font("����", Font.BOLD, 15));
		label_9.setBounds(36, 91, 65, 23);
		Ppanel.add(label_9);
		//=====================================================================================================================
		//�������洴��
		JPanelWithPic Tpanel = new JPanelWithPic();
		tabbedPane.addTab("�����"+"("+examquestions[1].length+")",Tpanel);//�����ж��⸴ѡ��
		Tpanel.setLayout(null);
		Tpanel.setBounds(89, 85, 720, 540);
		Ttext = new JTextPane();
		Ttext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Ttext.setEditable(true);
		for(int i=0;i<examquestions[1].length;i++){
			if(examquestions[1][i]==null)break;
			else{
				String tquetions=(String)examquestions[1][i];
				question_id[examquestions[0].length+i]=tquetions;
				Object[] tquestion=dbutil.selectById(tquetions);
				String tquestion1=(String) tquestion[0];
				byte[] buffer=ioimage.readBolb(tquetions);
				if(buffer==null){
					Ttext.replaceSelection(i+1+"��"+tquestion1);
					Ttext.replaceSelection("\n"+"\n");
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
				Ttext.replaceSelection(i+1+"��"+tquestion1);
				Ttext.replaceSelection("\n");
				Ttext.insertIcon(image1);
				Ttext.replaceSelection("\n"+"\n");}
			}
		}
		Ttext.setEditable(false);
		JScrollPane scrollPane_4 = new JScrollPane(Ttext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_4.setBounds(88, 27, 809, 342);
		Tpanel.add(scrollPane_4);
		
		JLabel label = new JLabel("\u8BD5\u9898");
		label.setFont(new Font("����", Font.BOLD, 15));
		label.setBounds(24, 10, 54, 27);
		Tpanel.add(label);
		
		JLabel label_3 = new JLabel("���");
		label_3.setFont(new Font("����", Font.BOLD, 15));
		label_3.setBounds(36, 381, 86, 27);
		Tpanel.add(label_3);
		
		Tnumber = new JLabel("�� 1 ��");
		Tnumber.setFont(new Font("����", Font.BOLD, 15));
		Tnumber.setBounds(60, 427, 69, 28);
		Tpanel.add(Tnumber);
		//������
		Tanswer = new JTextField();
		Tanswer.setFont(new Font("����", Font.PLAIN, 15));
		Tanswer.setBounds(140, 424, 230, 36);
		Tpanel.add(Tanswer);
		Tanswer.setColumns(10);
		
		for(int i=0;i<examquestions[1].length;i++){
			stu_tanswer[i]="";
		}
		
		JButton button_1 = new JButton("��һ��");
		button_1.setBounds(804, 439, 93, 30);
		Tpanel.add(button_1);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tnumber==examquestions[1].length){
					stu_tanswer[tnumber-1]=Tanswer.getText();
					tnumber=tnumber-1;
					Tnumber.setText("��"+String.valueOf(tnumber)+"��");
					Tanswer.setText(stu_tanswer[tnumber-1]);
				}
				else if(tnumber>1){
					tnumber=tnumber-1;
					Tnumber.setText("��"+String.valueOf(tnumber)+"��");
					Tanswer.setText(stu_tanswer[tnumber-1]);
				}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton button_2 = new JButton("��һ��");
		button_2.setBounds(804, 524, 93, 30);
		Tpanel.add(button_2);
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tnumber<examquestions[1].length){
					stu_tanswer[tnumber-1]=Tanswer.getText();
					tnumber=tnumber+1;
					Tnumber.setText("��"+String.valueOf(tnumber)+"��");
					Tanswer.setText(stu_tanswer[tnumber-1]);
					}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ������һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
				}
		});
		
		//=====================================================================================================================
		//�������洴��
		JPanelWithPic Jpanel = new JPanelWithPic();
		tabbedPane.addTab("����� "+"("+examquestions[3].length+")",Jpanel);
		Jpanel.setLayout(null);
		//��ӿ����������ı�
		Jtext = new JTextPane();
		Jtext.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Jtext.setEditable(true);
		for(int i=0;i<examquestions[3].length;i++){
			if(examquestions[3][i]==null)break;
			else{
				String jquetions=(String)examquestions[3][i];
				question_id[examquestions[0].length+examquestions[1].length+examquestions[2].length+i]=jquetions;
				Object[] jquestion=dbutil.selectById(jquetions);
				String jquestion1=(String) jquestion[0];
				byte[] buffer=ioimage.readBolb(jquetions);
				if(buffer==null){
					Jtext.replaceSelection(i+1+"��"+jquestion1);
					Jtext.replaceSelection("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
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
				Jtext.replaceSelection(i+1+"��"+jquestion1);
				Jtext.replaceSelection("\n");
				Jtext.insertIcon(image1);
				Jtext.replaceSelection("\n"+"\n"+"\n"+"\n"+"\n");}
			}
		}
		Jtext.setEditable(false);
		JScrollPane scrollPane_2 = new JScrollPane(Jtext,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_2.setBounds(88, 27, 809, 342);
		Jpanel.add(scrollPane_2);
		for(int i=0;i<examquestions[3].length;i++){
			stu_janswer[i]="";
		}
		//��Ӱ�ť�л�������
		JButton Jbutton_1 = new JButton("��һ��");
		Jbutton_1.setBounds(804, 517, 93, 30);
		Jpanel.add(Jbutton_1);
		Jbutton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(jnumber<examquestions[3].length){
					stu_janswer[jnumber-1]=Janswer.getText();
					jnumber=jnumber+1;
					Jnumber.setText("��"+String.valueOf(jnumber)+"��");
					Janswer.setText(stu_janswer[jnumber-1]);
				}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ������һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton Jbutton_2 = new JButton("��һ��");
		Jbutton_2.setBounds(804, 441, 93, 30);
		Jpanel.add(Jbutton_2);
		Jbutton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(jnumber==examquestions[3].length){
					stu_janswer[jnumber-1]=Janswer.getText();
					jnumber=jnumber-1;
					Jnumber.setText("��"+String.valueOf(jnumber)+"��");
					Janswer.setText(stu_janswer[jnumber-1]);
				}
				else if(jnumber>1){
					jnumber=jnumber-1;
					Jnumber.setText("��"+String.valueOf(jnumber)+"��");
					Janswer.setText(stu_janswer[jnumber-1]);
				}
				else{
					JOptionPane.showMessageDialog(null,"���Ѿ��ǵ�һ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//����ı����������
		Janswer = new JTextArea();
		Janswer.setFont(new Font("Monospaced", Font.PLAIN, 15));
		Janswer.setText(" ");
		JScrollPane scrollPane_3 = new JScrollPane(Janswer,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_3.setBounds(88, 405, 642, 152);
		Jpanel.add(scrollPane_3);
		//��ӱ�ǩ��ʶ�����
		JLabel label_1 = new JLabel("���");
		label_1.setFont(new Font("����", Font.BOLD, 15));
		label_1.setBounds(24, 357, 54, 30);
		Jpanel.add(label_1);
		//��ӱ�ǩ��ʶ������
		JLabel label_10 = new JLabel("\u8BD5\u9898");
		label_10.setFont(new Font("����", Font.BOLD, 15));
		label_10.setBounds(24, 10, 54, 30);
		Jpanel.add(label_10);
		//��ӱ�ǩ��ʶ��������
		Jnumber = new JLabel("��"+String.valueOf(jnumber)+"��");
		Jnumber.setFont(new Font("����", Font.BOLD, 15));
		Jnumber.setBounds(19, 397, 59, 30);
		Jpanel.add(Jnumber);
		//=====================================================================================================================

		//=====================================================================================================================
		//��ӱ�ǩ��ʾʣ��ʱ��
		lablefttime = new JLabel("ʣ��ʱ�䣺");
		lablefttime.setFont(new Font("����", Font.BOLD, 15));
		lablefttime.setForeground(Color.BLUE);
		lablefttime.setBounds(475, 39, 170, 25);
		contentPane.add(lablefttime);
		//��ӱ�ǩ��ʾ��������
		JLabel Jname = new JLabel("����������"+name);//��ʾ��������
		Jname.setFont(new Font("����", Font.BOLD, 15));
		Jname.setForeground(Color.BLUE);
		Jname.setBounds(33, 39, 170, 25);
		contentPane.add(Jname);
		//��ӱ�ǩ��ʾ����ʱ��
		JLabel Jtime = new JLabel("����ʱ�䣺"+time+"����");//��ʾ����ʱ��
		Jtime.setFont(new Font("����", Font.BOLD, 15));
		Jtime.setForeground(Color.BLUE);
		Jtime.setBounds(247, 39, 150, 25);
		contentPane.add(Jtime);
		JButton button_7 = new JButton("ʹ�ü�����");
		button_7.setBounds(650, 39, 107, 35);
		contentPane.add(button_7);
		button_7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CaculatorFrame cacu =new CaculatorFrame();
				cacu.Caculator();
			}
		});
		//��Ӱ�ťʵ���ύ�Ծ�
		JButton button = new JButton("�ύ�Ծ�");//�ύ�Ծ�
		button.addActionListener(new ActionListener(){         //ȷ���ύ�Ծ�
			public void actionPerformed(ActionEvent e){
				int confirm = JOptionPane.showConfirmDialog(null,"��ȷ���ύ��","ȷ���ύ",JOptionPane.OK_CANCEL_OPTION);
				if(confirm==0){
					flag=0;
					if(tnumber==examquestions[1].length){
						stu_tanswer[tnumber-1]=Tanswer.getText();
					}
					if(jnumber==examquestions[3].length){
						stu_janswer[jnumber-1]=Janswer.getText();
					}
					for(int i=0;i<examquestions[0].length;i++){
						stu_answer[i]=stu_xanswer[i];
					}
					for(int i=0;i<examquestions[1].length;i++){
						stu_answer[examquestions[0].length+i]=stu_tanswer[i];
					}
					for(int i=0;i<examquestions[2].length;i++){
						if(stu_panswer[i]=="��"){
						    stu_answer[examquestions[0].length+examquestions[1].length+i]="0";
						}
						else{
							stu_answer[examquestions[0].length+examquestions[1].length+i]="1";
						}
					}
					for(int i=0;i<examquestions[3].length;i++){
						stu_answer[examquestions[0].length+examquestions[1].length+examquestions[2].length+i]=stu_janswer[i];
					}
					if(stu_answer==null){}
					else{
					try {
						dbutil.recordAnswer(id, question_id, stu_answer);
						dbutil.autoMark(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
					frame.dispose();
					MainMenuFrame_student.frame.setVisible(true);
				}
			}
		});
		button.setBounds(822, 39, 107, 35);
		contentPane.add(button);
		
		//ִ���߳�ʵ�ֵ���ʱ����
		Thread clocker=new Thread(new TimeCount());
		clocker.start();
		frame.setVisible(true);
		}
		}
	
	}
/*class loading{
	public static JWindow win;
	public void load(){
		win =new JWindow();
		JLabel label=new JLabel();
		label.setSize(200,32);
		label.setText("Loading������");
		label.setForeground(Color.BLUE);
		label.setBackground(Color.WHITE);
		win.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-200)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-32)/2,200,32);
		win.setVisible(true);
		win.add(label);
	}
}*/
