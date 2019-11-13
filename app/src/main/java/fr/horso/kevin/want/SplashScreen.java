package fr.horso.kevin.want;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 4000;
    InterstitialAd mAdView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        /// methode pube
        MobileAds.initialize(this,"ca-app-pub-2983017470343859~2617788894");
        mAdView1 = new InterstitialAd(SplashScreen.this);
        mAdView1.setAdUnitId(getString(R.string.interstitial_id_image));
        AdRequest adRequest = (new AdRequest.Builder().build());
        mAdView1.loadAd(adRequest);
        mAdView1.setAdListener(new AdListener(){

            @Override
            public void onAdLoaded() {
                displayInterstitial ();

            }
        });
        mAdView1.show();






        //Redirige vers la page principalle "MainActivityLogin" aprés 7 seconde
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // demarrer une page
                Intent  intent = new Intent(getApplicationContext(),MainActivityLogin.class);
                startActivity(intent);
                finish();


            }
        }, SPLASH_SCREEN_TIMEOUT);



    }



    /// methode intersticiel
    public void  displayInterstitial () {
        if (mAdView1.isLoaded()){
            mAdView1.show();
        }

    }
}
