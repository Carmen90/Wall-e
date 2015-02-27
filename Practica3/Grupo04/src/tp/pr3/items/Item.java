package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
/**
 * The superclass of every type of item.
 *  It contains the common information for all the items and it
 *   defines the interface that the items must match
 *
 */
public abstract class Item extends java.lang.Object{
	protected String id;
	protected java.lang.String description;
	protected int times;
	
	public Item(java.lang.String id, java.lang.String description){
		this.id=id;
		this.description= description;
		this.times = 0;
	}
	/**
	 * Try to use the item with a robot in a given place. 
	 * It returns whether the action was completed. 
	 * Subclasses must override this method
	 * @param r
	 * @param nav
	 * @return
	 */
	
	public abstract boolean use(RobotEngine r, NavigationModule nav);
	/**
	 * Checks if the item can be used. Subclasses must override this method
	 * returns: Checks if the item can be used. Subclasses must override this method
	 */
	public abstract boolean canBeUsed();
	/**
	 * Return the item identifier
	 * @return: The item identifier
	 */
    public java.lang.String getId(){  	
    	
    	return this.id;
    }
    /**
     * Generates a String with the Item description
     */
    public java.lang.String toString(){
    	return this.description;
    }
    
    public boolean menor ( String iden){
    	
    	if ( this.id.compareToIgnoreCase(iden) < 0){
    		return true;
    	}
    	return false;
    }
    
    public boolean   equal ( String iden){
    	
    	if ( this.id.compareToIgnoreCase(iden) == 0){
    		return true;
    	}
    	return false;
    }

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

   
}
