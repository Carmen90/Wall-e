package tp.pr3;

import tp.pr3.items.CodeCard;
/**
 * A street links two places A and B in one direction.
 *  If it is defined as Street(A,NORTH,B) it means that Place B is at NORTH of Place A.
 *   Streets are two-way streets, i.e.
 *  if B is at NORTH of A then A is at SOUTH of B.
 *  Some streets are closed and a code (contained in a code card) is needed to open them
 *
 */
public class Street {
	private Place source;
	private Place target;
	private Direction direction;
	private boolean isOpen;
	private java.lang.String code;
	private String id;

	public Street (){
		this.id = "";
	}
	public Street (Place source, Direction direction, Place target){
		this.direction = direction;
		this.target = target;
		this.source = source;
		this.isOpen = true;
		this.code = "";
		
	}
	
	public Street (Place source, Direction direction, Place target, boolean isOpen, java.lang.String code ){
		this.source=source;
		this.direction=direction;
		this.target=target;
		this.isOpen = isOpen;
		this.code = code;
	}
	/**
	 * Checks if the street comes out from a place in a given direction.
	 *  Remember that streets are two-way
	 * @param place
	 * @param whichDirection
	 * @Returns true if the street comes out from the input Place.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){ //Mira a ver si la calle sale de un lugar en la dir dada
		boolean isValid = false;
		if ( whichDirection == this.direction && place == this.source ){
			isValid = true;
		}
		else if( whichDirection == whichDirection.dirOpuesta(this.direction) && place == this.target){
			isValid = true;
		}
		else {
			isValid = false;
		}
		return isValid;
	}
	/**
	 * Returns the place of the other side from the place whereAmI.
	 *  This method does not consider whether the street is open or closed.
	 * @param whereAmI
	 * @return: It returns the Place at the other side of the street (even if the street is closed).
	 *  Returns null if whereAmI does not belong to the street.
	 */
	public Place nextPlace ( Place whereAmI){//Devuelve el lugar del otro lado del lugar whereAmI
		Place place = null;
		
		if ( whereAmI == this.source ){
			
			place = this.target;
		}
		else if ( whereAmI == this.target ){
			place = this.source;
		}
		else {
			place = null;
		}
		return place;
	}
	/**
	 * Checks if the street is open or closed
	 * @return: true, if the street is open, and false when the street is closed
	 */
	public boolean isOpen(){ //Mira si la calle est� abierta		
		return isOpen;
	}
	/**
	 * Tries to open a street using a code card.
	 *  Codes must match in order to complete this action
	 * @param card
	 * @return true if the action has been completed
	 */
	public boolean open(CodeCard card){//Intenta abrir la puerta usando la codeCard	
		boolean bool= false;
		if(this.code==card.getCode()){
			bool=true;
			this.isOpen=true;
		}
		return bool;
	}
	/**
	 * Tries to open a street using a code card.
	 *  Codes must match in order to complete this action
	 * @param card
	 * @return: true if the action has been completed
	 */
	public boolean close(CodeCard card){
		boolean bool=false;
		if(this.code==card.getCode()){
			bool=true;
			this.isOpen=false;
		}
		return bool;
	}

	public java.lang.String getCode() {
		return code;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public Place getSource() {
		return source;
	}
	public Place getTarget() {
		return target;
	}
}
