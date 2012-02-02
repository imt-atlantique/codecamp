package tb.wilfriedyoro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rechercherActivity extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        Button ecolei = (Button) findViewById(R.id.button1);
	        Button ecolec = (Button) findViewById(R.id.button2);
	        Button universités = (Button) findViewById(R.id.button3);
	         ecolei.setOnClickListener(new View.OnClickListener(){
	        	 public void onClick(View v){
	        		 Intent myIntent = new Intent (v.getContext(),ecolesListeActivity.class);
	        		 startActivityForResult(myIntent, 0);
	        	 }
	         });
  }
}