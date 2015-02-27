package pr1;

public class Place {
	private String name;
	private boolean isSpaceShip;
	private String description;

	public Place(java.lang.String name, boolean isSpaceShip, java.lang.String description){
		this.description = description;
		this.name = name;
		this.isSpaceShip = isSpaceShip;
	}
	
	public boolean isSpaceShip (){
		return this.isSpaceShip;
	}
	
	public java.lang.String toString(){
		String LINE_SEPARATOR = System.getProperty("line.separator");
		return (this.name.toString() + LINE_SEPARATOR +  this.description.toString());
	}
	
}
