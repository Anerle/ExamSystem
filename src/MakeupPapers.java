import java.util.Random;
public class MakeupPapers {
	public static int time;
	private  int d;
	private  int re;
	DButil dbutil=new DButil(); 
public int getQdetails(double[] a,double [] types,int difficulty ,int totalscore,int Seconds) throws Exception{
		d=difficulty;
		time=Seconds;
        //===========================================================================================================
		//���
		double s=0;
		int n;
		double q;
		int[] k={0,0,0,0};
		double [] qnumber1=new double[10];
		int[] qnumber=new int[10];
		int[][] c =new int[4][10]; 
		int[] number=new int[4];
		int total=0;
		for(int i=0;i<4;i++){
			if(i<3){
			double num=Math.floor(10*types[i])+3;
			number[i]=(int)num;
			}
			else{
				double num=Math.floor(10*types[i]);
				number[i]=(int)num;
			}
			total=total+number[i];
		}
		//ʵ��֪ʶ��Լ���µ�����һά����
		for(int i=0;i<10;i++){              //��һ�鰴֪ʶ��������㲢��������
			qnumber1[i]=Math.floor(total*a[i]);//��һ�����ÿһ֪ʶ��ȡ��С����������Ŀ
			s=s+qnumber1[i];
		}
		q=total-s;
		n=(int)q;
		for(int i=0;i<10;i++){         
			qnumber[i]=(int)qnumber1[i];//����Ŀ������double��ת��Ϊint��
		}
		for(int i=0;i<10;i++){          //�ڶ���������ʣ���������ķ���
			if(n==0) break;
			else if(qnumber[i]==0){}
			else{
				qnumber[i]=qnumber[i]+1;
				n=n-1;
			}
		}
		//�����Ŀ����Լ����ʵ��֪ʶ����������͹�ͬԼ���µ������ά����
		do{
			for(int i=0;i<10;i++){
				if(k[0]>=number[0]){
					break;
				}
				else if(qnumber[i]==0){
				}
				else{
					qnumber[i]=qnumber[i]-1;
					c[0][i]=c[0][i]+1;
					k[0]=k[0]+1;
				}
			}
			}while(k[0]<number[0]);
		do{
			for(int i=0;i<10;i++){
				if(k[1]>=number[1]){
					break;
				}
				else if(qnumber[i]==0){
				}
				else{
					qnumber[i]=qnumber[i]-1;
					c[1][i]=c[1][i]+1;
					k[1]=k[1]+1;
				}
			}
			}while(k[1]<number[1]);
		do{
			for(int i=0;i<10;i++){
				if(k[2]>=number[2]){
					break;
				}
				else if(qnumber[i]==0){
				}
				else{
					qnumber[i]=qnumber[i]-1;
					c[2][i]=c[2][i]+1;
					k[2]=k[2]+1;
				}
			}
			}while(k[2]<number[2]);
	   do{
		    for(int i=0;i<10;i++){
			  if(k[3]>=number[3]){
				break;
			}
			else if(qnumber[i]==0){
			}
			else{
				qnumber[i]=qnumber[i]-1;
				c[3][i]=c[3][i]+1;
				k[3]=k[3]+1;
			}
		}
		}while(k[3]<number[3]);
	   //=====================================================================================================================
	   //=====================================================================================================================
	  try{ 
		  MakeupPapers makeuppapers=new MakeupPapers();
		 re= makeuppapers.buidPapers(c,a,types,totalscore,number);    //����������������ݿ��ȡ���⣬����Ծ��齨���������ݿⱣ��
		 }catch (Exception e){
			  e.printStackTrace();
		  }
	  return re;             //�����Ծ��������Ŀ����
	}
	public int buidPapers(int[][] c,double[] a,double[] types,int totalscore,int[] num) throws Exception{
		String[]Qnumber=new String[20];
		Random random= new Random();
		int k=0;
		int dif=0;
		//����Ѷ�Լ����������ӣ�ʵ���Ѷȡ�֪ʶ�㡢����Լ���µ�������ά���䣬���ݴ˴�������ѡ����
		for(int i=0;i<4;i++){
			for(int j=0;j<10;j++){
				if(c[i][j]==0){}
				else{
					do{
					int number=0;
					if(d==0){      //���Ծ��Ѷ�Ϊ0ʱ����ȡ�Ѷ�Ϊ0~1������
						dif=random.nextInt(2);
					}
					else if(d==4){ //���Ծ��Ѷ�Ϊ4ʱ����ȡ�Ѷ�Ϊ3~4������
						dif=3+random.nextInt(2);
					}
					else{         //���Ծ��Ѷ�Ϊiʱ����ȡ�Ѷ�Ϊi-1~i+1������
							dif=d-1+random.nextInt(3);
					}
					String[] questions=dbutil.selectByDegree(dif,j,i);//��������ȡ��������������ŷ���string����
					if(questions.length==0)break;
					else{
						int m=0;
						number=random.nextInt(questions.length); //�����������ţ���ȡ�ض�����
					    m=number;
					    while(dbutil.getFlag(questions[number])==1){//�������ѱ�ѡȡʱ��˳���ȡ��һ�����
					    	/*m=m+1;
					    	if(m>=5)break;
					        else{number=random.nextInt(questions.length);}*/
					    	number=(number+1)%questions.length;   //ͨ��ȡ�ౣ֤ѡȡ�����ⲻ�ᳬ������Ҫ�����������
					    	System.out.println(number);
					    	if(number==m)break;
					    	};
					Qnumber[k]=questions[number];        //�������ȡ�����⣬�������ʶλ��1
					dbutil.setFlag(questions[number], 1);
					k=k+1;
					c[i][j]=c[i][j]-1;}}while(c[i][j]>0);
				}
			}
		}
		try{for(int i=0;i<20;i++){         //�Ծ������ȡ��ɣ�����������ı�־λ��ԭΪ0
		  if(Qnumber[i]==null){}
		  else{
			dbutil.setFlag(Qnumber[i],0);}
		}}catch(Exception e2){
			e2.printStackTrace();
		}
		String p=TimeUtils.GetTime(1);    //����Ծ��齨��Ӧ�ı�׼ʱ�䣬��Ϊ�Ծ��
	    int length=0;
		for(int i=0;i<10;i++){
			if(a[i]==0){}
			else{
				length++;
			}
		}
		int[] chapters=new int[length];//���Ծ����������֪ʶ���������鱣��
		int flag=0;
		for(int i=0;i<10;i++){
			if(a[i]==0){}
			else{
				chapters[flag]=i;
				flag++;
			}
		}
		
		double[] score=new double[4];
		for(int i=0;i<4;i++){         //���ֺܷ�ÿ������ռ����������õ�ÿ�����͵ķ������������鱣��
			score[i]=types[i]*totalscore/num[i];
			System.out.println("score[i]"+score[i]);
		}
		System.out.println("tscore"+totalscore);
		int count=dbutil.addPaper(p, Qnumber,totalscore,time,score,d,chapters);
		return count;
	}
}