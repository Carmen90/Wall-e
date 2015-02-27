package tp.pr3.items;

import java.util.ArrayList;
/**
 * A container of items. It can be employed by any class that stores items. A container 
 * cannot store two items with the same identifier
  It provides methods to add new items, access them and remove them from the container.
 *
 */
public class ItemContainer extends java.lang.Object {
	private ArrayList<Item> container;
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public ItemContainer(){
		this.container= new ArrayList<Item>();
	}
	/**
	 * Checks if the Item with this id exists in the container.
	 * @param id
	 * @return rue if the container as an item with that name.
	 */
	public boolean containsItem(java.lang.String id){	// comprueba si existe un item con ese id en el contenedor
		boolean encontrado = false;
		int i = 0;
		while (encontrado == false && i < this.container.size() ){
			if (id.equalsIgnoreCase(this.container.get(i).getId())){
				encontrado = true;
			} else {
				encontrado = false;
				i++;
			}
		}
		return encontrado;
	}
	/**
	 * Add an item to the container. The operation can fail, returning false
	 * @param item
	 * @return: true if and only if the item is added, i.e., an item with the same identifier does not exists in the container
	 */
	public boolean addItem(Item item){

		boolean anadido = true;
		boolean menor = false;
		int i=0;
		
		while ( !menor && i < this.container.size() && anadido){
			if ( item.menor(this.container.get(i).id)){
				menor = true;
			}
			else if ( this.container.get(i).id == item.id){
				anadido = false;
				System.out.println ( "WALL·E says: I am stupid! I had already the object spaceballs-card");
			}
			else if ( this.container.get(i).equal(item.getId()))
			{
				anadido = false;
			}
			else{
				i++;
			}
		}
		if ( anadido){
			if ( i == 0){
				this.container.add(0, item);
			}
			else{
				for ( int j = this.container.lastIndexOf(item); j > i; j--){
					this.container.add(j, this.container.get(j-1));
					
				}
				this.container.add(i, item);
			}
		}
		
		return anadido;

	}
    /**
     * Returns the item from the container according to the item name
     * @param id
     * @return: Item with that name or null if the container does not store an item with that name.
     */
	public Item getItem(java.lang.String id){
		Item item = null;
		boolean encontrado = false;
		int i=0;
		
		while ( !encontrado && i < this.container.size()){
			
			if ( this.container.get(i).equal(id))
			{
				item = this.container.get(i);
				encontrado = true;
			}
			else{
				i++;
			}
		}		
		return item;
	}
	
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
	 
	public void removeItem(Item item){
		int i=0;
		while (this.container.get(i)!=item){
			i++;
		}
		this.container.remove(i);	
	}
	/**
	 * Generates a String with information about the items contained in the container. 
	 * Note that the items must appear sorted but the item name.
	 */
	public java.lang.String toString(){
		String st = "   ";
		String espacios = "   ";
		
		for (int i = 0; i < this.container.size(); i++){
			st = st + this.container.get(i).id ;
			if(i!=this.container.size()-1){
				st = st + LINE_SEPARATOR + espacios;
				
			}
		}
		return st;}

}
