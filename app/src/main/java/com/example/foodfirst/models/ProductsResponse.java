
package com.example.foodfirst.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProductsResponse {

    @SerializedName("items")
    private List<Item> mItems;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("res")
    private String mRes;

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getRes() {
        return mRes;
    }

    public void setRes(String res) {
        mRes = res;
    }

}
