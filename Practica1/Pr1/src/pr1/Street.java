package pr1;

public class Street {
	private Place source;
	private Place target;
	private Direction direction;

	public Street (Place source, Direction direction, Place target){
		this.direction = direction;
		this.target = target;
		this.source = source;
		
		//source = origen target = destino
	}
	
	public boolean comeOutFrom(Place place, Direction whichDirection){
		boolean isValid= false;
		if (whichDirection == this.direction && place==this.source){
			isValid=true;
		}
		else if(whichDirection == this.direction.dirOpuesta(whichDirection) && place == this.target){
			isValid =true;
		}
		return isValid;
	}
	
	public Place nextPlace ( Place whereAmI){
		Place place = null;
		if( whereAmI == this.source){
			place = this.target;
		}
		else if(whereAmI == this.target){
			place=this.source;
		}
		return place;
	}
}
