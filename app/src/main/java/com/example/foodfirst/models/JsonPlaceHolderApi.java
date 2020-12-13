package com.example.foodfirst.models;

import com.example.foodfirst.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.example.foodfirst.Activity3.KEY_NAME3;
import static com.example.foodfirst.Activity3.KEY_NAME4;
import static com.example.foodfirst.MainActivity.KEY_NAME1;
import static com.example.foodfirst.MainActivity.KEY_NAME2;

public interface JsonPlaceHolderApi {
    @POST("login")
    @FormUrlEncoded
    Call<String> login(
            @Field(KEY_NAME1) String name,
            @Field(KEY_NAME2) String num
    );


    @POST("order")
    @FormUrlEncoded
    Call<String> order(
            @Field(KEY_NAME3) String name,
            @Field(KEY_NAME4) String num
    );

    @GET("getUsername")
    Call<String> getUserName(

    );

    @GET("getItems")
    Call<ProductsResponse> getProducts();

}
