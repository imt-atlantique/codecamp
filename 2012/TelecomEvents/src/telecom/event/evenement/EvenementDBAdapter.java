package telecom.event.evenement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class EvenementDBAdapter {
	public static final int BASE_VERSION = 1;
	public static final String BASE_NOM = "data.db";
	public static final String TABLE_EVENEMENT = "evenement";
	public static final String COLONNE_ID_EVENEMENT = "_id";
	public static final int COLONNE_ID_EVENEMENT_ID = 5;
	public static final String COLONNE_NOM_EVENEMENT = "nom";
	public static final int COLONNE_NOM_EVENEMENT_ID = 1;
	public static final String COLONNE_LIEU = "Lieu";
	public static final int COLONNE_LIEU_ID = 2;
	public static final String COLONNE_DATE_DEBUT = "Date_debut";
	public static final int COLONNE_DATE_DEBUT_ID = 3;
	public static final String COLONNE_DATE_FIN = "Date_fin";
	public static final int COLONNE_DATE_FIN_ID = 4;
	public static final String COLONNE_HEURE = "heure";
	public static final int COLONNE_HEURE_ID = 0;
	public static final String COLONNE_DESCRIPTION = "description";
	public static final int COLONNE_DESCRIPTION_ID = 6;
	public static final String COLONNE_ID_CREATEUR = "id_createur";
	public static final int COLONNE_ID_CREATEUR_ID = 7;
	
	public static final String TABLE_PARTICIPANT = "participant";
	public static final String COLONNE_ID_PARTICIPANT = "_id";
	public static final int COLONNE_ID_PARTICIPANT_ID = 0;
	public static final String COLONNE_NOM_PARTICIPANT = "nom";
	public static final int COLONNE_NOM_PARTICIPANT_ID = 1;
	public static final String COLONNE_MAIL = "mail";
	public static final int COLONNE_MAIL_ID = 2;
	
	private static final String REQUETE_CREATION_TABLE_EVENEMENT = "create table "
			+ TABLE_EVENEMENT + " (" + COLONNE_ID_EVENEMENT
			+ " integer primary key autoincrement, " + COLONNE_NOM_EVENEMENT
			+ " text not null, " + COLONNE_LIEU + " text not null, "
			+ COLONNE_DATE_DEBUT + " text not null, " + COLONNE_DATE_FIN
			+ " text not null, " + COLONNE_HEURE + " text not null, " + COLONNE_DESCRIPTION 
			+ " text , " + COLONNE_ID_CREATEUR + " INTEGER ,FOREIGN KEY ("+COLONNE_ID_CREATEUR
			+") REFERENCES "+TABLE_PARTICIPANT+"("+COLONNE_ID_PARTICIPANT+"));";
	
	private static final String REQUETE_CREATION_TABLE_PARTICIPANT = "create table   "
			+ TABLE_PARTICIPANT + " ( " + COLONNE_ID_PARTICIPANT
			+ " integer primary key autoincrement, " + COLONNE_NOM_PARTICIPANT + " text not null, " + COLONNE_MAIL + " text not null);";
			
	private static final String REQUETE_CREATION_BD = REQUETE_CREATION_TABLE_PARTICIPANT +" \n "+ REQUETE_CREATION_TABLE_EVENEMENT;
	
	private SQLiteDatabase BDEvenement;
	
	private EvenementOpenHelper baseHelper;
	
	private static String BASE_CHEMIN = "/data/data/YOUR_PACKAGE/databases/";
	 


	//constructeur
	
	public EvenementDBAdapter(Context ctx)
	{
		baseHelper = new EvenementOpenHelper(ctx);
		
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BD);
		}
		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
	/**
	* Ouvre la base de données en écriture.
	*/
	public SQLiteDatabase open() {
	BDEvenement = baseHelper.getWritableDatabase();
	return BDEvenement;
	}
	/**
	* Ferme la base de données.
	*/
	public void close() {
		BDEvenement.close();
	}
	
	public SQLiteDatabase getBaseDonnees() {
		return BDEvenement;
		}
	
	public long insererEvenement(Evenement E)
	{

				ContentValues values = new ContentValues();
				
				values.put(COLONNE_NOM_EVENEMENT, E.getName());
				values.put(COLONNE_LIEU, E.getLieu());
				values.put(COLONNE_DATE_DEBUT, E.getDate_debut());
				values.put(COLONNE_DATE_FIN, E.getDate_fin());
				values.put(COLONNE_HEURE, E.getHeure());
				values.put(COLONNE_DESCRIPTION, E.getDescription());
				values.put(COLONNE_ID_CREATEUR, this.getID_PART(E.getCreateur()));
				
				//on insère l'objet dans la BDD via le ContentValues
				return BDEvenement.insert(TABLE_EVENEMENT, null, values);
	}
	
	public int getID_PART(Participant p) {
		Cursor c = BDEvenement.query(TABLE_EVENEMENT, new String[] {
		COLONNE_ID_PARTICIPANT}, "WHERE "+COLONNE_MAIL+" = "+p.getMail(), null, null,null, null);
		return c.getInt(COLONNE_ID_PARTICIPANT_ID) ;
		}
	
	public Cursor getAllEvenement()
	{
			    Cursor c = BDEvenement.query(TABLE_EVENEMENT, new String[] {COLONNE_NOM_EVENEMENT,COLONNE_DATE_DEBUT
				}, null, null, null,null, null);
				return c ;
	}
			
	
	
}
