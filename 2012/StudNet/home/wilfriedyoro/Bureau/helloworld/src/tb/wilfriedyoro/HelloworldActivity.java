package tb.wilfriedyoro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloworldActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button interiorAdvizer = (Button) findViewById(R.id.button1);
        Button driverAssistance = (Button) findViewById(R.id.button2);
        Button studentNetwork = (Button) findViewById(R.id.button3);
         studentNetwork.setOnClickListener(new View.OnClickListener(){
        	 public void onClick(View v){
        		 Intent myIntent = new Intent (v.getContext(),studentNetwork.class);
        		 startActivityForResult(myIntent, 0);
        	 }
         });
    }
}