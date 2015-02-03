import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class testframe {
	private JPasswordField txtPwd;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(){
		JFrame checkPwd=new JFrame();
		checkPwd.setSize(219,155);
		
		JPanel panel = new JPanel();
		checkPwd.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(16, 43, 170, 21);
		panel.add(txtPwd);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u67E5\u5377\u53E3\u4EE4\uFF1A");
		label.setBounds(16, 14, 109, 15);
		panel.add(label);
		
		JButton btnOk = new JButton("\u786E\u8BA4");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(55, 78, 93, 23);
		panel.add(btnOk);
	}
}
