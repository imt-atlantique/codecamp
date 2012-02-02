package telecom.event.evenement;

import java.util.ArrayList;

public class Evenement {

	// attributs d'un évenement 
		private String name;
		private String Lieu;
		private String date_debut;
		private String date_fin;
		private String Description;
		private String Heure;
		private ArrayList<Participant> participants;
		private Participant createur;
		// private ArrayList<Image> images;
		
		public Evenement(String nom,String Lieu,String date_debut,String date_fin, String Description,Participant createur)
		{
			this.name = nom;
			this.Lieu = Lieu;
			this.Description = Description;
			this.createur = createur;
			this.date_debut = date_debut;
			this.date_fin = date_fin;
			
			
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLieu() {
			return Lieu;
		}

		public void setLieu(String lieu) {
			Lieu = lieu;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}

		public ArrayList<Participant> getParticipants() {
			return participants;
		}

		public void setParticipants(ArrayList<Participant> participants) {
			this.participants = participants;
		}

		public Participant getCreateur() {
			return createur;
		}

		public void setCreateur(Participant createur) {
			this.createur = createur;
		}

		
		public void setDate_debut(String date_debut) {
			this.date_debut = date_debut;
		}

		public void setDate_fin(String date_fin) {
			this.date_fin = date_fin;
		}

		public String getDate_debut() {
			return date_debut;
		}

		public String getDate_fin() {
			return date_fin;
		}

		public String getHeure() {
			return Heure;
		}

		public void setHeure(String heure) {
			Heure = heure;
		}
}
