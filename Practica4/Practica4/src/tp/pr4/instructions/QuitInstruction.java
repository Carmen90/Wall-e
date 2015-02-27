package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * Its execution request the robot to finish the simulation 
 * This Instruction works if the user writes QUIT or SALIR
 *
 */
public class QuitInstruction implements Instruction{
	private RobotEngine robot;
	
	/**
	 * Constructor without parameters 
	 */
	public QuitInstruction (){
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
		this.robot = engine;
		
	}

	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException
	 */
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		System.out.println ("WALL·E says: I have communication problems. Bye bye" );
		this.robot.requestQuit();
		
	}
	/** Returns a description of the Instruction syntax. 
	 * The string does not end with the line separator.
	 *  It is up to the caller adding it before printing.
	 * @returns the Instruction syntax QUIT|SALIR
	 */

	public String getHelp() {
		// TODO Auto-generated method stub
		return "QUIT|SALIR";
	}
	/**
	* Parsers the String returning an instance of QuitInstruction or throwing a WrongInstructionFormatException()
	* @ param cad - text String to parse
	@ returns Instruction reference to an instance of QuitInstruction
	@ throws WrongInstructionFormatException - When the String is not QUIT | SALIR
	*/
	@Override
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");
		
		if ( !cadena[0].equalsIgnoreCase("QUIT") && !cadena[0].equalsIgnoreCase("SALIR")
				|| cadena.length != 1){
			throw new WrongInstructionFormatException ();
		}
		else{
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
		this.robot.requestQuit();
	}

}
