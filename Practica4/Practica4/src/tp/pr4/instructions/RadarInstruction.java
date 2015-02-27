package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * This Instruction shows the description of the current place and the items in it. 
 * This Instruction works if the user writes RADAR
 *
 */
public class RadarInstruction implements Instruction {
	private NavigationModule navega;
	
	/**
	 * Constructor without parameters 
	 */
	public RadarInstruction (){
		this.navega = new NavigationModule ();
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
	}

	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		System.out.print (this.navega.getInitialPlace().toString());
	}
	/** Returns a description of the Instruction syntax. 
	 * The string does not end with the line separator.
	 *  It is up to the caller adding it before printing.
	 * @returns the Instruction syntax RADAR
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "RADAR";
	}
	/**
	* Parses the String returning an instance of RadarInstruction or throwing a WrongInstructionFormatException()
	* @param cad - text String to parse
	* @returns Instruction Reference to an instance of RadarInstruction
	* @ throws WrongInstructionFormatException - When the String is not RADAR
	*/
	@Override
	public Instruction parse(String cad)
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String []cadena = cad.split(" ");
		if ( !cadena[0].equalsIgnoreCase("RADAR") || cadena.length != 1){
			throw new WrongInstructionFormatException();
		}
		return this;
	}

	/**
	 * Not for swing
	 */
	@Override
	public void executeVista() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		
	}

}
