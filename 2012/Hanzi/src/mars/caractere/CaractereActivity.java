package mars.caractere;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CaractereActivity extends Activity {
    /** Called when the activity is first created. */
	
	private TextView Text_01 = null;
	private Button Btn_01 = null;
	private Button Btn_02 = null;
	private Button Btn_03 = null;
	
	private Button Btn_04 = null;
	private Button Btn_05 = null;
	
	private String SDPath = null;
	
	private String[] words_00 = null;
	private String[] words_01 = null;
	private String[] words_02 = null;
	
	private int now_index=0;

	private String[] words_now;
	private String chinese_char;
	private String meaning;
	
	private boolean ifReset=false;
	
	//hard mode
	private int mode = 0;
	
	public static String getField(String str,int n){
		String[] s=str.split(",,");
		if(s.length<=n)
			return null;
		else
			return s[n];
    }
	public static String getLearn(String str){
		String s=str.replaceAll(",,0", ",,1");
		return s;
	}
    @Override
    
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //1,if has temporary files?if no,make it and store;if yes,read it
        
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        	SDPath=Environment.getExternalStorageDirectory()+"/";
        	File AppDir=new File(SDPath + getString(R.string.dir_name));
        	
    		File file00 =new File(AppDir + "/" + getString(R.string.File_00));
    		File file01 =new File(AppDir + "/" + getString(R.string.File_01));
    		File file02 =new File(AppDir + "/" + getString(R.string.File_02));
    		File file_mode =new File(AppDir + "/" + getString(R.string.File_mode));
    		
//    		file00.delete();
//    		file01.delete();
//    		file02.delete();
//    		file_mode.delete();
//    		AppDir.delete();
    		//if has the director
        	if(AppDir.exists()==false){
        		//if no make the director,write all words in,and then read them all
        		AppDir.mkdir();
        		try{
        			FileOutputStream fos00 = new FileOutputStream(file00);
        			FileOutputStream fos01 = new FileOutputStream(file01);
        			FileOutputStream fos02 = new FileOutputStream(file02);
        			FileOutputStream fos = new FileOutputStream(file_mode);
        			fos.write("0".getBytes());
        			words_00= getResources().getStringArray(R.array.Words_00);
        			words_01= getResources().getStringArray(R.array.Words_01);
        			words_02= getResources().getStringArray(R.array.Words_02);
        			for(int i=0;i<words_00.length;i++){
        				fos00.write(words_00[i].getBytes());
        				fos00.write("\n".getBytes());
        			}
        			for(int i=0;i<words_01.length;i++){
        				fos01.write(words_01[i].getBytes());
        				fos01.write("\n".getBytes());
        			}
        			for(int i=0;i<words_02.length;i++){
        				fos02.write(words_02[i].getBytes());
        				fos02.write("\n".getBytes());
        			}
        			fos00.close();
        			fos01.close();
        			fos02.close();
        			fos.close();
        		}catch(Exception e){
        		}
        	}
        	else{
        		//if exist,read all words
        		try {
                    FileInputStream fis00 = new FileInputStream(file00);
                    FileInputStream fis01 = new FileInputStream(file01);
                    FileInputStream fis02 = new FileInputStream(file02);
                    FileInputStream fis = new FileInputStream(file_mode);
                    byte[] b00 = new byte[fis00.available()];
                    byte[] b01 = new byte[fis01.available()];
                    byte[] b02 = new byte[fis02.available()];
                    byte[] b = new byte[fis.available()];
                    fis00.read(b00);
                    fis01.read(b01);
                    fis02.read(b02);
                    fis.read(b);

                    words_00=(new String(b00)).split("\n");
                    words_01=(new String(b01)).split("\n");
                    words_02=(new String(b02)).split("\n");
                    mode=Integer.parseInt(new String(b));
                } catch (Exception e) {
                }
        	}

        } else {
            Toast.makeText(CaractereActivity.this,"SDcard´íÎó£¡", Toast.LENGTH_SHORT).show();
        }
        

        //2,show welcome information
        Toast.makeText(CaractereActivity.this,getString(R.string.Welcome),Toast.LENGTH_SHORT).show();
        //3,find all things
        Text_01=(TextView)findViewById(R.id.textView1);
        Btn_01=(Button)findViewById(R.id.button1);
        Btn_02=(Button)findViewById(R.id.button2);
        Btn_03=(Button)findViewById(R.id.button3);
        
        Btn_04=(Button)findViewById(R.id.button4);
        Btn_05=(Button)findViewById(R.id.button5);
        Btn_01.setEnabled(false);
        Btn_02.setEnabled(false);
        Btn_04.setEnabled(false);
        Btn_05.setEnabled(false);
        
		words_now=words_00;
		if(mode==1)words_now=words_01;
		if(mode==2)words_now=words_02;
        
        //see the meaning
        Btn_01.setOnClickListener(
			new OnClickListener(){
				@Override
				public void onClick(View v){
					//Toast.makeText(HanziActivity.this,meaning,Toast.LENGTH_SHORT).show();
					AlertDialog.Builder builder = new AlertDialog.Builder(CaractereActivity.this);
					builder.setMessage(meaning);
					//builder.setTitle(R.string.app_name);
					builder.setPositiveButton(getString(R.string.Close),new DialogInterface.OnClickListener() {
						      public void onClick(DialogInterface dialog, int which) {
						    	  return;
						      }
						      });
					builder.create().show();
				}
			}
        );
        //add to learned list
        Btn_02.setOnClickListener(
			new OnClickListener(){
				@Override
				public void onClick(View v){
					Toast.makeText(CaractereActivity.this,getString(R.string.Congrat),Toast.LENGTH_SHORT).show();
					String[] str=words_00;
					if(mode==1)str=words_01;
					if(mode==2)str=words_02;
					str[now_index]=getLearn(str[now_index]);
					
				}
			}
        );
        //Start!
        Btn_03.setOnClickListener(
			new OnClickListener(){
				@Override
				public void onClick(View v){
					if(Btn_01.isEnabled() == false){
						Btn_01.setEnabled(true);
						Btn_02.setEnabled(true);
						Btn_04.setEnabled(true);
						Btn_05.setEnabled(true);
						Btn_03.setEnabled(false);
						Text_01.setTextColor(getResources().getColor(R.color.COL_Char));
						Text_01.setBackgroundColor(getResources().getColor(R.color.COL_Char_bg));
					}
					
					while(getField(words_now[now_index],2).equals("1")){
						now_index++;
					}
					
					chinese_char=getField(words_now[now_index],0);
					meaning=getField(words_now[now_index],1);
					Text_01.setText(chinese_char);
				}
			}
        );
        //to the previous
        Btn_04.setOnClickListener(
			new OnClickListener(){
				@Override
				public void onClick(View v){
					int len=words_now.length;
					do{
						now_index--;
						now_index+=len;
						now_index%=len;
					}
					while(getField(words_now[now_index],2).equals("1"));
					
					chinese_char=getField(words_now[now_index],0);
					meaning=getField(words_now[now_index],1);
					Text_01.setText(chinese_char);
				}
			}
        );
        //to the next
        Btn_05.setOnClickListener(
			new OnClickListener(){
				@Override
				public void onClick(View v){
					int len=words_now.length;
					do{
						now_index++;
						now_index%=len;
					}
					while(getField(words_now[now_index],2).equals("1"));
					
					chinese_char=getField(words_now[now_index],0);
					meaning=getField(words_now[now_index],1);
					Text_01.setText(chinese_char);
				}
			}
        );
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	switch (keyCode){
    	case KeyEvent.KEYCODE_MENU:
    		showListDialog();
    		break;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    //Make the menu
	private void showListDialog(){  
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        builder.setTitle(getString(R.string.Menu));  

        builder.setItems(R.array.Menu_Item, new DialogInterface.OnClickListener() {
        	String cont="";
			File AppDir=new File(SDPath + getString(R.string.dir_name));
    		File file00 =new File(AppDir + "/" + getString(R.string.File_00));
    		File file01 =new File(AppDir + "/" + getString(R.string.File_01));
    		File file02 =new File(AppDir + "/" + getString(R.string.File_02));
    		File file_mode =new File(AppDir + "/" + getString(R.string.File_mode));
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch(which){
				case 0://change hard mode
					AlertDialog.Builder builder = new AlertDialog.Builder(CaractereActivity.this);
					builder.setMessage("Coming soon~");
					builder.setPositiveButton(getString(R.string.Close),new DialogInterface.OnClickListener() {
						      public void onClick(DialogInterface dialog, int which) {
						    	  return;
						      }
						      });
					builder.create().show();
					break;
				case 1://show the learned list
					AlertDialog.Builder learned = new AlertDialog.Builder(CaractereActivity.this);
					learned.setTitle(getString(R.string.Learned));
					for(int i=0;i<words_00.length;i++){
						if(getField(words_00[i],2).equals("1")){
							cont+=getField(words_00[i],0);
							cont+=" ";
						}
					}

					learned.setMessage(cont);
					learned.create().show();
					break;
				case 2://reset
		    		//delete all temp files and director
					ifReset=true;
		    		file00.delete();
		    		file01.delete();
		    		file02.delete();
		    		file_mode.delete();
		    		AppDir.delete();
		    		break;
				case 3://save & quit
					//if the application has reseted,then quit directly
					if(ifReset)
						System.exit(0);
		    		//delete the old temporary files
		    		file00.delete();
		    		file01.delete();
		    		file02.delete();
		    		file_mode.delete();
		    		//write new temporary files
	        		try{
	        			FileOutputStream fos00 = new FileOutputStream(file00);
	        			FileOutputStream fos01 = new FileOutputStream(file01);
	        			FileOutputStream fos02 = new FileOutputStream(file02);
	        			FileOutputStream fos = new FileOutputStream(file_mode);
	        			fos.write(Integer.toString(mode).getBytes());

	        			for(int i=0;i<words_00.length;i++){
	        				fos00.write(words_00[i].getBytes());
	        				fos00.write("\n".getBytes());
	        			}
	        			for(int i=0;i<words_01.length;i++){
	        				fos01.write(words_01[i].getBytes());
	        				fos01.write("\n".getBytes());
	        			}
	        			for(int i=0;i<words_02.length;i++){
	        				fos02.write(words_02[i].getBytes());
	        				fos02.write("\n".getBytes());
	        			}
	        			fos00.close();
	        			fos01.close();
	        			fos02.close();
	        			fos.close();
	        		}catch(Exception e){
	        		}
	        		System.exit(0);
					break;
				}
				
			}
		});
 
        builder.create().show();  
	}  


    
}