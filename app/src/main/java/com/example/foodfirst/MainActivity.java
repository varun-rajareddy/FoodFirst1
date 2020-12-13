package com.example.foodfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.foodfirst.models.RetrofitInstance;

import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = "MainActivity";
    public static final String KEY_NAME1 = "name";
    public static final String KEY_NAME2 = "number";
    public static final String PREFNAME = "pref";

    public EditText personName;
    public EditText personNumber;

    //public FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        personName = findViewById(R.id.personName);
        personNumber = findViewById(R.id.personNumber);

        Button LoginBtn= (Button) findViewById(R.id.loginbtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginDetails();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }


    public void loginDetails() {

        String name = personName.getText().toString();
        String number = personNumber.getText().toString();

        Call<String> call = RetrofitInstance.getClient().login(
                name,
                number
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_LONG).show();

                openActivity2();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error! :"+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}