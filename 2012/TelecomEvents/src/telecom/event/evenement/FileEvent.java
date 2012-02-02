package telecom.event.evenement;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class FileEvent extends Activity {
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        
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
    }

    
	
}