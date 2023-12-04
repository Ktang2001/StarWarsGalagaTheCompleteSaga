package game_Code;

import java.awt.Image;

import javax.swing.*;

public class Ship  {
	
	private int shipNum;
	private int numWep;
	private String playerShip = null; 
	private Image shipImage = null;
	
	public Ship(int shipNum, int numWep,String string) {
		this.setShipNum(shipNum);
		this.setNumWep(numWep);
	    this.shipImage = setImage(shipNum);
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
	public Image setImage(int shipNum) {
		switch(shipNum) {
		 case 0:
			 this.shipImage = new ImageIcon("N1_Starfigher.png").getImage();
             break;
		 case 1:
			 this.shipImage = new ImageIcon("JediStarfighter.png").getImage();
			 break;
		 case 2:
			 this.shipImage = new ImageIcon("ARC-170.png").getImage();
			 break;
		 case 3:
			 this.shipImage = new ImageIcon("X-Wing.png").getImage();
			 break;
		 case 4:
			 this.shipImage = new ImageIcon("Y-Wing.png").getImage();
			 break;
		 case 5:
			 this.shipImage = new ImageIcon("falcon.png").getImage();
			 break;
		 case 7:
			 this.shipImage = new ImageIcon("VultureDroid.png").getImage();
			 break;
		 case 8:
			 this.shipImage = new ImageIcon("Tri-Fighter.png").getImage();
			 break;
		 case 9:
			 this.shipImage = new ImageIcon("battle-station.png").getImage();
			 break;
		 case 10:
			 this.shipImage = new ImageIcon("Grievous-Ship.png").getImage();
			 break;
		 case 11:
			 this.shipImage = new ImageIcon("malevolence-starship.png").getImage();
			 break;
		 case 12:
			 this.shipImage = new ImageIcon("Tie-Fighter.png").getImage();
			 break;
		 case 13:
			 this.shipImage = new ImageIcon("star-destroyer.png").getImage();
			 break;
		 case 14:
			 this.shipImage = new ImageIcon("executor-star-destroyer.png").getImage();
			 break;
		 case 15:
			 this.shipImage = new ImageIcon("death-star.png").getImage();
			 break;
		 case 16:
			 this.shipImage = new ImageIcon("death-star-2.png").getImage();
			 break;
		}
		return this.shipImage;
	}
	public Image getImage() {
		return this.shipImage;
	}
}
