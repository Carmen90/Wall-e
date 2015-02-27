package tp.pr3.instructions;
import tp.pr3.*;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public interface Instruction {
	
	public abstract void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	
	public abstract void execute()
			throws InstructionExecutionException;
	
	public abstract String getHelp();
	
	public abstract Instruction parse (String cad)
			throws WrongInstructionFormatException;

}
