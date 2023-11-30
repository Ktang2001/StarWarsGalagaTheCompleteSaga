package game_Code;



public class Ship  {
	
	private int shipNum;
	private int numWep;
	private String playerShip = null; 
	
	public Ship(int shipNum, int numWep,String string) {
		this.setShipNum(shipNum);
		this.setNumWep(numWep);
	    this.getPlayership();
	}
	public void setShipNum( int shipNum) {
		this.shipNum = shipNum;
	}
	public void setNumWep(int numberOfWepons) {
		this.numWep = numberOfWepons;
	}
	public int getShipNum() {   
		return this.shipNum;
	}
	public int getNumWep() {
		return this.numWep;
	}
	public void setPlayerShip(String playership) {
		this.playerShip = playership;
	}
	public String getPlayership() {
		return this.playerShip;
	}
}
