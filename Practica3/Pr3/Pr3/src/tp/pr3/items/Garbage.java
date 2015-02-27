package tp.pr3.items;
import tp.pr3.*;
/**
 * The garbage is a type of item that generates recycled material after using it.
 *  The garbage can be used only once.
 *   After using it, it must be removed from the robot inventory
 *
 */
public class Garbage extends Item{
	private int times;
	private int recycledMaterial;
	private boolean  usar;
	
	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.times=1;
		this.recycledMaterial = recycledMaterial;
		this.usar =true;
	}
	/**
	 * Garbage can be used only once
	 * returns: true if the item has not been used yet
	 */
	public boolean canBeUsed(){
		return this.usar;
	}
	/**
	 * The garbage generates recycled material for the robot that uses it
	 * true if the garbage was transformed in recycled material
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		// TODO Auto-generated method stub
		if (this.canBeUsed()){
			r.addRecycledMaterial(this.recycledMaterial);
			this.usar = false;
			System.out.println("   * My power is " + r.getPower());
			System.out.println("   * My recycled material is: " + r.getRecycledMaterial());
			this.times--;
			if(this.times==0){
				System.out.println("WALL·E says: What a pity! I have no more " + this.getId().toLowerCase() + " in my inventory" );
			}
			return true;
		}
		return false;
	}
	/**
	 * Generates a String with the Item description
	 */
	public java.lang.String toString(){
		return this.id + ": "+ this.description;  //+ this.usar + this.times;
	}
	

}
