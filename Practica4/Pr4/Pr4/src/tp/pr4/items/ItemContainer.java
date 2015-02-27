package tp.pr4.items;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * A container of items. It can be employed by any class that stores items. A container 
 * cannot store two items with the same identifier
 * It provides methods to add new items, access them and remove them from the container.
 *
 */
public class ItemContainer extends java.lang.Object {
	private List<Item> container;
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * ItemContainer default constuctor
	 */
	public ItemContainer(){
		this.container= new ArrayList<Item>();
	}
	
	/**
	 * Checks if the Item with this id exists in the container.
	 * @param id
	 * @return true if the container has an item with that id.
	 */
	public boolean containsItem(java.lang.String id){	// comprueba si existe un item con ese id en el contenedor
		Item item = null;
		int i = -1;
		item = this.getItem(id);
		
		if ( item != null){
			i = Collections.binarySearch(this.container, item);
		}
		
		return i >= 0;
	}
		
	/**
	 * Add an item to the container. The operation can fail, returning false
	 * @param item
	 * @return: true if and only if the item is added, i.e., an item with the same identifier does not exists in the container
	 */
	public boolean addItem(Item item){
		int i=0, j;
		
		i = Collections.binarySearch(this.container, item);
		
		if ( i >= 0){
			System.out.println ( "WALL·E says: I am stupid! I had already the object spaceballs-card");
		}
		else{
			j = -i -1;
			this.container.add(j, item);
		}	
		return i < 0;

	}
	
    /**
     * Returns the item from the container according to the item name
     * @param id
     * @return: Item with that name or null if the container does not store an item with that name.
     */
	public Item getItem(java.lang.String id){
		Item item = null;
		
		for ( Item itemAux : container){
			if (itemAux.equals(id)){
				item = itemAux;
			}
		}
		return item;
	}
	
	/**
	 * Number of items from the container
	 * @return number of items
	 */
	public int numberOfItems(){
		return this.container.size();
	}
	/**
	 * Returns and deletes an item from the inventory.
	 *  This operation can fail, returning null
	 * @param id
	 * @return An item if and only if the item identified by id exists in the inventory. Otherwise it returns null
	 */
	public  Item pickItem(java.lang.String id){
		Item item = this.getItem(id);
		if (item!=null){
			removeItem(item);
		}
		return item;
	}
	 
	/**
	 * Removes the item given for the container
	 * @param item the item to remove
	 */
	public void removeItem(Item item){
		int i=0;
		i = Collections.binarySearch(this.container, item);
		if ( i > 0) this.container.remove(i);
		this.container.remove(i);	
	}
	
	/**
	 * Generates a String with information about the items contained in the container. 
	 * Note that the items must appear sorted but the item name.
	 */
	public java.lang.String toString(){
		String st = "   ";
		String espacios = "   ";
		int i = 0;
		
		for ( Item aux : container){
			st = st + aux.id ;
			if(i!=this.container.size()-1){
				st = st + LINE_SEPARATOR + espacios;
			}
			i++;
		}
		return st;
	}
	
	public List<Item> getContainer() {
		return container;
	}

}
