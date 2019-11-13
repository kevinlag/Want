package fr.horso.kevin.want;







import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;


import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.ads.AdRequest;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {




     private Button btn_vendre_1;
     private Button btn_Parcourir;
     private Button btn_Favorite;
     private Button btn_Profile;
    private AdView mAdView;
    private GridView postView;
    private RecyclerView list_view;
    private List <PastView>post_layout ;
    FirebaseAuth mAuth;
    SwipeRefreshLayout swipeRefreshLayout;
    String currentUserID;
    private static final String TAG = "MainActivity";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chekConnection();

        ///code rafraichire la page
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip_refreshLayout);





        /// code pube banner
        mAdView = (AdView) findViewById(R.id.adView_B_1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ///declaration firebasse
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();


        ///test
        post_layout = new ArrayList<>();




        /// code gridview
        this.postView = (GridView) findViewById(R.id.postView_R);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);






        /// code button btn_Parcourir cathegorie
        this.btn_Parcourir = (Button) findViewById(R.id.btn_Parcourir);
        btn_Parcourir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainactivitypageC = new Intent(getApplicationContext(),Page_Cathegorie.class);
                startActivity(mainactivitypageC);

            }
        });



        /// code btn_Message ouvre page Messagerie
        this.btn_Favorite = (Button) findViewById(R.id.btn_Favorite);
        btn_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityMessage = new Intent(getApplicationContext(), Page_Favorite.class);
                startActivity(activityMessage);

            }
        });


        /// ouvre page Profile
        this.btn_Profile = (Button) findViewById(R.id.btn_Profile);
        btn_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityMessage = new Intent(getApplicationContext(),Page_Profile.class);
                startActivity(activityMessage);

            }
        });

        // ouvre page vendre
        this.btn_vendre_1 = (Button) findViewById(R.id.btn_vendre_1);
        btn_vendre_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainactivitypageC = new Intent(getApplicationContext(),Vendre.class);
                startActivity(mainactivitypageC);

            }
        });










    }



    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
            /// Metthode renvois sur la page connection si lutilisateur na pas de compte
            SendUserToLoginActivity();
        }

    }

    /// renvois sur la page connection si lutilisateur na pas de compte
    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, MainActivityLogin.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }


    public void chekConnection(){

        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork){



        }
        else  {

            Nointernete();

        }

    }

    private void Nointernete() {
        Intent MainActivity = new Intent(getApplicationContext(), CheckConnection.class);
        startActivity(MainActivity);
        finish();
    }

}
