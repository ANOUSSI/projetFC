package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Global.GlobalVar;
import model.BonCommande;
import model.Commande;
import model.Fournisseurs;
import model.MyConnexion;
import model.Statistique;
import model.TypeAgregats;
import model.User;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserService implements InterService {
	MyConnexion connexion;

	PreparedStatement statement;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
	SimpleDateFormat sdff = new SimpleDateFormat("yyyy MMMM dd");

	public UserService() {
		super();
		connexion = new MyConnexion();
	}

	@Override
	public List<Commande> getListComment(int idUser) {
		String query = "SELECT\r\n" + "commande.id as idcommande,boncommande.idboncommande,\r\n"
				+ "boncommande.quantite,\r\n" + "boncommande.numero_bon,\r\n" + "boncommande.idfournisseur,\r\n"
				+ "boncommande.utiliser,\r\n" + "boncommande.nom_fournisseur,\r\n" + "fournisseur.nom,\r\n"
				+ "fournisseur.solde,\r\n" + "user.name,user.prenom, \r\n" + "user.Telephone,\r\n" + "user.adresse,\r\n"
				+ "commande.id as idcommande,commande.supprimer,\r\n" + "commande.idbon,\r\n" + "commande.id_client,\r\n"
				+ "commande.caminion,\r\n" + "commande.id,\r\n" + "commande.quantite as quantitecommande,\r\n"
				+ "commande.qualite,\r\n" + "commande.montant,\r\n" + "commande.id_user,\r\n" + "commande.type,\r\n"
				+ "typeagregats.id as idtype,\r\n" + "typeagregats.montant as montunitaire,\r\n" + "commande.date,\r\n"
				+ "user.solde as soldeuser,\r\n" + "commande.soldefournisseur,\r\n" + "commande.montantapayer,\r\n"
				+ "commande.soldeclient,\r\n" + "commande.anciensoldeclient,\r\n" + "typeagregats.nom as nomagregat\r\n"
				+ " FROM commande ,boncommande,user,fournisseur,typeagregats\r\n" + "WHERE \r\n"
				+ "boncommande.idboncommande=commande.idbon\r\n" + "and\r\n" + "commande.id_client=user.id\r\n"
				+ "and\r\n" + "fournisseur.idfournisseur=boncommande.idfournisseur		\r\n" + "and\r\n"
				+ " typeagregats.nom=	commande.type  order by commande.id desc ";
		System.out.println("requette execute");
		List<Commande> listCommande = new ArrayList<>();
		Commande commande = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setInt(1, idUser);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				commande = new Commande();
				commande.setID(rs.getInt("idcommande"));
				commande.setNUMERO_BON(rs.getString("NUMERO_BON"));
				commande.setCAMION(rs.getString("caminion"));
				commande.setDATE(rs.getString("date"));
				commande.setMONTANT(rs.getInt("MONTANT"));
				commande.setNOM_DU_CLIENT(rs.getString("name") + " " + rs.getString("prenom"));
				commande.setQUANTITE(rs.getString("quantitecommande"));
				commande.setTYPE(rs.getString("TYPE"));
				String val = rs.getString("TYPE");
				if (val.equals("all")) {
					commande.setTYPE("ajout d'un fournisseur");
				} else {
					if (val.equals("update")) {
						commande.setTYPE("modification du solde");
					} else {
						commande.setTYPE(val);
					}

				}
				// commande.setIDBON(rs.getInt("IDBON"));
				commande.setID_CLIENT(rs.getInt("ID_CLIENT"));
				commande.setIdfournisseur(rs.getInt("idfournisseur"));
				commande.setNom_fournisseur(rs.getString("nom_fournisseur"));
				commande.setSolde(rs.getString("solde"));
				commande.setNom(rs.getString("nom"));
				commande.setName(rs.getString("name"));
				commande.setAdresse(rs.getString("adresse"));
				commande.setQuantitecommande(rs.getInt("quantitecommande"));
				commande.setIdtype(rs.getInt("idtype"));
				commande.setNomagregat(rs.getString("nomagregat"));
				commande.setId_user(rs.getInt("id_user"));
				commande.setId_client(rs.getInt("id_client"));
				commande.setIdbon(rs.getInt("idbon"));
				commande.setIdcommande(rs.getInt("idcommande"));
				commande.setMontunitaire(rs.getInt("montunitaire"));
				commande.setUtiliser(rs.getBoolean("utiliser"));
				commande.setTelephone(rs.getString("Telephone"));
				commande.setSoldeuser(rs.getInt("soldeuser"));
				commande.setMontantapayer(rs.getInt("montantapayer"));
				commande.setSoldeFournisseur(rs.getInt("soldefournisseur"));
				commande.setSupprimer(rs.getBoolean("supprimer"));
				commande.setSoldeClient(rs.getInt("soldeclient"));
				commande.setAnciensoldeClient(rs.getInt("anciensoldeclient"));

				listCommande.add(commande);
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println(ex);

		}

		return listCommande;
	}

	public List<Commande> getListCommentText(String text, boolean parnon) {

		String query = "";
		if (parnon) {
			query = "SELECT\r\n" + "commande.id as idcommande, boncommande.idboncommande,\r\n"
					+ "boncommande.quantite,\r\n" + "boncommande.numero_bon,\r\n" + "boncommande.idfournisseur,\r\n"
					+ "boncommande.utiliser,\r\n" + "boncommande.nom_fournisseur,\r\n" + "fournisseur.nom,\r\n"
					+ "fournisseur.solde,\r\n" + "user.name,user.prenom, \r\n" + "user.Telephone,\r\n"
					+ "user.adresse,\r\n" + "commande.id as idcommande,\r\n" + "commande.idbon,\r\n"
					+ "commande.id_client,commande.supprimer,\r\n" + "commande.caminion,\r\n" + "commande.id,\r\n"
					+ "commande.quantite as quantitecommande,\r\n" + "commande.qualite,\r\n" + "commande.montant,\r\n"
					+ "commande.id_user,\r\n" + "commande.type,\r\n" + "typeagregats.id as idtype,\r\n"
					+ "typeagregats.montant as montunitaire,\r\n" + "commande.date,\r\n"
					+ "user.solde as soldeuser,\r\n" + "commande.soldefournisseur,\r\n" + "commande.montantapayer,\r\n"
					+ "commande.soldeclient,\r\n" + "commande.anciensoldeclient,\r\n"
					+ "typeagregats.nom as nomagregat\r\n"
					+ " FROM commande ,boncommande,user,fournisseur,typeagregats\r\n" + "WHERE \r\n"
					+ "boncommande.idboncommande=commande.idbon\r\n" + "and\r\n" + "commande.id_client=user.id\r\n"
					+ "and\r\n" + "fournisseur.idfournisseur=boncommande.idfournisseur		\r\n" + "and\r\n"
					+ "user.name like '%" + text + "%' \r\n" + "and\r\n"
					+ " typeagregats.nom=	commande.type  order by commande.id desc ";
		} else {
			query = "SELECT\r\n" + "commande.id as idcommande, boncommande.idboncommande,\r\n"
					+ "boncommande.quantite,\r\n" + "boncommande.numero_bon,\r\n" + "boncommande.idfournisseur,\r\n"
					+ "boncommande.utiliser,\r\n" + "boncommande.nom_fournisseur,\r\n" + "fournisseur.nom,\r\n"
					+ "fournisseur.solde,\r\n" + "user.name,user.prenom,\r\n" + "user.Telephone,\r\n"
					+ "user.adresse,\r\n" + "commande.id as idcommande,\r\n" + "commande.idbon,\r\n"
					+ "commande.id_client,commande.supprimer,\r\n" + "commande.caminion,\r\n" + "commande.id,\r\n"
					+ "commande.quantite as quantitecommande,\r\n" + "commande.qualite,\r\n" + "commande.montant,\r\n"
					+ "commande.id_user,\r\n" + "commande.type,\r\n" + "typeagregats.id as idtype,\r\n"
					+ "typeagregats.montant as montunitaire,\r\n" + "commande.date,\r\n"
					+ "user.solde as soldeuser,\r\n" + "commande.soldefournisseur,\r\n" + "commande.montantapayer,\r\n"
					+ "commande.soldeclient,\r\n" + "commande.anciensoldeclient,\r\n"
					+ "typeagregats.nom as nomagregat\r\n"
					+ " FROM commande ,boncommande,user,fournisseur,typeagregats\r\n" + "WHERE \r\n"
					+ "boncommande.idboncommande=commande.idbon\r\n" + "and\r\n" + "commande.id_client=user.id\r\n"
					+ "and\r\n" + "fournisseur.idfournisseur=boncommande.idfournisseur		\r\n" + "and\r\n"
					+ "boncommande.numero_bon like '%" + text + "%' \r\n" + "and\r\n"
					+ " typeagregats.nom=	commande.type   order by commande.id desc ";
		}

		System.out.println("requette execute");
		List<Commande> listCommande = new ArrayList<>();
		Commande commande = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setInt(1, idUser);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				commande = new Commande();
				commande.setID(rs.getInt("idcommande"));
				commande.setIDBON(rs.getInt("idboncommande"));
				commande.setNUMERO_BON(rs.getString("NUMERO_BON"));
				commande.setCAMION(rs.getString("caminion"));
				commande.setDATE(rs.getString("date"));
				commande.setMONTANT(rs.getInt("MONTANT"));
				commande.setNOM_DU_CLIENT(rs.getString("name"));
				commande.setQUANTITE(rs.getString("quantitecommande"));
				commande.setSupprimer(rs.getBoolean("supprimer"));
				// commande.setTYPE(rs.getString("TYPE"));

				String val = rs.getString("TYPE");
				if (val.equals("all")) {
					commande.setTYPE("ajout d'un fournisseur");
				} else {
					if (val.equals("update")) {
						commande.setTYPE("modification du solde");
					} else {
						commande.setTYPE(val);
					}

				}

				// commande.setIDBON(rs.getInt("IDBON"));
				commande.setID_CLIENT(rs.getInt("ID_CLIENT"));
				commande.setIdfournisseur(rs.getInt("idfournisseur"));
				commande.setNom_fournisseur(rs.getString("nom_fournisseur"));
				commande.setSolde(rs.getString("solde"));
				commande.setNom(rs.getString("nom"));
				commande.setName(rs.getString("name"));
				commande.setAdresse(rs.getString("adresse"));
				commande.setQuantitecommande(rs.getInt("quantitecommande"));
				commande.setIdtype(rs.getInt("idtype"));
				commande.setNomagregat(rs.getString("nomagregat"));
				commande.setId_user(rs.getInt("id_user"));
				commande.setId_client(rs.getInt("id_client"));
				commande.setIdbon(rs.getInt("idbon"));
				commande.setIdcommande(rs.getInt("idcommande"));
				commande.setMontunitaire(rs.getInt("montunitaire"));
				commande.setUtiliser(rs.getBoolean("utiliser"));
				commande.setTelephone(rs.getString("Telephone"));
				commande.setSoldeuser(rs.getInt("soldeuser"));
				commande.setMontantapayer(rs.getInt("montantapayer"));
				commande.setSoldeFournisseur(rs.getInt("soldefournisseur"));

				commande.setSoldeClient(rs.getInt("soldeclient"));
				commande.setAnciensoldeClient(rs.getInt("anciensoldeclient"));

				listCommande.add(commande);
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println(ex);

		}

		return listCommande;
	}

	@Override
	public List<Commande> getListComment(String date) {
		String query = "SELECT * FROM commande WHERE Date=?";
		Commande commande = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setString(1, date);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				commande = new Commande();
				commande.setID(rs.getInt("id"));
				commande.setNUMERO_BON(rs.getString("numero_bon"));
				commande.setCAMION(rs.getString("nom_client"));
				commande.setDATE(rs.getString("caminion"));
				commande.setMONTANT(rs.getInt("montant"));
				commande.setNOM_DU_CLIENT(rs.getString("qualite"));
				commande.setQUANTITE(rs.getString("quantite"));
				commande.setTYPE(rs.getString("Date"));
				commande.setIduser(rs.getInt("id_user"));
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<Commande>) commande;
	}

	@Override
	public Commande getCommende(int id) {
		String query = "SELECT * FROM commande WHERE id=?";
		Commande commande = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				commande = new Commande();
				commande.setID(rs.getInt("id"));
				commande.setNUMERO_BON(rs.getString("numero_bon"));
				commande.setCAMION(rs.getString("nom_client"));
				commande.setDATE(rs.getString("caminion"));
				commande.setMONTANT(rs.getInt("quantite"));
				commande.setNOM_DU_CLIENT(rs.getString("qualite"));
				commande.setQUANTITE(rs.getString("montant"));
				commande.setTYPE(rs.getString("Date"));
				commande.setIduser(rs.getInt("id_user"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commande;
	}

	@Override
	public Commande getCommende(String NumBon) {
		String query = "SELECT * FROM commande WHERE numero_bon=?";
		Commande commande = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setString(1, NumBon);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				commande = new Commande();
				commande.setID(rs.getInt("id"));
				commande.setNUMERO_BON(rs.getString("numero_bon"));
				commande.setCAMION(rs.getString("nom_client"));
				commande.setDATE(rs.getString("caminion"));
				commande.setMONTANT(rs.getInt("montant"));
				commande.setNOM_DU_CLIENT(rs.getString("quantite"));
				commande.setQUANTITE(rs.getString("montant"));
				commande.setTYPE(rs.getString("Date"));
				commande.setIduser(rs.getInt("id_user"));
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Commande saveCommande(Commande commande1) {

		String query = "insert into commande (idbon,id_client,caminion,quantite,montant,date,id_user,type,soldefournisseur,montantapayer,soldeclient,anciensoldeclient) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try { // id idbon nom_client
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setLong(1, commande1.getIDBON());
				statement.setLong(2, commande1.getID_CLIENT());
				statement.setString(3, commande1.getCAMION());
				statement.setString(4, commande1.getQUANTITE());
				statement.setDouble(5, commande1.getMONTANT());
				statement.setString(6, commande1.getDATE());
				statement.setInt(7, commande1.getIduser());
				statement.setString(8, commande1.getTYPE());
				statement.setDouble(9, commande1.getSoldeFournisseur());
				statement.setDouble(10, commande1.getMontantapayer());
				statement.setDouble(11, commande1.getSoldeClient());
				statement.setDouble(12, commande1.getAnciensoldeClient());
				statement.executeUpdate();
				statement.close();
				// update Numero bon
				updateBonCommande(commande1.getNumero_bon(), true);
				// JOptionPane.showMessageDialog(null,
				// "la commande de" + commande1.getNOM_DU_CLIENT() + " a ete Ajouter avec
				// succes\n Merci! ");

			} else {
				JOptionPane.showMessageDialog(null, "la connexion est null ");

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return commande1;
	}

	@Override
	public boolean deleteCommande(long id) {
		
		String query = "DELETE FROM commande WHERE id = ?";
		try {
		    if (connexion == null) {
		        connexion = new MyConnexion();
		    }
		    statement = connexion.getConnection().prepareStatement(query);
		    statement.setLong(1, id);
		    
		    int rowsDeleted = statement.executeUpdate(); // Execute the delete statement
		    
		    if (rowsDeleted > 0) {
		        System.out.println("Record deleted successfully.");
		    } else {
		        System.out.println("No matching record found to delete.");
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		

		return true;
	}
	
	
	//////////////////////////////////////////////////////////////////////
	
	
	@Override
	public boolean updateCommandeSuppression(long id, boolean bol) {
		// TODO Auto-generated method stub
		String query = "update commande set supprimer=? where id =?";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setBoolean(1, bol);
				statement.setLong(2, id);
				statement.executeUpdate();
				statement.close();
				return true;
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
  return false;
	}
	
	
	
	//////////////////////////////////////////////////////////////////
	

	@Override
	public boolean deleteCommande(String Numero_Bon) {
		String query = "DELETE FROM commande WHERE  numero_bon =?";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.getConnection().prepareStatement(query);
			statement.setString(1, Numero_Bon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public User get(String login, String password) {
		// TODO Auto-generated method stub
		String query = "SELECT* FROM user WHERE name=? and password=? and actif=1";
		System.out.println("requette execute");
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSolde(rs.getInt("solde"));
			}
			rs.close();
			statement.close();

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return user;

	}

	@Override
	public User saveUser(User user) {
		String query = "insert into user (name,password,Telephone,adresse,actif,prenom,solde) values(?,?,?,?,?,?,?)";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setString(1, user.getName());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getTelephone());
				statement.setString(4, user.getAdresse());
				statement.setBoolean(5, false);
				statement.setString(6, user.getPrenom());
				statement.setInt(7, user.getSolde());

				statement.executeUpdate();
				statement.close();

				user.setId(getMaxId("user"));
				/*
				 * JOptionPane.showMessageDialog(null, user.getName() +
				 * " a ete Ajouter avec succes\n Merci! ");
				 */
			} else {
				JOptionPane.showMessageDialog(null, "la connexion est null ");

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return user;
	}

	public int getMaxId(String table) {
		// TODO Auto-generated method stub
		String query = "SELECT MAX(id)  as id FROM " + table;
		int id = 0;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			statement.close();

		} catch (SQLException ex) {
			// Exception handling

			System.out.println(ex);

		}

		return id;

	}

	@Override
	public List<BonCommande> getListBonCommande() {
		List<BonCommande> listBonCommand = new ArrayList<>();
		// String query = "SELECT * FROM boncommande";
		String query = "SELECT idboncommande,iduser,numero_bon,quantite,nom_fournisseur,boncommande.idfournisseur,utiliser,solde ,nom_user,detenteurs,description FROM    \r\n"
				+ "boncommande, fournisseur \r\n" + "where \r\n"
				+ "boncommande.idfournisseur=fournisseur.idfournisseur and 'system'=0 order by idboncommande desc";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setUtilise(rs.getBoolean("utiliser"));
				bon.setSoldeFournisseur(rs.getInt("solde"));
				bon.setNomUsers(rs.getString("detenteurs"));
				bon.setDescription(rs.getString("description"));
				bon.setIdUser(rs.getInt("iduser"));
				listBonCommand.add(bon);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBonCommand;
	}

	public BonCommande getBonCommandewithId(String numero_Bon,boolean utilise) {
		// List<BonCommande> listBonCommand = new ArrayList<>();
		// String query = "SELECT * FROM boncommande";
		String query = "SELECT idboncommande,iduser,utiliser,numero_bon,quantite,nom_fournisseur,boncommande.idfournisseur,utiliser,solde ,nom_user,detenteurs,description FROM    \r\n"
				+ "boncommande, fournisseur \r\n" + "where \r\n"
				+ "boncommande.idfournisseur=fournisseur.idfournisseur and 'system'=0 and utiliser=0 and numero_bon = '"
				+ numero_Bon + "'   order by idboncommande desc";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setUtilise(rs.getBoolean("utiliser"));
				bon.setSoldeFournisseur(rs.getInt("solde"));
				bon.setNomUsers(rs.getString("detenteurs"));
				bon.setDescription(rs.getString("description"));
				bon.setIdUser(rs.getInt("iduser"));

			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bon;
	}

	@Override
	public List<BonCommande> getListBonCommande(boolean utilise) {
		List<BonCommande> listBonCommand = new ArrayList<>();
		// String query = "SELECT * FROM boncommande";
		String query = "SELECT idboncommande,iduser,numero_bon,quantite,nom_fournisseur,boncommande.idfournisseur,utiliser,solde,detenteurs,description  FROM    \r\n"
				+ "boncommande, fournisseur \r\n" + "where \r\n"
				+ "boncommande.idfournisseur=fournisseur.idfournisseur and boncommande.utiliser=" + utilise
				+ " order by idboncommande desc";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setUtilise(rs.getBoolean("utiliser"));
				bon.setSoldeFournisseur(rs.getInt("solde"));
				bon.setNomUsers(rs.getString("detenteurs"));
				bon.setDescription(rs.getString("description"));
				bon.setIdUser(rs.getInt("iduser"));
				listBonCommand.add(bon);

			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBonCommand;
	}

	@Override
	public BonCommande getFirstBonCommande(boolean utilise) {
		String query = "SELECT idboncommande,iduser,numero_bon,quantite,nom_fournisseur,boncommande.idfournisseur,utiliser,solde,nom_user,detenteurs ,description FROM    \r\n"
				+ "boncommande, fournisseur \r\n" + "where \r\n"
				+ "boncommande.idfournisseur=fournisseur.idfournisseur  and boncommande.utiliser=" + utilise
				+ " order by idboncommande limit 1";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setUtilise(rs.getBoolean("utiliser"));
				bon.setSoldeFournisseur(rs.getInt("solde"));
				bon.setDescription(rs.getString("description"));
				bon.setNomUsers(rs.getString("detenteurs"));
				bon.setIdUser(rs.getInt("iduser"));
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bon;
	}

	@Override
	public List<BonCommande> getListBonCommande(int idUser) {
		String query = "SELECT * FROM boncommande WHERE utiliser=false";
		BonCommande bon = null;
		List<BonCommande> listBonCommand = new ArrayList<>();
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setInt(1, idUser);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setIdUser(rs.getInt("iduser"));

				if (rs.getDate("dateactuel") != null) {
					String dateFormatee = sdf.format(rs.getDate("dateactuel"));
					bon.setDates(dateFormatee);
				}

				listBonCommand.add(bon);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBonCommand;
	}

	@Override
	public List<BonCommande> getListBonCommande(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BonCommande getBonCommande(int id) {

		String query = "SELECT * FROM boncommande WHERE idboncommande=?";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setIdUser(rs.getInt("iduser"));
				bon.setUtilise(false);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bon;
	}

	@Override
	public BonCommande getBonCommande(String numeroBon) {
		String query = "SELECT * FROM boncommande WHERE numero_bon=?";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setString(1, numeroBon);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				bon = new BonCommande();
				bon.setIdboncommande(rs.getLong("idboncommande"));
				bon.setNumerobon(rs.getString("numero_bon"));
				bon.setQuantite(rs.getInt("quantite"));
				bon.setIdfournisseur(rs.getInt("idfournisseur"));
				bon.setNomFournisseur(rs.getString("nom_fournisseur"));
				bon.setIdUser(rs.getInt("iduser"));
				bon.setUtilise(false);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bon;
	}

	public void saveListbon(List<BonCommande> listBon) {
		Connection con = connexion.connect();
		if (con != null) {
			try {
				Statement statement = null;

				con.setAutoCommit(false);
				statement = con.createStatement();
				for (BonCommande bon : listBon) {
					try {
						statement.executeUpdate(
								"insert into boncommande (numero_bon,quantite,nom_fournisseur,idfournisseur,utiliser,description) values('"
										+ bon.getNumerobon() + "','" + bon.getQuantite() + "','"
										+ bon.getNomFournisseur() + "','" + bon.getIdfournisseur() + "',"
										+ bon.isUtilise() + ",'" + bon.getDescription() + "')");

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
				con.commit();
				statement.close();
				con.close();
			} catch (SQLException e1) {

				e1.printStackTrace();

				try {
					if (con != null) {
						con.rollback();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public BonCommande saveBonCommande(BonCommande boncommande) {
		String query = "insert into boncommande (numero_bon,quantite,nom_fournisseur,idfournisseur,utiliser) values(?,?,?,?,?)";
		BonCommande bon = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setString(1, boncommande.getNumerobon());
				statement.setInt(2, boncommande.getQuantite());
				statement.setString(3, boncommande.getNomFournisseur());
				statement.setLong(4, boncommande.getIdfournisseur());
				statement.setBoolean(5, boncommande.isUtilise());
				statement.executeUpdate();
				statement.close();
				bon = getBonCommande(boncommande.getNumerobon());
			} else {
				JOptionPane.showMessageDialog(null, "la connexion est null ");

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return bon;
	}

	@Override
	public boolean deleteBonCommande(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBonCommande(String numeroBon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TypeAgregats> getListTypeAgregats() {
		String query = "SELECT * FROM typeagregats";
		TypeAgregats agregat = null;
		List<TypeAgregats> listAgregat = new ArrayList<>();
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (!rs.getString("nom").equals("all") && !rs.getString("nom").equals("update")) {
					agregat = new TypeAgregats();
					agregat.setId(rs.getInt("id"));
					agregat.setNom(rs.getString("nom"));
					agregat.setMontant(rs.getInt("montant"));
					listAgregat.add(agregat);
				}
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listAgregat;
	}

	@Override
	public List<TypeAgregats> getListTypeAgregats(int idAgregat) {

		return null;
	}

	@Override
	public List<TypeAgregats> getListTypeAgregats(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeAgregats getTypeAgregats(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeAgregats getTypeAgregats(String nomAgrega) {
		String query = "SELECT * FROM typeagregats WHERE nom=?";
		TypeAgregats agregat = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setString(1, nomAgrega);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				agregat = new TypeAgregats();
				agregat.setId(rs.getInt("id"));
				agregat.setMontant(rs.getInt("montant"));
				agregat.setNom(rs.getString("nom"));
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return agregat;
	}

	@Override
	public TypeAgregats saveTypeAgregats(TypeAgregats agregat) {

		return null;
	}

	@Override
	public boolean deleteTypeAgregats(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTypeAgregats(String nomAgrega) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Fournisseurs> getListFournisseur() {
		String query = "SELECT * FROM fournisseur";
		Fournisseurs fournisseur = null;
		List<Fournisseurs> listFoursseurs = new ArrayList<>();
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				fournisseur = new Fournisseurs();
				fournisseur.setId(rs.getInt("idfournisseur"));
				fournisseur.setName(rs.getString("nom"));
				fournisseur.setSolde(rs.getInt("solde"));
				listFoursseurs.add(fournisseur);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listFoursseurs;
	}

	@Override
	public List<Fournisseurs> getListFournisseur(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fournisseurs> getListFournisseur(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fournisseurs getFournisseur(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fournisseurs getFournisseur(String nomFournisseur) {
		String query = "SELECT * FROM fournisseur WHERE nom=?";
		Fournisseurs fournisseur = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setString(1, nomFournisseur);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				fournisseur = new Fournisseurs();
				fournisseur.setId(rs.getInt("idfournisseur"));
				fournisseur.setName(rs.getString("nom"));
				fournisseur.setSolde(rs.getInt("solde"));
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fournisseur;
	}

	@Override
	public boolean saveFournisseur(Fournisseurs fournisseur) {

		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd mm:ss");
		String formattedDateTime = dateTime.format(formatter);
		formattedDateTime = formattedDateTime.replaceAll("\\s", "");
		formattedDateTime = formattedDateTime.replaceAll("\\:", "");
		System.out.println(formattedDateTime);

		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		Date date = new Date();

		// Formatage de la date en utilisant le format sp�cifi�
		String dateFormatee = sdf.format(date);
		Fournisseurs fournisseu = getFournisseur(fournisseur.getName());

		/// System.out.println( "nom du fournisse"+fournisseu.getName());

		if (fournisseu == null) {
			String query = "insert into fournisseur (nom,solde) values(?,?)";
			try {
				if (connexion == null) {
					connexion = new MyConnexion();
				}
				if (connexion != null) {
					statement = connexion.connect().prepareStatement(query);
					// statement.setLong(1, fournisseur.getId());
					statement.setString(1, fournisseur.getName());
					statement.setDouble(2, fournisseur.getSolde());

					statement.executeUpdate();
					statement.close();
					// recuperer le fournisseur
					Fournisseurs four = getFournisseur(fournisseur.getName());

					/// INSERTION DE LA COMMANDE INITIALE
					// INSERTION BON DE COMMANDE
					BonCommande boncommande = new BonCommande();
					boncommande.setNumerobon("000547C" + four.getId() + formattedDateTime);
					boncommande.setIdfournisseur(four.getId());
					boncommande.setNomFournisseur(four.getName());
					// boncommande.setIdboncommande();
					boncommande.setQuantite(0);
					boncommande.setUtilise(true);
					BonCommande bonCommande = saveBonCommande(boncommande);

					/////////////////////////////////////////////////////////////////////////

					// BonCommande boncommande = getFirstBonCommande(false);
					Commande commande1 = new Commande();
					commande1.setIDBON(bonCommande.getIdboncommande());
					commande1.setSoldeFournisseur(fournisseur.getSolde());

					commande1.setMontantapayer(fournisseur.getSolde());
					commande1.setIduser(GlobalVar.IDUSER);
					commande1.setID_CLIENT(GlobalVar.IDUSER);
					commande1.setTYPE("all");
					commande1.setDATE(dateFormatee);
					Commande commande = saveCommande(commande1);

					return true;
				} else {

				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} else {
			double solde = fournisseu.getSolde() + fournisseur.getSolde();
			String query = "update fournisseur set solde=? where nom =?";
			try {
				if (connexion == null) {
					connexion = new MyConnexion();
				}
				if (connexion != null) {
					statement = connexion.connect().prepareStatement(query);
					statement.setDouble(1, solde);
					statement.setString(2, fournisseur.getName());
					statement.executeUpdate();
					statement.close();

					// recuperer le fournisseur
					Fournisseurs four = getFournisseur(fournisseur.getName());

					/// INSERTION DE LA COMMANDE INITIALE
					// INSERTION BON DE COMMANDE
					BonCommande boncommande = new BonCommande();
					boncommande.setNumerobon("000547U" + four.getId() + formattedDateTime);
					boncommande.setIdfournisseur(four.getId());
					boncommande.setNomFournisseur(four.getName());
					// boncommande.setIdboncommande();
					boncommande.setQuantite(0);
					boncommande.setUtilise(true);
					BonCommande bonCommande = saveBonCommande(boncommande);

					/////////////////////////////////////////////////////////////////////////
					// BonCommande boncommande = getFirstBonCommande(false);
					Commande commande1 = new Commande();
					commande1.setIDBON(bonCommande.getIdboncommande());
					commande1.setMontantapayer(fournisseur.getSolde());
					commande1.setSoldeFournisseur(solde);
					commande1.setIduser(GlobalVar.IDUSER);
					commande1.setID_CLIENT(GlobalVar.IDUSER);
					commande1.setTYPE("update");
					commande1.setDATE(dateFormatee);
					Commande commande = saveCommande(commande1);

					return true;
				} else {

				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

		return false;
	}

	/*
	 * public Commande saveCommande(Commande commande1) {
	 * 
	 * String query =
	 * "insert into commande (idbon,id_client,caminion,quantite,montant,date,id_user,type,soldefournisseur) values(?,?,?,?,?,?,?,?,?)"
	 * ; try { //id idbon nom_client if (connexion != null) { statement =
	 * connexion.connect().prepareStatement(query); statement.setLong(1,
	 * commande1.getIDBON()); statement.setLong(2, commande1.getID_CLIENT());
	 * statement.setString(3, commande1.getCAMION()); statement.setString(4,
	 * commande1.getQUANTITE()); statement.setInt(5, commande1.getMONTANT());
	 * statement.setString(6, commande1.getDATE()); statement.setInt(7,
	 * commande1.getIduser()); statement.setString(8, commande1.getTYPE());
	 * statement.setInt(9, commande1.getSoldeFournisseur());
	 * statement.executeUpdate(); statement.close(); // update Numero bon
	 * updateBonCommande(commande1.getNumero_bon());
	 * JOptionPane.showMessageDialog(null, "la commande de" +
	 * commande1.getNOM_DU_CLIENT() + " a ete Ajouter avec succes\n Merci! ");
	 * 
	 * } else { JOptionPane.showMessageDialog(null, "la connexion est null ");
	 * 
	 * } } catch (SQLException e1) {
	 * 
	 * e1.printStackTrace(); } return commande1; }
	 */

	@Override
	public boolean deleteFournisseur(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFournisseur(String nomFournisseur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getListUser(String nom) {
		String query = "SELECT * FROM user WHERE name like '%" + nom + "%' or prenom like '%" + nom + "%'";
		List<User> listUser = new ArrayList<User>();
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setString(1, nom);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPrenom(rs.getString("prenom"));
				user.setAdresse(rs.getString("adresse"));
				user.setPassword(rs.getString("password"));
				user.setTelephone(rs.getString("Telephone"));
				user.setSolde(rs.getInt("solde"));

				listUser.add(user);
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}

		return listUser;
	}

	@Override
	public boolean updateBonCommande(String numero_bon, boolean utiliser) {
		String query = "update boncommande set utiliser=? where numero_bon =?";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setBoolean(1, utiliser);
				statement.setString(2, numero_bon);
				statement.executeUpdate();
				statement.close();
				return true;
			} else {

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return false;
	}

	@Override
	public void updateSoldeFournisseur(long idfournisseur, double newSolde) {
		// TODO Auto-generated method stub
		String query = "update fournisseur set solde=? where idfournisseur =?";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setDouble(1, newSolde);
				statement.setLong(2, idfournisseur);
				statement.executeUpdate();
				statement.close();
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public Fournisseurs getFournisseur(long idboncommande) {
		String query = "SELECT  idfournisseur  FROM boncommande where idboncommande= ?";
		Fournisseurs fournisseur = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			statement.setLong(1, idboncommande);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				fournisseur = new Fournisseurs();
				fournisseur.setId(rs.getInt("idfournisseur"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fournisseur;
	}

	@Override
	public void updateSoldeClient(int id, double soldifference) {
		// TODO Auto-generated method stub
		// String query = "update fournisseur set solde=? where idfournisseur =?";
		String query = "update user set solde= solde -  ?   where id =?;";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setDouble(1, soldifference);
				statement.setLong(2, id);
				statement.executeUpdate();
				statement.close();
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public void updateSoldesClientT(long id, double soldifference) {
		// TODO Auto-generated method stub
		// String query = "update fournisseur set solde=? where idfournisseur =?";
		String query = "update user set solde=  ?   where id =?;";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setDouble(1, soldifference);
				statement.setLong(2, id);
				statement.executeUpdate();
				statement.close();
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public List<User> getListUser() {
		String query = "SELECT * FROM user";
		List<User> listUser = new ArrayList<User>();
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setString(1, nom);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPrenom(rs.getString("prenom"));
				user.setAdresse(rs.getString("adresse"));
				user.setTelephone(rs.getString("Telephone"));
				user.setSolde(rs.getInt("solde"));
				listUser.add(user);
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}

		return listUser;
	}

	@Override
	public List<User> getListUser(int number) {
		String query = "SELECT * FROM user where solde <" + number;
		List<User> listUser = new ArrayList<User>();
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setString(1, nom);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPrenom(rs.getString("prenom"));
				user.setAdresse(rs.getString("adresse"));
				user.setTelephone(rs.getString("Telephone"));
				user.setSolde(rs.getInt("solde"));
				listUser.add(user);
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}

		return listUser;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		// String query = "update fournisseur set solde=? where idfournisseur =?";
		String query = "update user set solde= solde +  ? ,name=?, prenom=?   where id =?;";
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			if (connexion != null) {
				statement = connexion.connect().prepareStatement(query);
				statement.setInt(1, user.getSolde());
				statement.setString(2, user.getName());
				statement.setString(3, user.getPrenom());
				statement.setLong(4, user.getId());
				statement.executeUpdate();
				statement.close();
			}
			return true;
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Commande> getListComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBonTCommande(List<BonCommande> listBon) {

		Connection con = connexion.connect();
		if (con != null) {
			try {
				Statement statement = null;

				con.setAutoCommit(false);
				statement = con.createStatement();
				for (BonCommande bon : listBon) {
					try {
						String query = "update boncommande set iduser='" + bon.getIdUser() + "',detenteurs='"
								+ bon.getNomUsers() + "' where numero_bon ='" + bon.getNumerobon() + "'";
						statement.executeUpdate(query);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
				con.commit();
				statement.close();
				con.close();
			} catch (SQLException e1) {

				e1.printStackTrace();

				try {
					if (con != null) {
						con.rollback();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	@Override
	public User getUser(long idUser) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM user WHERE id = " + idUser;
		User user = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPrenom(rs.getString("prenom"));
				user.setAdresse(rs.getString("adresse"));
				user.setPassword(rs.getString("password"));
				user.setTelephone(rs.getString("Telephone"));
				user.setSolde(rs.getInt("solde"));
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			// Exception handling
			System.out.println(ex);
		}

		return user;
	}

	@Override
	public boolean deleteCommande(Commande updatecommande) {
		// TODO Auto-generated method stub

		// restitution des donn�es de l'utilisateur
		long Idfournisseur = updatecommande.getIdfournisseur();
		long idM = getMaxId("commande");
		if (idM == updatecommande.getID()) {
			// restitution des donn�es du fournisseur
			
			double soldefournisseur=updatecommande.getSoldeFournisseur();
			int quantitie= Integer.valueOf(updatecommande.getQUANTITE()) ;
			double montantUnitaire=updatecommande.getMontunitaire();
			double NouveauSoldeFournisseur = soldefournisseur
					+ quantitie*montantUnitaire ;
			updateSoldeFournisseur(Idfournisseur, NouveauSoldeFournisseur);
			// restitution du solde Client
			double montantApayer=updatecommande.getMontantapayer();
			double montantPayer=updatecommande.getMONTANT();
			double soldeClient= updatecommande.getSoldeClient();
			double dif =montantApayer - montantPayer;
			double newsoldeClient = soldeClient + dif;
			updateSoldesClientT(updatecommande.getID_CLIENT(), newsoldeClient);
			// restitution du bonde commande
			updateBonCommande(updatecommande.getNUMERO_BON(), false);
			// suppression de la commande
			//    deleteCommande(updatecommande.getID());
		  return    updateCommandeSuppression(updatecommande.getID(),true);
			
		}

		return false;
	}

	
	
	
	
	
	
	@Override
	public List<Statistique> getStatisique(Date dateDepart,Date dateFin) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       String dateDepartD = dateFormat.format(dateDepart);
	       String dateFinD = dateFormat.format(dateFin);
		
		String query = "SELECT\r\n" + "commande.id as idcommande,boncommande.idboncommande,\r\n"
				+ "boncommande.quantite, commande.currentdate,\r\n" + "boncommande.numero_bon,\r\n" + "boncommande.idfournisseur,\r\n"
				+ "boncommande.utiliser,\r\n" + "boncommande.nom_fournisseur,\r\n" + "fournisseur.nom,\r\n"
				+ "fournisseur.solde,\r\n" + "user.name,user.prenom, \r\n" + "user.Telephone,\r\n" + "user.adresse,\r\n"
				+ "commande.id as idcommande,commande.supprimer,\r\n" + "commande.idbon,\r\n" + "commande.id_client,\r\n"
				+ "commande.caminion,\r\n" + "commande.id,\r\n" + "commande.quantite as quantitecommande,\r\n"
				+ "commande.qualite,commande.currentdate,\r\n" + "commande.montant,\r\n" + "commande.id_user,\r\n" + "commande.type,\r\n"
				+ "typeagregats.id as idtype,\r\n" + "typeagregats.montant as montunitaire,\r\n" + "commande.date,\r\n"
				+ "user.solde as soldeuser,\r\n" + "commande.soldefournisseur,\r\n" + "commande.montantapayer,\r\n"
				+ "commande.soldeclient,\r\n" + "commande.anciensoldeclient,\r\n" + "typeagregats.nom as nomagregat\r\n"
				+ " FROM commande ,boncommande,user,fournisseur,typeagregats\r\n" + "WHERE \r\n"
				+ "boncommande.idboncommande=commande.idbon\r\n" + "and\r\n" + "commande.id_client=user.id\r\n"
				+ "and\r\n" + "fournisseur.idfournisseur=boncommande.idfournisseur		\r\n" + "and\r\n"
				+ " DATE(commande.currentdate) BETWEEN	'"+dateDepartD+"' and '"+dateFinD+"' \r\n" + "and\r\n"
				+ " typeagregats.nom=	commande.type  order by commande.id desc ";
		 List<Statistique>listStatisque = new ArrayList<>();
		 System.out.println(query);
		Commande commande = null;
		try {
			if (connexion == null) {
				connexion = new MyConnexion();
			}
			statement = connexion.connect().prepareStatement(query);
			// statement.setInt(1, idUser);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
			
					
				
				
				
				commande = new Commande();
				commande.setID(rs.getInt("idcommande"));
				commande.setNUMERO_BON(rs.getString("NUMERO_BON"));
				commande.setCAMION(rs.getString("caminion"));
				commande.setDATE(rs.getString("date"));
				commande.setMONTANT(rs.getInt("MONTANT"));
				commande.setNOM_DU_CLIENT(rs.getString("name") + " " + rs.getString("prenom"));
				commande.setQUANTITE(rs.getString("quantitecommande"));
				commande.setTYPE(rs.getString("TYPE"));
				String val = rs.getString("TYPE");
				if (val.equals("all")) {
					commande.setTYPE("ajout d'un fournisseur");
				} else {
					if (val.equals("update")) {
						commande.setTYPE("modification du solde");
					} else {
						commande.setTYPE(val);
					}

				}
				// commande.setIDBON(rs.getInt("IDBON"));
				commande.setID_CLIENT(rs.getInt("ID_CLIENT"));
				commande.setIdfournisseur(rs.getInt("idfournisseur"));
				commande.setNom_fournisseur(rs.getString("nom_fournisseur"));
				commande.setSolde(rs.getString("solde"));
				commande.setNom(rs.getString("nom"));
				commande.setName(rs.getString("name"));
				commande.setAdresse(rs.getString("adresse"));
				commande.setQuantitecommande(rs.getInt("quantitecommande"));
				commande.setIdtype(rs.getInt("idtype"));
				commande.setNomagregat(rs.getString("nomagregat"));
				commande.setId_user(rs.getInt("id_user"));
				commande.setId_client(rs.getInt("id_client"));
				commande.setIdbon(rs.getInt("idbon"));
				commande.setIdcommande(rs.getInt("idcommande"));
				commande.setMontunitaire(rs.getInt("montunitaire"));
				commande.setUtiliser(rs.getBoolean("utiliser"));
				commande.setTelephone(rs.getString("Telephone"));
				commande.setSoldeuser(rs.getInt("soldeuser"));
				commande.setMontantapayer(rs.getInt("montantapayer"));
				commande.setSoldeFournisseur(rs.getInt("soldefournisseur"));
				commande.setSupprimer(rs.getBoolean("supprimer"));
				commande.setSoldeClient(rs.getInt("soldeclient"));
				commande.setAnciensoldeClient(rs.getInt("anciensoldeclient"));
				
				
			//	listStatisque
				Statistique stat=new Statistique();
				if (rs.getDate("currentdate") != null) {
					String dateFormatee = sdf.format(rs.getDate("currentdate"));
					commande.setDateActuel(dateFormatee);
					stat.setDate(dateFormatee);
				}
				stat.setNom(commande.getNOM_DU_CLIENT());
				stat.setMontantEncaisse(commande.getMONTANT());
				stat.setMontantVendu(commande.getMontantapayer());
				stat.setSoldeFournisseur(commande.getSoldeFournisseur());
				stat.setSoldeClient(commande.getSoldeClient());
			    if(!commande.getNUMERO_BON().contains("000547C")&&!commande.getNUMERO_BON().contains("000547U")&&!commande.isSupprimer()) {
				  listStatisque.add(stat);
				}
				//listCommande.add(commande);
			}
			rs.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println(ex);

		}

		return listStatisque;
	}

	
	
	
	
	
	
	
	
	
}
