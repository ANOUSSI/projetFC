/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vue;

import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.InterService;
import dao.UserService;
import model.BonCommande;
import model.Fournisseurs;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 *
 * @author ANOUSSI
 */
public class FrameAddBon extends javax.swing.JDialog {
	List<Fournisseurs> list= new ArrayList<>();
	public FrameAddBon(java.awt.Frame parent) {
		super(parent, "Dialog", true);
		setTitle("FC");
		 list=serVice.getListFournisseur();
		initComponents();
	}

	
	public String commpleteZer(String numbero) {
		String zero="";
		if(numbero.length()<8) {
			String nombre="";
			for (int i=0;i<8-numbero.length();i++) {
				zero+="0";
			}
		}
		zero +=numbero;
		return zero;
		
	}
	@SuppressWarnings("unchecked")

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		textFieldFirstNumBon = new javax.swing.JTextField();
		textFieldFirstNumBon.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFirstNumBon.setFont(new Font("Times New Roman", Font.BOLD, 13));

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(51, 102, 255));

		jButton1.setText("OK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Cancel");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(393, Short.MAX_VALUE)
								.addComponent(jButton1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton2).addGap(71, 71, 71)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButton1).addComponent(jButton2))
								.addGap(19, 19, 19)));

		jPanel2.setBackground(Color.BLUE);
		 String imagePath = "logo1.jpg";
	      
	        int desiredWidth = 300;
	        int desiredHeight = 60;
	      //  BufferedImage resizedImage = FrameConnexion.resizeImage(imagePath, desiredWidth, desiredHeight);

	        
	     //   ImageIcon icon = new ImageIcon(resizedImage);
		//JLabel lblNewLabel_1 = new JLabel("GENERATION DES BONS",icon, JLabel.LEFT);
		JLabel lblNewLabel_1 = new JLabel("GENERATION DES BONS");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.WHITE);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
		);
		jPanel2.setLayout(jPanel2Layout);

		jLabel2.setText("premier N\u00B0 de bon sur le cahier");

		jLabel3.setText("Nom Fournisseur");

		JLabel lblNewLabel = new JLabel("Dernier N\u00B0de Bon sur le cahier");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));

		textFieldDerniernumBon = new JTextField();
		textFieldDerniernumBon.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDerniernumBon.setFont(new Font("Times New Roman", Font.BOLD, 13));
		textFieldDerniernumBon.setColumns(10);

	

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.textHighlight);

		btnGenerer = new JButton("Generer");
		btnGenerer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumBon = Integer.parseInt(textFieldFirstNumBon.getText().toString());
				lastNumBon = Integer.parseInt(textFieldDerniernumBon.getText().toString());
				
				
				progressBar.setToolTipText("ttt");
				progressBar.setStringPainted(true);
				progressBar.setMaximum(lastNumBon);
				progressBar.setMinimum(0);
				progressBar.setValue(0);
				textArea.setLineWrap(true);
		
				final StringBuilder annomalis = new StringBuilder("");
				System.out.println(firstNumBon);
				Runnable runnable = () -> {
					int i = 1;
					while (firstNumBon <= lastNumBon) {
						updateProgressBar(lastNumBon);
						 int numgenere=	firstNumBon;
					     String numeInser=commpleteZer(String.valueOf(numgenere));						
					     printAnnomalie(annomalis, "le" + i + " eme numero de bon generer est " + numeInser );
					     
					     // insertion dans la bd
					    int  index = comboBox.getSelectedIndex();
					    Fournisseurs four=list.get(index);
					    BonCommande boncommande=new BonCommande();
					    boncommande.setNumerobon(numeInser);
					    boncommande.setIdfournisseur(four.getId());
					    boncommande.setNomFournisseur(four.getName());
					   // boncommande.setIdboncommande();
					    boncommande.setQuantite(0);
					    boncommande.setUtilise(false);
					    BonCommande bonCommande= serVice.saveBonCommande( boncommande);
					  
					     firstNumBon++;
						i++;
					}

				};
				new Thread(runnable).start();
			}
		});
		btnGenerer.setBackground(SystemColor.textHighlight);
		btnGenerer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
	
		
		
		
		String[] elements= new  String[list.size()];
		 int idex=0;
		 for(Fournisseurs fourniseur: list) {
			 elements[idex]=fourniseur.getName();
			 idex++;
		 }
		
		
		 comboBox = new JComboBox(elements); 
		// scrollPane = new JScrollPane(textArea);
		/* scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);*/
		// panel.add(scrollPane);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(689)
					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
					.addGap(48)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(jLabel3))
					.addGap(26)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, 0, 184, Short.MAX_VALUE)
						.addComponent(textFieldFirstNumBon, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(textFieldDerniernumBon, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
					.addGap(285))
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(layout.createSequentialGroup()
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(layout.createSequentialGroup()
					.addGap(109)
					.addComponent(btnGenerer, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(427, Short.MAX_VALUE))
				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel2)
						.addComponent(textFieldFirstNumBon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldDerniernumBon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(32)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel3)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnGenerer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();

	}

	private void printAnnomalie(StringBuilder annomalis, String e) {
        annomalis.append(e + "\n");
        textArea.setText(annomalis.toString());
    }
	private int updateProgressBar(int i) {
		progressBar.setValue(++i);

		return i;

	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FrameAddBon.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrameAddBon.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrameAddBon.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrameAddBon.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				FrameAddBon dialog = new FrameAddBon(new javax.swing.JFrame());
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

	FrameConnexion login=new FrameConnexion();
	JTextArea textArea = new JTextArea(10,15);
	private JScrollPane scrollPane;
	JProgressBar progressBar = new JProgressBar();
	private DialogCallback dialogCallback;
	public InterService serVice = new UserService();
	private JPanel panel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextField textFieldFirstNumBon;
	private JTextField textFieldDerniernumBon;
	private JButton btnGenerer;
	int firstNumBon;
	int lastNumBon;
	String nomFournisseur;
	int numGenerer;
	private JComboBox comboBox;

	public int getFirstNumBon() {
		return firstNumBon;
	}

	public void setFirstNumBon(int firstNumBon) {
		this.firstNumBon = firstNumBon;
	}

	public int getLastNumBon() {
		return lastNumBon;
	}

	public void setLastNumBon(int lastNumBon) {
		this.lastNumBon = lastNumBon;
	}

	public String getNomFournisseur() {
		return nomFournisseur;
	}

	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
	}

	public int getNumGenerer() {
		return numGenerer;
	}

	public void setNumGenerer(int numGenerer) {
		this.numGenerer = numGenerer;
	}

	public DialogCallback getDialogCallback() {
		return dialogCallback;
	}

	public void setDialogCallback(DialogCallback dialogCallback) {
		this.dialogCallback = dialogCallback;
	}

	public javax.swing.JTextField getjTextFieldFirstNumBon() {
		return textFieldFirstNumBon;
	}

	public void setjTextFieldFirstNumBon(javax.swing.JTextField jTextFieldFirstNumBon) {
		this.textFieldFirstNumBon = jTextFieldFirstNumBon;
	}
}
