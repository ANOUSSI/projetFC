package dao;

import java.util.Date;
import java.util.List;

import model.BonCommande;
import model.Commande;
import model.Fournisseurs;
import model.Statistique;
import model.TypeAgregats;
import model.User;
import vue.Fournisseur1;

public interface InterService {
// COMMANDE
	public List<Commande> getListComment();

	public List<Commande> getListComment(int idUser);

	public List<Commande> getListComment(String date);

	public Commande getCommende(int id);

	public Commande getCommende(String Numero_Bordereau);

	public Commande saveCommande(Commande commande);

	public boolean deleteCommande(long id);

	public boolean deleteCommande(String Numero_Bordereau);

	// UTILISATEUR
	public User get(String login, String password);

	public User saveUser(User user);

	public boolean updateUser(User user);

	public List<User> getListUser(String nom);

	public List<User> getListUser();

	public List<User> getListUser(int number);
	
	public User getUser(long idUser);
	// BON DE COMMAND
	public List<BonCommande> getListBonCommande();

	public List<BonCommande> getListBonCommande(int idUser);

	public List<BonCommande> getListBonCommande(String date);

	public List<BonCommande> getListBonCommande(boolean utilise);

	public List<Commande> getListCommentText(String text, boolean parnon);

	public BonCommande getBonCommande(int id);

	public BonCommande getBonCommande(String numeroBon);

	public BonCommande saveBonCommande(BonCommande boncommande);

	public boolean deleteBonCommande(int id);
	public boolean updateBonCommande(String numero_bon,boolean utiliser);
	
	public boolean deleteBonCommande(String numeroBon);

	public BonCommande getFirstBonCommande(boolean utilise);

	// Type d'agregats
	public List<TypeAgregats> getListTypeAgregats();

	public List<TypeAgregats> getListTypeAgregats(int idAgregat);

	public List<TypeAgregats> getListTypeAgregats(String date);

	public TypeAgregats getTypeAgregats(int id);

	public TypeAgregats getTypeAgregats(String nomAgrega);

	public TypeAgregats saveTypeAgregats(TypeAgregats agregat);

	public boolean deleteTypeAgregats(int id);

	public boolean deleteTypeAgregats(String nomAgrega);

	// Fournisseur
	public List<Fournisseurs> getListFournisseur();

	public List<Fournisseurs> getListFournisseur(int idUser);

	public List<Fournisseurs> getListFournisseur(String date);

	public Fournisseurs getFournisseur(int id);

	public Fournisseurs getFournisseur(String nomFournisseur);

	public Fournisseurs getFournisseur(long idboncommande);

	public void updateSoldeFournisseur(long idfournisseur, double newSolde);

	public boolean saveFournisseur(Fournisseurs fournisseur1);

	public boolean deleteFournisseur(int id);

	public boolean deleteFournisseur(String nomFournisseur);

	public void updateSoldeClient(int id, double soldifference);
	
	public void updateSoldesClientT(long id, double soldifference) ;
	
	public void saveListbon(List<BonCommande> listBon);
	
	public void updateBonTCommande(List<BonCommande> listBon) ;
	public BonCommande getBonCommandewithId(String numero_Bon,boolean utilise);

	public boolean deleteCommande(Commande updatecommande);
	public int getMaxId(String table);
	public boolean updateCommandeSuppression(long id, boolean bol);
	public List<Statistique> getStatisique(Date dateDepart,Date dateFin);
}
