package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;

import Global.GlobalVar;
import dao.InterService;
import dao.UserService;
import model.BonCommande;
import model.Fournisseurs;
import model.TypeAgregats;
import model.User;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;

public class Custom extends JDialog {
	public InterService serVice = new UserService();
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCamion;
	private JTextField textFieldMontant;
	private JTextField textFieldQte;
	private JTextField textFieldName;
	private JComboBox comboBox;
	private JComboBox comboBoxtextNumBon;
	private DialogCallback dialogCallback;
	private JDateChooser dateChooser;
	private JTextField textFieldAvancer;
	private JRadioButton rRadioButtonAvancer ;
	private JRadioButton RadioButtonNonPayer ;
	private JRadioButton rRadioButtonComptant ;
    private javax.swing.JTable jTable1;
    List<User> list;
    FrameConnexion connexion=new FrameConnexion();
	public JComboBox getComboBox() {
		return comboBox;
	}

	public JTextField getTextFieldAvancer() {
		return textFieldAvancer;
	}

	public void setTextFieldAvancer(JTextField textFieldAvancer) {
		this.textFieldAvancer = textFieldAvancer;
	}

	public JRadioButton getrRadioButtonAvancer() {
		return rRadioButtonAvancer;
	}

	public void setrRadioButtonAvancer(JRadioButton rRadioButtonAvancer) {
		this.rRadioButtonAvancer = rRadioButtonAvancer;
	}

	public JRadioButton getRadioButtonNonPayer() {
		return RadioButtonNonPayer;
	}

	public void setRadioButtonNonPayer(JRadioButton radioButtonNonPayer) {
		RadioButtonNonPayer = radioButtonNonPayer;
	}

	public JRadioButton getrRadioButtonComptant() {
		return rRadioButtonComptant;
	}

	public void setrRadioButtonComptant(JRadioButton rRadioButtonComptant) {
		this.rRadioButtonComptant = rRadioButtonComptant;
	}

	public JComboBox getComboBoxNumeroBon() {
		return comboBoxtextNumBon;
	}

