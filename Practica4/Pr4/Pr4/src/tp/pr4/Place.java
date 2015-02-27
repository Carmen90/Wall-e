package tp.pr4;
import java.util.*;

import tp.pr4.items.Item;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * It represents a place in the city.
 * Places are connected by streets according to the 4 compass directions,
 * North, East, South and West. Every place has a name and a textual description about itself.
 * This description is displayed when the robot arrives at the place.
 * A place can represent the spaceship where the robot is safe.
 * When the robot arrives at this place, the application is over.
 *
 */
public class Place {
	private String name;
	private boolean isSpaceShip;
	private String description;
	private ArrayList<Item> item;

	/**
	 * Constructor without parameters 
	 */
	public Place (){
		this.name = "";
		this.isSpaceShip = false;
		this.description = "";
		this.item = new ArrayList <Item>();
	}

	/**
	 * Place contrustor with arguments.
	 * @param name the name of the place
	 * @param isSpaceShip true if the place is a spaceship
	 * @param description description of the place
	 */
	public Place(java.lang.String name, boolean isSpaceShip, java.lang.String description){
		this.description = description;
		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.item = new ArrayList<Item>();
	}
	/**
	 * Is this place the space ship?
	 * @return: true if the place represents a space ship
	 */
	public boolean isSpaceship (){
		return this.isSpaceShip;
	}

	/**
	 * Gives a item with a given id
	 * @param id
	 * @return item returns the item with a given id
	 */
	public Item getItem(java.lang.String id){
		Item it = null;
		
		for ( Item itemAux : item){
			if (itemAux.equals(id)){
				it = itemAux;
			}
		}
		return it;
	}
	/**
	 * Tries to pick an item characterized by a given identifier from the place. 
	 * If the action was completed the item must be removed from the place.
	 * @param id
	 * @return: The item of identifier id if it exists in the place.
	 *  Otherwise the method returns null
	 */
	public Item pickItem(java.lang.String id){
		Item it = null;
		int i = -1;
		it = this.getItem(id);
		
		if ( it != null){
			i = Collections.binarySearch(this.item, it);
			if ( i >= 0)
				this.item.remove(i);	
		}
			return it;	
	}
	
/**
 * Drop an item in this place. The operation can fail, returning false
 * @param item
 * @return: true if and only if the item is dropped in the place, i.e.,
 *  an item with the same identifier does not exists in the place
 */
	public boolean dropItem(Item item){
		boolean drop = false;
		
		if ( !this.existItem(item.getId())){
			this.item.add(item);
			drop = true;
		}

		return drop;
	}
	
	/**
	 * Checks if an item is in this place
	 * @param id
	 * @return: The item of identifier id if it exists in the place.
	 *  Otherwise the method returns null
	 */
	public boolean existItem(String id){//Mira a ver si existe un item con ese id en la lista de objetos de place
		Item it = null;
		int i = -1;
		it = this.getItem(id);
		
		if ( it != null){
			i = Collections.binarySearch(this.item, it);
		}
		
		return i >= 0;
	}
	
	/**
	 * Tries to add an item to the place.
	 *  The operation can fail (if the place already contains an item with the same name)
	 * @param it
	 * @return: true if and only if the item can be added to the place, i.e.,
	 *  the place does not contain an item with the same name
	 */
	public boolean addItem(Item it){//Intenta aï¿½adir un item a un lugar.la operacion falla, si place ya contiene un item con el mismo nombre.
		int i=0, j;
		
		i = Collections.binarySearch(this.item, it);
		
		if ( i >= 0){
			System.out.println ( "WALL·E says: I am stupid! I had already the object spaceballs-card");
		}
		else{
			j = -i -1;
			this.item.add(j, it);
		}	
		return i < 0;
	}
	
	/**
	 * Overrides toString method. Returns the place name, its description and the list of items contained in the place
	 */
	public java.lang.String toString(){ //Devuelve el nombre, la descripcion y la lista de los items que contiene el lugar
		String LINE_SEPARATOR = System.getProperty("line.separator");
		String st = this.name.toString() + LINE_SEPARATOR +  this.description.toString() + LINE_SEPARATOR;
		if ( this.item.size()==0){
			st+= "The place is empty. There are no objects to pick" + LINE_SEPARATOR;
		}else
		{
			st += "The place contains these objects:" + LINE_SEPARATOR;
			for (int i = 0; i < this.item.size(); i++){
				st = st + "   "+ this.item.get(i).getId() + LINE_SEPARATOR;
			}
		}
		return st;
	}

	/**
	 * Gives the name of the place
	 * @return the name of the place
	 */
	public String getName() {
		return name;
	}

}
