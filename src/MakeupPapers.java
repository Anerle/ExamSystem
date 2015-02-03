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
		//组卷
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
		//实现知识点约束下的试题一维分配
		for(int i=0;i<10;i++){              //第一遍按知识点比例计算并分配试题
			qnumber1[i]=Math.floor(total*a[i]);//第一遍分配每一知识点取最小正整数个题目
			s=s+qnumber1[i];
		}
		q=total-s;
		n=(int)q;
		for(int i=0;i<10;i++){         
			qnumber[i]=(int)qnumber1[i];//将题目个数由double型转换为int型
		}
		for(int i=0;i<10;i++){          //第二遍分配完成剩余试题量的分配
			if(n==0) break;
			else if(qnumber[i]==0){}
			else{
				qnumber[i]=qnumber[i]+1;
				n=n-1;
			}
		}
		//添加题目类型约束，实现知识点和试题类型共同约束下的试题二维分配
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
		 re= makeuppapers.buidPapers(c,a,types,totalscore,number);    //调用组卷函数，从数据库抽取试题，完成试卷组建并送入数据库保存
		 }catch (Exception e){
			  e.printStackTrace();
		  }
	  return re;             //返回试卷包含的题目总量
	}
	public int buidPapers(int[][] c,double[] a,double[] types,int totalscore,int[] num) throws Exception{
		String[]Qnumber=new String[20];
		Random random= new Random();
		int k=0;
		int dif=0;
		//完成难度约束条件的添加，实现难度、知识点、题型约束下的试题三维分配，并据此从试题库抽选试题
		for(int i=0;i<4;i++){
			for(int j=0;j<10;j++){
				if(c[i][j]==0){}
				else{
					do{
					int number=0;
					if(d==0){      //当试卷难度为0时，抽取难度为0~1的试题
						dif=random.nextInt(2);
					}
					else if(d==4){ //当试卷难度为4时，抽取难度为3~4的试题
						dif=3+random.nextInt(2);
					}
					else{         //当试卷难度为i时，抽取难度为i-1~i+1的试题
							dif=d-1+random.nextInt(3);
					}
					String[] questions=dbutil.selectByDegree(dif,j,i);//从试题库抽取满足条件的试题号放入string数组
					if(questions.length==0)break;
					else{
						int m=0;
						number=random.nextInt(questions.length); //随机产生试题号，抽取特定试题
					    m=number;
					    while(dbutil.getFlag(questions[number])==1){//当试题已被选取时，顺序抽取下一题填充
					    	/*m=m+1;
					    	if(m>=5)break;
					        else{number=random.nextInt(questions.length);}*/
					    	number=(number+1)%questions.length;   //通过取余保证选取的试题不会超出满足要求的试题总量
					    	System.out.println(number);
					    	if(number==m)break;
					    	};
					Qnumber[k]=questions[number];        //从试题库取出试题，并将其标识位置1
					dbutil.setFlag(questions[number], 1);
					k=k+1;
					c[i][j]=c[i][j]-1;}}while(c[i][j]>0);
				}
			}
		}
		try{for(int i=0;i<20;i++){         //试卷试题抽取完成，将所有试题的标志位还原为0
		  if(Qnumber[i]==null){}
		  else{
			dbutil.setFlag(Qnumber[i],0);}
		}}catch(Exception e2){
			e2.printStackTrace();
		}
		String p=TimeUtils.GetTime(1);    //获得试卷组建对应的标准时间，作为试卷号
	    int length=0;
		for(int i=0;i<10;i++){
			if(a[i]==0){}
			else{
				length++;
			}
		}
		int[] chapters=new int[length];//将试卷包含的所有知识点送入数组保存
		int flag=0;
		for(int i=0;i<10;i++){
			if(a[i]==0){}
			else{
				chapters[flag]=i;
				flag++;
			}
		}
		
		double[] score=new double[4];
		for(int i=0;i<4;i++){         //由总分和每题型所占比例，计算得到每种题型的分数并送入数组保存
			score[i]=types[i]*totalscore/num[i];
			System.out.println("score[i]"+score[i]);
		}
		System.out.println("tscore"+totalscore);
		int count=dbutil.addPaper(p, Qnumber,totalscore,time,score,d,chapters);
		return count;
	}
}