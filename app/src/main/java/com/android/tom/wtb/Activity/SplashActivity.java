package com.android.tom.wtb.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.android.tom.wtb.R;
import com.avos.avoscloud.AVUser;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().hide();
        delay();
    }

    @UiThread
    public void gotoM(){
        if(AVUser.getCurrentUser()!=null && !TextUtils.isEmpty(AVUser.getCurrentUser().getUuid())){
            startActivity(new Intent(SplashActivity.this, MainActivity_.class));
        }else{
            startActivity(new Intent(SplashActivity.this, LoginActivity_.class));
        }
        this.finish();
    }

    @Background
    public void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gotoM();
    }

}
