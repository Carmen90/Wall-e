package tp.pr4;

import tp.pr4.items.CodeCard;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * A street links two places A and B in one direction.
 *  If it is defined as Street(A,NORTH,B) it means that Place B is at NORTH of Place A.
 *   Streets are two-way streets, i.e.
 *  if B is at NORTH of A then A is at SOUTH of B.
 *  Some streets are closed and a code (contained in a code card) is needed to open them
 */
public class Street {
	private Place source;
	private Place target;
	private Direction direction;
	private boolean isOpen;
	private java.lang.String code;
	private String id;

	/**
	 * Constructor without parameters 
	 */
	public Street (){
		this.id = "";
	}
	
	/**
	 * Street constructor
	 * @param source source place
	 * @param direction	direction looking at
	 * @param target target place
	 */
	public Street (Place source, Direction direction, Place target){
		this.direction = direction;
		this.target = target;
		this.source = source;
		this.isOpen = true;
		this.code = "";
		
	}
	
	/**
	 * Street constructor
	 * @param source place
	 * @param direction looking at
	 * @param target place
	 * @param isOpen true if the street is open
	 * @param code code that open that closed street
	 */
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
		return ( whichDirection == whichDirection.dirOpuesta(this.direction) && place == this.target) || ( whichDirection == this.direction && place == this.source );
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
		if(this.code==card.getCode()){
			this.isOpen=true;
		}
		return this.code==card.getCode();
	}
	
	/**
	 * Tries to open a street using a code card.
	 *  Codes must match in order to complete this action
	 * @param card
	 * @return: true if the action has been completed
	 */
	public boolean close(CodeCard card){
		if(this.code==card.getCode()){
			this.isOpen=false;
		}
		return this.code==card.getCode();
	}

	/**
	 * Gives the code of the street
	 * @return
	 */
	public java.lang.String getCode() {
		return this.code;
	}
	
	/**
	 * Modify the id of the street
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gives the id of the street
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gives the source place
	 * @return
	 */
	public Place getSource() {
		return source;
	}
	
	/**
	 * Gives the target place
	 * @return
	 */
	public Place getTarget() {
		return target;
	}
}
