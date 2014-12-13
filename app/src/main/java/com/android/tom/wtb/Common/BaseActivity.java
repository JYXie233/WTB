package com.android.tom.wtb.Common;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;


/**
 * Created by tom on 14/12/11.
 */
public abstract class BaseActivity extends ActionBarActivity implements SwipeBackActivityBase {
    protected void show(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

        private SwipeBackActivityHelper mHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mHelper = new SwipeBackActivityHelper(this);
            mHelper.onActivityCreate();
        }

        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            mHelper.onPostCreate();
        }

        @Override
        public View findViewById(int id) {
            View v = super.findViewById(id);
            if (v != null)
                return v;
            return mHelper.findViewById(id);
        }

        @Override
        public SwipeBackLayout getSwipeBackLayout() {
            return mHelper.getSwipeBackLayout();
        }
        @Override
        public void setSwipeBackEnable(boolean enable) {
            getSwipeBackLayout().setEnableGesture(enable);
        }

        @Override
        public void scrollToFinishActivity() {
            getSwipeBackLayout().scrollToFinishActivity();
        }

}
