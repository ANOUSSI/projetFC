package model;

public class Commande {
	private boolean supprimer;
	private long ID;
	private String NUMERO_BON;
	private long IDBON;
	private long ID_CLIENT;
	private String CAMION;
	private String NOM_DU_CLIENT;
	private String QUANTITE;
	private double MONTANT;
	private String DATE;
	private int Iduser;
	private String TYPE;
	private  int quantite;
	private  String numero_bon;
	private  long idfournisseur;
	private  String nom_fournisseur;
	private  String solde;
	private String  nom;
	private String name;
	private String adresse;
	private String  idboncommande;
	private int quantitecommande;
	private String caminion;
	private int idtype;
	private String nomagregat;
	private int id_user;
	private int id_client;
	private int idbon;
	private int idcommande;
	private double montunitaire;
	private String type;
	private double montant;
	private boolean utiliser;
	private String Telephone;
	private double soldeuser;
	private double soldifference;
	private double soldeFournisseur;
	private double anciensoldeClient;
	private double soldeClient;
	private double montantapayer;
	private String dateActuel;
	public int getQuantite() {
		return quantite;
	}

	public String getDateActuel() {
		return dateActuel;
	}

	public void setDateActuel(String dateActuel) {
		this.dateActuel = dateActuel;
	}

	public boolean isSupprimer() {
		return supprimer;
	}

	public void setSupprimer(boolean supprimer) {
		this.supprimer = supprimer;
	}

	public double getSoldeClient() {
		return soldeClient;
	}

	public double getAnciensoldeClient() {
		return anciensoldeClient;
	}

	public void setAnciensoldeClient(double anciensoldeClient) {
		this.anciensoldeClient = anciensoldeClient;
	}

	public void setSoldeClient(double soldeClient) {
		this.soldeClient = soldeClient;
	}

	public double getSoldifference() {
		return soldifference;
	}

	public double getMontantapayer() {
		return montantapayer;
	}

	public void setMontantapayer(double montantapayer) {
		this.montantapayer = montantapayer;
	}

	public void setSoldifference(double soldifference) {
		this.soldifference = soldifference;
	}

	public double getSoldeFournisseur() {
		return soldeFournisseur;
	}

	public void setSoldeFournisseur(double soldeFournisseur) {
		this.soldeFournisseur = soldeFournisseur;
	}

	public double getSoldeuser() {
		return soldeuser;
	}

	public void setSoldeuser(double soldeuser) {
		this.soldeuser = soldeuser;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getNumero_bon() {
		return numero_bon;
	}

	public void setNumero_bon(String numero_bon) {
		this.numero_bon = numero_bon;
	}

	public long getIdfournisseur() {
		return idfournisseur;
	}

	public void setIdfournisseur(long idfournisseur) {
		this.idfournisseur = idfournisseur;
	}

	public String getNom_fournisseur() {
		return nom_fournisseur;
	}

	public void setNom_fournisseur(String nom_fournisseur) {
		this.nom_fournisseur = nom_fournisseur;
	}

	public String getSolde() {
		return solde;
	}

	public void setSolde(String solde) {
		this.solde = solde;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getIdboncommande() {
		return idboncommande;
	}

	public void setIdboncommande(String idboncommande) {
		this.idboncommande = idboncommande;
	}

	public int getQuantitecommande() {
		return quantitecommande;
	}

	public void setQuantitecommande(int quantitecommande) {
		this.quantitecommande = quantitecommande;
	}

	public String getCaminion() {
		return caminion;
	}

	public void setCaminion(String caminion) {
		this.caminion = caminion;
	}

	public int getIdtype() {
		return idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getNomagregat() {
		return nomagregat;
	}

	public void setNomagregat(String nomagregat) {
		this.nomagregat = nomagregat;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getIdbon() {
		return idbon;
	}

	public void setIdbon(int idbon) {
		this.idbon = idbon;
	}

	public int getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public double getMontunitaire() {
		return montunitaire;
	}

	public void setMontunitaire(double montunitaire) {
		this.montunitaire = montunitaire;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public boolean isUtiliser() {
		return utiliser;
	}

	public void setUtiliser(boolean utiliser) {
		this.utiliser = utiliser;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public Commande() {
		super();
	}
	
	public long getIDBON() {
		return IDBON;
	}

	public void setIDBON(long iDBON) {
		IDBON = iDBON;
	}

	public String getNOM_DU_CLIENT() {
		return NOM_DU_CLIENT;
	}

	public void setNOM_DU_CLIENT(String qUALITE) {
		NOM_DU_CLIENT = qUALITE;
	}

	public int getIduser() {
		return Iduser;
	}
	public void setIduser(int iduser) {
		Iduser = iduser;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getNUMERO_BON() {
		return NUMERO_BON;
	}
	public void setNUMERO_BON(String nUMERO_BON) {
		NUMERO_BON = nUMERO_BON;
	}
	
	public long getID_CLIENT() {
		return ID_CLIENT;
	}

	public void setID_CLIENT(long iD_CLIENT) {
		ID_CLIENT = iD_CLIENT;
	}

	public String getCAMION() {
		return CAMION;
	}
	public void setCAMION(String cAMION) {
		CAMION = cAMION;
	}
	public String getQUANTITE() {
		return QUANTITE;
	}
	public void setQUANTITE(String qUANTITE) {
		QUANTITE = qUANTITE;
	}
	public double getMONTANT() {
		return MONTANT;
	}
	public void setMONTANT(double mONTANT) {
		MONTANT = mONTANT;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	@Override
	public String toString() {
		return "Commande [ID=" + ID + ", NUMERO_BON=" + NUMERO_BON + ", NOM_DU_CLIENT=" + ID_CLIENT + ", CAMION="
				+ CAMION + ", QUANTITE=" + QUANTITE + ", MONTANT=" + MONTANT + ", DATE=" + DATE + "]";
	}

}
