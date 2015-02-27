package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class QuitInstruction implements Instruction{
	private RobotEngine robot;
	
	public QuitInstruction (){
		this.robot = new RobotEngine();
	}

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
		
	}

	
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		System.out.println ("WALL·E says: I have communication problems. Bye bye" );
		this.robot.requestQuit();
		
	}


	public String getHelp() {
		// TODO Auto-generated method stub
		return "QUIT|SALIR";
	}

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

}
