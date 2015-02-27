package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * This instruction tries to pick an Item from the current place and puts it the robot inventory.
 * This instruction works if the user writes PICK id or COGER id
 *
 */
public class PickInstruction implements Instruction{
	private NavigationModule navega;
	private String id;
	private ItemContainer container;
	
	/**
	 * Constructor without parameters 
	 */
	public PickInstruction (){
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
		this.container = robotContainer;
	}

	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException
	 */
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item item = null;
		 
		if (!this.navega.getInitialPlace().existItem(this.id)){
			throw new InstructionExecutionException ();
		}
		if ( this.container.containsItem(id)){
			throw new InstructionExecutionException ();
		}
		else{
			item = this.navega.pickItemFromCurrentPlace(id);
			this.container.addItem(item);
			System.out.println("WALL·E says: I am happy! Now I have " + this.id );
			if ( item == null){
				throw new InstructionExecutionException ();
			}
		}
		
	}

	/** Returns a description of the Instruction syntax. 
	 * The string does not end with the line separator.
	 *  It is up to the caller adding it before printing.
	 * @returns the Instruction syntax PICK|COGER <id>
	 */
	public String getHelp() {
		// TODO Auto-generated method stub
		return "PICK|COGER <id>";
	}

	/**
	* Parses the String returning an instance of PickInstruction or throwing a WrongInstructionFormatException().
	*@param cad - text String to parse
	*@returns Instruction Reference to an instance of PickInstruction
	@throws WrongInstructionFormatException
	*/
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		
		String []cadena = cad.split(" ");
		if ( cadena.length != 2){
			throw new WrongInstructionFormatException ();
		} 
		else if ( !cadena [0].equalsIgnoreCase("PICK") && !cadena[0].equalsIgnoreCase("COGER")){
			throw new WrongInstructionFormatException ();
		}
		else {
			this.id = cadena [1];
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
		Item item = null;
		 
		if (!this.navega.getInitialPlace().existItem(this.id)){
			throw new InstructionExecutionException ();
		}
		if ( this.container.containsItem(id)){
			throw new InstructionExecutionException ();
		}
		else{
			item = this.navega.pickItemFromCurrentPlace(id);
			this.container.addItem(item);
			if ( item == null){
				throw new InstructionExecutionException ();
			}
		}
		
	}

}
