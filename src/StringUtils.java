
public class StringUtils {
	
	public String ReadLine(String Text, int Line){
		String line=System.getProperty("line.separator");
		String[] s=Text.split(line);
		return s[Line-1];
	}
	
}
