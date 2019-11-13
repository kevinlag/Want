package fr.horso.kevin.want;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityMotdepaseoublie extends AppCompatActivity {

    private ImageView Retourne3;
    private TextView texteconnection;
    private TextView textCreeuncompte;
    private FirebaseAuth mAuth;
    private EditText userTexteMotdePasse;
    private Button btn_Confirme_Mot_de_Passse_oublier;
    private ProgressBar user_progressBar_Mot_de_passe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motdepaseoublie);


        /// declaration id
        userTexteMotdePasse = (EditText) findViewById(R.id.userTexteMotdePasse);
        btn_Confirme_Mot_de_Passse_oublier = (Button)  findViewById(R.id.btn_Confirme_Mot_de_Passse_oublier);
        user_progressBar_Mot_de_passe = (ProgressBar)  findViewById(R.id.user_progressBar_Mot_de_passe);

        ///visibilite progres barre
        user_progressBar_Mot_de_passe.setVisibility(View.INVISIBLE);


        //click button confirmation
        btn_Confirme_Mot_de_Passse_oublier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = userTexteMotdePasse.getText().toString();

                ///visibilite progresbarre et button
                user_progressBar_Mot_de_passe.setVisibility(View.INVISIBLE);
                btn_Confirme_Mot_de_Passse_oublier.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(userEmail))
                {
                    //message d'erreure
                    Toast.makeText(ActivityMotdepaseoublie.this, "veuillez vérifier vos informations", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    /// operation reusit un mail de renisialisation est envoyer
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                //message verification reusit
                                Toast.makeText(ActivityMotdepaseoublie.this, "verifier votre boite mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ActivityMotdepaseoublie.this,MainActivityLogin.class));

                                /// si reusite button devien invisible & la progres barre devien visible// ne marche pas
                                    user_progressBar_Mot_de_passe.setVisibility(View.VISIBLE);
                                    btn_Confirme_Mot_de_Passse_oublier.setVisibility(View.INVISIBLE);


                            }
                            else
                            {
                                ///automatique message d'erreur
                                String message = task.getException().getMessage();
                                Toast.makeText(ActivityMotdepaseoublie.this, "veuillez vérifier vos informations" + message, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });



        /// declaration firebasse
        mAuth = FirebaseAuth.getInstance();





   ////////code fleche du haut qui renvois sur loginpage
        this.Retourne3 = (ImageView) findViewById(R.id.Retourne3);
        Retourne3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivityLogin.class);
                startActivity(otherActivity);
                finish();
            }
        });

        /////code texte (se connecter)
        this.texteconnection = (TextView) findViewById(R.id.texteconnection);
        texteconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivityLogin.class);
                startActivity(otherActivity);
                finish();
            }
        });

        ////code texte (cree un compte) de la page mot de passe oublie
        this.textCreeuncompte = (TextView) findViewById(R.id.textCreeuncompte);
        textCreeuncompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivityInscription.class);
                startActivity(otherActivity);
                finish();
            }
        });


    }
}
