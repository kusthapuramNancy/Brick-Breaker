package Demogame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
   public int map[][];
   public int brickwidth;
   public int brickHeight;
   public MapGenerator(int r,int c) {
	   map=new int[r][c];
	   for(int i=0;i<r;i++) {
		   for(int j=0;j<c;j++) {
			   map[i][j]=1;
		   }
	   }
	   brickwidth=540/c;
	   brickHeight=150/r;
	   
   }
   public void setBrick(int v,int r,int c) {
	   map[r][c]=v;
	    
   }
   public void draw(Graphics2D g) {
	   for(int i=0;i<map.length;i++) {
		   for(int j=0;j<map[0].length;j++) {
			   if(map[i][j]>0) {
				   g.setColor(Color.white);
				   g.fillRect(j*brickwidth+80,i*brickHeight+50 , brickwidth, brickHeight);
				   g.setColor(Color.black);
				   g.setStroke(new BasicStroke(3));
				   g.drawRect(j*brickwidth+80,i*brickHeight+50 , brickwidth, brickHeight);
			   }
		   }
	   }
   }
   
}
