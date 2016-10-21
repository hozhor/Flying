
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Color;

class J_Panel extends JPanel implements ActionListener
{
  BufferedImage m_images[]=new BufferedImage[17];
	String m_items[]={"blue.PNG","red.PNG","yellow.PNG","green.PNG","center.PNG",
		                "bluep.PNG","redp.PNG","yellowp.PNG","greenp.PNG",
		                "toRight.PNG","toDown.PNG","toLeft.PNG","toUp.PNG",
		                "togreen.PNG","toblue.PNG","tored.PNG","toyellow.PNG"};
	Button ranBlue=new Button("蓝色色子");
	Button ranRed=new Button("红色色子");
	Button ranYellow=new Button("黄色色子");
	Button ranGreen=new Button("绿色色子");
	Button playButton=new Button("开始游戏");
	Button restartButton=new Button("重新开始");
	Button pauseButton=new Button("暂停");
	Button startButton=new Button("启动");
	TextField textBlue=new TextField(2);
	TextField textRed=new TextField(2);
	TextField textYellow=new TextField(2);
	TextField textGreen=new TextField(2);
	Button qi[][]={{new Button("B1"),new Button("B2"),new Button("B3"),new Button("B4")},
		             {new Button("R1"),new Button("R2"),new Button("R3"),new Button("R4")},
		             {new Button("Y1"),new Button("Y2"),new Button("Y3"),new Button("Y4")},
		             {new Button("G1"),new Button("G2"),new Button("G3"),new Button("G4")}};
	Button xianshi=new Button("显示");
	int num,bluenum,rednum,yellownum,greennum;
	int x=200;
	int y=80;
	int len=40;
	int X=x+5;
	int Y=y+5;
	int xo=0;
	int yo=1;
	Graphics gg;
	
	int blue=0;
	int red=1;
	int yellow=2;
	int green=3;
	int but;
  int cn[][]={{100,100,100,100},{100,100,100,100},{100,100,100,100},{100,100,100,100}};//代表4种旗子的4个飞机的位置
  int youxian[][]={{3,3,3,3},{3,3,3,3},{3,3,3,3},{3,3,3,3}};//每个旗子的优先级
  char blueend[]={'b','b','b','b'};
  char redend[]={'r','r','r','r'};
  char yellowend[]={'y','y','y','y'};
  char greenend[]={'g','g','g','g'};
  int SX[][]=new int[4][4];
  int SY[][]=new int[4][4];
  boolean bool[][]=new boolean[4][4];
  
	public J_Panel()
	{
	  set_images();
	  addButtons();
	}//构造方法J_Panel结束
	
	public void set_images()
	{
	  try
		{
		  File f[]=new File[17];
		  for(int i=0;i<17;i++)
		  {
		  	f[i]=new File(m_items[i]);
		  	m_images[i]=ImageIO.read(f[i]);//m_images是String类型的
		  }
		}
		catch(Exception e)
		{
		  System.err.println("发生异常:"+e);
		  e.printStackTrace();
		}
	}//方法setimage结束
	  
	public void addButtons()
	{
		add(xianshi);
		add(playButton);
		add(restartButton);
		add(pauseButton);
		add(startButton);
		add(ranBlue);
		add(textBlue);
		add(ranRed);
		add(textRed);
		add(ranYellow);
		add(textYellow);
		add(ranGreen);
		add(textGreen);
		for(int i=0;i<4;i++)
		  for(int j=0;j<4;j++)
		  {
		  	add(qi[i][j]);
		  	switch(i)
		  	{
		  		case 0:
		  		  qi[i][j].setBackground(Color.blue);
		  		  break;
		  		case 1:
		  		  qi[i][j].setBackground(Color.red);
		  		  break;
		  		case 2:
		  		  qi[i][j].setBackground(Color.yellow);
		  		  break;
		  		case 3:
		  		  qi[i][j].setBackground(Color.green);
		  		  break;
		  	}
		  }
		xianshi.addActionListener(this);
		ranBlue.addActionListener(this);
		ranRed.addActionListener(this);
		ranYellow.addActionListener(this);
		ranGreen.addActionListener(this);
		playButton.addActionListener(this);
		pauseButton.addActionListener(this);
		startButton.addActionListener(this);
		restartButton.addActionListener(this);
		for(int i=0;i<4;i++)
		  for(int j=0;j<4;j++)
		  {
		  	qi[i][j].addActionListener(this);
		  }
		  
		for(int m=0;m<4;m++)
  		  for(int n=0;n<4;n++)
  		  {
  		  	qi[m][n].setEnabled(false);
  		  }
  	restartButton.setEnabled(false);
  	ranBlue.setEnabled(false);
  	ranRed.setEnabled(false);
  	ranYellow.setEnabled(false);
  	ranGreen.setEnabled(false);
  	pauseButton.setEnabled(false);
  	startButton.setEnabled(false);
		
	}//方法addButton结束
	 
