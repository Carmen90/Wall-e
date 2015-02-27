package tp.pr3;

public class NavigationModule {
	private City city;
	private Direction dir;
	private Place initialPlace;
	
	public NavigationModule ( City aCity, Place initialPlace){
		//this.dir = Direction.NORTH;
		this.city = aCity;
		this.initialPlace = initialPlace;
	}
	
	public boolean atSpaceShip (){
		boolean isSpace=false;
		if ( this.initialPlace.isSpaceship()){
			isSpace= true;
		}
		return isSpace;
	}
	
	public void dropItemAtCurrentPlace(Item it){
		this.initialPlace.removeItem(it);
	}
	
	public boolean findItemAtCurrentPlace(String id){
		return this.initialPlace.existItem(id);
	}
	
	public Direction getCurrentHeading(){
		return this.dir;
	}
	
	public Place getCurrentPlace(){
		return this.initialPlace;
	}
	
	
}
