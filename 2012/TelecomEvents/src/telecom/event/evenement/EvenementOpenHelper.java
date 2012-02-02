package telecom.event.evenement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class EvenementOpenHelper extends SQLiteOpenHelper {
	
	public static final String BASE_NOM = "data.db";
	private static final String TABLE_EVENEMENT = "evenement";
	private static final String COLONNE_ID_EVENEMENT = "_id";
	private static final String COLONNE_NOM_EVENEMENT = "nom";
	private static final String COLONNE_LIEU = "Lieu";
	private static final String COLONNE_DATE_DEBUT = "Date_debut";
	private static final String COLONNE_DATE_FIN = "Date_fin";
	private static final String COLONNE_HEURE = "heure";
	private static final String COLONNE_DESCRIPTION = "description";
	private static final String COLONNE_ID_CREATEUR = "id_createur";
	private static final String	YOUR_PACKAGE = "telecom.event.evenement";
	
	private static final String TABLE_PARTICIPANT = "participant";
	private static final String COLONNE_ID_PARTICIPANT = "_id";
	private static final String COLONNE_NOM_PARTICIPANT = "nom";
	private static final String COLONNE_MAIL = "mail";
	
	private static final String REQUETE_CREATION_TABLE_EVENEMENT = "create table "
			+ TABLE_EVENEMENT + " (" + COLONNE_ID_EVENEMENT
			+ " integer primary key autoincrement, " + COLONNE_NOM_EVENEMENT
			+ " text not null, " + COLONNE_LIEU + " text not null, "
			+ COLONNE_DATE_DEBUT + " text not null, " + COLONNE_DATE_FIN
			+ " text not null, " + COLONNE_HEURE + " text not null, " + COLONNE_DESCRIPTION 
			+ " text , " + COLONNE_ID_CREATEUR + " INTEGER ,FOREIGN KEY ("+COLONNE_ID_CREATEUR
			+") REFERENCES "+TABLE_PARTICIPANT+"("+COLONNE_ID_PARTICIPANT+"));";
	
	private static final String REQUETE_CREATION_TABLE_PARTICIPANT = "create table  "
			+ TABLE_PARTICIPANT + " (" + COLONNE_ID_PARTICIPANT
			+ " integer primary key autoincrement, " + COLONNE_NOM_PARTICIPANT + " text not null, " + COLONNE_MAIL + " text not null);";
			
	private static final String REQUETE_CREATION_BD = REQUETE_CREATION_TABLE_PARTICIPANT +"\n"+ REQUETE_CREATION_TABLE_EVENEMENT;
	
	private SQLiteDatabase BDEvenement;
	
	private static String BASE_CHEMIN = "/data/data/"+YOUR_PACKAGE+"/databases/";
	 
    private final Context myContext;

	public EvenementOpenHelper(Context context) {
		super(context, BASE_NOM, null, 1);
        this.myContext = context;
	}
	
	  public void createEvenementDB() throws IOException{
		  
		     boolean dbExist = checkDataBase();
		 
		     if(dbExist){
		      //ne rien faire 
		     }else{
		 
		      //crée une Base de donnée dans le repertoire par defaut
		         this.getReadableDatabase();
		         try {
		       copyDataBase();
		      } catch (IOException e) {
		          throw new Error("Error copying database");
		         }
		     }
		 
	}
	  
	  private boolean checkDataBase(){
		  
		     SQLiteDatabase checkDB = null;
		     
		     try{
		      String myPath = BASE_CHEMIN + BASE_NOM;
		      checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		 
		     }catch(SQLiteException e){
		    	 
		      //La Base n'existe pas encore.
		     }
		     if(checkDB != null){
		 
		      checkDB.close();
		 
		     }
		     return checkDB != null ? true : false;
		    }
	  
	  private void copyDataBase() throws IOException{
		  
		     //Open your local db as the input stream
		     InputStream myInput = myContext.getAssets().open(BASE_NOM);
		 
		     // Path to the just created empty db
		     String outFileName = BASE_CHEMIN + BASE_NOM;
		 
		     //Open the empty db as the output stream
		     OutputStream myOutput = new FileOutputStream(outFileName);
		 
		     //transfer bytes from the inputfile to the outputfile
		     byte[] buffer = new byte[1024];
		     int length;
		     while ((length = myInput.read(buffer))>0){
		      myOutput.write(buffer, 0, length);
		     }
		     //Close the streams
		     myOutput.flush();
		     myOutput.close();
		     myInput.close();
		 
		    }
	  
	  public void openDataBase() throws SQLException{
		  
		     //Open the database
		        String myPath = BASE_CHEMIN + BASE_NOM;
		     BDEvenement = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		 
		    }
		    @Override
		 public synchronized void close() {	 
		         if(BDEvenement != null)
		        	 BDEvenement.close();
		 
		         super.close();
		 
		 }
	@Override
	public void onCreate(SQLiteDatabase db) {
	db.execSQL(REQUETE_CREATION_BD);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

}