	protected void paintComponent(Graphics g)//将各个旗子绘制到相应的位置
	{
		g.drawImage(m_images[13],x+len,y+len*3,len*5,len,this);//len=40
        g.drawImage(m_images[14],x+len*7,y+len*5,len,len*5,this);
        g.drawImage(m_images[15],x+len,y+len*11,len*5,len,this);
        g.drawImage(m_images[16],x-len,y+len*5,len,len*5,this);
		
	  g.drawImage(m_images[3],x,y+len*3,len,len,this);
	  g.drawImage(m_images[0],x,y+len*2,len,len,this);
      g.drawImage(m_images[1],x,y+len,len,len,this);
	  g.drawImage(m_images[2],x,y,len,len,this);
	  
	  g.drawImage(m_images[3],x+len,y,len,len,this);
	  g.drawImage(m_images[0],x+len*2,y,len,len,this);
      g.drawImage(m_images[1],x+len*3,y,len,len,this);
      g.drawImage(m_images[2],x+len*4,y,len,len,this);
      g.drawImage(m_images[3],x+len*5,y,len,len,this);
      g.drawImage(m_images[0],x+len*6,y,len,len,this);
      
      g.drawImage(m_images[1],x+len*6,y+len,len,len,this);
      g.drawImage(m_images[2],x+len*6,y+len*2,len,len,this);
      g.drawImage(m_images[3],x+len*6,y+len*3,len,len,this);
      g.drawImage(m_images[1],x+len*3,y+len,len,len,this);
      
      g.drawImage(m_images[1],x+len*3,y+len*2,len,len,this);
      g.drawImage(m_images[1],x+len*3,y+len*3,len,len,this);
      g.drawImage(m_images[1],x+len*3,y+len*4,len,len,this);
      g.drawImage(m_images[1],x+len*3,y+len*5,len,len,this);
    
	  g.drawImage(m_images[0],x+len*7,y+len*4,len,len,this);
      g.drawImage(m_images[1],x+len*8,y+len*4,len,len,this);
	  g.drawImage(m_images[2],x+len*9,y+len*4,len,len,this);
	  g.drawImage(m_images[3],x+len*10,y+len*4,len,len,this);
	  
	  g.drawImage(m_images[0],x+len*10,y+len*5,len,len,this);
      g.drawImage(m_images[1],x+len*10,y+len*6,len,len,this);
      g.drawImage(m_images[2],x+len*10,y+len*7,len,len,this);//
      g.drawImage(m_images[3],x+len*10,y+len*8,len,len,this);
      g.drawImage(m_images[0],x+len*10,y+len*9,len,len,this);
      g.drawImage(m_images[1],x+len*10,y+len*10,len,len,this);
      
      g.drawImage(m_images[2],x+len*9,y+len*10,len,len,this);
      g.drawImage(m_images[3],x+len*8,y+len*10,len,len,this);
      g.drawImage(m_images[0],x+len*7,y+len*10,len,len,this);//
      
      g.drawImage(m_images[2],x+len*9,y+len*7,len,len,this);
      g.drawImage(m_images[2],x+len*8,y+len*7,len,len,this);
      g.drawImage(m_images[2],x+len*7,y+len*7,len,len,this);
      g.drawImage(m_images[2],x+len*6,y+len*7,len,len,this);
      g.drawImage(m_images[2],x+len*5,y+len*7,len,len,this);
    
      g.drawImage(m_images[1],x+len*6,y+len*11,len,len,this);
	  g.drawImage(m_images[2],x+len*6,y+len*12,len,len,this);
	  g.drawImage(m_images[3],x+len*6,y+len*13,len,len,this);
	  g.drawImage(m_images[0],x+len*6,y+len*14,len,len,this);
	  
      g.drawImage(m_images[1],x+len*5,y+len*14,len,len,this);
      g.drawImage(m_images[2],x+len*4,y+len*14,len,len,this);
      g.drawImage(m_images[3],x+len*3,y+len*14,len,len,this);//
      g.drawImage(m_images[0],x+len*2,y+len*14,len,len,this);
      g.drawImage(m_images[1],x+len*1,y+len*14,len,len,this);
      
      g.drawImage(m_images[2],x,y+len*14,len,len,this);
      g.drawImage(m_images[3],x,y+len*13,len,len,this);
      g.drawImage(m_images[0],x,y+len*12,len,len,this);
      g.drawImage(m_images[1],x,y+len*11,len,len,this);
      
      g.drawImage(m_images[3],x+len*3,y+len*13,len,len,this);
      g.drawImage(m_images[3],x+len*3,y+len*12,len,len,this);
      g.drawImage(m_images[3],x+len*3,y+len*11,len,len,this);
      g.drawImage(m_images[3],x+len*3,y+len*10,len,len,this);
      g.drawImage(m_images[3],x+len*3,y+len*9,len,len,this);
        
	  g.drawImage(m_images[2],x-len,y+len*10,len,len,this);
	  g.drawImage(m_images[3],x-len*2,y+len*10,len,len,this);
	  g.drawImage(m_images[0],x-len*3,y+len*10,len,len,this);
      g.drawImage(m_images[1],x-len*4,y+len*10,len,len,this);
      g.drawImage(m_images[2],x-len*4,y+len*9,len,len,this);
      g.drawImage(m_images[3],x-len*4,y+len*8,len,len,this);//
      g.drawImage(m_images[0],x-len*4,y+len*7,len,len,this);
      g.drawImage(m_images[1],x-len*4,y+len*6,len,len,this);
      g.drawImage(m_images[2],x-len*4,y+len*5,len,len,this);
      g.drawImage(m_images[3],x-len*4,y+len*4,len,len,this);
      g.drawImage(m_images[0],x-len*3,y+len*4,len,len,this);
      g.drawImage(m_images[1],x-len*2,y+len*4,len,len,this);
      g.drawImage(m_images[2],x-len*1,y+len*4,len,len,this);
      g.drawImage(m_images[0],x-len*3,y+len*7,len,len,this);
      g.drawImage(m_images[0],x-len*2,y+len*7,len,len,this);
      g.drawImage(m_images[0],x-len*1,y+len*7,len,len,this);
      g.drawImage(m_images[0],x,y+len*7,len,len,this);
      g.drawImage(m_images[0],x+len,y+len*7,len,len,this);//结束画格子
    
      g.drawImage(m_images[4],x+len*2,y+len*6,len*3,len*3,this);
      g.drawImage(m_images[5],x-len*4,y,len*2,len*2,this);
      g.drawImage(m_images[6],x+len*9,y,len*2,len*2,this);
      g.drawImage(m_images[7],x+len*9,y+len*13,len*2,len*2,this);
      g.drawImage(m_images[8],x-len*4,y+len*13,len*2,len*2,this);
    
   
      g.drawImage(m_images[9],x-len*2,y+len*14,len*2,len,this);
      g.drawImage(m_images[10],x-len*4,y+len*2,len,len*2,this);
      g.drawImage(m_images[11],x+len*7,y,len*2,len,this);
      g.drawImage(m_images[12],x+len*10,y+len*11,len,len*2,this);
	}//方法paintComponent结束
	  
