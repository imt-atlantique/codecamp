package tb.wilfriedyoro;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ecolesListeActivity extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);

		  setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ecoles));

		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);

		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		     // Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
		      //    Toast.LENGTH_SHORT).setText("http://www"+((TextView) view).getText()+".eu");
		     
		      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
			          Toast.LENGTH_LONG).show();
		   
		      Intent i=new Intent(Intent.ACTION_VIEW);
			    Uri u= Uri.parse("http://www."+((TextView) view).getText()+".eu");
			    i.setData(u);
			    startActivity(i);
			
		    }
		  });
		///Intent i=new Intent(Intent.ACTION_VIEW);
	   // Uri u= Uri.parse("http://www"+((TextView) view).getText()+".eu");
	
	}

	static final String[] ecoles = new String[] {
    "telecom-Bretagne", "Télécom-ParisSud", "Télécom-Bretagne_rennes"

	};
}
