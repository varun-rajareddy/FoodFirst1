package com.example.foodfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodfirst.models.RetrofitInstance;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        getName();



        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });


    }

    private void getName() {

        Call<String> call = RetrofitInstance.getClient().getUserName();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                retriveName.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(Activity2.this, "Error Retrieving Name", Toast.LENGTH_SHORT).show();
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