	public void actionPerformed(ActionEvent e)//对按钮作出的反映
  {
    double i=6*Math.random();
  	num=(int)(i+1);
  	 
  	Button b=(Button)e.getSource();
  	if(b==xianshi){
  		for(int c = 0;c < 4;c++){
  			for(int id = 0;id<4;id++){
  				System.out.print(cn[c][id]+" ");
  				System.out.print(youxian[c][id]+" ");}
  			System.out.print("\n");
  		}
  	}
  	if(b==playButton)
  	{
  		
  		m_start();
  		b.setEnabled(false);
  		for(int m=0;m<4;m++)
  		  for(int n=0;n<4;n++)
  		  {
  		  	qi[m][n].setEnabled(true);
  		  }
  		restartButton.setEnabled(true);
  		ranBlue.setEnabled(true);
  		ranRed.setEnabled(true);
  		ranYellow.setEnabled(true);
  		ranGreen.setEnabled(true);
  		pauseButton.setEnabled(true);
  	}
  	else if(b==restartButton)
  	{
  		for(int m=0;m<4;m++)
  		  for(int n=0;n<4;n++)
  		  {
  		  	cn[m][n]=100;
  		  	qi[m][n].setSize(30,30);
  		  	qi[m][n].setLocation(SX[m][n],SY[m][n]);
  		  	qi[m][n].setEnabled(true);
  		  }
  	}
  	else if(b==pauseButton)
  	{
  		for(int m=0;m<4;m++)
  		  for(int n=0;n<4;n++)
  		  {
  		  	bool[m][n]=qi[m][n].isEnabled();
  		  	qi[m][n].setEnabled(false);
  		  }
  		restartButton.setEnabled(false);
  		ranBlue.setEnabled(false);
  		ranRed.setEnabled(false);
  		ranYellow.setEnabled(false);
  		ranGreen.setEnabled(false);
  		pauseButton.setEnabled(false);
  		startButton.setEnabled(true);
  	}
  	else if(b==startButton)
  	{
  		for(int m=0;m<4;m++)
  		  for(int n=0;n<4;n++)
  		  {
  		  	if(bool[m][n]==true)
  		  	  qi[m][n].setEnabled(true);
  		  }
  		restartButton.setEnabled(true);
  		ranBlue.setEnabled(true);
  		ranRed.setEnabled(true);
  		ranYellow.setEnabled(true);
  		ranGreen.setEnabled(true);
  		pauseButton.setEnabled(true);
  		startButton.setEnabled(false);
  	}
  	else if(b==ranBlue)
  	{
  	  bluenum=num;
      textBlue.setText(""+bluenum);
      but='b';
    }
    else if(b==qi[blue][0])
    {
    	qi_blue(0,b,bluenum);
    }
    else if(b==qi[blue][1])
    {
    	qi_blue(1,b,bluenum);
    }
    else if(b==qi[blue][2])
    {
    	qi_blue(2,b,bluenum);
    }
    else if(b==qi[blue][3])
    {
    	qi_blue(3,b,bluenum);
    }
  	}
  
  
  public void m_start()//设置棋子的最初位置
  {
  	for(int i=0;i<4;i++)
  	  for(int j=0;j<4;j++)
  	  {
  	  	qi[i][j].setSize(30,30);
  	  }
  	SX[blue][0]=x-len*4+5;
  	SY[blue][0]=y+5;
  	SX[blue][1]=x-len*3+5;
  	SY[blue][1]=y+5;
  	SX[blue][2]=x-len*4+5;
  	SY[blue][2]=y+5+len;
  	SX[blue][3]=x-len*3+5;
  	SY[blue][3]=y+5+len;
  	
    qi[blue][0].setLocation(x-len*4+5,y+5);
    qi[blue][1].setLocation(x-len*3+5,y+5);
    qi[blue][2].setLocation(x-len*4+5,y+len+5);
  	qi[blue][3].setLocation(x-len*3+5,y+len+5);
  	
  	SX[red][0]=x+len*9+5;
  	SY[red][0]=y+5;
  	SX[red][1]=x+len*10+5;
  	SY[red][1]=y+5;
  	SX[red][2]=x+len*9+5;
  	SY[red][2]=y+5+len;
  	SX[red][3]=x+len*10+5;
  	SY[red][3]=y+5+len;

    qi[red][0].setLocation(x+len*9+5,y+5);
    qi[red][1].setLocation(x+len*10+5,y+5);
   	qi[red][2].setLocation(x+len*9+5,y+len+5);
   	qi[red][3].setLocation(x+len*10+5,y+len+5);
   	
   	SX[yellow][0]=x+len*9+5;
   	SY[yellow][0]=y+len*13+5;
   	SX[yellow][1]=x+len*10+5;
   	SY[yellow][1]=y+len*13+5;
   	SX[yellow][2]=x+len*9+5;
   	SY[yellow][2]=y+len*14+5;
   	SX[yellow][3]=x+len*10+5;
   	SY[yellow][3]=y+len*14+5;
   
    qi[yellow][0].setLocation(x+len*9+5,y+len*13+5);
    qi[yellow][1].setLocation(x+len*10+5,y+len*13+5);
    qi[yellow][2].setLocation(x+len*9+5,y+len*14+5);
    qi[yellow][3].setLocation(x+len*10+5,y+len*14+5);
    
    SX[green][0]=x-len*4+5;
    SY[green][0]=y+len*13+5;
    SX[green][1]=x-len*3+5;
    SY[green][1]=y+len*13+5;
    SX[green][2]=x-len*4+5;
    SY[green][2]=y+len*14+5;
    SX[green][3]=x-len*3+5;
    SY[green][3]=y+len*14+5;
    
  	qi[green][0].setLocation(x-len*4+5,y+len*13+5);
    qi[green][1].setLocation(x-len*3+5,y+len*13+5);
  	qi[green][2].setLocation(x-len*4+5,y+len*14+5);
  	qi[green][3].setLocation(x-len*3+5,y+len*14+5);
  }//方法m_start结束
  
