package android.shopping;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.shopping.Shoppingdatabase.Row;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LastTimeActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lasttime);
    }
    
    int Id;
    public LinearLayout tolinearLayout(Row row){
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lasttime_produit);
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
		layout.addView(valueTV);
		linearLayout.addView(layout);
		return linearLayout;
	}
    
    
    @Override
    public void onResume(){
    	super.onResume();
    	setContentView(R.layout.lasttime);
    	
    	Shoppingdatabase sd = new Shoppingdatabase(this);
        ArrayList<Row> produit = new ArrayList<Row>();
        produit = sd.fetchAllRows();
        sd.close();
        ArrayList<LinearLayout> linearLayouts = new ArrayList<LinearLayout>();	
        if (produit.size() !=0) {
           	for (int i=0;i<produit.size();i++)
           		if (produit.get(i).statuson.equals("already"))
        		linearLayouts.add(tolinearLayout(produit.get(i)));
       
        }
    }
}
