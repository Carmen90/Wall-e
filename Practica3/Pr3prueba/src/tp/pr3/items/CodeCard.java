package tp.pr3.items;
import tp.pr3.*;
/**
 * A CodeCard can open or close the door placed in the streets.
 * The card contains a code that must match the street code in order to perform the action.
 *
 */
public class CodeCard extends Item{
	private java.lang.String code;
	private int times;
	
	public CodeCard(java.lang.String id ,java.lang.String description, java.lang.String code){
		super(id,description );
		this.code =code;
		this.times=1;
	}
	/**
	 * A code card always can be used. Since the robot has the code card it never loses it
	 * returns true because it always can be used
	 */
	public boolean canBeUsed(){
		return true;
	}
	

	/**
	 * The method to use a code card. 
	 * If the robot is in a place which contains a street in the direction he is looking at,
	 *  then the street is opened or closed if the street code and the card code match.
	 *  returns true If the code card can complete the action of opening or closing a street. 
	 *  Otherwise it returns false.
	 */

	public boolean use(RobotEngine r, NavigationModule nav) {//MIRAR LUEGO
		boolean bool=false;
		Street calle = nav.getHeadingStreet();
		if (calle == null){
			return bool;
		}
		//if(canBeUsed()){
			if ( this.code.equalsIgnoreCase(calle.getCode()) ){
				if ( !calle.isOpen()){
					calle.open(this);	
				}
				else{
					calle.close(this);
				}
				bool = true;
			}
	//}
		return bool;
	}
	/**
	 * Gets the code stored in the code card
	 * @return: A String that represents the code
	 */
	public java.lang.String getCode(){
		return this.code ;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
	public java.lang.String toString(){
		return this.id + ": "+ this.description;  //+ this.usar + this.times;
	}

}
