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
public abstract class Item implements Comparable<Item>{
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
    	return this.id.compareToIgnoreCase(iden) < 0;
    }
    /**
     * Revuelve true si el identificador es igual al identificador dado
     * @param iden identificador dado
     * @return si son iguales
     */
  /*  public boolean equal ( String iden){
    	return this.id.compareToIgnoreCase(iden) == 0;
    
    }*/

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
	
	/**
	 * Reimplements the equals metod
	 */
	@SuppressWarnings("unused")
	@Override 
	public boolean equals (Object id){
		boolean eq = false;
	        if (this == id)
	        	eq = true;
	       if ( id.getClass() == Item.class){
		        final Item other = (Item)id;
			    if (this.id == null) {
			        if (other.id == null) 
			        	eq = true;
			    } 
			    else if (this.id.equalsIgnoreCase(other.id)) 
			    	eq = true;
	        }
	        if ( id.getClass() == String.class){
	        	final String aux = (String) id;
	        	if (this.id == null){
	        		if ( aux == null) 
	        			eq = true;
	        	}
	        	else if ( this.id.equalsIgnoreCase(aux)) 
	        		eq = true;
	        }

	        return eq;
		
	}
	
	/**
	 * Reimplements the compareTo
	 * 
	 */
	@Override
	public int compareTo ( Item obj){
		  return this.id.compareTo(obj.id);	
	}

}