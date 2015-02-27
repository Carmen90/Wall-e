package pr1;

import java.util.Scanner;

public class RobotEngine {
	private Place initialPlace;
	private Direction direction;
	private Street[] cityMap;
	private Interpreter interprete;
	private Instruction instruction;
	/******/
	private int pos;
	
	/*****/
	
	public RobotEngine (Place initialPlace, Direction direction, Street [] cityMap){
		this.cityMap= cityMap;
		this.direction = direction; //this.direction = Direction.NORTH;??
		this.initialPlace= initialPlace;
	}
	
	public void startEngine(){
		String linea;
		
		System.out.println(this.initialPlace.toString() + '\n' +this.direction.toString()); // Primer punto
		Instruction instr = new Instruction ();
		instruction = instr;
		Interpreter inter = new Interpreter ();
		interprete = inter;
		
		while ( this.instruction.getAction() != Action.QUIT && !this.initialPlace.isSpaceShip()){ //Segundo punto
			
			System.out.println( "WALL-E> "); // Punto 2a
			Scanner leer = new Scanner(System.in); // Punto 2b
			linea = leer.nextLine();
			
			/******/
			//leer.close(); // Pongo esto porque sino me da un warning diciendo que no he cerrado leer o algo asi 
			/*****/
			
			this.instruction = this.interprete.generateInstruction ( linea); // Genera la istruccion del comando introducido. Punto 2c
			
			//Punto 2d1 implementado en interpreter
			
			if ( this.instruction.getAction() == Action.HELP ){
				System.out.println ( this.interprete.interpreterHelp()); //Punto 2d2
				
			}
			else if ( this.instruction.getAction() == Action.QUIT){ // Punto 2d3
				//QUITA LA SIMULACION
				System.out.println ( "The game is over.");
			}
			else if(this.instruction.getAction() == Action.TURN ){ // Punto 2d4
				if ( this.instruction.getRotation() == Rotation.LEFT) {
					if ( this.direction == Direction.EAST){
						this.direction = Direction.NORTH;
					}
					else if ( this.direction == Direction.NORTH ){
						this.direction = Direction.WEST;
					}
					else if ( this.direction == Direction.WEST){
						this.direction = Direction.SOUTH;
					}
					else if ( this.direction == Direction.SOUTH){
						this.direction = Direction.EAST;
					}
				}else if (this.instruction.getRotation() == Rotation.RIGHT){
					if ( this.direction == Direction.EAST){
						this.direction = Direction.SOUTH;
					}
					else if ( this.direction == Direction.NORTH ){
						this.direction = Direction.EAST;
					}
					else if ( this.direction == Direction.WEST){
						this.direction = Direction.NORTH;
					}
					else if ( this.direction == Direction.SOUTH){
						this.direction = Direction.WEST;
					}
				}
			}
			else if (this.instruction.getAction() == Action.MOVE){ // Punto 2d5
				
				/*La pos declarada arriba como atributo la declaro para poder usara aqui con el array de 
				 * citymap porque no sabia muy bien como hacerlo de otra forma
				 */
				
				if (this.cityMap[pos].comeOutFrom ( this.initialPlace, this.direction)){
					this.initialPlace = this.cityMap[pos].nextPlace(this.initialPlace);
					System.out.println(this.initialPlace.toString());
					pos++;
				}
				else {
					System.out.println ( " There is not a street in that direction.");
				}
			}
			
		}
	}

}
