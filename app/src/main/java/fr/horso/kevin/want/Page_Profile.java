package fr.horso.kevin.want;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;


public class Page_Profile extends AppCompatActivity  {

    private Button btn_2_deconection;
    private Button btn_Mon_Profile;
    private Button btn_R_Discorde;
    private Button btn_vendre_2;
    private Button btn_condiscion;
    private Button btn_favirie;
    private ImageView Retourne4;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__profile);






        ///imare retourne
        this.Retourne4 = (ImageView) findViewById(R.id.Retourne4);
        Retourne4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                finish();

            }
        });




        ////code  renvois vers modifi information
      this.btn_Mon_Profile = (Button) findViewById(R.id.btn_Mon_Profile);
        btn_Mon_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Mdifier_Information.class);
                startActivity(otherActivity);

            }
        });


        ///declaration firebasse
        mAuth = FirebaseAuth.getInstance();

        ///code button deconnection
        this.btn_2_deconection = (Button) findViewById(R.id.btn_2_deconection);
        btn_2_deconection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent mainactivitylogin2 = new Intent(getApplicationContext(),MainActivityLogin.class);
                startActivity(mainactivitylogin2);
                finish();
            }
        });



        ///button renvois une invitation discorde
        this.btn_R_Discorde = (Button) findViewById(R.id.btn_R_Discorde);
        btn_R_Discorde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://discord.gg/hf3ZCCj"));
                startActivity(intent);
                finish();
            }
        });

        /// code button renvoie sur la page vendre
        this.btn_vendre_2 = (Button) findViewById(R.id.btn_vendre_2);
        btn_vendre_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityvendre2 = new Intent(getApplicationContext(),Vendre.class);
                startActivity(activityvendre2);

            }
        });


        /// button condition renvois sur la page condition dutilisation
        this.btn_condiscion = (Button) findViewById(R.id.btn_condiscion);
        btn_condiscion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activitycondition = new Intent(getApplicationContext(),Condition_dutilisation.class);
                startActivity(activitycondition);

            }
        });

        /// renvois sur la page favorit
        this.btn_favirie = (Button) findViewById(R.id.btn_favirie);
        btn_favirie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activpfavori = new Intent(getApplicationContext(),Page_Favorite.class);
                startActivity(activpfavori);
            }
        });





    }




}
