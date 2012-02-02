package telecom.event.evenement;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Accueil  extends Activity {
	private ListView maListViewPerso;
	/** Called when the activity is extends first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Gestion des boutons du menu
        ImageButton btnAjouter = (ImageButton)this.findViewById(R.id.Buttonadd);
        btnAjouter.setOnClickListener(
        new OnClickListener(){
        public void onClick(View viewParam) {
        	Intent intent = new Intent(viewParam.getContext(),Ajouter.class);
        	startActivity(intent);
        }
        });
        
        ImageButton btnFavoris = (ImageButton)this.findViewById(R.id.ButtonLove);
        btnFavoris.setOnClickListener(
        new OnClickListener(){
        public void onClick(View viewParam) {
        	Intent intent = new Intent(viewParam.getContext(),Favoris.class);
        	startActivity(intent);
        }
        });
        
        ImageButton btnParcourir = (ImageButton)this.findViewById(R.id.Buttonbrowse);
        btnParcourir.setOnClickListener(
        new OnClickListener(){
        public void onClick(View viewParam) {
        	Intent intent = new Intent(viewParam.getContext(),Parcourir.class);
        	startActivity(intent);
        }
        });
        
        ImageButton btnAccueil = (ImageButton)this.findViewById(R.id.Buttonhome);
        btnAccueil.setOnClickListener(
        new OnClickListener(){
        public void onClick(View viewParam) {
        	Intent intent = new Intent(viewParam.getContext(),Accueil.class);
        	startActivity(intent);
        }
        });
        
        ImageButton btnReglages = (ImageButton)this.findViewById(R.id.Buttonsettings);
        btnReglages.setOnClickListener(
        new OnClickListener(){
        public void onClick(View viewParam) {
        	Intent intent = new Intent(viewParam.getContext(),Reglages.class);
        	startActivity(intent);
        }
        });
        
        
      //Récupération de la listview créée dans le fichier parcourir.xml
        maListViewPerso = (ListView) findViewById(telecom.event.evenement.R.id.listView1);
        
      //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
 
        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
 
       /* //Création d'une HashMap pour insérer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        map.put("titre", " The Artist ");
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("description", "Cinéma multiplexe de Brest lundi 30 Janvier 20h00");
        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("img", String.valueOf(telecom.event.evenement.R.drawable.artist));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);*/
 
        //On refait la manip plusieurs fois avec des données différentes pour former les items de notre ListView
 
        map = new HashMap<String, String>();
        map.put("titre", "Festival ArtLive");
        map.put("description", "Jeudi 02 Fevrier au Centre Vie à 20h30 ");
        map.put("img", String.valueOf(telecom.event.evenement.R.drawable.artlive));
        listItem.add(map);
 
        map = new HashMap<String, String>();
        map.put("titre", "Before au I2");
        map.put("description", "Jeudi 02 Fevrier Cuisine du I2 à 19h30");
        map.put("img", String.valueOf(telecom.event.evenement.R.drawable.not_found));
        listItem.add(map);
 
        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, telecom.event.evenement.R.layout.affichageitem,
               new String[] {"img", "titre", "description"}, new int[] {telecom.event.evenement.R.id.img, telecom.event.evenement.R.id.titre, telecom.event.evenement.R.id.description});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);
 
        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on récupère la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
        		//on créer une boite de dialogue
        		AlertDialog.Builder adb = new AlertDialog.Builder(Accueil.this);
        		//on attribut un titre à notre boite de dialogue
        		adb.setTitle("Sélection Item");
        		//on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
        		adb.setMessage("Votre choix : "+map.get("titre"));
        		//on indique que l'on veut le bouton ok à notre boite de dialogue
        		adb.setPositiveButton("Ok", null);
        		//on affiche la boite de dialogue
        		adb.show();
        	}
         });
 
    }
}
