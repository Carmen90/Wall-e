package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class MoveInstruction implements Instruction {
	private NavigationModule navega;
	private RobotEngine robot;

	public MoveInstruction (){
		this.navega = new NavigationModule();
		this.robot = new RobotEngine();
	}

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.navega = navigation;
		this.robot = engine;
	}

	@Override
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.move();
		System.out.println ("WALL·E says: Moving in direction " + this.navega.getCurrentHeading());
		this.robot.addFuel(-5);
		System.out.println(this.navega.getInitialPlace().toString());
		System.out.println(this.robot.printRobotState());
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "MOVE|MOVER";
	}

	@Override
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException{
		// TODO Auto-generated method stub
		
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		if ( !cadena [0].equalsIgnoreCase("MOVE") && !cadena [0].equalsIgnoreCase("MOVER") || cadena.length > 1){
			throw new WrongInstructionFormatException();
		}else {
			return this;
		}
	}
	
}
