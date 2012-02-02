package android.shopping;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.shopping.Shoppingdatabase.Row;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EasyShoppingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    int Id;
    int Idd;
    public LinearLayout tolinearLayout(Row row){
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_produit);
    	LinearLayout layout =new LinearLayout(this);
    	layout.setOrientation(LinearLayout.HORIZONTAL);
    	layout.setBackgroundResource(R.drawable.produitshape);
    	layout.setMinimumHeight(80);
    	layout.setVerticalGravity(Gravity.CENTER_VERTICAL);
    	android.view.Display display = ((android.view.WindowManager)getSystemService(this.WINDOW_SERVICE)).getDefaultDisplay();
    	
		TextView valueTV = new TextView(this);
		valueTV.setText(row.name);
		valueTV.setId(row.Id);
		valueTV.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,1));
		valueTV.setPadding(15,0,0,0);
		valueTV.setTextColor(Color.parseColor("BLACK"));
		valueTV.setClickable(true);
		valueTV.setTextSize(20);
		Id = row.Id;
		valueTV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(),DetailActivity.class);
				intent.putExtra("Id", v.getId());//this.getId());
            	startActivityForResult(intent,0);
			}
		});
		
		ImageView valueTV1 = new ImageView(this);
		if (row.status.equals("choose")){
			valueTV1.setImageResource(R.drawable.choose);
		}
		valueTV1.setId(row.Id+50);
		valueTV1.setLayoutParams(new LinearLayout.LayoutParams(120,50));
		valueTV1.setPadding(10,0,0,0);	
		layout.addView(valueTV);
		layout.addView(valueTV1);
		linearLayout.addView(layout);
		return linearLayout;
	}
    
    
    @Override
    public void onResume(){
    	super.onResume();
    	setContentView(R.layout.main);
    	
    	final Button main_button_add = (Button) findViewById(R.id.main_buttonadd);
        main_button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent addintent = new Intent(v.getContext(),AddActivity.class);
            	startActivityForResult(addintent,0);
            }
        });
    	
    	Shoppingdatabase sd = new Shoppingdatabase(this);
        ArrayList<Row> produit = new ArrayList<Row>();
        produit = sd.fetchAllRows();
        sd.close();
        ArrayList<LinearLayout> linearLayouts = new ArrayList<LinearLayout>();	
        if (produit.size() !=0) {
           	for (int i=0;i<produit.size();i++)
           		if (produit.get(i).statuson.equals("notyet"))
        		linearLayouts.add(tolinearLayout(produit.get(i)));
       
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menushop, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.menu_help:
        	Shoppingdatabase sd = new Shoppingdatabase(this);
            ArrayList<Row> produit = new ArrayList<Row>();
            produit = sd.fetchAllRows();
            if ((produit.size() !=0) && (produit.size()>sd.getAlready())) {
               	for (int i=0;i<produit.size();i++){
               		Idd = sd.getnameRow(produit.get(i).name);
               		if (produit.get(i).status.equals("choose")){
               			sd.updateRow(produit.get(i).Id, produit.get(i).name, produit.get(i).adress, produit.get(i).maxi, produit.get(i).prix, "unchoose", "already");
               		}
                	else sd.deleteRow(produit.get(i).Id);
               	}
                Intent intent =new Intent(EasyShoppingActivity.this, ProductActivity.class);
            	startActivity(intent);
            }
            sd.close();
           
            return true;
        case R.id.menu_barcode:
        	// TO DO
        	Intent intentLT =new Intent(EasyShoppingActivity.this, LastTimeActivity.class);
        	startActivity(intentLT);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}