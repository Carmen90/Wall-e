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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() { // generado por Eclipse.
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * Reimplementa el metodo equals de la clase Object
	 * Dos Items son iguales si su id es igual. Ignora mayúsculas y minúsculas
	 * @param objeto con el que se compara
	 */
	@Override 
	public boolean equals (Object obj){
		return (this == obj) ||
			   (obj != null) && (this.getClass() == obj.getClass()) 
				             && this.id.equalsIgnoreCase(((Item) obj).id)|| 
			   (obj != null) && (this.id.getClass() == obj.getClass())
			   				 && this.id.equalsIgnoreCase((String)obj);

	}
	
	/**
	 * Reimplements the compareTo
	 * Devuelve un entero en función de si son iguales o no. Devuelve 0 en caso de que sean iguales
	 * Ignora mayúsculas y minúsculas
	 * @param obj  item a comparar
	 */
	@Override
	public int compareTo ( Item obj){
		  return this.id.compareToIgnoreCase(obj.id);	
	}

}
