package fr.horso.kevin.want;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivityInscription extends AppCompatActivity {


    private final int SPLASH_SCREEN_PUBE = 4000;

    private ImageView Retourne1;
    private  EditText userRegistreMotdePasse1,userConfirmeMotdePasse;
    private EditText ueserRegistreEmail;
    private Button btnRegistrreSoumetre;
    private ProgressBar RegistreprogressBar;
    private InterstitialAd mAdView2;
    private  FirebaseAuth mAuth;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inscription);





        //Redirige vers la page  "MainActivity" aprés 6 seconde
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        }, SPLASH_SCREEN_PUBE);








        ///pube  intersticial
        MobileAds.initialize(this,"ca-app-pub-2983017470343859~2617788894");
        mAdView2 = new InterstitialAd(MainActivityInscription.this);
        mAdView2.setAdUnitId(getString(R.string.interstitial_Video));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest);
        mAdView2.show();






        ////code fleche du haut retourne login page
        this.Retourne1 = (ImageView) findViewById(R.id.Retourne1);
        Retourne1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivityLogin.class);
                startActivity(otherActivity);
                finish();
            }
        });


        /////// declaration id
        ueserRegistreEmail = (EditText) findViewById( R.id.ueserRegistreEmail);
        userRegistreMotdePasse1 = (EditText) findViewById(R.id.userRegistreMotdePasse1);
        userConfirmeMotdePasse = (EditText) findViewById(R.id.userConfirmeMotdePasse);
        btnRegistrreSoumetre = (Button) findViewById(R.id.btnRegistrreSoumetre);
        RegistreprogressBar = (ProgressBar) findViewById(R.id.RegistreprogressBar);







        /// declaration firebase autentification
        mAuth = FirebaseAuth.getInstance();




        ///visibilite progressbar
        RegistreprogressBar.setVisibility(View.INVISIBLE);



        /// click button soumettre et auvre une pube intersticiel et enregistre lutilisateur
        btnRegistrreSoumetre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                btnRegistrreSoumetre.setVisibility(View.INVISIBLE);
                RegistreprogressBar.setVisibility(View.VISIBLE);
                final String email = ueserRegistreEmail.getText().toString();
                final String passeword1 = userRegistreMotdePasse1.getText().toString();
                final String passeword2 = userConfirmeMotdePasse.getText().toString();



                if (email.isEmpty()   || passeword1.isEmpty() || !passeword1.equals(passeword2) ) {



                    /// afficher un message d'erreur
                    showMessage("veuillez vérifier vos informations");

                    ///ici visibilite button et progressebarre
                    btnRegistrreSoumetre.setVisibility(View.VISIBLE);
                    RegistreprogressBar.setVisibility(View.INVISIBLE);

                }
                else {
                    ///Tout est ok et tout rempli maintenant peut commencer à créer un compte utilisateur
                    createUserAccount(email,passeword1);


                }










            }
        });



    }




    ///metthode creation utilisateur
    private void createUserAccount(String email ,String passeword1) {
        mAuth.createUserWithEmailAndPassword(email,passeword1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {



                    ///utilisateur cree avec suces
                    /// metthode renvois sur la page principale
                    SendUserToSetupActivity();
                    updateUi();
                }
                else
                {
                    //compte non cree
                    showMessage("la cree ation du compre a echoer" + task.getException().getMessage());
                    btnRegistrreSoumetre.setVisibility(View.VISIBLE);
                    RegistreprogressBar.setVisibility(View.INVISIBLE);
                }

                {

                    if (mAdView2.isLoaded()){
                        mAdView2.show();

                    }

                }



            }
        });

    }

    private void SendUserToSetupActivity() {
        Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(MainActivity);
        finish();
    }


    /// renvois sur la page priscipalle (MainActivity)
    private void updateUi() {
        Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(MainActivity);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            ///
            //l'utilisateur est déjà connecté, nous devons donc le rediriger vers la page MainActivity
            updateUi();
        }


    }

    ///metthode show message pour affichrer un message
    private void showMessage(String message ) {

        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG).show();
    }
}
