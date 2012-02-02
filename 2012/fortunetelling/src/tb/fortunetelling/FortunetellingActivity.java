package tb.fortunetelling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FortunetellingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) this.findViewById(R.id.startButton);
		button.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(FortunetellingActivity.this, image.class);
				startActivity(intent);
				FortunetellingActivity.this.finish();
			}
		});
    }
}