  public void tomove(int c,int n,Button b,int id){
	  /*for(int i=n;i>0;i--)
		{   if((cn[c][id]-i)<52+c*6)//若并未进入跑道
			   {cn[c][id]=cn[c][id]%52;
			    move(cn[c][id]-i,b,id,c);
			   }
		else
			move(cn[c][id]-i,b,id,c);
		    try   
		    {   
		    Thread.currentThread().sleep(520);//毫秒   
		    }   
		    catch(Exception e){} ; 
		}*/
	  for(int i = 0;i<4;i++){
		  b.setBackground(Color.BLACK);
		  try   
		    {   
		    Thread.currentThread().sleep(300);//毫秒   
		    }   
		    catch(Exception e){} ; 
		  switch(c){
		  case 0:
			  b.setBackground(Color.blue);
			  break;
		  case 1:
			  b.setBackground(Color.red);
			  break;
		  case 2:
			  b.setBackground(Color.yellow);
		      break;
		  case 3:
			  b.setBackground(Color.green);
			  break;
		  }
		  try   
		    {   
		    Thread.currentThread().sleep(300);//毫秒   
		    }   
		    catch(Exception e){} ; 
	  }
	  if(cn[c][id]<52+c*6)//若并未进入跑道
	   cn[c][id]=cn[c][id]%52;
	  move(cn[c][id],b,id,c);
  }
  public void move(int d,Button b,int id,int color)//实现每格的标号与相应坐标
  {
	  if(d>52&&d<52+color*6)
		  d%=52;
    switch(d)
    {
    	case 1:
    	  b.setLocation(X-len*4,Y+len*4);
    	  break;
    	case 2:
    	  b.setLocation(X-len*3,Y+len*4);
    	  break;
    	case 3:
    	  b.setLocation(X-len*2,Y+len*4);
    	  break;
    	case 4:
    	  b.setLocation(X-len*1,Y+len*4);
    	  break;
    	case 5:
    	  b.setLocation(X,Y+len*3);
    	  break;
    	case 6:
    	  b.setLocation(X,Y+len*2);
    	  break;
    	case 7:
    	  b.setLocation(X,Y+len*1);
    	  break;
    	case 8:
    	  b.setLocation(X,Y);
    	  break;
    	case 9:
    	  b.setLocation(X+len,Y);
    	  break;
    	case 10:
    	  b.setLocation(X+len*2,Y);
    	  break;
    	case 11:
    	  b.setLocation(X+len*3,Y);
    	  break;
    	case 12:
    	  b.setLocation(X+len*4,Y);
    	  break;
    	case 13:
    	  b.setLocation(X+len*5,Y);
    	  break;
    	case 14:
    	  b.setLocation(X+len*6,Y);
    	  break;
    	case 15:
    	  b.setLocation(X+len*6,Y+len);
    	  break;
    	case 16:
    	  b.setLocation(X+len*6,Y+len*2);
    	  break;
    	case 17:
    	  b.setLocation(X+len*6,Y+len*3);
    	  break;
    	case 18:
    	  b.setLocation(X+len*7,Y+len*4);
    	  break;
    	case 19:
    	  b.setLocation(X+len*8,Y+len*4);
    	  break;
    	case 20:
    	  b.setLocation(X+len*9,Y+len*4);
    	  break;
    	case 21:
    	  b.setLocation(X+len*10,Y+len*4);
    	  break;
    	case 22:
    	  b.setLocation(X+len*10,Y+len*5);
    	  break;
    	case 23:
    	  b.setLocation(X+len*10,Y+len*6);
    	  break;
    	case 24:
    	  b.setLocation(X+len*10,Y+len*7);
    	  break;
    	case 25:
    	  b.setLocation(X+len*10,Y+len*8);
    	  break;
    	case 26:
    	  b.setLocation(X+len*10,Y+len*9);
    	  break;
    	case 27:
    	  b.setLocation(X+len*10,Y+len*10);
    	  break;
    	case 28:
    	  b.setLocation(X+len*9,Y+len*10);
    	  break;
    	case 29:
    	  b.setLocation(X+len*8,Y+len*10);
    	  break;
    	case 30:
    	  b.setLocation(X+len*7,Y+len*10);
    	  break;
    	case 31:
    	  b.setLocation(X+len*6,Y+len*11);
    	  break;
    	case 32:
    	  b.setLocation(X+len*6,Y+len*12);
    	  break;
    	case 33:
    	  b.setLocation(X+len*6,Y+len*13);
    	  break;
    	case 34:
    	  b.setLocation(X+len*6,Y+len*14);
    	  break;
    	case 35:
    	  b.setLocation(X+len*5,Y+len*14);
    	  break;
    	case 36:
    	  b.setLocation(X+len*4,Y+len*14);
    	  break;
    	case 37:
    	  b.setLocation(X+len*3,Y+len*14);
    	  break;
    	case 38:
    	  b.setLocation(X+len*2,Y+len*14);
    	  break;
    	case 39:
    	  b.setLocation(X+len*1,Y+len*14);
    	  break;
    	case 40:
    	  b.setLocation(X,Y+len*14);
    	  break;
    	case 41:
    	  b.setLocation(X,Y+len*13);
    	  break;
    	case 42:
    	  b.setLocation(X,Y+len*12);
    	  break;
    	case 43:
    	  b.setLocation(X,Y+len*11);
    	  break;
    	case 44:
    	  b.setLocation(X-len,Y+len*10);
    	  break;
    	case 45:
    	  b.setLocation(X-len*2,Y+len*10);
    	  break;
    	case 46:
    	  b.setLocation(X-len*3,Y+len*10);
    	  break;
    	case 47:
    	  b.setLocation(X-len*4,Y+len*10);
    	  break;
    	case 48:
    	  b.setLocation(X-len*4,Y+len*9);
    	  break;
    	case 49:
    	  b.setLocation(X-len*4,Y+len*8);
    	  break;
    	case 50:
    	  b.setLocation(X-len*4,Y+len*7);
    	  break;
    	case 51:
    	  b.setLocation(X-len*4,Y+len*6);
    	  break;
    	case 52:
    	  b.setLocation(X-len*4,Y+len*5);
    	  break;
    	case 53:
    	  b.setLocation(X-len*3,Y+len*7);
    	  break;
    	case 54:
    	  b.setLocation(X-len*2,Y+len*7);
    	  break;
    	case 55:
    	  b.setLocation(X-len*1,Y+len*7);
    	  break;
    	case 56:
    	  b.setLocation(X,Y+len*7);
    	  break;
    	case 57:
    	  b.setLocation(X+len*1,Y+len*7);
    	  break;
    	case 58:
    	  b.setLocation(X+len*2,Y+len*7);
    	  if(id==0)
    	    b.setLocation(X-len*4,Y);
    	  else if(id==1)
    	    b.setLocation(X-len*3,Y);
    	  else if(id==2)
    	    b.setLocation(X-len*4,Y+len);
    	  else if(id==3)
    	    b.setLocation(X-len*3,Y+len);
    	  b.setEnabled(false);
    	    break;
    	 case 59:
    	   b.setLocation(X+len*3,Y+len);
    	   break;
    	 case 60:
    	   b.setLocation(X+len*3,Y+len*2);
    	   break;
    	 case 61:
    	   b.setLocation(X+len*3,Y+len*3);
    	   break;
    	 case 62:
    	   b.setLocation(X+len*3,Y+len*4);
    	   break;
    	 case 63:
    	   b.setLocation(X+len*3,Y+len*5);
    	   break;
    	 case 64:
    	   b.setLocation(X+len*3,Y+len*6);
    	   cn[color][id]=-1;
    	   youxian[1][id]=-1;
    	   if(id==0)
    	     b.setLocation(X+len*9,Y);
    	   else if(id==1)
    	     b.setLocation(X+len*10,Y);
    	   else if(id==2)
    	     b.setLocation(X+len*9,Y+len);
    	   else if(id==3)
    	     b.setLocation(X+len*10,Y+len);
    	   break;
    	 case 65:
    	   b.setLocation(X+len*9,Y+len*7);
    	   break;
    	 case 66:
    	   b.setLocation(X+len*8,Y+len*7);
    	   break;
    	 case 67:
    	   b.setLocation(X+len*7,Y+len*7);
    	   break;
    	 case 68:
    	   b.setLocation(X+len*6,Y+len*7);
    	   break;
    	 case 69:
    	   b.setLocation(X+len*5,Y+len*7);
    	   break;
    	 case 70:
    	   b.setLocation(X+len*4,Y+len*7);
    	   //b.setEnabled(false);
    	   youxian[2][id]=-1;
    	   if(id==0)
    	     b.setLocation(X+len*9,Y+len*13);
    	   else if(id==1)
    	     b.setLocation(X+len*10,Y+len*13);
    	   else if(id==2)
    	     b.setLocation(X+len*9,Y+len*14);
    	   else if(id==3)
    	     b.setLocation(X+len*10,Y+len*14);
    	   break;
    	 case 71:
    	   b.setLocation(X+len*3,Y+len*13);
    	   break;
    	 case 72:
    	   b.setLocation(X+len*3,Y+len*12);
    	   break;
    	 case 73:
    	   b.setLocation(X+len*3,Y+len*11);
    	   break;
    	 case 74:
    	   b.setLocation(X+len*3,Y+len*10);
    	   break;
    	 case 75:
    	   b.setLocation(X+len*3,Y+len*9);
    	   break;
    	 case 76:
    	   b.setLocation(X+len*3,Y+len*8);
    	   //b.setEnabled(false);
    	   youxian[3][id]=-1;
    	   if(id==0)
    	     b.setLocation(X-len*4,Y+len*13);
    	   else if(id==1)
    	     b.setLocation(X-len*3,Y+len*13);
    	   else if(id==2)
    	     b.setLocation(X-len*4,Y+len*14);
    	   else if(id==3)
    	     b.setLocation(X-len*3,Y+len*14);
    	   break;
    }
  }//方法move结束
  public void qi_blue(int id,Button qi_b,int bluenum)//蓝色棋子
  {
  	if(but=='b')
  	{
  		if(cn[blue][id]==100&&bluenum==6)//最初位置
  		{
  			qi_b.setLocation(X-len*4,Y+len*3);
  			but='e';
  			cn[blue][id]=0;
  		}
  		else if(cn[blue][id]==0)//开始位置
  		{
  			cn[blue][id]+=bluenum;
  			tomove(0,bluenum,qi_b,id);
  		
  			for(int i=1;i<4;i++)
  			  for(int j=0;j<4;j++)
  			  {
  			    if(cn[blue][id]==cn[i][j])
  			    {
  			       qi[i][j].setLocation(SX[i][j],SY[i][j]);//返回初始位置
  			       cn[i][j]=100;
  			    }
  			  }//判断是否吃棋子
  		  switch(cn[blue][id])//蓝色棋子需要飞的格子
  		  {
  			 	case 2:
  			 	case 6:
  			 	case 10:
  				case 14:
  			 	case 22:
  				case 26:
  			 	case 30:
  			 	case 34:
  			 	case 38:
  			 	case 42:
  			 	case 46:
  			 	  cn[blue][id]+=4;
  			 	  break;
  			 	case 18:
  			 	  cn[blue][id]+=12;
  			 	  break;
  			 	case 50:
  			 	  cn[blue][id]=58;
  			  	break;
  			}
  			move(cn[blue][id],qi_b,id,0);
  			but='e';
  			for(int i=1;i<4;i++)
  			  for(int j=0;j<4;j++)
  			  { 
  			    if(cn[blue][id]==cn[i][j])
  			    {
  			       qi[i][j].setLocation(SX[i][j],SY[i][j]);
  			       cn[i][j]=100;
  			    }
  			  }//判断是否吃棋子
  		}
  		else if(cn[blue][id]>0&&cn[blue][id]<59)
  		{
  			cn[blue][id]+=bluenum;
  			if(blueend[id]=='b'&&cn[blue][id]>50)
  			{
  				cn[blue][id]+=2;
  				blueend[id]='c';
  			}
  			else if(blueend[id]=='c'&&cn[blue][id]>56)
  			{
  				cn[blue][id]=(116-cn[blue][id]);
  			}
  			tomove(0,bluenum,qi_b,id);
  			move(cn[blue][id],qi_b,id,0);
  			for(int i=1;i<4;i++)
  			  for(int j=0;j<4;j++)
  			  {
  			    if(cn[blue][id]==cn[i][j])
  			    {
  			       qi[i][j].setLocation(SX[i][j],SY[i][j]);
  			       cn[i][j]=100;
  			    }
  			  }//判断是否吃棋子
  			switch(cn[blue][id])//蓝色棋子需要飞的格子
  		  {
  			 	case 2:
  			 	case 6:
  			 	case 10:
  				case 14:
  			 	case 22:
  				case 26:
  			 	case 30:
  			 	case 34:
  			 	case 38:
  			 	case 42:
  			 	case 46:
  			 	  cn[blue][id]+=4;
  			 	  break;
  			 	case 18:
  			 	  cn[blue][id]+=12;
  			 	  break;
  			 	case 50:
  			 	  cn[blue][id]=58;
  			  	break;
  			}  			
  			move(cn[blue][id],qi_b,id,0);
  		  but='e';
  		  for(int i=1;i<4;i++)
  			  for(int j=0;j<4;j++)
  			  {
  			    if(cn[blue][id]==cn[i][j])
  			    {
  			       qi[i][j].setLocation(SX[i][j],SY[i][j]);
  			       cn[i][j]=100;
  			    }
  			  }//判断是否吃棋子
  		}
  	}
    auto();
  }//方法qi_blue结束
  
