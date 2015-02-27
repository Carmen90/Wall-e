package tp.pr4.instructions;


import tp.pr4.ConsoleMesages;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
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
		this.robot = engine;
		
	}
	/**
	* Turns the robot left or right
	* @throws InstructionExecutionException - When the rotation is unknown
	*/
	@Override
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.setDirection(this.navega.getDirection().rotate (rota));
		this.robot.addFuel(-5);
		ConsoleMesages.lookingAtMesage(robot, navega);
		
	}
	/** Returns a description of the Instruction syntax. 
	 * The string does not end with the line separator.
	 *  It is up to the caller adding it before printing.
	 * @returns the Instruction syntax TURN|GIRAR<LEFT|RIGHT>
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "TURN|GIRAR<LEFT|RIGHT>";
	}
	/**
	* Parses the String returning a TurnInstruction instance or throwing a WrongInstructionFormatException()
	* @param cad - text String to parse
	* @returns Instruction Reference pointing to an instance of a Instruction subclass, if it is corresponding to the String cad
	* @throws WrongInstructionFormatException - When the String is not TURN LEFT or RIGHT or GIRAR LEFT or RIGHT
	*/
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

	/**
	* Turns the robot left or right
	* @throws InstructionExecutionException - When the rotation is unknown
	* For swing
	*/
	@Override
	public void executeVista() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.setDirection(this.navega.getDirection().rotate (rota));
		this.robot.addFuel(-5);
	}

}
