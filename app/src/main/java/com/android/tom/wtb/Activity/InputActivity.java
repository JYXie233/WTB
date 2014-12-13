package com.android.tom.wtb.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.tom.wtb.Common.BaseActivity;
import com.android.tom.wtb.Common.Constants;
import com.android.tom.wtb.Model.BillDetail;
import com.android.tom.wtb.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.easyandroidanimations.library.BounceAnimation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_input)
public class InputActivity extends BaseActivity {

    @ViewById
    EditText price;
    @ViewById
    EditText title;
    @ViewById
    Button submit;

    private String date;
    @AfterViews
    public void init(){
        date = getIntent().getStringExtra(Constants.DATE);
        new BounceAnimation(price).animate();
    }

    @Click
    public void submit(View view){
        BillDetail billDetail = new BillDetail();
        billDetail.setUserId(AVUser.getCurrentUser().getObjectId());
        billDetail.setDate(date);
        billDetail.setTitle(title.getText().toString());
        billDetail.setPrice(Integer.parseInt(price.getText().toString()));
        billDetail.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){
                    show("Success");
                }else {
                    show("Error");
                }
            }
        });
    }
}
