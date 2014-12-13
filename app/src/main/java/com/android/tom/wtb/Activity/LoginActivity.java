package com.android.tom.wtb.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.android.tom.wtb.R;
import com.android.tom.wtb.Common.BaseActivity;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by tom on 14/12/11.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity{
    @ViewById(R.id.activity_login_et_name)
    EditText etName;
    @ViewById(R.id.activity_login_et_pwd)
    EditText etPwd;

    @Click(R.id.activity_login_btn_login)
    public void loginAction(View v){

        AVUser.logInInBackground(etName.getText().toString(), etPwd.getText().toString(), new LogInCallback() {
            public void done(AVUser user, AVException e) {
                if (user != null) {
                    // 登录成功
                    startActivity(new Intent(LoginActivity.this, MainActivity_.class));
                } else {
                    // 登录失败
                }
            }
        });
    }

    @Click(R.id.activity_login_btn_register)
    public void gotoRegister(View v ){
        startActivity(new Intent(LoginActivity.this, RegisterActivity_.class));
    }
}
