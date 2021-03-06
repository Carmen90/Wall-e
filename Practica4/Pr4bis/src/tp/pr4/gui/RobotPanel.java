package tp.pr4.gui;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.pr4.RobotEngine;
import tp.pr4.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Esta clase muestra información sobre el robot y su inventario
 * Tiene etiquetas con la cantidad de fuel y de recicled material y una tabla con el inventario del robot
 * Cada fila muestra informacion sobre un item contenido en el inventario
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel{
	private JLabel jLabelFuel;
	private JLabel jLabelRecicled;
	private JScrollPane panel;
	private JTable jTableItems;
	private JPanel jLabelPanel;
	
	/**
	 * Constructor without parameters 
	 */
	public RobotPanel(){
		initRobotPanel();
	}
	
	/**
	 * Inicializa un panel con el fuel y el recycled material
	 */
	public void labelPanel (){
		this.jLabelFuel = new JLabel ("Fuel: ");
		this.jLabelRecicled = new JLabel ("Recycled: ");
		this.jLabelPanel = new JPanel ();
		
		this.jLabelPanel.add(this.jLabelFuel);
		this.jLabelPanel.add(this.jLabelRecicled);
		
		this.jLabelPanel.setLayout(new FlowLayout());
	}
	
	/**
	 * Método que se encarga de meter todos los componentes en el panel del robot
	 */
	public void initRobotPanel(){
		this.jLabelFuel = new JLabel();
		this.jLabelRecicled = new JLabel ();
		String []columnas = {"id", "Descripcion"}; 
		Object [][] data = {{"",""}};
		this.jTableItems = new JTable (new DefaultTableModel (data, columnas));

		
    	this.setBorder(new TitledBorder("Robot info"));
    	
        this.setName("Form Text");
        this.setPreferredSize(new java.awt.Dimension (500, 100));
		
		this.jLabelFuel.setText("Fuel: ");
		this.jLabelFuel.setHorizontalAlignment(JLabel.RIGHT);
		this.jLabelFuel.setName("jLabelFuel");
		
		this.jLabelRecicled.setText("Recycled: ");
		this.jLabelRecicled.setName("jLabelRecicled");
		this.panel = new JScrollPane (this.jTableItems);
		
		labelPanel();
		this.add(this.jLabelPanel);
		this.add(this.panel);
		
		LayoutManager thisLayout = new BoxLayout(this,1);
        this.setLayout(thisLayout);
	}
	
	/**
	 * Actualiza el fuel, recycled y la tabla de items que contiene el observable que le entra
	 * @param arg0 observable
	 * @param arg1
	 */
	public void update(Observable arg0, Object arg1) {
		RobotEngine modelo = (RobotEngine) arg0;
		this.actualizaFuel(modelo.getFuel());
		this.actualizaRecycled(modelo.getRecycledMaterial());
		this.actualizaTabla (modelo.getContainer());
	}
	
	/**
	 * Cambia el label de fuel con el valor dado
	 * @param fuel nuevo valor de fuel
	 */
	public void actualizaFuel ( int fuel){
		this.jLabelFuel.setText ("Fuel: " + fuel);
	}
	
	/**
	 * Cambia el label de recycled por el valor dado
	 * @param recycled nuevo valor de recycled
	 */
	public void actualizaRecycled ( int recycled){
		this.jLabelRecicled.setText("Recycled: "+ recycled );
	}
	
	/**
	 * Actualiza la tabla de los elementos que contiene el robot por los elementos dados
	 * @param container elementos que ha de tener la tabla
	 */
	public void actualizaTabla ( ItemContainer container){
		String [][] nueva = {{" "}, {" "}};
		DefaultTableModel modelo = new DefaultTableModel();
		String []columnas = {"id", "Descripcion"}; 
		int cont = container.numberOfItems();
		String []aux;
		nueva = new String[cont][2];
		for ( int i = 0; i < container.numberOfItems(); i++){
			nueva [i][0] =  container.getContainer().get(i).getId();
			aux = container.getContainer().get(i).toString().split(":");
			nueva [i][1] = aux[1];
		}
		modelo.addRow(nueva);
		this.jTableItems.setModel(new DefaultTableModel (nueva, columnas));
	}



}
