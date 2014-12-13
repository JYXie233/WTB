package com.android.tom.wtb.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.android.tom.wtb.R;
import com.android.tom.wtb.Common.BaseActivity;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity{
    @ViewById
     EditText etName;
    @ViewById
     EditText etPwd;
    @ViewById
     EditText etEmail;
    @Click
    public void btnRegister(View v){
        AVUser user = new AVUser();
        user.setUsername(etName.getText().toString());
        user.setPassword(etPwd.getText().toString());
        user.setEmail(etEmail.getText().toString());

// 其他属性可以像其他AVObject对象一样使用put方法添加
        user.put("phone", "213-253-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // successfully
                    show("Register Success");
                    startActivity(new Intent(RegisterActivity.this, MainActivity_.class));
                } else {
                    // failed
                    show("Register Faile");
                }
            }
        });
    }
}
