/*authors JIANG Xiwen/ Lin Zuolu
 * intersemetre 2011 Codecamp2011
 * */

package org.traducteuranimaux.fr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AnimalChoix extends Activity {
	 /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	String animal="";
            	switch(position){
            		case 0: animal+="un oiseau";break;
            		case 1: animal+="un chat";break;
            		case 2: animal+="un chien";break;
            		case 3: animal+="un cochon";break;
            		case 4: animal+="un canard";break;
            		case 5: animal+="une poule";break;
            		case 6: animal+="un boeuf";break;
            		case 7: animal+="un chimpanzé";break;
            		case 8 : animal+="un cheval";break;
            		case 9 : animal+="un tigre";break;
            		case 10 : animal+="un serpent";break;
            		case 11 : animal+="un mouton";break;
     
            		default : animal+="un animal inconnu";break;	
            	}
                Toast.makeText(AnimalChoix.this, "Vous avez choisi "+animal, Toast.LENGTH_SHORT).show();
                
                //transmit the information of choice to the seconde activity
                Intent intent = new Intent();
                intent.setClass(AnimalChoix.this,Traducteur.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                startActivity(intent);           
            }
        });
    
	}
	
}
    
