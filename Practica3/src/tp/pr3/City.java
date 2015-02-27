package tp.pr3;

import java.util.ArrayList;

public class City extends java.lang.Object{
	private ArrayList<Street> cityMap;
	
	public City (){}
	
	public City ( Street[] cityMap){
		this.cityMap = new ArrayList <Street> ();
	
		for ( int i = 0; i < cityMap.length; i++){
			this.cityMap.add(cityMap[i]);
		}
	}
	
	public Street lookForStreet(Place currentPlace, Direction currentHeading){
		int i = 0;
		Street calle = null;
		boolean enc = false;
		
		if (this.cityMap != null){
			while ( i < this.cityMap.size() && !enc){
				if ( this.cityMap.get(i).comeOutFrom(currentPlace, currentHeading)){
					enc = true;
					calle = this.cityMap.get(i);
				}
				else{
					i++;
					
				}
			}
		}
		return calle;
	}

	public ArrayList<Street> getCityMap() {
		return cityMap;
	}

	
}