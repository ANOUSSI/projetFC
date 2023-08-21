package vue;
import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Commande;
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	List<Commande> listCommande;
	
	
    public CustomTableCellRenderer(List<Commande> listCommande) {
		super();
		this.listCommande = listCommande;
	}


	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Votre logique pour changer la couleur de la ligne en fonction des critères
        
        // Exemple : Changer la couleur de la ligne si la valeur de la colonne 2 est "Oui"
      /*  Object columnValue = table.getValueAt(row, 2);
        if (columnValue != null && columnValue.toString().equals("Oui")) {
            component.setBackground(Color.GREEN); // Changer la couleur de fond en vert
        } else {
           
        }*/
        System.out.println("linge  "+row+" colum "+column);
       
        if(listCommande.get(row).isSupprimer()) {
        	 component.setBackground(Color.GRAY); // Changer la couleur de fond en vert
        }
        
        /*else {
        	 component.setBackground(table.getBackground()); // Utiliser la couleur de fond par défaut de la table
        }*/
        
        
        
        
        
        
        return component;
    }
}