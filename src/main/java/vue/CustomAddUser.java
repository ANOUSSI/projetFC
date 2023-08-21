package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.InterService;
import dao.UserService;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class CustomAddUser extends JDialog {
	public InterService serVice = new UserService();
	private final JPanel contentPanel = new JPanel();
	private JTextField textNameClient;
	private JTextField textFieldPrenomClient;
	private JTextField textFieldTel;
	private JTextField textFieldAdresse;
	private JTextField textFieldSolde;
	private DialogCallback dialogCallback;
	
	
	/*
	 * AddNewSoldeClient dialog1; private void
	 * jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	 * 
	 * dialog1 = new AddNewSoldeClient(); dialog1.setLocationRelativeTo(null);
	 * //dialog1.setDialogCallback(this); dialog1.show(); }
	 */
	
	
	
	public DialogCallback getDialogCallback() {
		return dialogCallback;
	}

	public void setDialogCallback(DialogCallback dialogCallback) {
		this.dialogCallback = dialogCallback;
	}

	public JTextField getTextNameClient() {
		return textNameClient;
	}

	public void setTextNameClient(JTextField textNameClient) {
		this.textNameClient = textNameClient;
	}

	public JTextField getTextFieldPrenomClient() {
		return textFieldPrenomClient;
	}

	public void setTextFieldPrenomClient(JTextField textFieldPrenomClient) {
		this.textFieldPrenomClient = textFieldPrenomClient;
	}

	public JTextField getTextFieldTel() {
		return textFieldTel;
	}

	public void setTextFieldTel(JTextField textFieldTel) {
		this.textFieldTel = textFieldTel;
	}

	public JTextField getTextFieldAdresse() {
		return textFieldAdresse;
	}

	public void setTextFieldAdresse(JTextField textFieldAdresse) {
		this.textFieldAdresse = textFieldAdresse;
	}

	public JTextField getTextFieldSolde() {
		return textFieldSolde;
	}

	public void setTextFieldSolde(JTextField textFieldSolde) {
		this.textFieldSolde = textFieldSolde;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public CustomAddUser(JDialog parent) {
		super(parent, "Fraiche Construction", true);
		setBounds(100, 100, 670, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("NOM DU CLIENT");
			lblName.setBounds(48, 76, 140, 14);
			lblName.setFont(new Font("Times New Roman", Font.BOLD, 15));
			contentPanel.add(lblName);
		}
		
		JLabel lbPrenom = new JLabel("PRENOM");
		lbPrenom.setBounds(48, 130, 101, 14);
		lbPrenom.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPanel.add(lbPrenom);
		
		JLabel lblNewLabel_2 = new JLabel("TELEPHONE");
		lblNewLabel_2.setBounds(48, 194, 101, 14);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPanel.add(lblNewLabel_2);
		
		JLabel lbAdresse = new JLabel("ADRESSE");
		lbAdresse.setBounds(48, 254, 101, 14);
		lbAdresse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPanel.add(lbAdresse);
		
		JLabel lbSolde = new JLabel("SOLDE");
		lbSolde.setBounds(48, 316, 101, 14);
		lbSolde.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPanel.add(lbSolde);
		
		textNameClient = new JTextField();
		textNameClient.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textNameClient.setBounds(198, 67, 195, 33);
		contentPanel.add(textNameClient);
		textNameClient.setColumns(10);
		
		textFieldPrenomClient = new JTextField();
		textFieldPrenomClient.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textFieldPrenomClient.setBounds(198, 122, 195, 33);
		contentPanel.add(textFieldPrenomClient);
		textFieldPrenomClient.setColumns(10);
		
		textFieldTel = new JTextField();
		textFieldTel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldTel.setBounds(198, 186, 195, 33);
		contentPanel.add(textFieldTel);
		textFieldTel.setColumns(10);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldAdresse.setBounds(198, 246, 195, 33);
		contentPanel.add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		
		textFieldSolde = new JTextField();
		textFieldSolde.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textFieldSolde.setForeground(new Color(128, 0, 0));
		textFieldSolde.setBounds(198, 308, 195, 33);
		textFieldSolde.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(textFieldSolde);
		textFieldSolde.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 654, 56);
		panel.setBackground(SystemColor.textHighlight);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AJOUT D'UN NOUVEAU CLIENT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setBounds(0, 0, 654, 56);
		panel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-10, 365, 664, 62);
			buttonPane.setBackground(SystemColor.textHighlight);
			contentPanel.add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(425, 11, 47, 23);
				okButton.addActionListener(new ActionListener() {
        		
					public void actionPerformed(ActionEvent e) {
						
						
					String name=	textNameClient .getText().toString();
					String password=null;
					String prenom=	textFieldPrenomClient .getText().toString();
					String telephone=	textFieldTel .getText().toString();
					String adresse=	textFieldAdresse .getText().toString();
					int soldeClient=	Integer.parseInt(textFieldSolde.getText().toString());
					
				  User user=new User();
				 
				  user.setAdresse(adresse);
						  user.setName(name);
						  user.setPassword(password);
						  user.setTelephone(telephone);
						  user.setSolde(soldeClient);
						  user.setPrenom(prenom);
						  User userC=serVice.saveUser(user);
						  if(userC!=null){
							  JOptionPane.showMessageDialog(null,
										 user.getName() + " a ete Ajouter avec succes\n Merci! ");
						  }else {
							  JOptionPane.showMessageDialog(null,
										 user.getName() + " erreur l'or de l'enregistrement Contacté l'admin\n Merci! "); 
						  }
						  dispose();
						  
						  dialogCallback.actualiser(1);
						  
						
					}
				});
				buttonPane.setLayout(null);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(495, 11, 97, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(403, 56, 251, 306);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		String imagePath = "user.png";
		 int desiredWidth = 300;
	        int desiredHeight = 300;
	     //   BufferedImage resizedImage =FrameConnexion.resizeImage(imagePath, desiredWidth, desiredHeight);
	      //  ImageIcon icon = new ImageIcon(resizedImage);
	        
	        
	        
	    //	JLabel lblNewLabel_1 = new JLabel("",icon, JLabel.LEFT);
	        
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 251, 306);
		panel_1.add(lblNewLabel_1);
		
	}
	
	
}
