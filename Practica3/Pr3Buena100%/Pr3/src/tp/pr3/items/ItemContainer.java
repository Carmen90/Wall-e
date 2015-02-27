package tp.pr3.items;

import java.util.ArrayList;

public class ItemContainer extends java.lang.Object {
	private ArrayList<Item> container;
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public ItemContainer(){
		this.container= new ArrayList<Item>();
	}
	
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
