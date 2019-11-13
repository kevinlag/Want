package fr.horso.kevin.want;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Page_Registre_Adresse extends AppCompatActivity {

    private EditText fullName ,AdresL1 ,AdresL2, Aville ,Acpostal,AEmail;
    private Button AenregistreA;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__registre__adresse);


        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        fullName = (EditText) findViewById(R.id.fullName);
        AdresL1 = (EditText) findViewById(R.id.AdresL1);
        AdresL2 = (EditText) findViewById(R.id.AdresL2);
        Aville = (EditText) findViewById(R.id.Aville);
        Acpostal = (EditText) findViewById(R.id.Acpostal);
        AenregistreA = (Button) findViewById(R.id.AenregistreA);
        AEmail = (EditText) findViewById(R.id.AEmail);


        AenregistreA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveAdresseInformation();
            }
        });
    }

    private void SaveAdresseInformation() {

        String fullname =  fullName.getText().toString();
        String adres1 =  AdresL1.getText().toString();
        String adres2 =  AdresL2.getText().toString();
        String ville =  Aville.getText().toString();
        String codepostale =  Acpostal.getText().toString();
        String email =  AEmail.getText().toString();

        if(TextUtils.isEmpty(fullname))
        {
            Toast.makeText(this, "Please write your username...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(adres1))
        {
            Toast.makeText(this, "Please Adresse 1 ...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(adres2))
        {
            Toast.makeText(this, "Please Adresse 2...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(ville))
        {
            Toast.makeText(this, "Please vile...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(codepostale))
        {
            Toast.makeText(this, "Please codepostale...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please email..", Toast.LENGTH_SHORT).show();
        }
        else
        {


            HashMap userMap = new HashMap();
            userMap.put("fullname",fullname);
            userMap.put("adres1",adres1);
            userMap.put("adres2",adres2);
            userMap.put("ville",ville);
            userMap.put("codepostale",codepostale);
            userMap.put("email",email);
            UsersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful())
                    {
                        ///methode redirire aprait enregistrement des info
                        Redirectionpageprofile();

                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(Page_Registre_Adresse.this, "veuillez vérifier vos informations" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    ///methode redirire aprait enregistrement des info
    private void Redirectionpageprofile() {
        Intent MainActivity = new Intent(getApplicationContext(), Mdifier_Information.class);
        startActivity(MainActivity);
        finish();
    }
}
