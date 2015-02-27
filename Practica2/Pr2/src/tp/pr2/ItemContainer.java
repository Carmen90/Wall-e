package tp.pr2;

import java.util.ArrayList;
import java.util.Collections;

public class ItemContainer extends java.lang.Object {
	private ArrayList<Item> container;
	private ArrayList<String> listaOrd;
	private ArrayList<String> auxLista;
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public ItemContainer(){
		this.container= new ArrayList<Item>();
		this.auxLista= new ArrayList<String>();
		this.listaOrd= new ArrayList<String>();
	}
	
	public boolean existsItem(java.lang.String id){	// comprueba si existe un item con ese id en el contenedor
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
	/*	
		boolean anadido = false;
		boolean enc = false;
		int i = 0;
		
		while ( i < this.container.size() && !enc){
			if(item.equal(this.container.get(i).getId())){
				enc = true;
				System.out.println("WALL·E says: I am stupid! I had already the object " + container.get(i).getId().toLowerCase());
			}
			i++;
		}
		if(!enc){
			container.add(item);
			anadido = true;
		}
		
		return anadido;
	}*/
		////PROBLEMA AQUI ESCRIBE 2 VECES spaceballs-card
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
		String st = "   ";//"The contents of the container are: " + LINE_SEPARATOR;
		String espacios = "   ";
		
		for (int i = 0; i < this.container.size(); i++){
			st = st + this.container.get(i).id ;
			if(i!=this.container.size()-1){
				st = st + LINE_SEPARATOR + espacios;
				
			}
		}
		return st;}
		/*boolean encontrado=false;
		String cadena="";
		int z=0, j=0;//variable que usare en los bucles
		//primero paso todos los id a un lista auxiliar para ordenarla lista poniendo todo en misnusculas 
		if(this.container.size()>0){
			for ( int i = 0; i < container.size(); i++){
				auxLista.add(container.get(i).getId().toLowerCase());
			}
			Collections.sort(auxLista);//lo ordeno
			while(j < container.size()) // y ahora hago la comparación el array real y pongo mayúsculas y minúsculas
			{
				while(!encontrado){
					if ( auxLista.get(j).equalsIgnoreCase(container.get(z).getId())  ){
						encontrado = true;
						listaOrd.add(container.get(z).getId());
					}
					z++;
				}
				z=0;
				encontrado = false;
				j++;		
			}
			
			for(int w=0;w< container.size();w++){
				cadena = cadena +"   "+ listaOrd.get(w) + "\n";			
			}
		} 
		else{
			cadena = cadena + "The place is empty. There are no objects to pick\n";
		}
		return cadena;
	}*/
}
