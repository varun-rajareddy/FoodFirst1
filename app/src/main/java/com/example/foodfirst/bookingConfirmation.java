package com.example.foodfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodfirst.models.RetrofitInstance;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.foodfirst.Activity3.KEY_NAME3;
import static com.example.foodfirst.Activity3.KEY_NAME4;


public class bookingConfirmation extends AppCompatActivity {

   // public FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView retriveName;
    TextView orderName;
    TextView orderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);


        retriveName = findViewById(R.id.retriveName2);
        orderName = findViewById(R.id.orderedName);
        orderPrice = findViewById(R.id.orderedPrice);

        orderName.setText(getIntent().getStringExtra("orderedItem"));
        orderPrice.setText(getIntent().getStringExtra("orderedItemPrice"));
        getName();

        }



//
//    public void order() {
//        db.collection("loginPerson").document("OrderedDetails")
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()) {
//                    String orderedName = documentSnapshot.getString(KEY_NAME3);
//                    String orderedPrice = documentSnapshot.getString(KEY_NAME4);
//                    orderName.setText(orderedName);
//                    orderPrice.setText("and price is "+orderedPrice);
//                } else {
//                    Toast.makeText(bookingConfirmation.this, "Document does not exist", Toast.LENGTH_SHORT).show();
//                }
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), "Error! :"+e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//
//    }
    private void getName() {

        Call<String> call = RetrofitInstance.getClient().getUserName();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                retriveName.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(bookingConfirmation.this, "Error Retrieving Name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}