  public void auto(){
       int j;
	  	for(int i=1;i<4;i++){
		    double r=6*Math.random();
		  	num=(int)(r+1);
	  		switch(i){
	  		case 1:
	  			textRed.setText(""+num);
	  		    j = panduanyouxian(1,num);
	  		    moveqi(1,j,qi[1][j],num);
	  		    break;
	  		case 2:
	  			textYellow.setText(""+num);
	  		    j = panduanyouxian(2,num);
	  		    moveqi(2,j,qi[2][j],num);
	  		    break;
	  		case 3:
	  			textGreen.setText(""+num);
	  		    j = panduanyouxian(3,num);
	  		    moveqi(3,j,qi[3][j],num);
	  		    break;
	  		}
	  	}
  }
  public int panduanyouxian(int color,int num){
	int i;
	for(i = 0;i<4;i++){
		if(cn[color][i]!=-1)
			youxian[color][i] = 3;//初值为3,到终点则初值为-1不变
		if(cn[color][i]==100&&youxian[color][i]!=-1){
			if(num==6)
    		 youxian[color][i]=4;//若摇到了6 则棋盘中旗子优先级为4
			else if(youxian[color][i]!=-1)
				youxian[color][i]=0;//否则为0
    	}//如果在机场 则默认优先级为3,若num!=6,则优先级为0
		if(cn[color][i]+num==58+6*color||cn[color][i]+num==13*color-2)
			youxian[color][i]+=3;
		if(num==6&&13*color-2==cn[color][i])
			youxian[color][i]+=3;//若可以到达终点 则优先级+3
		if(cn[color][i]<13*color-2&&cn[color][i]+num>13*color-2)
			youxian[color][i]+=2;
        int number = 0;
		if(cn[color][i]==0)
            number = color*13;
		 if(cn[color][i]!=-1&&((cn[color][i]+num+number)%4==(color+2)%4))
        	youxian[color][i]++;//如果可以跳格 则优先级加1
    	if(cn[color][i]!=-1&&(cn[color][i]+num-color*13)==18)
    		youxian[color][i]++;//如果可以跳大格 则优先级再加1
        for(int j=0;j<4;j++){
        	/*if(cn[color][i]!=-1&&cn[color][i]==cn[color][j]&&i>j){
        		youxian[color][i]++;//如果两个飞机相叠，编号大的飞机先走
  			}	*/
        	int cp=(cn[color][i]+2)%4;
        	if(cn[color][i]!=-1&&cp!=color&&cn[cp][j]<cn[color][i]&&cn[color][i]-cn[cp][j]<10
        			&&cn[color][i]-cn[cp][j]>4)
        		youxian[color][i]++;
        	//如果飞机所在的格子是别的颜色 并且身后5-10格内有该色敌机，则优先级加1
           for(int k=0;k<4;k++){
        	 if(cn[color][i]!=-1&&
        			 cn[color][i]>cn[j][k]&&cn[color][i]-cn[j][k]<7&&color!=j)
        		youxian[color][i]++;//如果某机后6格有敌机，优先级增加(快跑)
        	 if(cn[color][i]!=-1&&(cn[color][i]+number+num)%4==(color+2)%4)
        		{if(cn[color][i]!=-1&&cn[color][i]+4+num==cn[j][k]&&color!=j&&
        		(cn[color][i]+num-color*13)!=18)
        		youxian[color][i]+=2;//如果跳格后可以踩到敌机 则加2优先级
        		}
        	 if(cn[color][i]!=-1&&((cn[color][i]+num-color*13)==18)){
        		if(cn[color][i]!=-1&&cn[color][i]+num+12==cn[j][k]&&color!=j)
        			youxian[color][i]+=2;//如果跳大格后可以踩到敌机 优先级+2
        	   }
        	 if(cn[color][i]!=-1&&cn[color][i]+number+num==cn[j][k]&&color!=j)
        		youxian[color][i]+=2;//如果不可以跳格，可以踩到敌机 则加2优先级
           }       
        }
	}//优先级算法
	int max=-1,re=0;
	for(i=0;i<4;i++){
		if(youxian[color][i]>max){
			max=youxian[color][i];
		    re=i;}
	}//找到优先级最大的旗子
	return re;
  }

 
  public void eat(int color,int id){
	  for(int i=0;i<4;i++)
			  for(int j=0;j<4;j++)
			  {
			    if(cn[color][id]==cn[i][j]&&i!=color)
			    {
			       qi[i][j].setLocation(SX[i][j],SY[i][j]);
			       cn[i][j]=100;
			    }
			  }//判断是否吃棋子
  }
  public void toFly(int color,int id){
	  if(cn[color][id]<53){
	  if(cn[color][id]==color*13-2||(color==0&&cn[color][id]==50))
		  cn[color][id]=52+(color+1)*6;
	  else if(cn[color][id]==5+(color+1)*13||(cn[color][id]==5&&color==3))
	  {
		  cn[color][id]+=12;
		  if(cn[color][id]>52)
			  cn[color][id]=cn[color][id]%52;
	  }
	  else if(cn[color][id]%4==((color+2)%4))
	  {
		  cn[color][id]+=4;	  
		  System.out.println(color+"jump!");}
	  if(cn[color][id]>52&&cn[color][id]!=color*6+58)
	  {
		  cn[color][id]=cn[color][id]%52;
	  }
	  }
  }
  
