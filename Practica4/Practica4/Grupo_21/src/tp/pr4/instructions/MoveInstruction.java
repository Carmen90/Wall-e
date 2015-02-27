package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * Its execution moves the robot from one place to the next one in the current direction.
 *  This instruction works if the user writes MOVE or MOVER
 *
 */
public class MoveInstruction implements Instruction {
	private NavigationModule navega;
	private RobotEngine robot;
	
	/**
	 * Constructor without parameters 
	 */
	public MoveInstruction (){
		this.navega = new NavigationModule();
		this.robot = new RobotEngine();
	}

	/**
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container)
	 *  even though the actual implementation of execute() may not require it.
	 * @param engine
	 * @param navigation
	 * @param robotContainer
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.navega = navigation;
		this.robot = engine;
	}

	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException
	 */
	@Override
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.move();
		System.out.println ("WALL·E says: Moving in direction " + this.navega.getCurrentHeading());
		this.robot.addFuel(-5);
		System.out.println(this.navega.getInitialPlace().toString());
		System.out.println(this.robot.printRobotState());
	}
	
	/** Returns a description of the Instruction syntax. 
	 * The string does not end with the line separator.
	 *  It is up to the caller adding it before printing.
	 * @return
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "MOVE|MOVER";
	}

	@Override
	/**
	*Parses the String returning a MoveInstruction instance or throwing a WrongInstructionFormatException()
	*@return Instruction Reference to an instance of MoveInstruction
	*@throws  WrongInstructionFormatException - When the String is not MOVE
	*/
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException{
		// TODO Auto-generated method stub
		
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		if ( !cadena [0].equalsIgnoreCase("MOVE") && !cadena [0].equalsIgnoreCase("MOVER") || cadena.length > 1){
			throw new WrongInstructionFormatException();
		}else {
			return this;
		}
	}

	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException
	 * Just for swing
	 */
	@Override
	public void executeVista() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.move();
		this.robot.addFuel(-5);
	}
	
}
