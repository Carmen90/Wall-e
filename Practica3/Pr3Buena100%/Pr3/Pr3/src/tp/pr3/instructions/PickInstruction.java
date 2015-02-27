package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;
/**
 * This instruction tries to pick an Item from the current place and puts it the robot inventory.
 *  This instruction works if the user writes PICK id or COGER id
 *
 */
public class PickInstruction implements Instruction{
	private NavigationModule navega;
	private String id;
	private ItemContainer container;
	
	public PickInstruction (){
		this.navega = new NavigationModule ();
	}
	
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		
		this.navega = navigation;
		this.container = robotContainer;
	}

	
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


	public String getHelp() {
		// TODO Auto-generated method stub
		return "PICK|COGER <id>";
	}

	
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

}
