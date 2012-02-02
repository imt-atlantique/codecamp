package android.shopping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.shopping.Shoppingdatabase.Row;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {
	EditText adress;
	String add_nom;
	String add_max;
	String add_prix;
	String rememberadd;
	int Idd;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        adress = (EditText) findViewById(R.id.add_editimage);
        
	}
	
	public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     // TODO Auto-generated method stub
     super.onActivityResult(requestCode, resultCode, data);

     if (resultCode == RESULT_OK){
      Uri targetUri = data.getData();
      adress.setText(getRealPathFromURI(targetUri));
      Log.d("address",targetUri.toString());
     }
    }

	
	@Override
    public void onResume() {
        super.onResume();
        //setContentView(R.layout.add);
        final EditText nom = (EditText) findViewById(R.id.add_editnom);
        //final EditText adress = (EditText) findViewById(R.id.add_editimage);
        final EditText max = (EditText) findViewById(R.id.add_editmax);
        final EditText prix = (EditText) findViewById(R.id.add_editprix);
                
        final Button add_button_image = (Button) findViewById(R.id.add_buttonimage);
          add_button_image.setOnClickListener(new View.OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {
      			// TODO Auto-generated method stub
      			Intent intent = new Intent(Intent.ACTION_PICK,
      				     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      			startActivityForResult(intent, 0);
      			
      		}	
      	});
        final Button add_button_ok = (Button) findViewById(R.id.add_buttonsubmit);
    	add_button_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Shoppingdatabase sd = new Shoppingdatabase(v.getContext());
            	sd.createRow(nom.getText().toString(), adress.getText().toString(), max.getText().toString(), prix.getText().toString(), "unchoose", "notyet");
            	sd.close();
            	Log.d("address",adress.getText().toString());
            	finish();
            }
        });
    	
    	final Button add_button_cancel = (Button) findViewById(R.id.add_buttoncancel);
    	add_button_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               finish();
            }
        });
	}
	
}
