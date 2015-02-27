package tp.pr3;
import java.util.*;

import tp.pr3.items.Item;
/**
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

	public Place (){
		this.name = "";
		this.isSpaceShip = false;
		this.description = "";
		this.item = new ArrayList <Item>();
	}

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
	
	public Item getItem(java.lang.String id){
		Item item = null;
		boolean encontrado = false;
		int i=0;
		
		while ( !encontrado && i < this.item.size()){
			
			if ( this.item.get(i).equals(id))
			{
				item = this.item.get(i);
				encontrado = true;
			}
			else{
				i++;
			}
			
			return item;
		}
		
		return item;
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
		boolean encontrado = false;
		int i =0;
		if(this.existItem(id)){
			while(i < this.item.size() && !encontrado){
				it = this.item.get(i);
				if(it.getId().equalsIgnoreCase(id)){
					encontrado = true;
					this.item.remove(i);
				}
				i++;			
			}
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
		boolean bool=false;
		Item aux;
		int i=0;
		
		while( i < this.item.size() && !bool){
			aux = this.item.get(i);
			if (aux.getId().equalsIgnoreCase(id)){
				bool = true;
			}
			i++;
		}
		
		return bool;
	}
	/**
	 * Tries to add an item to the place.
	 *  The operation can fail (if the place already contains an item with the same name)
	 * @param it
	 * @return: true if and only if the item can be added to the place, i.e.,
	 *  the place does not contain an item with the same name
	 */
	public boolean addItem(Item it){//Intenta a�adir un item a un lugar.la operacion falla, si place ya contiene un item con el mismo nombre.
		boolean bool = true;
		boolean menor = false;
		int i = 0;
		
		while ( !menor && i < this.item.size() && bool){
			if ( it.menor(this.item.get(i).getId())){
				menor = true;
			}
			else if (existItem(it.getId()))
			{
				bool = false;
			}
			else{
				i++;
			}
		}
		if (bool){
			if ( i == 0){
				this.item.add(0, it);
			}
			else
			{
				for ( int j = this.item.size(); j > i; j--){
					this.item.add(j, this.item.get(j-1));
				}
				this.item.add(i, it);
				
			}
		}
		return bool;

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

	public String getName() {
		return name;
	}

}
