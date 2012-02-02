package tb.fortunetelling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class result extends Activity {

	int type=0;
	
	private int[] baguaIds = new int[] { 
			R.string.bagua1, R.string.bagua2, R.string.bagua3, R.string.bagua4,
			R.string.bagua5, R.string.bagua6, R.string.bagua7, R.string.bagua8,
			R.string.bagua9, R.string.bagua10, R.string.bagua11, R.string.bagua12,
			R.string.bagua13, R.string.bagua14, R.string.bagua15, R.string.bagua16,
			R.string.bagua17, R.string.bagua18, R.string.bagua19, R.string.bagua20,
			R.string.bagua21, R.string.bagua22, R.string.bagua23, R.string.bagua24,
			R.string.bagua25, R.string.bagua26, R.string.bagua27, R.string.bagua28,
			R.string.bagua29, R.string.bagua30, R.string.bagua31, R.string.bagua32,
			R.string.bagua33, R.string.bagua34, R.string.bagua35, R.string.bagua36,
			R.string.bagua37, R.string.bagua38, R.string.bagua39, R.string.bagua40,
			R.string.bagua41, R.string.bagua42, R.string.bagua43, R.string.bagua44,
			R.string.bagua45, R.string.bagua46, R.string.bagua47, R.string.bagua48,
			R.string.bagua49, R.string.bagua50, R.string.bagua51, R.string.bagua52,
			R.string.bagua53, R.string.bagua54, R.string.bagua55, R.string.bagua56,
			R.string.bagua57, R.string.bagua58, R.string.bagua59, R.string.bagua60,
			R.string.bagua61, R.string.bagua62, R.string.bagua63, R.string.bagua64,};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        Bundle bunde = this.getIntent().getExtras();
        type=bunde.getInt("type");
        
        showText(type);
        
        Button button = (Button) this.findViewById(R.id.restartButton);
		button.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(result.this, FortunetellingActivity.class);
				startActivity(intent);
				result.this.finish();
			}
		});
    }
    
    private void showText(int type) {
    	TextView textView = (TextView) findViewById(R.id.status_text);
    	textView.setText(baguaIds[type]);
    }
}
