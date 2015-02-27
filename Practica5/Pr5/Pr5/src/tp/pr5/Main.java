package tp.pr5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.console.Console;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Application entry-point. The application admits a parameter -m | --map with the name of the map file to be used and a 
 * parameter -i | --interface with the type of interface (console, swing or both)
 * If no arg is specified (or more than one file is given), it prints an error message (in System.err) and the application
 * finishes with an error code (-1).
 * If the map file cannot be read (or it does not exist), the application ends with a different error code (-2).
 * If the interface arg is not correct (console or swing) the application prints a message and the application finishes
 * with an error code (-3). If the interface arg is not included it starts the application in console mode. Otherwise, 
 * the simulation starts and eventually the application will end normally (return code 0).
 *
 */
public class Main {
	static boolean consola = false;
	static boolean interfaz = false;
	static boolean swing = false;
	static boolean help = false;
	static boolean both = false;
	static FileInputStream file = null;

	static String archivo = null;

	/**
	* Método principal que ejecuta el programa
	*/
	public static void main(String[] args) {
		
		Options opcion = new Options();
		opcion = addOptions();
		
		CommandLineParser parser = new BasicParser();
		
		try{
			CommandLine linea = parser.parse( opcion, args);
			parsearIns (linea);
		}

		catch(ParseException e){
			System.err.println (  "Parsing Error Motivo:."  + e.getMessage ()  );			
			System.exit(1);
		}
		ejecuteErrores();
	}
	
	/**
	 * Método que añade las opciones a ejecutar
	 * @return opciones
	 */
	public static Options addOptions(){
		Options opcion = new Options();
		opcion.addOption("h", false, "Show this help message");
		opcion.addOption("m", "map", true, "File with the description of the city");
		opcion.addOption("i", "interface", true, "The type of interface: console, swing or both");
		return opcion;
	}
	
	/**
	 * Método que se encarga de mostrar la ayuda. Muestra las distintas acciones qu epuedes ralizar 
	 */
	public static void mostrarHelp (){
		System.out.println("Execute this assignment with these parameters:");
	    System.out.println("usage: tp.pr5.Main [-h] [-i <type>] [-m <mapfile>]");
	    System.out.println(" -h,--help               Shows this help message");
	    System.out.println(" -i,--interface <type>   The type of interface: console, swing or both");
	    System.out.println(" -m,--map <mapfile>      File with the description of the city");
	}
	
	/**
	 * Método que, dada una linea, se encarga de ver las opciones que contiene motificando los valores de los atributos de la clase
	 * en función de las opciones seleccionadas en la linea
	 * @param linea
	 */
	public static void parsearIns(CommandLine linea){

		String tipo = null;
		  
		if ( linea.hasOption('h')){
			help = true;
			mostrarHelp();
		}
		if ( linea.hasOption('m')){
			archivo = linea.getOptionValue('m');
		}
		if (linea.hasOption('i')){
			interfaz = true;
			tipo = linea.getOptionValue('i');
			if ( tipo.equalsIgnoreCase("Console")){
				consola = true;
			}
			else if (tipo.equalsIgnoreCase("swing")){
				swing = true;
			}
			else if ( tipo.equalsIgnoreCase("both")){
				both = true;
			}
			
		}
	}

	/**
	 * Método que se encarga de cargar el fichero del mapa
	 */
	public static void cargarFile(){
		File f = new File (archivo);
		 
		try {
			file = new FileInputStream (f);
		} catch (FileNotFoundException e) {
			System.err.println ("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
			System.exit(2);
		}
	}
	
	/**
	 * Método que dada una ciudad inicializada vacía carga la cuidad segun un mapa dado. Crea el robot, así como la ciudad
	 * a partir del mapa dado 
	 * @param city almacen para el mapa de la ciudad
	 */
	public static void executeMain(CityLoaderFromTxtFile city ){
		RobotEngine engine;
		try {
			
			engine = new RobotEngine(city.loadCity(file), city.getInitialPlace(), Direction.NORTH);
			if ( consola){
				
				ConsoleController control = new ConsoleController(engine);	
				@SuppressWarnings("unused")
				Console console = new Console (control);
				engine.requestStart();
				control.startController();
			
			}
			else if ( swing){
				GUIController gui = new GUIController(engine);
				MainWindow vistaGui = new MainWindow (gui);
			//	MainWindow vista2 = new MainWindow (gui);
				gui.startController();
				vistaGui.arranca();
			//	vista2.arranca();
				
			}
			else if ( both){
				ConsoleController controlConsole = new ConsoleController(engine);
				@SuppressWarnings("unused")
				Console console = new Console (controlConsole);
				
				GUIController gui = new GUIController(engine);
				MainWindow vista = new MainWindow (gui);
				
				gui.startController();
				vista.arranca();
				
			}
			
		} catch (IOException e) {
			System.err.println ("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
			System.exit(2);
		}
	}
	
	/**
	 * Método que muestra los errores de cada tiepo que se pueden dar al ejecutar el main. Se muestran por System.err y cada uno de ellos tiene 
	 * una salida de la aplicación diferente
	 */
	public static void ejecuteErrores(){
		if(!help){
			if ( archivo != null){
				
				CityLoaderFromTxtFile city = new CityLoaderFromTxtFile();
				if ( interfaz){
					if ( consola || swing || both){
						cargarFile();
						executeMain(city);
					 }
					else{
						System.err.println ("Wrong type of interface");
						System.exit(3);
					}
				}
				else{
					System.err.println ("Interface not specified");
					System.exit(1);
				}
			}
			else{
				System.err.println("Map file not specified");
				System.exit(1);
			}
		}
	}
}
