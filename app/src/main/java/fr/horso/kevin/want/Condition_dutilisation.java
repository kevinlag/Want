package fr.horso.kevin.want;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Condition_dutilisation extends AppCompatActivity {

    private ImageView image_retourne2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_dutilisation);


        ///code image retourne
        this.image_retourne2 = (ImageView) findViewById(R.id.image_retourne2);
        image_retourne2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Page_Profile.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }
}
