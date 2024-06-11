package Demogame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
public class  GamePlay extends JPanel implements ActionListener, KeyListener{
   
   private boolean play=false;
   private int score=0;
   private int totalBricks=21;
   private Timer timer;
   private int delay=8;
   private int ballposX=120;
   private int ballposY=350;
   private int ballXdir=-4;
   private int ballYdir=-5;
   private int playerX=350;
   private MapGenerator map;
   
   public GamePlay() {
	   addKeyListener(this);
	   setFocusable(true);
	   setFocusTraversalKeysEnabled(true);
	   timer=new Timer(delay,this);
	   timer.start();
	   map=new MapGenerator(3,7);
	   
	   
   }
   public void paint(Graphics g) {
	   //canvas black
	   g.setColor(Color.black);
	   g.fillRect(1, 1, 692,592);
	   //border
	   g.setColor(Color.yellow);
	   g.fillRect(0,0,692,3);
	   g.fillRect(0,3,3,592);
	   g.fillRect(691,3,3,592 );
	   // down paddle
	   g.setColor(Color.green);
	   g.fillRect(playerX, 550, 100,8);
	   //bricks 
	   map.draw((Graphics2D)g);
	   // ball
	   g.setColor(Color.red);
	   g.fillOval(ballposX, ballposY, 20,20);
	   g.setColor(Color.green);
	   g.setFont(new Font("serif",Font.BOLD,20));
	   g.drawString("Score :"+score, 550, 30);
	   //game over
	   if(ballposY>=570) {
		   play=false;
		   ballXdir=0;
		   ballYdir=0;
		   g.setColor(Color.white);
		   g.setFont(new Font("serif",Font.BOLD,30));
		   g.drawString("Game Over!! ,score : "+score,200,300);
		   g.setFont(new Font("serif",Font.BOLD,25));
		   g.drawString("Print enter to Restart !! ",230,350);
		   
	   }
	   if(totalBricks<=0) {
		   play=false;
		   ballXdir=0;
		   ballYdir=0;
		   g.setColor(Color.white);
		   g.setFont(new Font("serif",Font.BOLD,30));
		   g.drawString("You Won!! ,score : "+score,200,300);
		   g.setFont(new Font("serif",Font.BOLD,25));
		   g.drawString("Print enter to Restart !! ",230,350);
	   }
	   }
   private void moveLeft() {
	   //To start the game when you click Left
	   play=true;
	   // to move left
	   playerX-=20;
   }
   private void moveRight() {
	   //To start the game when you click Right
	   play=true;
	   // to move right
	   playerX+=20;
   }
   @Override
   public void keyPressed(KeyEvent e) {
	   if(e.getKeyCode() == KeyEvent.VK_LEFT) {
		   //to stop moving paddle out of frame
		   if(playerX<=0) {
			   
			   playerX=0;
		   }
		   else {
		   moveLeft();
	   }
	   }
	   if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		   if(playerX>=600) {
			   playerX=600;
		   }
		   else {
		   moveRight();
	   }
	   }
	   if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		   if(!play) {
			   score=0;
			   totalBricks=21;
			   ballposX=120;
			   ballposY=350;
			    ballXdir=-2;
			    ballYdir=-3;
			    playerX=320;
			    map=new MapGenerator(3,7);
			    
		   }
	   }
	   // to make visible keypad after moving
	   repaint();
   }

@Override
          // moving ball
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(play) {
		//  if ball touches left then we have to move it in opp direction
		if(ballposX<=0) {
			ballXdir=-ballXdir;
		}
		if(ballposY<0) {
			//it should not goo out of frame upside
			ballYdir=-ballYdir;
		}
		if(ballposX>=670) {
			ballXdir=-ballXdir;
		}
		Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
		Rectangle paddleRect=new Rectangle(playerX,550,100,8);
		
		if(ballRect.intersects(paddleRect)) {
			ballYdir=-ballYdir;
		}
		A:for(int i=0;i<map.map.length;i++) {
			for(int j=0;j<map.map[0].length;j++) {
				if(map.map[i][j]>0) {
					int width=map.brickwidth;
					int height=map.brickHeight;
					int brickposX=80+j*width;
					int brickposY=50+i*height;
					Rectangle brickRect=new Rectangle(brickposX,brickposY,width,height);
					if(ballRect.intersects(brickRect)) {
						map.setBrick(0, i, j);
						totalBricks--;
						score+=5;
						//ball intersect from left side or right side
						if(ballposX+19<=brickposX || ballposX+1>=brickposX+width) {
							//x direction must change
							ballXdir=-ballXdir;
							
						}
						//not from left and right then it wikk be from top or bottom
						else {
							ballYdir=-ballYdir;
						}
						
						break A; 
						
					}
					
				}
			}
			
		}
		
		
		
		
		ballposX+=ballXdir;
		ballposY+=ballYdir;
	}
	repaint(); 
}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
