package org.traducteuranimaux.fr;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Traducteur extends Activity implements TextToSpeech.OnInitListener{
    /** Called when the activity is first created. */
	private Button startEnregistrer;
	private Button stopEnregistrer;
	private Button playVoix;
    private static final String TAG = "TextToSpeechDemo";
    private TextToSpeech mTts;
	private int duree =0;// to give a value after record
	private Time startTime;
	private Time stopTime;
	private int positionOfAnimal=-1;// to be improved the characteristics of TextToSpeech can be configured base on this value
	ImageView imageView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traducteur);
    
	    //create buttons    
	    startEnregistrer=(Button) findViewById(R.id.start);
	    startEnregistrer.setOnClickListener(startListener);
	    stopEnregistrer=(Button) findViewById(R.id.stop);
	    stopEnregistrer.setOnClickListener(stopListener);
	    playVoix=(Button) findViewById(R.id.play);
	    playVoix.setOnClickListener(playListener);
	    imageView = new ImageView(this);  
	    imageView.setImageResource(R.drawable.monde_animaux);
	    
        // Initialize text-to-speech. This is an asynchronous operation.
        // The OnInitListener (second argument) is called after initialization completes.
        mTts = new TextToSpeech(this,
            this  // TextToSpeech.OnInitListener
            );
        positionOfAnimal=this.getIntent().getExtras().getInt("position");

    }
    
    
    //create listeners
    private OnClickListener startListener = new OnClickListener() {
        public void onClick(View v) {  
        	startTime= new Time();
        	MediaPlayer mp = MediaPlayer.create(Traducteur.this, R.raw.button25);
            mp.start();
            Toast.makeText(Traducteur.this, "Start recording", Toast.LENGTH_LONG).show();
          
        }
    };
    
    private OnClickListener stopListener = new OnClickListener() {
        public void onClick(View v) {
        	stopTime=new Time();
        	MediaPlayer mp = MediaPlayer.create(Traducteur.this, R.raw.button14);
            mp.start();
            Toast.makeText(Traducteur.this, "Stop recording", Toast.LENGTH_SHORT).show();
        }
    };
    
    // The button is disabled in the layout.
    // It will be enabled upon initialization of the TTS engine.
    private OnClickListener playListener = new OnClickListener() {
        public void onClick(View v) {
        	sayHello();
        	//alert.show();
        }
    };
    
    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }

        super.onDestroy();
    }
    
 // Implements TextToSpeech.OnInitListener.
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            //int result = mTts.setLanguage(Locale.US);
            // Try this someday for some interesting results.
            int result=mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
               // Language data is missing or the language is not supported.
                Log.e(TAG, "Language is not available.");
            } else {
                // Check the documentation for other possible result codes.
                // For example, the language may be available for the local,
                // but not for the specified country and variant.

                // The TTS engine has been successfully initialized.
                // Allow the user to press the button for the app to speak again.
                playVoix.setEnabled(true);
                // Greet the user.
                //sayHello();
            }
        } else {
            // Initialization failed.
            Log.e(TAG, "Could not initialize TextToSpeech.");
        }
    }

    private static final Random RANDOM = new Random();

   private String[] shortSentenses= {"bonjour","ça va?","je veux manger","je veux faire dodo","tu est con","tu m'énerve","coucou, tout le monde"};
   //private String[] mediumSentenses= {	   };
   
   private String[] longSentenses={
		   
		   "bon nouvel, mon ami, Qu'est-ce tu préfère? Du poulet ou du porc?",
		   "J'ai bien mangé, j'ai bien bu; j'ai la ventre bien tendu, merci, petit Jésus.",
		   "Quelle soirée tu aime le plus, moi, je préfère la soirée pink.",
		   "Je suis timide, ne me regarde pas, je vais faire mon boulot.",
		   "Je veux faire pipi, ne me regarde pas?",
		   "Oh la la la la, tu peut me comprendre maintenant",
		   "Il faut beaux aujourd'hui, je veux faire dodo",
		   "Tu sais? Je vais devenir un papa bientôt",
		   "Quelle catastrophe, je suis cocu",
		   "Je suis en train de draguer une poule",
		   "J'ai trop faim, je veux me bourrer",
		   "C'est quoi? ce petit truc là. Moi, je ne l'aime pas",
		   "ça me fait peur, ton téléphone. Je te propose de le jeter.",
		   "C'est moche, ton petit truc, il faut changer un nouveau."
		   
   };

    private void sayHello(){
    	
    	//depend on time span of record, choose the sentences from different categories 
    	String hello;
        duree=Time.compare(startTime,stopTime);
       	if(positionOfAnimal==3)//just use this exception for the fun of demonstration
       		hello="ça fait dix mois que j'ai pas fait l'amour avec ma copine. Et toi, tous se passent bien ?";	
       	else if(duree<3 && duree>1)
    		hello=shortSentenses[RANDOM.nextInt(shortSentenses.length)];
    	else if(duree<20){
    		hello=longSentenses[RANDOM.nextInt(longSentenses.length)];
    	}
    	else{
    		hello="Vous n'avez pas bien appuyé le bouton soit c'est trop cours soit c'est trop long, merci de réssayer";
    	}
    	// dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(hello)
            .setCancelable(false)
            .setTitle(R.string.dialog_title)
            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                }
            });
        final AlertDialog alert = builder.create();
        alert.show();
        
        //use the TextToSpeech to read the sentence 
        mTts.speak("Traduction . "+hello,TextToSpeech.QUEUE_FLUSH, null);     

    };
    
    
}      
       