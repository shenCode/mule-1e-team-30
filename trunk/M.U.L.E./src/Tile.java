import java.awt.*;

import javax.swing.JButton;

public class Tile extends JButton{
	
	private Image image;
	protected Point p;
	protected static int food;
	protected static int energy;
	protected static int ore;
	protected static int crystite;
	protected Player owner;
	protected Boolean bought = false;
	protected int price;
	
	/**
	 * the construction of tile class, creating different tiles
	 * in the map.
	 * @param str	the tile type
	 */
	public boolean Tile(String str){
		
		//checking the tile input and creat the tile
		if (str == null){
			return false;
		}
		switch (str){
			case "M1":
				createM1();
				break;
			
			case "M2":
				createM2();
				break;
				
			case "M3":
				createM3();
				break;
				
			case "Town":
				createTown();
				break;
			
			case "Plain":
				createPlain();
				break;
			
			case "River":
				createRiver();
				break;
		}
		return true;
	}
	
	public void createM1();
	public void createM2();
	public void createM3();
	public void createTown();
	public void createPlain();
	public void createRiver();



	

	
}