package com.android.tom.wtb.Activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.tom.wtb.Common.Constants;
import com.android.tom.wtb.Model.BillDetail;
import com.android.tom.wtb.R;
import com.android.tom.wtb.Adapter.MainRecyclerAdapter;
import com.android.tom.wtb.Common.BaseActivity;
import com.android.tom.wtb.Utils.DateUtil;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.FlipHorizontalAnimation;
import com.easyandroidanimations.library.ParallelAnimator;
import com.easyandroidanimations.library.ScaleInAnimation;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById
    RecyclerView recyclerView;
    MainRecyclerAdapter mAdapter;
    private List<BillDetail> list = new ArrayList<BillDetail>();
    @AfterViews
    public void init(){
        mAdapter = new MainRecyclerAdapter(list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(headersDecoration);
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecoration);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Intent intent = new Intent(MainActivity.this, InputActivity_.class);
                        intent.putExtra(Constants.DATE, DateUtil.dateWith(headerId));
                        startActivity(intent);
                    }
                });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });
        recyclerView.addOnItemTouchListener(touchListener);

    }


    @Override
    protected void onResume() {
        super.onResume();

        AVQuery<BillDetail> query = AVObject.getQuery(BillDetail.class);
        query.whereEqualTo("userId", AVUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<BillDetail>() {
            @Override
            public void done(List<BillDetail> results, AVException e) {
                if (e == null) {
                    list.clear();
                    list.addAll(results);
                    mAdapter.notifyDataSetChanged();
                    if(list.size() == 0){
                        Intent intent = new Intent(MainActivity.this, InputActivity_.class);
                        intent.putExtra(Constants.DATE, DateUtil.dateWith(new Date().getTime()));
                        startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(MainActivity.this, InputActivity_.class);
                    intent.putExtra(Constants.DATE, DateUtil.dateWith(new Date().getTime()));
                    startActivity(intent);
                }
            }
        });
    }


}
