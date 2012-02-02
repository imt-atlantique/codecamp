package android.shopping;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.shopping.Shoppingdatabase.Row;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity{
	int Id;
	String p_address;
	String rememberdetail;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        
        Bundle bundle = getIntent().getExtras();
        //int Id;
        Id= bundle.getInt("Id");
        Shoppingdatabase sd = new Shoppingdatabase(this);
        Row produit = sd.new Row();
        produit = sd.fetchRow(Id);
        Log.d("Id", ((Integer)Id).toString());
        sd.close();
        
        
        final TextView nom = (TextView) findViewById(R.id.detail_textnomproduit);
        nom.setText(produit.name);
        final TextView maxi = (TextView) findViewById(R.id.detail_textmaxproduit);
        maxi.setText(produit.maxi);
        final TextView prix = (TextView) findViewById(R.id.detail_textprixproduit);
        prix.setText(produit.prix);
        p_address = produit.adress;
        rememberdetail = produit.statuson;        
        
        File imageFile = new File(produit.adress);
        Log.d("address",produit.adress);
        if(imageFile.exists()){

        	Bitmap myBitmap = BitmapFactory.decodeFile(produit.adress);
        	ImageView myImage = (ImageView) findViewById(R.id.detail_imageproduit);
        	myImage.setImageBitmap(myBitmap);
        } 
        
        final Button detail_button_cancel = (Button) findViewById(R.id.detail_buttoncancel);
        detail_button_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Shoppingdatabase sd = new Shoppingdatabase(v.getContext());
            	sd.deleteRow(Id);
            	sd.close();
                finish();
            }
        });
        
        final Button detail_button_ok = (Button) findViewById(R.id.detail_buttonsubmit);
        detail_button_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Shoppingdatabase sd = new Shoppingdatabase(v.getContext());
            	sd.updateRow(Id, nom.getText().toString(), p_address, maxi.getText().toString(), prix.getText().toString(), "choose",rememberdetail);
            	sd.close();
            	finish();
            }
        });
	}
}
