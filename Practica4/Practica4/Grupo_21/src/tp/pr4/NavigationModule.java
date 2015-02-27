package tp.pr4;

import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.Item;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * This class is in charge of the robot navigation features.
 * It contains the city where the robot looks for garbage,
 * the current place where the robot is, and the current direction of the robot.
 * It contains methods to handle the different robot movements and
 * to pick and drop items at the current place.
 */
public class NavigationModule{
	private City city;
	private Direction direction;
	private Place initialPlace;
	
	/**
	 * Constructor without parameters 
	 */
	public NavigationModule (){
	}
	
	/**
	 * Navigation module constructor.
	 *  It needs the city map and the initial place
	 * @param aCity
	 * @param initialPlace
	 */
	public NavigationModule ( City aCity, Place initialPlace){
		this.city = aCity;
		this.initialPlace = initialPlace;
		this.direction = Direction.NORTH;
	}
	/**
	 * Checks if the robot has arrived at a spaceship
	 * @return: true if an only if the current place is the spaceship
	 */
	public boolean atSpaceship (){
		boolean isSpace=false;
		if ( this.initialPlace.isSpaceship()){
			isSpace= true;
		}
		return isSpace;
	}
	/**
	 * Drop an item in the current place.
	 * It assumes that there is no other item with the same name/id there.
	 * Otherwise, the behaviour is undefined.
	 * @param it
	 */
	public void dropItemAtCurrentPlace(Item it){
		if ( !this.initialPlace.existItem(it.getId())){
			this.initialPlace.addItem(it);
		}
	}
	/**
	 * Checks if there is an item with a given id in this place
	 * @param id: true if and only if an item with this id is in the current place
	 * @return
	 */
	public boolean findItemAtCurrentPlace(String id){
		return this.initialPlace.existItem(id);
	}
	/**
	 * Returns the robot heading
	 * @return The direction where the robot is facing to.
	 */
	public Direction getCurrentHeading(){
		return this.direction;
	}
	/**
	 * Returns the current place where the robot is (for testing purposes)
	 * @return: The current place
	 */
	public Place getCurrentPlace(){
		return this.initialPlace;
	}
	/**
	 * Returns the street opposite the robot
	 * @return
	 */
	public Street getHeadingStreet(){
		return this.city.lookForStreet( this.initialPlace, this.direction);
	}
	/**
	 * Initializes the current heading according to the parameter
	 * @param heading
	 */
	public void initHeading (Direction heading){
		this.direction = heading;
	}
	/**
	 * The method tries to move the robot following the current direction.
	 * If the movement is not possible because there is no street,
	 * or there is a street which is closed, then it throws an exception.
	 * Otherwise the current place is updated according to the movement
	 * @throws InstructionExecutionException
	 */
	public void move () 
			throws InstructionExecutionException{
				
		Street calle = this.getHeadingStreet();
		
		if(calle==null){
			throw new InstructionExecutionException();
		}
		else{
			if(calle.isOpen()){
				this.initialPlace = calle.nextPlace(this.initialPlace);
			}
			else{
				throw new InstructionExecutionException();
			}			
		}	
	}
	/**
	 * Tries to pick an item characterized by a given identifier from the current place.
	 *  If the action was completed the item is removed from the current place.
	 * @param id
	 * @return: The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItemFromCurrentPlace(java.lang.String id){
		Item item = null;
		if( this.initialPlace.existItem(id)){
			item = this.initialPlace.pickItem(id);
		}
		return item;
	}
	
	/**
	 * Updates the current direction of the robot according to the rotation
	 * @param rotation
	 */
	public void rotate (Rotation rotation){
		if ( rotation == Rotation.LEFT) {
			if ( this.direction == Direction.EAST){
				direction = Direction.NORTH;
			}
			else if ( this.direction  == Direction.NORTH ){
				direction = Direction.WEST;
			}
			else if ( this.direction  == Direction.WEST){
				direction = Direction.SOUTH;
			}
			else if ( this.direction  == Direction.SOUTH){
				direction = Direction.EAST;
			}
		}else if (rotation == Rotation.RIGHT){
			if ( this.direction  == Direction.EAST){
				direction = Direction.SOUTH;
			}
			else if ( this.direction  == Direction.NORTH ){
				direction = Direction.EAST;
			}
			else if ( this.direction  == Direction.WEST){
				direction = Direction.NORTH;
			}
			else if ( this.direction  == Direction.SOUTH){
				direction = Direction.WEST;
			}
			else {
				direction = Direction.UNKNOWN;		
			}
		}
	}

	/**
	 * Prints the information (description + inventory) of the current place
	 */
	public void scanCurrentPlace(){
		this.initialPlace.toString();
	}

	/**
	 * Modify the direction with the given one
	 * @param direction new direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Returns the initial place where the robot is (for testing purposes)
	 * @return the initial place
	 */
	public Place getInitialPlace() {
		return initialPlace;
	}
	
	/**
	 * Returns the direction the robot are looking at (for testing purposes)
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}
    
}
