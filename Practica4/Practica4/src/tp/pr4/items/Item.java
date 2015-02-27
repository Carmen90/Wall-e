package tp.pr4.items;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * The superclass of every type of item.
 *  It contains the common information for all the items and it
 *   defines the interface that the items must match
 *
 */
public abstract class Item extends java.lang.Object{
	protected String id;
	protected java.lang.String description;
	protected int times;
	
	/**
	 * Item constructor
	 * @param id identification of the item
	 * @param description of the item
	 */
	public Item(java.lang.String id, java.lang.String description){
		this.id=id;
		this.description= description;
		this.times = 0;
	}
	/**
	 * Try to use the item with a robot in a given place. 
	 * It returns whether the action was completed. 
	 * Subclasses must override this method
	 * @param r the robot
	 * @param nav the navigation module
	 * @return if it is use
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
    
    /**
     * Devuelve true si el elemento es menor cronologicamente al dado
     * @param iden elemento dado
     * @return si es menor
     */
    public boolean menor ( String iden){
    	
    	if ( this.id.compareToIgnoreCase(iden) < 0){
    		return true;
    	}
    	return false;
    }
    /**
     * Revuelve true si el identificador es igual al identificador dado
     * @param iden identificador dado
     * @return si son iguales
     */
    public boolean equal ( String iden){
    	
    	if ( this.id.compareToIgnoreCase(iden) == 0){
    		return true;
    	}
    	return false;
    }

	/**
	* Devuelve el numero de veces que se puede usar este item
	*/
	public int getTimes() {
		return times;
	}

	/**
	* Modifica el numero de veces que se puede usar el item
	*/
	public void setTimes(int times) {
		this.times = times;
	}

   
}
