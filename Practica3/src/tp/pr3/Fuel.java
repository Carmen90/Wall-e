package tp.pr3;

public class Fuel extends Item {
	private int power;
	private int times;
	
	
	//Fuel constructor
	public Fuel(String id, String description, int power, int times){
		super ( id, description);
		this.power = power;
		this.times = times;
	}

	public boolean canBeUsed (){
		boolean canBeUsed=false;
		if ( this.times > 0){
			canBeUsed=true;
		}
		return canBeUsed;
	}

	@Override
	public boolean use(RobotEngine r, Place p) {
		boolean seUsa=false;
		if ( this.canBeUsed()){
			seUsa=true;				
			//power2=r.getFuel()+ this.power;
			r.addFuel(this.power) ;
			System.out.println("   * My power is " + r.getPower());
			System.out.println("   * My recycled material is: " + r.getRecycledMaterial());
			this.times--;
			if(this.times==0){
				System.out.println("WALL·E says: What a pity! I have no more " + this.getId().toLowerCase() + " in my inventory" );
			}
		}
			return seUsa;	
	}
	public java.lang.String toString(){
		return this.id + ": "+ this.description + "// " + "power = "+
				this.power + ", times = " + this.times; 
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
