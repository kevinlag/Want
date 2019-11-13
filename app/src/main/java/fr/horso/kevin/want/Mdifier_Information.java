package fr.horso.kevin.want;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Mdifier_Information extends AppCompatActivity {

    private Button Btn_User_Adress;
    private Button Btn_nouveaux_passe;
    private TextView User_Name_M;
    private TextView User_Mail_M;
    private TextView UserAdresV1;
    private TextView UserAdresV2;
    private TextView Ville;
    private TextView Codeposte;

    String currentUserID;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdifier__information);


        User_Name_M = (TextView) findViewById(R.id.User_Name_M);
        User_Mail_M = (TextView) findViewById(R.id.User_Mail_M);
        UserAdresV1 = (TextView) findViewById(R.id.userAdresV1);
        UserAdresV2 = (TextView) findViewById(R.id.UserAdresV2);
        Ville = (TextView) findViewById(R.id.Ville);
        Codeposte = (TextView) findViewById(R.id.Codeposte);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");



        UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String fullemail = dataSnapshot.child("email").getValue().toString();
                    String fullname = dataSnapshot.child("fullname").getValue().toString();
                    String adress1 = dataSnapshot.child("adres1").getValue().toString();
                    String adress2 = dataSnapshot.child("adres2").getValue().toString();
                    String ville = dataSnapshot.child("ville").getValue().toString();
                    String codpostale = dataSnapshot.child("codepostale").getValue().toString();

                    User_Name_M.setText(fullname);
                    User_Mail_M.setText(fullemail);
                    UserAdresV1.setText(adress1);
                    Ville.setText(ville);
                    UserAdresV2.setText(adress2);
                    Codeposte.setText(codpostale);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        /// code button renvois sur la page registre adresse
        this.Btn_User_Adress = (Button) findViewById(R.id.Btn_User_Adress);
        Btn_User_Adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Page_Registre_Adresse.class);
                startActivity(otherActivity);

            }
        });


        /// code button revois sur la page Nouveaux Mot de pasese
        this.Btn_nouveaux_passe = (Button) findViewById(R.id.Btn_nouveaux_passe);
        Btn_nouveaux_passe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Nouveaux_Mot_de_Passe.class);
                startActivity(otherActivity);

            }
        });





    }
}
