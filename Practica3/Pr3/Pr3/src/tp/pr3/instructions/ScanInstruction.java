package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
/**
 * The execution of this instruction shows the information of the inventory 
 * of the robot or the complete description about the item with identifier id contained in the inventory
 *  This Instruction works if the player writes SCAN or ESCANEAR (id is not mandatory)
 *
 */
public class ScanInstruction implements Instruction {
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	private ItemContainer container;
	private String id = " ";

	public ScanInstruction (){
		this.container = new ItemContainer();
	}
	
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.container = robotContainer;
		
	}

	@Override
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		if ( this.id != ""){
			if ( !this.container.containsItem(id)){
				throw new InstructionExecutionException();
			}else{
				if (container.containsItem(id)){
					System.out.print("WALL·E says: ");
					System.out.println(container.getItem(id).toString());
				}
			}

		}else {
			System.out.println ("WALL·E says: I am carrying the following items");
			System.out.println (this.container.toString());
		}
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "SCAN|ESCANEAR [id]";
	}

	@Override
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");
		
		if( !cadena[0].equalsIgnoreCase("SCAN") && !cadena[0].equalsIgnoreCase("ESCANEAR")){
				throw new WrongInstructionFormatException();
			}
		else{
			if ( cadena.length > 2){
				throw new WrongInstructionFormatException();
			}
			else if ( cadena.length == 2){
				this.id = cadena[1];
			}
			else{
				this.id ="";
			}
		return this;
		}
	}
}
