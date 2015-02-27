package tp.pr3.instructions;


import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
/**
 * Its execution rotates the robot This Instruction works
 *  if the robot writes TURN LEFT or RIGHT or GIRAR LEFT or RIGHT
 *
 */
public class TurnInstruction implements Instruction{
	private NavigationModule navega;
	private RobotEngine robot;
	private Rotation rota;
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public TurnInstruction (){
		this.navega = new NavigationModule();
		this.rota = Rotation.UNKNOWN;
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
		this.navega.rotate(rota);
		this.robot.addFuel(-5);
		System.out.println("WALL·E is looking at direction " + this.navega.getCurrentHeading()+ LINE_SEPARATOR + this.robot.printRobotState());
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "TURN|GIRAR<LEFT|RIGHT>";
	}

	@Override
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");
		if ( cadena.length != 2){
			throw new WrongInstructionFormatException ();
		}else if ( !cadena[0].equalsIgnoreCase("TURN") && !cadena[0].equalsIgnoreCase("GIRAR")){
			throw new WrongInstructionFormatException ();
		}
		else if( !cadena[1].equalsIgnoreCase("LEFT") && !cadena[1].equalsIgnoreCase("RIGHT") ){
			throw new WrongInstructionFormatException ();
		}
		else{
			this.rota = Rotation.valueOf(cadena[1].toUpperCase());
			return this;
		}
	}

}
