package eu.telecombretagne.codecamp2011.reve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{
	/** Called when the activity is first created. */
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Handler x = new Handler();
        x.postDelayed(new splashhandler(),3500);
    }
    class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(SplashActivity.this,SplashTelecom.class));
            SplashActivity.this.finish();
            overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        }
        
    }
}
