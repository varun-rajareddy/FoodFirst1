package com.example.foodfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodfirst.models.Item;
import com.example.foodfirst.models.ProductsResponse;
import com.example.foodfirst.models.RetrofitInstance;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

    ListView list;
    ArrayList<Item> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        arrayList = new ArrayList<>();

        list = findViewById(R.id.list);

       getItems();




    }

    private void getItems() {
        Call<ProductsResponse> call = RetrofitInstance.getClient().getProducts();
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {


                arrayList.addAll(response.body().getItems());
                list.setAdapter(new CustomAdapter(Activity3.this,arrayList));
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

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






    public void savedata3() {
        String OrderedItem = itemName3.getText().toString();
        String OrderedItemPrice = itemPrice3.getText().toString();

        Map<String, Object> details = new HashMap<>();
        details.put(KEY_NAME3 , OrderedItem);
        details.put(KEY_NAME4 , OrderedItemPrice);
        db.collection("loginPerson").document("OrderedDetails")
                .set(details)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Activity3.this, "Ordered", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error! :"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

    }

    private class CustomAdapter extends BaseAdapter {
        Activity activity;
        ArrayList<Item> arrayList;
        public CustomAdapter(Activity activity, ArrayList<Item> arrayList) {
            this.activity = activity;
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.row_items,viewGroup,false);

            Item item
                    = arrayList.get(i);
            TextView name = view.findViewById(R.id.name);
            TextView price = view.findViewById(R.id.price);
            ImageView image = view.findViewById(R.id.image);
            Button but = view.findViewById(R.id.btnbuy);

            name.setText(item.getName());
            price.setText(item.getPrice());
            Picasso.get().load(item.getImageurl()).into(image);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    savedata1(item.getName(),item.getPrice());
                }
            });
            return view;
        }
    }
}