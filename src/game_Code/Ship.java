package game_Code;

public class Ship {
	private int shipNum;
	private int numWep;
	
	public Ship(int shipNum, int numWep) {
		this.setShipNum(shipNum);
		this.setNumWep(numWep);
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
}
