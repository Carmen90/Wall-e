package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;

/**
 * An item that represents fuel. 
 * This item can be used at least once and it provides power energy to the robot.
 *  When the item is used the configured number of times, then it must be removed from the robot inventory
 *
 */
public class Fuel extends Item {
	private int power;
	private int times;
	
	
	//Fuel constructor
	public Fuel(String id, String description, int power, int times){
		super ( id, description);
		this.power = power;
		this.times = times;
	}
/**
 * Fuel can be used as many times as it was configured
 * return: true it the item still can be used
 */
	public boolean canBeUsed (){
		boolean canBeUsed=false;
		if ( this.times > 0){
			canBeUsed=true;
		}
		return canBeUsed;
	}

	/**
	 * Try to use the item with a robot in a given place. It returns whether the action was completed. 
	 * Subclasses must override this method
	 * return:true if the action was completed. Otherwise, it returns false
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean seUsa=false;
		if ( this.canBeUsed()){
			seUsa=true;
			r.addFuel(this.power) ;
			System.out.println("   * My power is " + r.getPower());
			System.out.println("   * My recycled material is: " + r.getRecycledMaterial());
			this.times--;
			if(this.times==0){
				System.out.println("WALL·E says: What a pity! I have no more " + this.getId().toLowerCase() + " in my inventory" );
			}
		}
			return seUsa;	
	}
	/**
	 * Generates a String with the Item description
	 */
	public java.lang.String toString(){
		return this.id + ": "+ this.description + " // " + "power = "+
				this.power + ", times = " + this.times; 
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
