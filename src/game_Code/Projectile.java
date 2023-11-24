package game_Code;

public class Projectile {
	private String weponName;
	private int width ;
	private int height;
	private int damage;
	private int xPos;
	private int yPos;
	
	
	public Projectile(int w,int h, int damage,String weponName) {
		
	}
	public int getWidth () {
		return this.width;
	}
	public void setWidth (int width) {
		this.width = width ;
	}
	public int getHeight () {
		return this.height;
	}
	public void setHeight (int height) {
		this.height = height ;
	}
	public int getDamage () {
		return this.damage;
	}
	public void setDamage (int damage) {
		this.damage = damage ;
	}
	public void setWeponName(String weponName) {
		this.weponName = weponName;
	}
	public String getWeponName(String weponName) {
		return this.weponName;
	}
	public void setDimension (int width,int height) {
		this.width = width;
		this.height = height;		
	}
	public void setY(int y) {
		this.yPos = y;
		
	}
	public void setX(int x) {
		this.xPos = x;
	}
	public int getY(){
		return this.yPos;
		
	}
	public int getX(){
		return this.xPos;
		
	}
	public void setLocation(int x,int y) {
		this.xPos = x;
		this.yPos = y;
	}

	
}
