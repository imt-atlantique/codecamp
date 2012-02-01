package eu.telecombretagne.codecamp2011.reve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashTelecom extends Activity{
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telecom);
        Handler x = new Handler();
        x.postDelayed(new splashhandler(),2500);
    }
    class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(SplashTelecom.this,reveMain.class));
            SplashTelecom.this.finish();
            overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        } 
    }
}