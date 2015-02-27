package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import tp.pr4.RobotEngine;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Esta clase tiene los siguientes elementos:
 * Un panel de acciones con button que implementan MOVE, TURN, OPERATE, PICK y DROP. 
 * Adem�s tiene un combo box con las posibles rotation de la acci�n TURN y un textField para escribir el nombre dle item que queremos
 *  que coja del lugar actual.
 * Un RobotPanel que muestra la informaci�n del robot y su inventario ( una tabla con los nombres de los items y sus descripciones)
 * El usuario podr� seleccionar un item del inventario para ejecutar DROP u OPERATE con el.
 * Un CityPanel que representa la ciudad. Muestra los lugares que el robot ha visitado y un icono que representa donde esta mirando walle.
 * El icono de walle ser� actualizado cada vez que se ejecute la instruccion TURN. Los lugares visibles ser�n actualizados cuando el robot
 * ejecute la instrucci�n MOVE.
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements Observer{
	private NavigationPanel navigPanel;
	private Container panel;
	private RobotPanel robotPanel;
	private JSplitPane splitPane;
	private JMenu jMenu;
	private JMenuBar jMenuBar;
	private JMenuItem jButtonQuit;
	private ButtonPanel buttonPanel;
	private RobotEngine robot;
	
	/**
	 * Constructor without parameters 
	 */
	public MainWindow (){
		super ("MainWindow");
		initPanel();
	}
	
	/**
	 * Constructor with params
	 * @param robot
	 */
	public MainWindow(RobotEngine robot){
		super ("MainWindow");
		this.robot = robot;
		initPanel();
		fijarControlador(robot);
	}

	/**
	 * El splitPane se encarga de meter en un mismo panel con una separaci�n movil el panel de 
	 * las instrucciones y el panel de la inromaci�n del robot
	 */
	public void superPanel (){
		//Create a split pane with the two scroll panes in it.
	    this.buttonPanel = new ButtonPanel ();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.buttonPanel, this.robotPanel);
		splitPane.setDividerLocation(275);
	}
	
	/**
	 * Inicializamos todo el panel de la MainWindow
	 */
	public void initPanel(){
	    this.setSize(3*320, 340);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.panel = this.getContentPane();
	    this.robotPanel = new RobotPanel();
	    this.navigPanel= new NavigationPanel(this.robot);
	    this.jMenu = new JMenu("File");
	    this.jMenuBar = new JMenuBar ();

	   
	    this.superPanel();
	    
	    this.jButtonQuit =new JMenuItem ("Quit");
	    this.jMenu.add( this.jButtonQuit);
	    
	    this.jButtonQuit.setName("jButtonQuit");
	    
	    this.setJMenuBar(jMenuBar);
	    this.jMenuBar.add(jMenu);
	    
	    this.panel.add(splitPane, BorderLayout.NORTH);
	    this.panel.add(this.navigPanel, BorderLayout.CENTER);
	    
	    this.update(robot, null);
	  
	    this.pack();
		}
	 
	/**
	 * M�todo que se encarga de arrancar la vista de la interfaz
	 */
	public void arranca(){
       EventQueue.invokeLater(new Runnable(){
       	public void run() {
       		setVisible(true);
       	}
       });		
	}
	
	/**
	 * M�todo que se encarga de actualizar RobotPanel y NavigationPanel
	 */
	public void update(Observable arg0, Object arg1) {
		this.robotPanel.update(arg0, arg1);
		this.navigPanel.update(arg0,arg1);
		if ( this.robot.isQuit() || this.robot.getNavega().getInitialPlace().isSpaceship() || this.robot.getFuel()==0){
			this.updateQuit();
		}	
	}
	
	/**
	 * Se encarga de actualizar la opcion de quit cuando sea necesario
	 */
	public void updateQuit (){
		if (this.robot.getNavega().getInitialPlace().isSpaceship()){
			JOptionPane.showMessageDialog(buttonPanel, "Ok, finally you have found your spaceship...Bye Bye");
			System.exit(0);
		}
		else if ( this.robot.getFuel() == 0){
			JOptionPane.showMessageDialog(buttonPanel, "I run out of fuel. I cannot move. Shutting down...");
			System.exit(0);
		}
		else if (JOptionPane.showConfirmDialog(null, "Would you like to quit the game?") == 0){
			System.exit(0);				
		}
		else {
			this.robot.setQuit(false);
		}
	}
	
	/**
    * M�todo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
    * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener y FocusListener.
    */
	public void fijarControlador(EventListener controlador){
		this.buttonPanel.fijarControlador(controlador);
		this.jButtonQuit.addActionListener((ActionListener) controlador);
	}
	
}