package vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

//import com.google.protobuf.Service;

import Global.GlobalVar;
import dao.InterService;
import dao.UserService;
import model.BonCommande;
import model.Commande;
import model.Fournisseurs;
import model.TypeAgregats;
import model.User;

import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;

import java.awt.Color;
import java.awt.Component;

import Global.GlobalVar;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author ANOUSSI
 */
public class SableGestion extends javax.swing.JFrame implements DialogCallback {

	List<Commande> listCommande = new ArrayList<>();
	public InterService serVice = new UserService();
	JRadioButton rdbtnNewRadioNumBn;
	JComboBox comboBox;
	BonCommande bonCommande;
	JRadioButton rdbtnNewRadioNomClient;
	private javax.swing.JTable jTable1;
	List<User> listClient;
	private long userIdSelected;
	private String currenNameSelected;
	Commande updatecommande;
	JDateChooser dateChooser;
	public SableGestion() {
		setTitle("FC");
		getContentPane().setBackground(new Color(70, 130, 180));
		listCommande = serVice.getListComment(GlobalVar.IDUSER);
		initComponents();
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

	}

	@SuppressWarnings("unchecked")

	private void initComponents() {
		jSeparator1 = new javax.swing.JSeparator();
		jScrollPane1 = new javax.swing.JScrollPane();
		myTable = new javax.swing.JTable();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		// contentPanel.add(jScrollPane1);

		///////////////////////
		GlobalVar.listAgregat = serVice.getListTypeAgregats();
		GlobalVar.listBonCommande = serVice.getListBonCommande(false);
		// String[] elements = { "SABLE CARRIERE", "GRAVIER 5/15","GRAVIER
		// 15/25","GRAVIER 6/10","TOUTVENANT","MOELLON","MOELLON40/70" };

		String[] elements = new String[GlobalVar.listAgregat.size()];
		int idex = 0;
		for (TypeAgregats agregar : GlobalVar.listAgregat) {
			elements[idex] = agregar.getNom();
			idex++;
		}
		String[] elementsClient = { "ancien client", "nouveau client" };

		//////////////////////////////////////////////////////////////////////////////////

		Object[][] tableau = new Object[listCommande.size()][14];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(listCommande.size());
		System.out.println("__________________________________");
		for (Commande commande : listCommande) {
			System.out.print(commande.getNUMERO_BON());
			tableau[i][0] = false;
			tableau[i][1] = commande.getDATE();
			tableau[i][2] = commande.getNUMERO_BON();
			tableau[i][3] = commande.getNOM_DU_CLIENT();
			tableau[i][4] = commande.getCAMION();
			tableau[i][5] = commande.getQUANTITE();
			tableau[i][6] = commande.getMontantapayer();
			tableau[i][7] = commande.getMONTANT();
			tableau[i][8] = commande.getTYPE();
			tableau[i][9] = commande.getMontunitaire();
			tableau[i][10] = commande.getAnciensoldeClient();
			tableau[i][11] = commande.getSoldeuser(); // SOLDE CLIENT
			tableau[i][12] = commande.getSoldeFournisseur(); // MON SOLDE
			tableau[i][13] = commande.getNom_fournisseur(); // SOLDE CLIENT
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(tableau,
				new String[] { "", "DATE", "NUMERO BON", "NOM DU CLIENT", "CAMION", "QUANTITE(T)", "MONTANT A PAYER",
						"MONTANT AVANCE", "TYPE AGREGAT", "PRIX UNITAIRE", "ANCIEN SOLDE DU CLIENT", "SOLDE DU CLIENT",
						"MON SOLDE", "FOURNISSEUR" })

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
		myTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer(listCommande));
	    tailleDeColum();

		// Create a cell renderer to change the row color
		/*
		 * DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		 * 
		 * @Override public Component getTableCellRendererComponent(JTable table, Object
		 * value, boolean isSelected, boolean hasFocus, int row, int column) { Component
		 * component = super.getTableCellRendererComponent(table, value, isSelected,
		 * hasFocus, row, column);
		 * 
		 * // Change the background color based on the row index if (row % 2 == 0) {
		 * component.setBackground(Color.YELLOW); } else {
		 * component.setBackground(Color.WHITE); }
		 * 
		 * return component; } };
		 * 
		 * // Set the cell renderer to the table
		 * myTable.setDefaultRenderer(Object.class, cellRenderer);
		 */

		// Create a selection listener for the row
	/*	ListSelectionListener selectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = myTable.getSelectedRow();
					if (selectedRow != -1) {
						//boolean isSelected = (boolean) myTable.getValueAt(selectedRow, 0);
						boolean isSelected = listCommande.get(selectedRow).isSupprimer();
						if (!isSelected) {
							updatecommande = listCommande.get(selectedRow);
							textFieldnumBon.setText(updatecommande.getNUMERO_BON());
							textFielTextClient.setText(updatecommande.getNOM_DU_CLIENT());
							textFieldQuantite.setText(updatecommande.getQUANTITE());
							textFieldApayer.setText(String.valueOf(updatecommande.getMontantapayer()));
							textFielAvance.setText(String.valueOf(updatecommande.getMONTANT()));
							textFieldCamion.setText(String.valueOf(updatecommande.getCAMION()));
							if (!textFieldnumBon.getText().toString().isEmpty()) {
								String bondComande = updatecommande.getNUMERO_BON();
								bonCommande = serVice.getBonCommandewithId(bondComande, true);
								if (bonCommande != null) {
									// textFielTextClient.setText(bonCommande.getNomUsers());
									currenNameSelected = bonCommande.getNomUsers();
									userIdSelected = bonCommande.getIdUser();
									GlobalVar.CurentClient = serVice.getUser(userIdSelected);
									System.out.println("userIdSelected " + userIdSelected);
								}
							}

						}
					}
				}
			}
		};
*/
		// Add the selection listener to the table's selection model
		/*ListSelectionModel rowSelectionModel = myTable.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowSelectionModel.addListSelectionListener(selectionListener);

		// Customize the checkbox column renderer and editor
		TableColumn checkboxColumn = myTable.getColumnModel().getColumn(0);

		checkboxColumn.setCellRenderer(new TableCellRenderer() {
			private final JCheckBox checkbox = new JCheckBox();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				checkbox.setSelected((boolean) value);
				return checkbox;
			}
		});
		checkboxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));*/
		
		
		 // Add a MouseListener to the table
        myTable.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check for double-click event
                if (e.getClickCount() == 2) {
                    // Get the selected row index
                  //  int rowIndex = myTable.getSelectedRow();
                    int selectedRow = myTable.getSelectedRow();
                	boolean isSelected = listCommande.get(selectedRow).isSupprimer();
                	boolean isSysteme = isSystemComande(listCommande.get(selectedRow));
					if (!isSelected&&!isSysteme) {
						updatecommande = listCommande.get(selectedRow);
						textFieldnumBon.setText(updatecommande.getNUMERO_BON());
						textFielTextClient.setText(updatecommande.getNOM_DU_CLIENT());
						textFieldQuantite.setText(updatecommande.getQUANTITE());
						textFieldApayer.setText(String.valueOf(updatecommande.getMontantapayer()));
						textFielAvance.setText(String.valueOf(updatecommande.getMONTANT()));
						textFieldCamion.setText(String.valueOf(updatecommande.getCAMION()));
						if (!textFieldnumBon.getText().toString().isEmpty()) {
							String bondComande = updatecommande.getNUMERO_BON();
							bonCommande = serVice.getBonCommandewithId(bondComande, true);
							if (bonCommande != null) {
								// textFielTextClient.setText(bonCommande.getNomUsers());
								currenNameSelected = bonCommande.getNomUsers();
								userIdSelected = bonCommande.getIdUser();
								GlobalVar.CurentClient = serVice.getUser(userIdSelected);
								System.out.println("userIdSelected " + userIdSelected);
							}
						}

					}
                }
            }
        });
		
		
		

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		jScrollPane1.setViewportView(myTable);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 3));
		panel.setBackground(new Color(0, 0, 255));
		FrameJDialogClient client;
		Fournisseur1 fournisseurDialog;

		ButtonGroup buttonGroup = new ButtonGroup();
		/*
		 * selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 * selectionModel.addListSelectionListener(new ListSelectionListener() {
		 * 
		 * @Override public void valueChanged(ListSelectionEvent e) { if
		 * (!e.getValueIsAdjusting()) { int selectedRow = jTable1.getSelectedRow();
		 * userIdSelected = listClient.get(selectedRow).getId(); GlobalVar.CurentClient
		 * = listClient.get(selectedRow); currenNameSelected =
		 * listClient.get(selectedRow).getName() + " " +
		 * listClient.get(selectedRow).getPrenom();
		 * textFielTextClient.setText(currenNameSelected);
		 * 
		 * } } });
		 */

		//////////////////////////////////////////////////////////////
		// Add a ListSelectionListener to the table's selection model
		/*
		 * ListSelectionModel selectionModelselect = myTable.getSelectionModel();
		 * selectionModelselect.addListSelectionListener(new ListSelectionListener() {
		 * 
		 * @Override public void valueChanged(ListSelectionEvent e) {
		 * System.out.println("Clicked Row: ID="); /* if (!e.getValueIsAdjusting()) {
		 * int selectedRow = myTable.getSelectedRow(); if (selectedRow != -1) { Object
		 * id = myTable.getValueAt(selectedRow, 0); Object name =
		 * myTable.getValueAt(selectedRow, 1); System.out.println("Clicked Row: ID=" +
		 * id + ", Name=" + name); } }
		 */
		/*
		 * } });
		 */

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1698, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jScrollPane1, Alignment.LEADING)
								.addGroup(Alignment.LEADING, layout.createSequentialGroup()
									.addGap(6)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1587, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(95, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(1688))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(null);
		textFieldnumBon = new JTextField();
		textFieldnumBon.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textFieldnumBon.setBounds(0, 47, 105, 34);
		panel_1.add(textFieldnumBon);
		textFieldnumBon.setColumns(10);

		lblNewLabel_3 = new JLabel("Numero Bon");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(0, 31, 99, 14);
		panel_1.add(lblNewLabel_3);
		jTable1 = new javax.swing.JTable();

		////////////////////////////////////////////////////////////////

		jScrollPane1_1 = new JScrollPane();
		jScrollPane1_1.setBounds(239, 67, 164, 63);
		panel_1.add(jScrollPane1_1);
		jScrollPane1_1.setViewportView(jTable1);
		comboBoxclient = new JComboBox(elementsClient);
		comboBoxclient.setFont(new Font("Times New Roman", Font.BOLD, 14));
		comboBoxclient.setBounds(109, 47, 117, 34);
		panel_1.add(comboBoxclient);
		textFielTextClient = new JTextField();
		textFielTextClient.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFielTextClient.setBounds(239, 40, 161, 27);
		panel_1.add(textFielTextClient);
		textFielTextClient.setColumns(10);

		lblNewLabel_2 = new JLabel("Nom client");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(248, 26, 80, 14);
		panel_1.add(lblNewLabel_2);

		lblNewLabel_4 = new JLabel("Quantite");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_4.setBounds(434, 31, 61, 14);
		panel_1.add(lblNewLabel_4);

		textFieldQuantite = new JTextField();
		textFieldQuantite.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQuantite.setForeground(new Color(128, 0, 0));
		textFieldQuantite.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textFieldQuantite.setBounds(424, 47, 105, 34);
		panel_1.add(textFieldQuantite);
		textFieldQuantite.setColumns(10);

		lblNewLabel_5 = new JLabel("Camion");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5.setBounds(555, 31, 61, 14);
		panel_1.add(lblNewLabel_5);

		textFieldCamion = new JTextField();
		textFieldCamion.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCamion.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textFieldCamion.setBounds(539, 47, 105, 34);
		panel_1.add(textFieldCamion);
		textFieldCamion.setColumns(10);
		comboBox = new JComboBox(elements);
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 16));
		comboBox.setBounds(654, 47, 123, 34);
		panel_1.add(comboBox);

		lblNewLabel_7 = new JLabel("Qualite");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_7.setBounds(672, 31, 68, 14);
		panel_1.add(lblNewLabel_7);

		lblNewLabel_6 = new JLabel("Montant a payer");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_6.setBounds(798, 22, 105, 23);
		panel_1.add(lblNewLabel_6);

		textFieldApayer = new JTextField();
		textFieldApayer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldApayer.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textFieldApayer.setBounds(787, 44, 158, 37);
		panel_1.add(textFieldApayer);
		textFieldApayer.setColumns(10);

		lblNewLabel_8 = new JLabel("Avance");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(998, 22, 89, 23);
		panel_1.add(lblNewLabel_8);

		textFielAvance = new JTextField();
		textFielAvance.setHorizontalAlignment(SwingConstants.CENTER);
		textFielAvance.setForeground(new Color(128, 0, 0));
		textFielAvance.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textFielAvance.setBounds(992, 43, 162, 34);
		panel_1.add(textFielAvance);
		textFielAvance.setColumns(10);

		btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1.setBounds(1432, 86, 145, 34);
		panel_1.add(btnNewButton_1);

		btnNewButtonValider = new JButton("Valider");
		btnNewButtonValider.setForeground(Color.WHITE);
		btnNewButtonValider.setBackground(new Color(0, 128, 0));
		btnNewButtonValider.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButtonValider.setBounds(1196, 44, 145, 35);
		panel_1.add(btnNewButtonValider);

		lblNewLabel_1 = new JLabel("rechercher une commande  par :");
		lblNewLabel_1.setBounds(0, 3, 217, 17);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		rdbtnNewRadioNomClient = new JRadioButton("Numero de bon");
		rdbtnNewRadioNomClient.setBounds(223, 2, 105, 22);
		panel_1.add(rdbtnNewRadioNomClient);
		rdbtnNewRadioNomClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbtnNewRadioNomClient.setForeground(new Color(0, 0, 0));
		buttonGroup.add(rdbtnNewRadioNomClient);

		rdbtnNewRadioNumBn = new JRadioButton("Nom du client");
		rdbtnNewRadioNumBn.setBounds(340, 1, 99, 23);
		panel_1.add(rdbtnNewRadioNumBn);
		rdbtnNewRadioNumBn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbtnNewRadioNumBn.setForeground(new Color(0, 0, 0));
		rdbtnNewRadioNumBn.setSelected(true);
		buttonGroup.add(rdbtnNewRadioNumBn);

		jButton1 = new javax.swing.JButton();
		jButton1.setBounds(628, -1, 136, 25);
		panel_1.add(jButton1);
		jButton1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1ActionPerformed(e);
			}
		});

		jButton1.setText("Gestion des clients");

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(456, -1, 136, 26);
		panel_1.add(textFieldSearch);
		textFieldSearch.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSearch.setForeground(Color.BLUE);
		textFieldSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textFieldSearch.setColumns(10);
		jButton3 = new javax.swing.JButton();
		jButton3.setBounds(774, 0, 161, 25);
		panel_1.add(jButton3);
		jButton3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeBonsUse(e);

			}
		});

		jButton3.setText("Bons Disponibles");

		JButton btnNewButton = new JButton("Gestion des Bons");
		btnNewButton.setBounds(998, -1, 153, 25);
		panel_1.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnAddFournisseur = new JButton("Gerer le Solde FC");
		btnAddFournisseur.setBounds(1186, 0, 161, 25);
		panel_1.add(btnAddFournisseur);
		btnAddFournisseur.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnNewButton_2 = new JButton("Vider");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				initialiserVariable();
				
				//ddf
			}
		});
		btnNewButton_2.setBounds(1432, 1, 145, 39);
		panel_1.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Comptabilité");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				jButton4ActionPerformed(e);			
			}
		});
		btnNewButton_3.setBounds(1432, 44, 145, 38);
		panel_1.add(btnNewButton_3);
		btnAddFournisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton3ActionPerformed(e);

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnNewButton(e);
			}
		});

		textFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				// Get the text entered in the text field
				String enteredText = textFieldSearch.getText();

				if (rdbtnNewRadioNumBn.isSelected()) {

					SearchTable(enteredText, true);
				} else {
					if (rdbtnNewRadioNomClient.isSelected()) {

						SearchTable(enteredText, false);
					}
				}

			}
		});
		btnNewButtonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addCommande();
			}

		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//////////////////////////////////////////////////
				if(updatecommande!=null) {
					
					 int response = JOptionPane.showConfirmDialog(null, "Voulez-vous continuer la suppression ?", "Confirmation", JOptionPane.YES_NO_OPTION);

					 if (response == JOptionPane.YES_OPTION) {
						 boolean execut = serVice.deleteCommande(updatecommande);
							if (execut) {
								 bonCommande = new BonCommande();
								bonCommande.setUtilise(execut);
								updatecommande=null;
								// addCommande();
								initTable();
								JOptionPane.showInternalMessageDialog(null,"Operation terminee avec succes\nMerci!");
							}
				        } 
					
					
					
					
				}

				

			}
		});

		textFielTextClient.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				// Get the text entered in the text field
				String enteredText = textFieldSearch.getText();

				if (comboBoxclient.getSelectedIndex() == 0) {

					String enteredTextClient = textFielTextClient.getText();
					if (!enteredTextClient.isEmpty())
						actualiserTabeau(enteredTextClient);
					else
						GlobalVar.CurentClient = null;
				}

				/*
				 * if(comboBoxclient.getSelectedIndex()==1) { GlobalVar.CurentClient.setId(0);
				 * 
				 * 
				 * }
				 */

			}
		});
		comboBoxclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println((String.valueOf(comboBoxclient.getSelectedIndex())));
				textFielTextClient.setText("");
				if (comboBoxclient.getSelectedIndex() == 1) {
					// if(comboBoxclient.getSelectedItem().equals("nouveau client")) {
					userIdSelected = 0;
					GlobalVar.CurentClient = null;
					User user = new User();
					user.setId(0);
					GlobalVar.CurentClient = user;
					// jScrollPane1_1.enable(false);
					// jScrollPane1_1.setVisible(false);
					// repaint();
				}
				if (comboBoxclient.getSelectedIndex() == 0) {
					// jScrollPane1_1.enable(true);
					// jScrollPane1_1.setVisible(true);
					// repaint();
				}

			}
		});
		ListSelectionModel selectionModel = jTable1.getSelectionModel();

		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = jTable1.getSelectedRow();
					userIdSelected = listClient.get(selectedRow).getId();
					GlobalVar.CurentClient = listClient.get(selectedRow);
					currenNameSelected = listClient.get(selectedRow).getName() + " "
							+ listClient.get(selectedRow).getPrenom();
					textFielTextClient.setText(currenNameSelected);

				}
			}
		});
		// rechercher Bon et utilisateur
		textFieldnumBon.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				// Get the text entered in the text field
				String enteredText = textFieldnumBon.getText();
				// actualiserTabeau(enteredText);
				System.out.println(enteredText);
				/// recherche le bon dans la bd

				// BonCommande bonCommande=GlobalVar.listBonCommande.get(index);
				bonCommande = serVice.getBonCommandewithId(enteredText, false);
				if (bonCommande != null) {
					textFielTextClient.setText(bonCommande.getNomUsers());
					currenNameSelected = bonCommande.getNomUsers();
					userIdSelected = bonCommande.getIdUser();
					GlobalVar.CurentClient = serVice.getUser(userIdSelected);
					System.out.println("userIdSelected " + userIdSelected);
				}

				else {
					textFielTextClient.setText("");
					currenNameSelected = "";
					userIdSelected = 0;
					User user = new User();
					user.setId(0);
					GlobalVar.CurentClient = user;
				}

			}
		});
		panel.setLayout(null);

		String imagePath = "logo1.jpg";
		int desiredWidth = 250;
		int desiredHeight = 55;
	//	BufferedImage resizedImage = FrameConnexion.resizeImage(imagePath, desiredWidth, desiredHeight);
	//	ImageIcon icon = new ImageIcon(resizedImage);

	//	JLabel lblNewLabel = new JLabel("                  BIENVENNUE   CHEZ  FC", icon, JLabel.LEFT);
		JLabel lblNewLabel = new JLabel(" BIENVENNUE   CHEZ  FC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(30, 144, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(535, 11, 363, 59);
		panel.add(lblNewLabel);
		dateChooser = new JDateChooser();
		dateChooser.setBorder(new EmptyBorder(3, 0, 0, 0));
		dateChooser.setForeground(new Color(139, 0, 0));
		dateChooser.setBackground(new Color(0, 128, 0));
		dateChooser.setBounds(1427, 11, 162, 59);
		panel.add(dateChooser);
		dateChooser.setDate(new Date());
		colorLine();
		getContentPane().setLayout(layout);

		pack();
	}

	private void tailleDeColum() {
		// TODO Auto-generated method stub
		TableColumn column = myTable.getColumnModel().getColumn(0); // Colonne d'index 1 (Age)
	    TableColumn column5 = myTable.getColumnModel().getColumn(5); // Colonne d'index 1 (Age)
	    TableColumn column13 = myTable.getColumnModel().getColumn(13); // Colonne d'index 1 (Age)
	    column.setPreferredWidth(25); // D�finir la largeur pr�f�r�e de la colonne en pixels
	    column5.setPreferredWidth(25); // D�finir la largeur pr�f�r�e de la colonne en pixels
	    column13.setPreferredWidth(25); // D�finir la largeur pr�f�r�e de la colonne en pixels
	}

	public void actualiserTabeau(String text) {

		listClient = serVice.getListUser(text);
		Object[][] tableau = new Object[listClient.size()][1];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(listClient.size());
		System.out.println("__________________________________");
		for (User user : listClient) {
			tableau[i][0] = user.getName() + " " + user.getPrenom();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(tableau, new String[] { "NOM DU CLIENT" }) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0; // Seule la colonne des cases � cocher est �ditable
			}
		};
		jTable1.setModel(model);

	}

	FrameJDialogListBonUse bonUse;

	private void listeBonsUse(java.awt.event.ActionEvent evt) {

		bonUse = new FrameJDialogListBonUse(this);
		bonUse.setLocationRelativeTo(null);
		bonUse.setDialogCallback(this);
		bonUse.show();

	}

	
	
	
	
	FrameActivite activite;
	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		activite = new FrameActivite(this);
		activite.setLocationRelativeTo(null);
		activite.setDialogCallback(this);
		activite.show();
	}
	
	
	
	
	FrameJDialogClient client2;
	Fournisseur1 fournisseur1;

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {

		fournisseur1 = new Fournisseur1(this);
		fournisseur1.setLocationRelativeTo(null);
		fournisseur1.setDialogCallback(this);
		fournisseur1.show();

	}

	// FrameAddBon addBon;
	FrameGestionBon addBon;

	private void btnNewButton(java.awt.event.ActionEvent evt) {

		addBon = new FrameGestionBon(this);
		addBon.setLocationRelativeTo(null);
		addBon.setDialogCallback(this);
		addBon.show();

	}

	Custom dialog;

	FrameGestionClient dialog1;

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		dialog1 = new FrameGestionClient(this);
		dialog1.setLocationRelativeTo(null);
		dialog1.setDialogCallback(this);
		dialog1.show();

	}

	public static void main(String args[]) {
		init();
	}

	public static void init() {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SableGestion.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SableGestion.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SableGestion.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SableGestion.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				SableGestion sableGestion = new SableGestion();

				sableGestion.setSize(800, 600);
				sableGestion.setLocationRelativeTo(null); // Centrer la fen�tre

				// Mettre la fen�tre en plein �cran
				sableGestion.setExtendedState(JFrame.MAXIMIZED_BOTH);

				// Fermer l'application lorsque la fen�tre est ferm�e
				sableGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Afficher la fen�tre
				sableGestion.setVisible(true);

				// Cr�er le tableau avec le mod�le de donn�es
				// Redimensionner automatiquement les colonnes du tableau

			}
		});
	}

	FrameConnexion login = new FrameConnexion();
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTable myTable;
	double montant;
	String nomClient;
	String numBordero;
	String quantite;
	String numCamion;
	String qualite;
	String dateCreate;
	int user;
	String type;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
	private JButton btnAddFournisseur;
	private JTextField textFieldSearch;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textFielTextClient;
	private JTextField textFieldnumBon;
	private JComboBox comboBoxclient;
	private JTextField textFieldQuantite;
	private JTextField textFieldApayer;
	private JTextField textFieldCamion;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JTextField textFielAvance;
	private JScrollPane jScrollPane1_1;
	private JButton btnNewButtonValider;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	// private boolean
	@Override
	public void onButtonClicked() {
		Commande commande = new Commande();
		String montants = dialog.getTextFieldMontant().getText().toString();
		montants = montants.trim();
		if (isNumeric(montants) && !montants.isEmpty()) {
			montant = Double.parseDouble(montants);
			Date selectedDate = dialog.getdateChooser().getDate();
			if (!selectedDate.toString().isEmpty()) {
				dateCreate = sdf.format(selectedDate);
			}

			if (!montants.isEmpty()) {

			}

			if (!dialog.getTextFieldName().getText().toString().isEmpty())
				nomClient = dialog.getTextFieldName().getText().toString();

			if (!dialog.getTextFieldQte().getText().toString().isEmpty())
				quantite = dialog.getTextFieldQte().getText().toString();

			if (!dialog.getTextFieldCamion().getText().toString().isEmpty())
				numCamion = dialog.getTextFieldCamion().getText().toString();
			commande.setMontantapayer(montant);
			if (dialog.getrRadioButtonAvancer().isSelected()) {
				String avance = dialog.getTextFieldAvancer().getText().toString();
				commande.setMONTANT(Double.valueOf(avance));
				double dif = montant - Double.valueOf(avance);
				commande.setSoldifference(dif);

			} else {
				if (dialog.getRadioButtonNonPayer().isSelected()) {
					commande.setSoldifference(montant);
					commande.setMONTANT(0);

				} else {
					if (dialog.getrRadioButtonComptant().isSelected()) {

						commande.setMONTANT(montant);
						commande.setSoldifference(0);
					}
				}
			}

			type = (String) dialog.getComboBox().getSelectedItem();
			int indexAgreg = dialog.getComboBox().getSelectedIndex();
			double valeur = GlobalVar.listAgregat.get(indexAgreg).getMontant();
			int index = dialog.getComboBoxNumeroBon().getSelectedIndex();

			BonCommande bonCommande = GlobalVar.listBonCommande.get(index);

			// commande.setNUMERO_BON(numBordero);
			commande.setID_CLIENT(GlobalVar.CurentClient.getId());
			commande.setTYPE(type);
			commande.setIDBON(bonCommande.getIdboncommande());
			commande.setCAMION(numCamion);
			commande.setQUANTITE(quantite);

			commande.setDATE(dateCreate);
			commande.setIduser(GlobalVar.IDUSER);

			double NouveauSoldeFournisseur = bonCommande.getSoldeFournisseur() - Integer.parseInt(quantite) * valeur;
			commande.setSoldeFournisseur(NouveauSoldeFournisseur);
			commande.setNumero_bon(bonCommande.getNumerobon());
			double anciensoldeclient = GlobalVar.CurentClient.getSolde();
			commande.setAnciensoldeClient(anciensoldeclient);
			double nowsoledclient = anciensoldeclient - commande.getSoldifference();
			commande.setSoldeClient(nowsoledclient);
			serVice.saveCommande(commande);

			// UPDATE FOURNISSEUR SOLDE
			Fournisseurs fournisseur = serVice.getFournisseur(commande.getIDBON());
			serVice.updateSoldeFournisseur(fournisseur.getId(), commande.getSoldeFournisseur());

			serVice.updateSoldeClient(GlobalVar.CurentClient.getId(), commande.getSoldifference());

			initTable();
		} else {
			JOptionPane.showMessageDialog(null, " veillez saisie les informations valides  \n Merci!");

		}

	}

