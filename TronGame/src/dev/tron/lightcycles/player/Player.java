package dev.tron.lightcycles.player;
import java.util.ArrayList;
import dev.tron.lightcycles.draw.Block;
public class Player {
	//Static list of players
	private static ArrayList<Player> players = new ArrayList<>();
	//Queue of blocks
	private ArrayList<Block> blocks = new ArrayList<>();
	//Velocities
	private int horizontalVel;
	private int verticalVel;
	//Position
	private int x;
	private int y;
	//Length of tron cycle
	private boolean lost = false;
	//Speed of tron cycle to increase updates
	private int speed;
	public Player(int x, int y, int hVel, int vVel){
		this.x = x;
		this.y = y;
		horizontalVel = hVel;
		verticalVel = vVel;
		getPlayers().add(this);
		getBlocks().add(new Block(this.x, this.y));
	}
	//Update player position and corresponding blocks
	public void update(){
		x += horizontalVel;
		y += verticalVel;
		Block z = new Block(x, y);
		getBlocks().add(0, z);
	}
	//Check out of bounds
	public void bound(){
		if(x > 590 || x < 0 || y > 390 || y < 0)setLost(true);
	}
	//Collision detection
	public void collide(){
		for(Player z : getPlayers()){
			int count = 0;
			for(Block b : z.getBlocks()){
				count++;
				if(count == 1)continue;
				if((x+horizontalVel >= b.getX() && x <= b.getX() && (y >= b.getY() && y <= b.getY()+Block.getSize()) && horizontalVel > 0) 
					|| (y+verticalVel >= b.getY() && y <= b.getY() && (x >= b.getX() && x <= b.getX()+Block.getSize()) && verticalVel > 0) 
					|| (x+horizontalVel <= b.getX() && x >= b.getX() && (y >= b.getY() && y <= b.getY()+Block.getSize()) && horizontalVel < 0) 
					|| (y+verticalVel <= b.getY() && y >= b.getY() && (x >= b.getX() && x <= b.getX()+Block.getSize()) && verticalVel < 0)){
					setLost(true);
				}
			}
		}
	}
	//Getters + Setters
	public void setHorizontalVel(int vel){
		horizontalVel = vel;
		verticalVel = 0; //Move only up/down or left/right
	}
	public void setVerticalVel(int vel){
		verticalVel = vel;
		horizontalVel = 0;
	}
	public void setSpeed(int s){
		speed = s;
	}
	public int getHorizontalVel(){
		return horizontalVel;
	}
	public int getVerticalVel(){
		return verticalVel;
	}
	public int getSpeed(){
		return speed;
	}
	public boolean isLost() {
		return lost;
	}
	public void setLost(boolean lost) {
		this.lost = lost;
	}
	public static ArrayList<Player> getPlayers() {
		return players;
	}
	public static void setPlayers(ArrayList<Player> players) {
		Player.players = players;
	}
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}
}