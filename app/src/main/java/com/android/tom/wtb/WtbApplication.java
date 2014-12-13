package com.android.tom.wtb;

import android.app.Application;

import com.android.tom.wtb.Model.BillDetail;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import org.androidannotations.annotations.EApplication;

/**
 * Created by tom on 14/12/11.
 */
@EApplication
public class WtbApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AVObject.registerSubclass(BillDetail.class);
        AVOSCloud.isDebugLogEnabled();
        AVOSCloud.initialize(this, "6w38lo6blornidm3eiihnoo8cfq4bs4av6cxqzqg9uo6sxx9", "m2d5c9wa9ug73ehaku8xsvkck0nz6cgx4o52wh3snj1d0dbk");
    }
}
