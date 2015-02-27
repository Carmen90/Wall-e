package tp.pr4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.gui.MainWindow;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Application entry-point. The application admits a parameter -m | --map with the name of the map file to be used and a 
 * parameter -i | --interface with the type of interface (console or swing)
 * If no arg is specified (or more than one file is given), it prints an error message (in System.err) and the application
 * finishes with an error code (-1).
 * If the map file cannot be read (or it does not exist), the application ends with a different error code (-2).
 * If the interface arg is not correct (console or swing) the application prints a message and the application finishes
 * with an error code (-3). If the interface arg is not included it starts the application in console mode. Otherwise, 
 * the simulation starts and eventually the application will end normally (return code 0).
 *
 */
public class Main {

	/**
	* The main metod that ejecutes the program 
	*/
	public static void main(String[] args) {
		FileInputStream file = null;
		boolean consola = false;
		boolean interfaz = false;
		boolean swing = false;
		boolean help = false;
		String archivo = null;
		String tipo = null;
		
		CommandLineParser parser = new BasicParser();
		
		Options opcion = new Options();
		opcion.addOption("h", false, "Show this help message");
		opcion.addOption("m", "map", true, "File with the description of the city");
		opcion.addOption("i", "interface", true, "The type of interface: console or swing");
		  
		  

		try{
			CommandLine linea = parser.parse( opcion, args);
			  
			if ( linea.hasOption('h')){
				help = true;
				System.out.println("Execute this assignment with these parameters:");
			    System.out.println("usage: tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]");
			    System.out.println(" -h,--help               Shows this help message");
			    System.out.println(" -i,--interface <type>   The type of interface: console or swing");
			    System.out.println(" -m,--map <mapfile>      File with the description of the city");
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
				
			}
		}
		catch(ParseException e){
			System.err.println (  "Parsing Error Motivo:."  + e.getMessage ()  );			
			System.exit(1);
		}
		
		if (!help){
			if ( archivo != null){
				
				CityLoaderFromTxtFile city = new CityLoaderFromTxtFile();
				if ( interfaz){
					if ( consola || swing){
						File f = new File (archivo);
						 
						try {
							file = new FileInputStream (f);
						} catch (FileNotFoundException e) {
							System.err.println ("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
							System.exit(2);
						}
						 
						RobotEngine engine;
						try {
							engine = new RobotEngine(city.loadCity(file), city.getInitialPlace(), Direction.NORTH);
							if ( consola){
								engine.startEngine();						 
							}
							else if ( interfaz){
								MainWindow mainWin;
									 
								mainWin = new MainWindow (engine);
								engine.addObserver(mainWin);
								mainWin.arranca();
							}
						} catch (IOException e) {
							System.err.println ("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
							System.exit(2);
						}	 
					 }
					else{
						System.err.println ("Wrong type of interface");
						System.exit(1);
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
	static String LINE_SEPARATOR = System.getProperty("line.separator");
	
}
