package dev.tron.lightcycles.luminous;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dev.tron.lightcycles.draw.Block;
import dev.tron.lightcycles.player.Player;
public class Tron {
	static int speed = 2;
	static JFrame f;
	static int size = 10;
	static DrawPanel p;
	static Player one;
	static Player two;
	static boolean run = true;
	public static void init(){
		f = new JFrame();
		Block.setSize(size);
		p = new DrawPanel();
		p.addKeyListener(p);
		one = new Player(100, 100, speed, 0);
		two = new Player(500, 300, -speed, 0);
		one.setSpeed(1);
		two.setSpeed(1);
		f.setSize(600, 400);
		
		f.setMinimumSize( new Dimension(600, 400));
		p.setFocusable(true);
		f.add(p);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(true);
	}
	public static void update(){
		for(Player x : Player.getPlayers()){
			for(int i = 0; i < x.getSpeed(); i++){
				x.update();
				x.bound();
				x.collide();
				if(x.isLost()){
					run = false;
					break;	}		}
			if(x.isLost())break;	}
		p.repaint();
		try{Thread.sleep(10);} catch (Exception e){}
	}
	static class DrawPanel extends JPanel implements KeyListener{

		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.green);
            for(int i = 0; i < one.getBlocks().size(); i++){
            	g.fillRect(one.getBlocks().get(i).getX(), one.getBlocks().get(i).getY(), Block.getSize(), Block.getSize());
            }
            g.setColor(Color.blue);
            for(int i = 0; i < two.getBlocks().size(); i++){
            	g.fillRect(two.getBlocks().get(i).getX(), two.getBlocks().get(i).getY(), Block.getSize(), Block.getSize());
            }
           
        }
      	public void keyPressed(KeyEvent e){
	      	if(e.getKeyCode() == KeyEvent.VK_Z){
	      		if(one.getVerticalVel() == 0)one.setVerticalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_S){
				if(one.getVerticalVel() == 0)one.setVerticalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_Q){
	      		if(one.getHorizontalVel() == 0)one.setHorizontalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_D){
	      		if(one.getHorizontalVel() == 0)one.setHorizontalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_UP){
	      		if(two.getVerticalVel() == 0)two.setVerticalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
	      		if(two.getVerticalVel() == 0)two.setVerticalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_LEFT){
	      		if(two.getHorizontalVel() == 0)two.setHorizontalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
	      		if(two.getHorizontalVel() == 0)two.setHorizontalVel(speed);
	      	} 
	      	
      	}
	    public void keyReleased(KeyEvent e){

	    }
	    public void keyTyped(KeyEvent e){}
	}

	public static void main(String args[]){
		init();
		while(run){
			update();
		}
	}
}