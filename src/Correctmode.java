import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Correctmode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame Mode=new JFrame();
		Mode.setSize(228,213);
		
		JPanel panel = new JPanel();
		Mode.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BF7\u9009\u62E9\u8981\u6279\u6539\u7684\u9898\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane.setBounds(10, 13, 192, 113);
		panel.add(layeredPane);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		final JSpinner spinnerTianNumber = new JSpinner();
		
		JRadioButton radioButtonALL = new JRadioButton("\u5168  \u90E8",true);
		radioButtonALL.setBounds(24, 26, 70, 23);
		radioButtonALL.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				spinnerTianNumber.setEnabled(false);
			}
			
		});
		layeredPane.add(radioButtonALL);
		
		JRadioButton radioButtonTian = new JRadioButton("\u586B\u7A7A\u9898  \u7B2C",false);
		radioButtonTian.setBounds(24, 51, 85, 23);
		radioButtonTian.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				spinnerTianNumber.setEnabled(true);
			}
			
		});
		layeredPane.add(radioButtonTian);
		
		buttonGroup.add(radioButtonALL);
		buttonGroup.add(radioButtonTian);
		
		JRadioButton radioButtonJian = new JRadioButton("\u7B80\u7B54\u9898  \u7B2C", false);
		radioButtonJian.setBounds(24, 76, 85, 23);
		layeredPane.add(radioButtonJian);
		
		spinnerTianNumber.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		spinnerTianNumber.setBounds(113, 51, 35, 22);
		spinnerTianNumber.setEnabled(false);
		layeredPane.add(spinnerTianNumber);
		
		JLabel label_1 = new JLabel("\u9898");
		label_1.setBounds(155, 55, 25, 15);
		layeredPane.add(label_1);
		
		JSpinner spinnerJianNumber = new JSpinner();
		spinnerJianNumber.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		spinnerJianNumber.setEnabled(false);
		spinnerJianNumber.setBounds(113, 76, 35, 22);
		layeredPane.add(spinnerJianNumber);
		
		JLabel label = new JLabel("\u9898");
		label.setBounds(155, 80, 25, 15);
		layeredPane.add(label);
		
		JButton btnOk = new JButton("\u786E\u5B9A");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	int 
			}
		});
		btnOk.setBounds(59, 136, 93, 23);
		panel.add(btnOk);
		Mode.setVisible(true);
		Mode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
