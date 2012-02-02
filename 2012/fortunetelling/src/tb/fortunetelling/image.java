package tb.fortunetelling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class image extends Activity {
	
	java.util.Random r=new java.util.Random();
	int type=r.nextInt(65);
	
	private int[] baguaIds = new int[] { 
            R.drawable.bagua1, R.drawable.bagua2, R.drawable.bagua3, R.drawable.bagua4, 
            R.drawable.bagua5, R.drawable.bagua6, R.drawable.bagua7, R.drawable.bagua8,
            R.drawable.bagua9, R.drawable.bagua10, R.drawable.bagua11, R.drawable.bagua12,
            R.drawable.bagua13, R.drawable.bagua14, R.drawable.bagua15, R.drawable.bagua16,
            R.drawable.bagua17, R.drawable.bagua18, R.drawable.bagua19, R.drawable.bagua20,
            R.drawable.bagua21, R.drawable.bagua22, R.drawable.bagua23, R.drawable.bagua24,
            R.drawable.bagua25, R.drawable.bagua26, R.drawable.bagua27, R.drawable.bagua28,
            R.drawable.bagua29, R.drawable.bagua30, R.drawable.bagua31, R.drawable.bagua32,
            R.drawable.bagua33, R.drawable.bagua34, R.drawable.bagua35, R.drawable.bagua36,
            R.drawable.bagua37, R.drawable.bagua38, R.drawable.bagua39, R.drawable.bagua40,
            R.drawable.bagua41, R.drawable.bagua42, R.drawable.bagua43, R.drawable.bagua44,
            R.drawable.bagua45, R.drawable.bagua46, R.drawable.bagua47, R.drawable.bagua48,
            R.drawable.bagua49, R.drawable.bagua50, R.drawable.bagua51, R.drawable.bagua52,
            R.drawable.bagua53, R.drawable.bagua54, R.drawable.bagua55, R.drawable.bagua56,
            R.drawable.bagua57, R.drawable.bagua58, R.drawable.bagua59, R.drawable.bagua60,
            R.drawable.bagua61, R.drawable.bagua62, R.drawable.bagua63, R.drawable.bagua64,
            };
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        
        showPhoto(type);
        
        Button button = (Button) this.findViewById(R.id.explainButton);
		button.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(image.this, result.class);
				Bundle bundle = new Bundle();
				bundle.putInt("type", type);
				intent.putExtras(bundle);
				startActivity(intent);
				image.this.finish();
			}
		});
		
		Button button1 = (Button) this.findViewById(R.id.returnButton);
		button1.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(image.this, FortunetellingActivity.class);
				startActivity(intent);
				image.this.finish();
			}
		});
		
    }
    
    private void showPhoto(int type) {
        ImageView imageView = (ImageView) findViewById(R.id.preview1);
        imageView.setImageResource(baguaIds[type]);
    }

}
