package tp.pr3.instructions;
import tp.pr3.RobotEngine;
import tp.pr3.NavigationModule;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class HelpInstruction implements Instruction{
	private RobotEngine robot;
	
	public HelpInstruction (){
		this.robot = new RobotEngine ();
	}
	
	public void configureContext(RobotEngine engine,
            NavigationModule navigation,
            ItemContainer robotContainer){
		this.robot = engine;
		
	}

	@Override
	public void execute() 
			throws InstructionExecutionException{
		// TODO Auto-generated method stub
		this.robot.requestHelp();
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "HELP|AYUDA";
	}

	@Override
	public Instruction parse(java.lang.String cad) 
			throws WrongInstructionFormatException{
		cad = cad.trim();//elimina los espacios en blanco.
		
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		// TODO Auto-generated method stub
		if(!cadena[0].equalsIgnoreCase("HELP") && !cadena[0].equalsIgnoreCase("AYUDA") 
				|| cadena.length > 1)
		{
			throw new WrongInstructionFormatException();
		}
		else {
			return this;
		}
	}	
}
