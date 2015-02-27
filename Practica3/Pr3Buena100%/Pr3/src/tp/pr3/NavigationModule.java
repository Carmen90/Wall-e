package tp.pr3;

import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.items.Item;

public class NavigationModule {
	private City city;
	private Direction direction;
	private Place initialPlace;
	
	public NavigationModule (){
	}
	public NavigationModule ( City aCity, Place initialPlace){
		this.city = aCity;
		this.initialPlace = initialPlace;
		this.direction = Direction.NORTH;
	}
	
	public boolean atSpaceship (){
		boolean isSpace=false;
		if ( this.initialPlace.isSpaceship()){
			isSpace= true;
		}
		return isSpace;
	}
	
	public void dropItemAtCurrentPlace(Item it){
		if ( !this.initialPlace.existItem(it.getId())){
			this.initialPlace.addItem(it);
		}
	}
	
	public boolean findItemAtCurrentPlace(String id){
		return this.initialPlace.existItem(id);
	}
	
	public Direction getCurrentHeading(){
		return this.direction;
	}
	
	public Place getCurrentPlace(){
		return this.initialPlace;
	}
	
	public Street getHeadingStreet(){
		return this.city.lookForStreet( this.initialPlace, this.direction);
	}
	
	public void initHeading (Direction heading){
		this.direction = heading;
	}

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

	public Item pickItemFromCurrentPlace(java.lang.String id){
		Item item = null;
		if( this.initialPlace.existItem(id)){
			item = this.initialPlace.pickItem(id);
		}
		return item;
	}
	
	public void rotate (Rotation rotation){
		if ( rotation == Rotation.LEFT) {
			if ( this.direction == Direction.EAST){
				this.direction = Direction.NORTH;
			}
			else if ( this.direction == Direction.NORTH ){
				this.direction = Direction.WEST;
			}
			else if ( this.direction == Direction.WEST){
				this.direction = Direction.SOUTH;
			}
			else if ( this.direction == Direction.SOUTH){
				this.direction = Direction.EAST;
			}
		}else if (rotation == Rotation.RIGHT){
			if ( this.direction == Direction.EAST){
				this.direction = Direction.SOUTH;
			}
			else if ( this.direction == Direction.NORTH ){
				this.direction = Direction.EAST;
			}
			else if ( this.direction == Direction.WEST){
				this.direction = Direction.NORTH;
			}
			else if ( this.direction == Direction.SOUTH){
				this.direction = Direction.WEST;
				}
			}
			else {
				System.out.println( "WALL·E > WALL·E says: I do not understand. Please repeat" );
				
			}
			
	}

	public void scanCurrentPlace(){
		this.initialPlace.toString();
	}

	public Place getInitialPlace() {
		return initialPlace;
	}
}
