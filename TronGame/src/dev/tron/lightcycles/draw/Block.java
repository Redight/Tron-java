package dev.tron.lightcycles.draw;

//Block class for painting tron cycle lines
public class Block {
	private static int size;
	private int x;
	private int y;
	public Block(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		Block.size = size;
	}
}