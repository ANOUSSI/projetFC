/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vue;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Global.GlobalVar;
import dao.InterService;
import dao.UserService;
import model.Commande;
import model.User;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author ANOUSSI
 */
public class FrameGestionClient extends javax.swing.JDialog implements DialogCallback{
	List<User> listUser= new ArrayList<>();
	User userSelect;
	public InterService serVice = new UserService();
    /**
     * Creates new form FrameGestionClient
     */
    public FrameGestionClient(java.awt.Frame parent) {
    	super(parent, "Fraiche Construction", true);
    	getContentPane().setBackground(SystemColor.activeCaption);
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(SystemColor.textHighlight);
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(SystemColor.textHighlight);
        jButton1 = new javax.swing.JButton();
        jButton1.setBackground(new Color(0, 51, 0));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        jButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		jButton1ActionPerformed(e);
        		
        	}
        });
        
        jButton2 = new javax.swing.JButton();
        jButton2.setBackground(new Color(0, 51, 0));
        jButton2.setForeground(Color.WHITE);
        jButton2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        jButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		initTableAllClientNegatif();
        	}
        });
        jButton3 = new javax.swing.JButton();
        jButton3.setForeground(Color.WHITE);
        jButton3.setBackground(new Color(0, 51, 0));
        jButton3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        jButton3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		jButton1ActionPerformedf(e);
        		
        	}
        });
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        String imagePath = "user.png";

        int desiredWidth = 45;
        int desiredHeight = 45;
     //   BufferedImage resizedImage =FrameConnexion.resizeImage(imagePath, desiredWidth, desiredHeight);
      //  ImageIcon icon = new ImageIcon(resizedImage);
        
    //    lblelGestclient = new JLabel("GESTION DES CLIENTS DE FC ",icon, JLabel.LEFT);
        lblelGestclient = new JLabel("GESTION DES CLIENTS DE FC ");
        lblelGestclient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblelGestclient.setForeground(Color.WHITE);
        lblelGestclient.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(lblelGestclient, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(lblelGestclient, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );
        jPanel1.setLayout(jPanel1Layout);
        
        btnNewButton_1 = new JButton("Cancel");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(584)
        			.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        			.addContainerGap(16, Short.MAX_VALUE)
        			.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        jPanel2.setLayout(jPanel2Layout);

        jButton1.setText("Ajouter un client");

        jButton2.setText("voir les client Insolvable");

        jButton3.setText("Solder un client");
        initTableAllClient() ;
        jScrollPane1.setViewportView(myTable);
        
        btnNewButton = new JButton("Actualiser");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(153, 0, 153));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		initTableAllClient() ;
        	}
        });
        
        

		  ListSelectionModel selectionModel = myTable.getSelectionModel();
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        selectionModel.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                   try {
	                	   int selectedRow = myTable.getSelectedRow();
		                   userSelect=  listUser.get(selectedRow);
	                   }catch(Exception ex) {
	                	   
	                   }
	                   
	               //    textFieldName.setText(GlobalVar.CurentClient.getName());
	                 }
	            }
	        });
        
        textFieldSearch = new JTextField();
        textFieldSearch.setFont(new Font("Times New Roman", Font.BOLD, 13));
        textFieldSearch.setForeground(new Color(0, 128, 0));
        textFieldSearch.setColumns(10);
        
     // Add a key listener to the text field for auto-completion
        textFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
     			public void keyReleased(java.awt.event.KeyEvent evt) {
     				// Get the text entered in the text field
     				String enteredText = textFieldSearch.getText();
     				actualiserTabeau(enteredText);
     				
     				/* textArea.setText("");
     				// Clear the combo box
     				///comboBox_1.removeAllItems();
     				final StringBuilder annomalis = new StringBuilder("");
     				// Add items to the combo box that match the entered text
     				for (String item : getMatchingItems(enteredText)) {
     					//comboBox_1.addItem(item);
     					printAnnomalie(annomalis, item);
     				}
     */
     				// Show the combo box drop-down
     				// comboBox_1.setPopupVisible(true);
     			}
     		});
     		

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        		.addComponent(jPanel1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(22)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jButton1)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton2)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton3)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnNewButton)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton3)
        				.addComponent(btnNewButton))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
        			.addGap(84)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    CustomAddUser dialog1;
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		dialog1 = new CustomAddUser(this);
		dialog1.setLocationRelativeTo(null);
		dialog1.setDialogCallback(this);
		dialog1.show();
	}
	
	
	AddNewSoldeClient dialog2;
		private void jButton1ActionPerformedf(java.awt.event.ActionEvent evt) {

			
			 if(userSelect!=null) {
			    dialog2 = new AddNewSoldeClient(this,userSelect);
				dialog2.setLocationRelativeTo(null);
				dialog2.setDialogCallback(this);
				dialog2.show();
			 }else {
				 JOptionPane.showMessageDialog(null, " veillez selectionner un Client  \n Merci!");
			 }
			
		}
		
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameGestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameGestionClient dialog = new FrameGestionClient(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    FrameConnexion connc= new FrameConnexion();
    private DialogCallback dialogCallback;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable myTable;
    private JButton btnNewButton;
    private JTextField textFieldSearch;
    private JButton btnNewButton_1;
    private JLabel lblelGestclient;
	public DialogCallback getDialogCallback() {
		return dialogCallback;
	}

	public void setDialogCallback(DialogCallback dialogCallback) {
		this.dialogCallback = dialogCallback;
	}

	@Override
	public void onButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiser(int provenance) {
	// TODO Auto-generated method stub
			System.out.println(" actualisation ddddddddddddddddd");
			if(provenance==1) {
				initTableAllClient() ;
			}
			
		
	}
	
	 public void actualiserTabeau(String text) {
		  listUser=serVice.getListUser(text);
		  Object[][] tableau = new Object[listUser.size()][6];
			int i = 0;
			System.out.println("__________________________________");
			System.out.println(listUser.size());
			System.out.println("__________________________________");
			for (User user : listUser) {
				System.out.print(user.getName());
				tableau[i][0] = false;
				tableau[i][1] = user.getName();
				tableau[i][2] = user.getPrenom();
				tableau[i][3] = user.getTelephone();
				tableau[i][4] = user.getAdresse();
				tableau[i][5] = user.getSolde();	
				i++;
			}

		
				
			DefaultTableModel model = new DefaultTableModel(tableau, new String[] {"","NOM DU CLIENT", "PRENOM", "TELEPHONE", "ADRESSE", "SOLDE"  }) 
					
			
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
			
			
			
			myTable.setModel(model);
			
		  
		  
	  }
		
public void initTableAllClient() {
		
		
	    listUser=serVice.getListUser();
		Object[][] tableau = new Object[listUser.size()][6];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(listUser.size());
		System.out.println("__________________________________");
		for (User user : listUser) {
			System.out.print(user.getName());
			tableau[i][0] = false;
			tableau[i][1] = user.getName();
			tableau[i][2] = user.getPrenom();
			tableau[i][3] = user.getTelephone();
			tableau[i][4] = user.getAdresse();
			tableau[i][5] = user.getSolde();	
			i++;
		}

	
			
		DefaultTableModel model = new DefaultTableModel(tableau, new String[] {"","NOM DU CLIENT", "PRENOM", "TELEPHONE", "ADRESSE", "SOLDE"  }) 
				
		
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
		
		
		
		myTable.setModel(model);
		
	}


/*******************************************Client avec Solde Negatif*************************/

public void initTableAllClientNegatif() {
	
	
    listUser=serVice.getListUser(0);
	Object[][] tableau = new Object[listUser.size()][6];
	int i = 0;
	System.out.println("__________________________________");
	System.out.println(listUser.size());
	System.out.println("__________________________________");
	for (User user : listUser) {
		System.out.print(user.getName());
		tableau[i][0] = false;
		tableau[i][1] = user.getName();
		tableau[i][2] = user.getPrenom();
		tableau[i][3] = user.getTelephone();
		tableau[i][4] = user.getAdresse();
		tableau[i][5] = user.getSolde();	
		i++;
	}


		
	DefaultTableModel model = new DefaultTableModel(tableau, new String[] {"","NOM DU CLIENT", "PRENOM", "TELEPHONE", "ADRESSE", "SOLDE"  }) 
			
	
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
	
	
	
	myTable.setModel(model);
	
}


















}
