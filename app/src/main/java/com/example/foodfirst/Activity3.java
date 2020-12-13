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

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity3 extends AppCompatActivity {
    public static final String KEY_NAME3 = "Selected_Item_Name";
    public static final String KEY_NAME4 = "Selected_Item_Price";

    Button buy1;
    Button buy2;
    Button buy3;

    TextView itemName1;
    TextView itemName2;
    TextView itemName3;

    TextView itemPrice1;
    TextView itemPrice2;
    TextView itemPrice3;

    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        itemName1 = findViewById(R.id.itemName1);
        itemName2 = findViewById(R.id.itemName2);
       itemName3 = findViewById(R.id.itemName3);
        itemPrice1 = findViewById(R.id.itemPrice1);
        itemPrice2 = findViewById(R.id.itemPrice2);
       itemPrice3 = findViewById(R.id.itemPrice3);


        Button buy1= (Button) findViewById(R.id.btnBuy1);
        Button buy2= (Button) findViewById(R.id.btnBuy2);
        Button buy3= (Button) findViewById(R.id.btnBuy3);


        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata1(itemName1.getText().toString(),itemPrice1.getText().toString());

                    //openBookingConfirmation();

            }
        });
        buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata1(itemName2.getText().toString(),itemPrice2.getText().toString());

                //openBookingConfirmation();
            }
        });
        buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata1(itemName3.getText().toString(),itemPrice3.getText().toString());


            }
        });
    }

    public void openBookingConfirmation(String orderedItem, String orderedItemPrice) {

        Intent intent4 = new Intent(this,bookingConfirmation.class);
        intent4.putExtra("orderedItem",orderedItem);
        intent4.putExtra("orderedItemPrice",orderedItemPrice);
        startActivity(intent4);
    }

    public void savedata1(String OrderedItem,String OrderedItemPrice){
        Map<String, Object> details = new HashMap<>();
        details.put(KEY_NAME3 , OrderedItem);
        details.put(KEY_NAME4 , OrderedItemPrice);

        Call<String> call = RetrofitInstance.getClient().order(
                OrderedItem,
                OrderedItemPrice
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Activity3.this, "Ordered", Toast.LENGTH_LONG).show();
                openBookingConfirmation(OrderedItem,OrderedItemPrice);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

    }

//    public void savedata3() {
//        String OrderedItem = itemName3.getText().toString();
//        String OrderedItemPrice = itemPrice3.getText().toString();
//
//        Map<String, Object> details = new HashMap<>();
//        details.put(KEY_NAME3 , OrderedItem);
//        details.put(KEY_NAME4 , OrderedItemPrice);
//        db.collection("loginPerson").document("OrderedDetails")
//                .set(details)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(Activity3.this, "Ordered", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), "Error! :"+e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//
//    }
}