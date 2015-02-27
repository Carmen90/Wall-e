package tp.pr3.items;
import tp.pr3.*;

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
	public boolean canBeUsed(){
		return this.usar;
	}
	@Override
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
	
	public java.lang.String toString(){
		return this.id + ": "+ this.description;  //+ this.usar + this.times;
	}
	

}
