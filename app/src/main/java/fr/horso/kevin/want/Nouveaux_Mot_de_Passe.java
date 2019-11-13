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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Nouveaux_Mot_de_Passe extends AppCompatActivity {

    private Button btn_confirme_Nouveaux_passe;
    private EditText Nouve_Passe2;

    private ImageView image_retourne;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveaux__mot_de__passe);

        ///id
        Nouve_Passe2 = (EditText) findViewById(R.id.Nouve_Passe2);



        ///code image retourne
        this.image_retourne = (ImageView) findViewById(R.id.image_retourne);
        image_retourne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Page_Profile.class);
                startActivity(otherActivity);
                finish();
            }
        });


        /// code button btn_confirme_Nouveaux_passe
        btn_confirme_Nouveaux_passe = (Button) findViewById(R.id.btn_confirme_Nouveaux_passe);
        btn_confirme_Nouveaux_passe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = Nouve_Passe2.getText().toString();



                if (TextUtils.isEmpty(userEmail))
                {
                    //message d'erreure
                    Toast.makeText(Nouveaux_Mot_de_Passe.this, "veuillez vérifier vos informations", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    /// operation reusit un mail de renisialisation est envoyer
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                //message verification reusit
                                Toast.makeText(Nouveaux_Mot_de_Passe.this, "verifier votre boite mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Nouveaux_Mot_de_Passe.this,MainActivity.class));

                                /// si reusite button devien invisible & la progres barre devien visible// ne marche pas
                                btn_confirme_Nouveaux_passe.setVisibility(View.VISIBLE);


                            }
                            else
                            {
                                ///automatique message d'erreur
                                String message = task.getException().getMessage();
                                Toast.makeText(Nouveaux_Mot_de_Passe.this, "veuillez vérifier vos informations" + message, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });



        /// declaration firebasse
        mAuth = FirebaseAuth.getInstance();

    }
}
