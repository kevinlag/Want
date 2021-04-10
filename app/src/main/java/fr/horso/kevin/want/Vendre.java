package fr.horso.kevin.want;





import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;


public class Vendre extends AppCompatActivity {

    private ImageView newuserimage;
    private EditText newuserdes;
    private EditText newuserprix;
    private EditText newusertitre;
    private Button btndesconfirme;

    private Uri postImageUri = null ;
    private StorageReference mStorageRef;
    private FirebaseFirestore firebaseFirestore ;
    private FirebaseAuth mAuth;
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendre);


        newuserimage = (ImageView) findViewById(R.id.New_User_image);
        newuserdes = (EditText) findViewById(R.id.New_User_Des);
        newuserprix = (EditText) findViewById(R.id.New_User_Prix);
        newusertitre = (EditText) findViewById(R.id.New_User_Titre);
        btndesconfirme = (Button) findViewById(R.id.Btn_Des_confirme);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();



        btndesconfirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String titre = newusertitre.getText().toString();
                final String prix = newuserprix.getText().toString();
                final String desc = newuserdes.getText().toString();





                if (!TextUtils.isEmpty(desc) && postImageUri != null){

                    String randomName = FieldValue.serverTimestamp().toString();

                    StorageReference filePath = mStorageRef.child("post_annonce").child(randomName + ".jpg");
                    filePath.putFile(postImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            if (task.isSuccessful()){


                                String downloadUri = task.getResult().getStorage().toString();

                                Map<String,Object> postMap = new HashMap<>();
                                postMap.put("image_uri",downloadUri);
                                postMap.put("desc",desc);
                                postMap.put("titre",titre);
                                postMap.put("prix ",prix);
                                postMap.put("user_id",userID);
                                postMap.put("temps",FieldValue.serverTimestamp());

                                firebaseFirestore.collection("post_annonce").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                        if (task.isSuccessful()){



                                            Intent mainIntent = new Intent(Vendre.this,MainActivity.class);
                                            startActivity(mainIntent);
                                            finish();

                                        }


                                    }


                                });

                            }
                        }
                    });


                }
            }
        });



        /// code choisi une image ou prendre une photo
        newuserimage = (ImageView) findViewById(R.id.New_User_image);
        newuserimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(Vendre.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(Vendre.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(Vendre.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setMinCropResultSize(512, 512)
                                .setAspectRatio(1, 1)
                                .start(Vendre.this);
                    }

                }

            }
        });

    }




    /// code choisi une image ou prendre une photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                newuserimage.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }
    }
}








