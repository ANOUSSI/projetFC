/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vue;
import javax.swing.GroupLayout.Alignment;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Global.GlobalVar;
import dao.InterService;
import dao.UserService;
import model.Commande;
import model.Fournisseurs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 *
 * @author ANOUSSI
 */
public class Fournisseur1 extends javax.swing.JDialog {
	private DialogCallback dialogCallback;
GlobalVar globalVar;
	List<Fournisseurs> list= new ArrayList<>();
    public Fournisseur1(Frame parent) {
        super(parent,"Dialog", true);   
        list=serVice.getListFournisseur();
        initComponents();
      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(SystemColor.textHighlight);
        jButtoCancel = new javax.swing.JButton();
        jButtoCancel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jButtoCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtoCancel.setText("Cancel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(430, Short.MAX_VALUE)
        			.addComponent(jButtoCancel)
        			.addGap(19))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(14)
        			.addComponent(jButtoCancel)
        			.addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

        
        
        Object[][] tableau = new Object[list.size()][3];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(list.size());
		System.out.println("__________________________________");
		for (Fournisseurs fournisseur : list) {
			tableau[i][0] = false;
			tableau[i][1] = fournisseur.getName();
			tableau[i][2] = fournisseur.getSolde();
			i++;
		}
 
        
        
	DefaultTableModel model = new DefaultTableModel(tableau, new String[] {"", "NOM DU FOURNISSEUR", "SOLDE" })
		{
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Colonne des cases � cocher
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Seule la colonne des cases � cocher est �ditable
            }
        };
		
		
       
		
        jTable1.setModel(model);
        
        
        ListSelectionModel selectionModel = jTable1.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	
                    int selectedRow = jTable1.getSelectedRow();
                    if(selectedRow>=0) {
                    	Fournisseurs fournis=  list.get(selectedRow);
                        textFieldnomFournisseur.setText(fournis.getName());
                        textFieldSolde.setText(String.valueOf(fournis.getSolde()));
                        System.out.println("Selected item: " + selectedRow);
                       /* int selectedColumn = jTable1.getSelectedColumn();
                        if (selectedRow != -1 && selectedColumn != -1) {
                            Object selectedValue = jTable1.getValueAt(selectedRow, selectedColumn);
                            System.out.println("Selected item: " + selectedValue);
                        }*/
                    }
                    
                }
            }
        });
        
        
        
        
        jScrollPane1.setViewportView(jTable1);
        
        lblNewLabel = new JLabel("Nom du fornisseur");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        lblNewLabel_1 = new JLabel("New Solde");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        textFieldnomFournisseur = new JTextField();
        textFieldnomFournisseur.setFont(new Font("Times New Roman", Font.BOLD, 15));
        textFieldnomFournisseur.setForeground(Color.DARK_GRAY);
        textFieldnomFournisseur.setColumns(10);
        
        textFieldSolde = new JTextField();
        textFieldSolde.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldSolde.setForeground(new Color(128, 0, 0));
        textFieldSolde.setFont(new Font("Times New Roman", Font.BOLD, 25));
        textFieldSolde.setColumns(10);
        
        panel = new JPanel();
        panel.setBackground(SystemColor.textHighlight);
        btOk = new javax.swing.JButton();
        btOk.setForeground(Color.WHITE);
        btOk.setBackground(new Color(0, 128, 0));
        btOk.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		/*if (dialogCallback != null) {
					dialogCallback.onButtonClicked();
				}*/
        		
        		if (dialogCallback != null) {
        			

            		Fournisseurs fournisseur1=new Fournisseurs();
            		
    				String name=textFieldnomFournisseur.getText().toString();
    				String soldes=textFieldSolde.getText().toString();
    				
    				if( GlobalVar.isNumber(soldes)) {
    					int solde=	Integer.parseInt(soldes);
    					fournisseur1.setName(name);
    					fournisseur1.setSolde(solde);
    				
    					boolean fait=serVice.saveFournisseur(fournisseur1);
    	        		if(fait) {	
    	        			//JOptionPane.showMessageDialog(null, "vous avez Ajouter  dans votre compte chez le fournisseur "+fournisseur1.getName()+" le montant de "+ globalVar.toLetter(fournisseur1.getSolde()));
    	        			//dispose();
    	        			 actualiserTabeau();
    	        			 dialogCallback.actualiser(1);
    					}else {
    						JOptionPane.showMessageDialog(null, "Fournisseur pas cr�er");
    					}	
    				}else {
    					JOptionPane.showMessageDialog(null, "le champ solde est un Nombre");				
    				}
				}	
        	}
        });
        
                btOk.setText("Valider");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
        				.addComponent(jPanel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addGap(20)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel)
        						.addComponent(lblNewLabel_1))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(textFieldnomFournisseur, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
        							.addGap(42)
        							.addComponent(btOk, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
        						.addComponent(textFieldSolde, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(textFieldnomFournisseur, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btOk, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
        			.addGap(27)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_1)
        				.addComponent(textFieldSolde, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        panel.setLayout(null);
        
        
        
        
        
        
        
        
        
        
        
        String imagePath = "credit.jpg";

        int desiredWidth = 150;
        int desiredHeight = 100;
      //  BufferedImage resizedImage =FrameConnexion.resizeImage(imagePath, desiredWidth, desiredHeight);
      //  ImageIcon icon = new ImageIcon(resizedImage);
        
        
        
        
        
        
        
        
        
        
    //    lblNewLabel_2 = new JLabel("CEDITER MON COMPTE CHEZ LE FOURNISSEUR",icon, JLabel.LEFT);
        
        lblNewLabel_2 = new JLabel("CEDITER MON COMPTE CHEZ LE FOURNISSEUR");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(10, 0, 496, 78);
        panel.add(lblNewLabel_2);
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents
  public void actualiserTabeau() {
	  
	    list=serVice.getListFournisseur();
	    Object[][] tableau = new Object[list.size()][3];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(list.size());
		System.out.println("__________________________________");
		for (Fournisseurs fournisseur : list) {
			tableau[i][0] = false;
			tableau[i][1] = fournisseur.getName();
			tableau[i][2] = fournisseur.getSolde();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(tableau, new String[] {"", "NOM DU FOURNISSEUR", "SOLDE" })
		{
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Colonne des cases � cocher
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Seule la colonne des cases � cocher est �ditable
            }
        };
		
		
		
        jTable1.setModel(model);
	  
	  
	  
  }
    public JTextField getTextFieldnomFournisseur() {
		return textFieldnomFournisseur;
	}

	public void setTextFieldnomFournisseur(JTextField textFieldnomFournisseur) {
		this.textFieldnomFournisseur = textFieldnomFournisseur;
	}

	public JTextField getTextFieldSolde() {
		return textFieldSolde;
	}

	public void setTextFieldSolde(JTextField textFieldSolde) {
		this.textFieldSolde = textFieldSolde;
	}

	/**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     /*   try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fournisseur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fournisseur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fournisseur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fournisseur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
      /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Fournisseur1 dialog = new Fournisseur1(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/

    
    private javax.swing.JButton btOk;
    private javax.swing.JButton jButtoCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JTextField textFieldnomFournisseur;
    private JTextField textFieldSolde;
    List<Fournisseurs> listFournisseurs= new ArrayList<>();
	public InterService serVice = new UserService();
	private JPanel panel;
	private JLabel lblNewLabel_2;
	public DialogCallback getDialogCallback() {
		return dialogCallback;
	}


	public void setDialogCallback(DialogCallback dialogCallback) {
		this.dialogCallback = dialogCallback;
	}
}
