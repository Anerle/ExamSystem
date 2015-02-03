import javax.swing.JTable;

public class MyTable extends JTable{
	public boolean isCellEditable(int row,int col)
	{
		return false;
	}
}
