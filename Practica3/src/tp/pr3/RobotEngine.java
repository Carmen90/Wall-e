package tp.pr3;
//import java.util.ArrayList;
import java.util.Scanner;

//import tp.pr2.testprofesor.MockCity;

public class RobotEngine {
	private Place initialPlace;
	private Direction direction;
	private City cityMap;
	private ItemContainer container;
	private Instruction instruction;
	private String id;
	private static 	Scanner leer = new Scanner(System.in);
	private int power;
	private int reciclado;
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public RobotEngine (){
	}
	public RobotEngine ( Place initialPlace, Direction direction,City cityMap ){
		this.cityMap= cityMap;
		this.direction = direction; //this.direction = Direction.NORTH;??
		this.initialPlace= initialPlace;
		this.power = 50;
		this.reciclado = 0;
		this.container = new ItemContainer ();
		this.instruction = new Instruction();
		this.id="";
	}
	
	public RobotEngine(City cityMap, Place initialPlace, Direction direction){
		this.cityMap= cityMap;
		this.direction = direction; //this.direction = Direction.NORTH;??
		this.initialPlace= initialPlace;
		this.power = 50;
		this.reciclado = 0;
		this.container = new ItemContainer ();
		this.instruction = new Instruction();
		this.id="";
	}
	
	public void startEngine(){
		String linea;
		instruction = new Instruction ();
		String LINE_SEPARATOR = System.getProperty("line.separator");
		
		
		System.out.println(this.initialPlace.toString()+ LINE_SEPARATOR + "   * My power is " + power+ LINE_SEPARATOR + "   * My recycled material is: " + reciclado 
				+ LINE_SEPARATOR + "WALL·E is looking at direction "+ this.direction ); // Primer punto
		
		while ( this.instruction.getAction() != Action.QUIT && !this.initialPlace.isSpaceship()&& this.power > 0 ){ //Segundo punto
		 // Punto 2b
			System.out.print("WALL·E > ");
			linea = leer.nextLine();			
			this.instruction = Interpreter.generateInstruction ( linea); // Genera la istruccion del comando introducido. Punto 2c			
			//Punto 2d1 implementado en interpreter
			comprobarInstruccion();
		if (this.initialPlace.isSpaceship()){
			System.out.println ( "WALL·E says: I am at my space ship. Bye Bye");
		}	
		if ( power == 0){
			System.out.println ("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
	}
	}
	
	public void instruccionTurn (){
		this.power--;
		if ( this.instruction.getRotation() == Rotation.LEFT) {
			System.out.println("---Orden: turn left---");
			if ( this.direction == Direction.EAST){
				this.direction = Direction.NORTH;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " + this.direction );
			}
			else if ( this.direction == Direction.NORTH ){
				this.direction = Direction.WEST;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
			}
			else if ( this.direction == Direction.WEST){
				this.direction = Direction.SOUTH;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
			}
			else if ( this.direction == Direction.SOUTH){
				this.direction = Direction.EAST;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
			}
		}else if (this.instruction.getRotation() == Rotation.RIGHT){
			System.out.println("---Orden: turn right---");
			if ( this.direction == Direction.EAST){
				this.direction = Direction.SOUTH;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
			}
			else if ( this.direction == Direction.NORTH ){
				this.direction = Direction.EAST;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
			}
			else if ( this.direction == Direction.WEST){
				this.direction = Direction.NORTH;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
			}
			else if ( this.direction == Direction.SOUTH){
				this.direction = Direction.WEST;
				System.out.println("   * My power is " + this.power);
				System.out.println("   * My recycled material is: " + this.reciclado);
				System.out.println( "WALL·E is looking at direction " +  this.direction );
				}
			}
			else {
				System.out.println( "WALL·E > WALL·E says: I do not understand. Please repeat" );
				
			}
			
		}		
	public String direccion(){
		String dir="";
		if ( direction == Direction.NORTH ){
			dir = " NORTH";
		} else if ( direction == Direction.SOUTH ){
			dir = " SOUTH";
		} else if ( direction == Direction.WEST ){
			dir = " WEST";
		} else if ( direction == Direction.EAST ){
			dir = " EAST";
		} else {
			dir = "WALL·E says: I do not understand. Please repeat";
		}
		return dir;
		
	}
	public void instruccionMove (){
		int i=0;		
		Street calle=this.cityMap.lookForStreet(this.initialPlace,this.direction);
		if(calle==null){
			i=0;
		}
		else{
			if(calle.isOpen()){
				i=1;
				this.initialPlace=calle.nextPlace(this.initialPlace);
			}
			else{
				i=2;
			}			
		}
		if(i==0){
			System.out.println ( " There is not a street in that direction.");
		}
		if(i==1){
			this.power -=5;
			System.out.println("WALL·E says: Moving in direction"+ this.direccion()); 
			System.out.println(initialPlace.toString());
			System.out.println("   * My power is " + power + LINE_SEPARATOR + "   * My recycled material is: " + reciclado); // Primer punto
			System.out.println( "WALL·E is looking at direction " + this.direction );
		}
		else{
			System.out.println("WALL·E says: Arrggg, there is a street but it is closed!");
		}
	}	
	
	public void instruccionOperate (){
		Item it = null;
		this.id = this.instruction.getId();
		System.out.println("---Orden: operate " + id + "---");
		
		if ( this.container.existsItem(id) ){
			it = this.container.getItem(id);
			if (it.canBeUsed()){
				if(!it.use(this, this.initialPlace)){
					System.out.println( "WALL·E says: I have problems using the object " + id);
				}
				
				if (it.getTimes()== 0  ){
					this.container.removeItem(it);
					//System.out.println ( "gettimes = 0");
				}
			}else{
				System.out.println( "WALL·E says: What a pity! I have no more " + id + " in my inventory");
			}			
		}
		else{
			System.out.println("WALL·E says: I have not such object");
		}
	}
	
	public void instruccionPick (){
		Item item = null;
		String aux = "";
		aux = instruction.getId();
		System.out.println("---Orden: pick " + aux + "---");
		
		if( this.initialPlace.existItem(aux)){
			item = this.initialPlace.pickItem(aux);
			
			if( this.container.addItem(item)){
				System.out.println("WALL·E says: I am happy! Now I have  " + item.id );
				
			}
		} 
		else 
		{
		System.out.println("WALL·E says: Ooops, this place has not the object " + aux);
		}
	}
	
	public void instruccionScan (){
		Item it=null;
		id=this.instruction.getId();
		it = container.getItem(id);
		if(id==""){
			System.out.println("---Orden: scan---");
			if (container.numberOfItems() == 0){
				System.out.println ("WALL·E says: My inventory is empty");
			} else {
				System.out.println ("WALL·E says: I am carrying the following items");
				System.out.println(this.container.toString()+ LINE_SEPARATOR);
			}
			
		}
		else{		
			System.out.println("---Orden: scan " + id + "---");
			if (container.existsItem(id)){
				System.out.print("WALL·E says: ");
				System.out.println(it.toString());
			}
			else{
				System.out.println( "WALL·E says: I have not such object");
			}	
		}
	}
	
	public void comprobarInstruccion (){

		String LINE_SEPARATOR = System.getProperty("line.separator");
		
		if ( this.instruction.getAction() == Action.HELP ){
			System.out.println("---Orden: help---");
			System.out.println ( Interpreter.interpreterHelp()); //Punto 2d2
			
		}
		else if ( this.instruction.getAction() == Action.QUIT){ // Punto 2d3
			//QUITA LA SIMULACION
			System.out.println("---Orden: quit---");
			System.out.println ("WALL·E > WALL·E says: I have communication problems. Bye bye" );
		}
		else if(this.instruction.getAction() == Action.TURN ){ // Punto 2d4
			
			instruccionTurn();
		}
		
		else if (this.instruction.getAction() == Action.PICK){ 
			this.instruccionPick();
			
		}
		else if (this.instruction.getAction() == Action.SCAN){ 
			instruccionScan ();
			
		}
		else if (this.instruction.getAction() == Action.OPERATE){ 
			
			instruccionOperate();
		}
		else if (this.instruction.getAction() == Action.MOVE){ // Punto 2d5
			System.out.println("---Orden: move---");
			instruccionMove();
		}
		else {
			System.out.println( "WALL·E > WALL·E says: I do not understand. Please repeat" + (LINE_SEPARATOR));
		}
	
	}
	
	public void addFuel(int fuel){
		this.power += fuel;
		if(this.power<0){
			this.power=0;
		}
	}
	
	public Street getHeadingStreet(){
		return this.cityMap.lookForStreet( this.initialPlace, this.direction);
	}
	public int getPower(){
		return this.power;
	}

	public void setPower(int power2) {
		this.power=power2;
		}
	
	public void addRecycledMaterial(int weight){
		this.reciclado += weight;		
	}
	
	public int getRecycledMaterial(){
		return this.reciclado;
	}

	public void setReciclado( int reciclado) {
		// TODO Auto-generated method stub
		this.reciclado = reciclado;
		
	}

	public int getFuel() {
		return this.power;
	}

	public void setFuel(int power) {
		this.power = power;
	}
	public Direction getDirection() {
		return direction;
	}


}

