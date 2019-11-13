package fr.horso.kevin.want;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Page_Cathegorie extends AppCompatActivity {

    private ImageView Rtourne_Ch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__cathegorie);






        ///imare retourne
        this.Rtourne_Ch = (ImageView) findViewById(R.id.Rtourne_Ch);
        Rtourne_Ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                finish();

            }
        });

    }
}
