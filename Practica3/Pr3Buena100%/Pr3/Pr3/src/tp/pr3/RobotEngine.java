package tp.pr3;

import java.util.Scanner;

import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
/**
 * This class represents the robot engine. 
 * It controls robot movements by processing the instructions introduced with the keyboard.
 * The engine stops when the robot arrives at the space ship, runs out of fuel or receives a quit instruction.
 * The robot engine is also responsible for updating the fuel level and the recycled material according to
 *  the actions that the robot performs in the city.
 * The robot engine also contains an inventory where the robot stores the items that it collects from the city
 *
 */
public class RobotEngine {
	private ItemContainer container;
	private Instruction instruction;
	private NavigationModule navega;
	private int power;
	private int reciclado;
	private boolean quit = false;
	private static 	Scanner leer = new Scanner(System.in);
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public RobotEngine (){}
	public RobotEngine(City cityMap, Place initialPlace, Direction direction){
		this.navega = new NavigationModule ( cityMap, initialPlace);
		this.power = 100;
		this.reciclado = 0;
		this.container = new ItemContainer ();
		this.instruction = null;
	}
	public RobotEngine ( Place initialPlace, Direction direction,City cityMap ){
		this.navega = new NavigationModule ( cityMap, initialPlace);
		this.power = 100;
		this.reciclado = 0;
		this.container = new ItemContainer ();
		this.instruction = null;
	}
	/**
	 * It starts the robot engine. Basically, it reads a line,
	 *  the interpreter generates the corresponding instruction and executes it. 
	 * The simulation finishes when the robot arrives at the space ship, 
	 * the user types "QUIT", or the robot runs out of fuel
	 */
	
	public void startEngine(){
		String linea;
		
		System.out.print(this.navega.getInitialPlace().toString());
		System.out.println ( "WALL·E is looking at direction " + this.navega.getCurrentHeading());
		System.out.println (this.printRobotState());
		
		while ( !this.quit && !this.navega.getInitialPlace().isSpaceship() && this.power > 0 ){ 
			System.out.print("WALL·E> ");
			linea = leer.nextLine();
				
			try {
				this.instruction = Interpreter.generateInstruction (linea);
			} catch (WrongInstructionFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			this.communicateRobot(instruction);
				
			if (this.navega.getInitialPlace().isSpaceship()){
				System.out.println ( "WALL·E says: I am at my spaceship. Bye bye");
			}	
			if ( power == 0){
				System.out.println ("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
			}
		}
	}

/**
 * Prints the information about all possible instructions
 */
	public void requestHelp(){
		System.out.println ( Interpreter.interpreterHelp());
	}
/**
 * Adds an amount of fuel to the robot (it can be negative)
 * @param fuel: fuel - Amount of fuel added to the robot
 */
	public void addFuel(int fuel){
		this.power += fuel;
		if(this.power<0){
			this.power=0;
		}
	}
	/**
	 * Increases the amount of recycled material of the robot
	 * @param weight  - Amount of recycled material
	 */
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
	

	/**
	 * It executes an instruction.
	 *  The instruction must be configured with the context before executing it.
	 *  It controls the end of the simulation.
	 *   If the execution of the instruction throws an exception,
	 *    then the corresponding message is printed
	 */
	public void communicateRobot(Instruction c) {
		 c.configureContext(this,this.navega,this.container);
		 try{
			 c.execute();			 
		 }catch(InstructionExecutionException e){
			 e.toString();
		 }
	}
	
	/**
	 * Requests the end of the simulation
	 */
	public void requestQuit(){
		this.quit = true;
	}
	/**
	 * Prints the state of the robot
	 */
	public String printRobotState(){
		return ("      * My power is " + this.power + LINE_SEPARATOR + "      * My reclycled material is " + this.reciclado);
	}

	public int getPower() {
		return this.power;
	}
	


}
