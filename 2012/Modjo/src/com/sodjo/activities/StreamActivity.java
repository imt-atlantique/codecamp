package com.sodjo.activities;

import java.util.ArrayList;
import java.util.HashMap;

import com.sodjo.R;
import com.sodjo.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class StreamActivity extends Activity {
	
	private ViewPager mPager;
	private static int NB_VIEWS = 2;
	private StreamPagerAdapter mPagerAdapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stream);
        
        mPagerAdapter = new StreamPagerAdapter();
        mPager = (ViewPager) findViewById(R.id.streamsPanels);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);
    }
    
    private void populateGeoStream(View stream){
    	// List des donn√©es de chaque ment de la liste des flux
    	ArrayList<HashMap<String, String>> stream_data = new ArrayList<HashMap<String, String>>();
    	
    	// Un HashMap par item
    	HashMap<String, String> item_data;
    	
    	item_data = new HashMap<String, String>();
    	item_data.put("user_image", String.valueOf(R.drawable.moi));
    	item_data.put("action", "Supm4n a atteint le niveau \"Danger Above\" sur Angry bird");
    	item_data.put("zone", "Télécom Bretagne");
    	
    	stream_data.add(item_data);
    	
    	item_data = new HashMap<String, String>();
    	item_data.put("user_image", String.valueOf(R.drawable.tele4l));
    	item_data.put("action", "John a téléchargé Fruit Slice");
    	item_data.put("zone", "ISEN");
    
    	stream_data.add(item_data);
    	
    	item_data = new HashMap<String, String>();
    	item_data.put("user_image", String.valueOf(R.drawable.yo));
    	item_data.put("action", "2xyo devient le nouveau Boss à Tank Hero");
    	item_data.put("zone", "Maisel de Télécom Bretagne - Batiment I3");
    	
    	stream_data.add(item_data);
    	
    	item_data = new HashMap<String, String>();
    	item_data.put("user_image", String.valueOf(R.drawable.moi));
    	item_data.put("action", "Supm4n suit maintenant 2xyo");
    	item_data.put("zone", "Télécom Bretagne");
    	
    	stream_data.add(item_data);
    	
    	 //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mAdapter = new SimpleAdapter (this.getBaseContext(), stream_data, R.layout.stream_item,
               new String[] {"user_image", "action", "zone"}, new int[] {R.id.user_image, R.id.action, R.id.zone});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        ((ListView) stream).setAdapter(mAdapter);
    	
    }
    
    private class StreamPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return NB_VIEWS;
		}
		
		public Object instantiateItem(View collection, int position) {
			 
	            LayoutInflater inflater = (LayoutInflater) collection.getContext()
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            
	            int resId = 0;
	            View view = null;
	            View stream_list = null;
	                      
	            switch (position) {
		            case 0:
		            	resId = R.layout.geo_stream;
		            	view = inflater.inflate(resId, null);
   	
		                break;   
		                
		            case 1:                
		                resId = R.layout.friend_stream;
		                view = inflater.inflate(resId, null);

		                break;
	            }
	            
	            
	            ((ViewPager) collection).addView(view, 0);
	            return view;
	    }
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return arg0 == ((View) arg1);
		}
    	
    }
}