package com.sodjo.activities;


import com.sodjo.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class TabsActivity extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        // Create a Intent to lauch an Activity for the lab
        intent = new Intent().setClass(this,StreamActivity.class);
        spec = tabHost.newTabSpec("streams").setIndicator("Flux", res.getDrawable(R.drawable.ic_tab_stream)).setContent(intent);
        tabHost.addTab(spec);
      
        // Tab : Zones
        intent = new Intent().setClass(this,ZonesActivity.class);
        spec = tabHost.newTabSpec("zones").setIndicator("Zones", res.getDrawable(R.drawable.ic_tab_zone)).setContent(intent);
        tabHost.addTab(spec);
        
        // Tab : Games
        intent = new Intent().setClass(this,GamesActivity.class);
        spec = tabHost.newTabSpec("games").setIndicator("Jeux", res.getDrawable(R.drawable.ic_tab_zone)).setContent(intent);
        tabHost.addTab(spec);
                   
        // Tab : Profile
        intent = new Intent().setClass(this,ProfileActivity.class);
        spec = tabHost.newTabSpec("profile").setIndicator("Profil", res.getDrawable(R.drawable.ic_tab_zone)).setContent(intent);
        tabHost.addTab(spec);  
        
        tabHost.setCurrentTab(0);
        
    }
}