package eu.telecombretagne.codecamp2011.reve;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class reveMain extends Activity {
	ImageButton btn_ok;
	public TextView txt_name,txt_marque;
	//TextView txt_explication;
	String[] listReve;
	String[] listExplication;	
	private Intent intent = new Intent("com.angel.Android.MUSIC");
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    startService(intent);
	    setAutoCompleteTextView();
		findAllView();
		setAllClickListener();
	}
	private void setAutoCompleteTextView(){
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txt_name);
		listReve = getResources().getStringArray(R.array.reve_array);
		listExplication = getResources().getStringArray(R.array.explication_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, listReve);
		textView.setAdapter(adapter);
	}
	private void findAllView() {
		btn_ok = (ImageButton) findViewById(R.id.btn_ok);
		txt_name = (TextView) findViewById(R.id.txt_name);
		txt_marque= (TextView) findViewById(R.id.txt_marque);
		//txt_explication= (TextView) findViewById(R.id.txt_explication);
		//txt_explication.setMovementMethod(ScrollingMovementMethod.getInstance());
		
	}
	private void setAllClickListener() {
		btn_ok.setOnClickListener(btn_ok_listener);	
	}
	private Button.OnClickListener btn_ok_listener = new Button.OnClickListener(){
		public void onClick(View v) {
			String msg="";
			boolean exist=false;
     		for(int i=0;i<listReve.length;i++){
				if(listReve[i].equals(txt_name.getText().toString())){
					msg=msg+"EXPLICATION:"+"\n"+listExplication[i];
					exist=true;
				}
			}
     		if(exist){
         		Dialog dialog = new AlertDialog.Builder(reveMain.this)
                .setTitle("EXPLICATION")
                .setMessage(msg)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int whichButton){
                         dialog.cancel();
                 }
               }).create();
               dialog.show();}
     		if(txt_name.getText().toString().equals(""))
     			Toast.makeText(getApplicationContext(),getResources().getText(R.string.msg_invalideinput), 
         				Toast.LENGTH_LONG).show();
     		else if(!exist)
				Toast.makeText(getApplicationContext(),getResources().getText(R.string.msg_existpas), 
	     				Toast.LENGTH_LONG).show();
		    //txt_explication.setText(msg);
	    }
	};	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { 
            stopService(intent); 
            reveMain.this.finish();
            return false; 
        } 
        return false; 
    }	
}