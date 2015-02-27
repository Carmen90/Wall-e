package tp.pr4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.instructions.MoveInstruction;
import tp.pr4.instructions.TurnInstruction;
import tp.pr4.instructions.QuitInstruction;
import tp.pr4.instructions.RadarInstruction;
import tp.pr4.instructions.HelpInstruction;
import tp.pr4.instructions.PickInstruction;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.ScanInstruction;
import tp.pr4.instructions.OperateInstruction;
import tp.pr4.instructions.Instruction;


/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * The interpreter is in charge of converting the user input in an instruction for the robot.
 *
 */
public class Interpreter {
	private static List <Instruction> miLista;
	static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Constructor without parameters 
	 */
	public Interpreter(){
		miLista = new ArrayList<Instruction>();
		
	}
	/**
	 * Método que mete en una lista las distintas operaciones que se pueden realizar
	 * @return miLista lista de instrucciones posibles 
	 */
	public static List<Instruction> anadirInst(){
		
		List<Instruction> miLista = Arrays.asList(new DropInstruction(), new HelpInstruction(), 
				new OperateInstruction(), new PickInstruction(),new QuitInstruction(), new RadarInstruction(),
				new ScanInstruction(),new TurnInstruction(), new MoveInstruction());
		return miLista;
		
	}
	
	/**
	 * The interpreter is in charge of converting the user input in an instruction for the robot.
	 * @param line
	 * @return The instruction read from the given line. If the instruction is not correct, then it throws an exception.
	 * @throws WrongInstructionFormatException
	 */
	 public static Instruction generateInstruction(java.lang.String line) 
			 						throws WrongInstructionFormatException{
		 Instruction inst = null;
		 boolean encontrado = false;
		 Interpreter.miLista = anadirInst();
		 Iterator <Instruction> e = miLista.iterator();
		 while(e.hasNext() && !encontrado){
				try {
					inst = e.next().parse(line);
					encontrado = true;
				} catch (WrongInstructionFormatException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
		 }
		 if ( !encontrado){
			 throw new WrongInstructionFormatException();
		 }
		 
		 return inst;
	 }
	/**
	 * It returns information about all the instructions that the robot understands
	 * @return: A string with the information about all the available instructions
	 */
	public static java.lang.String interpreterHelp(){
		String ayuda=" ";
		for (int i=0; i<9 ;i++){
			ayuda=ayuda + miLista.get(i).getHelp() + LINE_SEPARATOR;
		}
		return ayuda;
	}

}
