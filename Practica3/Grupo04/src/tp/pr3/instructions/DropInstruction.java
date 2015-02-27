package tp.pr3.instructions;
import tp.pr3.RobotEngine;
import tp.pr3.NavigationModule;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
import tp.pr3.items.Item;
/**
 * This instruction drops an Item from the current place and puts it the robot inventory.
 *  This instruction works if the user writes DROP or SOLTAR
 *
 */
public class DropInstruction implements Instruction{
	private NavigationModule navega;
	private String id;// = " ";
	private ItemContainer container;
	
	public DropInstruction (){
		this.navega = new NavigationModule ();
		this.container = new ItemContainer ();
		this.id = "";
	}
	/**
	 * Fixes the current context, i.e., the robot engine and the navigation module
	 */
	public void configureContext(RobotEngine engine,
            NavigationModule navigation,
            ItemContainer robotContainer){
		this.navega = navigation;
		this.container = robotContainer;
	}
    /**
     * The robot drops an Item from its inventory in the current place, if the item exists
     */
	public void execute() 
			throws InstructionExecutionException{
		Item it = null;

		if(this.container.containsItem(this.id)){
			if ( !navega.findItemAtCurrentPlace (this.id)){
				it = this.container.getItem(this.id);
				this.container.removeItem(it);
				this.navega.getCurrentPlace().addItem(it);
				System.out.println("Great! I have dropped " + this.id );
			}else
			{
				throw new InstructionExecutionException();
			}
		}
		else
		{
			System.out.println("You do not have any Cord.");
			throw new InstructionExecutionException();
		}
	}
	/**
	 * Instruction help
	 * returns the instruction syntax
	 */
	public String getHelp(){
		return " DROP|SOLTAR <id>";
	}
	/**
	 * Parses the String returning an instance of DropInstruction
	 *  or throwing a WrongInstructionFormatException()
	 *  returns Instruction Reference to an instance of DropInstruction
	 */
	public Instruction parse (String cad)
			throws WrongInstructionFormatException{
		
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		
		if ( cadena.length != 2){
			throw new WrongInstructionFormatException();
		}
		else if (!cadena [0].equalsIgnoreCase("DROP") && !cadena [0].equalsIgnoreCase("SOLTAR")){
			throw new WrongInstructionFormatException();
		}
		else{
			this.id = cadena[1];
			return this;
		}
	}


	
}