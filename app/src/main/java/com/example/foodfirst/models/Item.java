
package com.example.foodfirst.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Item {

    @SerializedName("id")
    private String mId;
    @SerializedName("imageurl")
    private String mImageurl;
    @SerializedName("name")
    private String mName;
    @SerializedName("price")
    private String mPrice;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImageurl() {
        return mImageurl;
    }

    public void setImageurl(String imageurl) {
        mImageurl = imageurl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

}
