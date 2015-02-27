package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;

public abstract class Item extends java.lang.Object{
	protected String id;
	protected java.lang.String description;
	protected int times;
	
	public Item(java.lang.String id, java.lang.String description){
		this.id=id;
		this.description= description;
		this.times = 0;
	}
	
	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	public abstract boolean canBeUsed();
	
    public java.lang.String getId(){  	
    	
    	return this.id;
    }
    
    public java.lang.String toString(){
    	return this.description;
    }
    
    public boolean menor ( String iden){
    	
    	if ( this.id.compareToIgnoreCase(iden) < 0){
    		return true;
    	}
    	return false;
    }
    
    public boolean   equal ( String iden){
    	
    	if ( this.id.compareToIgnoreCase(iden) == 0){
    		return true;
    	}
    	return false;
    }

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

   
}
