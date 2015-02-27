package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
/**
 * This Instruction shows the description of the current place and the items in it. 
 * This Instruction works if the user writes RADAR
 *
 */
public class RadarInstruction implements Instruction {
	private NavigationModule navega;
	
	public RadarInstruction (){
		this.navega = new NavigationModule ();
	}

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.navega = navigation;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		System.out.print (this.navega.getInitialPlace().toString());
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "RADAR";
	}

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

}
