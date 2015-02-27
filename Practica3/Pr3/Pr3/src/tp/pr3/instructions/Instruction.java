package tp.pr3.instructions;
import tp.pr3.*;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
/**
 * his interface represents an instruction supported by the application.
 *  Every instruction that the robot may perform implements this interface.
 *  It forces instructions to provide with the implementation of four different methods:
   Parse method tries to parse a string with the information of the instruction the class represents
   Help method returns a string with an explanation of the kind of expression that the parse method supports
   ConfigureContext method establishes the context needed to execute the instruction
   Execute method performs the actual work of the instruction, executing it.
   The execute method does not have any parameter. 
   Therefore the context of execution must be given to the instruction object prior to its invocation using the configureContext method.
   Note that the actual context depends on the subclass because the information needed varies between instructions.
 * @author Nerea
 *
 */
public interface Instruction {
	/**
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container)
	 *  even though the actual implementation of execute() may not require it.
	 * @param engine
	 * @param navigation
	 * @param robotContainer
	 */
	public abstract void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException
	 */
	public abstract void execute()
			throws InstructionExecutionException;
	/**
	 * Returns a description of the Instruction syntax. 
	 * The string does not end with the line separator.
	 *  It is up to the caller adding it before printing.
	 * @return
	 */
	public abstract String getHelp();
	/**
	 * Parses the String returning an instance its corresponding subclass if the string fits the instruction's syntax.
	 *  Otherwise it throws an WrongInstructionFormatException. Each non abstract subclass must implement its corresponding parse.
	 * @param cad
	 * @return
	 * @throws WrongInstructionFormatException
	 */
	public abstract Instruction parse (String cad)
			throws WrongInstructionFormatException;

}
