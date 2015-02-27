package tp.pr3;

import tp.pr3.instructions.DropInstruction;
import tp.pr3.instructions.HelpInstruction;
import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.MoveInstruction;
import tp.pr3.instructions.OperateInstruction;
import tp.pr3.instructions.PickInstruction;
import tp.pr3.instructions.QuitInstruction;
import tp.pr3.instructions.RadarInstruction;
import tp.pr3.instructions.ScanInstruction;
import tp.pr3.instructions.TurnInstruction;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class Interpreter {
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	private static String help ="The valid instructions for WALL-E are:" + LINE_SEPARATOR +
							"     MOVE"+ LINE_SEPARATOR + "     " + "TURN <LEFT | RIGHT>" + LINE_SEPARATOR 
							+ "     PICK <id>" + LINE_SEPARATOR +
							"     SCAN [ <id> ]" + LINE_SEPARATOR + "     OPERATE <id>" + LINE_SEPARATOR +
							"     HELP" + LINE_SEPARATOR + "     QUIT" + LINE_SEPARATOR +
							"     RADAR" + LINE_SEPARATOR + "     DROP <id>"+ LINE_SEPARATOR;
	private static Instruction instruction;
	

	
	public Interpreter(){ 		
	}
	
	public static java.lang.String interpreterHelp(){
		return help;
	}
	
	public static Instruction generateInstruction(java.lang.String line)
			throws WrongInstructionFormatException{
		//line= line.trim();//elimina los espacios en blanco.
		
		String [] cadena = line.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		
		
	if ( cadena.length ==  1){
		if(cadena[0].equalsIgnoreCase("move")){
			instruction = new MoveInstruction ();
		}
		else if(cadena[0].equalsIgnoreCase("QUIT")){
			instruction = new QuitInstruction ().parse(line);
		}
		else if(cadena[0].equalsIgnoreCase("HELP")){
		
			instruction = new HelpInstruction().parse(line);
		}
		else if(cadena[0].equalsIgnoreCase("TURN")){
			
			instruction = new TurnInstruction().parse(line);
		}
		else if ( cadena[0].equalsIgnoreCase("SCAN")){
			instruction = new ScanInstruction().parse(line);
		}
		else if ( cadena[0].equalsIgnoreCase("Radar")){
			instruction = new RadarInstruction().parse(line);
		}
		else {
			throw new WrongInstructionFormatException();
		}
	}
	else if(cadena.length > 1){
		if(cadena.length==2){
			if ( cadena[0].equalsIgnoreCase("PICK")){
				instruction= new PickInstruction().parse(line);
			}
			else if ( cadena[0].equalsIgnoreCase("SCAN")){
				instruction= new ScanInstruction().parse(line);
			}
			else if ( cadena[0].equalsIgnoreCase("OPERATE")){
				instruction= new OperateInstruction().parse(line);
			}
			else if ( cadena[0].equalsIgnoreCase("TURN")){
				instruction= new TurnInstruction().parse(line);
			}
			else if ( cadena[0].equalsIgnoreCase("Drop")){
				instruction = new DropInstruction().parse (line);
			}
		}
	
	}
	return instruction;
	
	}


}
