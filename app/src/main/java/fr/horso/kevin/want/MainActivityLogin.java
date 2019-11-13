package fr.horso.kevin.want;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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


public class MainActivityLogin extends AppCompatActivity {



    private TextView TexInscription;
    private TextView TexteMot_de_passe_oubliée;
    private EditText userLoginEmail,userLoginMotdepasse;
    private ProgressBar userLoginProgressBar;
    private Button btnConnectiion;
    private FirebaseAuth mAuth ;
    private Intent MainActivity;
    private InterstitialAd mAdView3;
    private GoogleApiClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    private Button btn_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);









        ///pube  intersticial
        MobileAds.initialize(this,"ca-app-pub-2983017470343859~2617788894");
        mAdView3 = new InterstitialAd(MainActivityLogin.this);
        mAdView3.setAdUnitId(getString(R.string.interstitial_Video));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest);
        mAdView3.show();





        ///declaration id
        userLoginEmail = (EditText) findViewById(R.id.userLoginEmail);
        userLoginMotdepasse = (EditText) findViewById(R.id.userLoginMotdepasse);
        userLoginProgressBar = (ProgressBar) findViewById(R.id.userLoginProgressBar);
        btnConnectiion = (Button) findViewById(R.id.btnConnectiion);



        ////test




        /// decraration firebase
        mAuth = FirebaseAuth.getInstance();

        /// declare que lutilisateur  sera renvoyer sur cete page aprait verrification :)
        MainActivity = new Intent(this, fr.horso.kevin.want.MainActivity.class);



        ///visibilite progres barre
        userLoginProgressBar.setVisibility(View.INVISIBLE);

        ///clique bouton connection
        btnConnectiion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLoginProgressBar.setVisibility(View.INVISIBLE);
                btnConnectiion.setVisibility(View.VISIBLE);

                final String Email = userLoginEmail.getText().toString();
                final String password = userLoginMotdepasse.getText().toString();

                if (Email.isEmpty() || password.isEmpty()) {
                    showMessage("veuillez vérifier vos informations");


                }
                else
                {
                    singnIn (Email,password);

                }


            }
        });






        this.btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivityLogin.this);

                View view = LayoutInflater.from(MainActivityLogin.this).inflate(R.layout.dialog_user,null);
                TextView title = (TextView) view.findViewById(R.id.textView32);
                TextView email = (TextView) view.findViewById(R.id.editText2f);
                TextView name = (TextView) view.findViewById(R.id.editText3ff);
                Button tbnd = (Button) view.findViewById(R.id.button2ffez);


                alert.setView(view);
                alert.show();

            }

        });





        /////code texte mot de passe oubie renvois sur la page ActivityMotdepasseoublie
        this.TexteMot_de_passe_oubliée = (TextView) findViewById(R.id.TexteMot_de_passe_oubliée);
        TexteMot_de_passe_oubliée.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),ActivityMotdepaseoublie.class);
                startActivity(otherActivity);
            }
        });


//////// texte inscription renvois sur la page Activityinscription
        this.TexInscription = (TextView) findViewById(R.id.TexInscription);
        TexInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivityInscription.class);
                startActivity(otherActivity);

            }
        });


        ///////


    }

    /// methode verifie les  information de connection et permet de se connecter
    private void singnIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // methode permet de rendre visible la progresse barre et rend invisible le button
                if (task.isSuccessful()) {
                    userLoginProgressBar.setVisibility(View.INVISIBLE);
                    btnConnectiion.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else
                    showMessage(task.getException().getMessage());
                {
                    if (mAdView3.isLoaded()){
                        mAdView3.show();
                    }
                }
            }
        });
    }

    /// methode redirection aprais verification des information
    private void updateUI() {
        startActivity(MainActivity);
        finish();
    }

    ////utilisateur reste connecter
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            ///
            //l'utilisateur est déjà connecté, nous devons donc le rediriger vers la page MainActivity

            updateUI();
        }

    }



    ///metthode show message pour affichrer et dure du styl de message un message
    private void showMessage(String texte) {
        Toast.makeText(getApplicationContext(),texte,Toast.LENGTH_LONG).show();
    }
}
