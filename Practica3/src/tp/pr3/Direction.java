package tp.pr3;

public enum Direction {
	EAST, NORTH, WEST, SOUTH, UNKNOWN;
	
	public Direction dirOpuesta (Direction dir){
		Direction dirOpuesta = Direction.UNKNOWN;
		
		if(dir == Direction.NORTH ){
			dirOpuesta=Direction.SOUTH;
			
		}else if(dir==Direction.SOUTH){
			dirOpuesta=Direction.NORTH;
			
		}else if(dir== Direction.EAST){
			dirOpuesta= Direction.WEST;
		
		}else if(dir== Direction.WEST){
			dirOpuesta= Direction.EAST;
		
		}else
		{
			dirOpuesta=Direction.UNKNOWN;
		}
		
		return dirOpuesta;
	}
	
}