  public void caozuo(int color,int id,Button qi,int num){
	    tomove(color,num-1,qi,id);//移动旗子
		eat(color,id);//判断是否有吃子
		toFly(color,id);//判断要飞的格子
		move(cn[color][id],qi,id,color);//移动飞行后的飞机
		eat(color,id);//判断是否有吃子
  }
  
  public void moveqi(int color,int id,Button qi,int num)//qi=qi[color][id]
  {
    
    	if(cn[color][id]==100&&num==6)//最初位置
    	{
    		switch(color){
    		case 1:
    			     qi.setLocation(X+len*7,Y);
    		         break;
    		case 2:
    			    qi.setLocation(X+len*10,Y+len*11);
    		        break;
    		case 3:
    			    qi.setLocation(X-len,Y+len*14);
    		        break; 
    		}
    		cn[color][id]=0;
  	  }
  	  else if(cn[color][id]==0)//开始位置
  	  {
  	  	    cn[color][id]+=color*13;//初始位置0+color*13
  			cn[color][id]+=num;
  			caozuo(color,id,qi,num);//进行操作	
  	  }
  	  else if((cn[color][id]>0)&&(cn[color][id]<13*color-2)
  			  ||(cn[color][id]==13*color-2))
  	  {//如果一开始在转过一圈后
  		  if(cn[color][id]+num>13*color-2)
  			  cn[color][id]=cn[color][id]+num-13*color+2+52+color*6;
  		  //如果到了终点跑道 则直接给大于52的值
  		  else
  			  cn[color][id]+=num;

  	  //否则直接相加
  		caozuo(color,id,qi,num);//进行操作
  	  }
  	  else if((cn[color][id]>13*color-2)&&(cn[color][id]<52))
  	  { //还在第一段时
  		  if(cn[color][id]+num<52){
  			  cn[color][id]+=num;
  			caozuo(color,id,qi,num);//进行操作
  		  }
  		  else if(cn[color][id]+num>52){
  			  cn[color][id]+=num;
  			  cn[color][id]=cn[color][id]%52;
  			  caozuo(color,id,qi,num);//进行操作*/
  		  }
  	  }
  	  else if((cn[color][id]>52+6*color)&&(cn[color][id]<58+6*color)){
  		  //在跑道上时
  		  cn[color][id]+=num;
  		  if(cn[color][id]>58+6*color){
  			  cn[color][id]=58+6*color-(cn[color][id]-(58+6*color));
  		  }//如果超出范围
  		  caozuo(color,id,qi,num);
  	  }
  	  else if(cn[color][id]==color*6+58){
  		    caozuo(color,id,qi,num);
    		youxian[color][id]=-1;//到终点后棋子优先级设定为-1
    		cn[color][id]=-1;
    		qi.setBackground(Color.white);
    	}
}
  //方法moveqi结束

}//类J_Panel结束


public class Play {	
	public static void main(String args[])
	{
		JFrame f=new JFrame("飞行棋游戏");
		Container c=f.getContentPane();
		c.add(new J_Panel(),BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(690,740);
		f.setVisible(true);
	}//方法main结束
}//类Play结束