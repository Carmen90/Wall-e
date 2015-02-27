package tp.pr3;
import java.util.ArrayList;
/**
 * This class represents the city where the robot is wandering. 
 * It contains information about the streets and the places in the city
 */
public class City {
		private ArrayList<Street> cityMap;
		
		public City (){}
		/**
		 * Creates a city using an array of streets
		 */
		public City ( Street[] cityMap){
			this.cityMap = new ArrayList <Street> ();
		
			for ( int i = 0; i < cityMap.length; i++){
				this.cityMap.add(cityMap[i]);
			}
		}
		/**
		 * Looks for the street that starts from the given place in the given direction.
		 * @param currentPlace - The place where to look for the street
		 * @param currentHeading - The direction to look for the street
		 * @return: the street that stars from the given place in the given direction.
		 *  It returns null if there is not any street in this direction from the given place
		 */
		public Street lookForStreet(Place currentPlace, Direction currentHeading){
			int i = 0;
			Street calle = null;
			boolean enc = false;
			
			if (this.cityMap != null){
				while ( i < this.cityMap.size() && !enc && this.cityMap.get(i)!= null){
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
		/**
		 * Adds an street to the city
		 * @param street
		 */		
		public void addStreet ( Street street){
			this.cityMap.add(street);
		}
}
