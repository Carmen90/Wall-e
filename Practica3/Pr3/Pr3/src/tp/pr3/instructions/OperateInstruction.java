package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;;
/**
 * The Instruction for using an item.
 *  This Instruction works if the user writes OPERATE id or OPERAR id
 *
 */
public class OperateInstruction implements Instruction{
	private ItemContainer container;
	private RobotEngine robot;
	private NavigationModule navega;
	private String id;
	
	public OperateInstruction (){
		this.container = new ItemContainer ();
		this.id = " ";
		this.navega = new NavigationModule ();
		this.robot = new RobotEngine ();
	}
	
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.container = robotContainer;
		this.robot = engine;
		this.navega = navigation;
	}


	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item it = null;
		//System.out.println("---Orden: operate " + id + "---");
		
		if ( this.container.containsItem(this.id) ){
			it = this.container.getItem(this.id);
				if(!it.use(robot, this.navega)){
					throw new InstructionExecutionException ();
				}
				
				if (it.getTimes()== 0 ){
					this.container.removeItem(it);
				}			
		}
		else{
			throw new InstructionExecutionException ();
		}
		
	}

	public String getHelp() {
		// TODO Auto-generated method stub
		return "OPERATE|OPERAR <ID>";
	}

	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub

		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posiciï¿½n del array*/
		
		if ( cadena.length != 2){
			throw new  WrongInstructionFormatException();
		}
		else if ( !cadena[0].equalsIgnoreCase("OPERATE") && !cadena[0].equalsIgnoreCase("OPERAR")){
			throw new  WrongInstructionFormatException();
		}
		else
		{
			this.id = cadena[1];
			return this;
		}
	}

}