/////////////////////////////	

	public void initTable() {
		listCommande = serVice.getListComment(GlobalVar.IDUSER);
		initTables();
	}

	public void SearchTable(String text, boolean parnon) {
		listCommande = serVice.getListCommentText(text, parnon);
		initTables();
	}

	public void initTables() {
		Object[][] tableau = new Object[listCommande.size()][14];
		int i = 0;
		System.out.println("__________________________________");
		System.out.println(listCommande.size());
		System.out.println("__________________________________");
		for (Commande commande : listCommande) {
			System.out.print(commande.getNUMERO_BON());
			tableau[i][0] = false;
			tableau[i][1] = commande.getDATE();
			tableau[i][2] = commande.getNUMERO_BON();
			tableau[i][3] = commande.getNOM_DU_CLIENT();
			tableau[i][4] = commande.getCAMION();
			tableau[i][5] = commande.getQUANTITE();
			tableau[i][6] = commande.getMontantapayer();
			tableau[i][7] = commande.getMONTANT();
			tableau[i][8] = commande.getTYPE();
			tableau[i][9] = commande.getMontunitaire();
			tableau[i][10] = commande.getAnciensoldeClient();
			tableau[i][11] = commande.getSoldeClient();
			tableau[i][12] = commande.getSoldeFournisseur(); // MON SOLDE
			tableau[i][13] = commande.getNom_fournisseur(); // SOLDE CLIENT
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(tableau,
				new String[] { "", "DATE", "NUMERO BON", "NOM DU CLIENT", "CAMION", "QUANTITE(T)", "MONTANT A PAYER",
						"MONTANT AVANCE", "TYPE AGREGAT", "PRIX UNITAIRE", "ANCIEN SOLDE DU CLIENT", "SOLDE DU CLIENT",
						"MON SOLDE", "FOURNISSEUR" }) {
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

		// myTable.setDefaultRenderer(Object.class, new
		// CustomTableCellRenderer(listCommande));
		// Create a selection listener for the row
	/*	ListSelectionListener selectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = myTable.getSelectedRow();
					if (selectedRow != -1) {
						//boolean isSelected = (boolean) myTable.getValueAt(selectedRow, 0);
						boolean isSelected = listCommande.get(selectedRow).isSupprimer();
						if (!isSelected) {
							updatecommande = listCommande.get(selectedRow);
							textFieldnumBon.setText(updatecommande.getNUMERO_BON());
							textFielTextClient.setText(updatecommande.getNOM_DU_CLIENT());
							textFieldQuantite.setText(updatecommande.getQUANTITE());
							textFieldApayer.setText(String.valueOf(updatecommande.getMontantapayer()));
							textFielAvance.setText(String.valueOf(updatecommande.getMONTANT()));
							textFieldCamion.setText(String.valueOf(updatecommande.getCAMION()));
							if (!textFieldnumBon.getText().toString().isEmpty()) {
								bonCommande = serVice.getBonCommandewithId(updatecommande.getNUMERO_BON(), true);
								if (bonCommande != null) {
									// textFielTextClient.setText(bonCommande.getNomUsers());
									currenNameSelected = bonCommande.getNomUsers();
									userIdSelected = bonCommande.getIdUser();
									GlobalVar.CurentClient = serVice.getUser(userIdSelected);
									System.out.println("userIdSelected " + userIdSelected);
								}
							}

						}
					}
				}
			}
		};

		// Add the selection listener to the table's selection model
		ListSelectionModel rowSelectionModel = myTable.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowSelectionModel.addListSelectionListener(selectionListener);

		// Customize the checkbox column renderer and editor
		TableColumn checkboxColumn = myTable.getColumnModel().getColumn(0);

		checkboxColumn.setCellRenderer(new TableCellRenderer() {
			private final JCheckBox checkbox = new JCheckBox();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				checkbox.setSelected((boolean) value);
				return checkbox;
			}
		});
		checkboxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));*/
		
		
		 // Add a MouseListener to the table
        myTable.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check for double-click event
                if (e.getClickCount() == 2) {
                    // Get the selected row index
                  //  int rowIndex = myTable.getSelectedRow();
                    int selectedRow = myTable.getSelectedRow();
                	boolean isSelected = listCommande.get(selectedRow).isSupprimer();
                	boolean isSysteme = isSystemComande(listCommande.get(selectedRow));
					if (!isSelected&&!isSysteme) {
						updatecommande = listCommande.get(selectedRow);
						textFieldnumBon.setText(updatecommande.getNUMERO_BON());
						textFielTextClient.setText(updatecommande.getNOM_DU_CLIENT());
						textFieldQuantite.setText(updatecommande.getQUANTITE());
						textFieldApayer.setText(String.valueOf(updatecommande.getMontantapayer()));
						textFielAvance.setText(String.valueOf(updatecommande.getMONTANT()));
						textFieldCamion.setText(String.valueOf(updatecommande.getCAMION()));
						if (!textFieldnumBon.getText().toString().isEmpty()) {
							String bondComande = updatecommande.getNUMERO_BON();
							bonCommande = serVice.getBonCommandewithId(bondComande, true);
							if (bonCommande != null) {
								// textFielTextClient.setText(bonCommande.getNomUsers());
								currenNameSelected = bonCommande.getNomUsers();
								userIdSelected = bonCommande.getIdUser();
								GlobalVar.CurentClient = serVice.getUser(userIdSelected);
								System.out.println("userIdSelected " + userIdSelected);
							}
						}

					}
                }
            }

			
        });
		
        tailleDeColum();
		
		colorLine();
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}
	private boolean isSystemComande(Commande commande) {
		// TODO Auto-generated method stub
		
		if(commande.getNUMERO_BON().contains("000547C")||commande.getNUMERO_BON().contains("000547U")) {
			return true;
		}
		
		return false;
	}
	public void colorLine() {
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				if (listCommande.get(row).isSupprimer()) {
					component.setBackground(Color.pink);// BLUE
				} else {
					// component.setBackground(Color.B);

					// Reset the background color to default for non-selected rows
					// if (!isSelected) {
					// component.setBackground(table.getBackground());
					// }
					Color cyan = Color.decode("#39698a"); // Cyan
					Color cyan2 = Color.decode("#f2f2f1"); // Cyan
					if (row % 2 == 0) {
						component.setBackground(cyan);
					} else {
						component.setBackground(Color.LIGHT_GRAY);
					}

				}

				return component;
			}
		};

		myTable.setDefaultRenderer(Object.class, cellRenderer);

	}

	public void addCommande() {
		System.out.println(String.valueOf(userIdSelected));
	
		///////////////////////////////////////////////////////////////
		Commande commande = new Commande();
		// String montants = dialog.getTextFieldMontant().getText().toString();
		String montants = textFieldApayer.getText().toString();
		montants = montants.trim();
		if (isNumeric(montants) && !montants.isEmpty()) {

			if (bonCommande != null && bonCommande.isUtilise() == false && GlobalVar.CurentClient != null) {
				if (!textFielTextClient.getText().toString().isEmpty())
					nomClient = textFielTextClient.getText().toString();
				if (GlobalVar.CurentClient.getId() == 0) {
					GlobalVar.CurentClient.setSolde(0);
					System.out.println(GlobalVar.CurentClient.getId());
					
					
					if(!nomClient.isEmpty()) {
						String[] nomc = nomClient.split(" ");
						GlobalVar.CurentClient.setName(nomc[0]);
						if(nomc.length>1) {
							GlobalVar.CurentClient.setPrenom(nomc[1]);
						}
					}
					
					
					
					User userC = serVice.saveUser(GlobalVar.CurentClient);
					GlobalVar.CurentClient = userC;

				}
				montant = Double.parseDouble(montants);
				Date selectedDate = dateChooser.getDate();
				if (!selectedDate.toString().isEmpty()) {
					dateCreate = sdf.format(selectedDate);
				}

				

				if (!textFieldQuantite.getText().toString().isEmpty())
					quantite = textFieldQuantite.getText().toString();

				if (!textFieldCamion.getText().toString().isEmpty())
					numCamion = textFieldCamion.getText().toString();
				commande.setMontantapayer(montant);

/////////////////////////////////////////////////////////////////////////////////////////////
				if (!textFielAvance.getText().toString().isEmpty()) {
					String avance = textFielAvance.getText().toString();
					commande.setMONTANT(Double.valueOf(avance));
					double dif = montant - Double.valueOf(avance);
					commande.setSoldifference(dif);
				} else {
					commande.setSoldifference(montant);
					commande.setMONTANT(0);
				}

				/*
				 * if(textFielAvance.getText().toString().isEmpty()) { String avance=
				 * dialog.getTextFieldAvancer().getText().toString();
				 * commande.setMONTANT(Integer.valueOf(avance)); int dif=
				 * montant-Integer.valueOf(avance); commande.setSoldifference(dif);
				 * 
				 * }else { if(dialog.getRadioButtonNonPayer().isSelected()) {
				 * commande.setSoldifference(montant); commande.setMONTANT(0);
				 * 
				 * }else { if(dialog.getrRadioButtonComptant().isSelected()) {
				 * 
				 * commande.setMONTANT(montant); commande.setSoldifference(0); } } }
				 */

				//////////////////////////////////////////////////////////////////////////////////////////////////////

				type = (String) comboBox.getSelectedItem();
				int indexAgreg = comboBox.getSelectedIndex();
				double valeur = GlobalVar.listAgregat.get(indexAgreg).getMontant();

				// int index = dialog.getComboBoxNumeroBon().getSelectedIndex();

				// commande.setNUMERO_BON(numBordero);
				commande.setID_CLIENT(GlobalVar.CurentClient.getId());
				commande.setTYPE(type);
				commande.setIDBON(bonCommande.getIdboncommande());
				commande.setCAMION(numCamion);
				commande.setQUANTITE(quantite);

				commande.setDATE(dateCreate);
				commande.setIduser(GlobalVar.IDUSER);

				double NouveauSoldeFournisseur = bonCommande.getSoldeFournisseur() - Double.parseDouble(quantite) * valeur;
				commande.setSoldeFournisseur(NouveauSoldeFournisseur);
				commande.setNumero_bon(bonCommande.getNumerobon());

				double anciensoldeclient = GlobalVar.CurentClient.getSolde();
				commande.setAnciensoldeClient(anciensoldeclient);
				double nowsoledclient = anciensoldeclient - commande.getSoldifference();
				commande.setSoldeClient(nowsoledclient);

				serVice.saveCommande(commande);

				// UPDATE FOURNISSEUR SOLDE
				Fournisseurs fournisseur = serVice.getFournisseur(commande.getIDBON());
				serVice.updateSoldeFournisseur(fournisseur.getId(), commande.getSoldeFournisseur());

				serVice.updateSoldeClient(GlobalVar.CurentClient.getId(), commande.getSoldifference());
				bonCommande = null;
				initialiserVariable();
				initTable();
			} else {
				JOptionPane.showMessageDialog(null, " veillez saisie les informations valides  \n Merci!");

			}

		} else {
			JOptionPane.showMessageDialog(null, " aucun Montant Saisi  \n Merci!");
		}

		//////////////////////////////////////////////////////////////////////////////////////
	}

	private void initialiserVariable() {
		// TODO Auto-generated method stub
		textFieldnumBon.setText("");
		textFielTextClient.setText("");
		textFieldQuantite.setText("");
		textFieldCamion.setText("");
		textFieldApayer.setText("");
		textFielAvance.setText("");
		GlobalVar.CurentClient = null;
		bonCommande = null;

	}

	public boolean isNumeric(String contenuVariable) {
		try {
			double entier = Double.parseDouble(contenuVariable);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void actualiser(int provenance) {
		// TODO Auto-generated method stub
		System.out.println(" actualisation ddddddddddddddddd");
		initTable();
	}
}
