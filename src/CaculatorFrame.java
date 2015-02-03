import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class CaculatorFrame implements ActionListener {
	double num1;
	double num2;
	String str1="";
	String str2="";
	double result;
	int fh =0;
	String operation;
	JFrame f;
	JTextField nametxt ;
	public void Caculator(){
		JFrame f = new JFrame("Caculator");
		f.setResizable(false);
		f.setLayout(null);
		f.setVisible(false);
		f.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-420)/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-350)/2,420,350);
		JPanel p = new JPanel();
		p.setBounds(0,70,415,252);
		p.setBackground(Color.CYAN);
		p.setLayout(new GridLayout(5,5,1,1));
		
		nametxt = new JTextField("");
		nametxt.setEditable(false);
		nametxt.setBackground(Color.BLACK);
		nametxt.setForeground(Color.WHITE);
		nametxt.setBounds(1,1,413,67);
		nametxt.setFont(new Font("Monospaced", Font.BOLD, 17));
		nametxt.setEditable(false);
		
		JPanel p2 = new JPanel();
		p2.setBounds(0,0,415,70);
		p2.setLayout(null);
		p2.setBackground(Color.CYAN);
		p2.add(nametxt);
		f.add(p2);
		f.add(p);
		//添加并定义计算器按键
		JButton b1 = new JButton("π");
		b1.addActionListener(this);
		b1.setFont(new Font("Monospaced", Font.BOLD, 17));
		b1.setBackground(Color.GRAY);
		b1.setForeground(Color.BLUE);
		
		JButton b2 = new JButton("√x");
		b2.addActionListener(this);
		b2.setFont(new Font("Monospaced", Font.BOLD, 16));
		b2.setBackground(Color.GRAY);
		b2.setForeground(Color.BLUE);
		
		JButton b3 = new JButton("1/x");
		b3.addActionListener(this);
		b3.setFont(new Font("Monospaced", Font.BOLD, 16));
		b3.setBackground(Color.GRAY);
		b3.setForeground(Color.BLUE);
		
		JButton b4 = new JButton("CLR");
		b4.addActionListener(this);
		b4.setFont(new Font("Monospaced", Font.BOLD, 16));
		b4.setBackground(Color.GRAY);
		b4.setForeground(Color.RED);
		
		JButton b5 = new JButton("7");
		b5.addActionListener(this);
		b5.setFont(new Font("Monospaced", Font.BOLD, 17));
		b5.setBackground(Color.GRAY);
		b5.setForeground(Color.YELLOW);
		
		JButton b6 = new JButton("8");
		b6.addActionListener(this);
		b6.setFont(new Font("Monospaced", Font.BOLD, 17));
		b6.setBackground(Color.GRAY);
		b6.setForeground(Color.YELLOW);
		
		JButton b7 = new JButton("9");
		b7.addActionListener(this);
		b7.setFont(new Font("Monospaced", Font.BOLD, 17));
		b7.setBackground(Color.GRAY);
		b7.setForeground(Color.YELLOW);
		
		JButton b8 = new JButton("/");
		b8.addActionListener(this);
		b8.setFont(new Font("Monospaced", Font.BOLD, 16));
		b8.setBackground(Color.GRAY);
		b8.setForeground(Color.GREEN);
		
		JButton b9 = new JButton("4");
		b9.addActionListener(this);
		b9.setFont(new Font("Monospaced", Font.BOLD, 17));
		b9.setBackground(Color.GRAY);
		b9.setForeground(Color.YELLOW);
		
		JButton b10 = new JButton("5");
		b10.addActionListener(this);
		b10.setFont(new Font("Monospaced", Font.BOLD, 17));
		b10.setBackground(Color.GRAY);
		b10.setForeground(Color.YELLOW);
		
		JButton b11 = new JButton("6");
		b11.addActionListener(this);
		b11.setFont(new Font("Monospaced", Font.BOLD, 17));
		b11.setBackground(Color.GRAY);
		b11.setForeground(Color.YELLOW);
		
		JButton b12 = new JButton("*");
		b12.addActionListener(this);
		b12.setFont(new Font("Monospaced", Font.BOLD, 16));
		b12.setBackground(Color.GRAY);
		b12.setForeground(Color.GREEN);
		
		JButton b13 = new JButton("1");
		b13.addActionListener(this);
		b13.setFont(new Font("Monospaced", Font.BOLD, 17));
		b13.setBackground(Color.GRAY);
		b13.setForeground(Color.YELLOW);
		
		JButton b14 = new JButton("2");
		b14.addActionListener(this);
		b14.setFont(new Font("Monospaced", Font.BOLD, 17));
		b14.setBackground(Color.GRAY);
		b14.setForeground(Color.YELLOW);
		
		JButton b15 = new JButton("3");
		b15.addActionListener(this);
		b15.setFont(new Font("Monospaced", Font.BOLD, 17));
		b15.setBackground(Color.GRAY);
		b15.setForeground(Color.YELLOW);
		
		JButton b16 = new JButton("-");
		b16.addActionListener(this);
		b16.setFont(new Font("Monospaced", Font.BOLD, 16));
		b16.setBackground(Color.GRAY);
		b16.setForeground(Color.GREEN);
		
		JButton b17 = new JButton("0");
		b17.addActionListener(this);
		b17.setFont(new Font("Monospaced", Font.BOLD, 17));
		b17.setBackground(Color.GRAY);
		b17.setForeground(Color.YELLOW);
		
		JButton b18 = new JButton(".");
		b18.addActionListener(this);
		b18.setFont(new Font("Monospaced", Font.BOLD, 17));
		b18.setBackground(Color.GRAY);
		b18.setForeground(Color.YELLOW);
		
		JButton b19 = new JButton("=");
		b19.addActionListener(this);
		b19.setFont(new Font("Monospaced", Font.BOLD, 16));
		b19.setBackground(Color.GRAY);
		b19.setForeground(Color.GREEN);
		
		JButton b20 = new JButton("+");
		b20.addActionListener(this);
	    b20.setFont(new Font("Monospaced", Font.BOLD, 16));
		b20.setBackground(Color.GRAY);
		b20.setForeground(Color.GREEN);
		
		JButton b21 = new JButton("DEL");
		b21.addActionListener(this);
		b21.setFont(new Font("Monospaced", Font.BOLD, 16));
		b21.setBackground(Color.GRAY);
		b21.setForeground(Color.RED);
		
		JButton b22 = new JButton("ln");
		b22.addActionListener(this);
		b22.setFont(new Font("Monospaced", Font.BOLD, 16));
		b22.setBackground(Color.GRAY);
		b22.setForeground(Color.BLUE);
		
		JButton b23 = new JButton("log");
		b23.addActionListener(this);
		b23.setFont(new Font("Monospaced", Font.BOLD, 16));
		b23.setBackground(Color.GRAY);
		b23.setForeground(Color.BLUE);
		
		JButton b24 = new JButton("sin");
		b24.addActionListener(this);
		b24.setFont(new Font("Monospaced", Font.BOLD, 16));
		b24.setBackground(Color.GRAY);
		b24.setForeground(Color.BLUE);
		
		JButton b25 = new JButton("cos");
		b25.addActionListener(this);
		b25.setFont(new Font("Monospaced", Font.BOLD, 16));
		b25.setBackground(Color.GRAY);
		b25.setForeground(Color.BLUE);
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b21);
		p.add(b5);
		p.add(b6);
		p.add(b7);
		p.add(b8);
		p.add(b22);
		p.add(b9);
		p.add(b10);
		p.add(b11);
		p.add(b12);
		p.add(b23);
		p.add(b13);
		p.add(b14);
		p.add(b15);
		p.add(b16);
		p.add(b24);
		p.add(b17);
		p.add(b18);
		p.add(b19);
		p.add(b20);
		p.add(b25);
		f.setVisible(true);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.dispose();*/
	}
	public void actionPerformed(ActionEvent e){
		JButton btn = (JButton)e.getSource();
		String text = nametxt.getText();     //设置backspace键
		if (btn.getText()=="DEL"){
			
			if(text.length()==0){
				nametxt.setText("");
			}
			else{
				int i=text.length();
				text=text.substring(0, i-1);
				nametxt.setText(text);
				if(fh==0) str1=nametxt.getText();
				else str2=nametxt.getText();
			}
		}
		else if(btn.getText()=="CLR"){   //设置ce 和  c键
			nametxt.setText("");
			str1="";
			str2="";
			fh=0;
		}
		else if(btn.getText()=="1/x"){
			if(str1==""){}
			else if(fh==0){
				num1=Double.parseDouble(str1);
				result=1/num1;
				nametxt.setText(String.valueOf(result));
			}
			else{
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else{
				fh=0;
				num2=Double.parseDouble(str2);
				result=1/num2;
				nametxt.setText(String.valueOf(result));
				str2="";
				}
			}
		}
		else if(btn.getText()=="√x"){
			if(str1==""){}
			else if(fh==0){
				str1=nametxt.getText();
				num1=Double.parseDouble(str1);
				result=Math.sqrt(num1);
				nametxt.setText(String.valueOf(result));
			}
				else{
					if(str2==""){
						nametxt.setText("ERRO");
						fh=0;
						str1="";
					}
					else{
				fh=0;
				num2=Double.parseDouble(str2);
				result=Math.sqrt(num2);
				nametxt.setText(String.valueOf(result));
				str2="";
					}
			}
		}
		else if(btn.getText()=="sin"){
			if(str1==""){}
			else if(fh==0){
				str1=nametxt.getText();
				num1=Double.parseDouble(str1);
				result=Math.sin(num1);
				nametxt.setText(String.valueOf(result));
			}
			else{
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else{
				fh=0;
				num2=Double.parseDouble(str2);
				result=Math.sin(num2);
				nametxt.setText(String.valueOf(result));
				str2="";
				}
			}
		}
		else if(btn.getText()=="cos"){
			if(str1==""){}
			else if(fh==0){
				str1=nametxt.getText();
				num1=Double.parseDouble(str1);
				result=Math.cos(num1);
				nametxt.setText(String.valueOf(result));
			}
			else{
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else{
				fh=0;
				num2=Double.parseDouble(str2);
				result=Math.cos(num2);
				nametxt.setText(String.valueOf(result));
				str2="";
				}
			}
		}
		else if(btn.getText()=="ln"){
			if(str1==""){}
			else if(fh==0){
				str1=nametxt.getText();
				num1=Double.parseDouble(str1);
				result=Math.log(num1);
				nametxt.setText(String.valueOf(result));
			}
			else{
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else{
				fh=0;
				num2=Double.parseDouble(str2);
				result=Math.log(num2);
				nametxt.setText(String.valueOf(result));
				str2="";
				}
			}
		}
		else if(btn.getText()=="log"){
			if(str1==""){}
			else if(fh==0){
				str1=nametxt.getText();
				num1=Double.parseDouble(str1);
				result=Math.log10(num1);
				nametxt.setText(String.valueOf(result));
			}
			else{
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else{
				fh=0;
				num2=Double.parseDouble(str2);
				result=Math.log10(num2);
				nametxt.setText(String.valueOf(result));
				str2="";
				}
			}
		}
		else if(btn.getText()=="π"){
			if(fh==0){
				nametxt.setText(String.valueOf(Math.PI));
				str1=String.valueOf(Math.PI);
			}
			else if(fh==1){
				nametxt.setText(String.valueOf(Math.PI));
				str2=String.valueOf(Math.PI);
			}
			}
		else if(btn.getText()=="+"){                     //设置符号键
			if(str1==""){}
			else if(str1=="-"){
				nametxt.setText("ERRO");
			}
			else if(fh==1){
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else if(str2=="-"){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
					str2="";
				}
				else{
				num1=Double.parseDouble(str1);
				num2=Double.parseDouble(str2);
				str2="";
				if(operation=="+"){
					result=num1+num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="-"){
					result=num1-num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="*"){
					result=num1*num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="/"){
					result=num1 / num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				operation="+";
			}}
			else{
			fh =1;
			str1=nametxt.getText();
			operation="+";
		}
		}
		else if(btn.getText()=="-"){
			if(str1==""){
					str1=str1+btn.getText();
					nametxt.setText(str1);
				}
			else if(str1=="-"){
				nametxt.setText("ERRO");
			}
			else if(fh==1){
				if(str2==""){
					str2=str2+btn.getText();
					nametxt.setText(str2);
				}
				else if(str2=="-"){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
					str2="";
				}
				else{
				num1=Double.parseDouble(str1);
				num2=Double.parseDouble(str2);
				str2="";
				if(operation=="+"){
					result=num1+num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="-"){
					result=num1-num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="*"){
					result=num1*num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="/"){
					result=num1 / num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				operation="-";
				}}
			else{
			fh=1;
			str1=nametxt.getText();
			operation="-";
		}
		}
		else if(btn.getText()=="*"){
			if(str1==""){}
			else if(str1=="-"){
				nametxt.setText("ERRO");
			}
			else if(fh==1){
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else if(str2=="-"){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
					str2="";
				}
				else{
				num1=Double.parseDouble(str1);
				num2=Double.parseDouble(str2);
				str2="";
				if(operation=="+"){
					result=num1+num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="-"){
					result=num1-num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="*"){
					result=num1*num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="/"){
					result=num1 / num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				operation="*";}}
			else{
			fh=1;
			str1=nametxt.getText();
			operation="*";
		}
		}
		else if(btn.getText()=="/"){
			if(str1==""){}
			else if(str1=="-"){
					nametxt.setText("ERRO");
			}
			else if(fh==1){
				if(str2==""){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
				}
				else if(str2=="-"){
					nametxt.setText("ERRO");
					fh=0;
					str1="";
					str2="";
				}
				else{
				num1=Double.parseDouble(str1);
				num2=Double.parseDouble(str2);
				str2="";
				if(operation=="+"){
					result=num1+num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="-"){
					result=num1-num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="*"){
					result=num1*num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				else if(operation=="/"){
					result=num1 / num2;
					str1=String.valueOf(result);
					nametxt.setText(String.valueOf(result));
				}
				operation="/";}}
			else{
			fh=1;
			str1=nametxt.getText();
			operation="/";
		}  
		}                                                //设置等号输出
		else if(btn.getText()=="="){
			fh=0;
			num1=Double.parseDouble(str1);
			num2=Double.parseDouble(str2);
			str2="";
			if(operation=="+"){
				result=num1+num2;
				str1=String.valueOf(result);
				nametxt.setText(String.valueOf(result));
			}
			else if(operation=="-"){
				result=num1-num2;
				str1=String.valueOf(result);
				nametxt.setText(String.valueOf(result));
			}
			else if(operation=="*"){
				result=num1*num2;
				str1=String.valueOf(result);
				nametxt.setText(String.valueOf(result));
			}
			else if(operation=="/"){
				result=num1 / num2;
				str1=String.valueOf(result);
				nametxt.setText(String.valueOf(result));
			}
		}
		else{                                        //设置数字键
			if(fh==0){
				str1=str1+btn.getText();
				nametxt.setText(str1);
			}
			else{
				str2=str2+btn.getText();
				nametxt.setText(str2);
			}
		}
	}
}
