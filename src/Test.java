
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a={{2,2,2},
				{1,1,1}};
		int[][] b=a.clone();
//		int[][] b=a;
		b[0][0]=1;
		System.out.println(a[0][0]);
		System.out.println(a[0][1]);
		System.out.println(a[0][2]);
		System.out.println(b[0][0]);
		System.out.println(b[0][1]);
		System.out.println(b[0][2]);
		String n="hello";
		char[] m=new char[n.length()];
		n.getChars(0, n.length(), m, 0);
		System.out.println(m[0]);
		System.out.println(m[1]);
		System.out.println(m[2]);
		System.out.println(m[3]);
		System.out.println(m[4]);
		System.out.println(new String(m));
	}

}
