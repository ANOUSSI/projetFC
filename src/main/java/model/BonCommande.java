package model;

public class BonCommande {
	private long idboncommande;
	private String numerobon;
	private String description;
	private int quantite;
	private boolean utilise;
	private long idfournisseur;
	private String nomFournisseur;
	private double soldeFournisseur;
	private String dates;
	private int idUser;
	private String nomUsers;
	private int system;
	
	public int getSystem() {
		return system;
	}
	public void setSystem(int system) {
		this.system = system;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNomUsers() {
		return nomUsers;
	}
	public void setNomUsers(String nomUsers) {
		this.nomUsers = nomUsers;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public double getSoldeFournisseur() {
		return soldeFournisseur;
	}
	public void setSoldeFournisseur(double soldeFournisseur) {
		this.soldeFournisseur = soldeFournisseur;
	}
	public String getNumerobon() {
		return numerobon;
	}
	public void setNumerobon(String numerobon) {
		this.numerobon = numerobon;
	}
	public long getIdfournisseur() {
		return idfournisseur;
	}
	public void setIdfournisseur(long idfournisseur) {
		this.idfournisseur = idfournisseur;
	}
	public String getNomFournisseur() {
		return nomFournisseur;
	}
	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
	}
	public long getIdboncommande() {
		return idboncommande;
	}
	public void setIdboncommande(long idboncommande) {
		this.idboncommande = idboncommande;
	}
	
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean isUtilise() {
		return utilise;
	}
	public void setUtilise(boolean utilise) {
		this.utilise = utilise;
	}
	public BonCommande() {
		super();
	}
	

}
