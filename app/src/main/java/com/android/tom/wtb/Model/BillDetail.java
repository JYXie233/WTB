package com.android.tom.wtb.Model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by tom on 14/12/12.
 */
@AVClassName("BillDetail")
public class BillDetail extends AVObject{
    public void setUserId(String userId){
        put("userId",userId);
    }
    public String getUserId(){
        return getString("userId");
    }

    public String getDate(){
        return getString("date");
    }
    public void setDate(String date){
        put("date", date);
    }
    public String getTitle(){
        return getString("title");
    }
    public void setTitle(String title){
        put("title", title);
    }

    public int getPrice(){
        return getInt("price");
    }
    public void setPrice(int price){
        put("price", price);
    }
}
