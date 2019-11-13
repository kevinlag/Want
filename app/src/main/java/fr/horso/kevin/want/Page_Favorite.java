package fr.horso.kevin.want;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Page_Favorite extends AppCompatActivity {
    private ImageView Retourne_F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__favorite);


        ///imare retourne
        this.Retourne_F = (ImageView) findViewById(R.id.Retourne_F);
        Retourne_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                finish();

            }
        });
    }
}
