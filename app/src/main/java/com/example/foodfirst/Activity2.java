package com.example.foodfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodfirst.models.loginPerson;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import static com.example.foodfirst.MainActivity.KEY_NAME1;
import static com.example.foodfirst.MainActivity.KEY_NAME2;

public class Activity2 extends AppCompatActivity {

    TextView retriveName;
    Button menubtn;

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        retriveName = findViewById(R.id.retriveName);
        menubtn = findViewById(R.id.menubtn);

        retriveName.setText(getSharedPreferences(MainActivity.PREFNAME,MODE_PRIVATE).getString(KEY_NAME1,""));

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });


    }
    public void openActivity3() {

        Intent intent3 = new Intent(this,Activity3.class);
        startActivity(intent3);
    }

//    public void getName() {
//        db.collection("loginPerson").document("logindetails")
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()) {
//                    String name = documentSnapshot.getString(KEY_NAME1);
//                    retriveName.setText(name);
//                } else {
//                    Toast.makeText(Activity2.this, "Document does not exist", Toast.LENGTH_SHORT).show();
//                }
//            }
//        })
//        .addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//               Toast.makeText(getApplicationContext(), "Error! :"+e.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

}