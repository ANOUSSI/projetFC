package model;

public class Statistique {
	private  String date;
	private double montantVendu;
	private double montantEncaisse;
	private double soldeFournisseur;
	private double soldeClient;
	private String nom;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDate() {
		return date;
	}
	public double getSoldeClient() {
		return soldeClient;
	}
	public void setSoldeClient(double soldeClient) {
		this.soldeClient = soldeClient;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getMontantVendu() {
		return montantVendu;
	}
	public void setMontantVendu(double montantVendu) {
		this.montantVendu = montantVendu;
	}
	public double getMontantEncaisse() {
		return montantEncaisse;
	}
	public void setMontantEncaisse(double montantEncaisse) {
		this.montantEncaisse = montantEncaisse;
	}
	
	public double getSoldeFournisseur() {
		return soldeFournisseur;
	}
	public void setSoldeFournisseur(double soldeFournisseur) {
		this.soldeFournisseur = soldeFournisseur;
	}
	public Statistique() {
		super();
	}
	

}