	public void setComboBoxNumeroBon(JComboBox comboBoxtextNumBon) {
		this.comboBoxtextNumBon = comboBoxtextNumBon;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JTextField getTextFieldCamion() {
		return textFieldCamion;
	}

	public void setTextFieldCamion(JTextField textFieldCamion) {
		this.textFieldCamion = textFieldCamion;
	}

	public JTextField getTextFieldMontant() {
		return textFieldMontant;
	}

	public void setTextFieldMontant(JTextField textFieldMontant) {
		this.textFieldMontant = textFieldMontant;
	}

	public JTextField getTextFieldQte() {
		return textFieldQte;
	}

	public void setTextFieldQte(JTextField textFieldQte) {
		this.textFieldQte = textFieldQte;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	

	/*
	 * public JComboBox getComboBox() { return comboBox; }
	 * 
	 * public void setComboBox(JComboBox comboBox) { this.comboBox = comboBox; }
	 */

	public void setDialogCallback(DialogCallback callback) {
		this.dialogCallback = callback;
	}

	public JDateChooser getdateChooser() {
		return dateChooser;
	}

	public void setdateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public Custom(JFrame parent) {
		super(parent, "Dialog", true);
		setBounds(100, 100, 702, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lbTupe = new JLabel("TYPE D'AGREGATS");
		lbTupe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbTupe.setHorizontalAlignment(SwingConstants.CENTER);
		lbTupe.setBounds(343, 123, 129, 22);
		contentPanel.add(lbTupe);

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
		comboBox = new JComboBox(elements);
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBox.setBounds(479, 120, 181, 31);

		contentPanel.add(comboBox);

		String[] elementCommandes = new String[GlobalVar.listBonCommande.size()];
		int idexp = 0;
		for (BonCommande boncommande : GlobalVar.listBonCommande) {
		    elementCommandes[idexp] = boncommande.getNumerobon();
			idexp++;
		}
		comboBoxtextNumBon = new JComboBox(elementCommandes);
		comboBoxtextNumBon.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBoxtextNumBon.setBounds(479, 188, 171, 37);
		contentPanel.add(comboBoxtextNumBon);

		////////////////////////////////////////////////////////////////////////////////////////

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 686, 70);
		contentPanel.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabelAvancer = new JLabel("Saisie M avancer");
		lblNewLabelAvancer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabelAvancer.setForeground(new Color(0, 0, 255));
	
		String imagePath = "logo1.jpg";
		 int desiredWidth = 290;
	        int desiredHeight = 67;
		
	//	BufferedImage resizedImage = FrameConnexion.resizeImage(imagePath, desiredWidth, desiredHeight);
	//	    ImageIcon icon = new ImageIcon(resizedImage);
	      //  JLabel lblNewLabel = new JLabel("ENREGISTREMENT D'UNE NOUVELLE COMMANDE",icon, JLabel.LEFT);
			JLabel lblNewLabel = new JLabel("ENREGISTREMENT D'UNE NOUVELLE COMMANDE");
			lblNewLabel.setForeground(Color.WHITE);
			//lblNewLabel.setIcon(new ImageIcon("logo 1.jpg"));
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblNewLabel.setBounds(0, 0, 708, 70);
			
			panel.add(lblNewLabel);
		JLabel lblBon = new JLabel("N\u00B0 BON");
		lblBon.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBon.setHorizontalAlignment(SwingConstants.LEFT);
		lblBon.setBounds(346, 190, 137, 31);
		contentPanel.add(lblBon);

		JLabel lbCaminion = new JLabel("CAMION");
		lbCaminion.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbCaminion.setHorizontalAlignment(SwingConstants.LEFT);
		lbCaminion.setBounds(16, 277, 71, 22);
		contentPanel.add(lbCaminion);

		textFieldCamion = new JTextField();
		textFieldCamion.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textFieldCamion.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCamion.setColumns(10);
		textFieldCamion.setBounds(143, 271, 171, 37);
		contentPanel.add(textFieldCamion);

		JLabel lbMontant = new JLabel("MONTANT");
		lbMontant.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lbMontant.setBounds(16, 361, 82, 27);
		contentPanel.add(lbMontant);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class); // Type de nombre accepté (Integer)

		// formatter.setMinimum(0); // Valeur minimale
		// formatter.setMaximum(100); // Valeur maximale
		formatter.setAllowsInvalid(true); // Empêche les valeurs non valides

		// textFieldMontant.setColumns(10);

		// textFieldMontant = new JFormattedTextField(formatter);
		textFieldMontant = new JTextField();
		textFieldMontant.setForeground(new Color(139, 0, 0));
		textFieldMontant.setFont(new Font("Times New Roman", Font.BOLD, 26));

		textFieldMontant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMontant.setColumns(10);
		textFieldMontant.setBounds(143, 357, 171, 37);
		contentPanel.add(textFieldMontant);

		JLabel lblNameClient = new JLabel("NOM DU CLIENT\r\n");
		lblNameClient.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNameClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameClient.setBounds(12, 119, 129, 31);
		contentPanel.add(lblNameClient);

		JLabel lblDate = new JLabel("DATE\r\n");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setBounds(346, 359, 71, 31);
		contentPanel.add(lblDate);

		JLabel lblQte = new JLabel("QUANTITE (T)");
		lblQte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQte.setHorizontalAlignment(SwingConstants.LEFT);
		lblQte.setBounds(343, 273, 126, 31);
		contentPanel.add(lblQte);

		textFieldQte = new JTextField();
		textFieldQte.setFont(new Font("Times New Roman", Font.BOLD, 27));
		textFieldQte.setForeground(new Color(0, 0, 0));
		textFieldQte.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQte.setColumns(10);
		textFieldQte.setBounds(479, 271, 171, 37);
		contentPanel.add(textFieldQte);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setColumns(10);
		textFieldName.setBounds(143, 117, 171, 37);
		contentPanel.add(textFieldName);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(479, 351, 171, 37);
		dateChooser.setDate(new Date()); // Définir la date minimale
		contentPanel.add(dateChooser);

		rRadioButtonComptant = new JRadioButton("payer comptant");
		rRadioButtonComptant.setForeground(new Color(0, 128, 0));
		rRadioButtonComptant.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rRadioButtonAvancer = new JRadioButton("Avancer");
		rRadioButtonAvancer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rRadioButtonAvancer.setForeground(new Color(0, 128, 0));
		RadioButtonNonPayer = new JRadioButton("Non Payer");
		RadioButtonNonPayer.setForeground(new Color(0, 128, 0));
		RadioButtonNonPayer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rRadioButtonAvancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAvancer.setVisible(true);
				lblNewLabelAvancer.setVisible(true);
				textFieldAvancer.enable(true);
				lblNewLabelAvancer.enable(true);
			}
		});
		rRadioButtonComptant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAvancer.setVisible(false);
				lblNewLabelAvancer.setVisible(false);
				textFieldAvancer.enable(false);
				lblNewLabelAvancer.enable(false);
			}
		});
		RadioButtonNonPayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAvancer.setVisible(false);
				lblNewLabelAvancer.setVisible(false);
				textFieldAvancer.enable(false);
				lblNewLabelAvancer.enable(false);
			}
		});

		rRadioButtonComptant.setBounds(20, 455, 121, 23);
		rRadioButtonAvancer.setBounds(164, 455, 121, 23);
		RadioButtonNonPayer.setBounds(300, 455, 121, 23);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rRadioButtonComptant);
		buttonGroup.add(rRadioButtonAvancer);
		buttonGroup.add(RadioButtonNonPayer);

		contentPanel.add(rRadioButtonComptant);
		contentPanel.add(rRadioButtonAvancer);
		contentPanel.add(RadioButtonNonPayer);

		textFieldAvancer = new JTextField();
		textFieldAvancer.setForeground(new Color(139, 0, 0));
		textFieldAvancer.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textFieldAvancer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAvancer.setVisible(false);
		textFieldAvancer.setEnabled(false);
		textFieldAvancer.setBounds(175, 503, 139, 37);
		contentPanel.add(textFieldAvancer);
		textFieldAvancer.setColumns(10);
		 jTable1 = new javax.swing.JTable();
		lblNewLabelAvancer.setEnabled(false);
		lblNewLabelAvancer.setVisible(false);
		lblNewLabelAvancer.setBounds(38, 504, 127, 37);
		contentPanel.add(lblNewLabelAvancer);

		// Add action listener to the combo box
		/*
		 * comboBox_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { // Get the selected item from the combo box
		 * String selectedItem = (String) comboBox_1.getSelectedItem();
		 * textFieldName.setText(selectedItem); } });
		 */

		// Add a key listener to the text field for auto-completion
		textFieldName.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				// Get the text entered in the text field
				String enteredText = textFieldName.getText();
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
		
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(143, 175, 173, 85);
		jScrollPane1.setViewportView(jTable1);
		contentPanel.add(jScrollPane1);

		
		  ListSelectionModel selectionModel = jTable1.getSelectionModel();
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        selectionModel.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                   int selectedRow = jTable1.getSelectedRow();
	                   GlobalVar.CurentClient=  list.get(selectedRow);
	                   textFieldName.setText(GlobalVar.CurentClient.getName());
	                 }
	            }
	        });
	      
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(SystemColor.textHighlight);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dialogCallback != null) {
					dialogCallback.onButtonClicked();
				}
				JOptionPane.showMessageDialog(null, "Operation effectué avec succes\n merci!");

				dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	private String[] getMatchingItems(String text) {
		List<User> listUser = serVice.getListUser(text);
		String[] elements = new String[listUser.size()];
		//int idex = 0;
		for (User user : listUser) {
			/*elements[idex] = user.getName();
			idex++;*/
		}
		return elements;
	}
	
	
	 public void actualiserTabeau(String text) {
		  list = serVice.getListUser(text);
		
		  Object[][] tableau = new Object[list.size()][1];
			int i = 0;
			System.out.println("__________________________________");
			System.out.println(list.size());
			System.out.println("__________________________________");
			for (User user : list) {
				tableau[i][0] = user.getName();			
				i++;
			}

			DefaultTableModel model = new DefaultTableModel(tableau, new String[] {"NOM DU CLIENT"})
			{
	            @Override
	            public Class<?> getColumnClass(int columnIndex) {
	            	return super.getColumnClass(columnIndex);
	            }

	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return column == 0; // Seule la colonne des cases à cocher est éditable
	            }
	        };
			 jTable1.setModel(model);
		  
		  
		  
	  }
	
	

}
